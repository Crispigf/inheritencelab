import java.util.Objects;
import java.util.Calendar;
public class Person
{
    private String firstName;
    private String lastName;
    private String ID;
    private String title;
    private int YOB;

    public Person(String ID, String firstName, String lastName, String title, int YOB) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.ID = ID;
        this.title = title;
        this.YOB = YOB;
    }

    public String fullName()
    {
        return firstName + " " + lastName;
    }

    public String formalName()
    {
        return title + " " + fullName();
    }

    public String getAge()
    {
        Calendar cal = Calendar.getInstance();
        int age = cal.get(Calendar.YEAR) - getYOB();
        return Integer.toString(age);
    }

    public String getAge(int year)
    {
        int age = year - getYOB();
        return Integer.toString(age);
    }

    public String toCSVDataRecord()
    {
        return ID + ", " + firstName + ", " + lastName + ", " + title + ", " + Integer.toString(YOB);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYOB() {
        return YOB;
    }

    public void setYOB(int YOB) {
        this.YOB = YOB;
    }


}
