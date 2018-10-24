/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controles;

import DAOs.DAOStatus;
import Entidades.Status;
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
@WebServlet(name = "StatusServelet", urlPatterns = {"/Status"})
public class StatusServelet extends HttpServlet {

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
            DAOStatus daoStatus = new DAOStatus();
            Status status = new Status();
            String tabela = "";

            //  if (request.getParameter("idStatus") == "" || request.getParameter("idStatus") == null) {
            if (request.getParameter("idStatus").equals("") || request.getParameter("idStatus") == null) {
                List<Status> lista = daoStatus.listInOrderId();
                for (Status p : lista) {
                    tabela += "<tr>"
                            + "<td>" + p.getIdStatus() + "</td>"
                            + "<td>" + p.getNomeStatus() + "</td>"
                            + "</tr>";
                    //System.out.println(tabela);
                }
            } else {
                Integer id = Integer.valueOf(request.getParameter("idStatus"));
                String nome = request.getParameter("nomeStatus");
                inserir(id, nome);
                List<Status> lista = daoStatus.listInOrderId();
                for (Status p : lista) {
                    tabela += "<tr>"
                            + "<td>" + p.getIdStatus() + "</td>"
                            + "<td>" + p.getNomeStatus() + "</td>"
                            + "</tr>";
                    //System.out.println(tabela);
                }
                nome = "";
            }
            request.getSession().setAttribute("resultado", tabela);

            response.sendRedirect(request.getContextPath() + "/pages/tabelaStatus.jsp");
            //id ="";
//            nome = "";
        }
    }

    protected void inserir(int id, String nome) {
        DAOStatus daoStatus = new DAOStatus();
        Status status = new Status();
        status.setIdStatus(id);
        status.setNomeStatus(nome);
        daoStatus.inserir(status);
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
        processRequest(request, response);
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
