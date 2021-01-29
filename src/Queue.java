import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;

public class Queue {

    public static final int MAX_ELEMENT = 100; // Queue capacity
    private ArrayList<Integer> queue; // queue object
    public static Queue data_queue = new Queue(); // Our main queue.
    public static ArrayList<String[]> data_queue_out = new ArrayList<String[]>(); // ArrayList for write outputs.

    public Queue() {
        // Constructor
        queue = new ArrayList<Integer>(MAX_ELEMENT);
    }

    public static void readtxt() throws IOException {
        // Method for read txt file and enqueue datas to queue.
        File file = new File("queue.txt");

        BufferedReader br = new BufferedReader(new FileReader(file));

        String st = br.readLine();
        for (String s : st.split(" ")){
            data_queue.enqueue(Integer.parseInt(s));

        }
    }

    public void enqueue(int number){
        // enqueue method for queue
        if (!isFull()){
            queue.add(queue.size(),number);
        }
    }

    public int poll(){
        // poll method for queue
        int i = peek();
        queue.remove(0);
        return i;
    }

    public int peek(){
        // peek method for queue
        return queue.get(0);
    }

    public boolean isFull(){
        // isfull method for queue.
        return size() == 100;
    }

    public boolean isEmpty(){
        // isempty method for queue
        return size() == 0;
    }

    public int size(){
        // size method for queue
        int length = 0;
        for (int i = 0; i < queue.size(); i++){
            length = length + 1;
        }
        return length;
    }

    public static void add_writeList(){
        // Method for add outputs to Arraylist(data_queue_out).
        Queue backup_queue = new Queue();
        String[] liste1 = new String[data_queue.size()];
        int i = 0;
        while (!data_queue.isEmpty()){
            liste1[i] = String.valueOf(data_queue.peek());
            i = i + 1;
            backup_queue.enqueue(data_queue.poll());
        }
        data_queue = backup_queue;
        data_queue_out.add(liste1);
    }

    public static void removeGreater(int number){
        // Method for execute removeGreater operation for queue.
        Queue biggernumber_queue = new Queue(); // Dummy queue for hold bigger numbers than input.
        Queue lessnumber_queue = new Queue(); // Dummy queue for hold less numbers than input.
        String[] liste = {"After removeGreater " + number + ":"};
        data_queue_out.add(liste);
        while (!data_queue.isEmpty()){
            if (data_queue.peek() <= number){
                lessnumber_queue.enqueue(data_queue.poll());
            }
            else if (data_queue.peek() > number){
                biggernumber_queue.enqueue(data_queue.poll());
            }
        }
        while (!lessnumber_queue.isEmpty()){
            data_queue.enqueue(lessnumber_queue.poll());
        }
        add_writeList();
    }

    public static void calculate_distance(){
        // Method for execute calculateDistance operation for queue.
        String[] liste = {"After calculateDistance:"};
        data_queue_out.add(liste);
        ArrayList<ArrayList<Integer>> dummy_array = new ArrayList<ArrayList<Integer>>();
        while (!data_queue.isEmpty()){
            ArrayList<Integer> dummy = new ArrayList<Integer>();
            dummy.add(data_queue.poll());
            dummy_array.add(dummy);
        }
        int distance = 0;
        for (int i = 0; i < dummy_array.size(); i++){
            for (int j = i + 1; j < dummy_array.size(); j++){
                distance = distance + (Math.abs(dummy_array.get(i).get(0)- dummy_array.get(j).get(0)));
            }
        }
        for (ArrayList<Integer> integers : dummy_array) {
            data_queue.enqueue(integers.get(0));
        }
        String[] liste1 = {"Total distance=" + distance};
        data_queue_out.add(liste1);
    }

    public static void add_remove(int number){
        // Method for execute addOrRemove operation for queue.
        String[] liste = {"After addOrRemove " + number};
        data_queue_out.add(liste);
        if (number < 0){
            try{
                for (int i = 0; i < Math.abs(number); i++){
                    data_queue.poll();
                }
            }
            catch (IndexOutOfBoundsException ignored){
            }
        }
        if (number > 0){
            for (int i = 0; i < number; i++){
                Random rn = new Random();
                data_queue.enqueue(rn.nextInt(50) + 1);
            }
        }
        add_writeList();
    }

    public static void reverse(int number){
        // Method for execute reverse operation for queue.
        String[] liste = {"After reverse " + number};
        data_queue_out.add(liste);
        Queue dummy_queue = new Queue(); // Dummy queue to hold remaining elements than dummy stack.
        Stack dummy_stack = new Stack(); // Dummy stack to hold first input number elements.
        for (int i = 0; i < number; i++){
            dummy_stack.push(data_queue.poll());
        }
        while (!data_queue.isEmpty()){
            dummy_queue.enqueue(data_queue.poll());
        }
        while (!dummy_stack.isEmpty()){
            data_queue.enqueue(dummy_stack.pop());
        }
        while (!dummy_queue.isEmpty()){
            data_queue.enqueue(dummy_queue.poll());
        }
        add_writeList();
    }

    public static void sortElements(){
        // Method for execute sortElements operation for queue.
        String[] liste = {"After sortElements:"};
        data_queue_out.add(liste);
        ArrayList<Integer> dummy = new ArrayList<Integer>();
        while (!data_queue.isEmpty()){
            dummy.add(data_queue.poll());
        }
        Collections.sort(dummy);
        for (Integer integer : dummy) {
            data_queue.enqueue(integer);
        }
        add_writeList();
    }

    public static void distinctElements(){
        // Method for execute distinctElements operation for queue.
        String[] liste = {"After distinctElements:"};
        data_queue_out.add(liste);
        Queue backup_queue = new Queue(); // Backup queue for hold datas.
        HashSet<Integer> hashSet = new HashSet<Integer>();
        while (!data_queue.isEmpty()){
            hashSet.add(data_queue.peek());
            backup_queue.enqueue(data_queue.poll());
        }
        data_queue = backup_queue;
        String[] liste1 = {"Total distinct element=" + hashSet.size()};
        data_queue_out.add(liste1);
    }
}
