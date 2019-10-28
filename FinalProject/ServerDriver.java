package javafaces;

/**
*   ServerDriver Class
*   @Shivi, Jack, Arjun, Rahul
*   @June 6, 2019
*/
public class ServerDriver
{
    /**
    *   Main Method
    *   @Param String[] args
    */
    public static void main(String[] args)
    {
        //only runs while the FRDriver main method runs without exception
        while(true)
        {
            FRDriver.main(new String[0]);
        }
    }
}
