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
    public void Insertar(ActionEvent event) throws IOException{

        No++;
        System.out.println(txtTiempoLLegada.toString()+" "+txtTiempoAtencion.toString());
        //Crear una nueva instancia del tipo Alumno
        Registro a = new Registro(No,
                txtTiempoLLegada.getText(),
                txtTiempoAtencion.getText(),
                " ",
                " ", " ", " ");
        //Llamar al metodo guardarRegistro de la clase Alumno
        conexion.establecerConexion();
        int resultado = a.guardarRegistro(conexion.getConnection());
        conexion.cerrarConexion();
/*
        if (resultado == 1) {
            lista.add(a);
            //JDK 8u>40
            Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
            mensaje.setTitle("Registro agregado");
            mensaje.setContentText("El registro ha sido agregado exitosamente");
            mensaje.setHeaderText("Resultado:");
            mensaje.show();
        } else {
            Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
            mensaje.setTitle("No se Registro");
            mensaje.setContentText("El registro NO ha sido agregado exitosamente");
            mensaje.setHeaderText("Resultado:");
            mensaje.show();
        }*/
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
}
