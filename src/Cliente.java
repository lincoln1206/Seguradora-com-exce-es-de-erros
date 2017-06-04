import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;

public class Cliente {
	public String cliente;
	public String endereco;
	public double valor_imovel;
	public double seguro;
	public static boolean saiu = false, ok = false, cancelar = false;;
	public String check;
	Object[] options = { "Gerar Contrato", "Sair" };

	public void mostraContrato() {
		String nomeArq = JOptionPane.showInputDialog(null, "Informe o seu CPF/CNPJ:\n");

		try {
			if (nomeArq.equals("") == false) {
				nomeArq = nomeArq + ".txt";
				FileReader arq = new FileReader(nomeArq);
				BufferedReader lerArq = new BufferedReader(arq);
				String linha = lerArq.readLine();

				while (linha != null) {
					System.out.printf("%s\n", linha);
					linha = lerArq.readLine();
				}

				arq.close();
			} else if (nomeArq.equals("") == true) {
				throw new NullPointerException();
			}
		} catch (NullPointerException ex) {

		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, "Arquivo não existe!");
		}
	}
}
