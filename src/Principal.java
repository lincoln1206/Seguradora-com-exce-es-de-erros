import javax.swing.JOptionPane;

/*Classe principal(Menu)*/
public class Principal extends Cliente {

	/* Menu */
	public static void main(String[] args) {
		ContratoResidencial pessoaFisica = new ContratoResidencial();
		ContratoEmpresarial pessoaJuridica = new ContratoEmpresarial();
		Object[] menu = { "Fazer Cadastro Pessoa Fisica", "\nFazer Cadastro Pessoa Juridica", "Abrir Contrato",
				"Ver Clientes Cadastrados", "\nSair" };
		Object[] pessoas = {"Pessoa F�sica", "Pessoa Jur�dica", "Cancelar"};

		int opcao = 0;

		do {/* Inicio do la�o de repeti��o que s� para de "opcao" = 4 */

			/* Menu de op��es */
			opcao = JOptionPane.showOptionDialog(null, "***SEGURADORA***", "Seguradora", JOptionPane.DEFAULT_OPTION,
					JOptionPane.WARNING_MESSAGE, null, menu, menu[0]);

			switch (opcao) {/* In�cio do Switch do Menu */
			case 0:/* Cadastro de pessoa f�sica */
				pessoaFisica.cadastro();

				/* if para se o usu�rio n�o saiu do programa */
				if (saiu == false) {
					pessoaFisica.calculoSeguroResidencial();
					pessoaFisica.gerarContrato();
					pessoaFisica.salvarCadastro();
				}
				break;

			case 1:/* Cadastro de pessoa jur�dica */
				pessoaJuridica.cadastro();

				/* if para se o usu�rio n�o saiu do programa */
				if (saiu == false) {
					pessoaJuridica.calculoSeguroEmpresarial();
					pessoaJuridica.gerarContrato();
					pessoaJuridica.salvarCadastro();
				}
				break;

			case 2:/* Mostra contrato atrav�s de entrada do usu�rio */
				mostraContrato();
				break;

			case 3:/* L� cadastros j� realizados no programa */
				int opcaoPessoa = JOptionPane.showOptionDialog(null, "Escolha o tipo dos Contratos que voc� quer ver", "Seguradora", JOptionPane.DEFAULT_OPTION,
						JOptionPane.WARNING_MESSAGE, null, pessoas, pessoas[0]);
				if(opcaoPessoa==0){
					lerCadastroFisica();
				}
				if(opcaoPessoa==1){
					lerCadastroJuridica();
				}
				else{
					break;
				}
				break;
			case 4:/* Sair do programa */
				break;

			}/* Fim do Switch do Menu */
		} while (opcao != 4);/*
								 * Fim do la�o de repeti��o que s� para de
								 * "opcao" = 4
								 */
	}
}
