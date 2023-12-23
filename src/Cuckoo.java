import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;
import java.util.Formatter;

public class Cuckoo {
    
    public Cuckoo(){
        
    }

    public void insert(String x){
        Integer f = generateFingerprint(x, 8);
        Integer i1 = x.hashCode();
        Integer i2 = i1 ^ hash(f);


    }

    public boolean lookup(String x){
        return false;
    }

    public void delete(String x){

    }

    private static Integer generateFingerprint(String text, Integer len) {
        UUID uuid = UUID.nameUUIDFromBytes(text.getBytes());
        String fingerprint = uuid.toString().substring(0, len);
        return Integer.parseInt(fingerprint, 16);
    }

    private static int hash(Integer x) {
        x ^= (x << 13);
        x ^= (x >>> 17);
        x ^= (x << 5);
        return x;
    }

    

}