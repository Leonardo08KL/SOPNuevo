package com.example.proyectonuevo.Animation;

import com.example.proyectonuevo.HelloApplication;
import com.example.proyectonuevo.Registros.Conexion;
import com.example.proyectonuevo.Registros.Registro;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FIFOAnimation implements Initializable {
    @FXML
    private TableColumn<Registro, Number> clmNo;
    @FXML
    private TableColumn<Registro, String> clmTLlegada;
    @FXML
    private TableColumn<Registro, String> clmTAtencion;
    @FXML
    private TableColumn<Registro, String> clmTEntrada;
    @FXML
    private TableColumn<Registro, String> clmTSalida;
    @FXML
    private TableColumn<Registro, String> clmTEspera;
    @FXML
    private TableColumn<Registro, String> clmTTotal;

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
        conexion = new Conexion();
        conexion.establecerConexion();

        //Inicializar listas
        lista = FXCollections.observableArrayList();

        //Llenar listas
        Registro.llenarInformacion(conexion.getConnection(), lista);
        tblRegistrosFIFO.setItems(lista);

        //Enlazar columnas con atributos
        clmNo.setCellValueFactory(new PropertyValueFactory<Registro, Number>("NO"));
        clmTLlegada.setCellValueFactory(new PropertyValueFactory<Registro, String>("T_Llegada"));
        clmTAtencion.setCellValueFactory(new PropertyValueFactory<Registro, String>("T_Atencion"));
        clmTEntrada.setCellValueFactory(new PropertyValueFactory<Registro,String>("T_Entrada"));
        clmTSalida.setCellValueFactory(new PropertyValueFactory<Registro,String>("T_Salida"));
        clmTEspera.setCellValueFactory(new PropertyValueFactory<Registro,String>("T_Espera"));
        clmTTotal.setCellValueFactory(new PropertyValueFactory<Registro,String>("T_Total"));
        //gestionarEventos();
        conexion.cerrarConexion();
    }

    @FXML
    private void regresar(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/proyectonuevo/FIFO.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Inicio");
        stage.setScene(scene);
        stage.show();
        ( (Node) (event.getSource() ) ).getScene().getWindow().hide();
    }
}
