import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class PeerDiscovery extends Thread {

    final protected int MAX_BUFF_LEN = 256;

    protected MulticastSocket   socket;
    protected InetAddress       address ;
    protected DatagramPacket    packet;
    protected String            peerIP;
    protected byte[]            buf;

    //Thread constructor
    PeerDiscovery(String _group, int portNumber, String _peerIP){
        super("PeerDiscovery");
        try {
            address = InetAddress.getByName(_group);
            socket  = new MulticastSocket(portNumber);
            socket.joinGroup(address);
            peerIP=_peerIP;
            buf = new byte[MAX_BUFF_LEN];

        } catch (java.io.IOException e) {
            System.err.println("Could not create multicastSocket socket.");
        }
    }

    public void run() {
        System.out.println("Discovering Peers...");

        while (true) {
            try {

                packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);

                if(!peerIP.equals(packet.getAddress().toString().substring(1))){
                    Object received=ObjectSerialization.deserialize(packet.getData());

                    try {
                        /*
                        * In case we want to terminate the PeerDiscovery.
                        * Note that the else statement is to cover the case
                        * that the received Object is a String but not "EOF".
                         */

                        if( ((String)received).equals("EOF") ) break;
                        else {
                            ReceivedObject.setReceivedObject(received);
                        }
                    }catch (ClassCastException castException){
                        /*
                        * The catch statement handles receiving other types
                        * of objects.
                         */
                        ReceivedObject.setReceivedObject(received);
                        Update.discoveredPeers();
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
                break;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                break;
            }
        }

        //termination
        try {
            socket.leaveGroup(address);
        } catch (IOException e) {
            e.printStackTrace();
        }
        socket.close();
    }

}
