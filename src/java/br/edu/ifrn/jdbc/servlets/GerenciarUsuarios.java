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

public class GerenciarUsuarios extends HttpServlet {
       
@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try{
        String acao = request.getParameter("acao");
        // Listar Usuários
         if (acao.equals("listar")){
            PessoaDAO pessoaDao = new PessoaDAO();
                 try {
                     List listaPessoas = pessoaDao.getLista();
                     request.setAttribute("sessaoListaPessoas", listaPessoas);
                     RequestDispatcher rd = request.getRequestDispatcher("/usuarios.jsp");
                     rd.forward(request, response);
                 } catch (SQLException ex) {
                     Logger.getLogger(GerenciarUsuarios.class.getName()).log(Level.SEVERE, null, ex);
                 }
             }
         
         
         
         
         
         
         
            if(acao.equals("excluirusuario")){
             String matricula = request.getParameter("matricula");
              Pessoa pessoa = new Pessoa();
              pessoa.setMatricula(matricula);
              PessoaDAO dao = new PessoaDAO();
              List<Pessoa> getAll = dao.getLista();
              for (Pessoa cont : getAll) {
            if(cont.getMatricula().equals(matricula)){
                dao.removerPessoaPorMatricula(pessoa);
                dao.update(pessoa);
                
                
                acao = "listar";
                if (acao.equals("listar")){
            PessoaDAO pessoaDao = new PessoaDAO();
                 try {
                     List listaPessoas = pessoaDao.getLista();
                     request.setAttribute("sessaoListaPessoas", listaPessoas);
                     RequestDispatcher rd = request.getRequestDispatcher("/usuarios.jsp");
                     rd.forward(request, response);
                 } catch (SQLException ex) {
                     Logger.getLogger(GerenciarUsuarios.class.getName()).log(Level.SEVERE, null, ex);
                 }
             }
                
                
                
                
                RequestDispatcher rd = request.getRequestDispatcher("/usuarios.jsp");
             rd.forward(request,response);
            }
          }
         }
            
        }catch(Exception ex){
            Logger.getLogger(GerenciarUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         String acao = request.getParameter("acao");
        String acaobuscar = request.getParameter("acaobuscar");
        
        if (acao.equals("alterarDados")){
             try {
                this.alterarDados(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(GerenciarSalas.class.getName()).log(Level.SEVERE, null, ex);
            }
         }
        
        
        if(acaobuscar.equals("buscar")){
            try {
                this.buscarUsuarios(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(GerenciarUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
         
         // Cadastrar usuário
         if (acao.equals("confirmarDados")){
             try {
                this.confirmarDadosDoCadastro(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(GerenciarSalas.class.getName()).log(Level.SEVERE, null, ex);
            }
         }
        
    }

 
    private void confirmarDadosDoCadastro(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        //CRIANDO OBJETO DO TIPO PESSOA
        Pessoa pessoa = new Pessoa();
        String categoria = "";
        String tipoPegar = request.getParameter("categoriaUsuario");
        String nome = request.getParameter("nome");
        String matricula = request.getParameter("matricula");
        String cpf = request.getParameter("cpf");
        String rg = request.getParameter("rg");
        String telefone = request.getParameter("telefone");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String cSenha = request.getParameter("confirmarSenha");
        RequestDispatcher rr = null;
        pessoa.setMatricula(matricula);
        pessoa.setNome(nome);
        pessoa.setTelefone(telefone);
        pessoa.setEmail(email);
        pessoa.setRg(rg);
        pessoa.setCpf(cpf);
        pessoa.setSenha(senha);
        if(!tipoPegar.equals("0")){
             pessoa.setTipo(Integer.parseInt(tipoPegar));
        } else {
            request.setAttribute("mensagem", "Informe os campos corretamente!");
            rr = request.getRequestDispatcher("/usuarios.jsp");
                 rr.forward(request, response);
    }
        int tipoNum = Integer.parseInt(tipoPegar);
        if(tipoNum == 1){
            categoria = "Aluno";
            pessoa.setCategoria(categoria);
       } if(tipoNum == 2){
            categoria = "Professor";
            pessoa.setCategoria(categoria);
        } if(tipoNum == 3){
            categoria = "Técnico Administrativo";
            pessoa.setCategoria(categoria);
        }
        if(tipoNum == 1){
            if(cSenha.equals(senha)){
                int validade = 0;
                String matricula_form = matricula;
                try {
        PessoaDAO dao = new PessoaDAO();
        List<Pessoa> getAll = dao.getListaAlunos();
          for (Pessoa cont : getAll) {
            if(cont.getMatricula().equals(matricula_form)){
                validade++; 
            }
          }
        } catch (Exception e) {
        }
                String rg_form = rg;
        try {
        PessoaDAO dao = new PessoaDAO();
        List<Pessoa> getAll = dao.getListaAlunos();
          for (Pessoa cont : getAll) {
            if(cont.getRg().equals(rg_form)){
                validade++; 
            }
          }
        } catch (Exception e) {
        }
        String cpf_form = cpf;
        try {
        PessoaDAO dao = new PessoaDAO();
        List<Pessoa> getAll = dao.getListaAlunos();
          for (Pessoa cont : getAll) {
            if(cont.getCpf().equals(cpf_form)){
                validade++; 
            }
          }
        } catch (Exception e) {
        }
        if(validade > 0){
            // JAVASCRIPT AQUI 
            request.setAttribute("mensagem", "Usuário já cadastrado!");
            rr = request.getRequestDispatcher("/usuarios.jsp");
            rr.forward(request, response);
        } else {
            try{
            PessoaDAO dao = new PessoaDAO();
            dao.adicionarPessoa(pessoa);
        } catch (SQLException e){
            throw new ServletException(e);
        }
            // Através do response.getWriter() mostrar uma mensagem de ok para o usuário
        RequestDispatcher rd = request.getRequestDispatcher("/usuarios.jsp");
        rd.forward(request,response);
        return;
            }
        } else {
               request.setAttribute("mensagem", "Informe senha corretamente!");
            rr = request.getRequestDispatcher("/usuarios.jsp");
            rr.forward(request, response);
            }
    }
        if(tipoNum == 2){
            if(cSenha.equals(senha)){
                
                int validade = 0;
                String matricula_form = matricula;
                try {
        PessoaDAO dao = new PessoaDAO();
        List<Pessoa> getAll = dao.getListaProfessores();
          for (Pessoa cont : getAll) {
            if(cont.getMatricula().equals(matricula_form)){
                validade++; 
            }
          }
        } catch (Exception e) {
        }
                String rg_form = rg;
        try {
        PessoaDAO dao = new PessoaDAO();
        List<Pessoa> getAll = dao.getListaProfessores();
        
          for (Pessoa cont : getAll) {
            if(cont.getRg().equals(rg_form)){
                validade++; 
            }
          }
        } catch (Exception e) {
        }
        String cpf_form = cpf;
        try {
        PessoaDAO dao = new PessoaDAO();
        List<Pessoa> getAll = dao.getListaProfessores();
          for (Pessoa cont : getAll) {
            if(cont.getCpf().equals(cpf_form)){
                validade++; 
            }
          }
        } catch (Exception e) {
        }
        if(validade > 0){
           request.setAttribute("mensagem", "Usuário já cadastrado!");
            rr = request.getRequestDispatcher("/usuarios.jsp");
            rr.forward(request, response);
        } else {
            try{
            PessoaDAO dao = new PessoaDAO();
            dao.adicionarPessoa(pessoa);
        } catch (SQLException e){
            throw new ServletException(e);
        }
            // Através do response.getWriter() mostrar uma mensagem de ok para o usuário
        RequestDispatcher rd = request.getRequestDispatcher("/usuarios.jsp");
        rd.forward(request,response);
        return;
            }
        } else {
                request.setAttribute("mensagem", "Informe senha corretamente!");
            rr = request.getRequestDispatcher("/usuarios.jsp");
            rr.forward(request, response);
            }
    }
        if(tipoNum == 3){
            if(cSenha.equals(senha)){
                int validade = 0;
                String matricula_form = matricula;
                try {
        PessoaDAO dao = new PessoaDAO();
        List<Pessoa> getAll = dao.getListaTecnicosAdm();
          for (Pessoa cont : getAll) {
            if(cont.getMatricula().equals(matricula_form)){
                validade++; 
            }
          }
        } catch (Exception e) {
        }
                String rg_form = rg;
        try {
        PessoaDAO dao = new PessoaDAO();
        List<Pessoa> getAll = dao.getListaTecnicosAdm();
          for (Pessoa cont : getAll) {
            if(cont.getRg().equals(rg_form)){
                validade++; 
            }
          }
        } catch (Exception e) {
        }
        String cpf_form = cpf;
        try {
        PessoaDAO dao = new PessoaDAO();
        List<Pessoa> getAll = dao.getListaTecnicosAdm();
          for (Pessoa cont : getAll) {
            if(cont.getCpf().equals(cpf_form)){
                validade++; 
            }
          }
        } catch (Exception e) {
        }
        if(validade > 0){
            request.setAttribute("mensagem", "Usuário já cadastrado!");
            rr = request.getRequestDispatcher("/usuarios.jsp");
            rr.forward(request, response);
           
        } else {
            try{
            PessoaDAO dao = new PessoaDAO();
            dao.adicionarPessoa(pessoa);
        } catch (SQLException e){
            throw new ServletException(e);
        }
            // Através do response.getWriter() mostrar uma mensagem de ok para o usuário
        RequestDispatcher rd = request.getRequestDispatcher("/usuarios.jsp");
        rd.forward(request,response);
        return;
            }
        } else {
                request.setAttribute("mensagem", "Informe senha corretamente!");
            rr = request.getRequestDispatcher("/usuarios.jsp");
            rr.forward(request, response);
            }
    }
}
       
  private void alterarDados(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
      String idpessoa = request.getParameter("idpessoa");
               String nome = request.getParameter("nome");
               String matricula = request.getParameter("matricula");
               String rg = request.getParameter("rg");
               String cpf = request.getParameter("cpf");
               String telefone = request.getParameter("telefone");
               String email = request.getParameter("email");
               Pessoa pessoa = new Pessoa();
               pessoa.setNome(nome);
               pessoa.setMatricula(matricula);
               pessoa.setRg(rg);
               pessoa.setCpf(cpf);
               pessoa.setTelefone(telefone);
               pessoa.setEmail(email);
               pessoa.setIdpessoa(Integer.parseInt(idpessoa));
               PessoaDAO dao = new PessoaDAO();
               dao.alteraPessoa(pessoa);
               RequestDispatcher rd = request.getRequestDispatcher("usuarios.jsp");
               rd.forward(request, response);
  }
  
  
    private void buscarUsuarios(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
    String tipo = request.getParameter("categoriaBusca");
    String nome = request.getParameter("nomeBusca");
    String matricula = request.getParameter("matriculaBusca");
    
    //Se o NOME e a MATRÍCULA estiverem em BRANCO!
    if(nome.equals("") && matricula.equals("")){
      
        if(tipo.equals("0")){
            PessoaDAO pessoaDAO = new PessoaDAO();
            try{
                List listaPessoas = pessoaDAO.getLista();
                request.setAttribute("sessaoListaPessoas", listaPessoas);
                RequestDispatcher rd = request.getRequestDispatcher("/usuarios.jsp");
                rd.forward(request, response);
            } catch (Exception ex){
                Logger.getLogger(GerenciarUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    }        
}
