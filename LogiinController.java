/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject4;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author My Computer
 */
public class LogiinController implements Initializable {
    @FXML
    private Label label;
    private Label pesan;
   
    @FXML
    private TextField tfUsername;
   
    @FXML
    private PasswordField pfPassword;
    private Connection conn=null;
    
    @FXML
    private void btnLogin(ActionEvent event) throws IOException, SQLException{
//        System.out.println("clicked btn login");
        String usern = tfUsername.getText();
        String pass = pfPassword.getText();
            
            String url = "jdbc:sqlite:logiin.db";
            conn = DriverManager.getConnection(url);
            Statement p = conn.createStatement();
            System.out.println(usern);
            System.out.println(pass);
            String query = "select *from user WHERE username = '"+usern+"' AND password = '"+pass+"'";
            ResultSet q = p.executeQuery(query);
            
            if(q.next()){
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Home.fxml"));
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/style/Style.css");
            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        } else {
                if(usern.equals(" ") && pass.equals(" ")){
                    pesan.setText("username dan password harus diisi!");
                }
                else if(usern.equals("")){
                    pesan.setText("username kosong");
                }
        }

    }
    
    @FXML
    private void btnSignUp(ActionEvent event) throws IOException, SQLException{
//        System.out.println("clicked");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/SignUp.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));  
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
