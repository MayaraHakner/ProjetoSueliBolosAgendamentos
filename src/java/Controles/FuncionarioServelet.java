/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controles;

import DAOs.DAOFuncionario;
import DAOs.DAOStatus;
import Entidades.Funcionario;
import Entidades.Status;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
            DAOFuncionario daoFuncionario = new DAOFuncionario();
            
            DAOStatus daoStatus = new DAOStatus();
            Status status = new Status();
            Funcionario funcionario = new Funcionario();
            String tabela = "";
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date data;
            String id1 = request.getParameter("idFuncionario");

            //  if (request.getParameter("idFuncionario") == "" || request.getParameter("idFuncionario") == null) {
            if (request.getParameter(id1).equals("") || request.getParameter(id1) == null) {
                List<Funcionario> lista = daoFuncionario.listInOrderId();
                for (Funcionario p : lista) {
                    tabela += "<tr>"
                            + "<td>" + p.getIdFuncionario() + "</td>"
                            + "<td>" + p.getNomeFuncionario() + "</td>"
                            + "<td>" + p.getTelefoneFuncionario() + "</td>"
                            + "<td>" + sdf.format(p.getDataNascFuncionario()) + "</td>"
                            + "<td>" + p.getEndereçoFuncionario() + "</td>"
                            + "<td>" + p.getStatusIdStatus().getNomeStatus() + "</td>"
                            + "<td>" + "<img src=" + p.getFotoFuncionario() + ">" + "</td>"
                            + "</tr>";
                    //System.out.println(tabela);
                }
            } else {
                funcionario = new Funcionario();
                Integer id = Integer.valueOf(request.getParameter("idFuncionario"));
                String nome = request.getParameter("nomeFuncionario");
                String telefone = request.getParameter("telefoneFuncionario");
                String endereco = request.getParameter("enderecoFuncionario");
                String foto = request.getParameter("fotoFuncionario");
                out.print("here");
                //Status status = Integer.valueOf(request.getParameter("statusFuncionario"));
                Integer status1 = Integer.valueOf(request.getParameter("status"));
                status = daoStatus.obter(status1);
                out.print("aqui2");
                try {
                    data = sdf.parse(request.getParameter("dataFuncionario"));
                    funcionario.setDataNascFuncionario(data);
                } catch (ParseException ex) {
                    //Logger.getLogger(FuncionarioServelet.class.getName()).log(Level.SEVERE, null, ex);
                }  
                funcionario.setIdFuncionario(id);
                funcionario.setNomeFuncionario(nome);
                funcionario.setTelefoneFuncionario(telefone);
                funcionario.setEndereçoFuncionario(endereco);
                funcionario.setStatusIdStatus(status);
                funcionario.setFotoFuncionario(foto);
                daoFuncionario.inserir(funcionario);
                List<Funcionario> lista = daoFuncionario.listInOrderId();
                for (Funcionario p : lista) {
                    tabela += "<tr>"
                            + "<td>" + p.getIdFuncionario() + "</td>"
                            + "<td>" + p.getNomeFuncionario() + "</td>"
                            + "<td>" + p.getTelefoneFuncionario() + "</td>"
                            + "<td>" + sdf.format(p.getDataNascFuncionario()) + "</td>"
                            + "<td>" + p.getEndereçoFuncionario() + "</td>"
                            + "<td>" + p.getStatusIdStatus().getNomeStatus() + "</td>"
                            + "<td>" + "<img src=" + p.getFotoFuncionario() + ">" + "</td>"
                            + "</tr>";
                    //System.out.println(tabela);
                }
                nome = "";
            }
            request.getSession().setAttribute("resultado", tabela);

            response.sendRedirect(request.getContextPath() + "/pages/tabelaFuncionario.jsp");
        }
    }

//    protected void inserir(int id, String nome, String telefone, String endereco, Status status2, String foto, Date data) {
//        DAOFuncionario daoFuncionario = new DAOFuncionario();
//        DAOStatus daoStatus = new DAOStatus();
//        Status status1 = new Status();
//
//    }

    /*protected void processRequest(HttpServletRequest request, HttpServletResponse response) // prcoessaa a requisição http
            throws ServletException, IOException { // comunica com o banco
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
 /*SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
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
    }*/

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
