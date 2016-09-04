package persistence;

import model.Produto;

public interface VendasDao {

	public String codigoNotaFiscal(); 
	public String vendeProduto(int notaFiscal, Produto produto);
	public float valorTotalNota(int notaFiscal);
	
}
