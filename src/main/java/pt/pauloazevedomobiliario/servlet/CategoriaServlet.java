package pt.pauloazevedomobiliario.servlet;

import pt.pauloazevedomobiliario.backend.dao.CategoriaDAO;
import pt.pauloazevedomobiliario.backend.model.Categoria;

import java.io.*;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.http.*;

public class CategoriaServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {

            String action = request.getParameter("action");
            if (action.equalsIgnoreCase("insert")) {
                String nome = request.getParameter("nome");

                if (nome.equalsIgnoreCase("")) {
                    out.println("0");
                } else {
                    CategoriaDAO cat = new CategoriaDAO();
                    if (cat.insertCategoria(new Categoria(01, nome))) {
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
                    CategoriaDAO cat = new CategoriaDAO();
                    if (cat.removeCategoria(new Categoria(id, ""))) {
                        out.println("1");
                    } else {
                        out.println("0");
                    }
                }
            } else if (action.equalsIgnoreCase("update")) {
                String name = request.getParameter("name");
                int id = Integer.parseInt(request.getParameter("id"));

                if (id == 0) {
                    out.println("0");
                } else {
                    CategoriaDAO cat = new CategoriaDAO();
                    if (cat.updateCategoria(new Categoria(id, name))) {
                        out.println("1");
                    } else {
                        out.println("0");
                    }
                }
            } else if (action.equalsIgnoreCase("list")) {
                CategoriaDAO cat = new CategoriaDAO();
                ArrayList<Categoria> categorias = cat.listCategoria();
                if (categorias.size() == 0) {
                    out.println("0");
                } else {
                    String result = "";
                    for (int i = 0; i < categorias.size(); i++) {
                        if (i < categorias.size() - 1) {
                            result = result + "{id: " + categorias.get(i).getId() + ", name: " + categorias.get(i).getName() + "},\n";
                        } else {
                            result = result + "{id: " + categorias.get(i).getId() + ", name: " + categorias.get(i).getName() + "}";
                        }
                    }
                    out.println("[" + result + "]");
                }
            }

            } catch(Exception e){
                out.println("0");
                throw new RuntimeException(e);
            }
        }
    }