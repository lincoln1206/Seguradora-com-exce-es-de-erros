import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;

import javax.swing.JOptionPane;

public class ContratoResidencial extends PessoaFisica {
	ChecarEntrada checar = new ChecarEntrada();
	ValidarCPF_CNPJ vCPF = new ValidarCPF_CNPJ();

	private String valorImovelChecar;
	private String tipoM;
	private String zonaM;
	private boolean ok = false;

	public void cadastro() {
		cliente = JOptionPane.showInputDialog("Digite o nome do cliente:");

		do {
			try {
				setCpf(JOptionPane.showInputDialog("Digite o CPF do cliente:"));

				if (vCPF.isValidCPF(getCpf()) == true) {
					ok = true;
				} else {
					throw new NumberFormatException();
				}
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "Você digitou um CPF inválido!");
			}

		} while (ok != true);

		ok = false;

		setEndereco(JOptionPane.showInputDialog("Digite o endereco do cliente:"));

		do {
			try {
				valor_imovel = Float.parseFloat(JOptionPane.showInputDialog("Digite o valor do imovel:"));

				valorImovelChecar = String.valueOf(valor_imovel);

				if (checar.isCurrency(valorImovelChecar) == true) {
					ok = true;
				} else {
					throw new NumberFormatException();
				}
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "Você digitou um valor não aceito!");
			}

		} while (ok != true);

		ok = false;

		do {
			try {
				setZona(Integer.parseInt(JOptionPane.showInputDialog("Digite a zona (1-urbana,2-suburbana,3-rural):")));

				if (getZona() == 1 || getZona() == 2 || getZona() == 3) {
					ok = true;
				} else {
					throw new IOException();
				}
			} catch (IOException ex) {
				JOptionPane.showMessageDialog(null, "Voce digitou uma opcao invalida!");
			}
		} while (ok != true);

		ok = false;

		do {
			try {
				setTipo(Integer
						.parseInt(JOptionPane.showInputDialog("Digite o tipo de residencia(1-casa,2-apartamento):")));

				if (getTipo() == 1 || getTipo() == 2) {
					ok = true;
				} else {
					throw new IOException();
				}
			} catch (IOException ex) {
				JOptionPane.showMessageDialog(null, "Voce digitou uma opcao invalida!");
			}
		} while (ok != true);

	}

	public void calculoSeguroResidencial() {

		seguro += valor_imovel * 0.01;

		if (getZona() == 1) {
			seguro += valor_imovel * 0.01;
		}

		if (getZona() == 2) {
			seguro += valor_imovel * 0.025;
		}

		if (getTipo() == 1) {
			seguro += valor_imovel * 0.005;
		}
	}

	public void salvarContrato() {
		NumberFormat f = NumberFormat.getCurrencyInstance();

		try {

			if (getZona() == 1) {
				zonaM = "Urbana";
			}
			if (getZona() == 2) {
				zonaM = "Suburbana";

			}
			if (getZona() == 3) {
				zonaM = "Rural";
			}
			if (getTipo() == 1) {
				tipoM = "Casa";
			}
			if (getTipo() == 2) {
				tipoM = "Predio";
			}

			int opcao2 = JOptionPane.showOptionDialog(null, "Clique na operação a qual deseja realizar:", "Operação",
					JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
			if (opcao2 == 0) {
				File check = new File(getCpf() + ".txt");
				if (check.exists() == true) {
					JOptionPane.showMessageDialog(null,
							"Ja existe um contrato com esse CPF!\nClique em OK para retornar ao menu");
				} else if (check.exists() == false) {
					FileWriter arq = new FileWriter(getCpf() + ".txt");
					PrintWriter gravarArq = new PrintWriter(arq);
					gravarArq.printf("**CONTRATO**%n%nNome do cliente: " + cliente + "%nCPF: " + getCpf()
							+ "%nEndereco: " + getEndereco() + "%n" + tipoM + "%nZona: " + zonaM + "%nValor do imovel: "
							+ f.format(valor_imovel) + "%nValor do seguro: " + f.format(seguro));

					JOptionPane.showMessageDialog(null, "Contrato salvo com sucesso como " + getCpf() + ".txt !");
					arq.close();
				}
			} else if (opcao2 == 1) {
				JOptionPane.showMessageDialog(null, "Você não gerou o contrato!\nClique em OK para retornar ao menu");
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Erro ao salvar contrato!");
		}
	}
}
