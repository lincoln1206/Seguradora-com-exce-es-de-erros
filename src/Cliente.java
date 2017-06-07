import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Cliente {
	// VARI�VEIS GLOBAIS
	public String cliente;
	public String endereco;
	public double valor_imovel;
	public double seguro;
	public static boolean saiu = false, ok = false, cancelar = false;
	public String check;
	Object[] options = { "Gerar Contrato", "Sair" };

	public static void mostraContrato() {// MOSTRA UM CONTRATO ATRAV�S DE ENTRADA DO
									// USU�RIO
		String nomeArq = JOptionPane.showInputDialog(null, "Informe o seu CPF/CNPJ:\n");// ENTRADA
																						// DO
																						// USU�RIO

		try {
			if (nomeArq != null && nomeArq.equals("") == false) {// IF PARA SE O
																	// USU�RIO
																	// DIGITOU
																	// ALGO
				JTextArea ta = new JTextArea(20, 20);// CRIA UMA JANELA DE TEXTO
				ta.read(new FileReader(nomeArq + ".txt"), null);// L� O ARQUIVO
																// ".txt"
				ta.setEditable(false);// TORNA O ARQUIVO N�O EDIT�VEL ATRAV�S DA
										// JANELA DE TEXTO
				JOptionPane.showMessageDialog(null, new JScrollPane(ta), "Seguradora", 2, null);// MOSTRA
																								// O
																								// CONTRATO
																								// NA
																								// JANELA
																								// DE
																								// TEXTO
			} else if (nomeArq != null && nomeArq.equals("") == true) {// JOGA
																		// UMA
																		// EXCE��O
																		// PERSONALIZADA
																		// "DigitouNada"
																		// SE O
																		// USU�RIO
																		// N�O
																		// DIGITOU
																		// NADA
				throw new DigitouNada();
			}
		} catch (NullPointerException ex) {

		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, "Arquivo de contrato n�o existe!");
		} catch (IllegalArgumentException ex) {
			JOptionPane.showMessageDialog(null, "Arquivo de contrato n�o existe!");
		} catch (DigitouNada e) {
		}
	}

	public static void lerCadastro() {// MOSTRA TODOS OS CADASTROS

		String dadosPF = null, dadosPJ = null;// STRINGS PARA ARMAZENAR OS DADOS
												// DE PESSOA F�SICA(dadosPF) E
												// PESSOA JUR�DICA(dadosPJ)

		try {
			FileFilter filter = new FileFilter() {// FILTRO PARA FILTRAR E PEGAR
													// TODOS OS ARQUIVOS ".bin"
				public boolean accept(File file) {
					return file.getName().endsWith(".bin");
				}
			};

			File dir = new File("./");// ABRIR DIRET�RIO ONDE O PROGRAMA EST�
			File[] files = dir.listFiles(filter);// USANDO O FILTRO DE ".bin" NO
													// DIRET�RIO

			// ARRAYLISTS PARA ARMAZENAR DADOS DE CLIENTES
			List<String> listaClientesPF = new ArrayList<>();
			List<String> listaClientesPJ = new ArrayList<>();

			for (int i = 0; i < files.length; i++) {// FOR PARA LER TODOS OS
													// ARQUIVOS ".bin"
													// ENCONTRADOS NO DIRET�RIO
				FileInputStream fis = new FileInputStream(files[i]);
				ObjectInputStream ois = new ObjectInputStream(fis);

				Pessoa ler = (Pessoa) ois.readObject();

				NumberFormat f = NumberFormat.getCurrencyInstance();

				if (ler.isPessoaFisica() == true) {// IF PARA SE O CLIENTE FOR
													// PESSOA F�SICA

					dadosPF = ("\n\nCliente\n\nNome : " + ler.getCliente() + "\nCPF : " + ler.getCpf_cnpj()
							+ "\nValor do Seguro: " + f.format(ler.getSeguro()));// TRANSFORMA
																					// DADOS
																					// LIDOS
																					// EM
																					// STRINGS
					if (dadosPJ != null)// SE A STRING N�O FOR NULA, ARMAZENA NO
										// ARRAYLIST
						listaClientesPF.add(dadosPF);
				} else if (ler.isPessoaFisica() == false) {// IF PARA SE O
															// CLIENTE FOR
															// PESSOA JUR�DICA
					dadosPJ = ("\n\nCliente\n\nNome : " + ler.getCliente() + "\nCNPJ : " + ler.getCpf_cnpj()
							+ "\nValor do Seguro: " + f.format(ler.getSeguro()));// TRANSFORMA
																					// DADOS
																					// LIDOS
																					// EM
																					// STRINGS
					if (dadosPJ != null)// SE A STRING N�O FOR NULA, ARMAZENA NO
										// ARRAYLIST
						listaClientesPJ.add(dadosPJ);
				}

			}

			String TextArea = ("PESSOA FISICA:" + listaClientesPF + "\n\nPESSOA JURIDICA:" + listaClientesPJ);// STRING
																												// PARA
																												// SER
																												// UTULIZADA
																												// NA
																												// JANELA
																												// DE
																												// TEXTO
			JTextArea ta = new JTextArea(20, 25);// ABRE UMA NOVA JANELA DE
													// TEXTO DE DIMENS�ES 20 X
													// 25
			ta.append(TextArea);// ADICIONA CONTE�DO DA STRING "TextArea" NA
								// JANELA DE TEXTO
			ta.setEditable(false);// TORNA O ARQUIVO N�O EDIT�VEL ATRAV�S DA
									// JANELA DE TEXTO
			JOptionPane.showMessageDialog(null, new JScrollPane(ta), "Seguradora", 2, null);// MOSTRA
																							// A
																							// JANELA
																							// DE
																							// TEXTO

		} catch (NullPointerException | FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null, "N�o h� cadastros ainda...");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Erro ao abrir arquivos!");
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Erro ao abrir arquivos!");
		}

	}
}
