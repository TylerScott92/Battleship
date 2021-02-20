import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
   The Board class constructs the boards and layouts for the game
*/

public class Board
{
   // Fields
   private ArrayList<ArrayList<CellStatus>> layout;
   private Fleet fleet;
   private CellStatus ship;
   private int index = 0;
   public static final int SIZE = 10;
   
   /**
      The constructor creates boards based on .txt file
   */
   
   public Board(String boardType) throws IOException
   {
      // Initialize first ArrayList
      layout = new ArrayList<ArrayList<CellStatus>>();
     
      
      // Loop first ArrayList and add second
      for (int i = 0; i < SIZE; i++)
      {  
         // Create second ArrayList 
         ArrayList<CellStatus> layoutRows = new ArrayList<>();
         
         // Loop second ArrayList and add enum
         for (int k = 0; k < SIZE; k++)
         {
            layoutRows.add(CellStatus.NOTHING);
         }
         
         // Add second ArrayList to first
         layout.add(layoutRows);
      }
      
      // Call load from file
      loadFromFile(boardType);
      
      // Initialize Flee
      fleet = new Fleet();
     
      
      
      
   }
   
   /**
      The loadFromFile method takes input from a file to be used in the board layout
   */
   public void loadFromFile(String filename) throws IOException
   {
         // Using absolute path as I can't seem to get a relative one to work
         File file = new File(filename); 
         Scanner s = new Scanner(file);
         while (s.hasNext())
         {
            // Set variables and objects
            String letter = s.next();
            Move start = new Move(s.next());
            Move end = new Move(s.next());
            Character ch = letter.charAt(0);
            
            // Length 
            int spaces = 0;
            CellStatus type = CellStatus.NOTHING;
            if (ch == 'A')
            {
               spaces = 5;
               type = CellStatus.AIRCRAFT_CARRIER;
            }
            if (ch == 'B')
            {
               spaces = 4;
               type = CellStatus.BATTLESHIP;
            }
            if (ch == 'C')
            {
               spaces = 3;
               type = CellStatus.CRUISER;
            }
            if (ch == 'S')
            {
               spaces = 3;
               type = CellStatus.SUB;
            }
            if (ch == 'D')
            {
               spaces = 2;
               type = CellStatus.DESTROYER;
            }
            
            // Direction
            if (start.row() > end.row())
            {
               for (int i = 0; i < spaces; i++)
                  layout.get(end.row() + i).set(end.col(), type);
            }
            else if (start.row() < end.row())
            {
               for (int i = 0; i < spaces; i++)
                  layout.get(start.row() + i).set(start.col(), type);
            }
            else if (start.col() > end.col())
            {
               for (int i = 0; i < spaces; i++)
                  layout.get(end.row()).set(end.col() + i, type);
            }
            else if (start.col() < end.col())
            {
               for (int i = 0; i < spaces; i++)
                  layout.get(start.row()).set(start.col() + i, type);
            }
         }
         
         // Close file
         s.close();
      
   }
   
   /**
      The applyMoveToLayout method takes in a move and applies it to the board
      @param The Move parameter takes in the move to be made
   */
   
   public void applyMoveToLayout(Move move)
   {
      // Create new cell status object
      CellStatus cell = layout.get(move.row()).get(move.col());
      
      // If cell matches, make move and update fleet
      if (cell != CellStatus.NOTHING)
      {
         if (cell == CellStatus.AIRCRAFT_CARRIER)
         {
            fleet.updateFleet(ShipType.ST_AIRCRAFT_CARRIER);
            layout.get(move.row()).set(move.col(), CellStatus.AIRCRAFT_CARRIER_HIT);
         }
         
         if (cell == CellStatus.CRUISER)
         {
            fleet.updateFleet(ShipType.ST_CRUISER);
            layout.get(move.row()).set(move.col(), CellStatus.CRUISER_HIT);
         }
         
         if (cell == CellStatus.DESTROYER)
         {
            fleet.updateFleet(ShipType.ST_DESTROYER);
            layout.get(move.row()).set(move.col(), CellStatus.DESTROYER_HIT);
         }
         
         if (cell == CellStatus.BATTLESHIP)
         {
            fleet.updateFleet(ShipType.ST_BATTLESHIP);
            layout.get(move.row()).set(move.col(), CellStatus.BATTLESHIP_HIT);
         }
         
         if (cell == CellStatus.SUB)
         {
            fleet.updateFleet(ShipType.ST_SUB);
            layout.get(move.row()).set(move.col(), CellStatus.SUB_HIT);
         }
      }
      
      else
      {
         System.out.println("Miss!");
         layout.get(move.row()).set(move.col(), CellStatus.NOTHING_HIT);
      }
      
   }
   
   /**
      The moveTaken method checks if the move is possible
      @param The Move parameter checks the Move that has been passed
      @return Returns if move is possible
   */
   
   public boolean moveTaken(Move m)
   {
      String move = m.toString();
      if (move.length() < 2 || move.length() >3 ||
         (move.charAt(0) < 'A' || move.charAt(0) > 'J') ||
         (Integer.parseInt(move.substring(1)) < 1 || Integer.parseInt(move.substring(1)) > 10))
      {
        return false; 
      }
      
      else
      {
         return true;
      }
      
   }
   
   /**
      The getLayout method returns the board layout
      @return Returns board layout
   */
   
   public CellStatus getStatus(int row, int col)
   {
      return layout.get(row).get(col);
   }
   
   public ArrayList<ArrayList<CellStatus>> getLayout()
   {
      return layout;
   }
   
   /**
      The getFleey method returns the fleet
      @return Returns the fleet
   */
   
   public Fleet getfleet()
   {
      return fleet;
   }
   
   /**
      The gameOver method checks if the game is over
      @return Checks gameOver method from feel to determine if game is over
   */
   public boolean gameOver()
   {
      return fleet.gameOver();
   }
   
}