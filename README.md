# Virtual File System Simulator
Simulation of Allocation and de-allocation of files and folders using different allocation techniques. 

## Techniques
* **<a href="https://www.javatpoint.com/os-contiguous-allocation">Contigous Allocation </a>**
* **<a href="https://www.javatpoint.com/os-indexed-allocation#:~:text=Instead%20of%20maintaining%20a%20file,allocated%20to%20that%20particular%20file.">Indexed Allocation </a>**
* **<a href="https://www.javatpoint.com/os-linked-list-allocation#:~:text=Linked%20List%20allocation%20solves%20all,be%20contiguous%20on%20the%20disk">Linked Allocation</a>**

### The user will interact with your virtual file system through a series of commands :
1. CreateFile root/file.txt 100 <br />
  * *This command used to create file named “file.txt” with 100 KB size under the path “root”* 
    * Pre-requests : <br />
      1- The path is already exist<br />
      2- No file with the same name is already created under this path<br />
      3- Enough space exists<br />
      
      
2. CreateFolder root/folder1 <br />
  * *This command is used to create a new folder named “folder1” under the path “root”*
    * Pre-requests : <br />
      1- The path is already exist<br />
      2- No folder with the same name is already created under this path. <br />
      
      
3. DeleteFile root/folder1/file.txt <br />
  * *This command used to delete file named “file.txt” form the path "root/folder1". Any blocks allocated by this file should be de-allocated.*
    * Pre-requests : <br />
      1- The file is already exist under the path specified <br />
    
    
4. DeleteFile root/folder1/file.txt <br />
  * *This command used to display the status of your Driver the status should contain the following information :* <br/>
      1- Empty space <br />
      2- Allocated Space 
      3- Empty Blocks in the Disk 
      4- Allocated Blocks in the Disk 
      
5. DisplayDiskStructure <br />
  * *This command will display the files and folders in your system file in a tree structure.* <br/>

