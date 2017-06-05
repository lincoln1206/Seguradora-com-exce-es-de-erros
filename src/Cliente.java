import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

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
			if (nomeArq != null && nomeArq.equals("") == false) {
				JTextArea ta = new JTextArea(20, 20);
				nomeArq = nomeArq + ".txt";
				ta.read(new FileReader(nomeArq), null);
				ta.setEditable(false);
				JOptionPane.showMessageDialog(null, new JScrollPane(ta));
			} else if (nomeArq != null && nomeArq.equals("") == true) {
				throw new DigitouNada();
			}
		} catch (NullPointerException ex) {

		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, "Arquivo não existe!");
		} catch (IllegalArgumentException ex) {
			JOptionPane.showMessageDialog(null, "Arquivo não existe!");
		} catch (DigitouNada e) {
		}
	}
}
