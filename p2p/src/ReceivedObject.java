
/*
* This class all what it does is saving the last received
* Object. This is done in the PeerDiscovery class
*/

public class ReceivedObject {

    static private Object received;

    public static Object getReceivedObject(){
        return received;
    }
    public static void setReceivedObject(Object _received){
        received=_received;
    }


}
