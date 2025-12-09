package pt.pauloazevedomobiliario.backend.dao;

import pt.pauloazevedomobiliario.backend.config.DataBaseConnection;
import pt.pauloazevedomobiliario.backend.model.Contactos;

import java.sql.*;
import java.util.ArrayList;

public class ContactosDAO {

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public ContactosDAO() {
        try {
            connection = DataBaseConnection.getConnection();
            statement = connection.createStatement();
        } catch (SQLException e) {
            System.out.println("Error creating statement: " + e.getMessage());
        }
    }

    public boolean insertContacto(Contactos contacto) {
        String sql =
                "INSERT INTO contactos (telemovel, gmail, morada, redesSociais_id) " +
                        "VALUES (" +
                        contacto.getTelemovel() + ", " +
                        "'" + contacto.getGmail() + "', " +
                        "'" + contacto.getMorada() + "', " +
                        contacto.getRedesSociais_id() +
                        ")";

        try {
            statement.executeUpdate(sql);
            return true;
        } catch (Exception e) {
            System.out.println("Error inserting contacto: " + e.getMessage());
            return false;
        }
    }

    public boolean removeContacto(Contactos contacto) {
        String sql =
                "DELETE FROM contactos WHERE id = " + contacto.getId();

        try {
            statement.executeUpdate(sql);
            return true;
        } catch (Exception e) {
            System.out.println("Error removing contacto: " + e.getMessage());
            return false;
        }
    }

    public boolean updateContacto(Contactos contacto) {
        String sql =
                "UPDATE contactos SET " +
                        "telemovel = " + contacto.getTelemovel() + ", " +
                        "gmail = '" + contacto.getGmail() + "', " +
                        "morada = '" + contacto.getMorada() + "', " +
                        "redesSociais_id = " + contacto.getRedesSociais_id() + " " +
                        "WHERE id = " + contacto.getId();

        try {
            statement.executeUpdate(sql);
            return true;
        } catch (Exception e) {
            System.out.println("Error updating contacto: " + e.getMessage());
            return false;
        }
    }

    public ArrayList<Contactos> getList() {
        ArrayList<Contactos> list = new ArrayList<>();
        String sql = "SELECT * FROM contactos";

        try {
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                list.add(new Contactos(
                        resultSet.getInt("id"),
                        resultSet.getInt("telemovel"),
                        resultSet.getString("gmail"),
                        resultSet.getString("morada"),
                        resultSet.getInt("redesSociais_id")
                ));
            }
        } catch (Exception e) {
            System.out.println("Error listing contactos: " + e.getMessage());
            return null;
        }

        return list;
    }

    public Contactos getContactoById(int id) {
        Contactos contacto = new Contactos();
        String sql = "SELECT * FROM contactos WHERE id = " + id;

        try {
            resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                contacto.setId(resultSet.getInt("id"));
                contacto.setTelemovel(resultSet.getInt("telemovel"));
                contacto.setGmail(resultSet.getString("gmail"));
                contacto.setMorada(resultSet.getString("morada"));
                contacto.setRedesSociais_id(resultSet.getInt("redesSociais_id"));
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println("Error getting contacto: " + e.getMessage());
            return null;
        }

        return contacto;
    }
}
