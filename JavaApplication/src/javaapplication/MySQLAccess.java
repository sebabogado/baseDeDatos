package javaapplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javaapplication.panelDatos;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MySQLAccess {

    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public void readDataBase() throws Exception {
        try {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.jdbc.Driver");
            // Setup the connection with the DB
            connect = DriverManager
                    .getConnection("jdbc:mysql://localhost/clientes?"
                            + "user=root");

            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            // Result set get the result of the SQL query
            resultSet = statement
                    .executeQuery("select * from clientes.clientes");

            // PreparedStatements can use variables and are more efficient
            preparedStatement = connect
                    .prepareStatement("SELECT usuario,nombre,apellido,mail,sumario,comentarios,fecha from clientes.clientes");
            resultSet = preparedStatement.executeQuery();
            writeResultSet(resultSet);

            // Remove again the insert comment
            /* preparedStatement = connect
            .prepareStatement("delete from clientes.clientes where user= ? ; ");
            preparedStatement.setString(1, "Test");
            preparedStatement.executeUpdate();
             */
        } catch (Exception e) {
            throw e;
        }
        /*finally {
            close();
        }*/

    }

    public void mostrar(String usuario,String nombre,String apellido, String mail, String sumario, String comentarios) throws SQLException {
        try {
            preparedStatement = connect
                    .prepareStatement("select * from clientes.clientes");
            LocalDate localDate = LocalDate.now();
            Date date = java.sql.Date.valueOf(localDate);
            System.out.println(date);
            //preparedStatement.setString(, );
            preparedStatement.setString(1, usuario);
            preparedStatement.setString(2, nombre);
            preparedStatement.setString(3, apellido);
            preparedStatement.setString(4, mail);
            preparedStatement.setString(5, sumario);
            preparedStatement.setString(6, comentarios);
            preparedStatement.setDate(7, (java.sql.Date) date);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void mostrarPorConsola() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/clientes?"
                + "user=root");

        Statement st = con.createStatement();
        String sql = ("select * from clientes.clientes");
        ResultSet rs = st.executeQuery(sql);
        if (rs.next()) {
            int id = rs.getInt("id");
            String str1 = rs.getString("usuario");
            String nombre = rs.getString("nombre");
            String apellido = rs.getString("apellido");

            System.out.println(id+ " " + str1 + " " + nombre + " " + apellido);

        }

        con.close();
    }

    public void setearValorEnTextield(JTextField toSet, String name) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/clientes?"
                + "user=root");

        Statement st = con.createStatement();
        String sql = ("select * from clientes.clientes");
        ResultSet rs = st.executeQuery(sql);
        if (rs.next()) {
            int id = rs.getInt("id");
            String usuario = rs.getString("usuario");
            String nombre = rs.getString("nombre");
            String apellido = rs.getString("apellido");

            toSet.setText(id+ " " +usuario+ " "+nombre+" "+ apellido);
        }

        con.close();
    }
    
    public void setearValorEnArea(JTextArea toSet) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/clientes?"
                + "user=root");

        Statement st = con.createStatement();
        String sql = ("select * from clientes.clientes");
        ResultSet rs = st.executeQuery(sql);
        if (rs.next()) {
            int id = rs.getInt("id");
            String usuario = rs.getString("usuario");
            String nombre = rs.getString("nombre");
            String apellido = rs.getString("apellido");

            toSet.setText(id+ " " +usuario+ " "+nombre+" "+ apellido);
        }

        con.close();
    }
    
    
    
    
    

    public void guardar(String usuario,String nombre, String apellido, String mail, String sumario, String comentarios) throws SQLException {
        try {
            preparedStatement = connect
                    .prepareStatement("insert into clientes.clientes values (default, ?, ?, ?, ?, ?, ?, ?)");

            LocalDate localDate = LocalDate.now();
            Date date = java.sql.Date.valueOf(localDate);
            System.out.println(date);
            preparedStatement.setString(1, usuario);
            preparedStatement.setString(2, nombre);
            preparedStatement.setString(3, apellido);
            preparedStatement.setString(4, mail);
            preparedStatement.setString(5, sumario);
            preparedStatement.setString(6, comentarios);
             preparedStatement.setDate(7, (java.sql.Date) date);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void writeMetaData(ResultSet resultSet) throws SQLException {
        //  Now get some metadata from the database
        // Result set get the result of the SQL query

        System.out.println("The columns in the table are: ");

        System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
        for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
            System.out.println("Column " + i + " " + resultSet.getMetaData().getColumnName(i));
        }
    }

    private void writeResultSet(ResultSet resultSet) throws SQLException {
        // ResultSet is initially before the first data set
        while (resultSet.next()) {

        }
    }

    // You need to close the resultSet
    private void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connect != null) {
                connect.close();
            }
        } catch (Exception e) {

        }
    }

}
