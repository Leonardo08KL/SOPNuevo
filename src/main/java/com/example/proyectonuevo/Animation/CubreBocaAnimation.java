package com.example.proyectonuevo.Animation;

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
    private TableView<Registro2> tblRegistrosCubreBocas;

    private Conexion conexion;
    private ObservableList<Registro2> lista;

    Double Llega;
    Double LlegaAnterior;
    Double Atencion;
    Double AtencionAnterior;
    double sale = 0;
    int No;
    String Desicion;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        refresh();
    }

    public void refresh(){
        conexion = new Conexion();
        conexion.establecerConexion();
        lista = FXCollections.observableArrayList();
        Registro2.llenarInformacion(conexion.getConnection(), lista);
        tblRegistrosCubreBocas.setItems(lista);

        clmNo.setCellValueFactory(new PropertyValueFactory<Registro2, Number>("NO"));
        clmSiNo.setCellValueFactory(new PropertyValueFactory<Registro2, String>("cubrebocas"));
        clmTLlegada.setCellValueFactory(new PropertyValueFactory<Registro2, Double>("T_Llegada"));
        clmTAtencion.setCellValueFactory(new PropertyValueFactory<Registro2, Double>("T_Atencion"));
        clmTEntrada.setCellValueFactory(new PropertyValueFactory<Registro2,Double>("T_Entrada"));
        clmTSalida.setCellValueFactory(new PropertyValueFactory<Registro2,Double>("T_Salida"));
        clmTEspera.setCellValueFactory(new PropertyValueFactory<Registro2,Double>("T_Espera"));
        clmTTotal.setCellValueFactory(new PropertyValueFactory<Registro2,Double>("T_Total"));
        gestionarEventos();
        conexion.cerrarConexion();
    }

    public void gestionarEventos() {
        tblRegistrosCubreBocas.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Registro2>() {
                    @Override
                    public void changed(ObservableValue<? extends Registro2> arg0,
                                        Registro2 valorAnterior, Registro2 valorSeleccionado) {
                        if (valorSeleccionado != null) {
                            No = (Integer.valueOf(valorSeleccionado.getNO()));
                            Desicion = (String.valueOf(valorSeleccionado.getCubrebocas())) ;
                            Llega = (Double.valueOf(valorSeleccionado.getT_Llegada()));
                            Atencion = (Double.valueOf(valorSeleccionado.getT_Atencion()));


                            System.out.println(Llega + "\n" + Atencion);
                        }
                    }
                }
        );
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

    @FXML
    private void Insertar(ActionEvent event) throws IOException {

        System.out.println(Llega + "+++\n" + Atencion+"\n"+Desicion);

        System.out.println();

        if (sale == 0) {
            //No++;
            System.out.println("Primero "+No+"\n"+Llega+"\n"+Atencion);
            sale = Llega + (Atencion/100);
            Registro2 a = new Registro2(
                    No,
                    Desicion,
                    Llega,
                    Atencion,
                    Llega,
                    sale,
                    0.0,
                    (Llega + (Atencion/100)- Llega));

            conexion.establecerConexion();
            int resultado = a.actualizarRegistro(conexion.getConnection());
            conexion.cerrarConexion();
        }else{
            if(sale < Llega){
                System.out.println("T Sale menor"+sale);
                sale = Llega + (Atencion/100);
                Registro2 a = new Registro2(
                        No,
                        Desicion,
                        Llega,
                        Atencion,
                        Llega,
                        sale,
                        0.0,
                        (Llega + (Atencion/100)- Llega));

                conexion.establecerConexion();
                int resultado = a.actualizarRegistro(conexion.getConnection());
                conexion.cerrarConexion();
            }else{
                if(sale > Llega){
                    System.out.println("T Sale mayor"+sale);
                    //sale = Llega + (Atencion/100);
                    Registro2 a = new Registro2(
                            No,
                            Desicion,
                            Llega,
                            Atencion,
                            sale,
                            sale+(Atencion/100),
                            (sale)-Llega,
                            ((sale+(Atencion/100))- Llega)*100);
                    sale = sale+(Atencion/100);
                    conexion.establecerConexion();
                    int resultado = a.actualizarRegistro(conexion.getConnection());
                    conexion.cerrarConexion();
                }}
        }
        refresh();
    }
}
