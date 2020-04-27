package sample.FxmlAndControllerClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.security.NoSuchAlgorithmException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;




public class RegisterController {
    public Label Label1;
    public TextField Username;
    public TextField Mail;
    public DatePicker Data;
    public PasswordField Password;
public void changescenebutton(ActionEvent event) throws IOException {

    Parent login = FXMLLoader.load(getClass().getResource("Login.fxml"));
    Scene loginview = new Scene(login);
    Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

    window.setScene(loginview);
    window.show();
}
public void SaveData(ActionEvent event) throws Exception {
    String dataName,UserName;
    boolean sw =true;

    UserName = Username.getText();
        if(Username.getText().trim().isEmpty() ||
            Mail.getText().trim().isEmpty() ||
            Data.getValue().toString().isEmpty() ||
                Password.getText().trim().isEmpty()
        )
        {
            Label1.setText("Empty");
        }

    try {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        Statement myStatement = connection.createStatement();
        ResultSet myResult = myStatement.executeQuery("select * from user");

        while (myResult.next()) {
            dataName=myResult.getString("Name");
            if(UserName.equals(dataName))
                sw=false;

        } }catch(Exception exe){exe.printStackTrace();}


        if(sw)
        {

            ConnectionClass connectionClass = new ConnectionClass();
            Connection connection = connectionClass.getConnection();
            String parola=Password.getText();
            try {
                parola = BasicCrypto.encrypt(parola);
            }
            catch(NoSuchAlgorithmException e) {}
            String sql = "INSERT INTO USER VALUES('" + Username.getText() + "' , '" + parola + "' , '" + Mail.getText() + "' , '" + Data.getValue().toString() + "')";
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            JavaMailSend.sendMail(Mail.getText(),Username.getText(),Password.getText());
        }
        else
        {
            Label1.setText("This Username alredy exist!");
        }

}
}
