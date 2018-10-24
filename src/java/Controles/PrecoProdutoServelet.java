/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controles;

import DAOs.DAOPrecoProduto;
import DAOs.DAOProduto;
import Entidades.PrecoProduto;
import Entidades.PrecoProdutoPK;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static org.eclipse.persistence.sessions.remote.corba.sun.TransporterHelper.id;

/**
 *
 * @author Mayara Hakner
 */
@WebServlet(name = "PrecoProdutoServelet", urlPatterns = {"/PrecoProduto"})
public class PrecoProdutoServelet extends HttpServlet {

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
            DAOPrecoProduto daoPrecoProduto = new DAOPrecoProduto();
            DAOProduto daoProduto = new DAOProduto();
            PrecoProduto status = new PrecoProduto();
            PrecoProduto precoProduto = new PrecoProduto();
            PrecoProdutoPK precoProdutoPK = new PrecoProdutoPK();
            String tabela = "";
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date data;

            //  if (request.getParameter("idPrecoProduto") == "" || request.getParameter("idPrecoProduto") == null) {
             String id1 = request.getParameter("data");
            if (id1==null) {
            //if (request.getParameter("data").equals("") || request.getParameter("data") == null) {
                out.print("aquiaaaa");
                List<PrecoProduto> lista = daoPrecoProduto.listInOrderId();
                for (PrecoProduto p : lista) {
                    int id = p.getPrecoProdutoPK().getProdutoIdProduto();
                    out.print("aquiaaaa2");
                    tabela += "<tr>"
                            + "<td>" + daoProduto.obter(id).getNomeProduto() + "</td>"
                            + "<td>" + sdf.format(p.getPrecoProdutoPK().getDataPrecoProduto()) + "</td>"
                            + "<td>" + p.getPreco() + "</td>"
                            + "</tr>";
                    //System.out.println(tabela);
                }
            } else {
                precoProduto = new PrecoProduto();
                precoProdutoPK = new PrecoProdutoPK();
                Integer produto = Integer.valueOf(request.getParameter("produto"));

                Double preco = Double.valueOf(request.getParameter("preco"));
                out.print("here");
                //PrecoProduto status = Integer.valueOf(request.getParameter("statusPrecoProduto"));

                try {
                    data = sdf.parse(request.getParameter("data"));
                    precoProdutoPK.setDataPrecoProduto(data);
                    precoProduto.setPrecoProdutoPK(new PrecoProdutoPK(data, produto));
                } catch (ParseException ex) {
                    Logger.getLogger(PrecoProdutoServelet.class.getName()).log(Level.SEVERE, null, ex);
                }

                precoProdutoPK.setProdutoIdProduto(produto);
                precoProduto.setPreco(preco);

                daoPrecoProduto.inserir(precoProduto);
                List<PrecoProduto> lista = daoPrecoProduto.listInOrderId();
                for (PrecoProduto p : lista) {
                    int id = p.getPrecoProdutoPK().getProdutoIdProduto();
                    tabela += "<tr>"
                            + "<td>" + daoProduto.obter(id).getNomeProduto() + "</td>"
                            + "<td>" + sdf.format(p.getPrecoProdutoPK().getDataPrecoProduto()) + "</td>"
                            + "<td>" + p.getPreco() + "</td>"
                            + "</tr>";
                    //System.out.println(tabela);

                }
            }
            request.getSession().setAttribute("resultado", tabela);

            response.sendRedirect(request.getContextPath() + "/pages/tabelaPrecoProduto.jsp");
        }
    }

    /*
    try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
 /* DAOPrecoProduto daoPrecoProduto = new DAOPrecoProduto();
            Produto produto = new Produto();
            DAOProduto daoProduto = new DAOProduto();
            List<PrecoProduto> lista = daoPrecoProduto.listInOrderId();
            String tabela = "";
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            for (PrecoProduto p : lista) {
                int id = p.getPrecoProdutoPK().getProdutoIdProduto();
                tabela += "<tr>"
                        + "<td>" + daoProduto.obter(id).getNomeProduto()+ "</td>"
                        + "<td>" + sdf.format(p.getPrecoProdutoPK().getDataPrecoProduto())+ "</td>"
                        + "<td>" + p.getPreco()+ "</td>"
                        + "</tr>";
                //System.out.println(tabela);
            }
            request.getSession().setAttribute("resultado", tabela);
            
            response.sendRedirect(request.getContextPath() + "/pages/tabelaPrecoProduto.jsp");
        }
     */
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
