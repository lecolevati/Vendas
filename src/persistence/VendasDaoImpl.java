package persistence;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.swing.JOptionPane;

import model.Produto;

public class VendasDaoImpl implements VendasDao{
	
	Connection c;

	public VendasDaoImpl(){
		GenericDao gDao = new GenericDaoImpl();
		c = gDao.getConnection();
	}
	
	@Override
	public String vendeProduto(int notaFiscal, Produto produto) {
		String sql = "{call sp_inserevenda (?,?,?,?)}";
		String saida = "";
		try {
			CallableStatement cs = c.prepareCall(sql);
			cs.setInt(1, notaFiscal);
			cs.setInt(2, produto.getCodigo());
			cs.setInt(3, produto.getQuantidade());
			cs.registerOutParameter(4, Types.VARCHAR);
			cs.execute();
			saida = cs.getString(4);
			cs.close();
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO",JOptionPane.ERROR_MESSAGE);
		}
		
		return saida;
	}

	@Override
	public float valorTotalNota(int notaFiscal) {
		float valorFinal = 0.0f;
		String sql = "select SUM(valor_total) as valor_final from venda where nota_fiscal = ?";
		try {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, notaFiscal);
			ResultSet rs = ps.executeQuery();
			if (rs.next()){
				valorFinal = rs.getFloat("valor_final");
			}
			rs.close();
			ps.close();
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO",JOptionPane.ERROR_MESSAGE);
		}
		return valorFinal;
	}

	@Override
	public String codigoNotaFiscal() {
		String sql = "{call sp_proximocodigo(?)}";
		String saida = "";
		try {
			CallableStatement cs = c.prepareCall(sql);
			cs.registerOutParameter(1, Types.INTEGER);
			cs.execute();
			saida = String.valueOf(cs.getInt(1));
			cs.close();
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO",JOptionPane.ERROR_MESSAGE);
		}
		
		return saida;
	}

}
