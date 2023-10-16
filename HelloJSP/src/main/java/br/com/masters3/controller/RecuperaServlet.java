package br.com.masters3.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.masters3.beans.Veiculo;

//PARA RODAR ESSE CÓDIGO, ADICIONE NO FINAL DA URL /RECUPERA.DO?ID=1
//ISSO IRÁ FAZER A BUSCA PELO ID 1
@WebServlet("/recupera.do")
public class RecuperaServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException
	{
		
		//BANCO
		ArrayList<Veiculo> bd = new ArrayList<Veiculo>();
		
		//ADICIONAR O BANCO AO ARRAY BD PARA QUE O CÓDIGO FUNCIONE
		
		//BUSCANDO ID
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		//LÓGICA PARA VERIFICAR SE ENCONTROU OU NÃO O ID
		
		Veiculo result = null;
		for(Veiculo car : bd) {
			if(car.getId() == id)
			{
				result = car;
				break;
			}
		}
		
		//PREPARANDO O RETORNO PARA O SERVIDOR
		String paginaDestino;
		
		if(result != null) {
			request.setAttribute("Veiculo", result);
			paginaDestino = "/info.jsp";
		}
		else {
			paginaDestino = "/erro.jsp";
		}
		
		
		//RETORNA A CHAMADA HTTP PARA O SERVER
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(paginaDestino);
		dispatcher.forward(request, response);
	}
}
