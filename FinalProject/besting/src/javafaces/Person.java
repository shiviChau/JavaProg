package javafaces;
import java.sql.*;

public class Person
{
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/sys";

    //  Database credentials
    static final String USER = "admin";
    static final String PASS = "password";

    private int idNum;
    private String name;
    private String email;
    private String phoneNum;

    public Person()
    {
        idNum = 0;
        name = null;
        email = null;
        phoneNum = null;
    }
    public Person(String n)
    {
        idNum = 0;
        name = n;
        email = null;
        phoneNum = null;
    }
    public Person(int i, String n, String e, String p)
    {
        idNum = i;
        name = n;
        email = e;
        phoneNum = p;
    }

    public int getID()
    {
        return idNum;
    }
    public String getFirstName()
    {
        String returnString = "";
        //return name.substring(0,name.indexOf(" "));
        if(name.indexOf(" ") > 0)
            returnString = name.substring(0,name.indexOf(" "));
        else
            returnString = name;

        return returnString;
    }
    public String getLastName()
    {
        String returnString = " ";

        if(name.indexOf(" ") > 0)
            returnString = name.substring(name.indexOf(" "));

        return returnString;
    }
    public String getName()
    {
        return name;
    }
    public String getEmail()
    {
        return email;
    }
    public String getPhoneNum()
    {
        return phoneNum;
    }

    public void setIDnum(int id)
    {
        idNum = id;
    }
    public void setName(String n)
    {
        name = n;
    }
    public void setEmail(String e)
    {
        email = e;
    }
    public void setPhoneNum(String p)
    {
        phoneNum = p;
    }

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
            System.out.println("\nperson line 90\n");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("\nperson line 92\n");

            //STEP 4: Execute a query
            System.out.println("\nperson line 95\n");
            stmt = conn.createStatement();

            //String query = "SELECT * FROM users WHERE surname='" + isim + "'";
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
        System.out.println("\nperson line 132\n");

        return isPresent;
    }
}