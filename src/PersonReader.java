import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.SQLOutput;
import java.util.Scanner;
import javax.swing.JFileChooser;
import java.util.ArrayList;

public class PersonReader {
    public static void main(String[] args) {

        ArrayList<Person> folks = new ArrayList<>();

        JFileChooser chooser = new JFileChooser();
        Scanner inFile;
        String line;
        Path target = new File(System.getProperty("user.dir")).toPath();
        target = target.resolve("src");
        // set the chooser to the project src directory
        chooser.setCurrentDirectory(target.toFile());

        try  // Code that might trigger the exception goes here
        {

            if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
            {
                target = chooser.getSelectedFile().toPath();  // this is a File object not a String filename

                inFile = new Scanner(target);
                System.out.printf("%-15s%-15s%-15s%-15s%-15s\n", "ID", "Firstname","Lastname","Title","YOB");
                String dashLine = "-".repeat(15 * 5);
                System.out.printf("%s\n", dashLine);
                while(inFile.hasNextLine())
                {
                    line = inFile.nextLine();
                    String[] row = line.split(", ");
                    int intValue = Integer.parseInt(row[4]);
                    Person people = new Person(row[0], row[1], row[2], row[3], intValue);
                    folks.add(people);
                    System.out.printf("%-15s%-15s%-15s%-15s%-15s\n", row[0], row[1], row[2], row[3], row[4]);

                }

                inFile.close();
            }
            else   // User did not pick a file, closed the chooser
            {
                System.out.println("Sorry, you must select a file! Termininating!");
                System.exit(0);
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File Not Found Error");
            e.printStackTrace();
        }
        catch (IOException e) // code to handle this exception
        {
            System.out.println("IOException Error");
            e.printStackTrace();
        }
    }
}
