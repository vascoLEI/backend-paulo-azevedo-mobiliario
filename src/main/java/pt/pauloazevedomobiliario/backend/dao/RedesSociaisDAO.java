package pt.pauloazevedomobiliario.backend.dao;

import pt.pauloazevedomobiliario.backend.config.DataBaseConnection;
import pt.pauloazevedomobiliario.backend.model.RedesSociais;

import java.sql.*;
import java.util.ArrayList;

public class RedesSociaisDAO {

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public RedesSociaisDAO() {
        try {
            connection = DataBaseConnection.getConnection();
            statement = connection.createStatement();
        } catch (SQLException e) {
            System.out.println("Error creating statement: " + e.getMessage());
        }
    }

    public boolean insertRedeSocial(RedesSociais rede) {
        String sql =
                "INSERT INTO redes_sociais (name, icone, url) " +
                        "VALUES (" +
                        "'" + rede.getName() + "', " +
                        "'" + rede.getIcone() + "', " +
                        "'" + rede.getUrl() + "'" +
                        ")";

        try {
            statement.executeUpdate(sql);
            return true;
        } catch (Exception e) {
            System.out.println("Error inserting rede social: " + e.getMessage());
            return false;
        }
    }

    public boolean removeRedeSocial(RedesSociais rede) {
        String sql =
                "DELETE FROM redes_sociais WHERE id = " + rede.getId();

        try {
            statement.executeUpdate(sql);
            return true;
        } catch (Exception e) {
            System.out.println("Error removing rede social: " + e.getMessage());
            return false;
        }
    }

    public boolean updateRedeSocial(RedesSociais rede) {
        String sql =
                "UPDATE redes_sociais SET " +
                        "name = '" + rede.getName() + "', " +
                        "icone = '" + rede.getIcone() + "', " +
                        "url = '" + rede.getUrl() + "' " +
                        "WHERE id = " + rede.getId();

        try {
            statement.executeUpdate(sql);
            return true;
        } catch (Exception e) {
            System.out.println("Error updating rede social: " + e.getMessage());
            return false;
        }
    }

    public ArrayList<RedesSociais> getList() {
        ArrayList<RedesSociais> list = new ArrayList<>();
        String sql = "SELECT * FROM redes_sociais";

        try {
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                list.add(new RedesSociais(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("icone"),
                        resultSet.getString("url")
                ));
            }
        } catch (Exception e) {
            System.out.println("Error listing redes sociais: " + e.getMessage());
            return null;
        }

        return list;
    }

    public RedesSociais getRedeSocialByName(String name) {
        RedesSociais rede = new RedesSociais();
        String sql =
                "SELECT * FROM redes_sociais WHERE name = '" + name + "'";

        try {
            resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                rede.setId(resultSet.getInt("id"));
                rede.setName(resultSet.getString("name"));
                rede.setIcone(resultSet.getString("icone"));
                rede.setUrl(resultSet.getString("url"));
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("Error getting rede social: " + e.getMessage());
            return null;
        }

        return rede;
    }
}
