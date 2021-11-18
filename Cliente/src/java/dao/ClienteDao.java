/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Cliente;

/**
 *
 * @author ADM
 */
public class ClienteDao {
    
    private final String jdbcURL;
    private final String jdbcUsername;
    private final String jdbcPassword;
    private Connection jdbcConnection;
     
    public ClienteDao(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = "jdbc:derby://localhost:1527/Cliente";
        this.jdbcUsername = "root";
        this.jdbcPassword = "root";
    }
     
    protected void connect() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
                Class.forName("org.apache.derby.jdbc.ClientDriver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            jdbcConnection = DriverManager.getConnection(
                                        jdbcURL, jdbcUsername, jdbcPassword);
        }
    }
     
    protected void disconnect() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }
     
    public boolean incluir(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO ROOT.CLIENTES (id, nome, endereco, telefone, email, cpf ) VALUES (?, ?, ?, ?, ?, ?)";
        connect();
         
        boolean rowInserted;
        try (PreparedStatement statement = jdbcConnection.prepareStatement(sql)) {
            statement.setInt(1,cliente.getId());
            statement.setString(2, cliente.getNome());
            statement.setString(3, cliente.getEndereco());
            statement.setString(4, cliente.getTelefone());
            statement.setString(5, cliente.getEmail());
            statement.setString(6, cliente.getCpf());            
            rowInserted = statement.executeUpdate() > 0;
        }
        disconnect();
        return rowInserted;
    }
     
    public List<Cliente> listaCliente() throws SQLException {
        List<Cliente> listarCliente = new ArrayList<Cliente>();
         
        String sql = "SELECT * FROM ROOT.CLIENTES";
         
        connect();
         
        try (Statement statement = jdbcConnection.createStatement(); 
                ResultSet resultSet = statement.executeQuery(sql)) {
            
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                String endereco = resultSet.getString("endereco");
                String telefone  = resultSet.getString("telefone");
                String email = resultSet.getString("email");
                String cpf = resultSet.getString("cpf");
                    
                
                Cliente cliente = new Cliente(cpf, id, nome, endereco, telefone, email);
                listarCliente.add(cliente);
            }
            
        }
         
        disconnect();
         
        return listarCliente;
    }
     
    public boolean deletar(Cliente cliente) throws SQLException {
        String sql = "DELETE FROM ROOT.CLIENTES where ID = ?";
         
        connect();
         
        boolean rowDeleted;
        try (PreparedStatement statement = jdbcConnection.prepareStatement(sql)) {
            statement.setInt(1, cliente.getId());
            rowDeleted = statement.executeUpdate() > 0;
        }
        disconnect();
        return rowDeleted;     
    }
     
    public boolean atualizar(Cliente cliente) throws SQLException {
        String sql = "UPDATE ROOT.CLIENTES SET nome = ?, endereco = ?, telefone = ?, email = ?, cpf = ?";
        sql += " WHERE id = ?";
        connect();
         
        boolean rowUpdated;
        try (PreparedStatement statement = jdbcConnection.prepareStatement(sql)) {
            statement.setString(1, cliente.getNome());
            statement.setString(2, cliente.getEndereco());
            statement.setString(3, cliente.getTelefone());
            statement.setString(4, cliente.getEmail());
            statement.setString(5, cliente.getCpf());
             statement.setInt(6, cliente.getId());
            rowUpdated = statement.executeUpdate() > 0;
        }
        disconnect();
        return rowUpdated;     
    }
     
    public Cliente getCliente(int id) throws SQLException {
        Cliente cliente = null;
        String sql = "SELECT * FROM ROOT.CLIENTES WHERE id = ?";
         
        connect();
         
        try (PreparedStatement statement = jdbcConnection.prepareStatement(sql)) {
            statement.setInt(1, id);
            
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String nome = resultSet.getString("nome");
                    String endereco = resultSet.getString("endereco");
                    String telefone = resultSet.getString("telefone");
                    String email = resultSet.getString("email");
                    String cpf = resultSet.getString("cpf");
                    
                    cliente = new Cliente(cpf, id, nome, endereco, telefone, email);
                    
                }
            }
        }
         
        return cliente;
    }
}

