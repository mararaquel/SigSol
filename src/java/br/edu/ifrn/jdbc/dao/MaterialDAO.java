/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifrn.jdbc.dao;

import br.edu.ifrn.jdbc.ConnectionFactory;
import static br.edu.ifrn.jdbc.ConnectionFactory.getConnection;
import br.edu.ifrn.jdbc.modelo.Material;
import br.edu.ifrn.jdbc.modelo.Pessoa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class MaterialDAO {
    //conexão com o banco de dados
    private Connection connection;
    PreparedStatement psmt = null;
    ResultSet rs = null;
    
    public MaterialDAO() throws SQLException {
        this.connection = ConnectionFactory.getConnection();
    }
    
    public void adicionarMaterial(Material material) throws SQLException {
       
        //prepared statement para inserir
        PreparedStatement stmt = this.connection.prepareStatement("insert into material (nome_material, tipo) values (?, ?)");
        
        //seta os valores
        stmt.setString(1, material.getNome_material());
        stmt.setInt(2, material.getTipo());
        
        //executa
        stmt.execute();
        stmt.close();
    }
    
    public List<Material> getListaEscritorio() throws SQLException{
       PreparedStatement stmt = this.connection.prepareStatement("select * from material where tipo = 1");
        ResultSet rs = stmt.executeQuery();
        
        List<Material> materiais = new ArrayList<Material>();
        
        while (rs.next()){
            //Criando o objeto Mateirial
            Material material = new Material();
            material.setIdmaterial(rs.getInt("idmaterial"));
            material.setNome_material(rs.getString("nome_material"));
            material.setTipo(rs.getInt("tipo"));
            
            //adicionando objeto à lista
            materiais.add(material);
        }
        rs.close();
        stmt.close();
        return materiais;
    }
    
    public void update(Material material) throws SQLException {
        connection = getConnection();
        PreparedStatement stmt;
        String sql = "UPDATE material set nome_material = ?, tipo = ? where idmaterial = ?";
        try{
            
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, material.getNome_material());
            stmt.setInt(2, material.getTipo());
            stmt.setInt(3, material.getIdmaterial());
            stmt.executeUpdate();
            System.out.println("Dado atualizado com sucesso");
            
            
        }catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }
    
    
        public void alterarMaterial(Material material) throws SQLException {
            PreparedStatement stmt = connection.prepareStatement("UPDATE material set nome_material = ? where idmaterial = ?;");
            stmt.setString(1, material.getNome_material());
            stmt.setInt(2, material.getIdmaterial());
            
            stmt.execute();
            stmt.close();
        }

}
