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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.sql.*;

/**
 * FXML Controller class
 *
 * @author xxazo
 */
public class AddMedicineController implements Initializable {

    @FXML
    private Button AInventory;
    @FXML
    private Button ADeleteMedicine;
    @FXML
    private Button AAddMedicine;
    @FXML
    private Button ALogOut;
    @FXML
    private TextField IDM;
    @FXML
    private TextField nameM;
    @FXML
    private TextField weightM;
    @FXML
    private TextField priceM;
     @FXML
    private TextField quantityM;
    @FXML
    private TextField usageM;
    @FXML
    private Button ADDBut;
    @FXML
    private Label labelID;
    
    
    private Parent root;
    private Stage stage;
    private Scene scene;
  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    public void AInventory(ActionEvent event) throws IOException{
        
        root = FXMLLoader.load(getClass().getResource("main.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    
    }
    
    @FXML
    public void ALogOut(ActionEvent event) throws IOException{
        
        root = FXMLLoader.load(getClass().getResource("LogIn.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    
    }
    
    @FXML
    public void ADeleteMedicine(ActionEvent event) throws IOException{
        
        root = FXMLLoader.load(getClass().getResource("DelMedicine.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    
    }
    
    @FXML
    public void AddMedicine(ActionEvent event) throws ClassNotFoundException, SQLException, IOException{
      
        Class.forName("com.mysql.cj.jdbc.Driver");
        
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacy", "root", "123456");
        
        String Q = "INSERT INTO med (ID,Name,Weight,Price,Quantity,U) VALUES (?,?,?,?,?,?)";
        
        PreparedStatement pst = con.prepareStatement (Q);
        
        pst.setString(1,IDM.getText());
        pst.setString(2,nameM.getText());
        pst.setString(3,weightM.getText());
        pst.setString(4,priceM.getText());
        pst.setString(5, quantityM.getText());
        pst.setString(6, usageM.getText());
        int c = pst.executeUpdate();
        
        if(c == 1){
            root = FXMLLoader.load(getClass().getResource("main.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }else{
            labelID.setText("The ID is already used");
        }     
    }
}