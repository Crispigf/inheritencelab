import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

public class PersonGenerator
{
    public static void main(String[] args)
    {
        ArrayList<Person> folks = new ArrayList<>(); //array of people in an array called "folks"
        Scanner in = new Scanner(System.in); //scanner named "in: for system

        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\PersonTestData.txt");


        boolean done = false; //do while flag
        /*
        a.	ID (a String)
        b.	FirstName
        c.	LastName
        d.	Title (a string like Mr., Mrs., Ms., Dr., etc.)
        e.	YearOfBirth (an int)
         */
        String personRec = "";
        String ID = "";
        String firstname ="";
        String lastname = "";
        String title= "";
        int YOB = 0;

        do{
            ID = SafeInput.getNonZeroLenString(in, "Enter the ID (6 digits)");
            firstname= SafeInput.getNonZeroLenString(in, "Enter the first name");
            lastname = SafeInput.getNonZeroLenString(in, "Enter the last name");
            title = SafeInput.getNonZeroLenString(in, "Enter the title");
            YOB = SafeInput.getRangedInt(in, "Enter the year of birth", 1940, 2000 );
            Person people = new Person(firstname, lastname, ID, title, YOB);

            folks.add(people);

            done = SafeInput.getYNConfirm(in, "Are you done?");

        }while(!done);

        for(Person p: folks)
            System.out.println(p.toCSVDataRecord());


        try
        {
            // Typical java pattern of inherited classes
            // we wrap a BufferedWriter around a lower level BufferedOutputStream
            OutputStream out =
                    new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(out));

            // Finally can write the file LOL!

            for(Person line: folks)
            {
                writer.write(line.toCSVDataRecord(), 0, line.toCSVDataRecord().length());  // stupid syntax for write rec
                // 0 is where to start (1st char) the write
                // rec. length() is how many chars to write (all)
                writer.newLine();  // adds the new line

            }
            writer.close(); // must close the file to seal it and flush buffer
            System.out.println("Data file written!");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }


    }
}