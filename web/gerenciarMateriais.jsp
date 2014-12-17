

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@taglib tagdir="/WEB-INF/tags/" prefix="tagsAviso" %>
        <%@taglib tagdir="/WEB-INF/tags/" prefix="tagsUsuario" %>

        <title>SigSol</title>
        <link type="text/css" rel="stylesheet" href="css/folha_de_estilo.css">
    </head>
    <body id="home" class="big">
        <section id="section">
            <p id="tel">
                <strong> <tagsUsuario:UsuarioLogado/></strong>

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
                        <li><a href="GerenciarUsuarios?acao=listar"><strong>Usuários</strong></a></li>
                        <li class="selected"><a href="#"><strong>Gerenciar</strong></a>
                            <ul>
                                <li><a href="#"><strong>Material</strong></a></li>
                                <li><a href="#"><strong>Sala</strong></a></li>
                                <li><a href="#"><strong>Livro</strong></a></li>
                                <li><a href="#"><strong>Horários</strong></a></li>

                            </ul>
                        </li>
                        <li><a href="#"><strong>Gerenciar</strong></a>
                            <ul>
                                <li><a href="#"><strong>Material</strong></a></li>
                                <li><a href="gerenciarSala.jsp"><strong>Sala</strong></a></li>
                                <li><a href="gerenciarLivro.jsp"><strong>Livro</strong></a></li>
                                <li><a href="gerenciarHorarios.jsp"><strong>Horários</strong></a></li>

                            </ul>
                        </li>
                        <li><a href=""><strong>Relatório</strong></a>
                            <ul>
                                <li><a href="relatorio_individual.jsp"><strong>Individual</strong></a></li>
                                <li><a href="relatorio_geral.jsp"><strong>Geral</strong></a></li>

                            </ul>
                        </li>
                        <li><a href="#"><strong>Solicitações</strong></a>
                            <ul>
                                <li><a href="solicitar_materialEscritorio.jsp"><strong>Material escritório</strong></a></li>
                                <li><a href="solicitar_materialVideoaula.jsp"><strong>Material videoaula</strong></a></li>
                                <li><a href="solicitar_sala.jsp"><strong>Salas</strong></a></li>
                                <li><a href="solicitar_livro.jsp"><strong>Livros</strong></a></li>
                                <li><a href="solicitar_objetos.jsp"><strong>Objetos perdidos</strong></a></li>
                                <li><a href="solicitar_limpeza.jsp"><strong>Limpeza</strong></a></li>

                            </ul>
                        </li>
                        <li><a href="horarios.jsp"><strong>Horários</strong></a></li>
                    </ul>

                </div>
            </div>
            <section id="menu">
                <div id="espaco">

                    <h2 id="a1">Gerenciar materiais</h2>
                    <table border="0">
                        <tr>
                            
                        <td><h3 id="a2">Faça solicitação de objetos perdidos dentro do IFRN</h3></td>
                        <td width="10%"></td>
                        <td>
                            <p id="aviso"><strong><tagsAviso:statusInformarCategoria/></strong></p>
                        </td>
                        </tr>
                    </table>
                    <table border="0">
                        <tr>
                            <td colspan="5" width="0.5%" id="colunatb"></td>
                        </tr>
                        <tr>
                            <td width="45%">
                                <h1>Cadastrar material de escritório</h1> 
                                <br>
                                <form action="cadastrarMaterial" method="post">
                                    <div id="formm">
                                        <table>
                                            <tr>
                                                <td>Nome: </td>
                                                <td>
                                                    <input type="text" required size="40" name="nome_escritorio">
                                                </td>
                                            </tr>

                                            <br>
                                        </table>
                                        <br>
                                        <div align="center">
                                            <input type="hidden" name="acao" value="confirmar_escritorio"/>
                                            <input type="submit" value="Cadastrar">
                                            <input type="reset" value="Limpar">
                                        </div>
                                    </div> 
                                </form>



                            </td>
                            <td width="5%">
                            </td>
                            <td width="0.5%" id="colunatb" rowspan="2">
                            </td>
                            <td width="5%">
                            </td>

                            <td width="50%">

                                <h1>Cadastrar material para videoaula</h1>
                                <br>
                                <form action="gerenciarMaterial.jsp" method="post">
                                    <div id="formm">
                                        <table>
                                            <tr></tr>
                                            <tr>
                                                <td>Nome: </td>
                                                <td>
                                                    <input type="text" size="40" required>
                                                </td>
                                            </tr>

                                        </table>
                                        <br>
                                        <br>
                                        <div align="center">
                                            <input type="submit" value="Cadastrar">
                                            <input type="reset" value="Limpar">
                                        </div>
                                    </div> 
                                </form>

                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">

                                <h1>Materiais de escritório</h1>
                                <br>
                                <table border="1" width="95%">
                                    <tr>
                                        <th>Material</th>
                                        <th colspan="2">Ação</th>
                                    </tr>
                                    
                                    <c:forEach var="registroEscritorio" items="${sessaoListaEscritorio}">
                                        <tr>
                                            <td>
                                                ${registroEscritorio['nome_material']}
                                            </td>
                                            <td>
                                        <a href="alteraMateriais.jsp?idmaterial=${registroEscritorio['idmaterial']}&nome_material=${registroEscritorio['nome_material']}"> <img src="imagens/editar.png" width="20" height="20" alt="editar"></a>
                                    </td>
                                    <td>
                                        <a href="#"><img src="imagens/delete.png" width="20" height="20" alt="deletar"/></a>
                                    </td>
                                        </tr>
                                    </c:forEach>

                                </table>
                            </td>
                            <td></td>
                            <td colspan="2" valign="top">

                                <h1>Materiais de escritório</h1>
                                <br>
                                <table border="1" width="95%">
                                    <tr>
                                        <th>Material</th>
                                        <th colspan="2">Ação</th>
                                    </tr>
                                    <tr>
                                        <td>
                                            Material
                                        </td>
                                        <td width="3%">
                                            <a href="editarMaterial.jsp"><img src="imagens/editar.png" width="20" height="20" alt="editar"/></a>
                                        </td>
                                        <td width="3%">
                                            <a href="#"><img src="imagens/delete.png" width="20" height="20" alt="editar"/></a>
                                        </td>
                                    </tr>


                                </table>
                            </td>

                        </tr>



                    </table>










                </div>
            </section>

        </section>

    </body>

</html>
