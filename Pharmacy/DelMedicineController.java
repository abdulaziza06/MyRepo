/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package z1;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
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

/**
 * FXML Controller class
 *
 * @author xxazo
 */
public class DelMedicineController implements Initializable {

    @FXML
    private Button DInventory;
    @FXML
    private Button DAddMedicine;
    @FXML
    private Button DDeleteMedicine;
    @FXML
    private Button DLogOut;
    @FXML
    private TextField IDD;
    @FXML
    private Button Delete;
   
    private Parent root;
    private Stage stage;
    private Scene scene;
    @FXML
    private Label dLabel;

    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    public void DInventory(ActionEvent event) throws IOException{
        
        root = FXMLLoader.load(getClass().getResource("main.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    
    }
    
    @FXML
    public void DAddMedicine(ActionEvent event) throws IOException{
        
        root = FXMLLoader.load(getClass().getResource("AddMedicine.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    
    }
    
    @FXML
    public void DLogOut(ActionEvent event) throws IOException{
        
        root = FXMLLoader.load(getClass().getResource("LogIn.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    
    }
    @FXML
    public void Delete(ActionEvent event) throws ClassNotFoundException, SQLException, IOException{
        
        
        Class.forName("com.mysql.cj.jdbc.Driver");
        
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacy", "root", "123456");
        
        String Q = "DELETE FROM med WHERE id = ?";
        
        PreparedStatement pst = con.prepareStatement (Q);
        
        pst.setString(1, IDD.getText());
        int c = pst.executeUpdate();
        
        if(c == 1){
            root = FXMLLoader.load(getClass().getResource("main.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }else{
            dLabel.setText("The ID does not exist");
        }
    }
}
