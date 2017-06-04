import javax.swing.JOptionPane;

public class CNPJinvalido extends Exception {

	private static final long serialVersionUID = -5601310225608164318L;
	
	public CNPJinvalido(){
		JOptionPane.showMessageDialog(null, "Você digitou um CNPJ inválido!");
    }

}

