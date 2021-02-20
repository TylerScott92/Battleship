import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.Node;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import java.util.Random;
import java.util.Scanner;
import java.io.*;

public class BattleShipGUI2 extends Application
{
   enum Player {USER, COMPUTER}
   private Game game;
   private final int SIZE = 10;
   private BorderPane mainPane;
   private GridPane compGrid;
   private GridPane userGrid;
   private Button compTurn;
   private Button exit;
   private Button newGame;
   private Button gridButton;
   private VBox sideButtons;
   private HBox bottomPane;
   private Text title;
   private String labelString = "";
   private String move;
   private Label gridLabel;
   private Player player;
   private Alert winner;
   private Label sideMessage;
   private String moveString;
   
   /**
      The userBoard method creates the user board
   */
   public void userBoard() throws IOException
   {
      CellStatus cell;
      Character c;
      
      
      for (int i = 0 ; i < SIZE ; i++) 
      {
         ColumnConstraints colConstraints = new ColumnConstraints();
         colConstraints.setHgrow(Priority.SOMETIMES);
         userGrid.getColumnConstraints().add(colConstraints);
      }

      for (int i = 0 ; i < SIZE ; i++) 
      {
         RowConstraints rowConstraints = new RowConstraints();
         rowConstraints.setVgrow(Priority.SOMETIMES);
         userGrid.getRowConstraints().add(rowConstraints);
      }
      
      for (int i = 0; i < SIZE; i++)
      {
         for (int j = 0; j < SIZE; j++)
         {
            cell = game.getComputerStatus(i, j);
            c = cell.toString().charAt(1);
            labelString = c.toString();
            gridLabel = new Label(labelString);
            gridLabel.setPrefSize(50, 50);
            gridLabel.setAlignment(Pos.CENTER);
            gridLabel.setStyle("-fx-background-color: LightSkyBlue");
            userGrid.add(gridLabel, i, j);
         }
      }
      
   }
   
   /**
      The compBoard method creates the computer board
   */
   public void compBoard() throws IOException
   {
      CellStatus cell;
      Character c;
      
      for (int i = 0 ; i < SIZE ; i++) 
      {
         ColumnConstraints colConstraints = new ColumnConstraints();
         colConstraints.setHgrow(Priority.SOMETIMES);
         compGrid.getColumnConstraints().add(colConstraints);
      }

      for (int i = 0 ; i < SIZE ; i++) 
      {
         RowConstraints rowConstraints = new RowConstraints();
         rowConstraints.setVgrow(Priority.SOMETIMES);
         compGrid.getRowConstraints().add(rowConstraints);
      }
      
      for (int i = 0; i < SIZE; i++)
      {
         for (int j = 0; j < SIZE; j++)
         {
            cell = game.getComputerStatus(i, j);
            c = cell.toString().charAt(1);
            labelString = c.toString();
            gridLabel = new Label(labelString);
            gridLabel.setPrefSize(50, 50);
            gridLabel.setAlignment(Pos.CENTER);
            gridLabel.setStyle("-fx-background-color: tomato");
            compGrid.add(gridLabel, j, i);
            
            gridButton = new Button();
            gridButton.setPrefSize(50, 50);
            gridButton.setStyle("-fx-background-color: LightSkyBlue");
            compGrid.add(gridButton, j, i);
            buttonPress();
         }
      }
   }
   
   /**
      The updateUserBoard method updates the user board to reflect the computer move
   */
   public void updateUserBoard(int row, int col)
   {
      Move move = new Move(row, col);
      game.makePlayerMove(move.toString());
//       CellStatus cell;
//       Character c;
//       gridLabel = new Label(labelString);
//       gridLabel.setPrefSize(50, 50);
//       gridLabel.setAlignment(Pos.CENTER);
//       gridLabel.setStyle("-fx-background-color: tomato");
//       compGrid.add(gridLabel, 0, 0, 1, 1);
//       Move move = new Move(row, col);
      
   }
   
   /**
      The updateCompBoard method updates the computer board to reflecte the user move
   */
   public void updateCompBoard()
   {
      CellStatus cell;
      String[] result = game.makeComputerMove();
      System.out.println(result[0] + result[1] + result +"bleep");
      Move move = new Move(result[0]);
      String test = new String(move.toString()); // Add the change of CellStatus character to this string
      gridLabel = new Label(test);
      gridLabel.setAlignment(Pos.CENTER);
      gridLabel.setPrefSize(50, 50);
      gridLabel.setStyle("-fx-background-color: yellow");
      int r = move.row();
      int column = move.col();
      userGrid.add(gridLabel, r, column);
      
      
   }
   
