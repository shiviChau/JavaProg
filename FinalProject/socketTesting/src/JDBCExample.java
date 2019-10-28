//STEP 1. Import required packages
import java.sql.*;

public class JDBCExample {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/sys";

    //  Database credentials
    static final String USER = "admin";
    static final String PASS = "password";

    public String takeAttendence(Person person)
    {
        String returnString = "";

        if(person.getID() != 0) //if a new member is being added after training, all values will be filled
            returnString = newMember(person);
        else
            if(person.isPresent())
                returnString = checkOut(person);
            else
                returnString = checkIn(person);

        return returnString;
    }

    private static String checkIn(Person person)
    {
        Connection conn = null;
        Statement stmt = null;
        try
        {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
            stmt = conn.createStatement();

            String sql = "INSERT INTO attendence VALUES (" + person.getID() + ",'" + person.getFirstName() + "','" + person.getLastName() + "','testTime1', 'testtime2')";
            stmt.executeUpdate(sql);

            //change isPresent in members to true
            sql = "UPDATE members SET isPresent=1 WHERE id=" + person.getID();
            stmt.executeUpdate(sql);
        }catch(SQLException se)
        {
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e)
        {
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally
        {
            //finally block used to close resources
            try
            {
                if(stmt!=null)
                    conn.close();
            }catch(SQLException se) {}// do nothing
            try
            {
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        return person.getName() + "checked in at" + "<time>";
    }
    private String checkOut(Person person)
    {
        //put check out time in attendence table
        //change present boolean in members to false
        return person.getName() + "checked out at " + "<time>";
    }
    private String newMember(Person person)
    {
        //retrain?
        //put all info into members table
        checkIn(person);
        return person.getName() + "added to members list and checked in at " + "<time>";
    }

    public static void main(String[] args)
    {
        Person person = new Person(3333334, "Arjun Ghoshal", "eml", "phn");
        checkIn(person);
    }
}