import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.Node;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.ColumnConstraints;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import java.util.Random;
import java.util.Scanner;
import java.io.*;

public class BattleshipGUI extends Application
{
   enum Player {USER, COMPUTER}
   private Game game;
   private GridPane userGrid;
   private GridPane compGrid;
   private Pane cellPane = new Pane();
   //private AnchorPane mainPane;
   private BorderPane mainPane;
   private VBox statusPane;
   private VBox sideButtons;
   private Text status;
   private Text title;
   private HBox layout;
   private HBox buttonPane;
   private Label nameLabel;
   private Button exit, newGame;
   private Button gridButton;
   private Scanner scanner;
   private Random random;
   private final int SIZE = 10;
   private String buttonLabel = "";
   private String userMove;
   private char gridChar;
   private char intChar;
   private int gridRow;
   private int gridCol;
   
   public static void main(String[] args) throws IOException // Must catch and throw this in final product
   {  
      launch(args);
   }
   
   public void userBoard()
   {
      CellStatus cell;
      Character c;
      cellPane = new Pane();
      
      for (int i = 0;  i < SIZE; i++)
      {
         RowConstraints row = new RowConstraints(50);
         userGrid.getRowConstraints().add(row);
         
      
         for (int j = 0; j < SIZE; j++)
         {  
            cell = game.getUserStatus(i,j); 
            c = cell.toString().charAt(1);
            buttonLabel = c.toString();
            nameLabel = new Label(buttonLabel);
            nameLabel.setPrefSize(50, 50);
            nameLabel.setStyle("-fx-background-color: blue");
            //userGrid.setFillWidth(nameLabel, true);
           // nameLabel.setMaxWidth(Double.MAX_VALUE);
            nameLabel.setAlignment(Pos.CENTER);
            userGrid.add(nameLabel,i,j);
            gridButton = new Button("");
            gridButton.setPrefSize(50, 50);
            //gridButton.setStyle("-fx-background-color:blue");
            userGrid.add(gridButton, i, j);
//            ap.getChildren().remove(btn); // Code to get coordintes and remove
//             cellPane = new Pane();
//             cellPane.setPrefSize(50, 50);
//             cellPane.setStyle("-fx-background-color:blue");
//             cellPane.setOnAction(new EventHandler<ActionEvent>() 
//             {
//                @Override 
//                public void handle(ActionEvent e) 
//                {  
//                   cellPane.setVisible(false);
//                   //userGrid.getChildren().remove(cellPane);
//                }
//             });
//            userGrid.add(cellPane, i, j);
            //cellButton.setStyle("-fx-background-color: transparent;"); // Makes the button invisible
         }
      }
//             gridButton.setOnAction(new EventHandler<ActionEvent>() 
//             {
//                @Override 
//                public void handle(ActionEvent e) 
//                {
//                   gridButton.setStyle("-fx-background-color: transparent;");
//                }
//             });
   }
   
   public void compBoard() //throws IOException
   {
      CellStatus cell;
      Character c;
      
//      compgrid.getChildren().clear(); // clear the board
      
      for (int i = 0;  i < SIZE; i++)
      {
      
         for (int j = 0; j < SIZE; j++)
         {  
            cell = game.getComputerStatus(i,j); 
            c = cell.toString().charAt(1);
            buttonLabel = c.toString();
            nameLabel = new Label(buttonLabel);
            nameLabel.setPrefSize(50, 50);
            nameLabel.setAlignment(Pos.CENTER);
            nameLabel.setStyle("-fx-background-color:blue");
            compGrid.add(nameLabel,i,j);
            gridButton = new Button("");
            gridButton.setPrefSize(50, 50);
            //gridButton.setStyle("-fx-background-color:red");
           // gridButton.setVisible(false);
           // compGrid.add(gridButton, i, j);

//             cellPane = new Pane();
//             cellPane.getStyleClass().add("-fx-background-color:  blue");
//             compGrid.add(cellPane, i, j);

         }
         
      } 
      
//             gridButton.setOnAction(new EventHandler<ActionEvent>() 
//             {
//                @Override 
//                public void handle(ActionEvent e) 
//                {
//                   gridButton.setStyle("-fx-background-color: transparent;");
//                }
//             });
      
//       gridButton.setOnAction(new EventHandler<ActionEvent>() {
//     @Override public void handle(ActionEvent e) {
//         gridButton.setVisible(false);
//     }
// });
           
   }
   

