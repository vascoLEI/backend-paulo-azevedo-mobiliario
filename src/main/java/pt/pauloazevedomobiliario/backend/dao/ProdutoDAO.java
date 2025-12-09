package pt.pauloazevedomobiliario.backend.dao;

import pt.pauloazevedomobiliario.backend.config.DataBaseConnection;
import pt.pauloazevedomobiliario.backend.model.Produto;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ProdutoDAO {


    public Statement statement;
    public ResultSet resultSet;
    public static Connection connection;

    public ProdutoDAO() {
        this.connection = DataBaseConnection.getConnection();
        try{
            this.statement = connection.createStatement();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean insertProduto (Produto produto){
        String sql =
                "INSERT INTO produto (Name, Description, Image, Categoria_ID)" +
                        " VALUES " +
                            "('"+produto.getName()+"'," +
                            " '"+produto.getDescription()+"'," +
                            " '"+produto.getImage()+"'," +
                            " "+produto.getCategoria_id()+")";

        try{
            statement.executeUpdate(sql);
            return  true;
        } catch (Exception e) {
            System.out.println("Error");
            return false;
        }
    }
    public boolean removeProduto (Produto produto){
        String sql =
                "DELETE FROM PRODUTO WHERE id = "+produto.getId();

        try{
            statement.executeUpdate(sql);
            return  true;
        } catch (Exception e) {
            System.out.println("Error");
            return false;
        }
    }

    public boolean updateProduto (Produto produto){
        String sql =
                " UPDATE PRODUTO SET name = '"+produto.getName()+"'," +
                        " description = '"+produto.getDescription()+"'," +
                        " image = '"+produto.getImage()+"'"+
                        " WHERE id = "+produto.getId();

        try{
            statement.executeUpdate(sql);
            return  true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error");
            return false;
        }
    }

    public ArrayList<Produto> getList (){
        ArrayList<Produto> list = new ArrayList<>();
        String sql = "SELECT * FROM produto";

        try{
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                list.add(new Produto(resultSet.getInt("ID"),
                        resultSet.getString("Name"),
                        resultSet.getString("Description"),
                        resultSet.getString("Image"),
                        resultSet.getInt("Categoria_ID")));
            }

        } catch (Exception e) {
            System.out.println("Error");
            return null;
        }
        return list;
    }

    public Produto getProduto (String name){
        Produto produto = new Produto();
        String sql =
                "SELECT FROM produto WHERE name = '"+name+"'";
        try{
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                produto.setId(resultSet.getInt("ID"));
                produto.setName(resultSet.getString("Name"));
            }

        } catch (Exception e) {
            System.out.println("Error");
            return null;
        }
        return produto;
    }
}
