import java.io.PrintStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

public class Main{
    public static void main(String[] args) {
        StringBuilder inputSB = new StringBuilder();
        StringBuilder outputSB = new StringBuilder();

        Cuckoo cuckoo = new Cuckoo(100, 4, 500, 0.05);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String command = scanner.nextLine();
            inputSB.append(command).append("\n");
            if ("exit".equals(command)){
                    break;
            }
            String[] parts = command.split(" ");

            if (parts.length == 2) {
                String operation = parts[0];
                String arg = parts[1];

                if ("insert".equals(operation)) {
                    boolean result = cuckoo.insert(arg);
                    System.out.println(result);
                    outputSB.append(Boolean.toString(result)).append("\n");
                } else if ("lookup".equals(operation)) {
                    boolean result = cuckoo.lookup(arg);
                    System.out.println(result);
                    outputSB.append(Boolean.toString(result)).append("\n");
                } else if ("delete".equals(operation)) {
                    boolean result = cuckoo.delete(arg);
                    System.out.println(result);
                    outputSB.append(Boolean.toString(result)).append("\n");
                } else {
                    System.out.println("Invalid command!");
                }
            } else {
                System.out.println("Invalid command format!");
            }
        }

        BufferedWriter inputWriter = null;
        BufferedWriter outputWriter = null;
        try {
            inputWriter = new BufferedWriter(new FileWriter("inputs.txt"));
            inputWriter.write(inputSB.toString());
            inputWriter.close();

            outputWriter = new BufferedWriter(new FileWriter("outputs.txt"));
            outputWriter.write(outputSB.toString());
            outputWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
