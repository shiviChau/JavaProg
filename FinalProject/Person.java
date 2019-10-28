package javafaces;
import java.sql.*;

/**
 * This class models a person
 *
 * @author Rahul Kulkarni
 * @version 1.3
 */
public class Person
{
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/sys";

    //  Database credentials
    static final String USER = "admin";
    static final String PASS = "password";

    //instance variables
    private int idNum;
    private String name;
    private String email;
    private String phoneNum;

    /**
     * constructor for person
     */
    public Person()
    {
        idNum = 0;
        name = null;
        email = null;
        phoneNum = null;
    }
    /**
     * constructor for person
     *
     * @param n the name of the person
     */
    public Person(String n)
    {
        idNum = 0;
        name = n;
        email = null;
        phoneNum = null;
    }
    /**
     * constructor for person
     *
     * @param i the person's id number
     * @param n the person's name
     * @param e the person's email address
     * @param p the person's phone number
     */
    public Person(int i, String n, String e, String p)
    {
        idNum = i;
        name = n;
        email = e;
        phoneNum = p;
    }

    /**
     * getter for idNum
     *
     * @return idNum
     */
    public int getID()
    {
        return idNum;
    }
    /**
     * returns the person's first name
     *
     * @return the person's first name
     */
    public String getFirstName()
    {
        String returnString = "";
        //return name.substring(0,name.indexOf(" "));

        if(name.indexOf(" ") > 0) //if the name has a space in it (if there is a first and last name)
            //return the first name portion
            returnString = name.substring(0,name.indexOf(" "));
        else //if there is no space in the person's name
            returnString = name;

        return returnString;
    }
    /**
     * returns the person's last name
     *
     * @return the person's last name
     */
    public String getLastName()
    {
        String returnString = " ";

        if(name.indexOf(" ") > 0)
            returnString = name.substring(name.indexOf(" "));

        return returnString;
    }
    /**
     * getter for name
     *
     * @return name
     */
    public String getName()
    {
        return name;
    }
    /**
     * getter for email
     *
     * @return email
     */
    public String getEmail()
    {
        return email;
    }
    /**
     * getter for phoneNum
     *
     * @return phoneNum
     */
    public String getPhoneNum()
    {
        return phoneNum;
    }

    /**
     * setter for idNum
     *
     * @param id the new id
     */
    public void setIDnum(int id)
    {
        idNum = id;
    }
    /**
     * setter for name
     *
     * @param n the new name
     */
    public void setName(String n)
    {
        name = n;
    }
    /**
     * setter for email
     *
     * @param e the new email
     */
    public void setEmail(String e)
    {
        email = e;
    }
    /**
     * setter for phoneNum
     *
     * @param p the new phone number
     */
    public void setPhoneNum(String p)
    {
        phoneNum = p;
    }

    /**
     * checks whether the person is present
     *
     * @return isPresent whether or not the person is present
     */
    public boolean isPresent()
    {
        boolean isPresent = false;

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

            //select the isPresent variable
            String sql = "SELECT isPresent FROM members WHERE lastName='" + getLastName() + "'";
            ResultSet rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
            //Retrieve by column name
            while(rs.next())
                isPresent  = rs.getBoolean("isPresent");

            rs.close();
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
            }catch(SQLException se){}// do nothing
            try
            {
                if(conn!=null)
                    conn.close();
            }catch(SQLException se)
            {
                se.printStackTrace();
            }//end finally try
        }//end try

        return isPresent;
    }
}
