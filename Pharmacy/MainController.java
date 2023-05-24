/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package z1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import java.sql.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author xxazo
 */
public class MainController implements Initializable {

    @FXML
    private Button DelMedicine;
    @FXML
    private Button LogOut;
    @FXML
    private Button Inventory;
    @FXML
    private Button AddMedicine;
    @FXML
    public TextArea ViewMedicines1;
    @FXML
    private TextArea ViewMedicines2;
    @FXML
    private TextArea ViewMedicines3;
    @FXML
    private TextArea ViewMedicines4;
    @FXML
    private TextArea ViewMedicines5;
    @FXML
    private TextArea ViewMedicines6;
    
    private Parent root;
    private Stage stage;
    private Scene scene;
    
    StringBuffer dataID = new StringBuffer();
    StringBuffer dataName = new StringBuffer();
    StringBuffer dataWeight = new StringBuffer();
    StringBuffer dataPrice = new StringBuffer();
    StringBuffer dataQuantity = new StringBuffer();
    StringBuffer dataUsage = new StringBuffer();
    @FXML
    private TextField Search;
    @FXML
    private Button Searchb;
    @FXML
    private Label Labels;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacy", "root", "123456");
            String Q = "select * from med";
            PreparedStatement pst = con.prepareStatement (Q);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                dataID.append(rs.getString("ID")).append("\n\n");
                dataName.append(rs.getString("Name")).append("\n\n");
                dataWeight.append(rs.getString("Weight")).append("\n\n");
                dataPrice.append(rs.getString("Price")).append("\n\n");
                dataQuantity.append(rs.getString("Quantity")).append("\n\n");
                dataUsage.append(rs.getString("U")).append("\n\n");
            }
            
            ViewMedicines1.setText(dataID.toString());
            ViewMedicines2.setText(dataName.toString());
            ViewMedicines3.setText(dataWeight.toString());
            ViewMedicines4.setText(dataPrice.toString());
            ViewMedicines5.setText(dataQuantity.toString());
            ViewMedicines6.setText(dataUsage.toString());
            
            
        } catch (Exception ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void LogOut(ActionEvent event) throws IOException{
        
        root = FXMLLoader.load(getClass().getResource("LogIn.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    
    
    }
    
    @FXML
    public void AddMedicine(ActionEvent event) throws IOException{
        
        root = FXMLLoader.load(getClass().getResource("AddMedicine.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    
    }
    
    @FXML
    public void DelMedicine(ActionEvent event) throws IOException{
        
        root = FXMLLoader.load(getClass().getResource("DelMedicine.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    
    }
    
    @FXML
    public void Searchb(ActionEvent event) throws ClassNotFoundException, SQLException{
        
         Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacy", "root", "123456");
        String Q = "select * from med where ID=?";
        PreparedStatement pst = con.prepareStatement (Q);
        pst.setString(1,Search.getText());
        ResultSet rs = pst.executeQuery();
            
        if(rs.next()){
            String ID = rs.getString("ID");
            String Name = rs.getString("Name");
            String Weight = rs.getString("Weight");
            String Price = rs.getString("Price");
            String Quantity = rs.getString("Quantity");
            String Usage= rs.getString("U");
            
            ViewMedicines1.setText(ID);
            ViewMedicines2.setText(Name);
            ViewMedicines3.setText(Weight);
            ViewMedicines4.setText(Price);
            ViewMedicines5.setText(Quantity);
            ViewMedicines6.setText(Usage); 
        }else{
            Labels.setText("ID is incorrect");
            pst.setString(1,Search.getText());
        }     
    }
    
    @FXML
    public void Inventory(ActionEvent event) throws IOException{
        
        root = FXMLLoader.load(getClass().getResource("main.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    
    }
    
}
