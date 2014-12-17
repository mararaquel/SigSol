
package br.edu.ifrn.jdbc.servlets;

import br.edu.ifrn.jdbc.dao.DisciplinaDAO;
import br.edu.ifrn.jdbc.modelo.Disciplina;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GerenciarDisciplinas extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
       String acao = request.getParameter("acao");
        if (acao.equals("listar")){
            DisciplinaDAO disciplinaDao = new DisciplinaDAO();
                 try {
                     List listaDisciplinas = disciplinaDao.getListaDisciplinas();
                     request.setAttribute("sessaoListaDisciplinas", listaDisciplinas);
                     RequestDispatcher rd = request.getRequestDispatcher("/cadastrarDisciplina.jsp");
                     rd.forward(request, response);
                 } catch (Exception e) {
                     e.printStackTrace();
                 }
            
        }
        
        if(acao.equals("excluirdisciplina")){
            String idD = request.getParameter("iddisciplina");
            int id = Integer.parseInt(idD);
              Disciplina disciplina = new Disciplina();
              disciplina.setIddisciplina(id);
              DisciplinaDAO dao = new DisciplinaDAO();
              List<Disciplina> getAll = dao.getListaDisciplinas();
              for (Disciplina cont : getAll) {
            if(cont.getIddisciplina() == id){
                dao.removerDisciplinaPorId(disciplina);
                RequestDispatcher rd = request.getRequestDispatcher("/InicioAdm.jsp");
             rd.forward(request,response);
            }
          }
        }
        
        }catch(Exception ex){
           Logger.getLogger(GerenciarDisciplinas.class.getName()).log(Level.SEVERE, null, ex);

        }
        
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String acao = request.getParameter("acao");
        if (acao.equals("confirmarDados")){
             try {
                this.confirmarDadosDoCadastroDisciplina(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(GerenciarSalas.class.getName()).log(Level.SEVERE, null, ex);
            }
         }
        
        if (acao.equals("alterarDados")){
             try {
                this.alterarDados(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(GerenciarSalas.class.getName()).log(Level.SEVERE, null, ex);
            }
         }
    }

        private void confirmarDadosDoCadastroDisciplina(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
            RequestDispatcher rr = null;
            Disciplina disciplina = new Disciplina();
            String nome = request.getParameter("nome");
            boolean validacao = false;
            
            disciplina.setNome(nome);
            
            
            String nomeMinusculo = nome.toLowerCase();
            DisciplinaDAO dao = new DisciplinaDAO();
            List<Disciplina> getAll = dao.getListaDisciplinas();
            for(Disciplina cont : getAll){
                if(cont.getNome().toLowerCase().equals(nomeMinusculo)){
                    validacao = true;
                }
            }
            
            if(validacao == false){
                try{
                dao.adicionarDisciplina(disciplina);
                } catch (SQLException e){
                    throw new ServletException(e);
        }
            // Através do response.getWriter() mostrar uma mensagem de ok para o usuário
        RequestDispatcher rd = request.getRequestDispatcher("/cadastrarDisciplina.jsp");
        rd.forward(request,response);
        return;
            } else{
                request.setAttribute("mensagem", "Disciplina já cadastrada!");
            rr = request.getRequestDispatcher("/cadastrarDisciplina.jsp");
                 rr.forward(request, response);
            }
            
        }
    
     private void alterarDados(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
               String iddisciplina = request.getParameter("iddisciplina");
               String nome = request.getParameter("nome");
               
               Disciplina disciplina = new Disciplina();
               disciplina.setNome(nome);
               disciplina.setIddisciplina(Integer.parseInt(iddisciplina));
               DisciplinaDAO dao = new DisciplinaDAO();
               dao.alterarDisciplina(disciplina);
               RequestDispatcher rd = request.getRequestDispatcher("InicioAdm.jsp");
               rd.forward(request, response);
  
     
     }
}
