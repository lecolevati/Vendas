package controller;

import java.awt.print.PrinterException;
import java.text.MessageFormat;

import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import model.Produto;
import persistence.VendasDao;
import persistence.VendasDaoImpl;

public class VendasBean {
	
	private JTextField tfNf;
	private JTextArea taNotaFiscal;
	private JFormattedTextField tfProduto;
	private JFormattedTextField tfQtd;
	
	public VendasBean(JTextField tfNf, JTextArea taNotaFiscal, JFormattedTextField tfProduto,  JFormattedTextField tfQtd){
		this.tfNf = tfNf;
		this.taNotaFiscal = taNotaFiscal;
		this.tfProduto = tfProduto;
		this.tfQtd = tfQtd;
	}

	public void totalVenda() {

		VendasDao vDao = new VendasDaoImpl();
		float valorTotal = vDao.valorTotalNota(Integer.parseInt(tfNf.getText()));
		StringBuffer sb = new StringBuffer();
		sb.append("\n");
		sb.append("Total da Compra = R$");
		sb.append(String.valueOf(valorTotal));
		sb.append("\n");
		sb.append(" ");
		taNotaFiscal.append(sb.toString());
		try {
			taNotaFiscal.print(new MessageFormat("Nota"), new MessageFormat("Mercado Três Irmãos"));
		} catch (PrinterException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO",JOptionPane.ERROR_MESSAGE);
		}

	}

	public void insereVenda() {
		VendasDao vDao = new VendasDaoImpl();
		Produto produto = new Produto();
		produto.setCodigo(Integer.parseInt(tfProduto.getText().trim()));
		produto.setQuantidade(Integer.parseInt(tfQtd.getText().trim()));
		String saida = vDao.vendeProduto(Integer.parseInt(tfNf.getText()),
				produto);
		StringBuffer sb = new StringBuffer();
		sb.append("\n");
		sb.append(saida);
		taNotaFiscal.append(sb.toString());
	}

	public void limpaProduto() {
		tfProduto.setValue(null);
		tfQtd.setValue(null);
	}

	public void limpaCampos() {
		tfProduto.setValue(null);
		tfQtd.setValue(null);
		cabecalho();
		proximoCodigo();
	}

	public void proximoCodigo() {
		VendasDao vDao = new VendasDaoImpl();
		tfNf.setText(vDao.codigoNotaFiscal());
	}

	public void cabecalho() {
		StringBuffer sb = new StringBuffer();
		sb.append("cod");
		sb.append("\t");
		sb.append("nome");
		sb.append("\t");
		sb.append("qtd");
		sb.append("\t");
		sb.append("\t");
		sb.append("vl_total");
		taNotaFiscal.setText(sb.toString());
	}
	
}
