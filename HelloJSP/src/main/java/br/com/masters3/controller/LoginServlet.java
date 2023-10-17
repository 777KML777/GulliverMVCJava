package br.com.masters3.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.com.masters3.beans.Usuario;
import br.com.masters3.dao.DataSource;
import br.com.masters3.dao.UsuarioDAO;

@WebServlet("/login")
public class LoginServlet extends HttpServlet{
	
	public void doPost(HttpServletRequest request,
                       HttpServletResponse response) {
		String pagina="";
		try {
// CRIO A CONEXAO COM O BANCO
			DataSource dataSource = new DataSource();
// PASSO O DATASOURCE PARA O DAO
			UsuarioDAO usuarioDao = 
                           new UsuarioDAO(dataSource);
// AGORA VAMOS CRIAR UM USUÁRIO PARA  RECUPERAR OS 
// DADOS DO FORM VIA REQUEST
			Usuario userLogin = new Usuario();
			userLogin.setEmail(
request.getParameter("email"));
			userLogin.setSenha(
request.getParameter("senha"));
			Usuario user = usuarioDao.read(userLogin);
			if (user != null) {
// AQUI UM SEGREDO IMPORTANTÍSSIMO MANTER O 
//  USUÁRIO CONECTADO!
				request.getSession()
   .setAttribute("Usuario", user);
				pagina = "/dados_usuario.jsp";
			}
			else {
				request.setAttribute("ErroMSG", 
                         "Usuario nao encontrado");
				pagina = "/erro.jsp";
			}

// SÓ NOS RESTA REDIRECIONAR PARA A PÁGINA RESULTANTE
			RequestDispatcher dispatcher = 
    getServletContext()
   .getRequestDispatcher(pagina);
			dispatcher.forward(request, response);
		}
		catch(Exception ex) {
			System.out.println("ERRO on LOGINSERVLET "+
                             ex.getMessage());
		}
	}
}