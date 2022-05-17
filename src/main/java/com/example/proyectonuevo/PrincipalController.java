package com.example.proyectonuevo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PrincipalController implements Initializable {
    @FXML
    public void FiFo(ActionEvent event) throws IOException {
        Parent Root = FXMLLoader.load(getClass().getResource("/com/example/proyectonuevo/FIFO.fxml"));
        Stage Stage = new Stage();
        Stage.setTitle("Another Window Triggered by Clicking");
        Stage.setScene(new Scene(Root, 800, 600));
        Stage.show();
        ( (Node) (event.getSource() ) ).getScene().getWindow().hide();
    }
    @FXML
    void Cubrebocas(ActionEvent event) throws IOException {
        Parent Root = FXMLLoader.load(getClass().getResource("/com/example/proyectonuevo/Cubrebocas.fxml"));
        Stage Stage = new Stage();
        Stage.setTitle("Another Window Triggered by Clicking");
        Stage.setScene(new Scene(Root, 800, 600));
        Stage.show();
        ( (Node) (event.getSource() ) ).getScene().getWindow().hide();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}