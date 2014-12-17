/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifrn.jdbc.servlets;

import br.edu.ifrn.jdbc.dao.PessoaDAO;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author maryna
 */
public class logar extends HttpServlet {

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       }
 @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = request.getParameter("acao");
        if (acao.equals("confirmarLogin")){
            try {
                this.confirmarLogin(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(GerenciarSalas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
        private void confirmarLogin(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        String usuario = request.getParameter("matricula");
        String senha = request.getParameter("senha");
        
        Pessoa pessoa = new Pessoa();
        pessoa.setMatricula(usuario);
        pessoa.setSenha(senha);
        
        boolean verificar = false;
        PessoaDAO dao = new PessoaDAO();
        List<Pessoa> getAll = dao.getLista();
            RequestDispatcher rd = null;
        
            
        for (Pessoa cont : getAll) {
            if((cont.getMatricula().equals(usuario)) && (cont.getSenha().equals(senha))){
                // Se for ADMINISTRADOR
            if(cont.getTipo() == 4){
                verificar = true;
                HttpSession sessao = request.getSession();
                sessao.setAttribute("sessaoUsuario", cont.getNome());
                rd = request.getRequestDispatcher("/InicioAdm.jsp");
                 rd.forward(request, response);
            }
            
            // Se for ALUNO
            if(cont.getTipo() == 1){
                verificar = true;
                HttpSession sessao = request.getSession();
                sessao.setAttribute("sessaoUsuario", cont.getNome());
                rd = request.getRequestDispatcher("/InicioAluno.jsp");
                 rd.forward(request, response);
            }
            
            //Se for PROFESSOR
            if(cont.getTipo() == 2){
                verificar = true;
                HttpSession sessao = request.getSession();
                sessao.setAttribute("sessaoUsuario", cont.getNome());
                rd = request.getRequestDispatcher("/InicioProfessor.jsp");
                 rd.forward(request, response);
            }
            
            // Se for TÉCNICO ADMINISTRATIVO
            if(cont.getTipo() == 3){
                verificar = true;
                HttpSession sessao = request.getSession();
                sessao.setAttribute("sessaoUsuario", cont.getNome());
                rd = request.getRequestDispatcher("/InicioTecAdm.jsp");
                 rd.forward(request, response);
            }
          }
            }
        
        // SE A SENHA OU USUÁRIO ESTIVEREM INCORRETOS!
        if(verificar == false){
            request.setAttribute("mensagem", "Usuário ou senha inválidos.");
            rd = request.getRequestDispatcher("/index.jsp");
                 rd.forward(request, response);
        }
        }

}