   /**
      The getGridCell method returns the contents of the GridPane cell on mouse click
   */
   public void getGridCell()
   {
      compGrid.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() 
      {
         @Override
         public void handle(MouseEvent e) 
         {
            for(Node node: compGrid.getChildren()) 
            {
               if(node instanceof Label) 
               {
                  if(node.getBoundsInParent().contains(e.getSceneX(),  e.getSceneY())) 
                  {
                     int row = GridPane.getRowIndex(node);
                     int col = GridPane.getColumnIndex(node);
                     Move m = new Move(row, col);
                     move = m.toString();
                     updateUserBoard(row, col);
                     System.out.println(node + " at " + GridPane.getRowIndex(node) + "," + GridPane.getColumnIndex(node));
//                      sideMessage = new Label("Move made: " + move);
//                      sideButtons.getChildren().add(sideMessage);
                     if (game.computerDefeated())
                     {
                        winner = new Alert(AlertType.INFORMATION);
                        winner.setHeaderText("You won! The game will now exit.");
                        winner.showAndWait();
                        Platform.exit();
                     }
                  }
               }
            }
         }
      });
   }
   
   // Get Node content
   private Node getNodeFromGridPane(GridPane compGrid, int col, int row) 
   {
      for (Node node : compGrid.getChildren()) 
      {
         if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) 
         {
            return node;
         }
      }
         return null;
   }
   
   public void buttonPress()
   {
      gridButton.setOnAction(new EventHandler<ActionEvent>() 
      {
         @Override 
         public void handle(ActionEvent e) 
         {
            if (player == Player.USER)
            {
               compGrid.getChildren().remove(e.getTarget());
            }
            
            player = Player.COMPUTER;
            compTurn.setDisable(false);
           //gridButton.setDisable(true);
           //gridButton.setVisible(false);               
           //gridButton.setStyle("-fx-background-color: transparent");
         }
      });
   }
   
   public void gameOver()
   {

   }
   /**
      Start of game
   */
   public void start(Stage primaryStage) throws IOException
   {
      game = new Game();
      primaryStage.setTitle("Battleship");
      mainPane = new BorderPane();
      compGrid = new GridPane();
      userGrid = new GridPane();
      sideButtons = new VBox();
      mainPane.setLeft(compGrid);
      mainPane.setCenter(userGrid);
      Random rand = new Random();
      compTurn = new Button("Computer Turn");
     
      // Initializes user board
      userBoard();
      userGrid.setGridLinesVisible(true);
      userGrid.setPadding(new Insets(10, 50, 25, 10));
      
      // Initializes computer boad
      compBoard();
      compGrid.setGridLinesVisible(true);
      compGrid.setPadding(new Insets(10, 25, 25, 25));
      
      // Returns contents of cell on mouse click
      //getGridCell();
      
      // Determine player at start
      if (rand.nextBoolean())
      {
         player = Player.COMPUTER;
      }
         
      else
      {
         player = Player.USER;
      }
         
      if (player == Player.USER) // Works, need to switch between turns now
      {
         getGridCell();
         compTurn.setDisable(true);  
      }
      
      if (player == Player.COMPUTER)
      {
         getGridCell();
      }
      
      if (game.userDefeated() == true)
      {
         System.out.println("You lost!");
         Platform.exit();
      }
      
      if (game.computerDefeated() == true)
      {
         System.out.println("Computer Lost!");
         Platform.exit();
      }

      // Side buttons layout
      sideButtons = new VBox(10);
      sideButtons.setAlignment(Pos.CENTER);
      sideButtons.setPadding(new Insets(10, 50, 10, 25)); 
      
      // Computer turn button
      compTurn.setOnAction(new EventHandler<ActionEvent>()
      {
         public void handle(ActionEvent e)
         {
            updateCompBoard();
            player = Player.USER;
            compTurn.setDisable(true);
         }
      });
      
      sideButtons.getChildren().add(compTurn); 
      
      // Alerts

      
      // Exit button
      exit = new Button("Exit");
      exit.setOnAction( new EventHandler<ActionEvent>() 
      {
         public void handle(ActionEvent e)
         {
            Platform.exit();
         }
      });
      
      sideButtons.getChildren().add(exit);
      
      // New game button
      newGame = new Button("New Game");
      newGame.setOnAction( new EventHandler<ActionEvent>() 
      {
         public void handle(ActionEvent e) 
         {

         }
      });
      
      sideButtons.getChildren().add(newGame);
      
      sideMessage = new Label("Move made: " + move);
      sideButtons.getChildren().add(sideMessage);
      
      // Add side buttons to main pane
      mainPane.setRight(sideButtons);
      
      Scene scene = new Scene(mainPane);
      primaryStage.setScene(scene);
      primaryStage.show();
   }
   
   public static void main(String[] args) throws IOException 
   {  
      launch(args);
      
   }
}