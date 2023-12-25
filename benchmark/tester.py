import subprocess
import csv
import matplotlib.pyplot as plt

bucketEntries = [1, 2, 4, 8, 16, 32, 64, 128, 256, 500]
lookups = [1, 2, 4, 8, 16, 32, 64, 128, 256, 500,1000,5000,20000,100000,1000000,10000000, 20000000 , 50000000, 80000000,100000000]
inserts = [10,100,1000,10000,100000,1000000,10000000]
max_kicks = [1, 2, 5, 10 ,20 ,40, 100]
results = []

def compile_and_run_java(filename, args) -> float:
  subprocess.run(['javac', filename])
  output = subprocess.check_output(['java', filename.split('.')[0]] + args)
  return float(output.decode('utf-8').strip())

for bucket_entry in max_kicks:
   elapsed_time = compile_and_run_java('Main.java', ["-k", str(bucket_entry)])
   results.append([bucket_entry, elapsed_time])


with open('max_kick_results.csv', 'w', newline='') as csvfile:
   fieldnames = ['max_kicks', 'elapsed_time']
   writer = csv.DictWriter(csvfile, fieldnames=fieldnames)
   writer.writeheader()
   for result in results:
       writer.writerow({'max_kicks': result[0], 'elapsed_time': result[1]})


data = []
with open('max_kick_results.csv', 'r') as csvfile:
   reader = csv.reader(csvfile)
   next(reader)
   for row in reader:
       data.append(list(map(float, row)))

plt.plot(*zip(*data))
plt.xlabel('max kicks')
plt.ylabel('elapsed time')
plt.title('Performance vs max kick')
plt.savefig('max_kick.png')
plt.show()