   // event handler for user clicking on a square
//    public void handleClick(MouseEvent e)
//    {  
         CellStatus cell;
//       CellPane cp = (CellPane)(e.getSource());
//       if (game.move(cp.getRow(),cp.getCol()))
//       {
//          userBoard();
//          if (game.gameOver())
//          {
//             for (Node pane: grid.getChildren())
//                pane.setOnMouseClicked(null);
// 
//             char ch = game.winner();
//             if (ch == 'x')
//                status.setText("X wins");
//             
//             else if (ch == 'o')
//                status.setText("O wins");
//             else
//                status.setText("It's a draw");
//             
//          }
//       }
//          
//       
//    }   

   
   @Override
   public void start(Stage primaryStage) throws IOException // Need to catch this as well
   {
      primaryStage.setTitle("BattleShip");
      
      // Set up panes
      mainPane = new BorderPane();
      userGrid = new GridPane();
      compGrid = new GridPane();
      sideButtons = new VBox();
      mainPane.setCenter(userGrid);
      mainPane.setLeft(compGrid);
      scanner = new Scanner(System.in);
      random = new Random(); 
      game = new Game();
      Player player;
      TextField tf = new TextField();
      
      // User board (for computer to make moves against)
       userBoard();
       userGrid.setGridLinesVisible(true);
      // userGrid.setAlignment(Pos.CENTER);
       userGrid.setPadding(new Insets(10, 25, 25, 10));
//        userGrid.setHgap(20);
//        userGrid.setVgap(10);
      

//       userGrid.setAlignment(Pos.CENTER);
//       userGrid.setMinSize(500, 500);
//          gridButton.setOnAction(new EventHandler<ActionEvent>() 
//          {
//             @Override 
//             public void handle(ActionEvent e) 
//             {
//                //gridButton.setDisable(true);
//                //gridButton.setVisible(false);               
//                gridButton.setStyle("-fx-background-color: transparent;");
//             }
//          });
//       userGrid.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
//       @Override
//       public void handle(MouseEvent e) {
// 
//           for(Node node: userGrid.getChildren()) {
//           
// //              gridRow = GridPane.getRowIndex(node);
// //              gridCol = GridPane.getColumnIndex(node);
//              
// 
//              if(node instanceof Label) {
//                   if(node.getBoundsInParent().contains(e.getSceneX(),  e.getSceneY())) {
//                       System.out.println(node + " at " + GridPane.getRowIndex(node) + ":" + GridPane.getColumnIndex(node));
//                   }
//                   
// //              Move move = new Move(gridRow, gridCol);
// //              userMove = move.toString();
// //              System.out.println(userMove);
//              }
//              
//    
//           }
//             }
//         });
      
//       // Computer board (for user to make moves against)
      compBoard(); 
      compGrid.setGridLinesVisible(true);
//      compGrid.setAlignment(Pos.CENTER);
      compGrid.setPadding(new Insets(10, 50, 25, 50));
//       compGrid.setHgap(20);
//       compGrid.setVgap(10);
//       compGrid.setMinSize(100, 100);
      compGrid.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {

          for(Node node: compGrid.getChildren()) {

              if(node instanceof Label) {
                  if( node.getBoundsInParent().contains(e.getSceneX(),  e.getSceneY())) {
                      System.out.println(node + " at " + GridPane.getRowIndex(node) + "," + GridPane.getColumnIndex(node));
                  }
              }
          }
            }
        });

      

        
      
        

      



      
      
      
//       // Add both boards to HBox
//       layout = new HBox();
//       layout.setAlignment(Pos.CENTER);
//       layout.setSpacing(30.0);
//       layout.setPadding(new Insets(5,5,5,5));
//       layout.getChildren().addAll(compGrid, userGrid);
//       mainPane.setCenter(layout);
      
//       // Top title
//       statusPane = new VBox();
//       title = new Text("Battleship");
//       title.setFont(Font.font("Arial",24));
//       statusPane.setAlignment(Pos.CENTER);
//       status = new Text("");
//       statusPane.getChildren().add(title);
//       statusPane.getChildren().add(status);
// 
//       mainPane.setTop(statusPane);
      
      // Bottom buttons
      sideButtons = new VBox(10);
      sideButtons.setAlignment(Pos.CENTER);
      sideButtons.setPadding(new Insets(10, 50, 10, 25));  
      //sideButtons.setPrefSize(100, 75);    
      exit = new Button("Exit");
      exit.setOnAction( new EventHandler<ActionEvent>() {
         public void handle(ActionEvent e)
         {
            Platform.exit();
         }
       });
      sideButtons.getChildren().add(exit);
      
      
      newGame = new Button("New Game");
      newGame.setOnAction( new EventHandler<ActionEvent>() {
         public void handle(ActionEvent e) 
         {
            
         }
       });
     sideButtons.getChildren().add(newGame);

     mainPane.setRight(sideButtons);
      
      // complete setup
      Scene scene = new Scene(mainPane);
      primaryStage.setScene(scene);
      
      primaryStage.show();
   }
   
   // Get GridPane content
   private Node getNodeFromGridPane(GridPane gridPane, int col, int row) {
    for (Node node : gridPane.getChildren()) {
        if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
            return node;
        }
    }
    return null;
}
   

   
//    class ButtonClickHandler implements EventHandler<actionevent>
//    {
//       @Override
//       public void buttonClick(ActionEvent event)
//       {
//          CellStauts cell;
//       }
//    }
}