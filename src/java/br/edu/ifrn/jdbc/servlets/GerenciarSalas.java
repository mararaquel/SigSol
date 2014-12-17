/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifrn.jdbc.servlets;

import br.edu.ifrn.jdbc.dao.SalaDAO;
import br.edu.ifrn.jdbc.modelo.Sala;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author maryna
 */
public class GerenciarSalas extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String acao = request.getParameter("acao");
        
        if (acao.equals("cadastrar")){
            RequestDispatcher dispatcher = request.getRequestDispatcher("cadastrarSala.jsp");
            dispatcher.forward(request, response);
        } else if (acao.equals("listar")){
            // dao para pegar todas as salas
            ArrayList<Sala> salas = new ArrayList<Sala>();
            HttpSession session = request.getSession();
            session.setAttribute("salas", salas);
            RequestDispatcher dispatcher = request.getRequestDispatcher("listasSalas.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String acao = request.getParameter("acao");
        if (acao.equals("confirmarDados")){
            try {
                this.confirmarDadosDoCadastro(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(GerenciarSalas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void confirmarDadosDoCadastro(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        String numero = request.getParameter("numero");
        String nome = request.getParameter("nome");
        String descricao = request.getParameter("descricao");
        
        Sala sala = new Sala();
        sala.setDescricao(descricao);
        sala.setNome(nome);
        sala.setNumero(Integer.parseInt(numero));
        
        SalaDAO dao = new SalaDAO();
        dao.adicionarSala(sala);
    }


}
