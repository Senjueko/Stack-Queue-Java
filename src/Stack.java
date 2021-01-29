import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;

public class Stack {
    /* In my stack implementation, first element of list represents top element. For example if there is a list like
     [3,4,2,1] , "3" is the top element of stack and "1" is bottom element of stack. */
    public static final int MAX_ELEMENT = 100; // Stack capacity
    private ArrayList<Integer> stack; // stack object
    public static Stack data_stack = new Stack(); // Our main stack.
    public static ArrayList<String[]> data_stack_out = new ArrayList<String[]>(); // ArrayList for write outputs.

    public Stack() {
        // Constructor
        stack = new ArrayList<Integer>(MAX_ELEMENT);
    }

    public static void readtxt() throws IOException {
        // Method for read txt file to push datas to stack.
        Stack dummy = new Stack(); // Dummy stack for hold datas.
        File file = new File("stack.txt");

        BufferedReader br = new BufferedReader(new FileReader(file));

        String st = br.readLine();
        for (String s : st.split(" ")){
            dummy.push(Integer.parseInt(s));
        }
        while (!dummy.isEmpty()){
            data_stack.push(dummy.pop());
        }
    }

    public void push(int number){
        // Push method for stack
        if (!isFull()){
            stack.add(0,number);
        }
    }

    public int pop(){
        // Pop method for stack
        int i = 0;
        i = peek();
        stack.remove(0);
        return i;
    }

    public int peek(){
        // Peek method for stack
        return stack.get(0);
    }

    public boolean isFull(){
        // isfull method for stack
        return size() == 100;
    }

    public boolean isEmpty(){
        // isempty method for stack
        return size() == 0;
    }

    public int size(){
        // size method for stack
        return stack.size();
    }

    public static void add_writeList(){
        // Method for add outputs to Arraylist(data_stack_out).
        Stack backup_stack = new Stack();
        String[] liste1 = new String[data_stack.size()];
        int i = 0;
        while (!data_stack.isEmpty()){
            liste1[i] = String.valueOf(data_stack.peek());
            i = i + 1;
            backup_stack.push(data_stack.pop());
        }
        while(!backup_stack.isEmpty()){
            data_stack.push(backup_stack.pop());
        }
        data_stack_out.add(liste1);
    }

    public static void removeGreater(int number){
        // Method for execute removeGreater operation for stack.
        Stack biggernumber_stack = new Stack(); // Dummy stack for hold bigger numbers than input.
        Stack lessnumber_stack = new Stack(); // Dummy stack for hold less numbers than input.
        String[] liste = {"After removeGreater " + number + ":"};
        data_stack_out.add(liste);
        while (!data_stack.isEmpty()){
            if (data_stack.peek() <= number){
                lessnumber_stack.push(data_stack.pop());
            }
            else if (data_stack.peek() > number){
                biggernumber_stack.push(data_stack.pop());
            }
        }
        while (!lessnumber_stack.isEmpty()){
            data_stack.push(lessnumber_stack.pop());
        }
        add_writeList();
    }

    public static void calculate_distance(){
        // Method for execute calculateDistance operation for stack.
        String[] liste = {"After calculateDistance:"};
        data_stack_out.add(liste);
        ArrayList<ArrayList<Integer>> dummy_array = new ArrayList<ArrayList<Integer>>();
        Queue dummy_queue = new Queue(); // Dummy queue for hold datas.
        while (!data_stack.isEmpty()){
            ArrayList<Integer> dummy = new ArrayList<Integer>();
            dummy.add(data_stack.pop());
            dummy_array.add(dummy);
        }
        int distance = 0;
        for (int i = 0; i < dummy_array.size(); i++){
            for (int j = i + 1; j < dummy_array.size(); j++){
                distance = distance + (Math.abs(dummy_array.get(i).get(0)- dummy_array.get(j).get(0)));
            }
        }
        for (ArrayList<Integer> integers : dummy_array) {
            dummy_queue.enqueue(integers.get(0));
        }
        while (!dummy_queue.isEmpty()){
            data_stack.push(dummy_queue.poll());
        }
        String[] liste1 = {"Total distance=" + distance};
        data_stack_out.add(liste1);
    }

    public static void add_remove(int number){
        // Method for execute addOrRemove operation for stack.
        String[] liste = {"After addOrRemove " + number};
        data_stack_out.add(liste);
        if (number < 0){
            try{
                for (int i = 0; i < Math.abs(number); i++){
                    data_stack.pop();
                }
            }
            catch (IndexOutOfBoundsException ignored){
            }
        }
        if (number > 0){
            for (int i = 0; i < number; i++){
                Random rn = new Random();
                data_stack.push(rn.nextInt(50) + 1);
            }
        }
        add_writeList();
    }

    public static void reverse(int number){
        // Method for execute reverse operation for stack.
        String[] liste = {"After reverse " + number};
        data_stack_out.add(liste);
        Queue dummy_queue = new Queue(); // Dummy queue for reverse first input number elements.
        for (int i = 0; i < number; i++){
            dummy_queue.enqueue(data_stack.pop());
        }
        while (!dummy_queue.isEmpty()){
            data_stack.push(dummy_queue.poll());
        }
        add_writeList();
    }

    public static void sortElements(){
        // Method for execute sortElements operation for stack.
        String[] liste = {"After sortElements:"};
        data_stack_out.add(liste);
        ArrayList<Integer> dummy = new ArrayList<Integer>();
        while (!data_stack.isEmpty()){
            dummy.add(data_stack.pop());
        }
        Collections.sort(dummy);
        for (int i = dummy.size(); i-->0;){
            data_stack.push(dummy.get(i));
        }
        add_writeList();
    }

    public static void distinctElements(){
        // Method for execute distinctElements operation for stack.
        String[] liste = {"After distinctElements:"};
        data_stack_out.add(liste);
        Stack backup_stack = new Stack(); // Backup stack fot hold datas.
        HashSet<Integer> hashSet = new HashSet<Integer>();
        while (!data_stack.isEmpty()){
            hashSet.add(data_stack.peek());
            backup_stack.push(data_stack.pop());
        }
        while(!backup_stack.isEmpty()){
            data_stack.push(backup_stack.pop());
        }
        String[] liste1 = {"Total distinct element=" + hashSet.size()};
        data_stack_out.add(liste1);
    }


}
