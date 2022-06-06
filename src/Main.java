import java.io.IOException;
import java.util.Scanner;


public class Main {
    public static void main ( String[] args ) throws IOException {

        VirtualFileSystem vfs;
        String fileName;

        while ( true ){
            Scanner scanner = new Scanner ( System.in );
            int numChoice, blockSize = 1;
            System.out.println ("Please enter the number of allocation type : \n" );
            System.out.println ("1- Contiguous Allocation \n" +
                    "2- Linked Allocation\n" +
                    "3- Indexed Allocation\n" );

            numChoice = scanner.nextInt ();
            if(numChoice == 1){
                fileName = "contiguous.vfs";
                vfs = new VirtualFileSystem(fileName, new Contigous());
            }
            else if(numChoice == 2){
                fileName = "linked.vfs";
                vfs = new VirtualFileSystem(fileName, new Linked());
            }
            else {
                fileName = "indexed.vfs";
                vfs = new VirtualFileSystem(fileName, new Indexed());
            }
            boolean flag = true;
            boolean firstTime = true;
            while(flag){
                System.out.println ("Enter your Command: " );
                String command;
                if(firstTime){
                    scanner.nextLine();
                    firstTime = false;
                }
                command = scanner.nextLine();
                String arr[] = command.split ( " " );
                String path = "", name="";
                Directory currDirectory = null;
                if (arr.length > 1){
                    for (int i = arr[1].length ()-1 ; i>=0;i--){
                        if (arr[1].charAt ( i ) == '/'){
                            path = arr[1].substring ( 0,i );
                            name = arr[1].substring ( i+1 );
                            break;

                        }
                    }
                }
                else if(arr.length==1){
                    switch (arr[0]){
                        case"DisplayDiskStatus":
                            vfs.printDiskStatus();
                            break;
                        case"DisplayDiskStructure":
                            vfs.displayStructure();
                            break;
                        case"Exit":
                            vfs.writeData(fileName);
                            flag = false;
                            break;
                    }
                    continue;
                }
                else{
                    continue;
                }

                switch (arr[0]){
                    case "CreateFile":
                        int size = (int)(Double.parseDouble ( arr[2] )+0.5);
                        vfs.createFile(path, name, size);
                        break;

                    case "CreateFolder":
                        vfs.createFolder(path, name);
                        break;

                    case "DeleteFile":
                        vfs.deleteFile(path + "/" + name);
                        break;

                    case"DeleteFolder":
                        vfs.deleteDirectory(path + "/" + name);
                        break;

                }

            }

            System.out.print ("Do you want to choose another Allocation Method? (Y|N): " );
            String choice;
            choice = scanner.next ( );
            if (choice.equalsIgnoreCase ( "N" )){
                break;

            }


        }



    }
}
