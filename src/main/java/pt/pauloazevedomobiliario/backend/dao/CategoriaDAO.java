package pt.pauloazevedomobiliario.backend.dao;

import pt.pauloazevedomobiliario.backend.config.DataBaseConnection;
import pt.pauloazevedomobiliario.backend.model.Categoria;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CategoriaDAO {

    public Statement statement;
    public ResultSet resultSet;
    public static Connection connection;

    public CategoriaDAO() {
        this.connection = DataBaseConnection.getConnection();
        try{
            this.statement = connection.createStatement();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public boolean insertCategoria (Categoria categoria){
        String sql =
                "INSERT INTO categoria (Name) VALUES ('"+categoria.getName()+"')";

        try{
            statement.executeUpdate(sql);
            return  true;
        } catch (Exception e) {
            System.out.println("Error");
            return false;
        }
    }

    public boolean removeCategoria (Categoria categoria){
        String sql =
                "DELETE FROM Categoria WHERE ID = "+categoria.getId();
        try{
            statement.executeUpdate(sql);
            return  true;
        } catch (Exception e) {
            System.out.println("Error");
            return false;
        }
    }

    public boolean updateCategoria (Categoria categoria){
        String sql=
                "UPDATE categoria SET name = '"+categoria.getName()+"' WHERE ID = "+categoria.getId();

        try{
            statement.executeUpdate(sql);
            return  true;
        } catch (Exception e) {
            System.out.println("Error");
            return false;
        }
    }

    public ArrayList<Categoria> listCategoria (){
        ArrayList<Categoria> list = new ArrayList<>();

        String sql =
                "SELECT * FROM categoria";

        try{
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                list.add(new Categoria(resultSet.getInt("ID"), resultSet.getString("Name")));
            }

        } catch (Exception e) {
            System.out.println("Error");
            return null;
        }
        return list;
    }

    public Categoria getCategoria (String name){
        Categoria categoria = new Categoria();

        String sql =
                "SELECT * FROM categoria WHERE name = '"+name+"'";

        try{
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                categoria.setId(resultSet.getInt("ID"));
                categoria.setName(resultSet.getString("Name"));
            }

        } catch (Exception e) {
            System.out.println("Error");
            return null;
        }
        return categoria;
    }



}
