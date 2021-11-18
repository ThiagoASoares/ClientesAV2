<%-- 
    Document   : CadastroCliente
    Created on : 15 de nov de 2021, 15:02:37
    Author     : ThiagoASoares
--%>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Controle de Cliente</title>
</head>
<body>
    <center>
        <h1>Gerenciamento de Clientes</h1>
        <h2>
            <a href="ControllerServlet?acao=novoX">Novo Cliente</a>
            &nbsp;&nbsp;&nbsp;
            <a href="ControllerServlet?acao=listarX">Listar Clientes</a>
             
        </h2>
    </center>
    <div align="center">
        <c:if test="${cliente != null}">
            <form action="ControllerServlet?acao=atualizarX" method="post">
        </c:if>
        <c:if test="${cliente == null}">
            <form action="ControllerServlet?acao=incluirX" method="post">
        </c:if>
        <table border="1" cellpadding="5">
            <caption>
                <h2>
                    <c:if test="${cliente != null}">
                        Atualizar Cliente
                    </c:if>
                    <c:if test="${cliente == null}">
                        Adicionar Cliente
                    </c:if>
                </h2>
            </caption>
                                     
            
            <tr>
                <th>Id  </th>
                <td>
                    <input type="text" name="id" size="10"
                            value="<c:out value='${cliente.id}' />"
                        />
                </td>
            </tr>
            <tr>
                <th>Nome  </th>
                <td>
                    <input type="text" name="nome" size="50"
                            value="<c:out value='${cliente.nome}' />"
                        />
                </td>
            </tr>
            <tr>
                <th>Endereco  </th>
                <td>
                    <input type="text" name="endereco" size="150"
                            value="<c:out value='${cliente.endereco}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Telefone  </th>
                <td>
                    <input type="text" name="telefone" size="15"
                            value="<c:out value='${cliente.telefone}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Email  </th>
                <td>
                    <input type="text" name="email" size="85"
                            value="<c:out value='${cliente.email}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Cpf  </th>
                <td>
                    <input type="text" name="cpf" size="14"
                            value="<c:out value='${cliente.cpf}' />"
                    />
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Salvar" />
                </td>
            </tr>
        </table>
        </form>
    </div>   
</body>
</html>
