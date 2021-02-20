import java.util.Scanner;

/**
   The Move class sets up the parameters to make moves in the game
*/

public class Move
{

   private int row;
   private int col;
   private String coordinates;
   private String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
   private String[] numbers = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
   
   /**
      The constructor takes in the the move as integers
      @param The integer parameters set the Move location
   */
   
   public Move(int r, int c)
   {
      row = r;
      col = c;
      
      coordinates = letters[row] + numbers[col];
      
   }
   
   /**
      The overloaded constructor takes in the move as a string
      @param The Move as a string
   */
   
   public Move(String c)
   {  
      // Set up rows
      Character ch = c.charAt(0);
      if (ch == 'A')
      {
         this.row = 0;
      }
      
      if (ch == 'B')
      {
         this.row = 1;
      }
      
      if (ch == 'C')
      {
         this.row = 2;
      }
      
      if (ch == 'D')
      {
         this.row = 3;
      }
      
      if (ch == 'E')
      {
         this.row = 4;
      }
      
      if (ch == 'F')
      {
         this.row = 5;
      }
      
      if (ch == 'G')
      {
         this.row = 6;
      }
      
      if (ch == 'H')
      {
         this.row = 7;
      }
      
      if (ch == 'I')
      {
         this.row = 8;
      }
      
      if (ch == 'J')
      {
         this.row = 9;
      }
      
      
      
      // Set up columns
      Character ch2 = c.charAt(1);
      if (ch2 == '1')
      {
         this.col = 0;
      }
      
      if (ch2 == '2')
      {
         this.col = 1;
      }
      
      if (ch2 == '3')
      {
         this.col = 2;
      }
      
      if (ch2 == '4')
      {
        this.col = 3;
      }
      
      if (ch2 == '5')
      {
         this.col = 4;
      }
      
      if (ch2 == '6')
      {
         this.col = 5;
      }
      
      if (ch2 == '7')
      {
         this.col = 6;
      }
      
      if (ch2 == '8')
      {
         this.col = 7;
      }
      
      if (ch2 == '9')
      {
         this.col = 8;
      }
      
      if (ch2 == '1' + '0')
      {
         this.col = 9;
      }
      
      
      
      
      coordinates = c;
   }
   
   /**
      The row method returns the row
      @return Returns row
   */
   
   public int row()
   {
      return row;
   }
   
   /**
      The col method retursn the column
      @return Returns the column
   */
   
   public int col()
   {
      return col;
   }
   
   /**
      The toString returs the coordinates
      @return Returns the coordinates
   */
   
   public String toString()
   {
      return coordinates;
     
   }
}