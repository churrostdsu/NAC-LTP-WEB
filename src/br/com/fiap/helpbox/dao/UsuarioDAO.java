package br.com.fiap.helpbox.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import oracle.sql.DATE;
import br.com.fiap.helpbox.beans.Objeto;
import br.com.fiap.helpbox.beans.Usuario;
import br.com.fiap.helpbox.conexao.ConexaoFactory;

public class UsuarioDAO {

	private Connection conexao;

	public UsuarioDAO() throws Exception{
		this.conexao = new ConexaoFactory().getConnection();
	}


		// CRUD + List
	public int acrescentadorID() throws Exception{
		String sql = "SELECT cd_usuario FROM T_HB_USUARIO";
		PreparedStatement estrutura = conexao.prepareStatement(sql);
		ResultSet resultadoCod = estrutura.executeQuery();
		int cod = 0;
		Usuario user= new Usuario();
		
		if(resultadoCod.next()){
			 user.setCodigoUsuario(resultadoCod.getInt("cd_usuario"));
			cod = user.getCodigoUsuario();
			return cod = cod++;
		}
		return cod;
	}
	
	// CRUD (Create)
	public boolean addUsuario(String nome,String sobrenome,int cpf,String rg,String dataNasc,String endereco,int cep,int telefone,String email,String senha) throws SQLException,Exception{
		String sql = "insert into T_HB_USUARIO (cd_usuario,nm_usuario, nm_sobrenome, nr_cpf, nr_rg, dt_nascimento, ds_endereco, nr_cep, nr_telefone, ds_email, ds_senha) values (?,?,?,?,?,?,?,?,?,?,?)";
		
		PreparedStatement estrutura = conexao.prepareStatement(sql);
		
		DateFormat fmt = new SimpleDateFormat("dd-mm-yy");
		Date data = new Date(fmt.parse(dataNasc).getTime());
		
		estrutura.setInt(1,acrescentadorID());
		estrutura.setString(2, nome);
		estrutura.setString(3, sobrenome);
		estrutura.setInt(4, cpf);
		estrutura.setString(5, rg);
		estrutura.setDate(6, data);
		estrutura.setString(7, endereco);
		estrutura.setInt(8, cep);
		estrutura.setInt(9, telefone);
		estrutura.setString(10, email);
		estrutura.setString(11, senha);
		
		estrutura.execute();
		estrutura.close();
		return true;
	}

	// CRUD (Read)
	public  Usuario pesquisarUsuario(String email) throws Exception{
		Usuario usuario = new Usuario();
		PreparedStatement estrutura = this.conexao.prepareStatement("select * from T_HB_USUARIO WHERE ds_email = ?");
		estrutura.setString(1, email);
		ResultSet resultadoDados = estrutura.executeQuery();
		
		try{
		if(resultadoDados.next()){
			usuario.setCodigoUsuario(resultadoDados.getInt("cd_usuario"));
			usuario.setNome(resultadoDados.getString("nm_usuario"));
			usuario.setSobrenome(resultadoDados.getString("nm_sobrenome"));
			usuario.setCpf(resultadoDados.getInt("nr_cpf"));
			usuario.setRg(resultadoDados.getString("nr_rg"));
			usuario.setDtNascimento(resultadoDados.getString("dt_nascimento"));
			usuario.setEndereco(resultadoDados.getString("ds_endereco"));
			usuario.setCep(resultadoDados.getInt("nr_cep"));
			usuario.setTelefone(resultadoDados.getInt("nr_telefone"));
			usuario.setEmail(resultadoDados.getString("ds_email"));
			usuario.setSenha(resultadoDados.getString("ds_senha"));
		}
		}catch(SQLException se){
			se.printStackTrace();
		}
		resultadoDados.close();
		estrutura.close();
		return usuario;
	}

	// CRUD (Update)
	public int atualizarUsuario(Usuario usuario) throws Exception{
		PreparedStatement stmt = conexao.prepareStatement("update T_HB_USUARIO set nm_usuario=?, nm_sobrenome=?, nr_cpf=?, nr_rg=?, dt_nascimento=?, ds_endereco=?, nr_cep=?, nr_telefone=?, ds_email=?, ds_senha=? where cd_usuario=?");
		stmt.setString(1, usuario.getNome());
		stmt.setString(2, usuario.getSobrenome());
		stmt.setInt(3, usuario.getCpf());
		stmt.setString(4, usuario.getRg());
		stmt.setString(5, usuario.getDtNascimento());
		stmt.setString(6, usuario.getEndereco());
		stmt.setInt(7, usuario.getCep());
		stmt.setInt(8, usuario.getTelefone());
		stmt.setString(9, usuario.getEmail());
		stmt.setString(10, usuario.getSenha());
		int saida = stmt.executeUpdate();
		stmt.close();
		return saida;
	}

	// CRUD (Delete)
	public int deletarUsuario(String x) throws Exception{
		PreparedStatement stmt = conexao.prepareStatement("delete from T_HB_USUARIO where cd_usuario=?");
		stmt.setString(1, x);
		int saida = stmt.executeUpdate();
		stmt.close();
		return saida;
	}

	// List
	public List<Usuario> listUsuario() throws Exception{
		List<Usuario> lstUsuarios = new ArrayList<Usuario>();
		PreparedStatement estrutura = this.conexao.prepareStatement("select * from T_HB_USUARIO");
		ResultSet resultadoDados = estrutura.executeQuery();
		while(resultadoDados.next()){
			Usuario usuario = new Usuario();
			usuario.setCodigoUsuario(resultadoDados.getInt("cd_usuario"));
			usuario.setNome(resultadoDados.getString("nm_usuario"));
			usuario.setSobrenome(resultadoDados.getString("nm_sobrenome"));
			usuario.setCpf(resultadoDados.getInt("nr_cpf"));
			usuario.setRg(resultadoDados.getString("nr_rg"));
			usuario.setDtNascimento(resultadoDados.getString("dt_nascimento"));
			usuario.setEndereco(resultadoDados.getString("ds_endereco"));
			usuario.setCep(resultadoDados.getInt("nr_cep"));
			usuario.setTelefone(resultadoDados.getInt("nr_telefone"));
			usuario.setEmail(resultadoDados.getString("ds_email"));
			usuario.setSenha(resultadoDados.getString("ds_senha"));
		}
		resultadoDados.close();
		estrutura.close();
		return lstUsuarios;
	}

	public boolean novaDoacao(Objeto obj) throws SQLException,Exception{
		String sql = "INSERT INTO T_HB_OBJETO (cd_objeto, tp_objeto, ds_objeto, qt_objeto, peso) VALUES (?,?,?,?,?)";
		PreparedStatement carrinho = conexao.prepareStatement(sql);
		
		carrinho.setInt(1, acrescentadorID());
	//	carrinho.setString(2,obj. );
		
		
		
		return true;
	}
}
