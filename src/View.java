import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;

public class View extends Pane {
    private Rectangle firstVertical;
    private Rectangle secondVertical;
    private Rectangle firstHorizontal;
    private Rectangle secondHorizontal;
    private Rectangle winningLine;
    private Label xWinsText;
    private Label yWinsText;
    private boolean xTurn;
    private Button reset;
    public Button[][] spaces;
    public View(){
        xTurn = true;
        winningLine = new Rectangle();
        firstVertical = new Rectangle(180,50,10,400);
        secondVertical = new Rectangle(310,50,10,400);
        firstHorizontal = new Rectangle(50,180,400,10);
        secondHorizontal = new Rectangle(50,310,400,10);

        xWinsText = new Label("0");
        xWinsText.relocate(20,50);

        yWinsText = new Label("0");
        yWinsText.relocate(40,50);

        reset = new Button("Reset");
        reset.relocate(10,10);
        reset.setPrefSize(50,25);
        getChildren().addAll(firstVertical,secondVertical,firstHorizontal,secondHorizontal,reset,xWinsText,yWinsText);

        spaces = new Button[3][3];
        int yIncrement = 150;
        int xIncrement = 150;
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                spaces[row][col] = new Button();
                spaces[row][col].relocate(60+ col*xIncrement,60+row*yIncrement);
                spaces[row][col].setPrefSize(115,115);
                spaces[row][col].setStyle("-fx-background-color: transparent;"+"-fx-font-size:60;");
                getChildren().add(spaces[row][col]);
                if(col == 0){
                    xIncrement = 132;
                }
            }
            if(row == 0){
                yIncrement = 132;
            }
        }
    }
    public String whoTurn(){
        if(xTurn == true){
            xTurn = false;
            return "X";
        }
        else {
            xTurn = true;
            return "o";
        }
    }

    public boolean checkWin(String XorO){
        if(spaces[0][0].getText() == XorO && spaces[0][1].getText() == XorO &&spaces[0][2].getText() == XorO){ // First horiz
            winningLine = new Rectangle(70,110,350,20);
            winningLine.setFill(Color.GREEN);
            getChildren().add(winningLine);
            return true;
        }
        if(spaces[1][0].getText() == XorO&& spaces[1][1].getText() == XorO &&spaces[1][2].getText() == XorO){ // Second horiz
            winningLine = new Rectangle(70,240,350,20);
            winningLine.setFill(Color.GREEN);
            getChildren().add(winningLine);
            return true;
        }
        if(spaces[2][0].getText() == XorO&& spaces[2][1].getText() == XorO &&spaces[2][2].getText() == XorO){ // Third horiz
            winningLine = new Rectangle(70,375,350,20);
            winningLine.setFill(Color.GREEN);
            getChildren().add(winningLine);
            return true;
        }
        if(spaces[0][0].getText() == XorO && spaces[1][0].getText() == XorO &&spaces[2][0].getText() == XorO){ // First Vert
            winningLine = new Rectangle(110,70,20,350);
            winningLine.setFill(Color.GREEN);
            getChildren().add(winningLine);
            return true;
        }
        if(spaces[0][1].getText() == XorO && spaces[1][1].getText() == XorO &&spaces[2][1].getText() == XorO){ // Second Vert
            winningLine = new Rectangle(240,70,20,350);
            winningLine.setFill(Color.GREEN);
            getChildren().add(winningLine);
            return true;
        }
        if(spaces[0][2].getText() == XorO && spaces[1][2].getText() == XorO &&spaces[2][2].getText() == XorO){ // Third Vert
            winningLine = new Rectangle(375,70,20,350);
            winningLine.setFill(Color.GREEN);
            getChildren().add(winningLine);
            return true;
        }
        if(spaces[0][0].getText() == XorO && spaces[1][1].getText() == XorO && spaces[2][2].getText() == XorO){ // Right down diagonal
            winningLine = new Rectangle(240,70,20,350);
            winningLine.setFill(Color.GREEN);
            Rotate r1 = new Rotate();
            r1.setAngle(-45);
            r1.setPivotX(250);
            r1.setPivotY(250);
            winningLine.getTransforms().addAll(r1);
            getChildren().add(winningLine);
            return true;
        }
        if(spaces[0][2].getText() == XorO && spaces[1][1].getText() == XorO && spaces[2][0].getText() == XorO){ // Left down diagonal
            winningLine = new Rectangle(240,70,20,350);
            winningLine.setFill(Color.GREEN);
            Rotate r1 = new Rotate();
            r1.setAngle(45);
            r1.setPivotX(250);
            r1.setPivotY(250);
            winningLine.getTransforms().addAll(r1);
            getChildren().add(winningLine);
            return true;
        }

        return false;
    }

    public void clearBoard(){
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                spaces[row][col].setText("");
                spaces[row][col].setDisable(false);
            }
        }
        xTurn = true;
        winningLine.setVisible(false);

    }
    public Button getReset(){
        return reset;
    }

    public void disableButtons() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                spaces[row][col].setDisable(true);
            }
        }
    }

    public void addWin(String winner){
        if(winner == "X"){
            xWinsText.setText(""+(Integer.parseInt(xWinsText.getText())+1));
        }
        else{
            yWinsText.setText(""+(Integer.parseInt(yWinsText.getText())+1));
        }
    }


}
