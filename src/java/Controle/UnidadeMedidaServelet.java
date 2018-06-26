/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import DAOs.DAOUnMedida;
import Entidades.UnMedida;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
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
            System.out.println("oi");
            /* TODO output your page here. You may use following sample code. */
            DAOUnMedida daoUnMedida = new DAOUnMedida();
            List<UnMedida> lista = daoUnMedida.listInOrderId();
            String tabela = "";
            for (UnMedida p : lista) {
                tabela += "<tr>"
                        + "<td>" + p.getNomeUnidadeMedida() + "</td>"
                        + "<td>" + p.getNomeUnidadeMedida() + "</td>"
                        + "</tr>";
                //System.out.println(tabela);
            }
            request.getSession().setAttribute("resultado", tabela);
            
            response.sendRedirect(request.getContextPath() + "/pages/tabelaUnidadeDeMedida.jsp");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
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
