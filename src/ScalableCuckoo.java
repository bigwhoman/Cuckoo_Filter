import java.util.ArrayList;
public class ScalableCuckoo{

    Integer scaleFactor;
    ArrayList<Cuckoo> cuckooList;
    
    public ScalableCuckoo(Integer buckets, Integer bucketEntries, Integer maxKicks, double errorRate, Integer scaleFactor){
        this.cuckooList  = new ArrayList<>();
        this.scaleFactor = scaleFactor;
    }   

    public boolean insert(String x){
        
        return false;
    }

    public boolean lookup(String x){
        

        return false;
    }

    public boolean delete(String x){
        
        return false;
    }

}