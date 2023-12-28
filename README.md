# Cuckoo_Filter
Cuckoo filter is a data structure which is used to find if an element is a part of a set or not. <br>

> [!NOTE]
> The base paper of this data structure is in [This Link](https://ieeexplore.ieee.org/document/9212434)
## Basic Questions
### What is a cuckoo filter
Cuckoo filter is a variant of a [Cuckoo Hash](https://en.wikipedia.org/wiki/Cuckoo_hashing) that stores fingerprints with high space efficiency. <br>
The cuckoo filter has 3 operations : 
<li> Insert </li>
<li> Lookup </li>
<li> Delete </li>
This filter consists of a hash table where each tables entry is called <b>Bucket</b> and each bucket consists of some entries. The image below (copied from source paper) illustrates this.
<br><br>

<img src="https://github.com/bigwhoman/Cuckoo_Filter/assets/79264715/e89a29b9-a911-4eb4-845c-093dbd15ae8a" width="300">
<br>

### Insert
This is the first operation of this data structure. <br>
Cuckoo filters stores fingerprints, a bit string derived from the item using a hash, which result in high space efficiency. Two hash, h<sub>1</sub> and h<sub>2</sub> are derived from this fingerprint.
The fingerprint is stored in <b> bucket\[h<sub>1</sub>(fingerprint)\] </b> or <b> bucket\[h<sub>2</sub>(fingerprint)\] </b> if it has an empty space in its entry. If neither of the buckets don't have any
empty entries, one of them is randomly chosen and an entry from it is randomly relocated to its other hash location. This reloctaion process is done until there is no item which needs relocating or we have reached our maximum
relocation.

<br>

The pseudo code shown in the paper is as below : 

<img src="https://github.com/bigwhoman/Cuckoo_Filter/assets/79264715/c8827636-79e5-4978-9c73-5402510b520e" width="500">


### Lookup
Upon lookup for item x, the hash table is searched for the fingerprint of x and returns true if the fingerprint is found in the table. Bucket\[h<sub>1</sub>(fingerprint)\] and bucket\[h<sub>2</sub>(fingerprint)\] are searched.
If it is not found, then we can definitely say that it does not exist in the table but if it is found, there is a possibility, ε, that the result could be false positive meaning that the item does not exist but the returned search query is true.

<br>

The pseudo code shown in the paper is as below : 

<img src="https://github.com/bigwhoman/Cuckoo_Filter/assets/79264715/d60c4ffc-4d78-4ca6-bb75-2a89288fdbe6" width="500">


### Delete
Upon deletion of item x, its fingerprint is searched in the hash table and if found, it is removed.

<img src="https://github.com/bigwhoman/Cuckoo_Filter/assets/79264715/7bf0b0f4-c805-4641-b845-c6e42a63f02b" width="500">

### Cuckoo vs Bloom
The most important difference between bloom and cuckoo filter is that bloom filter <b>does not support dynamic deletions</b> meaning that each delete from the bloom filter would need reconstruction of the 
whole filter. Cuckoo uses less space than bloom filters in many cases. Cuckoo provides higher lookup performance than bloom filters.

### Cuckoo Parameters
According to [Redis](https://redis.io/docs/data-types/probabilistic/cuckoo-filter/) these are the main parameters of the cuckoo filter :

<li> ε : Target false ppositive rate </li>
<li> f : Fingerprint length </li>
<li> α : Fill rate </li>
<li> b : Entries per bucket </li>
<li> m : Number of buckets </li>
<li> n : Number of items </li>
<li> C : Average bits per item </li>
<br>

$$ 
Capacity = \frac{n \times f}{α}
$$

<br>

$$
ε = \frac{buckets \times 2}{2^{(f)}}
$$

## Implementing Java Version

## Testing Cuckoo 
A testing java program is implemented to test the cuckoo data structure. You could find the testing program in 
the benchmark folder. <br>
The parameters of the program are as follows : 

<li> -i : number of inserts -- default = 10000 </li>
<li> -l : number of lookups -- default = 100000 </li>
<li> -d : number of deletes -- default = 10 </li>
<li> -a : (capacity) / (number of inserts) -- default = 2 </li>
<li> -e : error rate -- default = 0.05 </li>
<li> -k : maximum kicks when inserting -- default = 5 </li>
<li> -n : number of each bucket entry -- default = 4 </li>

Keep in mind that the number of buckets is calculated as follows : 

$$ \lceil \frac{a * inserts}{bucket entries} \rceil $$ 

<br>

### Tested Parameter 1 : Inserts
<img src="/benchmark/Cuckoo/inserts.png">

### Tested Parameter 2 : Lookups 
<img src="/benchmark/Cuckoo/lookups.png">

### Tested Parameter 3 : Bucket Entries 
<img src="/benchmark/Cuckoo/bucket_entries.png">

### Tested Parameter 4 : Bucket Entries -- 50000000 Lookups
<img src="/benchmark/Cuckoo/bucket_entries-50000000_lookups.png">

### Tested Parameter 5 : Max Kicks
<img src="/benchmark/Cuckoo/max_kicks.png">

### Tested Parameter 6 : Max Kicks -- 50000000 Lookups
<img src="/benchmark/Cuckoo/max_kicks-50000000_lookups.png">

## Scalable Cuckoo Filter

## Managing Multi-Sets
