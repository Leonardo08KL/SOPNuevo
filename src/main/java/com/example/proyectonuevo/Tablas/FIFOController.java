package com.example.proyectonuevo.Tablas;

import com.example.proyectonuevo.HelloApplication;
import com.example.proyectonuevo.Registros.Conexion;
import com.example.proyectonuevo.Registros.Registro;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.Random;
import java.util.ResourceBundle;

public class FIFOController implements Initializable {
    /**
     * @FXML private TextField txtTiempoLLegada;
     * @FXML private TextField txtTiempoAtencion;
     */

    @FXML
    private TableColumn<Registro, Number> clmNo;
    @FXML
    private TableColumn<Registro, Double> clmTLlegada;
    @FXML
    private TableColumn<Registro, Double> clmTAtencion;
    @FXML
    private TableColumn<Registro, Double> clmTEntrada;
    @FXML
    private TableColumn<Registro, Double> clmTSalida;
    @FXML
    private TableColumn<Registro, Double> clmTEspera;
    @FXML
    private TableColumn<Registro, Double> clmTTotal;

    @FXML
    private TextField txtTiempoLLegada;
    @FXML
    private TextField txtTiempoAtencion;

    @FXML
    private TableView<Registro> tblRegistrosFIFO;

    private Conexion conexion;
    private ObservableList<Registro> lista;

    int No = 0;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        refresh();
    }

    public void refresh(){
        conexion = new Conexion();
        conexion.establecerConexion();
        lista = FXCollections.observableArrayList();
        Registro.llenarInformacion(conexion.getConnection(), lista);
        tblRegistrosFIFO.setItems(lista);

        clmNo.setCellValueFactory(new PropertyValueFactory<Registro, Number>("NO"));
        clmTLlegada.setCellValueFactory(new PropertyValueFactory<Registro, Double>("T_Llegada"));
        clmTAtencion.setCellValueFactory(new PropertyValueFactory<Registro, Double>("T_Atencion"));
        clmTEntrada.setCellValueFactory(new PropertyValueFactory<Registro,Double>("T_Entrada"));
        clmTSalida.setCellValueFactory(new PropertyValueFactory<Registro,Double>("T_Salida"));
        clmTEspera.setCellValueFactory(new PropertyValueFactory<Registro,Double>("T_Espera"));
        clmTTotal.setCellValueFactory(new PropertyValueFactory<Registro,Double>("T_Total"));
        //gestionarEventos();
        conexion.cerrarConexion();
    }


    @FXML
    public void Insertar(ActionEvent event) throws IOException{

        No++;
        System.out.println(Double.parseDouble(txtTiempoLLegada.getText())+"---"+txtTiempoAtencion.getText());

        if(No < 6){
            Registro a = new Registro(No,
                    Double.parseDouble(txtTiempoLLegada.getText()),
                    Double.parseDouble(txtTiempoAtencion.getText()),
                    0,
                    0, 0, 0);
            conexion.establecerConexion();
            int resultado = a.guardarRegistro(conexion.getConnection());
            conexion.cerrarConexion();
        }else{
            Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
            mensaje.setTitle("NO MÁS");
            mensaje.setContentText("NO PUEDES AGREGAR MAS REGISTROS");
            mensaje.show();
        }
        refresh();
    }

    @FXML
    public void Simulacion(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/proyectonuevo/FIFOAnimation.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Inicio");
        stage.setScene(scene);
        stage.show();
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }
    @FXML
    void regresar(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/proyectonuevo/PantallaPrincipal.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Inicio");
        stage.setScene(scene);
        stage.show();
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    private void Eliminar() {
        Registro a = new Registro(0,
                0,
                0,
                0,
                0, 0, 0);
        conexion.establecerConexion();
        int resultado = a.guardarRegistro(conexion.getConnection());
        conexion.cerrarConexion();
    }
}
