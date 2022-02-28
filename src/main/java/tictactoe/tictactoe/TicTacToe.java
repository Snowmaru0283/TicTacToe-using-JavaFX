package tictactoe.tictactoe;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.geometry.Insets;
import java.io.IOException;

public class TicTacToe extends Application {
    private Label turn;
    private Button[] buttons;
    private boolean finished;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TicTacToe.class.getResource("hello-view.fxml"));
        BorderPane layout = new BorderPane();
        Button play = new Button("Play Tic Tac Toe!");
        layout.setCenter(play);
        layout.setAlignment(play, Pos.CENTER);
        play.setFont(Font.font("Verdana", FontWeight.BOLD,25));
        play.setTextFill(Color. RED);
        layout.setPrefSize(350, 350);
        play.setOnAction(event-> {
            GridPane board = (GridPane)makeBoard();
            layout.setCenter(board);
            layout.setTop(turn);
            layout.setBottom(play);
            layout.setAlignment(turn, Pos.CENTER);
            layout.setAlignment(play, Pos. CENTER);
            play.setText("Restart");
            play.setFont(Font.font("Verdana",15));
            layout.setPadding(new Insets(15, 15, 15, 15));
        });
        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.show();
    }

    private Parent makeBoard() {
        this.buttons = new Button[9];
        this.turn = new Label("Turn: X");
        this.finished = false;
        GridPane layout = new GridPane();
        int k = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Button btn = new Button(" ");
                btn.setFont(Font.font("Monospaced", 40));
                btn.setOnAction(event -> {
                    if (checkIfEmpty(btn) && !this.finished) {
                        String[] actualTurn = this.turn.getText().split(" ");
                        if (actualTurn[1].equals("X")) {
                            btn.setText("X");
                            this.turn.setText("Turn: O");
                        }
                        if (actualTurn[1].equals("O")) {
                            btn.setText("O");
                            this.turn.setText("Turn: X");
                        }
                        if (checkWinner(actualTurn[1])) {
                            if (actualTurn[1].equals("X")){
                                this.turn.setText("X wins!");
                                this.finished = true;
                            } else if (actualTurn[1].equals("O")){
                                this.turn.setText("O wins!");
                                this.finished = true;
                            }
                        } else if (checkIfFinished()){
                            this.turn.setText("Tie!");
                            this.finished = true;
                        }
                    }
                });
                layout.add(btn, i, j);
                this.buttons[k] = btn;
                k++;
            }
        }
        turn.setFont(Font.font("Monospaced", 30));
        layout.setHgap(1);
        layout.setVgap(1);
        layout.setPadding(new Insets(10, 10, 10, 10));
        return layout;
    }

    private boolean checkIfFinished() {
        if (!checkIfEmpty(this.buttons[0])
                && !checkIfEmpty(this.buttons[1])
                && !checkIfEmpty(this.buttons[2])
                && !checkIfEmpty(this.buttons[3])
                && !checkIfEmpty(this.buttons[4])
                && !checkIfEmpty(this.buttons[5])
                && !checkIfEmpty(this.buttons[6])
                && !checkIfEmpty(this.buttons[7])
                && !checkIfEmpty(this.buttons[8])) {
            return true;
        }
        return false;
    }

    private boolean checkWinner(String turn) {
        //Checking horizontals
        for (int i = 0; i < 9; i = i+3) {
            if (this.buttons[i].getText().equals(turn) && this.buttons[(i+1)].getText().equals(turn) && this.buttons[(i+2)].getText().equals(turn)) {
                return true;
            }
        }
        //Checking verticals
        for (int i = 0; i < 3; i++) {
            if (this.buttons[i].getText().equals(turn) && this.buttons[(i+3)].getText().equals(turn) && this.buttons[(i+6)].getText().equals(turn)) {
                return true;
            }
        }
        //Checking diagonals
        if (this.buttons[0].getText().equals(turn) && this.buttons[4].getText().equals(turn) && this.buttons[8].getText().equals(turn)) {
            return true;
        } else if (this.buttons[2].getText().equals(turn) && this.buttons[4].getText().equals(turn) && this.buttons[6].getText().equals(turn)) {
                return true;
            }
        return false;
    }

    private boolean checkIfEmpty(Button button) {
        if (button.getText().equals(" ")) {
            return true;
        }
        return false;
    }
}
