package pt.pauloazevedomobiliario.servlet;

import pt.pauloazevedomobiliario.backend.dao.ProdutoDAO;
import pt.pauloazevedomobiliario.backend.model.Produto;

import java.io.*;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.http.*;

public class ProdutoServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try{
            String action = request.getParameter("action");
            out.println(action);
            if (action.equalsIgnoreCase("insert")) {
                String name = request.getParameter("name");
                int cat_id = Integer.parseInt(request.getParameter("cat_id"));

                if (name.equalsIgnoreCase("")) {
                    out.println("0");
                } else {
                    ProdutoDAO produtoDAO = new ProdutoDAO();
                    if (produtoDAO.insertProduto(new Produto(01,name,"","",cat_id))) {
                        out.println("1");
                    } else {
                        out.println("0");
                    }
                }
            } else if (action.equalsIgnoreCase("remove")) {
                int id = Integer.parseInt(request.getParameter("id"));

                if (id == 0) {
                    out.println("0");
                } else {
                    ProdutoDAO produtoDAO = new ProdutoDAO();
                    if (produtoDAO.removeProduto(new Produto(id, null, null, null, 0))) {
                        out.println("1");
                    } else {
                        out.println("0");
                    }
                }
            } else if (action.equalsIgnoreCase("update")) {
                String name = request.getParameter("name");
                int id = Integer.parseInt(request.getParameter("id"));
                String description = request.getParameter("description");
                String image = request.getParameter("image");
                int cat_id = Integer.parseInt(request.getParameter("cat_id"));


                if (id == 0) {
                    out.println("0");
                } else {
                    ProdutoDAO produtoDAO = new ProdutoDAO();
                    if (produtoDAO.updateProduto(new Produto(id, name, description, image, cat_id))) {
                        out.println("1");
                    } else {
                        out.println("0");
                    }
                }
            } else if (action.equalsIgnoreCase("list")) {
                ProdutoDAO produtoDAO = new ProdutoDAO();
                ArrayList<Produto> produtos = produtoDAO.getList();
                if (produtos.size() == 0) {
                    out.println("0");
                } else {
                    String result = "";
                    for (int i = 0; i < produtos.size(); i++) {
                        if (i < produtos.size() - 1) {
                            result = result + "{id: " + produtos.get(i).getId() + ", name: " + produtos.get(i).getName() + "},\n";
                        } else {
                            result = result + "{id: " + produtos.get(i).getId() + ", name: " + produtos.get(i).getName() + "}";
                        }
                    }
                    out.println("[" + result + "]");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
