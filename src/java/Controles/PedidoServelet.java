/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controles;

import DAOs.DAOCliente;
import DAOs.DAOFuncionario;
import DAOs.DAOPedido;
import Entidades.Cliente;
import Entidades.Funcionario;
import Entidades.Pedido;
import static Entidades.Produto_.preco;
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
@WebServlet(name = "PedidoServelet", urlPatterns = {"/Pedido"})
public class PedidoServelet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    /* protected void processRequest(HttpServletRequest request, HttpServletResponse response) // prcoessaa a requisição http
            throws ServletException, IOException { // comunica com o banco
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
 /*DAOPedido daoPedido = new DAOPedido();
            List<Pedido> lista = daoPedido.listInOrderId();
            String tabela = "";
            for (Pedido p : lista) {
                tabela += "<tr>"
                        + "<td>" + p.getCodigoPedido()+ "</td>"
                        + "<td>" + p.getNomePedido()+ "</td>"
                        + "<td>" + p.getEnderecoPedido()+ "</td>"
                        + "<td>" + p.getObservacaoPedido()+ "</td>"
                        + "</tr>";
                //System.out.println(tabela);
            }
            request.getSession().setAttribute("resultado", tabela);
            
            response.sendRedirect(request.getContextPath() + "/pages/tabelaPedido.jsp");
        }
    }*/
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) // prcoessaa a requisição http
            throws ServletException, IOException { // comunica com o banco
        response.setContentType("text/html;charset=UTF-8");
        
        try (PrintWriter out = response.getWriter()) {
            System.out.println("aqui");
            DAOPedido daoPedido = new DAOPedido();
            Pedido pedido = new Pedido();         
            String tabela = "";
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date data;
            DAOCliente daoCliente = new DAOCliente();
            Cliente cliente = new Cliente();
            Funcionario funcionario = new Funcionario();
            DAOFuncionario daoFuncionario = new DAOFuncionario();

            //  if (request.getParameter("idPedido") == "" || request.getParameter("idPedido") == null) {
             String id1 = request.getParameter("idPedido");
            if (id1==null) {
            //if (request.getParameter("idPedido") == "" || request.getParameter("idPedido") == null) {
                List<Pedido> lista = daoPedido.listInOrderId();
                for (Pedido p : lista) {
                    tabela += "<tr>"
                            + "<td>" + p.getIdPedido()+ "</td>"
                            + "<td>" + p.getClienteCodigoCliente().getNomeCliente()+ "</td>"
                            + "<td>" + sdf.format(p.getDataPedido()) +"</td>"
                            + "<td>" + p.getHorarioEntrega()+ "</td>"
                            + "<td>" + p.getEntergaVemBuscar()+ "</td>"
                            + "<td>" + p.getEnderecoEntrega()+ "</td>"
                            + "<td>" + p.getDescontoPedido()+ "</td>"
                            + "<td>" + p.getFuncionarioIdFuncionario().getNomeFuncionario()+ "</td>"
                            + "<td>" + p.getObservacaoPedido()+ "</td>"
                            + "</tr>";
                    //System.out.println(tabela);
                }
                 request.getSession().setAttribute("resultado", tabela);
                response.sendRedirect(request.getContextPath() + "/pages/tabelaPedido.jsp");
            } else {
                pedido = new Pedido();               
                Integer pe = Integer.valueOf(request.getParameter("idPedido"));
                String c1 = String.valueOf(request.getParameter("cliente"));
                cliente = daoCliente.obter(c1);
                out.print("g=here1");
                String horario = request.getParameter("horario");
                Boolean vemBuscar = Boolean.valueOf(request.getParameter("vemBuscar"));
                String endereco = request.getParameter("endereco");
                out.print("g=here11");
                Double desconto = Double.valueOf(request.getParameter("desconto"));
                String obs = request.getParameter("observacao");
                out.print("g=here11");
                Integer f1 = Integer.valueOf(request.getParameter("funcionario"));
                funcionario = daoFuncionario.obter(f1);
                
                out.print("g=here11");
                try {
                    data = sdf.parse(request.getParameter("data"));
                    pedido.setDataPedido(data);      
                } catch (ParseException ex) {
                   // Logger.getLogger(PedidoServelet.class.getName()).log(Level.SEVERE, null, ex);
                }
                out.print("g=here2");
                pedido.setIdPedido(pe);
                pedido.setClienteCodigoCliente(cliente);
                pedido.setHorarioEntrega(horario);
                pedido.setEntergaVemBuscar(vemBuscar);
                pedido.setEnderecoEntrega(endereco);
                pedido.setDescontoPedido(desconto);
                pedido.setFuncionarioIdFuncionario(funcionario);
                pedido.setObservacaoPedido(obs);
                daoPedido.inserir(pedido);
                out.print("g=here2");
                List<Pedido> lista = daoPedido.listInOrderId();
                for (Pedido p : lista) {
                    tabela += "<tr>"
                            + "<td>" + p.getIdPedido()+ "</td>"
                            + "<td>" + p.getClienteCodigoCliente().getNomeCliente()+ "</td>"
                            + "<td>" + sdf.format(p.getDataPedido()) +"</td>"
                            + "<td>" + p.getHorarioEntrega()+ "</td>"
                            + "<td>" + p.getEntergaVemBuscar()+ "</td>"
                            + "<td>" + p.getEnderecoEntrega()+ "</td>"
                            + "<td>" + p.getDescontoPedido()+ "</td>"
                            + "<td>" + p.getFuncionarioIdFuncionario().getNomeFuncionario()+ "</td>"
                            + "<td>" + p.getObservacaoPedido()+ "</td>"
                            + "</tr>";
                    //System.out.println(tabela);
                }
                request.getSession().setAttribute("resultado", pe);
                response.sendRedirect(request.getContextPath() + "/pages/cadastroPedidoHasProduto.jsp");
            }
           
   
            
        }
    }

