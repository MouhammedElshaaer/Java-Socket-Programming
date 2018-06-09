import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.Scanner;

public class PeerClient extends Thread {

    protected InetAddress       group;
    protected PeerConfig        peerConfigurations;
    protected int               destPort;
    protected PeerConfig        host;
    protected Socket            socket;
    protected DataSource        data;
    protected DataOutputStream doutputStream;

    //PeerClient constructor
    public PeerClient(int portNumber,
                      PeerConfig _host,
                      DataSource _data,
                      PeerConfig _peerConfig,
                      String _group) throws IOException{

        group               = InetAddress.getByName(_group);
        peerConfigurations  = _peerConfig;
        destPort            = portNumber;
        host                = _host;
        data                = _data;
        doutputStream=null;
    }

    public void run() {

        while (true) {
            try {

                byte[] buf;
                System.out.println("The peer: "+PeerControlUnit.peerConfigurations.getDisplayName()+":"+PeerControlUnit.peerConfigurations.getIp()+
                                    " is connecting with host: "+host.getDisplayName()+":"+host.getIp()+
                                    "\nwould you like to connect [Y/N]");
                Scanner input=new Scanner(System.in);
                String str=input.nextLine();
                socket = new Socket(host.getIp(),destPort);
                buf = data.getSerializedData();
                if (buf.length == 0) {
                    buf = ObjectSerialization.serialize("EOF");
                    doutputStream=new DataOutputStream(socket.getOutputStream());
                    doutputStream.write(buf);
                    doutputStream.flush();
                    doutputStream.close();
                    break;
                }

                QueueModule.addToQueue(ObjectSerialization.deserialize(buf));

                // sending

                doutputStream=new DataOutputStream(socket.getOutputStream());
                doutputStream.write(buf);
                doutputStream.flush();
                doutputStream.close();


            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException c) {
                c.printStackTrace();
            }
        }
    }
}
