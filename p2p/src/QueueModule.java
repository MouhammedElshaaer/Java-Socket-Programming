import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class QueueModule {
    static private Queue<Object> queue = new LinkedList<>();

    public QueueModule(){}
    public QueueModule(Queue<Object> queue) {
        queue = queue;
    }
    public static Queue<Object> getQueue() {
        return queue;
    }
    public static void setQueue(Queue<Object> queue) {
        queue = queue;
    }
    public static void addToQueue(Object element){

        //System.out.println( ((MockingClass)element).message+": "+ ((MockingClass)element).displayName );
        if(queue.size()>PeerControlUnit.MAX_QUEUE_SIZE){
            //write data to DataStore class
        }
        queue=new LinkedList<>();
        queue.add(element);
    }
    public static boolean isEmpty(){
        return queue.isEmpty();
    }
    public static Object pop(){
        return queue.poll();
    }


}
