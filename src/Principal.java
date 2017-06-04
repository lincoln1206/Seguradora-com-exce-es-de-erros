import javax.swing.JOptionPane;

public class Principal extends Cliente {

	public static void main(String[] args) {
		ContratoResidencial pessoaFisica = new ContratoResidencial();
		ContratoEmpresarial pessoaJuridica = new ContratoEmpresarial();
		Cliente cliente = new Cliente();
		Object[] menu = { "Fazer Cadastro Pessoa Fisica", "\nFazer Cadastro Pessoa Juridica", "Abrir Contrato",
				"\nSair" };

		int opcao = 0;

		do {

			opcao = JOptionPane.showOptionDialog(null, "***SEGURADORA***", "Seguradora", JOptionPane.DEFAULT_OPTION,
					JOptionPane.WARNING_MESSAGE, null, menu, menu[0]);

			switch (opcao) {
			case 0:
				pessoaFisica.cadastro();
				if (saiu == false) {
					pessoaFisica.calculoSeguroResidencial();
					pessoaFisica.salvarContrato();
				}
				break;

			case 1:
				pessoaJuridica.cadastro();
				if (saiu == false) {
					pessoaJuridica.calculoSeguroEmpresarial();
					pessoaJuridica.salvarContrato();
				}
				break;

			case 2:
				cliente.mostraContrato();
				break;

			case 3:
				break;
				
			}
		} while (opcao != 3);
	}
}
