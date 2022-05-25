package com.example.proyectonuevo.Registros;

import javafx.collections.ObservableList;
import javafx.fxml.Initializable;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class Registro2 implements Initializable {

    private int NO;
    private Double T_Llegada;
    private String cubrebocas;
    private Double T_Atencion;
    private Double T_Entrada;
    private Double T_Salida;
    private Double T_Espera;
    private Double T_Total;

    public Registro2(int NO,  String cubrebocas, Double t_Llegada, Double t_Atencion, Double t_Entrada, Double t_Salida, Double t_Espera, Double t_Total) {
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

    public Double getT_Llegada() {
        return T_Llegada;
    }

    public void setT_Llegada(Double t_Llegada) {
        this.T_Llegada = t_Llegada;
    }

    public Double getT_Atencion() {
        return T_Atencion;
    }

    public void setT_Atencion(Double t_Atencion) {
        this.T_Atencion = t_Atencion;
    }

    public Double getT_Entrada() {
        return T_Entrada;
    }

    public void setT_Entrada(Double t_Entrada) {
        this.T_Entrada = t_Entrada;
    }

    public Double getT_Salida() {
        return T_Salida;
    }

    public void setT_Salida(Double t_Salida) {
        this.T_Salida = t_Salida;
    }

    public Double getT_Espera() {
        return T_Espera;
    }

    public void setT_Espera(Double t_Espera) {
        this.T_Espera = t_Espera;
    }

    public Double getT_Total() {
        return T_Total;
    }

    public void setT_Total(Double t_Total) {
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
            ps.setDouble(3, T_Llegada);
            ps.setDouble(4, T_Atencion);
            ps.setDouble(5, T_Entrada);
            ps.setDouble(6, T_Salida);
            ps.setDouble(7, T_Espera);
            ps.setDouble(8, T_Total);
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
                    "SELECT * from CubreBocas order by cubrebocas = 'NO' "
            );
            while (resultado.next()) {
                lista.add(
                        new Registro2(
                                resultado.getInt("NO"),
                                resultado.getString("cubrebocas"),
                                resultado.getDouble("T_Llegada"),
                                resultado.getDouble("T_Atencion"),
                                resultado.getDouble("T_Entrada"),
                                resultado.getDouble("T_Salida"),
                                resultado.getDouble("T_Espera"),
                                resultado.getDouble("T_Total")
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

    public int actualizarRegistro(Connection connection) {
        try {
            PreparedStatement instruccion =
                    connection.prepareStatement(
                            "update cubrebocas set cubrebocas = ?, T_Llegada = ?,T_Atencion = ?, T_Entrada = ?, T_Salida = ?, T_Espera = ?, T_Total=? where NO = ?"
                    );
            instruccion.setString(1,cubrebocas);
            instruccion.setDouble(2, T_Llegada);
            instruccion.setDouble(3, T_Atencion);
            instruccion.setDouble(4, T_Entrada);
            instruccion.setDouble(5, T_Salida);
            instruccion.setDouble(6, T_Espera);
            instruccion.setDouble(7, T_Total);
            instruccion.setInt(8, NO);
            return instruccion.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
