/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package z1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.sql.*;

/**
 * FXML Controller class
 *
 * @author xxazo
 */
public class SignUpController implements Initializable {

    @FXML
    private TextField nameText;
    @FXML
    private TextField SignUpEma;
    @FXML
    private TextField SignUpPas;
    @FXML
    private Button register;
    @FXML
    private Button signIn;
    
    private Parent root;
    private Stage stage;
    private Scene scene;
    
    @FXML
    private Label labelEma;
    @FXML
    private Label labelPass;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    @FXML
    public void switchToSceneLognIn(ActionEvent event) throws IOException{
        
        root = FXMLLoader.load(getClass().getResource("LogIn.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void signUp(ActionEvent event) throws ClassNotFoundException, SQLException, IOException{
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacy", "root", "123456");
        
            String Q = "INSERT INTO user (name,email,password) VALUES (?,?,?)";
            PreparedStatement pst = con.prepareStatement (Q);
        
            pst.setString(1,nameText.getText());
            pst.setString(2,SignUpEma.getText());
            pst.setString(3,SignUpPas.getText());
            
        
            int rs = pst.executeUpdate();
        
            if(rs == 1){
                root = FXMLLoader.load(getClass().getResource("LogIn.fxml"));
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show(); 
            }
            pst.close();
            con.close();
        }
        catch(Exception e){
            
            labelEma.setText("This email is already in use.");
        }
        

            
    }    
}