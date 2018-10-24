/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controles;

import DAOs.DAOUnMedida;
import Entidades.UnMedida;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Mayara Hakner
 */
@WebServlet(name = "UnidadeMedidaServelet", urlPatterns = {"/UnidadeMedida2"})
public class UnidadeMedidaServelet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) // prcoessaa a requisição http
            throws ServletException, IOException { // comunica com o banco
        response.setContentType("text/html;charset=UTF-8");
        
        try (PrintWriter out = response.getWriter()) {
            DAOUnMedida daoUnMedida = new DAOUnMedida();
            UnMedida unMedida = new UnMedida();
            String tabela = "";
            String id = request.getParameter("idUnMedida");
            String nome = request.getParameter("nomeUnMedida");

            if (request.getParameter("idUnMedida") == "" || request.getParameter("idUnMedida") == null) {
                List<UnMedida> lista = daoUnMedida.listInOrderId();
                for (UnMedida p : lista) {
                    tabela += "<tr>"
                            + "<td>" + p.getIdUnMedida()+ "</td>"
                            + "<td>" + p.getNomeUnidadeMedida() + "</td>"
                            + "</tr>";
                    //System.out.println(tabela);
                }
            }
            else {
                inserir(nome, id);

                     List<UnMedida> lista = daoUnMedida.listInOrderId();
                for (UnMedida p : lista) {
                    tabela += "<tr>"
                            + "<td>" + p.getIdUnMedida()+ "</td>"
                            + "<td>" + p.getNomeUnidadeMedida() + "</td>"
                            + "</tr>";
                    //System.out.println(tabela);
                }               
            }
            request.getSession().setAttribute("resultado", tabela);

            response.sendRedirect(request.getContextPath() + "/pages/tabelaUnidadeDeMedida.jsp");
            id = "";
            nome = "";
        }
    }

    protected void inserir(String nome, String id) {
        DAOUnMedida daoUnMedida = new DAOUnMedida();
        UnMedida unMedida = new UnMedida();
        unMedida.setIdUnMedida(id);
        unMedida.setNomeUnidadeMedida(nome);
        daoUnMedida.inserir(unMedida);

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET/</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("oi2");
        processRequest(request, response);
        System.out.println("oi3");
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
