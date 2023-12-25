import random
import string
import numpy as np
import subprocess

INSERTS = 5
LOOKUPS = 5
DELETES = 0

keys : list = []


def get_random_string(length : int) -> str:
    letters = string.ascii_lowercase
    result_str = ''.join(random.choice(letters) for i in range(length))
    return result_str


# INSERTS * alpha_rate = bucketEntries * buckets   
alpha_rate        : int = 1 


fingerprintLen : int = 8
maxKicks       : int = 5
bucketEntries  : int = 4
buckets        : int = int(np.ceil(INSERTS * alpha_rate/bucketEntries))


for name in range(2 * INSERTS + 1) :
    keys.append(get_random_string(10))

insert_command : str = '\t\tcuckoo.insert("{}");'
lookup_command : str = '\t\tcuckoo.lookup("{}");'
delete_command : str = '\t\tcuckoo.delete("{}");'

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


# def compile_and_run_java(filename):
#    subprocess.run(['javac', filename])
#    subprocess.run(['java', filename.split('.')[0]])

# compile_and_run_java('Main.java')