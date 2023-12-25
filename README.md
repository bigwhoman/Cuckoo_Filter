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
Cuckoo filters stores fingerprints, a bit string derived from the item using a hash, which result in high space efficiency. 
### Lookup
Upon lookup for item x, the hash table is searched for the fingerprint of x and returns true if the fingerprint is found in the table.
### Delete
Upon deletion of item x, its fingerprint is searched in the hash table and if found, it is removed.

### Cuckoo vs Bloom
### Cuckoo Parameters

## Implementing Java Version

## Testing Cuckoo 

## Open Question : 

## Managing Multi-Sets
