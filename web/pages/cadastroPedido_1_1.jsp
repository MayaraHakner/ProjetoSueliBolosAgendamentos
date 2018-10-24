<%-- 
    Document   : cadastroSabor
    Created on : 17/04/2018, 17:15:33
    Author     : Mayara Hakner
--%>

<%@page import="DAOs.DAOPrecoProduto"%>
<%@page import="Entidades.PrecoProduto"%>
<%@page import="DAOs.DAOPrecoProduto"%>
<%@page import="DAOs.DAOPedido"%>
<%@page import="DAOs.DAOProduto"%>
<%@page import="Entidades.Produto"%>
<%@page import="Entidades.Pedido"%>
<%@page import="Entidades.Cliente"%>
<%@page import="Entidades.Status"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="DAOs.DAOStatus"%>
<%@page import="DAOs.DAOStatus"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.util.Locale"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*,
        DAOs.DAOCliente,
        DAOs.DAOFuncionario,
        Entidades.Funcionario,
        java.text.NumberFormat,
        java.text.SimpleDateFormat"
        %>

<%
    Locale ptBr = new Locale("pt", "BR");
    NumberFormat formatoDinheiro = NumberFormat.getCurrencyInstance(ptBr);
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    DAOCliente daoCliente = new DAOCliente();
    DAOFuncionario daoFuncionario = new DAOFuncionario();
    List<Cliente> cliente = daoCliente.listInOrderId();
    List<Funcionario> funcionario = daoFuncionario.listInOrderId();

    DAOProduto daoProduto = new DAOProduto();
    Produto s = new Produto();
    List<Produto> produto = daoProduto.listInOrderId();

    DAOPedido daoPedido1 = new DAOPedido();
    Pedido u = new Pedido();
    List<Pedido> pedido = daoPedido1.listInOrderId();

    DAOPrecoProduto daoPrecoProduto = new DAOPrecoProduto();
    PrecoProduto pr = new PrecoProduto();
    List<PrecoProduto> preco = daoPrecoProduto.listInOrderId();

%>
<!DOCTYPE html>

