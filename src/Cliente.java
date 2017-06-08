import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Cliente {

	/* Varia�veis Globais */
	public String cliente;
	public String endereco;
	public double valor_imovel;
	public double seguro;
	public static boolean saiu = false, ok = false, cancelar = false, fezContrato = false;;
	public String check;
	Object[] options = { "Gerar Contrato", "Sair" };
	static Object[] escolha = { "Sim", "N�o" };
	static NumberFormat f = NumberFormat.getCurrencyInstance();

	/* Mostra o contrato atrav�s de entrada do usu�rio */
	public static void mostraContrato() {
		/* Entrada do usu�rio */
		String nomeArq = JOptionPane.showInputDialog(null, "Informe o seu CPF/CNPJ:\n");

		try {
			/* if para se o usu�rio digitou algo */
			if (nomeArq != null && nomeArq.equals("") == false) {
				/* Cria uma janela de texto 20 x 20 */
				JTextArea ta = new JTextArea(20, 20);
				/* L� o arquivo ".txt" */
				ta.read(new FileReader(nomeArq + ".txt"), null);
				/* Torna o arquivo n�o edit�vel */
				ta.setEditable(false);
				/* Mostra o contrato na Janela de Texto */
				JOptionPane.showMessageDialog(null, new JScrollPane(ta), "Seguradora", 2, null);
			}
			/*
			 * Joga a exce��o personalizada "DigitouNada" se o usu�rio n�o
			 * digitou nada
			 */
			else if (nomeArq != null && nomeArq.equals("") == true) {
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

	/* Mostra todos os cadastros de Pessoa F�sica */
	public static void lerCadastroFisica() {

		/* Strings para armazenar os dados de Pessoa Jur�dica */
		String dadosPF = null;

		try {
			/* Filtro para arquivos "PessoaFisica.bin" */
			FileFilter filter = new FileFilter() {
				public boolean accept(File file) {
					return file.getName().endsWith("PessoaFisica.bin");
				}
			};

			/* Abre diret�rio aonde o programa est� localizado */
			File dir = new File("./");
			/* Usa o filtro no diret�rio */
			File[] files = dir.listFiles(filter);

			/*
			 * Arraylists para armazenar Strings de dados de Pessoa F�sica e
			 */
			List<String> listaClientesPF = new ArrayList<>();

			/* For pra ler arquivos no diret�rio */
			for (int i = 0; i < files.length; i++) {
				/* L� arquivos */
				FileInputStream fis = new FileInputStream(files[i]);
				ObjectInputStream ois = new ObjectInputStream(fis);

				PessoaFisica lerPF = (PessoaFisica) ois.readObject();
				
				/* Transforma dados lidos de PessoaFisica em String */
				dadosPF = ("\n\nCliente\n\nNome : " + lerPF.getCliente() + "\nCPF : " + lerPF.getCpf()
						+ "\nValor do im�vel: " + f.format(lerPF.getValor_imovel()));
				/* Se os dados lidos n�o forem nulos, adiciona ao ArrayList */
				if (dadosPF != null)
					listaClientesPF.add(dadosPF);

				/* Checa se os usu�rios cadastrados tem contrato */
				if (lerPF.isFezContrato() == false) {
					try {

						/* Pergunta se o usu�rio quer gerar o contrato */
						int opcao = JOptionPane.showOptionDialog(null,
								"Cliente " + lerPF.getCliente() + " de CPF : " + lerPF.getCpf()
										+ " n�o tem contrato...\nDeseja gerar um?",
								"Opera��o", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, escolha,
								escolha[0]);
						/* if salva arquivo */
						if (opcao == 0) {

							/*
							 * Pega os dados do arquivo e coloca em Arrays
							 * tempor�rios
							 */
							String[] temp1 = { lerPF.getCliente(), lerPF.getCpf(), lerPF.getEndereco(),
									lerPF.getZonaM(), lerPF.getTipoM() };
							double[] temp2 = { lerPF.getValor_imovel(), lerPF.getSeguro() };

							/* Deleta arquivo */
							files[i].delete();

							PessoaFisica c = new PessoaFisica(temp1[0], temp1[1], temp1[2], temp2[0], temp1[3],
									temp1[4], temp2[1], true);

							/* Cria novo arquivo */
							try {

								FileOutputStream fos = new FileOutputStream(temp1[1] + ".PessoaFisica.bin");
								ObjectOutputStream oos = new ObjectOutputStream(fos);
								oos.writeObject(c);

								oos.close();
								fos.close();
							} catch (IOException e) {
							}

							/* Gera contrato pessoa f�sica */
							FileWriter arq = new FileWriter(temp1[1] + ".txt");
							PrintWriter gravarArq = new PrintWriter(arq);
							gravarArq.printf("**CONTRATO**%n%nNome do cliente: " + temp1[0] + "%nCPF: " + temp1[1]
									+ "%nEndere�o: " + temp1[2] + "%nTipo de resid�ncia: " + temp1[4] + "%nZona: "
									+ temp1[3] + "%nValor do im�vel: " + f.format(temp2[0]) + "%nValor do seguro: "
									+ f.format(temp2[1]));

							JOptionPane.showMessageDialog(null,
									"Contrato salvo com sucesso como " + temp1[1] + ".txt !");

							arq.close();

						} else if (opcao == 1) {
							continue;
						}
					} catch (IOException e) {
						JOptionPane.showMessageDialog(null, "Erro ao salvar contrato!");
					}
				}

				fis.close();
				ois.close();

			}

			/* String contendo a ArrayList */
			String TextArea = ("PESSOA FISICA:" + listaClientesPF);
			/* Janela de texto 20 x 25 */
			JTextArea ta = new JTextArea(20, 25);
			/* Adiciona conte�do da string "TextArea" na Janela de texto */
			ta.append(TextArea);
			/* Torna o arquivo n�o edit�vel */
			ta.setEditable(false);
			/* Mostra o contrato na Janela de Texto */
			JOptionPane.showMessageDialog(null, new JScrollPane(ta), "Seguradora", 2, null);

		} catch (NullPointerException | FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null, "N�o h� cadastros ainda...");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Erro ao abrir arquivos!");
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Erro ao abrir arquivos!");
		}

	}

	/* Mostra todos os cadastros de Pessoa Jur�dica */
	public static void lerCadastroJuridica() {

		/* Strings para armazenar os dados de Pessoa Jur�dica */
		String dadosPJ = null;

		try {
			/* Filtro para arquivos "PessoaJuridica.bin" */
			FileFilter filter = new FileFilter() {
				public boolean accept(File file) {
					return file.getName().endsWith("PessoaJuridica.bin");
				}
			};

			/* Abre diret�rio aonde o programa est� localizado */
			File dir = new File("./");
			/* Usa o filtro no diret�rio */
			File[] files = dir.listFiles(filter);

			/*
			 * Arraylists para armazenar Strings de dados de Pessoa Jur�dica
			 */
			List<String> listaClientesPJ = new ArrayList<>();

			/* For pra ler arquivos no diret�rio */
			for (int i = 0; i < files.length; i++) {
				/* L� arquivos */
				FileInputStream fis = new FileInputStream(files[i]);
				ObjectInputStream ois = new ObjectInputStream(fis);

				PessoaJuridica lerPJ = (PessoaJuridica) ois.readObject();
				
				/* Transforma dados lidos de PessoaJuridica em String */
				dadosPJ = ("\n\nCliente\n\nNome : " + lerPJ.getCliente() + "\nCNPJ : " + lerPJ.getCnpj()
						+ "\nValor do im�vel: " + f.format(lerPJ.getValor_imovel()));
				/* Se os dados lidos n�o forem nulos, adiciona ao ArrayList */
				if (dadosPJ != null)
					listaClientesPJ.add(dadosPJ);

				/* Checa se os usu�rios cadastrados tem contrato */
				if (lerPJ.isFezContrato() == false) {
					try {

						/* Pergunta se o usu�rio quer gerar o contrato */
						int opcao = JOptionPane.showOptionDialog(null,
								"Cliente " + lerPJ.getCliente() + " de CNPJ : " + lerPJ.getCnpj()
										+ " n�o tem contrato...\nDeseja gerar um?",
								"Opera��o", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, escolha,
								escolha[0]);
						/* if salva arquivo */
						if (opcao == 0) {

							/*
							 * Pega os dados do arquivo e coloca em Arrays
							 * tempor�rios
							 */
							String[] temp1 = { lerPJ.getCliente(), lerPJ.getCnpj(), lerPJ.getEndereco(),
									lerPJ.getRamoM() };
							double[] temp2 = { lerPJ.getValor_imovel(), lerPJ.getSeguro() };
							long[] temp3 = { lerPJ.getNumero_funcionarios(), lerPJ.getNumero_visitas() };

							/* Cria novo arquivo */
							files[i].delete();

							PessoaJuridica c = new PessoaJuridica(temp1[0], temp1[1], temp1[2], temp2[0], temp3[0],
									temp3[1], temp1[3], temp2[1], true);

							/* Cria novo arquivo */
							try {

								FileOutputStream fos = new FileOutputStream(temp1[1] + ".PessoaJuridica.bin");
								ObjectOutputStream oos = new ObjectOutputStream(fos);
								oos.writeObject(c);

								oos.close();
								fos.close();
							} catch (IOException e) {
							}

							/* Gera contrato pessoa jur�dica */
							FileWriter arq = new FileWriter(temp1[1] + ".txt");
							PrintWriter gravarArq = new PrintWriter(arq);
							gravarArq.printf("**CONTRATO**%n%nNome do cliente: " + temp1[0] + "%nCNPJ: " + temp1[1]
									+ "%nEndere�o: " + temp1[2] + "%nRamo: " + temp1[3] + "%nValor do im�vel: "
									+ f.format(temp2[0]) + "%nN�mero de Funcion�rios: " + temp3[0]
									+ "%nN�mero de visitas: " + temp3[1] + "%nValor do seguro: " + f.format(temp2[1]));

							JOptionPane.showMessageDialog(null,
									"Contrato salvo com sucesso como " + temp1[1] + ".txt !");

							arq.close();

						} else if (opcao == 1) {
							continue;
						}
					} catch (IOException e) {
						JOptionPane.showMessageDialog(null, "Erro ao gerar contrato!");
					}
				}

				fis.close();
				ois.close();

			}

			/* String contendo a ArrayList */
			String TextArea = ("PESSOA JURIDICA:" + listaClientesPJ);
			/* Janela de texto 20 x 25 */
			JTextArea ta = new JTextArea(20, 25);
			/* Adiciona conte�do da string "TextArea" na Janela de texto */
			ta.append(TextArea);
			/* Torna o arquivo n�o edit�vel */
			ta.setEditable(false);
			/* Mostra o contrato na Janela de Texto */
			JOptionPane.showMessageDialog(null, new JScrollPane(ta), "Seguradora", 2, null);

		} catch (NullPointerException | FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null, "N�o h� cadastros ainda...");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Erro ao abrir arquivos!");
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Erro ao abrir arquivos!");
		}

	}
}
