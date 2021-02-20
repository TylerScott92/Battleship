import java.io.*;
import java.util.Scanner;

/**
   The ComputerBoard class creates the computers's board
*/

public class ComputerBoard extends Board
{
   /**
      The constructor calls the super class to create board
   */
   
   public ComputerBoard(String compBoard) throws IOException
   {
      super(compBoard);
   }
   
   /**
      The makePlayerMove method makes the players move
      @param The Move parameter takes the Move being passed into it
   */
   public String makePlayerMove(Move m)
   {  
      applyMoveToLayout(m);
      
      String output = "Needs to call getSunk()";
      
      return output;
      
   }
   
   /**
      The toString returns the board
      @return Returns the board layout
   */
   
   @Override
   public String toString()
   {
      // Variables
      CellStatus cell;
      Character c;
      String s = "";
      
      // Loop to add strings to index of layout
      for (int i = 0; i < 10; i++)
      {
         for (int j = 0; j < 10; j++)
         {
            cell = super.getLayout().get(i).get(j);
            c = cell.toString().charAt(0);
            s += c.toString();
         }
         s += "\n";
      }
      
      // Show board
      return "Computer Board\n" + s;
   }
}