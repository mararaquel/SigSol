/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifrn.jdbc.servlets;

import br.edu.ifrn.jdbc.dao.MaterialDAO;
import br.edu.ifrn.jdbc.modelo.Material;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class cadastrarMaterial extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String acao = request.getParameter("acao");
        
        if(acao.equals("confirmar_escritorio")){
            try {
                
                this.confirmarDadosEscritorio(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(GerenciarMateriais.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
private void confirmarDadosEscritorio(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
            int validade = 0;
            RequestDispatcher rr = null;
            Material material = new Material();
            int tipo = 1; // tipo 1 = MATERIAL DE ESCRITÓRIO
            String nome_material = request.getParameter("nome_escritorio");
            material.setNome_material(nome_material);
            material.setTipo(tipo);
            //VERIFICAR SE MATERIAL JÁ ESTÁ CADASTRADO!!!
            if(tipo == 1){
                String teste = nome_material;
                try {
                    MaterialDAO dao = new MaterialDAO();
                    List<Material> getAll = dao.getListaEscritorio();
                    for (Material cont : getAll) {
                        if (cont.getNome_material().equals(teste)) {
                            validade++;
                        }
                    }
                } catch(Exception e){
                    
                }
            }
            
            if (validade > 0) {
                    request.setAttribute("mensagem", "Material já cadastrado!");
                    rr = request.getRequestDispatcher("/gerenciarMateriais.jsp");
                    rr.forward(request, response);
                } else {
                try{
                    MaterialDAO materialDao = new MaterialDAO();
                    materialDao.adicionarMaterial(material);
                    
                    
                    
                    //AQUI INÍCIO
                        String a = "listar";
                        if (a.equals("listar")) {
                            MaterialDAO pessoaDao = new MaterialDAO();
                            try {
                                List listaPessoas = pessoaDao.getListaEscritorio();
                                request.setAttribute("sessaoListaEscritorio", listaPessoas);
                                RequestDispatcher rd = request.getRequestDispatcher("/gerenciarMateriais.jsp");
                                rd.forward(request, response);
                            } catch (SQLException ex) {
                                Logger.getLogger(GerenciarUsuarios.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
            //AQUI FIM
                    //FALTAAAAAAAA AQUIIIIIIIIIIIIIIIIIIIIII
                    
                }catch(Exception e){
                    
                }
            }
            
        }
        

}
