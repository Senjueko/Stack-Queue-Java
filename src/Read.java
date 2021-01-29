import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Read {
    // Class for read command datas.
    public static List<String[]> data_command; // List for hold command datas.

    public static String[] readfile(String path) {
        // Method for read txt file
        try {
            int i = 0;
            int length = Files.readAllLines(Paths.get(path)).size();
            String[] results = new String[length];
            for (String line : Files.readAllLines(Paths.get(path))) {
                results[i++] = line;
            }
            return results;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<String> main(String[] args) throws IOException {
        // This method for keep datas in the lists.
        Stack.readtxt();
        Queue.readtxt();
        String[] lines = readfile(args[0]);
        List<String[]> newlist = new ArrayList<>();
        for (String line : lines) {
            List<String[]> newlist2 = new ArrayList<>();
            newlist2.add(line.split(" "));

            newlist.addAll(newlist2);
        }
        data_command =  newlist;

        return null;
    }

    public static void readcommands(){
        // Method for reading commands and call correct methods according to datas.
        for (String[] string : data_command){
            if (string[1].equals("removeGreater")){
                if (string[0].equals("Q")){
                    Queue.removeGreater(Integer.parseInt(string[2]));
                }
                if (string[0].equals("S")){
                    Stack.removeGreater(Integer.parseInt(string[2]));
                }
            }
            if (string[1].equals("calculateDistance")){
                if (string[0].equals("Q")){
                    Queue.calculate_distance();
                }
                if (string[0].equals("S")){
                    Stack.calculate_distance();
                }
            }
            if (string[1].equals("addOrRemove")){
                if (string[0].equals("Q")){
                    Queue.add_remove(Integer.parseInt(string[2]));
                }
                if (string[0].equals("S")){
                    Stack.add_remove(Integer.parseInt(string[2]));
                }
            }
            if (string[1].equals("reverse")){
                if (string[0].equals("Q")){
                    Queue.reverse(Integer.parseInt(string[2]));
                }
                if (string[0].equals("S")){
                    Stack.reverse(Integer.parseInt(string[2]));
                }
            }
            if (string[1].equals("sortElements")){
                if (string[0].equals("Q")){
                    Queue.sortElements();
                }
                if (string[0].equals("S")){
                    Stack.sortElements();
                }
            }
            if (string[1].equals("distinctElements")){
                if (string[0].equals("Q")){
                    Queue.distinctElements();
                }
                if (string[0].equals("S")){
                    Stack.distinctElements();
                }
            }
        }
    }

    public static String[] makearraystack(){
        // method for send stack datas to array to write.
        String[] stack_array = new String[Stack.data_stack.size()];
        int i = 0;
        while (!Stack.data_stack.isEmpty()){
            stack_array[i] = String.valueOf(Stack.data_stack.pop());
            i = i + 1;
        }
        return stack_array;
    }

    public static String[] makearrayqueue(){
        // method for send queue datas to array to write.
        String[] queue_array = new String[Queue.data_queue.size()];
        int i = 0;
        while (!Queue.data_queue.isEmpty()){
            queue_array[i] = String.valueOf(Queue.data_queue.poll());
            i = i + 1;
        }
        return queue_array;
    }

    public static void write() throws IOException {
        // Method for writing all datas to correct text files.
        FileWriter writer = new FileWriter("stack.txt");
        writer.write(Arrays.toString(makearraystack()).replace("[","")
                .replace("]","").replace(",","")+ System.lineSeparator());
        writer.close();

        writer = new FileWriter("queue.txt");
        writer.write(Arrays.toString(makearrayqueue()).replace("[","")
                .replace("]","").replace(",","")+ System.lineSeparator());
        writer.close();

        writer = new FileWriter("stackOut.txt");
        for (String[] strings : Stack.data_stack_out){
            writer.write(Arrays.toString(strings).replace("[","")
                    .replace("]","").replace(",","")+ System.lineSeparator());
        }
        writer.close();

        writer = new FileWriter("queueOut.txt");
        for (String[] strings : Queue.data_queue_out){
            writer.write(Arrays.toString(strings).replace("[","")
                    .replace("]","").replace(",","")+ System.lineSeparator());
        }
        writer.close();
    }
}
