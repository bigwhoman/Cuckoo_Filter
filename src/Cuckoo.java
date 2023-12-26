import java.util.UUID;
import java.util.Random;
import java.util.BitSet;
import java.util.HashMap;

public class Cuckoo {
    private HashMap<BitSet, BitSet[]> bucketArray ;
    private Integer buckets;
    private Integer bucketEntries;
    private Integer maxKicks;
    private Integer fingerprintLen;

    // Gets the number of buckets, max entries a bucket could have and maximum number of replacements in
    // case of a number gets thrown out
    public Cuckoo(Integer buckets, Integer bucketEntries, Integer maxKicks, double errorRate){
        this.buckets        = buckets;
        this.bucketEntries  = bucketEntries;
        this.maxKicks       = maxKicks;
        this.bucketArray    = new HashMap<>();
        this.fingerprintLen = Double.valueOf(
                                Math.ceil(
                                           1.44 * (Math.log(1/errorRate) / Math.log(2)))
                                         )
                                    .intValue();
    }

    // Inserts string x into the cuckoo data structure
    public boolean insert(String x){
        BitSet f   = generateFingerprint(x);

        // The hashes should be modded to the number of buckets
        BitSet i1  = BitSet.valueOf(new long[]{x.hashCode()});
        BitSet i2  = (BitSet) i1.clone();
        i2.xor(BitSet.valueOf(new long[]{f.hashCode()}));

        if(bucketArray.get(i1) == null){
            bucketArray.put(i1, new BitSet[bucketEntries]);
        }
        if(bucketArray.get(i2) == null){
            bucketArray.put(i2, new BitSet[bucketEntries]);
        }
        // ------------------------------------------

        for(Integer entry = 0; entry < bucketEntries ; entry++){
            if((bucketArray.get(i2))[entry] == null){
                bucketArray.get(i2)[entry] = f;
                return true;
            }
        }
        Random rand = new Random();
        BitSet i = rand.nextInt(2) == 0 ? (BitSet) i1.clone() : (BitSet) i2.clone();

        for(Integer kick = 0; kick < maxKicks; kick++){
            Integer randomBucketEntry = rand.nextInt(bucketEntries);
            BitSet bucketEntryFingerprint = (bucketArray.get(i))[randomBucketEntry];
            (bucketArray.get(i))[randomBucketEntry] = f;
            i.xor(BitSet.valueOf(new long[]{f.hashCode()}));
            for(Integer entry = 0; entry < bucketEntries ; entry++){
                if((bucketArray.get(i))[entry] == null){
                    bucketArray.get(i2)[entry] = bucketEntryFingerprint;
                    return true;
                }
            }
        }
        // Hash table is full
        return false;
    }

    // // looks up if string x is in the hash table (returns true if **possibly in table**)
    public boolean lookup(String x){
        BitSet f   = generateFingerprint(x);
        BitSet i1  = BitSet.valueOf(new long[]{x.hashCode()});
        BitSet i2  = (BitSet) i1.clone();
        i2.xor(BitSet.valueOf(new long[]{f.hashCode()}));
        
        for(Integer entry = 0; entry < bucketEntries ; entry++){
            if(bucketArray.get(i1) != null && 
                bucketArray.get(i1)[entry] != null &&
                bucketArray.get(i1)[entry].equals(f)){
                    return true;
            }
        }

        for(Integer entry = 0; entry < bucketEntries ; entry++){
            if(bucketArray.get(i2) != null && 
                bucketArray.get(i2)[entry] != null && 
                bucketArray.get(i2)[entry].equals(f)){
                    return true;
            }
        }
        // Item does not exist
        return false;
    }

    // deletes a key from table
    public boolean delete(String x){
        BitSet f   = generateFingerprint(x);
        BitSet i1  = BitSet.valueOf(new long[]{x.hashCode()});
        BitSet i2  = (BitSet) i1.clone();
        i2.xor(BitSet.valueOf(new long[]{f.hashCode()}));

        for(Integer entry = 0; entry < bucketEntries ; entry++){
            if(bucketArray.get(i1) != null && 
                bucketArray.get(i1)[entry] != null &&
                bucketArray.get(i1)[entry].equals(f)){
                    bucketArray.get(i1)[entry] = null;
                    return true;
            }
        }

        for(Integer entry = 0; entry < bucketEntries ; entry++){
            if(bucketArray.get(i2) != null && 
                bucketArray.get(i2)[entry] != null && 
                bucketArray.get(i2)[entry].equals(f)){
                    bucketArray.get(i2)[entry] = null;
                    return true;
            }
        }        

        return false;
    }
    
    // // Generates a unique fingerprint (with len) for a text
    private BitSet generateFingerprint(String text) {
        BitSet bs = new BitSet(fingerprintLen);
        return (bs.valueOf(text.getBytes())).get(0,fingerprintLen);
    }


}