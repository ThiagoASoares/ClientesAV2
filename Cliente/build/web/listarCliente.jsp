<%-- 
    Document   : listaCliente
    Created on : 15 de nov de 2021, 14:21:10
    Author     : ThiagoASoares
--%>
<%@page import="model.Cliente"%>
<%@page import="dao.ClienteDao"%>
<%@page import="ControllerServlet.ControllerServlet"%>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Cadastro de Clientes</title>
</head>
<body>
    <center>
        <h1>Gerenciamento de Clientes</h1>
        
    </center>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>Listagem de Clientes</h2></caption>
            <tr>
                <th>ID</th>
                <th>Nome</th>
                <th>Enderco</th>
                <th>Telefone</th>
                <th>Email</th>
                <th>CPF</th>
                <th>Ações</th>
            </tr>
            <c:forEach var="cliente" items="${listaCliente}">
                <tr>
                    <td><c:out value="${cliente.id}" /></td>
                    <td><c:out value="${cliente.nome}" /></td>
                    <td><c:out value="${cliente.endereco}" /></td>
                    <td><c:out value="${cliente.telefone}" /></td>
                    <td><c:out value="${cliente.email}" /></td>
                    <td><c:out value="${cliente.cpf}" /></td>
                    <td>
                        <a href="ControllerServlet?acao=editarX&id=<c:out value='${cliente.id}' />">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="ControllerServlet?acao=deletarX&id=<c:out value='${cliente.id}' />">Delete</a>                     
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>   
<center>
    <h2>
            <a href="ControllerServlet?acao=novoX">Novo Cliente</a>
            &nbsp;&nbsp;&nbsp;
            <a href="ControllerServlet?acao=listarX">Listar Cliente</a>
             
        </h2>
    </center>
</body>
</html>
