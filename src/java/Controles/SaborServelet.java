/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controles;

import DAOs.DAOSabor;
import Entidades.Sabor;
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
@WebServlet(name = "SaborServelet", urlPatterns = {"/Sabor"})
public class SaborServelet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    /*protected void processRequest(HttpServletRequest request, HttpServletResponse response) // prcoessaa a requisição http
            throws ServletException, IOException { // comunica com o banco
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWrite  r()) {
            /* TODO output your page here. You may use following sample code. */
 /*DAOSabor daoSabor = new DAOSabor();
            List<Sabor> lista = daoSabor.listInOrderId();
            String tabela = "";
            for (Sabor p : lista) {
                tabela += "<tr>"
                        + "<td>" + p.getIdSabor()+ "</td>"
                        + "<td>" + p.getNomeSabor()+ "</td>"
                        + "</tr>";
                //System.out.println(tabela);
            }
            request.getSession().setAttribute("resultado", tabela);
            
            response.sendRedirect(request.getContextPath() + "/pages/tabelaSabor.jsp");
        }
    }*/
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) // prcoessaa a requisição http
            throws ServletException, IOException { // comunica com o banco
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            DAOSabor daoSabor = new DAOSabor();
            Sabor sabor = new Sabor();
            String tabela = "";

            if (request.getParameter("idSabor") == "" || request.getParameter("idSabor") == null) {
                List<Sabor> lista = daoSabor.listInOrderId();
                 
                for (Sabor p : lista) {
     
                    tabela += "<tr>"
                            + "<td>" + p.getIdSabor() + "</td>"
                            + "<td>" + p.getNomeSabor() + "</td>"
                            + "<td>" + p.getStatus() + "</td>"
                            + "</tr>";

                    //System.out.println(tabela);
                }
            } else {
                Integer id = Integer.valueOf(request.getParameter("idSabor"));
                String nome = request.getParameter("nomeSabor");
                Boolean status = Boolean.valueOf(request.getParameter("statusSabor"));
                Boolean statusBoolean = false;
                if (status == true) {
                    statusBoolean = true;

                }
                // n~zo roda
                sabor.setIdSabor(id);
                sabor.setNomeSabor(nome);
                sabor.setStatus(statusBoolean);
                daoSabor.inserir(sabor);
                List<Sabor> lista = daoSabor.listInOrderId();
                for (Sabor p : lista) {
                    String statusBonito = "";
                    if (p.getStatus() ==true) {
                                statusBonito = "sim";
                            }else{
                                statusBonito="não";
                            }
                    tabela += "<tr>"
                            + "<td>" + p.getIdSabor() + "</td>"
                            + "<td>" + p.getNomeSabor() + "</td>"
                            + "<td>" + statusBonito + "</td>"
                            + "</tr>";
                }
                out.print("cad depois");
            }

            request.getSession().setAttribute("resultado", tabela);
            response.sendRedirect(request.getContextPath() + "/pages/tabelaSabor.jsp");
        }
       }
    

    protected void inserir(int id, String nome, Boolean statusBoolean) {
        DAOSabor daoSabor = new DAOSabor();
        Sabor sabor = new Sabor();
        sabor.setIdSabor(id);
        sabor.setNomeSabor(nome);
        sabor.setStatus(statusBoolean);
        daoSabor.inserir(sabor);
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
