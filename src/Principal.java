import javax.swing.JOptionPane;

/*Classe principal(Menu)*/
public class Principal extends Cliente {

	/* Menu */
	public static void main(String[] args) {
		ContratoResidencial pessoaFisica = new ContratoResidencial();
		ContratoEmpresarial pessoaJuridica = new ContratoEmpresarial();
		Object[] menu = { "Novo Cadastro", "Abrir Contrato", "Clientes Cadastrados", "\nSair" };
		Object[] pessoas = { "Pessoa Física", "Pessoa Jurídica", "Cancelar" };

		int opcao = 0;

		do {/* Inicio do laço de repetição que só para de "opcao" = 3 */

			/* Menu de opções */
			opcao = JOptionPane.showOptionDialog(null,
					"****************************************SEGURADORA********************************************",
					"Seguradora", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, menu, menu[0]);

			switch (opcao) {/* Início do Switch do Menu */
			case 0:/* Cadastro */
				int opcaoPessoaCadastro = JOptionPane.showOptionDialog(null,
						"Escolha o tipo de cadastro:", "Seguradora", JOptionPane.DEFAULT_OPTION,
						JOptionPane.WARNING_MESSAGE, null, pessoas, pessoas[0]);

				/* Cadastro de pessoa física */
				if (opcaoPessoaCadastro == 0) {
					pessoaFisica.cadastro();
					/* if para se o usuário não saiu do programa */
					if (saiu == false) {
						pessoaFisica.calculoSeguroResidencial();
						pessoaFisica.gerarContrato();
						pessoaFisica.salvarCadastro();
					}
				}

				/* Cadastro de pessoa jurídica */
				else if (opcaoPessoaCadastro == 1) {
					pessoaJuridica.cadastro();

					/* if para se o usuário não saiu do programa */
					if (saiu == false) {
						pessoaJuridica.calculoSeguroEmpresarial();
						pessoaJuridica.gerarContrato();
						pessoaJuridica.salvarCadastro();
					}
				} else {
					break;
				}

				break;

			case 1:/* Mostra contrato através de entrada do usuário */
				mostraContrato();
				break;

			case 2:/* Lê cadastros já realizados no programa */
				int opcaoPessoa = JOptionPane.showOptionDialog(null, "Escolha o tipo dos cadastros que você quer ver:",
						"Seguradora", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, pessoas,
						pessoas[0]);
				if (opcaoPessoa == 0) {
					lerCadastroFisica();
				} else if (opcaoPessoa == 1) {
					lerCadastroJuridica();
				} else {
					break;
				}
				break;
			case 3:/* Sair do programa */
				break;

			}/* Fim do Switch do Menu */
		} while (opcao != 3);/*
								 * Fim do laço de repetição que só para de
								 * "opcao" = 3
								 */
	}
}
