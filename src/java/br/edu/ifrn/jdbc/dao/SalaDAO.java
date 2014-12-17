/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifrn.jdbc.dao;

import br.edu.ifrn.jdbc.ConnectionFactory;
import br.edu.ifrn.jdbc.modelo.Pessoa;
import br.edu.ifrn.jdbc.modelo.Sala;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author maryna
 */
public class SalaDAO {
     //conexão com o banco de dados
    private Connection connection;
    PreparedStatement psmt = null;
    ResultSet rs = null;
    
    public SalaDAO() throws SQLException {
        this.connection = ConnectionFactory.getConnection();
    }
    
    public void adicionarSala(Sala sala) throws SQLException {
       
        //prepared statement para inserir
        PreparedStatement stmt = this.connection.prepareStatement(
                "insert into sala (descricao, nome, numero) values (?, ?, ?)");
        
        //seta os valores
        stmt.setString(1, sala.getDescricao());
        stmt.setString(2, sala.getNome());
        stmt.setInt(3, sala.getNumero());
        
        //executa
        stmt.execute();
        stmt.close();
    }
}
