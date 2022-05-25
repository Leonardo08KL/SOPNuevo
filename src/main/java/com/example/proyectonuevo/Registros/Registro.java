package com.example.proyectonuevo.Registros;

import javafx.collections.ObservableList;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Registro implements Initializable {

    private int NO;
    private double T_Llegada;
    private double T_Atencion;
    private double T_Entrada;
    private double T_Salida;
    private double T_Espera;
    private double T_Total;

    public Registro(int NO, double t_Llegada, double t_Atencion, double t_Entrada, double t_Salida, double t_Espera, double t_Total) {
        this.NO = NO;
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

    public double getT_Llegada() {
        return T_Llegada;
    }

    public void setT_Llegada(double t_Llegada) {
        this.T_Llegada = t_Llegada;
    }

    public double getT_Atencion() {
        return T_Atencion;
    }

    public void setT_Atencion(double t_Atencion) {
        this.T_Atencion = t_Atencion;
    }

    public double getT_Entrada() {
        return T_Entrada;
    }

    public void setT_Entrada(double t_Entrada) {
        this.T_Entrada = t_Entrada;
    }

    public double getT_Salida() {
        return T_Salida;
    }

    public void setT_Salida(double t_Salida) {
        this.T_Salida = t_Salida;
    }

    public double getT_Espera() {
        return T_Espera;
    }

    public void setT_Espera(double t_Espera) {
        this.T_Espera = t_Espera;
    }

    public double getT_Total() {
        return T_Total;
    }

    public void setT_Total(double t_Total) {
        this.T_Total = t_Total;
    }

    public int guardarRegistro(Connection connection) {
        try {
            PreparedStatement ps = connection.prepareStatement("insert into FIFO(`NO`, `T_Llegada`, `T_Atencion`, `T_Entrada`, `T_Salida`, `T_Espera`, `T_Total`) VALUES (?,?,?,?,?,?,?)");
            ps.setInt(1, NO);
            ps.setDouble(2, T_Llegada);
            ps.setDouble(3, T_Atencion);
            ps.setDouble(4, T_Entrada);
            ps.setDouble(5, T_Salida);
            ps.setDouble(6, T_Espera);
            ps.setDouble(7, T_Total);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static void llenarInformacion(Connection connection,
                                                ObservableList<Registro> lista) {
        try {
            Statement instruccion = connection.createStatement();
            ResultSet resultado = instruccion.executeQuery(
                    "SELECT * from fifo"
            );
            while (resultado.next()) {
                lista.add(
                        new Registro(
                                resultado.getInt("NO"),
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

/*    public void ConsultarAlumnos(Connection connection,
                                     ObservableList<Registro> lista){
        try {
            Statement instruccion = connection.createStatement();
            ResultSet resultado = instruccion.executeQuery(
                    "SELECT * from fifo"
            );
            while (resultado.next()) {
                lista.add(
                        new Registro(
                                resultado.getInt("NO"),
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
    }*/

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    public int actualizarRegistro(Connection connection) {
        try {
            PreparedStatement instruccion =
                    connection.prepareStatement(
                            "update fifo set T_Llegada = ?,T_Atencion = ?, T_Entrada = ?, T_Salida = ?, T_Espera = ?, T_Total=? where NO = ?"
                    );
            instruccion.setDouble(1, T_Llegada);
            instruccion.setDouble(2, T_Atencion);
            instruccion.setDouble(3, T_Entrada);
            instruccion.setDouble(4, T_Salida);
            instruccion.setDouble(5, T_Espera);
            instruccion.setDouble(6, T_Total);
            instruccion.setInt(7, NO);
            return instruccion.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void eliminar(Connection connection) {

    }
}
