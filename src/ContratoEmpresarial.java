import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.NumberFormat;

import javax.swing.JOptionPane;

public class ContratoEmpresarial extends PessoaJuridica {
	ChecarEntrada checar = new ChecarEntrada();
	ValidarCPF_CNPJ vCNPJ = new ValidarCPF_CNPJ();

	private String valorImovelChecar;
	private String checarEntrada;
	private String ramoM;
	private boolean ok = false;

	public void cadastro() {
		cliente = JOptionPane.showInputDialog("Digite o nome do cliente:");

		do {
			try {
				setCnpj(JOptionPane.showInputDialog("Digite o CNPJ do cliente:"));
				
				File check = new File(getCnpj() + ".txt");
				if (check.exists() == true){
					throw new IOException();
				}
				if (vCNPJ.isValidCNPJ(getCnpj()) == true) {
					ok = true;
				} else {
					throw new NumberFormatException();
				}
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "Você digitou um CNPJ inválido!");
			}catch(IOException e){
				JOptionPane.showMessageDialog(null, "Já existe um contrato para esse CNPJ!");
			}

		} while (ok != true);

		ok = false;

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
				setNumero_funcionarios(
						Integer.parseInt(JOptionPane.showInputDialog("Digite o numero de funcionarios:")));

				checarEntrada = String.valueOf(getNumero_funcionarios());
				if (checar.isNumeric(checarEntrada) == true) {
					ok = true;
				}
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "Você digitou um valor não aceito!");
			}

		} while (ok != true);

		ok = false;

		do {
			try {
				setNumero_visitas(Integer.parseInt(JOptionPane.showInputDialog("Digite o numero de visitas diarias:")));

				checarEntrada = String.valueOf(getNumero_visitas());
				if (checar.isNumeric(checarEntrada) == true) {
					ok = true;
				}
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "Você digitou um valor não aceito!");
			}

		} while (ok != true);

		ok = false;

		do {
			try {
				setRamo(Integer.parseInt(JOptionPane
						.showInputDialog("Digite o ramo de atuacao(1-Industria 2-Comercio 3-Agropecuaria):")));

				if (getRamo() == 1 || getRamo() == 2 || getRamo() == 3) {
					ok = true;
				} else {
					throw new IOException();
				}
			} catch (IOException ex) {
				JOptionPane.showMessageDialog(null, "Voce digitou uma opcao invalida!");
			}

		} while (ok != true);

	}

	public void calculoSeguroEmpresarial() {
		seguro += valor_imovel * 0.04;

		int i;
		double acPorcentagem = 0, porFunc = 0, porVis = 0;

		if (getNumero_funcionarios() >= 10) {
			porFunc = 0.002;
		}

		if (getNumero_visitas() >= 200) {
			porVis = 0.003;
		}

		for (i = 1; i <= getNumero_funcionarios(); i++) {
			if (i % 10 == 0) {
				porFunc += 0.002;
			}
		}

		for (i = 1; i <= getNumero_visitas(); i++) {
			if (i % 200 == 0) {
				porFunc += 0.003;
			}
		}

		acPorcentagem = porFunc + porVis;

		seguro += valor_imovel * acPorcentagem;

		if (getRamo() == 1) {
			seguro += valor_imovel * 0.01;
		}

		if (getRamo() == 2) {
			seguro += valor_imovel * 0.005;
		}
	}

	public void salvarContrato() {
		NumberFormat f = NumberFormat.getCurrencyInstance();

		try {
			if (getRamo() == 1) {
				ramoM = "Industria";
			}
			if (getRamo() == 2) {
				ramoM = "Comercio";
			}
			if (getRamo() == 3) {
				ramoM = "Agropecuaria";
			}

			int opcao2 = JOptionPane.showOptionDialog(null, "Clique na operação a qual deseja realizar:", "Operação",
					JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
			if (opcao2 == 0) {
				File check = new File(getCnpj()+".txt");
				if(check.exists()==true){
					JOptionPane.showMessageDialog(null, "Ja existe um contrato com esse CNPJ!\nClique em para retornar ao menu");
				}
				else if(check.exists()==false){
					FileWriter arq = new FileWriter(getCnpj() + ".txt");
					PrintWriter gravarArq = new PrintWriter(arq);
					gravarArq.printf("**CONTRATO**%n%nNome do cliente: " + cliente + "%nCNPJ: " + getCnpj() + "%nRamo: "
							+ ramoM + "%nValor do imovel: " + f.format(valor_imovel) + "%nNumero de Funcionarios: "
							+ getNumero_funcionarios() + "%nNumero de visitas: " + getNumero_visitas()
							+ "%nValor do seguro: " + f.format(seguro));

					JOptionPane.showMessageDialog(null, "Contrato salvo com sucesso como " + getCnpj() + ".txt !");
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
