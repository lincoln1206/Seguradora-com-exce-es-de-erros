import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
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
						+ "\nValor do Seguro: " + f.format(lerPF.getSeguro()));
				/* Se os dados lidos n�o forem nulos, adiciona ao ArrayList */
				if (dadosPF != null)
					listaClientesPF.add(dadosPF);

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
	
	/* Mostra todos os cadastros de Pessoa Jur�dica*/
	public static void lerCadastroJuridica(){
		
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
						+ "\nValor do Seguro: " + f.format(lerPJ.getSeguro()));
				/* Se os dados lidos n�o forem nulos, adiciona ao ArrayList */
				if (dadosPJ != null)
					listaClientesPJ.add(dadosPJ);

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
