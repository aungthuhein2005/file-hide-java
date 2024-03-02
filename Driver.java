package FileHide;

import java.awt.Desktop;
import java.io.*;
import java.util.Scanner;

public class Driver {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        
        String hiddenFolderPath = System.getProperty("user.home") + File.separator + ".myHiddenFolder";

        File hiddenFolder = new File(hiddenFolderPath);
        if (!hiddenFolder.exists()) {
            if (hiddenFolder.mkdir()) {
                System.out.println("Hidden folder created successfully");
            }
        }
        
        System.out.println("-----Welcome File hidder-----");
        while(true) {
        	System.out.println("Choose option");
            System.out.println("1. Hide file\n2. Open file\n3. View hidden file\n4. Delete File");
            int option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
    		case 1:
    			System.out.println("Enter the file path to hide:");
    	        String filePath = scanner.nextLine();
    	        hideFile(hiddenFolder, filePath);
    			break;
    		case 2:
    			System.out.println("Enter the file name to open:");
    	        String openFileName = scanner.nextLine();
    	        File openFile = new File(hiddenFolder, openFileName);
    	        openFile(openFile);
    	        break;
    		case 3:
    			viewFile(hiddenFolder);
    			break;
    		case 4:
    			System.out.println("Enter the file name to delete:");
    	        String deleteFileName = scanner.nextLine();
    	        File deleteFile = new File(hiddenFolder, deleteFileName);
    	        deleteFile(deleteFile);
    	        break;
    		default:
    			break;
    		}
            
            System.out.println("continue ? yes(y) or no(n)");
            char terminate = scanner.nextLine().charAt(0);
            if(terminate == 'n') break;
        }
        scanner.close();
        System.out.println("Program terminate");
        
    }

    private static void hideFile(File hiddenFolder, String filePath) throws IOException {
        String fileName = new File(filePath).getName();
        String hiddenFilePath = hiddenFolder.getAbsolutePath() + File.separator + fileName;
        copyFile(new File(filePath), new File(hiddenFilePath));
        System.out.println("File hidden successfully");
    }

    private static void copyFile(File inputFile, File outputFile) throws IOException {
        BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(inputFile));
        BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(outputFile));

        byte buffer[] = new byte[4000];

        int bytesRead;
        while ((bytesRead = inputStream.read(buffer, 0, buffer.length)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }
        inputStream.close();
        outputStream.close();
    }
    
    private static void viewFile(File hiddenFolder) {
    	File[] files = hiddenFolder.listFiles(); 
    	  
        System.out.println("Files are:"); 

        // Display the names of the files 
        for (int i = 0; i < files.length; i++) { 
            System.out.println(files[i].getName()); 
        } 
    }
    
    private static void openFile(File file) throws IOException {
    	if(!Desktop.isDesktopSupported())//check if Desktop is supported by Platform or not  
    	{  
    	System.out.println("not supported");  
    	return;  
    	}  
    	Desktop desktop = Desktop.getDesktop();  
    	if(file.exists())         //checks file exists or not  
    	desktop.open(file);              //opens the specified file  
    	}  
    
   private static void deleteFile(File file) {
	   if(file.delete()) {
		   System.out.println("File deleted successfully");
	   }else {
		   System.out.println("File delete fail!");
	   }
   }
    
}
