import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

public class VirtualFileSystem {
    Directory root;
    Techniques alloc;
    public VirtualFileSystem(String fileName, Techniques alloc){
        this.alloc = alloc;
        try {
            File vfs = new File(fileName);
            Scanner myReader = new Scanner(vfs);
            Stack<Directory> directories = new Stack<>();
            Stack<String> directoryPath = new Stack<>();

            String currPath = "root";
            int level = 0;
            String data = myReader.nextLine();
            root = new Directory("root", "root");
            Directory currDir = root;

            while (myReader.hasNextLine()) {
                data = myReader.nextLine();
                if(data.length()==0)
                    break;
                int spaces =0;
                for(int i =0; i<data.length();i++){
                    if(data.charAt(i)==' ')
                        spaces++;
                    else{
                        break;
                    }
                }
                if(spaces <= level*4){
                    level--;
                    currDir = directories.peek();
                    directories.pop();
                    currPath = directoryPath.peek();
                    directoryPath.pop();
                }
                if(data.charAt(spaces)=='.'){
                    String name = data.substring(spaces+1);
                    String path = currPath +"/" + name;
                    MyFile nFile = new MyFile(name, path);
                    currDir.addFile(nFile);
                }
                else if(data.charAt(spaces)=='-'){
                    String name = data.substring(spaces+1);
                    String path = currPath +"/" + name;
                    level++;
                    Directory nDir = new Directory(path, name);
                    currDir.addDirectory(nDir);
                    directories.push(currDir);
                    directoryPath.push(currPath);
                    currDir = nDir;
                    currPath = path;
                }
            }
            root.printDirectoryStructure(0);
            alloc.readData(root, myReader);
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeData(String file1){
        try {
            FileWriter myWriter = new FileWriter(file1);
            root.writeToFile(0, myWriter);
            alloc.display(myWriter, root);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void createFile(String path, String name, int numBlocks){
        Directory dir = root.findDirectory(path);
        if(dir == null){
            System.out.println("Directory path not found");
            return;
        }
        MyFile file = dir.findFileInThisDirectory(path+"/"+name);
        if(file != null){
            System.out.println("File with this name already exists.");
            return;
        }
        file = new MyFile(name, path+"/"+name);
        alloc.allocate(file, numBlocks);
        dir.addFile(file);
    }

    public void createFolder(String path, String name){
        Directory dir = root.findDirectory(path);
        if(dir == null){
            System.out.println("Directory path not found");
            return;
        }
        Directory file = dir.findDirectoryInThisDirectory(path+"/"+name);
        if(file != null){
            System.out.println("Directory with this name already exists.");
            return;
        }
        file = new Directory(path+"/"+name, name);
        dir.addDirectory(file);
    }

    public void deleteFile(String path) throws IOException {
        MyFile file = root.findFile(path);
        if(file == null){
            System.out.println("File do not exist exists.");
            return;
        }
        file.setDeleted(true);
        alloc.deAllocate(file);
    }

    public void deleteDirectory(String path) throws IOException {
        Directory dir = root.findDirectory(path);
        if(dir == null){
            System.out.println("Directory do not exist exists.");
            return;
        }
        dir.delete(alloc);
    }

    public void displayStructure(){
        root.printDirectoryStructure(0);
    }

    void printDiskStatus(){
        alloc.printStatus();
    }
}
