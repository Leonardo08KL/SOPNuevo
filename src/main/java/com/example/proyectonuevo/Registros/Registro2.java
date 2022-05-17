package com.example.proyectonuevo.Registros;

import javafx.collections.ObservableList;
import javafx.fxml.Initializable;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class Registro2 implements Initializable {

    private int NO;
    private String T_Llegada;
    private String cubrebocas;
    private String T_Atencion;
    private String T_Entrada;
    private String T_Salida;
    private String T_Espera;
    private String T_Total;

    public Registro2(int NO,  String cubrebocas, String t_Llegada, String t_Atencion, String t_Entrada, String t_Salida, String t_Espera, String t_Total) {
        this.NO = NO;
        this.cubrebocas = cubrebocas;
        this.T_Llegada = t_Llegada;
        this.T_Atencion = t_Atencion;
        this.T_Entrada = t_Entrada;
        this.T_Salida = t_Salida;
        this.T_Espera = t_Espera;
        this.T_Total = t_Total;
    }

    public int getNO() {
        return NO;
    }

    public void setNO(int NO) {
        this.NO = NO;
    }

    public String getCubrebocas() {
        return cubrebocas;
    }

    public void setCubrebocas(String cubrebocas) {
        this.cubrebocas = cubrebocas;
    }

    public String getT_Llegada() {
        return T_Llegada;
    }

    public void setT_Llegada(String t_Llegada) {
        this.T_Llegada = t_Llegada;
    }

    public String getT_Atencion() {
        return T_Atencion;
    }

    public void setT_Atencion(String t_Atencion) {
        this.T_Atencion = t_Atencion;
    }

    public String getT_Entrada() {
        return T_Entrada;
    }

    public void setT_Entrada(String t_Entrada) {
        this.T_Entrada = t_Entrada;
    }

    public String getT_Salida() {
        return T_Salida;
    }

    public void setT_Salida(String t_Salida) {
        this.T_Salida = t_Salida;
    }

    public String getT_Espera() {
        return T_Espera;
    }

    public void setT_Espera(String t_Espera) {
        this.T_Espera = t_Espera;
    }

    public String getT_Total() {
        return T_Total;
    }

    public void setT_Total(String t_Total) {
        this.T_Total = t_Total;
    }

    public int guardarRegistro(Connection connection) {
        try {
            //Evitar inyeccion SQL.
            //PreparedStatement instruccion2 = connection.prepareStatement("SET FOREIGN_KEY_CHECKS = OFF");
            //instruccion2.executeUpdate();
            //PreparedStatement ps = connection.prepareStatement("insert into employees (`employeeNumber`,`lastName`,`firstName`,`extension`,`email`,`officeCode`,`reportsTo`,`jobTitle`) values ( ?, ?, ?, ?, ?, ?, ?, ?)");
            PreparedStatement ps = connection.prepareStatement("insert into CubreBocas(`NO`, `cubrebocas`, `T_Llegada`, `T_Atencion`, `T_Entrada`, `T_Salida`, `T_Espera`, `T_Total`) VALUES (?,?,?,?,?,?,?,?)");
            ps.setInt(1, NO);
            ps.setString(2, cubrebocas);
            ps.setString(3, T_Llegada);
            ps.setString(4, T_Atencion);
            ps.setString(5, T_Entrada);
            ps.setString(6, T_Salida);
            ps.setString(7, T_Espera);
            ps.setString(8, T_Total);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static void llenarInformacion(Connection connection,
                                         ObservableList<Registro2> lista) {
        try {
            Statement instruccion = connection.createStatement();
            ResultSet resultado = instruccion.executeQuery(
                    "SELECT * from CubreBocas"
            );
            while (resultado.next()) {
                lista.add(
                        new Registro2(
                                resultado.getInt("NO"),
                                resultado.getString("cubrebocas"),
                                resultado.getString("T_Llegada"),
                                resultado.getString("T_Atencion"),
                                resultado.getString("T_Entrada"),
                                resultado.getString("T_Salida"),
                                resultado.getString("T_Espera"),
                                resultado.getString("T_Total")
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
