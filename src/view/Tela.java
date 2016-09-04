package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;

import controller.VendasBean;
import javax.swing.JFormattedTextField;

public class Tela extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfNf;
	private JTextArea taNotaFiscal;
	private JFormattedTextField tfProduto;
	private JFormattedTextField tfQtd;
	private MaskFormatter mascara;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tela frame = new Tela();
					frame.setVisible(true);
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Tela() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 11, 412, 524);
		contentPane.add(scrollPane);
		
		taNotaFiscal = new JTextArea();
		taNotaFiscal.setEditable(false);
		scrollPane.setViewportView(taNotaFiscal);
		
		JLabel lblMercadoTrsIrmos = new JLabel("Mercado Tr\u00EAs Irm\u00E3os");
		lblMercadoTrsIrmos.setHorizontalAlignment(SwingConstants.CENTER);
		lblMercadoTrsIrmos.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		lblMercadoTrsIrmos.setBounds(432, 16, 342, 70);
		contentPane.add(lblMercadoTrsIrmos);
		
		JLabel lblNotaFiscal = new JLabel("Nota Fiscal");
		lblNotaFiscal.setBounds(432, 173, 72, 14);
		contentPane.add(lblNotaFiscal);
		
		tfNf = new JTextField();
		tfNf.setEditable(false);
		tfNf.setBounds(502, 170, 272, 20);
		contentPane.add(tfNf);
		tfNf.setColumns(10);
		
		JLabel lblProduto = new JLabel("Produto");
		lblProduto.setBounds(432, 230, 46, 14);
		contentPane.add(lblProduto);
		
		JLabel lblQuantidade = new JLabel("Quantidade");
		lblQuantidade.setBounds(432, 290, 72, 14);
		contentPane.add(lblQuantidade);
		
		JButton btnInserir = new JButton("Inserir");
		btnInserir.setBounds(432, 359, 89, 23);
		contentPane.add(btnInserir);
		
		JButton btnFinalizar = new JButton("Finalizar");
		btnFinalizar.setBounds(685, 359, 89, 23);
		contentPane.add(btnFinalizar);
		
		
		try {
			mascara = new MaskFormatter("######");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		tfProduto = new JFormattedTextField(mascara);
		tfProduto.setBounds(502, 227, 72, 20);
		contentPane.add(tfProduto);
		tfProduto.setFocusLostBehavior(JFormattedTextField.COMMIT);
		
		tfQtd = new JFormattedTextField(mascara);
		tfQtd.setBounds(502, 287, 72, 20);
		contentPane.add(tfQtd);
		tfQtd.setFocusLostBehavior(JFormattedTextField.COMMIT);
		
		final VendasBean vBean = new VendasBean(tfNf, taNotaFiscal, tfProduto, tfQtd);
		vBean.limpaCampos();
		
		ActionListener insere = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				vBean.insereVenda();
				vBean.limpaProduto();
			}
		};
		
		ActionListener finaliza = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				vBean.totalVenda();
				vBean.limpaCampos();
			}
		};
		
		btnInserir.addActionListener(insere);
		btnFinalizar.addActionListener(finaliza);
	}
}
