import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class PeerServer extends Thread{

    public int                  port;
    public byte[]               buf;
    public ServerSocket         serverSocket;
    public Socket               client;
    public DataInputStream    dinputStream;

    PeerServer(){}
    PeerServer(int portNumber) throws IOException {
        port=portNumber;
        buf=new byte[256];
        serverSocket=new ServerSocket(port);
        client=null;
        dinputStream=null;
    }

    public void run(){

        while (true){
            try {

                client=serverSocket.accept();
                dinputStream=new DataInputStream(client.getInputStream());

                dinputStream.read(buf);
                Object received=ObjectSerialization.deserialize(buf);
                try {

                    if( ((String)received).equals("EOF")){
                        Update.removeFromDiscoveredPeers(client.getInetAddress().toString());
                        break;
                    }else{
                        QueueModule.addToQueue(received);
                    }

                }catch (ClassCastException c){
                    QueueModule.addToQueue(received);
                }

                System.out.println( ((MockingClass)received).message+": "+((MockingClass)received).displayName );

            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

}
