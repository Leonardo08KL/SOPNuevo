package com.example.proyectonuevo.Tablas;

import com.example.proyectonuevo.HelloApplication;
import com.example.proyectonuevo.Registros.Conexion;
import com.example.proyectonuevo.Registros.Registro;
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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CubreBocasController implements Initializable {

    /**
     * @FXML private TextField txtTiempoLLegada;
     * @FXML private TextField txtTiempoAtencion;
     */

    @FXML
    private TableColumn<Registro2, Number> clmNo;
    @FXML
    private TableColumn<Registro2, Double> clmTLlegada;
    @FXML
    private TableColumn<Registro2, Double> clmTAtencion;
    @FXML
    private TableColumn<Registro2, String> clmSiNo;
    @FXML
    private TableColumn<Registro2, Double> clmTEntrada;
    @FXML
    private TableColumn<Registro2, Double> clmTSalida;
    @FXML
    private TableColumn<Registro2, Double> clmTEspera;
    @FXML
    private TableColumn<Registro2, Double> clmTTotal;
    @FXML
    private TextField txtTiempoLLegada;
    @FXML
    private TextField txtTiempoAtencion;
    @FXML
    private ComboBox CBOXSiNo;

    @FXML
    private TableView<Registro2> tblRegistrosCubreBocas;

    private Conexion conexion;
    private ObservableList<Registro2> lista;

    int No = 0;
    public String Desicion = "";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        CBOXSiNo.getItems().setAll("SI", "NO");

        CBOXSiNo.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> selected, String oldCubrebocas, String newCubrebocas) {
                if (oldCubrebocas != null) {
                    switch (oldCubrebocas) {
                        case "SI":
                            Desicion = "SI";
                            break;
                        case "NO":
                            Desicion = "NO";
                            break;
                    }
                }
                if (newCubrebocas != null) {
                    switch (newCubrebocas) {
                        case "SI":
                            Desicion = "SI";
                            break;
                        case "NO":
                            Desicion = "NO";
                            break;
                    }
                }
            }
        });
        refresh();
    }

    @FXML
    public void Insertar(ActionEvent event) throws IOException{
        No++;
        System.out.println(txtTiempoLLegada.toString()+" "+txtTiempoAtencion.toString());
        if(No < 6) {
            Registro2 a = new Registro2(No,
                    Desicion,
                    Double.parseDouble(txtTiempoLLegada.getText()),
                    Double.parseDouble(txtTiempoAtencion.getText()),
                    0.0,
                    0.0, 0.0, 0.0);
            conexion.establecerConexion();
            int resultado = a.guardarRegistro(conexion.getConnection());
            conexion.cerrarConexion();
        }
        else {
            Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
            mensaje.setTitle("NO MÁS");
            mensaje.setContentText("NO PUEDES AGREGAR MAS REGISTROS");
            mensaje.show();
        }
        refresh();
    }
    public void refresh(){
        conexion = new Conexion();
        conexion.establecerConexion();

        //Inicializar listas
        lista = FXCollections.observableArrayList();
        Registro2.llenarInformacion(conexion.getConnection(), lista);
        tblRegistrosCubreBocas.setItems(lista);

        //Enlazar columnas con atributos
        clmNo.setCellValueFactory(new PropertyValueFactory<Registro2, Number>("NO"));
        clmSiNo.setCellValueFactory(new PropertyValueFactory<Registro2, String>("cubrebocas"));
        clmTLlegada.setCellValueFactory(new PropertyValueFactory<Registro2, Double>("T_Llegada"));
        clmTAtencion.setCellValueFactory(new PropertyValueFactory<Registro2, Double>("T_Atencion"));
        clmTEntrada.setCellValueFactory(new PropertyValueFactory<Registro2,Double>("T_Entrada"));
        clmTSalida.setCellValueFactory(new PropertyValueFactory<Registro2,Double>("T_Salida"));
        clmTEspera.setCellValueFactory(new PropertyValueFactory<Registro2,Double>("T_Espera"));
        clmTTotal.setCellValueFactory(new PropertyValueFactory<Registro2,Double>("T_Total"));
        //gestionarEventos();
        conexion.cerrarConexion();
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

    @FXML
    public void Simulacion(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/proyectonuevo/CubrebocasAnimation.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Inicio");
        stage.setScene(scene);
        stage.show();
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }
}