<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>SB Admin 2 - Bootstrap Admin Theme</title>

        <!-- Bootstrap Core CSS -->
        <link rel="stylesheet" href="../cssUnidadeMedida.css" type="text/css"/> 
        <link href="../vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <!-- MetisMenu CSS -->
        <link href="../vendor/metisMenu/metisMenu.min.css" rel="stylesheet">

        <!-- Custom CSS -->
        <link href="../dist/css/sb-admin-2.css" rel="stylesheet">

        <!-- Custom Fonts -->
        <link href="../vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->

    </head>
    <body>
        <div id="wrapper">

            <!-- Navigation -->
            <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">

                <div class="navbar-header" >
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a id="img" href="index.html"><img src="../fotos/logoDW.png" width="150" height="150"></a>    

                </div>  
                <div >
                    <h2 id="title"> Sueli Bolos Agendamentos</h2> 
                </div>
                <div class="navbar-default sidebar" role="navigation">
                    <div class="sidebar-nav navbar-collapse">
                        <ul class="nav" id="side-menu">
                            <li class="sidebar-search">
                                <div class="input-group custom-search-form">
                                    <input type="text" class="form-control" placeholder="Search...">
                                    <span class="input-group-btn">
                                        <button class="btn btn-default" type="button">
                                            <i class="fa fa-search"></i>
                                        </button>
                                    </span>
                                </div>
                                <!-- /input-group -->
                            </li>
                            <li>
                                <a href="cadastroPedido.jsp"><i class="fa fa-dashboard fa-fw"></i> Novo Pedido</a>
                            </li>
                            <li>
                                <a href="#"><i class="fa fa-bar-chart-o fa-fw"></i> Cadastros<span class="fa arrow"></span></a>
                                <ul class="nav nav-second-level">
                                    <li>
                                        <a href="cadastroProduto.jsp">Produto</a>
                                    </li>
                                    <li>
                                        <a href="cadastroFuncionario.jsp">Funcionário</a>
                                    </li>
                                    <li>
                                        <a href="cadastroPedido.jsp">Pedido</a>
                                    </li>
                                    <li>
                                        <a href="cadastroCliente.jsp">Cliente</a>
                                    </li>
                                    <li>
                                        <a href="cadastroUnidadeMedida.jsp">Unidade de Medida</a>
                                    </li>
                                    <li>
                                        <a href="cadastroSabor.jsp">Sabor</a>
                                    </li>
                                    <li>
                                        <a href="cadastroPrecoProduto.jsp">Preço Produto</a>
                                    </li>
                                    <li>
                                        <a href="cadastroStatus.jsp">Status</a>
                                    </li>

                                </ul>
                                <!-- /.nav-second-level -->
                            </li>
                            <li>
                                <a href="tables.html"><i class="fa fa-bar-chart-o fa-fw"></i> Relatórios<span class="fa arrow"></span></a>
                                <ul class="nav nav-second-level">
                                    <li>
                                        <a href="../Produto">Produto</a>
                                    </li>
                                    <li>
                                        <a href="../Funcionario">Funcionário</a>
                                    </li>
                                    <li>
                                        <a href="../Pedido">Pedido</a>
                                    </li>
                                    <li>
                                        <a href="../Cliente">Cliente</a>
                                    </li>
                                    <li>
                                        <a href="../UnidadeMedida2">Unidade de Medida</a>
                                    </li>
                                    <li>
                                        <a href="tabelaSabor.jsp">Sabor</a>
                                    </li>
                                    <li>
                                        <a href="../PrecoProduto">Preço Produto</a>
                                    </li>
                                    <li>
                                        <a href="tabelaStatus.jsp">Status</a>
                                    </li>
                                </ul>   
                            </li>

                            <li>
                                <a href="#"><i class="fa fa-dashboard fa-fw"></i> Agenda Sueli Bolos</a>
                            </li><!-- /.nav-second-level -->

                        </ul>
                    </div>
                    <!-- /.sidebar-collapse -->
                </div>
                <!-- /.navbar-static-side -->
            </nav>


            <div id="page-wrapper">
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">Cadastro Pedido</h1>
                    </div>
                    <!-- /.col-lg-12 -->
                </div>
                <!-- /.row -->
                <div class="row">
                    <div class="col-lg-12">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                Pedido
                            </div>
                            <div class="panel-body">
                                <div class="row">
                                    <div class="col-lg-6">
                                        <form method="post" >
                                            <div class="form-group">
                                                <label >Código</label>
                                                <% DAOs.DAOPedido daoPedido = new DAOs.DAOPedido();%>
                                                <input name="idPedido"class="form-control" placeholder="" value="<%=daoPedido.autoIdPedido()%>" readonly="">
                                            </div>


                                            <div class="form-group">
                                                <label > Desconto </label>
                                                <input name="desconto" class="form-control" placeholder="">
                                            </div>
                                            <div class="form-group">
                                                <label > Funcionário </label>
                                                <select class="form-control" name="funcionario">
                                                    <%
                                                        for (Funcionario f : funcionario) {
                                                    %>
                                                    <option value="<%=f.getIdFuncionario()%>"> <%= f.getNomeFuncionario()%> </option>
                                                    <%}%>
                                                </select>    
                                            </div>
                                            <div class="form-group">
                                                <label > Observação </label>
                                                <input name="observacao" class="form-control" placeholder="">
                                            </div>
                                            <div class="checkbox">
                                                <label>
                                                    <input name="vemBuscar" type="checkbox" value="true" >Vem Buscar

                                                </label>
                                            </div>


                                        </form>
                                    </div>
                                    <!-- /.col-lg-6 (nested) -->
                                    <div class="col-lg-6">
                                        <form role="form">

                                            <div class="form-group">
                                                <label > Cliente </label>
                                                <select class="form-control" name="cliente">
                                                    <%
                                                        for (Cliente p : cliente) {
                                                    %>
                                                    <option value="<%=p.getCodigoCliente()%>"> <%= p.getNomeCliente()%> </option>
                                                    <%}%>
                                                </select>    
                                            </div>
                                            <div class="form-group">
                                                <label > Data Pedido </label>
                                                <input name="data" class="form-control" placeholder="">
                                            </div>
                                            <div class="form-group">
                                                <label > Horário </label>
                                                <input name="horario" class="form-control" placeholder="">
                                            </div>
                                            <div class="form-group">
                                                <label > Endereço </label>
                                                <input name="endereco" class="form-control" placeholder="">
                                            </div>
                                            <button type="submit" name="ok" class="btn btn-default"  >Salvar e Adicionar Produtos</button>
                                            <button type="reset" class="btn btn-default">Reset Button</button>
                                        </form>



                                        <!-- /.panel-body -->
                                    </div>

                                    <!-- /.panel -->
                                </div>

                                <!-- /.row (nested) -->
                            </div>
                            <!-- /.panel-body -->
                        </div>
                        <!-- /.panel -->
                    </div>
                    <!-- /.col-lg-12 -->
                </div>
                <!-- /.row -->
            </div>
            <!-- /#page-wrapper -->

        </div>
        <!-- /#wrapper -->

        <!-- jQuery -->
        <script src="../vendor/jquery/jquery.min.js"></script>


        <!-- /.col-lg-6 (nested) -->
    </div>
    <!-- /.row (nested) -->
</div>
<!-- /.panel-body -->
</div>
<!-- /.panel -->
</div>
<!-- /.col-lg-12 -->
</div>
<!-- /.row -->
</div>
<!-- /#page-wrapper -->

</div>
<!-- /#wrapper -->

<!-- jQuery -->
<script src="../vendor/jquery/jquery.min.js"></script>



</div>

<!-- /.col-lg-6 (nested) -->
</div>

<!-- /.row (nested) -->
</div>
<!-- /.panel-body -->
</div>
<!-- /.panel -->
</div>
<!-- /.col-lg-12 -->
</div>
<!-- /.row -->
</div>
<!-- /#page-wrapper -->

</div>
<!-- /#wrapper -->

<!-- jQuery -->
<script src="../vendor/jquery/jquery.min.js"></script>


</div>
<!-- /.col-lg-6 (nested) -->
</div>
<!-- /.row (nested) -->
</div>
<!-- /.panel-body -->
</div>
<!-- /.panel -->
</div>
<!-- /.col-lg-12 -->
</div>
<!-- /.row -->
</div>
<!-- /#page-wrapper -->

</div>
<!-- /#wrapper -->

<!-- jQuery -->
<script src="../vendor/jquery/jquery.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="../vendor/bootstrap/js/bootstrap.min.js"></script>

<!-- Metis Menu Plugin JavaScript -->
<script src="../vendor/metisMenu/metisMenu.min.js"></script>

<!-- Custom Theme JavaScript -->
<script src="../dist/js/sb-admin-2.js"></script>

</body>

</html>