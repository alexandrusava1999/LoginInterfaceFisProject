package sample.FxmlAndControllerClass;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class DashBoardController {
    public static Label Label1;
    public void GoToLogin (ActionEvent event) throws IOException {
        Parent Register = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene registerwindow = new Scene(Register);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(registerwindow);
        window.show();
    }
    public static void main(String[] args){
        Label1.setText(LoginController.senduser());
    }

}
