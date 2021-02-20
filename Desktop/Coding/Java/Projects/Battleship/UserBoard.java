import java.util.Arrays;
import java.util.ArrayList;
import java.io.*;
import java.util.Random;

/**
   The UserBoard class creates the user's board
*/
public class UserBoard extends Board
{
   // Fields
   private ArrayList<Move> move;
   private Random rand;
   
   /**
      The Constructor takes in the board filename and passes it to the Board class. It initializes the move and random.
   */
   public UserBoard(String userBoard) throws IOException
   {
      // Pass filename to Board class 
      super(userBoard);
      
      // Initialize ArrayList
      move = new ArrayList<>();

      // Initialize Random
      rand = new Random();
  
   }
   
   /**
      The makeComputerMove method creates a random move for the computer to take
      @return Returns the random move and whether the move resulted in a ship sinkings
   */
   public String[] makeComputerMove()
   {
      // Variables and arrays
      String coordinates;
      String[] realMove = new String[2];
      String[] result = new String[10];   
      String[] num = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
      String[] let = {"A", "B", "C", "D", "E", "F", "G", "H", "J"};
      
      // Creat random move
      Random randLet = new Random();
      int randomLetter = randLet.nextInt(let.length);
      Random randNum = new Random();
      int randomNumber = randNum.nextInt(num.length);
      
      // Assign move to strings and combine; then assign to first index in array
      String r1 = let[randomLetter];
      String r2 = num[randomNumber];
      coordinates = r1 + r2;
      realMove[0] = coordinates;
      
      // Determines if move resulted in ship sunk
      //String test = "Needs to call getSunk()"; // Works, now just get it to call getSunk()
      //realMove[1] = test;  
      
      // Apply move
      Move m = new Move(coordinates);
      applyMoveToLayout(m);
      CellStatus cell = super.getLayout().get(m.row()).get(m.col());
      
      return realMove;
   }
   
   /**
      The toString method displays the user board as strings
      @return Returns user board
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
            c = cell.toString().charAt(1);
            s += c.toString();
         }
         s += "\n";   
      }
      
      // Show board
      return "User Board\n" + s;

   }

}