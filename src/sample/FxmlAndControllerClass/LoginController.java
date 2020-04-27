package sample.FxmlAndControllerClass;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.security.NoSuchAlgorithmException;
import java.sql.*;

import java.io.IOException;

public class LoginController {
       public TextField user;
       public PasswordField pass;
        public Label Label1;
        public static String demonu;
        public void GoToRegister (ActionEvent event) throws IOException {
                Parent Register = FXMLLoader.load(getClass().getResource("../../../../ProjectFisChatApplication/src/main/java/sample/FxmlAndControllerClass/Register.fxml"));
                Scene registerwindow = new Scene(Register);
                Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

                window.setScene(registerwindow);
                window.show();

        }

        public void GoToDashBoard (ActionEvent event) throws IOException {
                demonu=user.getText();
                String UserName = user.getText().toString();
                String dataName,dataPassword;
                boolean sw=false;
                try {
                        ConnectionClass connectionClass = new ConnectionClass();
                        Connection connection = connectionClass.getConnection();
                        Statement myStatement = connection.createStatement();
                        ResultSet myResult = myStatement.executeQuery("select * from user");

                        while (myResult.next()) {
                                dataName=myResult.getString("Name");
                                if(UserName.equals(dataName))
                                {
                                        sw=true;
                                        dataPassword=myResult.getString("Password");

                                        String parola=pass.getText();
                                        try {
                                                parola = BasicCrypto.encrypt(parola);
                                        }
                                        catch(NoSuchAlgorithmException e) {}

                                        if(dataPassword.equals(parola))
                                        {
                                        Parent Register = FXMLLoader.load(getClass().getResource("DashBoard.fxml"));
                                        Scene registerwindow = new Scene(Register);
                                        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

                                        window.setScene(registerwindow);
                                        window.show();}

                                }
                        }
                        if(sw)
                                Label1.setText("Wrong Password");
                }catch(Exception exe){exe.printStackTrace();}



        }
        public static String senduser()
        {
                return demonu;
        }


}
