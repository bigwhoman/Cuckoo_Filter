import random
import string

INSERTS = 10000
LOOKUPS = 1000000
DELETES = 1000

keys : list = []


def get_random_string(length : int) -> str:
    letters = string.ascii_lowercase
    result_str = ''.join(random.choice(letters) for i in range(length))
    return result_str


fingerprintLen : int = 8
maxKicks       : int = 3
bucketEntries  : int = 4
buckets        : int = 8


for name in range(2 * INSERTS + 1) :
    keys.append(get_random_string(10))

insert_command : str = 'cuckoo.insert("{}");'
lookup_command : str = 'cuckoo.lookup("{}");'
delete_command : str = 'cuckoo.delete("{}");'

insert : str = ''

for INSERT in range(INSERTS) :
    inserted_name = keys[random.randint(0, 2 * INSERTS)]
    insert_comm = insert_command.format(inserted_name)
    insert_comm += "\n"
    insert += insert_comm

lookup : str = ''

for LOOKUP in range(LOOKUPS) :
    lookup_name = keys[random.randint(0, 2 * INSERTS)]
    lookup_comm = lookup_command.format(lookup_name)
    lookup_comm += "\n"
    lookup += lookup_comm

delete : str = ''

for DELETE in range(DELETES) :
    delete_name = keys[random.randint(0, 2 * INSERTS)]
    delete_comm = delete_command.format(delete_name)
    delete_comm += "\n"
    delete += delete_comm

test_body : str = f'''
// -------------------------  INSERT ----------------------------

{insert}

// -------------------------  LOOKUP ----------------------------

{lookup}

// -------------------------  DELETE ----------------------------

{delete}
'''


base_main : str = f'''
import java.util.Scanner;
import java.io.File;
public class Main {{
    public static void main(String[] args) {{
        Cuckoo cuckoo = new Cuckoo({buckets}, {bucketEntries}, {maxKicks}, {fingerprintLen});

        // TEST BODY BEGIN    ---------------------
        {test_body}
        // TEST BODY END      ---------------------

        return ;
    }}
}} 
'''

x = open("Main.java",'w+')
x.write(base_main)