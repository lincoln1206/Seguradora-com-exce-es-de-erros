
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;

public class Cliente {
	public String cliente;
	public double valor_imovel;
	public double seguro;
	Object[] options = { "Gerar Contrato", "Sair"}; 
	
	public void mostraContrato() {
		String nome = JOptionPane.showInputDialog(null, "Informe o nome de arquivo texto:\n");
		
		nome = nome+".txt";

		try {
			FileReader arq = new FileReader(nome);
			BufferedReader lerArq = new BufferedReader(arq);
			String linha = lerArq.readLine();
			
			while (linha != null) {
				System.out.printf("%s\n", linha);
				linha = lerArq.readLine();
			}

			arq.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Arquivo não existe!");
		}
		System.out.println();
	}
}
