

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="br.edu.ifrn.jdbc.modelo.Pessoa"%>
<%@page import="java.util.ArrayList"%>
<%@page import="br.edu.ifrn.jdbc.dao.PessoaDAO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags/" prefix="tagsUsuario" %>
<%@taglib tagdir="/WEB-INF/tags/" prefix="tagsAviso" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SigSol</title>
        <link type="text/css" rel="stylesheet" href="css/folha_de_estilo.css">
    </head>
    <body id="home" class="big">
        <section id="section">
            <p id="tel">
                <strong><tagsUsuario:UsuarioLogado/></strong>

                <a href="Sair" id="tt">(Sair)</a>
            </p>
            <br>

            <section id="wrapper2">
                <div id="imagensLogo" align="center"> <img id="logo" alt="Sistema de Gerenciamento de Solicitações" align="center" src="imagens/cccaaaa.png" width="800" height="150"></div>
            </section>

            <div id="wrapper">
                <div id="navMenu">

                    <ul id="c">
                        <li><a href="InicioAdm.jsp"><strong>Início</strong></a>
                        <li class="selected"><a href="#"><strong>Usuários</strong></a></li>
                        <li><a href="#"><strong>Gerenciar</strong></a>
                            <ul>
                                <li><a href="GerenciarMateriais?acao=listar"><strong>Material</strong></a></li>
                                <li><a href="#"><strong>Sala</strong></a></li>
                                <li><a href="#"><strong>Livro</strong></a></li>
                                <li><a href="#"><strong>Horários</strong></a></li>

                            </ul>
                        </li>
                        <li><a href="#"><strong>Cadastrar</strong></a>
                            <ul>
                                <li><a href="cadastrarUsuario.jsp"><strong>Usuário</strong></a></li>
                                <li><a href="gerenciar_materialEscritorio.jsp"><strong>Material</strong></a></li>
                                <li><a href="gerenciar_salas.jsp"><strong>Sala</strong></a></li>
                                <li><a href="gerenciar_livros.jsp"><strong>Livro</strong></a></li>
                                <li><a href="gerenciarHorarios.jsp"><strong>Horários</strong></a></li>

                            </ul>
                        </li>
                        <li><a href=""><strong>Relatório</strong></a>
                            <ul>
                                <li><a><strong>Individual</strong></a></li>
                                <li><a><strong>Geral</strong></a></li>
                                <li><a><strong>Avaliação de serviço</strong></a></li>
                            </ul>
                        </li>
                        <li><a href="AvaliacaoDeServico.jsp"><strong>Avaliar</strong></a></li>
                        <li><a href="visualizarHorarios.jsp"><strong>Horários</strong></a></li>
                    </ul>

                </div>
            </div>
            <section id="menu">
                <div id="espaco">

                    <%-- FORMULÁRIO PARA CADASTRAR USUÁRIO --%>

                    <div id="divForm">
                        <form name="formulario" action="cadastrarUsuario" method="POST">

                            <h2 id="a1">Usuários</h2>
                            <h3 id="a2">Cadastre, visualize, modifique e exclua usuários do SigSol</h3>


                            <fieldset>

                                <legend>Novo usuário</legend>

                                <table id="tabelaUsuarios">

                                    <tr><td>
                                            Categoria:
                                        </td>
                                        <td width="100" height="10">
                                        </td>
                                        <td>
                                            <p id="aviso"><strong><tagsAviso:statusInformarCategoria/></strong></p>

                                        </td>
                                        <td rowspan="10">
                                            <img src="imagens/newuser2.png" width="300" height="170">
                                        </td>
                                    </tr>

                                    <tr><td>
                                            <select name="categoriaUsuario" id="categoriaUsuario" >
                                                <option value="0" selected>(Selecione)</option>
                                                <option value="1">Aluno</option>
                                                <option value="2">Professor</option>
                                                <option value="3">Técnico administrativo</option>
                                            </select>
                                        </td>
                                        <td width="100" height="10">
                                        </td>
                                        <td></td>
                                    </tr>

                                    <tr><td>
                                            Nome: 
                                        </td>
                                        <td width="100" height="10">
                                        </td>
                                        <td>
                                            Matrícula:
                                        </td>
                                    </tr>

                                    <tr><td>
                                            <input type="text" name="nome" required size="35"/>
                                        </td>
                                        <td width="100" height="10">
                                        </td>
                                        <td>
                                            <input type="text" name="matricula" maxlength="14" required size="35"/>
                                        </td>
                                    </tr>

                                    <tr><td>
                                            CPF:
                                        </td>
                                        <td width="100" height="10">
                                        </td>
                                        <td>
                                            RG:
                                        </td>
                                    </tr>

                                    <tr><td>
                                            <input type="text" name="cpf" onBlur="ValidarCPF(formulario.cpf);" onKeyPress="MascaraCPF(formulario.cpf);" maxlength="14" required size="35">
                                        </td>
                                        <td width="100" height="10">
                                        </td>
                                        <td>
                                            <input type="text" name="rg" onBlur="ValidarRG(formulario.rg);" onKeyPress="MascaraRG(formulario.rg);" maxlength="14" required size="35">
                                        </td>
                                    </tr>

                                    <tr><td>
                                            Telefone:
                                        </td>
                                        <td width="100" height="10">
                                        </td>
                                        <td>
                                            E-mail:
                                        </td>
                                    </tr>
                                    <tr><td>
                                            <input type="text" name="telefone" onKeyPress="MascaraTelefone(formulario.telefone);" maxlength="14"  onBlur="ValidaTelefone(formulario.telefone);" required size="35">
                                        </td>
                                        <td width="100" height="10">
                                        </td>
                                        <td>
                                            <input type="email" name="email" required size="35"/>
                                        </td>
                                    </tr>

                                    <tr><td>
                                            Senha:
                                        </td>
                                        <td width="100" height="10">
                                        </td>
                                        <td>
                                            Confirmar senha:
                                        </td>
                                    </tr>
                                    <tr><td>
                                            <input type="password" name="senha" required size="35"/> 
                                        </td>
                                        <td width="100" height="10">
                                        </td>
                                        <td>
                                            <input type="password" name="confirmarSenha" required size="35"/> 
                                        </td>
                                    </tr>

                                </table>
                                <div id="botao"><br><br>
                                    <input type="hidden" name="acao" value="confirmarDados"/>
                                    <input type="submit" name="cadastrar" value="Cadastrar"/>
                                    <input type="reset" name="cadastrar" value="Limpar"/>
                                </div>
                            </fieldset>
                            <br><br>

                        </form>
                        <hr><br>
                        <h3 id="a2">Exibir usuários</h3>
                        <form name="formulario2" action="buscarUsuario" method="POST">
                            <table>
                                <tr>
                                    <td>Categoria</td>
                                    <td>

                                        <select name="categoriaBusca" id="categoriaBusca">
                                            <option value="0">(Todos)</option>
                                            <option value="1">Aluno</option>
                                            <option value="2">Professor</option>
                                            <option value="3">Técnico administrativo</option>
                                        </select>

                                    </td>
                                </tr>


                                <tr>
                                    <td width="100">Nome: </td>
                                    <td>
                                        <input type="text" name="nomeBusca" size="40"/>
                                    </td>
                                </tr>

                                <tr>
                                    <td>Matrícula: </td>
                                    <td>
                                        <input type="text" name="matriculaBusca" size="40"/>
                                    </td>
                                </tr>
                            </table>
                            <input type="hidden" name="acaobuscar" value="buscar"/>
                            <input type="submit" name="buscar" value="Buscar"/>
                        </form>
                                

                        <br><br>
                        <table border="1">
                            <tr>

                                <th>
                                    Nome
                                </th>

                                <th>
                                    Matrícula
                                </th>
                                <th>
                                    Categoria
                                </th>
                                <th>
                                    CPF
                                </th>
                                <th>
                                    RG
                                </th>
                                <th>
                                    Telefone
                                </th>
                                <th>
                                    E-mail
                                </th>
                                <th colspan="2">
                                    Ação
                                </th>


                            </tr>
                            <c:forEach var="registroUsuarios" items="${sessaoListaPessoas}">
                                <tr>


                                    <td>
                                        ${registroUsuarios['nome']}
                                    </td>
                                    <td>
                                        ${registroUsuarios['matricula']}
                                    </td>
                                    <td>
                                        ${registroUsuarios['categoria']}
                                    </td>
                                    <td>
                                        ${registroUsuarios['cpf']}
                                    </td>
                                    <td>
                                        ${registroUsuarios['rg']}
                                    </td>
                                    <td>
                                        ${registroUsuarios['telefone']}
                                    </td>
                                    <td>
                                        ${registroUsuarios['email']}
                                    </td>
                                    <td>
                                        <a href="alteraUsuario.jsp?idpessoa=${registroUsuarios['idpessoa']}&matricula=${registroUsuarios['matricula']}&nome=${registroUsuarios['nome']}&cpf=${registroUsuarios['cpf']}&rg=${registroUsuarios['rg']}&telefone=${registroUsuarios['telefone']}&email=${registroUsuarios['email']}"> <img src="imagens/editar.png" width="20" height="20" alt="editar"></a>
                                    </td>
                                    <td>
                                        <a href="GerenciarUsuarios?acao=excluirusuario&matricula=${registroUsuarios['matricula']}"><img src="imagens/delete.png" width="20" height="20" alt="deletar"/></a>
                                    </td>

                                                                        
                                </tr>
                            </c:forEach>
                        </table>
                    </div>

            </section>

        </section>
    </body>
</html>
