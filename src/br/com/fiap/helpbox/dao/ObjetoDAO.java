package br.com.fiap.helpbox.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.helpbox.beans.Objeto;
import br.com.fiap.helpbox.beans.Usuario;
import br.com.fiap.helpbox.conexao.ConexaoFactory;

public class ObjetoDAO {

	private Connection conexao;

	public ObjetoDAO() throws Exception{
		this.conexao = new ConexaoFactory().getConnection();
	}


		// CRUD + List

	// CRUD (Create)
	public boolean addObjeto(Objeto o) throws SQLException, Exception{
		String sql = "INSERT INTO T_HB_OBJETO (cd_objeto, tp_objeto, ds_objeto, qt_objeto, peso) VALUES (?,?,?,?,?)";
		PreparedStatement estrutura = conexao.prepareStatement(sql);
		
		estrutura.setInt(1, o.getCodigoObjeto());
		estrutura.setString(2, o.getTipoObjeto());
		estrutura.setString(3, o.getDescricao());
		estrutura.setString(4, o.getQuantidade());
		estrutura.setString(5, o.getPeso());
		
		
		estrutura.execute();
		estrutura.close();
		return true;
	}

	// CRUD (Read)
	//public Objeto pesquisarObjeto(String strObjeto) throws Exception{
	//	Objeto objeto = new Objeto();
	//	PreparedStatement estrutura = this.conexao.prepareStatement("select * from T_HB_OBJETO");
//		estrutura.setString(1, strObjeto);
//		ResultSet resultadoDados = estrutura.executeQuery();
//		if(resultadoDados.next()){
//			objeto.setCodigoObjeto(resultadoDados.getInt("cd_objeto"));
//			objeto.setNome(resultadoDados.getString("nm_objeto"));
//			objeto.setDescricao(resultadoDados.getString("ds_objeto"));
//			objeto.setQuantidade(resultadoDados.getInt("qt_objeto"));
//			objeto.setEstado(resultadoDados.getString("ds_estado"));
//			objeto.setCor(resultadoDados.getString("ds_cor"));
//			objeto.setGenero(resultadoDados.getString("ds_genero"));
//		}
	//	resultadoDados.close();
//		estrutura.close();
//		return objeto;
//	}

	// CRUD (Update)
//	public int atualizarObjeto(Objeto objeto) throws Exception{
//		PreparedStatement stmt = conexao.prepareStatement("update T_HB_OBJETO set nm_objeto=?, ds_objeto=?, qt_objeto=?, ds_estado=?, ds_cor=?, ds_genero=? where cd_objeto=?");
//		stmt.setString(1,objeto.getNome() );
//		stmt.setString(2,objeto.getDescricao() );
//		stmt.setInt(3,objeto.getQuantidade() );
//		stmt.setString(4,objeto.getEstado() );
//		stmt.setString(5,objeto.getCor() );
//		stmt.setString(6,objeto.getGenero() );	
//		int saida = stmt.executeUpdate();
//		stmt.close();
//		return saida;
	//}

	// CRUD (Delete)
	public int deletarObjeto(int x) throws Exception{
		PreparedStatement stmt = conexao.prepareStatement("delete from T_HB_OBJETO where cd_objeto=?");
		stmt.setInt(1, x);
		int saida = stmt.executeUpdate();
		stmt.close();
		return saida;
	}

	
}
