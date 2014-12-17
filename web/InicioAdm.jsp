

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags/" prefix="tagsUsuario" %>
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
                        <li  class="selected"><a href="#"><strong>Início</strong></a>
                        <li><a href="GerenciarUsuarios?acao=listar"><strong>Usuários</strong></a></li>
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
                    <h2> Bem-vindo(a) ao SigSol, <tagsUsuario:UsuarioLogado/>!</h2>
                    <br><br>

                    <h1>O SigSol propõe uma melhor execução das tarefas e facilidade no processo de solicitações feitas
                        pelas pessoas que utilizam esse setor do Apoio Acadêmico do IFRN campus João Câmara.</h1>
                </div>
            </section>

        </section>
    </body>
</html>
