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
	// VARIÁVEIS GLOBAIS
	public String cliente;
	public String endereco;
	public double valor_imovel;
	public double seguro;
	public static boolean saiu = false, ok = false, cancelar = false;
	public String check;
	Object[] options = { "Gerar Contrato", "Sair" };

	public static void mostraContrato() {// MOSTRA UM CONTRATO ATRAVÉS DE ENTRADA DO
									// USUÁRIO
		String nomeArq = JOptionPane.showInputDialog(null, "Informe o seu CPF/CNPJ:\n");// ENTRADA
																						// DO
																						// USUÁRIO

		try {
			if (nomeArq != null && nomeArq.equals("") == false) {// IF PARA SE O
																	// USUÁRIO
																	// DIGITOU
																	// ALGO
				JTextArea ta = new JTextArea(20, 20);// CRIA UMA JANELA DE TEXTO
				ta.read(new FileReader(nomeArq + ".txt"), null);// LÊ O ARQUIVO
																// ".txt"
				ta.setEditable(false);// TORNA O ARQUIVO NÃO EDITÁVEL ATRAVÉS DA
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
																		// EXCEÇÃO
																		// PERSONALIZADA
																		// "DigitouNada"
																		// SE O
																		// USUÁRIO
																		// NÃO
																		// DIGITOU
																		// NADA
				throw new DigitouNada();
			}
		} catch (NullPointerException ex) {

		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, "Arquivo de contrato não existe!");
		} catch (IllegalArgumentException ex) {
			JOptionPane.showMessageDialog(null, "Arquivo de contrato não existe!");
		} catch (DigitouNada e) {
		}
	}

	public static void lerCadastro() {// MOSTRA TODOS OS CADASTROS

		String dadosPF = null, dadosPJ = null;// STRINGS PARA ARMAZENAR OS DADOS
												// DE PESSOA FÍSICA(dadosPF) E
												// PESSOA JURÍDICA(dadosPJ)

		try {
			FileFilter filter = new FileFilter() {// FILTRO PARA FILTRAR E PEGAR
													// TODOS OS ARQUIVOS ".bin"
				public boolean accept(File file) {
					return file.getName().endsWith(".bin");
				}
			};

			File dir = new File("./");// ABRIR DIRETÓRIO ONDE O PROGRAMA ESTÁ
			File[] files = dir.listFiles(filter);// USANDO O FILTRO DE ".bin" NO
													// DIRETÓRIO

			// ARRAYLISTS PARA ARMAZENAR DADOS DE CLIENTES
			List<String> listaClientesPF = new ArrayList<>();
			List<String> listaClientesPJ = new ArrayList<>();

			for (int i = 0; i < files.length; i++) {// FOR PARA LER TODOS OS
													// ARQUIVOS ".bin"
													// ENCONTRADOS NO DIRETÓRIO
				FileInputStream fis = new FileInputStream(files[i]);
				ObjectInputStream ois = new ObjectInputStream(fis);

				Pessoa ler = (Pessoa) ois.readObject();

				NumberFormat f = NumberFormat.getCurrencyInstance();

				if (ler.isPessoaFisica() == true) {// IF PARA SE O CLIENTE FOR
													// PESSOA FÍSICA

					dadosPF = ("\n\nCliente\n\nNome : " + ler.getCliente() + "\nCPF : " + ler.getCpf_cnpj()
							+ "\nValor do Seguro: " + f.format(ler.getSeguro()));// TRANSFORMA
																					// DADOS
																					// LIDOS
																					// EM
																					// STRINGS
					if (dadosPJ != null)// SE A STRING NÃO FOR NULA, ARMAZENA NO
										// ARRAYLIST
						listaClientesPF.add(dadosPF);
				} else if (ler.isPessoaFisica() == false) {// IF PARA SE O
															// CLIENTE FOR
															// PESSOA JURÍDICA
					dadosPJ = ("\n\nCliente\n\nNome : " + ler.getCliente() + "\nCNPJ : " + ler.getCpf_cnpj()
							+ "\nValor do Seguro: " + f.format(ler.getSeguro()));// TRANSFORMA
																					// DADOS
																					// LIDOS
																					// EM
																					// STRINGS
					if (dadosPJ != null)// SE A STRING NÃO FOR NULA, ARMAZENA NO
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
													// TEXTO DE DIMENSÕES 20 X
													// 25
			ta.append(TextArea);// ADICIONA CONTEÚDO DA STRING "TextArea" NA
								// JANELA DE TEXTO
			ta.setEditable(false);// TORNA O ARQUIVO NÃO EDITÁVEL ATRAVÉS DA
									// JANELA DE TEXTO
			JOptionPane.showMessageDialog(null, new JScrollPane(ta), "Seguradora", 2, null);// MOSTRA
																							// A
																							// JANELA
																							// DE
																							// TEXTO

		} catch (NullPointerException | FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null, "Não há cadastros ainda...");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Erro ao abrir arquivos!");
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Erro ao abrir arquivos!");
		}

	}
}
