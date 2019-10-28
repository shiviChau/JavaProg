
/**
 * Gives possible moves of a knight starting on 1 x 1
 *
 * @Shivi
 * @011019
 */
import java.util.*;
/*
  This program will start the knight chess piece at a corner of the
  board and attempt to move to all 64 squares.  This program
  will use a random number generator to attempt a move of type 1-8.
  The possible moves are categorized as follows:

  move 1:  +1 to the right, -2 up, a negative move to go up in the grid
  move 2:  +2 to the right, -1 up
  move 3:  +2 to the right, +1 down
  move 4:  +1 to the right, +2 down
  move 5:  -1 to the left, +2 down
  move 6:  -2 to the left, +1 down
  move 7:  -2 to the left, -1 up
  move 8:  -1 to the left, -2 up

  The program will move the knight until all 64 squares have been
  hit (extremely rare), or the piece gets stuck.  The program will
  print out the board, with numbers ranging from 1-64 for the order
  in which the squares were visited.
*/

class KnightsTour
{
      private int[] myHorizMove = { 1, 2, 2, 1, -1, -2, -2, -1};
      private int[] myVertMove = { -2, -1, 1, 2, 2, 1, -1, -2};
      private int[] knightsMatrix[][];
    //constructor 
  /**
     *constructor for KnightsTour
  */
    public KnightsTour()
    {
      int [][] knMatrix  = new int[9][9];
    }
    // generating a random number 
    private int randomNumGen()
    {
      Random random = new Random();
      int moveIndex = random.nextInt(7);
      return moveIndex;
    }
    //checks if move is valid 
    private boolean check(int moveIndex, int row, int col, int[][] knMatrix)
    {
      boolean validMove = false;
      int hMove = myHorizMove[moveIndex];
      int vMove = myVertMove[moveIndex];
      if((row + hMove <= 8 && row + hMove >= 1) &&   
        (col + vMove <= 8 && col + vMove >= 1) && 
        (knMatrix[row+hMove][col+vMove] == 0))
            validMove = true;
      return validMove; 
    }
    /**
     *Generates a new matrix 
     *initializes to all 0's
     *checks if a move is valid by sending to check method
     *brings knight to valid position
     *prints new matrix
      */
     public void knightMatrix()
    {
      int [][] knMatrix  = new int[9][9];
      KnightsTour knight = new KnightsTour();
      int checkedMove = 0;
      int timesChecked = 0;
      int hPosition = 1;
      int vPosition = 1;
      int counter = 1;
      boolean checkFlag = false;
      //initializes matrix to all 0's - A ZERO at any position means the knight can move in.
      //A NON-ZERO MEANS STABLE IS BURNT
      for(int row = 0; row < 9; row++)
        for(int col = 0; col < 9; col++) 
                knMatrix[row][col] = 0;
      for(int row = 1; row < 9; row++)
      {  
          for(int col = 1; col < 9; col++)
            {
                //int positionRow = 0;
                //int positionCol = 0; 
                int randomNum = knight.randomNumGen();
                if(row == 1 && col == 1) 
                   {
                     knMatrix[row][col] = counter;  
                     hPosition = 1;
                     vPosition = 1;
                   }
                if(knight.check(randomNum,hPosition,vPosition,knMatrix) == true)
                { 
                    int horizMove = myHorizMove[randomNum];
                    int vertMove = myVertMove[randomNum];
                    if(row == 1 && col == 1) 
                    {
                       hPosition = 1 + horizMove;
                       vPosition = 1 + vertMove;
                    } 
                    else 
                    {  
                        counter++;
                        hPosition += horizMove;
                        vPosition += vertMove;
                        // BURN THE STABLE BY PUTTING IN A NON-ZERO VALUE
                        knMatrix[hPosition][vPosition] = counter;
                    }   
                    
                }
                //check fails
                else
                {
                       //keeping track of how many false moves 
                       timesChecked = 1;
                
                       while(knight.check(randomNum,hPosition,vPosition,knMatrix) == false && timesChecked < 8)
                       {
                        timesChecked++;
                        randomNum++;
                        randomNum%=8;
                        checkedMove = randomNum;
                        if(timesChecked == 7)
                            checkFlag=true;
                        }
                       
                       if(timesChecked >= 8) 
                            break;
                       else 
                       {
                            // Found a valid move
                            timesChecked = 0;
                            if(knight.check(randomNum,hPosition,vPosition,knMatrix) == true)
                            {        
                                int horizMove = myHorizMove[randomNum];
                                int vertMove = myVertMove[randomNum];
                                counter++;
                                hPosition += horizMove;
                                vPosition += vertMove;
                                knMatrix[hPosition][vPosition] = counter;
                            
                        
                            }
                       }
                }//end of else check fails
            } // end of internal for 
            if(timesChecked >= 8)
            {
                break;
            }
          
      } // end of external for 
      for(int row = 1; row < 9; row++)
      {
            for(int col = 1; col < 9; col++)
              { 
                  System.out.print(knMatrix[row][col] + " " );
              }
              System.out.println();
         }
         System.out.println("there are no more moves to make");
         System.out.println("total moves: " + counter);
    }
}
