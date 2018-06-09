import java.io.IOException;
import java.util.Vector;

public class Update {

    static private Vector<PeerConfig> discoveredPeers=new Vector<>(1);

    public static void discoveredPeers() throws IOException {

        try{
            PeerConfig receivedPeer=(PeerConfig)ReceivedObject.getReceivedObject();

            if( (!contains(receivedPeer)) ){
                discoveredPeers.add(receivedPeer);
                printDiscoveredPeers();
                PeerPublisher.publish();
                new PeerClient(
                        PeerControlUnit.serverPort,
                        receivedPeer,
                        PeerControlUnit.dataSource,
                        receivedPeer,
                        PeerControlUnit.group).start();
            }

        }catch (ClassCastException e){
            System.out.println("************** ERROR DISCOVERING PEER **************");
        }
    }
    public static void removeFromDiscoveredPeers(String peerAddress){
        for (PeerConfig p:discoveredPeers) {
            if(p.getIp().equals(peerAddress)){
                discoveredPeers.remove(p);
            }
        }
        printDiscoveredPeers();
    }
    private static boolean contains(PeerConfig peer){
        for (PeerConfig p:discoveredPeers) {

            if( peer.getIp().equals(p.getIp())&&
                peer.getDisplayName().equals(p.getDisplayName()))
            {
                return true;
            }
        }
        return false;
    }
    private static void printDiscoveredPeers(){
        System.out.println("##########################");
        System.out.println("#### Discovered peers ####");
        System.out.println("##########################");
        for (PeerConfig p:discoveredPeers) {
            System.out.println(p.getDisplayName()+": "+p.getIp());
        }
        System.out.println("##########################");
    }

}
