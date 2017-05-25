import java.io.IOException;

import javax.swing.JOptionPane;

public class Principal {

	public static void main(String[] args) {// inicio da funcao principal
		ContratoResidencial pessoaFisica = new ContratoResidencial();
		ContratoEmpresarial pessoaJuridica = new ContratoEmpresarial();
		Cliente cliente = new Cliente();

		int opcao = 0;

		boolean ok = false;

		do {
			do {
				try {
					opcao = Integer.parseInt(JOptionPane.showInputDialog(
							"**SEGURADORA**\n\n3-Fazer Cadastro Pessoa Fisica \n2-Fazer Cadastro Pessoa Juridica\n1-Abrir Contrato\n0-Sair"));
					if (opcao == 1 || opcao == 2 || opcao == 3) {
						ok = true;
					}
					if (opcao == 0) {
						break;
					} else {
						if (ok != true)
							throw new IOException();
					}
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "Voce digitou uma opcao invalida!");

				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Voce digitou uma opcao invalida!");
				}
			} while (ok != true);

			switch (opcao) {// inicio do switch
			case 3:

				pessoaFisica.cadastro();
				pessoaFisica.calculoSeguroResidencial();
				pessoaFisica.salvarContrato();

				break;

			case 2:
				pessoaJuridica.cadastro();
				pessoaJuridica.calculoSeguroEmpresarial();
				pessoaJuridica.salvarContrato();

				break;

			case 1:
				cliente.mostraContrato();
				break;

			}// fim do switch
		} while (opcao != 0);
	}
}
