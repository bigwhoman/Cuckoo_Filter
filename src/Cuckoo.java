import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;
import java.util.Formatter;
import java.util.Random;


public class Cuckoo {
    private Integer[][] bucketArray;
    private Integer buckets;
    private Integer bucketEntries;
    private Integer maxKicks;

    // Gets the number of buckets, max entries a bucket could have and maximum number of replacements in
    // case of a number gets thrown out
    public Cuckoo(Integer buckets, Integer bucketEntries, Integer maxKicks){
        this.buckets        = buckets;
        this.bucketEntries  = bucketEntries;
        this.maxKicks       = maxKicks;
        this.bucketArray    = new Integer[buckets][bucketEntries];
    }

    // Inserts string x into the cuckoo data structure
    public boolean insert(String x){
        Integer f   = generateFingerprint(x, 8);

        // The hashes should be modded to the number of buckets
        Integer i1  = (x.hashCode() & 0x7fffffff) % buckets;
        Integer i2  = i1 ^ hash(f) % buckets;
        
        for(Integer entry = 0; entry < bucketEntries ; entry++){
            if(bucketArray[i1][entry] == null){ 
                bucketArray[i1][entry] = f;
                return true;
            }
        }

        for(Integer entry = 0; entry < bucketEntries ; entry++){
            if(bucketArray[i2][entry] == null){
                bucketArray[i2][entry] = f;
                return true;
            }
        }
        Random rand = new Random();
        Integer i = rand.nextInt(2) == 0 ? i1 : i2;

        for(Integer kick = 0; kick < maxKicks; kick++){
            Integer randomBucketEntry = rand.nextInt(bucketEntries);
            Integer bucketEntryFingerprint = bucketArray[i][randomBucketEntry];
            bucketArray[i][randomBucketEntry] = f;
            i = i ^ hash(f) % buckets;
            for(Integer entry = 0; entry < bucketEntries ; entry++){
                if(bucketArray[i][entry] == null){
                    bucketArray[i][entry] = bucketEntryFingerprint;
                    return true;
                }
            }
        }
        // Hash table is full
        return false;
    }

    public boolean lookup(String x){
        Integer f   = generateFingerprint(x, 8);
        Integer i1  = (x.hashCode() & 0x7fffffff) % buckets;
        Integer i2  = i1 ^ hash(f) % buckets; 
        for(Integer entry = 0; entry < bucketEntries ; entry++){
            if(bucketArray[i1][entry] != null && bucketArray[i1][entry].equals(f)){
                return true;
            }
        }

        for(Integer entry = 0; entry < bucketEntries ; entry++){
            if(bucketArray[i2][entry] != null && bucketArray[i2][entry].equals(f)){
                bucketArray[i2][entry] = f;
                return true;
            }
        }
        // Item does not exist
        return false;
    }

    public void delete(String x){

    }
    
    // Generates a unique fingerprint (with len) for a text
    private static Integer generateFingerprint(String text, Integer len) {
        UUID uuid = UUID.nameUUIDFromBytes(text.getBytes());
        long msb = uuid.getMostSignificantBits();
        long lsb = uuid.getLeastSignificantBits();

        int combined = (int) ((msb >>> 48) | (lsb >>> 48));
        return combined;
    }

    // Hashes an integer into another (maybe needs some changes)
    private static Integer hash(Integer x) {
        x ^= (x << 13);
        x ^= (x >>> 17);
        x ^= (x << 5);
        return (x & 0x7fffffff);
    }

    

}