/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControllerServlet;


import dao.ClienteDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cliente;


/**
 *
 * @author ADM
 */

@WebServlet(name = "ControllerServlet", urlPatterns = {"/ControllerServlet"})
public class ControllerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ClienteDao clienteDao ;
    
    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        String acao = request.getContextPath();
        acao = request.getParameter("acao");
        try {
            switch (acao) {
            case "novoX":
                novoFormulario(request, response);
                break;
            case "incluirX":
                insereCliente(request, response);
                break;
            case "deletarX":
                deletaCliente(request, response);
                break;
            case "editarX":
                FormularioEdicao(request, response);
                break;
            case "atualizarX":
                atualizaCliente(request, response);
                break;
            case "listarX":
                listaCliente(request, response);
                break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
 
    @Override
    public void init() {
        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
 
        clienteDao = new ClienteDao(jdbcURL, jdbcUsername, jdbcPassword);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
 
    private void listaCliente(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Cliente> listaCliente = clienteDao.listaCliente();
        request.setAttribute("listaCliente", listaCliente);
        RequestDispatcher dispatcher = request.getRequestDispatcher("listarCliente.jsp");
        dispatcher.forward(request, response);
    }
 
    private void novoFormulario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher paginaFormulario = request.getRequestDispatcher("CadastroCliente.jsp");
        paginaFormulario.forward(request, response);
    }
 
    private void FormularioEdicao(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Cliente clienteExiste = clienteDao.getCliente(id);
        RequestDispatcher paginaFormulario = request.getRequestDispatcher("CadastroCliente.jsp");
        request.setAttribute("cliente", clienteExiste);
        paginaFormulario.forward(request, response);
 
    }
 
    private void insereCliente(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nome = request.getParameter("nome");
        String endereco = request.getParameter("endereco");
        String telefone = request.getParameter("telefone");
        String email = request.getParameter("email");
        String cpf = request.getParameter("cpf");
         
        Cliente novocliente = new Cliente(cpf,id , nome, endereco, telefone, email);
        clienteDao.incluir(novocliente);
        response.sendRedirect("ControllerServlet?acao=listarX");
    }
 
    private void atualizaCliente(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nome = request.getParameter("nome");
        String endereco = request.getParameter("endereco");
        String telefone = request.getParameter("telefone");
        String email = request.getParameter("email");
        String cpf = request.getParameter("cpf");
        Cliente cliente = new Cliente(cpf, id, nome, endereco, telefone, email);
        clienteDao.atualizar(cliente);
        response.sendRedirect("ControllerServlet?acao=listarX");
    }
 
    private void deletaCliente(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
 
        Cliente cliente = new Cliente(id);
        clienteDao.deletar(cliente);
        response.sendRedirect("ControllerServlet?acao=listarX");
 
    }
    
    // O restante do código foi omitido
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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

    

  