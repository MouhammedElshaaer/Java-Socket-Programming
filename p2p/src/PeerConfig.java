import java.io.Serializable;

public class PeerConfig implements Serializable{

    private String ip;
    private String displayName;

    PeerConfig(){}
    PeerConfig(String _ip, String _displayName){
        this.ip=_ip;
        this.displayName=_displayName;
    }

    public String getIp() {
        return ip;
    }
    public String getDisplayName() {
        return displayName;
    }
}
