import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Cuckoo cuckoo = new Cuckoo(8, 4, 3);
        cuckoo.insert("hell");
        // cuckoo.insert("hool");
        // cuckoo.insert("hill");
        // cuckoo.insert("still");
        // cuckoo.insert("stall");
        // cuckoo.insert("stell");

        System.out.println(cuckoo.lookup("hell"));
        return ;
    }

}
