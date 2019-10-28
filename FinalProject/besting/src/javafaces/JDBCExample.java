package javafaces;

//STEP 1. Import required packages
import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class JDBCExample
{
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/sys";

    //  Database credentials
    static final String USER = "admin";
    static final String PASS = "password";

    public static String takeAttendence(Person person)
    {
        System.out.println("\nline 17\n");
        String returnString = "";

        if(person.getID() != 0) //if a new member is being added after training, all values will be filled
        {
            returnString = newMember(person);
            System.out.println("\nreached here\n");
        }
        else
            if(person.isPresent())
            {
                returnString = checkOut(person);
                System.out.println("\nreached HERE\n");
            }
            else
            {
                System.out.println("\nreached THIS\n");
                System.out.println("\nline 27\n");
                returnString = checkIn(person);
            }

        return returnString;
    }

    private static String checkIn(Person person)
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
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

            sql = "INSERT INTO attendence VALUES (" + person.getID() + ",'" + person.getFirstName() + "','" + person.getLastName() + "','" + dtf.format(now) + "','" + " " + "')";
            System.out.println("\nline 46\n");
            stmt.executeUpdate(sql);

            //change isPresent in members to true
            sql = "UPDATE members SET isPresent=1 WHERE id=" + person.getID();
            stmt.executeUpdate(sql);
            System.out.println("\nline 52\n");
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
            }catch(SQLException se) {}// do nothing
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
    private static String checkOut(Person person)
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        Connection conn = null;
        Statement stmt = null;

        //put check out time in attendence table
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
            System.out.println("\nID: " + person.getID() + "n");

            //insert timeout into attendence table
            sql = "UPDATE attendence SET timeOut='" + dtf.format(now) + "'WHERE id=" + person.getID();
            stmt.executeUpdate(sql);
            System.out.println("\nline 52\n");

            //change isPresent in members to true
            sql = "UPDATE members SET isPresent=0 WHERE id=" + person.getID();
            stmt.executeUpdate(sql);
            System.out.println("\nline 52\n");
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
            }catch(SQLException se) {}// do nothing
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

        //change present boolean in members to false
        return person.getName() + "checked out at " + "<time>";
    }
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

            //CHANGE
            String sql = "INSERT INTO members VALUES (" + person.getID() + ",'" + person.getFirstName() +  "','" + person.getLastName() + "','" + person.getEmail() + "','" + person.getPhoneNum() + "'," + 0 + ")";
            System.out.println("\nline 109\n");
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
            }catch(SQLException se) {}// do nothing
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
        //Person person = new Person(3333334, "Arjun Ghoshal", "eml", "phn");
        //checkIn(person);
    }
}