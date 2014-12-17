/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrn.jdbc.servlets;

import br.edu.ifrn.jdbc.dao.PessoaDAO;
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
public class buscarUsuario extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String acaobuscar = request.getParameter("acaobuscar");

        if (acaobuscar.equals("buscar")) {
            try {
                this.buscarUsuarios(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(GerenciarUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    private void buscarUsuarios(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        String tipo = request.getParameter("categoriaBusca");
        String nome = request.getParameter("nomeBusca");
        String matricula = request.getParameter("matriculaBusca");

        //Se os campos NOME e MATRÍCULA estiverem vazios.
        if (nome.equals("") && matricula.equals("")) {
            //TODOS
            if (tipo.equals("0")) {
                PessoaDAO pessoaDAO = new PessoaDAO();
                try {
                    List listaPessoas = pessoaDAO.getLista();
                    request.setAttribute("sessaoListaPessoas", listaPessoas);
                    RequestDispatcher rd = request.getRequestDispatcher("/usuarios.jsp");
                    rd.forward(request, response);
                } catch (Exception ex) {
                    Logger.getLogger(GerenciarUsuarios.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            //ALUNOS
            if (tipo.equals("1")) {
                PessoaDAO pessoaDAO = new PessoaDAO();
                try {
                    List listaPessoas = pessoaDAO.getListaAlunos();
                    request.setAttribute("sessaoListaPessoas", listaPessoas);
                    RequestDispatcher rd = request.getRequestDispatcher("/usuarios.jsp");
                    rd.forward(request, response);
                } catch (Exception ex) {
                    Logger.getLogger(GerenciarUsuarios.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            //PROFESSORES
            if (tipo.equals("2")) {
                PessoaDAO pessoaDAO = new PessoaDAO();
                try {
                    List listaPessoas = pessoaDAO.getListaProfessores();
                    request.setAttribute("sessaoListaPessoas", listaPessoas);
                    RequestDispatcher rd = request.getRequestDispatcher("/usuarios.jsp");
                    rd.forward(request, response);
                } catch (Exception ex) {
                    Logger.getLogger(GerenciarUsuarios.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            //TÉCNICOS ADMINISTRATIVOS
            if (tipo.equals("3")) {
                PessoaDAO pessoaDAO = new PessoaDAO();
                try {
                    List listaPessoas = pessoaDAO.getListaTecnicosAdm();
                    request.setAttribute("sessaoListaPessoas", listaPessoas);
                    RequestDispatcher rd = request.getRequestDispatcher("/usuarios.jsp");
                    rd.forward(request, response);
                } catch (Exception ex) {
                    Logger.getLogger(GerenciarUsuarios.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }

        if (tipo.equals("0")) {
            if (matricula.equals("")) {
                PessoaDAO pessoaDAO = new PessoaDAO();
                try {
                    List listaPessoas = pessoaDAO.pesquisarNomeTodos(nome);
                    request.setAttribute("sessaoListaPessoas", listaPessoas);
                    RequestDispatcher rd = request.getRequestDispatcher("/usuarios.jsp");
                    rd.forward(request, response);
                } catch (Exception ex) {
                    Logger.getLogger(GerenciarUsuarios.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (nome.equals("")) {
                PessoaDAO pessoaDAO = new PessoaDAO();
                try {
                    List listaPessoas = pessoaDAO.pesquisarMatriculaTodos(matricula);
                    request.setAttribute("sessaoListaPessoas", listaPessoas);
                    RequestDispatcher rd = request.getRequestDispatcher("/usuarios.jsp");
                    rd.forward(request, response);
                } catch (Exception ex) {
                    Logger.getLogger(GerenciarUsuarios.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if ((!nome.equals("")) && (!matricula.equals(""))) {
                PessoaDAO pessoaDAO = new PessoaDAO();
                try {
                    List listaPessoas = pessoaDAO.pesquisarTodos(matricula, nome);
                    request.setAttribute("sessaoListaPessoas", listaPessoas);
                    RequestDispatcher rd = request.getRequestDispatcher("/usuarios.jsp");
                    rd.forward(request, response);
                } catch (Exception ex) {
                    Logger.getLogger(GerenciarUsuarios.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            //TIPO = 1 (ALUNO)
            if (tipo.equals("1")) {
                if (matricula.equals("")) {
                    PessoaDAO pessoaDAO = new PessoaDAO();
                    try {
                        List listaPessoas = pessoaDAO.pesquisarNomeAluno(nome);
                        request.setAttribute("sessaoListaPessoas", listaPessoas);
                        RequestDispatcher rd = request.getRequestDispatcher("/usuarios.jsp");
                        rd.forward(request, response);
                    } catch (Exception ex) {
                        Logger.getLogger(GerenciarUsuarios.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (nome.equals("")) {
                    PessoaDAO pessoaDAO = new PessoaDAO();
                    try {
                        List listaPessoas = pessoaDAO.pesquisarMatriculaAluno(matricula);
                        request.setAttribute("sessaoListaPessoas", listaPessoas);
                        RequestDispatcher rd = request.getRequestDispatcher("/usuarios.jsp");
                        rd.forward(request, response);
                    } catch (Exception ex) {
                        Logger.getLogger(GerenciarUsuarios.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if ((!nome.equals("")) && (!matricula.equals(""))) {
                    PessoaDAO pessoaDAO = new PessoaDAO();
                    try {
                        List listaPessoas = pessoaDAO.pesquisarAluno(matricula, nome);
                        request.setAttribute("sessaoListaPessoas", listaPessoas);
                        RequestDispatcher rd = request.getRequestDispatcher("/usuarios.jsp");
                        rd.forward(request, response);
                    } catch (Exception ex) {
                        Logger.getLogger(GerenciarUsuarios.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else {
                //PROFESSOR
                if (tipo.equals("2")) {
                    if (matricula.equals("")) {
                        PessoaDAO pessoaDAO = new PessoaDAO();
                        try {
                            List listaPessoas = pessoaDAO.pesquisarNomeProfessor(nome);
                            request.setAttribute("sessaoListaPessoas", listaPessoas);
                            RequestDispatcher rd = request.getRequestDispatcher("/usuarios.jsp");
                            rd.forward(request, response);
                        } catch (Exception ex) {
                            Logger.getLogger(GerenciarUsuarios.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                   if (nome.equals("")) {
                    PessoaDAO pessoaDAO = new PessoaDAO();
                    try {
                        List listaPessoas = pessoaDAO.pesquisarMatriculaProfessor(matricula);
                        request.setAttribute("sessaoListaPessoas", listaPessoas);
                        RequestDispatcher rd = request.getRequestDispatcher("/usuarios.jsp");
                        rd.forward(request, response);
                    } catch (Exception ex) {
                        Logger.getLogger(GerenciarUsuarios.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } 
                   if ((!nome.equals("")) && (!matricula.equals(""))) {
                    PessoaDAO pessoaDAO = new PessoaDAO();
                    try {
                        List listaPessoas = pessoaDAO.pesquisarProfessor(matricula, nome);
                        request.setAttribute("sessaoListaPessoas", listaPessoas);
                        RequestDispatcher rd = request.getRequestDispatcher("/usuarios.jsp");
                        rd.forward(request, response);
                    } catch (Exception ex) {
                        Logger.getLogger(GerenciarUsuarios.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                } else {
                    //TÉCNICOS ADM
                    if(tipo.equals("3")){
                        if (matricula.equals("")) {
                        PessoaDAO pessoaDAO = new PessoaDAO();
                        try {
                            List listaPessoas = pessoaDAO.pesquisarNomeTecAdm(nome);
                            request.setAttribute("sessaoListaPessoas", listaPessoas);
                            RequestDispatcher rd = request.getRequestDispatcher("/usuarios.jsp");
                            rd.forward(request, response);
                        } catch (Exception ex) {
                            Logger.getLogger(GerenciarUsuarios.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                      if (nome.equals("")) {
                    PessoaDAO pessoaDAO = new PessoaDAO();
                    try {
                        List listaPessoas = pessoaDAO.pesquisarMatriculaTecAdm(matricula);
                        request.setAttribute("sessaoListaPessoas", listaPessoas);
                        RequestDispatcher rd = request.getRequestDispatcher("/usuarios.jsp");
                        rd.forward(request, response);
                    } catch (Exception ex) {
                        Logger.getLogger(GerenciarUsuarios.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }  
                      if ((!nome.equals("")) && (!matricula.equals(""))) {
                    PessoaDAO pessoaDAO = new PessoaDAO();
                    try {
                        List listaPessoas = pessoaDAO.pesquisarTecAdm(matricula, nome);
                        request.setAttribute("sessaoListaPessoas", listaPessoas);
                        RequestDispatcher rd = request.getRequestDispatcher("/usuarios.jsp");
                        rd.forward(request, response);
                    } catch (Exception ex) {
                        Logger.getLogger(GerenciarUsuarios.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                    }
                }
                
                
            }
        }

    }

}
