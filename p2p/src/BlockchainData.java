import java.io.IOException;

/*
* This class maintains the last state of the Blockchain class
* for the Peer.
*
* Class use cases:
* ________________
*
* 1- whenever the blockchain is updated, the last line of code
*   when updating should be calling the setBlockchain() function
*   of this class to make this class always containing a fresh
*   copy of the blockchain.
* 2- the other use case will just calling the send() function
*   of the PeerClient class which in turn will delegate this
*   class getSerializedData() function to Broadcast your updated
*   blockchain copy to all other peers.
 */



public class BlockchainData implements DataSource {

    static public MockingClass blockchain;

    public              BlockchainData(){
    }//default constructor
    public              BlockchainData(MockingClass _blockchain){
        this.blockchain=_blockchain;
    }//parameterized constructor
    public static void  setBlockchain(MockingClass _blockchain){
        blockchain=_blockchain;
    }//setter

    @Override
    public byte[] getSerializedData() throws IOException {
        if(blockchain.message.equals("EOF")){
            return new byte[0];
        }
        return ObjectSerialization.serialize(this.blockchain);
    }
}
