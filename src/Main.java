import java.util.Scanner;
import java.io.File;
import java.util.BitSet;
import java.util.UUID;
public class Main {

    public static void main(String[] args) {
        Cuckoo cuckoo = new Cuckoo(8, 4, 3, 0.05);
        
        cuckoo.insert("hell");
        cuckoo.insert("hool");
        cuckoo.insert("hill");
        cuckoo.insert("still");
        cuckoo.insert("stall");
        cuckoo.insert("stell");

        System.out.println(cuckoo.lookup("stall"));
        System.out.println(cuckoo.lookup("sel"));
        System.out.println(cuckoo.lookup("stell"));
        System.out.println(cuckoo.lookup("stell"));
        System.out.println(cuckoo.lookup("stell"));
        System.out.println(cuckoo.lookup("hell"));
        System.out.println(cuckoo.lookup("gell"));
        System.out.println(cuckoo.lookup("bell"));
        System.out.println(cuckoo.lookup("hill"));
        System.out.println(cuckoo.delete("stall"));
        System.out.println(cuckoo.lookup("stall"));
        System.out.println(cuckoo.delete("stall"));
        return ;
    }

}
