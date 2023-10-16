package br.com.masters3.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import br.com.masters3.beans.Cidade;
import br.com.masters3.beans.Usuario;


//import java.time.format.DateTimeFormatter;

public class UsuarioDAO implements DAO<Usuario>{

	private DataSource dataSource;
	
	public UsuarioDAO(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public DataSource getDataSource() {
		return this.dataSource;
	}
	
	/* --- CREATE method--- */
	@Override
	public void create(Usuario object) {
	   try{
// declare a STRING SQL correspond the command
// replace values to character “?”
		 String SQL = "insert into TB_TIN_USUARIO (nivel, genero, email, senha, nome, nascimento, ultimo_acesso, foto, id_cidade) values (?,?,?,?,?,?,?,?,?) ";
// create a Statement from String
		PreparedStatement stm = 
      				   dataSource.getConnection()
  .prepareStatement(SQL);
// fill with object values
		//stm.setString(1, object.getNivel());
		stm.setString(2, object.getGenero());
		stm.setString(3, object.getEmail());
		//stm.setString(4, object.getSenha());
		stm.setString(5, object.getNome());
		//stm.setString(6, object.getNascimento().format(DateTimeFormatter.ofPattern("DD/MM/YYYY")));
		//stm.setString(7, object.getUltimoAcesso().format(DateTimeFormatter.ofPattern("DD/MM/YYYY")));
		//stm.setString(8, object.getFoto());
		//stm.setInt(9, object.getCidade().getId());
		stm.setInt(10,  object.getId());
		
// execute table update operation
         int res = stm.executeUpdate();
	// if right, variable RES return != 0
		if (res != 0) {
			System.out.println("Usuario alterado com sucesso");
		}
		else {
			throw new RuntimeException("Erro ao inserir usuario ");
		}
	   }
	   catch (Exception ex) {
		  System.out.println("UsuarioDAO.CREATE = "+
                            ex.getMessage());
	   }
		
	}

    /* --- READ method --- */
	@Override
	public Usuario read(Usuario object) {
		// TODO Auto-generated method stub
		try {
// declare a STRING SQL correspond to the command
// replace values to character “?”
			String SQL = "select * from TB_TIN_USUARIO inner join TB_TIN_CIDADE on TB_TIN_USUARIO.ID_CIDADE = TB_TIN_CIDADE. where email = ? and senha = ?";
// create o Statement a from the connection
			PreparedStatement stm = 
  					dataSource.getConnection()
         .prepareStatement(SQL);

// fill just with email and password attributes 
			stm.setString(1, object.getEmail());
			//stm.setString(2, object.getSenha());

// here the result is storage at object ResultSet
			ResultSet rs = stm.executeQuery();

// the next() method indicates if there is register on result
// if there is, I fill the object
			if (rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(rs.getInt("ID"));
				usuario.setNome(rs.getString("NOME"));
				//usuario.setNivel(rs.getString("NIVEL"));
				usuario.setGenero(rs.getString("GENERO"));
	 		     usuario.setEmail(rs.getString("EMAIL"));
	 		    //usuario.setSenha(rs.getString("SENHA"));
				//usuario.setNascimento(rs.getDate("NASCIMENTO").toLocalDate());
				//usuario.setUltimoAcesso(rs.getDate("ULTIMO_ACESSO").toLocalDate());
				//usuario.setFoto(rs.getString("FOTO"));
				
				Cidade cidade = new Cidade();
				cidade.setId(rs.getInt("ID_CIDADE"));
				cidade.setCidade(rs.getString("CIDADE"));
				cidade.setEstado(rs.getString("ESTADO"));
				cidade.setSiglaEstado(rs.getString("SIGLA_ESTADO"));
				cidade.setPais(rs.getString("PAIS"));
				cidade.setSiglaPais(rs.getString("SIGLA_PAIS"));
			//	usuario.setCidade(CIDADE);
				return usuario;
			}
			else {
				return null;
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("UsuarioDAO.READ = "+
                    ex.getMessage());
		}
		return null;
	}

    /* --- UPDATE Method--- */
	@Override
	public void update(Usuario object) {
		try {
// declare a STRING SQL correspond to the command
// replace values to the character “?”
			String SQL = "update TB_TIN_USUARIO set nivel = ?, genero = ?, email = ?, senha = ?, nome = ?, nascimento = ?, ultimo_acesso = ?, foto = ?, id_cidade =  where id = ?";
// create a Statement from the connection			
 PreparedStatement stm = dataSource.getConnection().prepareStatement(SQL);

     //fill with object data
			//stm.setString(1, object.getNivel());
			stm.setString(2, object.getGenero());
			stm.setString(3, object.getEmail());
			//stm.setString(4, object.getSenha());
			stm.setString(5, object.getNome());
			//stm.setString(6,object.getNascimento().format(DateTimeFormatter.ofPattern("dd/MM/YYYY")));
			//stm.setString(7, object.getUltimoAcesso().format(DateTimeFormatter.ofPattern("dd/MM/YYYY")));
			//stm.setString(8, object.getFoto());
			//stm.setInt(9, object.getCidade().getId());
			stm.setInt(10,  object.getId());
			
// execute the statements to update the table
			int res = stm.executeUpdate();
			if (res != 0) {
				System.out.println("Usuario alterado com sucesso");
			}
			else {
				throw new RuntimeException("Erro ao atualizar usuario ");
			}
		}
		catch(Exception ex) {
			System.out.println("UsuarioDAO.UPDATE ="+
ex.getMessage());
		}
		
	}

    /* --- DELETE method --- */
	@Override
	public void delete(Usuario object) {
		try {
			// defined our statement SQL
			String SQL = "delete from TB_TIN_USUARIO where ID = ?";
			PreparedStatement stm =
     				dataSource.getConnection()
                       .prepareStatement(SQL);
			stm.setInt(1, object.getId());
int res = stm.executeUpdate();
			if (res != 0) {
				System.out.println("Usuario excluido com sucesso");
			}
			else {
				throw new RuntimeException("ERRO ao apagar usuario");
			}
		}
		catch (Exception ex) {
			System.out.println("UsuarioDAO.DELETE = "+
                             ex.getMessage());
		}
	}
}