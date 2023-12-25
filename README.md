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
If it is not found, then we can definitely say that it does not exist in the table but if it is found, there is a possibility that the result could be false positive meaning that the item does not exist but the returned search query
is true.

<br>

The pseudo code shown in the paper is as below : 

<img src="https://github.com/bigwhoman/Cuckoo_Filter/assets/79264715/d60c4ffc-4d78-4ca6-bb75-2a89288fdbe6" width="500">


### Delete
Upon deletion of item x, its fingerprint is searched in the hash table and if found, it is removed.

<img src="https://github.com/bigwhoman/Cuckoo_Filter/assets/79264715/7bf0b0f4-c805-4641-b845-c6e42a63f02b" width="500">

### Cuckoo vs Bloom
### Cuckoo Parameters

## Implementing Java Version

## Testing Cuckoo 

## Open Question : 

## Managing Multi-Sets
