import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class PeerIP {

    static private String peerIP;

    public static String getPeerIP() throws SocketException {
        //This block of code make the peer not receiving from it self
        Enumeration e= NetworkInterface.getNetworkInterfaces();
        String ipAddress="";
        while (e.hasMoreElements()){
            NetworkInterface n = (NetworkInterface) e.nextElement();
            Enumeration ee = n.getInetAddresses();
            while (ee.hasMoreElements())
            {
                InetAddress i = (InetAddress) ee.nextElement();
                ipAddress=i.getHostAddress();
                if(ipAddress.substring(0,3).equals("192")) break;
            }
            if(ipAddress.substring(0,3).equals("192")) break;
        }
        //end of  code make the peer not receiving from it self xD

        return ipAddress;
    }

}
