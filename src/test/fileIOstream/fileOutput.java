package test.fileIOstream;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class fileOutput {
    public static void main(String[] args) {
        String content;
        Scanner scanner = new Scanner(System.in);
        content = new String(scanner.nextLine());

        try {
            File myObj = new File("filename.txt");
            FileOutputStream fout = new FileOutputStream(myObj);

            // String content = "Welcome to java.";
            byte b[] = content.getBytes();// converting string into byte array
            fout.write(b);

            fout.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}