import java.util.ArrayList;
public class ScalableCuckoo{

    private int buckets;
    private int bucketEntries;
    private int maxKicks;
    private double scaleThreshold;
    private double errorRate;
    private int scaleFactor;
    private ArrayList<Cuckoo> cuckooList;
    
    public ScalableCuckoo(int buckets, int bucketEntries, int maxKicks, double errorRate, int scaleFactor){
        this.scaleThreshold = 0.9;
        this.cuckooList  = new ArrayList<>();
        this.buckets = buckets;
        this.maxKicks = maxKicks;
        this.bucketEntries = bucketEntries;
        this.errorRate = errorRate;
        this.scaleFactor = scaleFactor;
        Cuckoo cuckoo = new Cuckoo(buckets, bucketEntries, maxKicks, errorRate);
        cuckooList.add(cuckoo);
    }   

    public boolean insert(String x){
        Cuckoo cuckoo = cuckooList.get(cuckooList.size() - 1);
        if (cuckoo.getDensity() <= scaleThreshold && cuckoo.insert(x)){
            return true;
        }
        // Cuckoo needs scaling
        buckets *= scaleFactor;
        cuckooList.add(new Cuckoo(buckets, bucketEntries, maxKicks, errorRate));
        (cuckooList.get(cuckooList.size() - 1)).insert(x);
        return true;
    }

    public boolean lookup(String x){
        for(int i = 0; i < cuckooList.size(); i++){
            if ((cuckooList.get(i)).lookup(x)) 
                    return true;
        }
        return false;
    }

    public boolean delete(String x){
        for(int i = 0; i < cuckooList.size(); i++){
            if ((cuckooList.get(i)).delete(x)) 
                    return true;
        }
        return false;
    }

}