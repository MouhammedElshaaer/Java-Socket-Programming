import java.io.IOException;

public class DataStore implements DataSource {

    static public MockingClass object;

    DataStore(){}
    DataStore(MockingClass _object){object=_object;}

    @Override
    public          byte[]  getSerializedData() throws IOException, ClassNotFoundException {
        if(object.message.equals("EOF")){
            return new byte[0];
        }

        return ObjectSerialization.serialize(this.object);
    }
    public static   void    setObjet(MockingClass _object){
        object=_object;
    }//setter
}
