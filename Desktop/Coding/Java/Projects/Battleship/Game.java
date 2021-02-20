import java.util.Scanner;
import java.io.*;

/**
   The game class creates a game of battleship
*/ 
public class Game
{
  private ComputerBoard computer;
  private UserBoard player;
  
  /**
     The constructor initializes the computer and user board
  */
  public Game() throws IOException
  {
      computer = new ComputerBoard("compFleet.txt");
      player = new UserBoard("userFleet.txt");
  }
  
  public CellStatus getComputerStatus(int row, int col)
  {
     return computer.getStatus(row, col);
  }
  
  public CellStatus getUserStatus(int row, int col)
  {
     return player.getStatus(row, col);
  }
  
  /**
     The makeComputerMove method takes in the computers random move and applies it to the user board
     @return Returns computer move
  */
  public String[] makeComputerMove()
  {
     return player.makeComputerMove();    
    
  }
  
  /**
     The makePlayerMove method takes in the user input as a move and applies that move to the computer board
     @return Returns player move
  */
  public String makePlayerMove(String playerMove)
  {
     Move move = new Move(playerMove);
     return computer.makePlayerMove(move);
  
  }
  
  /**
     The computerDeafeated method determines if the computer has lost the game
     @return Returns true if computer is defeated
     @retrun Returns fales if computer is not defeated
  */
  public boolean computerDefeated()
  {
     if (computer.gameOver() == true)
     {
        System.out.println("The Computer Lost!");
        return true;
     }
     
     else 
     {
        return false;
     }
  }
  
  /**
     The userDeafeated method determines if the user has lost the game
     @return Returns true if user has lost the game
     @return Returns false if user has not lost the game
  */
  public boolean userDefeated()
  {
     if (player.gameOver() == true)
     {
        System.out.println("Sorry you lost!");
        return true;
     }
     else
     {
        return false;
     }
  }
  
  /**
     The toString method displays the updated user and computer boards
     @return Returns updated boards
  */
  
  public String toString()
  {
     return "\n///// Updated Boards \\\\\\\\\\" + "\n\n" + computer + "\n" + player;
  }
}