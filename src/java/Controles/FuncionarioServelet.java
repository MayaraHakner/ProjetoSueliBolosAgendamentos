/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controles;

import DAOs.DAOFuncionario;
import Entidades.Funcionario;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
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
@WebServlet(name = "FuncionarioServelet", urlPatterns = {"/Funcionario"})
public class FuncionarioServelet extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            DAOFuncionario daoFuncionario = new DAOFuncionario();
            List<Funcionario> lista = daoFuncionario.listInOrderId();
            String tabela = "";
            for (Funcionario p : lista) {
                tabela += "<tr>"
                        + "<td>" + p.getIdFuncionario()+ "</td>"
                        + "<td>" + p.getNomeFuncionario()+ "</td>"
                        + "<td>" + p.getTelefoneFuncionario()+ "</td>"
                        + "<td>" + sdf.format(p.getDataNascFuncionario())+ "</td>"
                        + "<td>" + p.getEndereçoFuncionario()+ "</td>"
                        + "<td>" + p.getStatusIdStatus().getNomeStatus()+ "</td>"
                        + "<td>" + "<img src="+ p.getFotoFuncionario()+ ">"+"</td>"                        
                        + "</tr>";
                //System.out.println(tabela);
            }

            request.getSession().setAttribute("resultado", tabela);
            
            response.sendRedirect(request.getContextPath() + "/pages/tabelaFuncionario.jsp");
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
