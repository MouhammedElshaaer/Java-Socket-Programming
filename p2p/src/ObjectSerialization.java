import java.io.*;

/*
* This class is to convert any object to a 
* byte array (i.e serialize) and vice versa
* (i.e deserialize)
*/


public class ObjectSerialization {

    static byte[] serialize(Object obj) throws IOException{

        ByteArrayOutputStream bos=new ByteArrayOutputStream();
        ObjectOutputStream out =new ObjectOutputStream(bos);

        try{
            if ( ((String)obj).equals("EOF")){
                out.writeObject("EOF");
                out.close();
            }else {
                out.writeObject(obj);
                out.close();
            }
        }catch (ClassCastException e){
            out.writeObject(obj);
            out.close();
        }

        byte[] buf=bos.toByteArray();

        return buf;
    }
    static Object deserialize(byte[] buf) throws IOException,ClassNotFoundException{

        ObjectInputStream in=new ObjectInputStream(new ByteArrayInputStream(buf));

        Object obj1=in.readObject();

        return obj1;

    }

}
