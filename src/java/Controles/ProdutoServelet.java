/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controles;

import DAOs.DAOPrecoProduto;
import DAOs.DAOProduto;
import DAOs.DAOSabor;
import DAOs.DAOUnMedida;
import Entidades.PrecoProduto;
import Entidades.Produto;
import Entidades.Sabor;
import Entidades.UnMedida;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Mayara Hakner
 */
@WebServlet(name = "ProdutoServelet", urlPatterns = {"/Produto"})
public class ProdutoServelet extends HttpServlet {

    Locale ptBr = new Locale("pt", "BR");
    NumberFormat formatoDinheiro = NumberFormat.getCurrencyInstance(ptBr);

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
            DAOProduto daoProduto = new DAOProduto();
            DAOPrecoProduto daoPrecoProduto = new DAOPrecoProduto();
            PrecoProduto precoProduto = new PrecoProduto();
            Produto produto = new Produto();
            DAOSabor daoSabor = new DAOSabor();
            DAOUnMedida daoUnMedida = new DAOUnMedida();         
            Sabor sabor = new Sabor();
            UnMedida unMedida = new UnMedida();
            String tabela = "";
            
            //int i = 0;
            String id1 = request.getParameter("idProduto");
            if (id1==null) {
                List<Produto> lista = daoProduto.listInOrderId();
                for (Produto p : lista) {
                    //precoProduto.setProduto(p);
                    //String valor = formatoDinheiro.format(precoProduto.getPreco());
                    String statusBonito = "";
                    if (p.getStatus() ) {
                                statusBonito = "sim";
                            }else{
                                statusBonito="não";
                            }
                    tabela += "<tr>"
                            + "<td>" + p.getIdProduto() + "</td>"
                            + "<td>" + p.getNomeProduto() + "</td>"
                            + "<td>" + p.getPreco() + "</td>"
                            + "<td>" + p.getSaborIdSabor().getNomeSabor() + "</td>"
                            + "<td>" + p.getUnMedidaIdUnMedida().getNomeUnidadeMedida() + "</td>"
                            + "<td>" + statusBonito + "</td>"
                            + "</tr>";
                    //System.out.println(tabela);
                }
            } else {
                //out.print("aqui11");
                produto = new Produto();
                Boolean status = Boolean.valueOf(request.getParameter("statusProduto"));
                Boolean statusBoolean = false;
                if (status == true) {
                    statusBoolean = true;

                }
                Integer id = Integer.valueOf(request.getParameter("idProduto"));
                String nome = request.getParameter("nomeProduto");
                Double preco = Double.valueOf(request.getParameter("precoProduto"));
                Integer sabor1 = Integer.valueOf(request.getParameter("saborProduto"));
                String unidade1 = request.getParameter("unidadeProduto");
                out.print("here");
                sabor = daoSabor.obter(sabor1);
                unMedida = daoUnMedida.obter(unidade1);
                out.print("aqui2");
                produto.setIdProduto(id);
                produto.setNomeProduto(nome);
                produto.setPreco(preco);
                produto.setSaborIdSabor(sabor);
                produto.setUnMedidaIdUnMedida(unMedida);
                produto.setStatus(statusBoolean);
                out.print(" passou sabor");
                daoProduto.inserir(produto);
                out.print("cadastrou");
                List<Produto> lista = daoProduto.listInOrderId();
                String statusBonito = "";
                 if (status==true) {
                                statusBonito = "sim";
                            }else{
                                statusBonito="não";
                            }
                for (Produto p : lista) {
                    tabela += "<tr>"
                            + "<td>" + p.getIdProduto() + "</td>"
                            + "<td>" + p.getNomeProduto() + "</td>"
                            + "<td>" + p.getPreco() + "</td>"
                            + "<td>" + p.getSaborIdSabor().getNomeSabor() + "</td>"
                            + "<td>" + p.getUnMedidaIdUnMedida().getNomeUnidadeMedida() + "</td>"
                            + "<td>" + statusBonito + "</td>"
                            + "</tr>";
                    //System.out.println(tabela);
                }
            }
            request.getSession().setAttribute("resultado", tabela);

            response.sendRedirect(request.getContextPath() + "/pages/tabelaProduto.jsp");
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
