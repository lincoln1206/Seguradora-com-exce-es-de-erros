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

	/* Variaáveis Globais */
	public String cliente;
	public String endereco;
	public double valor_imovel;
	public double seguro;
	public static boolean saiu = false, ok = false, cancelar = false, fezContrato = false;;
	public String check;
	Object[] options = { "Gerar Contrato", "Sair" };
	static NumberFormat f = NumberFormat.getCurrencyInstance();

	/* Mostra o contrato através de entrada do usuário */
	public static void mostraContrato() {
		/* Entrada do usuário */
		String nomeArq = JOptionPane.showInputDialog(null, "Informe o seu CPF/CNPJ:\n");

		try {
			/* if para se o usuário digitou algo */
			if (nomeArq != null && nomeArq.equals("") == false) {
				/* Cria uma janela de texto 20 x 20 */
				JTextArea ta = new JTextArea(20, 20);
				/* Lê o arquivo ".txt" */
				ta.read(new FileReader(nomeArq + ".txt"), null);
				/* Torna o arquivo não editável */
				ta.setEditable(false);
				/* Mostra o contrato na Janela de Texto */
				JOptionPane.showMessageDialog(null, new JScrollPane(ta), "Seguradora", 2, null);
			}
			/*
			 * Joga a exceção personalizada "DigitouNada" se o usuário não
			 * digitou nada
			 */
			else if (nomeArq != null && nomeArq.equals("") == true) {
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

	/* Mostra todos os cadastros de Pessoa Física */
	public static void lerCadastroFisica() {

		/* Strings para armazenar os dados de Pessoa Jurídica */
		String dadosPF = null;

		try {
			/* Filtro para arquivos "PessoaFisica.bin" */
			FileFilter filter = new FileFilter() {
				public boolean accept(File file) {
					return file.getName().endsWith("PessoaFisica.bin");
				}
			};

			/* Abre diretório aonde o programa está localizado */
			File dir = new File("./");
			/* Usa o filtro no diretório */
			File[] files = dir.listFiles(filter);

			/*
			 * Arraylists para armazenar Strings de dados de Pessoa Física e
			 */
			List<String> listaClientesPF = new ArrayList<>();

			/* For pra ler arquivos no diretório */
			for (int i = 0; i < files.length; i++) {
				/* Lê arquivos */
				FileInputStream fis = new FileInputStream(files[i]);
				ObjectInputStream ois = new ObjectInputStream(fis);

				PessoaFisica lerPF = (PessoaFisica) ois.readObject();

				/* Transforma dados lidos de PessoaFisica em String */
				dadosPF = ("\n\nCliente\n\nNome : " + lerPF.getCliente() + "\nCPF : " + lerPF.getCpf()
						+ "\nValor do Seguro: " + f.format(lerPF.getSeguro()));
				/* Se os dados lidos não forem nulos, adiciona ao ArrayList */
				if (dadosPF != null)
					listaClientesPF.add(dadosPF);

				ois.close();

			}

			/* String contendo a ArrayList */
			String TextArea = ("PESSOA FISICA:" + listaClientesPF);
			/* Janela de texto 20 x 25 */
			JTextArea ta = new JTextArea(20, 25);
			/* Adiciona conteúdo da string "TextArea" na Janela de texto */
			ta.append(TextArea);
			/* Torna o arquivo não editável */
			ta.setEditable(false);
			/* Mostra o contrato na Janela de Texto */
			JOptionPane.showMessageDialog(null, new JScrollPane(ta), "Seguradora", 2, null);

		} catch (NullPointerException | FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null, "Não há cadastros ainda...");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Erro ao abrir arquivos!");
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Erro ao abrir arquivos!");
		}

	}
	
	/* Mostra todos os cadastros de Pessoa Jurídica*/
	public static void lerCadastroJuridica(){
		
		/* Strings para armazenar os dados de Pessoa Jurídica */
		String dadosPJ = null;

		try {
			/* Filtro para arquivos "PessoaJuridica.bin" */
			FileFilter filter = new FileFilter() {
				public boolean accept(File file) {
					return file.getName().endsWith("PessoaJuridica.bin");
				}
			};

			/* Abre diretório aonde o programa está localizado */
			File dir = new File("./");
			/* Usa o filtro no diretório */
			File[] files = dir.listFiles(filter);

			/*
			 * Arraylists para armazenar Strings de dados de Pessoa Jurídica
			 */
			List<String> listaClientesPJ = new ArrayList<>();

			/* For pra ler arquivos no diretório */
			for (int i = 0; i < files.length; i++) {
				/* Lê arquivos */
				FileInputStream fis = new FileInputStream(files[i]);
				ObjectInputStream ois = new ObjectInputStream(fis);

				PessoaJuridica lerPJ = (PessoaJuridica) ois.readObject();
			
				/* Transforma dados lidos de PessoaJuridica em String */
				dadosPJ = ("\n\nCliente\n\nNome : " + lerPJ.getCliente() + "\nCNPJ : " + lerPJ.getCnpj()
						+ "\nValor do Seguro: " + f.format(lerPJ.getSeguro()));
				/* Se os dados lidos não forem nulos, adiciona ao ArrayList */
				if (dadosPJ != null)
					listaClientesPJ.add(dadosPJ);

				ois.close();

			}

			/* String contendo a ArrayList */
			String TextArea = ("PESSOA JURIDICA:" + listaClientesPJ);
			/* Janela de texto 20 x 25 */
			JTextArea ta = new JTextArea(20, 25);
			/* Adiciona conteúdo da string "TextArea" na Janela de texto */
			ta.append(TextArea);
			/* Torna o arquivo não editável */
			ta.setEditable(false);
			/* Mostra o contrato na Janela de Texto */
			JOptionPane.showMessageDialog(null, new JScrollPane(ta), "Seguradora", 2, null);

		} catch (NullPointerException | FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null, "Não há cadastros ainda...");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Erro ao abrir arquivos!");
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Erro ao abrir arquivos!");
		}
		
	}
}