//        tá funcionando
//        try (PrintWriter out = response.getWriter()) {
//            DAOPedido daoPedido = new DAOPedido();
//            Cliente cliente = new Cliente();
//            Funcionario funcionario = new Funcionario();
//            Pedido pedido = new Pedido();
//            String tabela = "";
//            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//            /*String id = request.getParameter("idPedido");
//            String nome = request.getParameter("nomePedido");
//            String endereco = request.getParameter("enderecoPedido");
//            String observacao = request.getParameter("observacaoPedido");*/
//
//            if (request.getParameter("idPedido") == "" || request.getParameter("idPedido") == null) {
//                List<Pedido> lista = daoPedido.listInOrderId();
//                for (Pedido p : lista) {
//                    tabela += "<tr>"
//                            + "<td>" + p.getIdPedido()+ "</td>"
//                            + "<td>" + p.getClienteCodigoCliente().getNomeCliente()+ "</td>"
//                            + "<td>" + sdf.format(p.getDataPedido()) +"</td>"
//                            + "<td>" + p.getHorarioEntrega()+ "</td>"
//                            + "<td>" + p.getEntergaVemBuscar()+ "</td>"
//                            + "<td>" + p.getEnderecoEntrega()+ "</td>"
//                            + "<td>" + p.getDescontoPedido()+ "</td>"
//                            + "<td>" + p.getFuncionarioIdFuncionario().getNomeFuncionario()+ "</td>"
//                            + "<td>" + p.getObservacaoPedido()+ "</td>"
//                            + "</tr>";
//                    //System.out.println(tabela);
//                }
//            }
    /*else {
                //inserir(id, nome, endereco, observacao);
                     List<Pedido> lista = daoPedido.listInOrderId();
                for (Pedido p : lista) {
                       tabela += "<tr>"
                            + "<td>" + p.getIdPedido()+ "</td>"
                            + "<td>" + p.getClienteCodigoCliente()+ "</td>"
                            + "<td>" + p.getHorarioEntrega()+ "</td>"
                            + "<td>" + p.getEntergaVemBuscar()+ "</td>"
                            + "<td>" + p.getEnderecoEntrega()+ "</td>"
                            + "<td>" + p.getDescontoPedido()+ "</td>"
                            + "<td>" + p.getFuncionarioIdFuncionario()+ "</td>"
                            + "<td>" + p.getObservacaoPedido()+ "</td>"
                            + "</tr>";   
                   
                    //System.out.println(tabela);
                }               
            }*/
//    request.getSession ()
//
//    .setAttribute("resultado", tabela);
//
//    response.sendRedirect (request.getContextPath
//
//() + "/pages/tabelaPedido.jsp");
////            id = "";
//  //          nome = "";
//        }
//    }

   /* protected void inserir(String id, String nome, String endereco, String obs) {
        DAOPedido daoPedido = new DAOPedido();
        Pedido pedido = new Pedido();
        pedido.setCodigoPedido(id);
        pedido.setNomePedido(nome);
        pedido.setEnderecoPedido(endereco);
        pedido.setObservacaoPedido(obs);
        daoPedido.inserir(pedido);

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
