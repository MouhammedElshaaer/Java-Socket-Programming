import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class PeerPublisher {

    static protected InetAddress        group;
    static protected MulticastSocket    socket;
    static protected int                destPort;
    static protected byte[]             buf;
    static protected DatagramPacket     packet;

    public static void initPeerPublisher(int portNumber) throws IOException{
        group = InetAddress.getByName("230.0.0.1");
        socket  =new MulticastSocket(portNumber);
        destPort=portNumber;
    }
    public static void publish() throws IOException {
        buf=ObjectSerialization.serialize(PeerControlUnit.peerConfigurations);
        packet=new DatagramPacket(buf, buf.length, group, destPort);
        socket.send(packet);
    }

}
