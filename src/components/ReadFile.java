package components;

import java.io.File; // Import the File class
import java.util.Scanner; // Import the Scanner class to read text files

public class ReadFile {
    File myObj;
    String data;

    public ReadFile() {
        myObj = null;
        data = "";
    }

    public ReadFile(String filePath) {
        myObj = new File(filePath);
        data = getData();
    }

    public ReadFile(File file) {
        myObj = file;
        data = getData();
    }

    public String getData() {
        try {
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
            return data;
        } catch (Exception err) {
            System.out.println("An error occurred.");
            // err.printStackTrace();
            return "";
        }
    }

    public static void main(String[] args) {
        ReadFile readFile = new ReadFile(System.getProperty("user.dir") + "/input.txt");

        String data = readFile.getData();
    }
}