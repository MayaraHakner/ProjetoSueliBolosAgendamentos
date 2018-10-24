/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controles;

import DAOs.DAOPedido;
import DAOs.DAOPedidoHasProduto;
import DAOs.DAOProduto;
import Entidades.Pedido;
import Entidades.PedidoHasProduto;
import Entidades.PedidoHasProdutoPK;
import Entidades.Produto;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "PedidoHasProdutoServelet", urlPatterns = {"/PedidoHasProduto"})
public class PedidoHasProdutoServelet extends HttpServlet {

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
            DAOPedidoHasProduto daoPedidoHasProduto = new DAOPedidoHasProduto();

            DAOProduto daoProduto = new DAOProduto();
            Produto produto = new Produto();
            PedidoHasProduto pedidoHasProduto = new PedidoHasProduto();
            String tabela = "";
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date data;
            DAOPedido daoPedido = new DAOPedido();
            PedidoHasProdutoPK pedidoHasProdutoPK = new PedidoHasProdutoPK();

            //  if (request.getParameter("idPedidoHasProduto") == "" || request.getParameter("idPedidoHasProduto") == null) {
            String id1 = request.getParameter("pedido");
            if (id1==null) {
            
            //if (request.getParameter("pedido").equals("") || request.getParameter("pedido") == null) {
                List<PedidoHasProduto> lista = daoPedidoHasProduto.listInOrderId();
                for (PedidoHasProduto p : lista) {
                    tabela += "<tr>"
                            + "<td>" + p.getPedidoHasProdutoPK().getPedidoIdPedido() + "</td>"
                            + "<td>" + p.getQuantidadeProduto() + "</td>"
                            + "<td>" + p.getProduto() + "</td>"
                            + "<td>" + p.getPrecoProduto() + "</td>"
                            + "</tr>";
                    //System.out.println(tabela);
                }
            } else {
                pedidoHasProduto = new PedidoHasProduto();
                Pedido pedido = daoPedido.obter(Integer.valueOf(request.getParameter("pedido")));
                Double quantidade = Double.valueOf(request.getParameter("quantidade"));
                Double preco = Double.valueOf(request.getParameter("preco"));
                out.print("here");
                //Produto produto = Integer.valueOf(request.getParameter("produtoPedidoHasProduto"));
                Integer produto1 = Integer.valueOf(request.getParameter("produto"));
                produto = daoProduto.obter(produto1);
                out.print("aqui2");
                
                pedidoHasProduto.setPedido(pedido);
                pedidoHasProduto.setQuantidadeProduto(quantidade);
                pedidoHasProduto.setPrecoProduto(preco);
                pedidoHasProduto.setProduto(produto);
//                pedidoHasProdutoPK.setPedidoIdPedido(pedido);
//                pedidoHasProdutoPK.setPedidoIdPedido(pr);
//                
               pedidoHasProduto.setPedidoHasProdutoPK(new PedidoHasProdutoPK(pedido.getIdPedido(), produto.getIdProduto()));
               //pedidoHasProduto.setPedidoHasProdutoPK(pedidoHasProdutoPK);
               out.print("passou dados");
                daoPedidoHasProduto.inserir(pedidoHasProduto);
                out.print("inseriu");

//                List<PedidoHasProduto> lista = daoPedidoHasProduto.listInOrderId();
//                for (PedidoHasProduto p : lista) {
//                    tabela += "<tr>"
//                            + "<td>" + p.getPedidoHasProdutoPK().getPedidoIdPedido() + "</td>"
//                            + "<td>" + p.getQuantidadeProduto() + "</td>"
//                            + "<td>" + p.getProduto() + "</td>"
//                            + "<td>" + p.getPrecoProduto() + "</td>"
//                            + "</tr>";
//                    //System.out.println(tabela);
//                }
            }
//            request.getSession().setAttribute("resultado", tabela);

            response.sendRedirect(request.getContextPath() + "/pages/cadastroPedidoHasProduto.jsp");
        }
        }
//    protected void inserir(int id, String nome, String telefone, String endereco, Produto produto2, String foto, Date data) {
//        DAOPedidoHasProduto daoPedidoHasProduto = new DAOPedidoHasProduto();
//        DAOProduto daoProduto = new DAOProduto();
//        Produto produto1 = new Produto();
//
//    }

            /*protected void processRequest(HttpServletRequest request, HttpServletResponse response) // prcoessaa a requisição http
            throws ServletException, IOException { // comunica com o banco
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
 /*SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            DAOPedidoHasProduto daoPedidoHasProduto = new DAOPedidoHasProduto();
            List<PedidoHasProduto> lista = daoPedidoHasProduto.listInOrderId();
            String tabela = "";
            for (PedidoHasProduto p : lista) {
                tabela += "<tr>"
                        + "<td>" + p.getIdPedidoHasProduto()+ "</td>"
                        + "<td>" + p.getNomePedidoHasProduto()+ "</td>"
                        + "<td>" + p.getTelefonePedidoHasProduto()+ "</td>"
                        + "<td>" + sdf.format(p.getDataNascPedidoHasProduto())+ "</td>"
                        + "<td>" + p.getEndereçoPedidoHasProduto()+ "</td>"
                        + "<td>" + p.getProdutoIdProduto().getNomeProduto()+ "</td>"
                        + "<td>" + "<img src="+ p.getFotoPedidoHasProduto()+ ">"+"</td>"                        
                        + "</tr>";
                //System.out.println(tabela);
            }

            request.getSession().setAttribute("resultado", tabela);
            
            response.sendRedirect(request.getContextPath() + "/pages/tabelaPedidoHasProduto.jsp");
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
            protected void doGet
            (HttpServletRequest request, HttpServletResponse response)
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
            protected void doPost
            (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                processRequest(request, response);
            }

            /**
             * Returns a short description of the servlet.
             *
             * @return a String containing servlet description
             */
            @Override
            public String getServletInfo
            
                () {
        return "Short description";
            }// </editor-fold>

        }
