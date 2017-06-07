import javax.swing.JOptionPane;

public class Principal extends Cliente {//CLASSE PRINCIPAL

	public static void main(String[] args) {// MENU PRINCIPAL
		ContratoResidencial pessoaFisica = new ContratoResidencial();// CHAMADA
																		// DA
																		// CLASSE
																		// "ContratoResidencial"
		ContratoEmpresarial pessoaJuridica = new ContratoEmpresarial();// CHAMADA
																		// DA
																		// CLASSE
																		// "ContratoEmpresarial"
		Object[] menu = { "Fazer Cadastro Pessoa Fisica", "\nFazer Cadastro Pessoa Juridica", "Abrir Contrato",
				"Ver Clientes Cadastrados", "\nSair" };// OBJETOS PARA SEREM
														// UTILIZADOS COMO OP��O
														// NO MENU

		int opcao = 0;// VARI�VEL "opcao"

		do {// IN�CIO DO WHILE QUE S� SAI DO LA�O SE A "opcao" = 4

			opcao = JOptionPane.showOptionDialog(null, "***SEGURADORA***", "Seguradora", JOptionPane.DEFAULT_OPTION,
					JOptionPane.WARNING_MESSAGE, null, menu, menu[0]);// OP��ES
																		// DO
																		// MENU

			switch (opcao) {// IN�CIO DO SWITCH DO MENU
			case 0:// CADASTRO PESSOA F�SICA
				pessoaFisica.cadastro();

				if (saiu == false) {// IF PARA SE O O USU�RIO N�O SAIU DO
									// PROGRAMA ,CLICOU EM "cancelar" , REALIZAR
									// OS METODOS LOGO ABAIXO
					pessoaFisica.calculoSeguroResidencial();
					pessoaFisica.salvarCadastro();
					pessoaFisica.gerarContrato();
				}
				break;

			case 1:// CADASTRO PESSOA JUR�DICA
				pessoaJuridica.cadastro();

				if (saiu == false) {// IF PARA SE O O USU�RIO N�O SAIU DO
									// PROGRAMA ,CLICOU EM "cancelar" , REALIZAR
									// OS METODOS LOGO ABAIXO
					pessoaJuridica.calculoSeguroEmpresarial();
					pessoaJuridica.salvarCadastro();
					pessoaJuridica.gerarContrato();
				}
				break;

			case 2:// MOSTRA UM CONTRATO PREVIAMENTE SALVO
				mostraContrato();
				break;

			case 3:// L� TODOS OS CADASTRO J� REALIZADOS NO PROGRAMA
				lerCadastro();
				break;
			case 4:
				break;

			}// FIM DO SWITCH
		} while (opcao != 4);// FIM DO WHILE QUE S� SAI DO LA�O SE A "opcao" = 4
	}
}
