/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package z1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import java.sql.*;

/**
 * FXML Controller class
 *
 * @author xxazo
 */
public class LogInController implements Initializable {

    @FXML
    private TextField EmaText;
    @FXML
    private PasswordField passText;
    @FXML
    private Button LogInBut;
    @FXML
    private Button SignUpBut;
    
    
    
    private Parent root;
    private Stage stage;
    private Scene scene;
    @FXML
    private Label Label;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    public void switchToSceneSignup(ActionEvent event) throws IOException{
        
         root = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
         stage = (Stage)((Node)event.getSource()).getScene().getWindow();
         scene = new Scene(root);
         stage.setScene(scene);
         stage.show();
        
    }
    
    @FXML
    public void logIn(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {
        
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacy", "root", "123456");
        String Q = "select * from user where email=? and password=?";
        PreparedStatement pst = con.prepareStatement (Q);
        pst.setString(1,EmaText.getText());
        pst.setString(2,passText.getText());
        ResultSet rs = pst.executeQuery();
        if(rs.next()){
            root = FXMLLoader.load(getClass().getResource("main.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show(); 
        }else{
            Label.setText("Email or password is incorrect");
            pst.setString(1,EmaText.getText());
            pst.setString(2,passText.getText());
        }
        pst.close();
        con.close();       
    }  
}
