import java.util.Scanner;
import java.io.File;

public class Main {

    public static void main(String[] args) {
        Cuckoo cuckoo = new Cuckoo(8, 4, 3, 8);
        
        cuckoo.insert("hell");
        cuckoo.insert("hool");
        cuckoo.insert("hill");
        cuckoo.insert("still");
        cuckoo.insert("stall");
        cuckoo.insert("stell");

        System.out.println(cuckoo.lookup("stall"));
        System.out.println(cuckoo.delete("stall"));
        System.out.println(cuckoo.lookup("stall"));
        return ;
    }

}
