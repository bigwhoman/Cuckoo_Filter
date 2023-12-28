import java.util.Scanner;
import java.io.File;
import java.util.BitSet;
import java.util.UUID;
public class Main {

    public static void main(String[] args) {
        ScalableCuckoo cuckoo = new ScalableCuckoo(2, 4, 500, 0.05,2);
        cuckoo.insert("hell");
        cuckoo.insert("hool");
        cuckoo.insert("hill");
        cuckoo.insert("still");
        cuckoo.insert("stall");
        cuckoo.insert("stell");

        System.out.println(cuckoo.lookup("stall")); // true
        System.out.println(cuckoo.lookup("sel"));   // false
        System.out.println(cuckoo.lookup("stell")); // true
        System.out.println(cuckoo.lookup("hool"));  // true
        System.out.println(cuckoo.lookup("hell"));  // true
        System.out.println(cuckoo.lookup("gell"));  // false
        System.out.println(cuckoo.lookup("bell"));  // false
        System.out.println(cuckoo.lookup("hill"));  // true
        System.out.println(cuckoo.delete("stall")); // true
        System.out.println(cuckoo.lookup("stall")); // false
        System.out.println(cuckoo.delete("stall")); // false
        return ;
    }

}
