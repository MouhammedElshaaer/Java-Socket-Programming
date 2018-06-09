import java.io.*;

public class main {

    public static void main(String[] args) throws IOException,InterruptedException{

        PeerControlUnit.initPeerControlUnit(new PeerConfig(PeerIP.getPeerIP(),"Elshaaer"),
                                            new DataStore(new MockingClass()),
                                            4447,
                                            4446,
                                            "230.0.0.1",
                                            20);
    }

}
