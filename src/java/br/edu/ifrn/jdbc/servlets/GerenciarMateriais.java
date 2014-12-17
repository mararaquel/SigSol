/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrn.jdbc.servlets;

import br.edu.ifrn.jdbc.dao.MaterialDAO;
import br.edu.ifrn.jdbc.dao.PessoaDAO;
import br.edu.ifrn.jdbc.modelo.Material;
import br.edu.ifrn.jdbc.modelo.Pessoa;
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

/**
 *
 * @author maryna
 */
public class GerenciarMateriais extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String acao = request.getParameter("acao");
            if (acao.equals("listar")) {
                MaterialDAO materialDao = new MaterialDAO();
                try {
                    List listaEscritorio = materialDao.getListaEscritorio();
                    request.setAttribute("sessaoListaEscritorio", listaEscritorio);
                    RequestDispatcher rd = request.getRequestDispatcher("gerenciarMateriais.jsp");
                    rd.forward(request, response);
                } catch (Exception e) {

                }
            }

        } catch (Exception e) {

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String acao = request.getParameter("acao");

        if(acao.equals("alterarDados")){
            try {
                this.alterarDadosMaterial(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(GerenciarMateriais.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    
      private void alterarDadosMaterial(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
            String nome = request.getParameter("nome_material");
            String id = request.getParameter("idmaterial");
            Material material = new Material();
            material.setNome_material(nome);
            material.setIdmaterial(Integer.parseInt(id));
            MaterialDAO dao = new MaterialDAO();
            dao.alterarMaterial(material);
            RequestDispatcher rd = request.getRequestDispatcher("gerenciarMateriais.jsp");
            rd.forward(request, response);
      }

    
   
    

}
