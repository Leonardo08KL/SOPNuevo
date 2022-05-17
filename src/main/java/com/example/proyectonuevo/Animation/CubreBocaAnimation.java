package com.example.proyectonuevo.Animation;

import com.example.proyectonuevo.HelloApplication;
import com.example.proyectonuevo.Registros.Conexion;
import com.example.proyectonuevo.Registros.Registro2;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CubreBocaAnimation implements Initializable {


    /**
     * @FXML private TextField txtTiempoLLegada;
     * @FXML private TextField txtTiempoAtencion;
     */

    @FXML
    private TableColumn<Registro2, Number> clmNo;
    @FXML
    private TableColumn<Registro2, String> clmTLlegada;
    @FXML
    private TableColumn<Registro2, String> clmTAtencion;
    @FXML
    private TableColumn<Registro2, String> clmSiNo;
    @FXML
    private TableColumn<Registro2, String> clmTEntrada;
    @FXML
    private TableColumn<Registro2, String> clmTSalida;
    @FXML
    private TableColumn<Registro2, String> clmTEspera;
    @FXML
    private TableColumn<Registro2, String> clmTTotal;
    @FXML
    private TableView<Registro2> tblRegistrosCubreBocas;

    private Conexion conexion;
    private ObservableList<Registro2> lista;

    int No = 0;
    String Desicion = "";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        conexion = new Conexion();
        conexion.establecerConexion();

        //Inicializar listas
        lista = FXCollections.observableArrayList();

        //Llenar listas
        Registro2.llenarInformacion(conexion.getConnection(), lista);
        tblRegistrosCubreBocas.setItems(lista);

        //Enlazar columnas con atributos
        clmNo.setCellValueFactory(new PropertyValueFactory<Registro2, Number>("NO"));
        clmSiNo.setCellValueFactory(new PropertyValueFactory<Registro2, String>("cubrebocas"));
        clmTLlegada.setCellValueFactory(new PropertyValueFactory<Registro2, String>("T_Llegada"));
        clmTAtencion.setCellValueFactory(new PropertyValueFactory<Registro2, String>("T_Atencion"));
        clmTEntrada.setCellValueFactory(new PropertyValueFactory<Registro2,String>("T_Entrada"));
        clmTSalida.setCellValueFactory(new PropertyValueFactory<Registro2,String>("T_Salida"));
        clmTEspera.setCellValueFactory(new PropertyValueFactory<Registro2,String>("T_Espera"));
        clmTTotal.setCellValueFactory(new PropertyValueFactory<Registro2,String>("T_Total"));
        //gestionarEventos();
        conexion.cerrarConexion();
    }


    @FXML
    private void regresar(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/proyectonuevo/Cubrebocas.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Inicio");
        stage.setScene(scene);
        stage.show();
        ( (Node) (event.getSource() ) ).getScene().getWindow().hide();
    }
}
