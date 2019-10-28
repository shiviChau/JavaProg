package javafaces;

//STEP 1. Import required packages
import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

/**
 * This class recives a person object and checks them in, checks them out, or adds them as a new member
 *
 * @author Rahul Kulkarni
 * @author tutorialspoint.com (original database code adapted from this website)
 * @version 2.4
 */
public class JDBCExample
{
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/sys";

    //  Database credentials
    static final String USER = "admin";
    static final String PASS = "password";

    /**
     * takes a person object and decides the appropriate action to take
     *
     * @param person the person to be checked in/checked out/added as a new member
     * @return returnString a string that accurately describes the taken action
     */
    public static String takeAttendence(Person person)
    {
        String returnString = "";

        if(person.getID() != 0) //if a new member is being added after training, all values will be filled
            returnString = newMember(person); //add the person as a new member
        else
            if(person.isPresent()) //if the person is currently in a meeting, check them out
                returnString = checkOut(person); //check the person out
            else //if the person is not currently in a meeting
                returnString = checkIn(person); //check the person in

        return returnString;
    }

    /**
     * checks a person in
     *
     * @param person the person to be checked in
     * @return a string that describes the action taken
     */
    private static String checkIn(Person person)
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        Connection conn = null;
        Statement stmt = null;
        try //catch any errors
        {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
            stmt = conn.createStatement();

            //get person's info from members table and put into person object
            String sql = "SELECT * FROM members WHERE firstName='" + person.getFirstName() + "'";

            // execute the query, and get a java resultset
            ResultSet rs = stmt.executeQuery(sql);

            // iterate through the java resultset
            while (rs.next())
            {
                person.setIDnum(rs.getInt("id"));
                person.setEmail(rs.getString("email"));
                person.setPhoneNum(rs.getString("phone"));
            }

            sql = "INSERT INTO attendance VALUES (" + person.getID() + ",'" + person.getFirstName() + "','" + person.getLastName() + "','" + dtf.format(now) + "','" + " " + "')";
            //insert all values into attendance table
            stmt.executeUpdate(sql);

            //change isPresent in members to true
            sql = "UPDATE members SET isPresent=1 WHERE id=" + person.getID();
            stmt.executeUpdate(sql);
        }
        catch(SQLException se)
        {
            //Handle errors for JDBC
            se.printStackTrace();
        }
        catch(Exception e)
        {
            //Handle errors for Class.forName
            e.printStackTrace();
        }
        finally
        {
            //finally block used to close resources
            try
            {
                if(stmt!=null)
                    conn.close();
            }
            catch(SQLException se) {}// do nothing
            try
            {
                if(conn!=null)
                    conn.close();
            }
            catch(SQLException se)
            {
                se.printStackTrace();
            }
        }
        return person.getName() + "checked in at" + "<time>";
    }

    /**
     * checks a person out
     *
     * @param person the person to be checked out
     * @return a string that describes the action taken
     */
    private static String checkOut(Person person)
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        Connection conn = null;
        Statement stmt = null;

        //put check out time in attendance table
        try
        {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
            stmt = conn.createStatement();

            //get person's info from members table and put into person object
            String sql = "SELECT * FROM members WHERE firstName='" + person.getFirstName() + "'";

            // execute the query, and get a java resultset
            ResultSet rs = stmt.executeQuery(sql);

            // iterate through the java resultset
            while (rs.next())
            {
                person.setIDnum(rs.getInt("id"));
                person.setEmail(rs.getString("email"));
                person.setPhoneNum(rs.getString("phone"));
            }

            //insert timeout into attendance table
            sql = "UPDATE attendence SET timeOut='" + dtf.format(now) + "'WHERE id=" + person.getID();
            stmt.executeUpdate(sql);

            //change isPresent in members to false
            sql = "UPDATE members SET isPresent=0 WHERE id=" + person.getID();
            stmt.executeUpdate(sql);
        }
        catch(SQLException se)
        {
            //Handle errors for JDBC
            se.printStackTrace();
        }
        catch(Exception e)
        {
            //Handle errors for Class.forName
            e.printStackTrace();
        }
        finally
        {
            //finally block used to close resources
            try
            {
                if(stmt!=null)
                    conn.close();
            }
            catch(SQLException se) {}// do nothing
            try
            {
                if(conn!=null)
                    conn.close();
            }
            catch(SQLException se)
            {
                se.printStackTrace();
            }
        }

        return person.getName() + "checked out at " + "<time>";
    }

    /**
     * adds a new member to the members table
     *
     * @param person the person to be added to the members table
     * @return a string that describes the action taken
     */
    private static String newMember(Person person)
    {//retrain?
        Connection conn = null;
        Statement stmt = null;

        //put all info into members table
        try
        {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
            stmt = conn.createStatement();

            //insert values
            String sql = "INSERT INTO members VALUES (" + person.getID() + ",'" + person.getFirstName() +  "','" + person.getLastName() + "','" + person.getEmail() + "','" + person.getPhoneNum() + "'," + 0 + ")";
            stmt.executeUpdate(sql);
        }
        catch(SQLException se)
        {
            //Handle errors for JDBC
            se.printStackTrace();
        }
        catch(Exception e)
        {
            //Handle errors for Class.forName
            e.printStackTrace();
        }
        finally
        {
            //finally block used to close resources
            try
            {
                if(stmt!=null)
                    conn.close();
            }
            catch(SQLException se) {}// do nothing
            try
            {
                if(conn!=null)
                    conn.close();
            }
            catch(SQLException se)
            {
                se.printStackTrace();
            }
        }

        //if member is being checked in, they should be checked in
        checkIn(person);

        return person.getName() + "added to members list and checked in at " + "<time>";
    }

    public static void main(String[] args)
    {

    }
}
