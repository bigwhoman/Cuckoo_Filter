import java.util.Scanner;
import java.io.File;
import java.util.Random;
public class Main {
    static String[] nameSpace;
    static int   nameSpaceSize;
    static int   numberOfInserts;
    static int   numberOfDeletes;
    static int   numberOfLookups;
    static double   errorRate;
    static int   maxKicks;
    static int   bucketEntries;
    static int   buckets;
    static double   alphaRate;


    static Random random = new Random();
    public static void main(String[] args) {
        
        numberOfInserts = parseArguments(args, "-i", 10000);
        numberOfLookups = parseArguments(args, "-l", 100000);
        numberOfDeletes = parseArguments(args, "-d", 10);
        alphaRate = parseDoubleArguments(args, "-a", 2);
        errorRate = parseDoubleArguments(args, "-e", 0.05);
        maxKicks = parseArguments(args, "-k", 5);
        bucketEntries = parseArguments(args, "-b", 4);

        buckets = Double.valueOf(Math.ceil(alphaRate * numberOfInserts / bucketEntries))
                               .intValue();

        nameSpaceSize = numberOfInserts * 1;

        nameSpace = new String[nameSpaceSize];
        Cuckoo cuckoo = new Cuckoo(buckets, bucketEntries, maxKicks, errorRate);
        for(int i = 0 ; i < nameSpaceSize; i++)
            nameSpace[i] = generateRandomName(10);


        // TEST BODY BEGIN    ---------------------
        long startTime = System.nanoTime();
// -------------------------  INSERT ----------------------------
        for(int i = 0; i < numberOfInserts; i++ ){
            String random_name = nameSpace[random.nextInt(nameSpaceSize)];
		    // System.out.println(cuckoo.insert(random_name));
            cuckoo.insert(random_name);
        }


// -------------------------  LOOKUP ----------------------------
        for(int i = 0; i < numberOfLookups; i++ ){
            String random_name = nameSpace[random.nextInt(nameSpaceSize)];
		    // System.out.println(cuckoo.lookup(random_name));
            cuckoo.insert(random_name);
        }


// -------------------------  DELETE ----------------------------
        for(int i = 0; i < numberOfDeletes; i++ ){
            String random_name = nameSpace[random.nextInt(nameSpaceSize)];
		    // System.out.println(cuckoo.delete(random_name));
            cuckoo.insert(random_name);
        }
        // TEST BODY END      ---------------------
        long endTime = System.nanoTime();
        double elapsedTime = (endTime - startTime) / 1_000_000_000.0;
        System.out.println(elapsedTime);
        return ;
    }

    private static int parseArguments(String[] args, String flag, int defaultValue) {
       for (int i = 0; i < args.length; i++) {
           if (args[i].equals(flag)) {
                return Integer.parseInt(args[++i]);
           }
       }
       return defaultValue;
    } 

    private static double parseDoubleArguments(String[] args, String flag, double defaultValue) {
       for (int i = 0; i < args.length; i++) {
           if (args[i].equals(flag)) {
               return Double.parseDouble(args[++i]);
           }
       }
       return defaultValue;
    } 

    private static String generateRandomName(int length) {
       int leftLimit = 97; 
       int rightLimit = 122; 

       StringBuilder buffer = new StringBuilder(length);
       for (int i = 0; i < length; i++) {
           int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
           buffer.append((char) randomLimitedInt);
       }
       String generatedString = buffer.toString();
       return generatedString;
   }
} 
