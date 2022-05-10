package controller;

import database.SchemeDB;
import model.Alumno;

import java.sql.*;

public class ControllerBD {

    private Connection conn;
    // no comprueba tipos
    private Statement statement;
    // comprueba tipos de datos
    private PreparedStatement preparedStatement;


    private void getConnection(){
        String host = SchemeDB.URL_SERVER;
        String dtbs = SchemeDB.DB_NAME;
        String user = "root";
        String pass = "";

        // jdbc:mysql://127.0.0.1:3306/colegio?user=root&pass=
        String newConnectionURL = "jdbc:mysql://" + host + "/" + dtbs
                + "?" + "user=" + user + "&password=" + pass;

        try {
            // Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(newConnectionURL);
            System.out.println(conn.getCatalog());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void closeConnection(){
        try {
            if (conn!= null){
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void insertarAlumnoStatement() {
        String nombre = "Borja";
        String apellido = "Martin";
        int  edad = 38;
        // INSERT INTO alumnos (nombre, apellido, edad) VALUES ('BORJA','MARTIN',38)
        try {
            getConnection();
            statement = conn.createStatement();
            String query = "INSERT INTO"+ SchemeDB.TAB_ALU+" ("+SchemeDB.COL_NOMBRE+","+ SchemeDB.COL_APELLIDO+","+ SchemeDB.COL_EDAD+") " +
                    "VALUES ('"+nombre+"','"+apellido+"',"+edad+")";
            String queryFormat = String.format("INSERT INTO %s (%s, %s, %s) VALUES ('%s','%s',%d)",SchemeDB.TAB_ALU,
                    SchemeDB.COL_NOMBRE,SchemeDB.COL_APELLIDO,SchemeDB.COL_EDAD,
                    nombre,apellido,edad);
            int numeroRow = statement.executeUpdate(queryFormat);
            //System.out.println(numeroRow);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            closeConnection();
        }
    }
    public void insertarAlumnoStatement(Alumno alumno) {

        // INSERT INTO alumnos (nombre, apellido, edad) VALUES ('BORJA','MARTIN',38)
        try {
            getConnection();
            statement = conn.createStatement();

            String queryFormat = String.format("INSERT INTO %s (%s, %s, %s) VALUES ('%s','%s',%d)",SchemeDB.TAB_ALU,
                    SchemeDB.COL_NOMBRE,SchemeDB.COL_APELLIDO,SchemeDB.COL_EDAD,
                    alumno.getNombre(),alumno.getApellido(),alumno.getEdad());
            //int numeroRow = statement.executeUpdate(queryFormat);
            if (statement.executeUpdate(queryFormat)>0){
                System.out.println("Alumno insertado correctamente");
            }
            //System.out.println(numeroRow);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            closeConnection();
        }
    }
    // Desde la entrada, pedir al usuario datos para introducir alumnos (recursivo)
    // el alumno se mete en base de datos
    // mediante un modelo
    public void insertarAlumnoPrepare(){
        String nombre = "Borja";
        String apellido = "Martin";
        int  edad = 38;
        String query  =  "INSERT INTO alumnos (nombre, apellido, edad) VALUES (?,?,?)";
        try {
            // no ejecuta la query --> la deja preparada
            getConnection();
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1,nombre);
            preparedStatement.setString(2,apellido);
            preparedStatement.setInt(3,edad);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            closeConnection();
        }

    }
    public Connection getConn() {
        return conn;
    }
    public void setConn(Connection conn) {
        this.conn = conn;
    }

    // modificar la edad de un usuario. Para ello el sistema pedira el nombre del usuario
    // y la nueva edad del mismo. Una vez realizada la modificacion el sistema
    // avisará del numero de usuarios que ha cambiado SE HAN ACTUALIZADO X USUARIOS

    // borrar los usuarios. Para ello el sistema pedira una edad y borrá todos aquellos
    // usuarios que tengan una edad inferior a la pedida por el sistema
    // el sistema avisará del numero de usuarios borrados
}