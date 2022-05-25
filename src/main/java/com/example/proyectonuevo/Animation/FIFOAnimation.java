package com.example.proyectonuevo.Animation;

import com.example.proyectonuevo.HelloApplication;
import com.example.proyectonuevo.Registros.Conexion;
import com.example.proyectonuevo.Registros.Registro;
import javafx.animation.PathTransition;
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
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;

public class FIFOAnimation implements Initializable {
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
    @FXML
    private Circle Lugar1,Lugar2,US1,US2,US3,US4,US5;
    private Conexion conexion;
    private ObservableList<Registro> lista;

    Double TiempoInicial = 0.0;
    Double Llega;
    Double LlegaAnterior;
    Double Atencion;
    Double AtencionAnterior;
    double sale = 0;
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
        gestionarEventos();
        conexion.cerrarConexion();
    }

    public void gestionarEventos() {
        tblRegistrosFIFO.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Registro>() {
                    @Override
                    public void changed(ObservableValue<? extends Registro> arg0,
                                        Registro valorAnterior, Registro valorSeleccionado) {
                        if (valorSeleccionado != null) {
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
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/proyectonuevo/FIFO.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Inicio");
        stage.setScene(scene);
        stage.show();
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    private void Insertar(ActionEvent event) throws IOException {

        System.out.println(Llega + "+++\n" + Atencion);

        System.out.println();

        if (sale == 0.0) {
            No++;
            sale = Llega + (Atencion/100);
            Registro a = new Registro(
                    No,
                    Llega,
                    Atencion,
                    Llega,
                    sale,
                    0,
                    (Llega + (Atencion/100)- Llega));

            conexion.establecerConexion();
            int resultado = a.actualizarRegistro(conexion.getConnection());
            conexion.cerrarConexion();
            PathTransition pathTransition = new PathTransition(Duration.seconds(1), new Line(0, 0, 150, 0), US1);
            pathTransition.setCycleCount(1);
            pathTransition.play();
        }else{
            if(sale < Llega){
                No++;
                System.out.println("T Sale menor"+sale);
                sale = Llega + (Atencion/100);
                Registro a = new Registro(
                        No,
                        Llega,
                        Atencion,
                        Llega,
                        sale,
                        0,
                        (Llega + (Atencion/100)- Llega));

                conexion.establecerConexion();
                int resultado = a.actualizarRegistro(conexion.getConnection());
                conexion.cerrarConexion();
                US1.setFill(Color.TRANSPARENT);
                PathTransition pathTransition = new PathTransition(Duration.seconds(1), new Line(0, 0, 200, 0), Lugar1);
                pathTransition.setCycleCount(1);
                pathTransition.play();
                PathTransition pathTransition2 = new PathTransition(Duration.seconds(1), new Line(0, 0, 200, 0), US2);
                pathTransition2.setCycleCount(1);
                pathTransition2.play();
            }else{
                if(sale > Llega){
                No++;
                    System.out.println("T Sale mayor"+sale);
                //sale = Llega + (Atencion/100);
                Registro a = new Registro(
                        No,
                        Llega,
                        Atencion,
                        sale,
                        sale+(Atencion/100),
                        (sale)-Llega,
                        ((sale+(Atencion/100))- Llega));
                sale = sale+(Atencion/100);
                conexion.establecerConexion();
                int resultado = a.actualizarRegistro(conexion.getConnection());
                conexion.cerrarConexion();
            }}
        }
        refresh();
    }

}
