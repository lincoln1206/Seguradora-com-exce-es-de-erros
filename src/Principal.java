import javax.swing.JOptionPane;

public class Principal extends Cliente {

	public static void main(String[] args) {
		ContratoResidencial pessoaFisica = new ContratoResidencial();
		ContratoEmpresarial pessoaJuridica = new ContratoEmpresarial();
		Cliente cliente = new Cliente();
		Object[] menu = { "Fazer Cadastro Pessoa Fisica", "\nFazer Cadastro Pessoa Juridica", "Abrir Contrato",
				"Ver Clientes Cadastrados", "\nSair" };

		int opcao = 0;

		do {

			opcao = JOptionPane.showOptionDialog(null, "***SEGURADORA***", "Seguradora", JOptionPane.DEFAULT_OPTION,
					JOptionPane.WARNING_MESSAGE, null, menu, menu[0]);

			switch (opcao) {
			case 0:
				pessoaFisica.cadastro();
				pessoaFisica.calculoSeguroResidencial();
				pessoaFisica.salvarCadastro();
				if (saiu == false) {
					pessoaFisica.gerarContrato();
				}
				break;

			case 1:
				pessoaJuridica.cadastro();
				pessoaJuridica.calculoSeguroEmpresarial();
				pessoaJuridica.salvarCadastro();
				if (saiu == false) {
					pessoaJuridica.gerarContrato();
				}
				break;

			case 2:
				cliente.mostraContrato();
				break;

			case 3:
				cliente.lerCadastro();
				break;
			case 4:
				break;

			}
		} while (opcao != 4);
	}
}
