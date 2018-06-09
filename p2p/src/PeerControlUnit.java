import java.io.IOException;

public class PeerControlUnit {

    static public PeerConfig    peerConfigurations;
    static public DataSource    dataSource;
    static public int           serverPort;
    static public int           groupPort;
    static public String        group;
    static public int           MAX_QUEUE_SIZE;

    public static void initPeerControlUnit(PeerConfig _peerConfigurations,
                                           DataSource _dataSource,
                                           int _serverPort,
                                           int _groupPort,
                                           String _group,
                                           int _MAX_QUEUE_SIZE) throws IOException {
        peerConfigurations=_peerConfigurations;
        dataSource=_dataSource;
        serverPort=_serverPort;
        groupPort=_groupPort;
        group=_group;
        MAX_QUEUE_SIZE=_MAX_QUEUE_SIZE;

        DataStore.setObjet(new MockingClass("The awesome guy",peerConfigurations.getDisplayName()));
        new PeerDiscovery(group,groupPort,peerConfigurations.getIp()).start();
        new PeerServer(serverPort).start();
        PeerPublisher.initPeerPublisher(groupPort);
        PeerPublisher.publish();
    }


}
