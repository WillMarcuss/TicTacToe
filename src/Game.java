import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Game extends Application {
    @Override
    public void start(Stage stage) {
        Pane pane = new Pane();
        View view = new View();
        pane.getChildren().add(view);


        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                int finalRow = row;
                int finalCol = col;
                view.spaces[row][col].setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        String s = view.whoTurn();
                            view.spaces[finalRow][finalCol].setText(s);
                            if(view.checkWin(s)){
                                view.addWin(s);
                                System.out.println(s+" won");
                                view.disableButtons();
                            }
                    }
                });
            }
        }

        view.getReset().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                view.clearBoard();
            }
        });

        stage.setTitle("Tic Tac Toe");
        stage.setResizable(false);
        stage.setScene(new Scene(pane, 500,500));
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
