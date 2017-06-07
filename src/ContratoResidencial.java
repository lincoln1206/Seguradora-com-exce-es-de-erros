import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.text.NumberFormat;
import javax.swing.JOptionPane;

public class ContratoResidencial extends Cliente implements Interface {
	ChecarEntrada checar = new ChecarEntrada();
	ValidarCPF_CNPJ vCPF = new ValidarCPF_CNPJ();

	private int zona;
	private int tipo;
	private String cpf;
	private String[] tipoA = { "Casa", "Apartamento" };
	private String[] zonaA = { "Urbana", "Suburbana", "Rural" };
	private String tipoM;
	private String zonaM;

	public void cadastro() {
		do {
			cancelar = false;
			ok = false;
			saiu = false;
			// NOME DO CLIENTE
			/////////////////
			do {
				try {
					cliente = JOptionPane.showInputDialog("Digite o nome do cliente:");

					if (cliente != null && cliente.length() > 0) {
						ok = true;
					} else if (cliente.length() == 0 && cliente != null) {
						throw new DigitouNada();
					}
				} catch (NullPointerException ex) {
					cancelar = true;
					saiu = true;
					break;
				} catch (DigitouNada ex) {

				}
			} while (ok == false);

			if (cancelar == true) {
				break;
			}

			ok = false;

			// CPF DO CLIENTE
			////////////////

			do {
				try {
					cpf = JOptionPane.showInputDialog("Digite o CPF do cliente:");

					File check = new File(cpf + ".txt");
					if (check.exists() == true) {
						throw new IOException();
					} else if (vCPF.isValidCPF(cpf) == true && cpf != null && cpf.length() > 0) {
						ok = true;
					} else if (cpf != null && vCPF.isValidCPF(cpf) == false) {
						throw new CPFinvalido();
					} else if (cpf.length() == 0) {
						throw new DigitouNada();
					}
				} catch (CPFinvalido ex) {
				} catch (IOException ex) {
					JOptionPane.showMessageDialog(null, "Já existe um contrato para esse CPF!");
				} catch (NullPointerException ex) {
					cancelar = true;
					saiu = true;
					break;
				} catch (DigitouNada e) {
				}
			} while (ok == false);

			if (cancelar == true) {
				break;
			}

			ok = false;

			// ENDERECO DO CLIENTE
			/////////////////////

			do {
				try {
					endereco = JOptionPane.showInputDialog("Digite o endereco do cliente:");

					if (endereco != null && endereco.length() > 0) {
						ok = true;
					} else if (endereco.length() == 0 && endereco != null) {
						throw new DigitouNada();
					}
				} catch (NullPointerException ex) {
					cancelar = true;
					saiu = true;
					break;
				} catch (DigitouNada ex) {

				}
			} while (ok == false);

			if (cancelar == true) {
				break;
			}

			ok = false;

			// VALOR DO IMOVEL DO CLIENTE
			////////////////////////////

			do {
				try {
					check = JOptionPane.showInputDialog("Digite o valor do imovel:");

					if (checar.isCurrency(check)) {
						ok = true;
					}
				} catch (NullPointerException ex) {
					cancelar = true;
					saiu = true;
					break;
				}
			} while (ok == false);

			if (cancelar == true) {
				break;
			} else {
				valor_imovel = Float.parseFloat(check);
				System.out.println(valor_imovel);
			}

			// ZONA ONDE ESTA O IMOVEL DO CLIENTE
			////////////////////////////////////

			Object[] zonaEscolha = { "Urbana", "Suburbana", "Rural", "Cancelar" };

			zona = JOptionPane.showOptionDialog(null, "Escolha a zona aonde a habitação se encontra:", "Seguradora",
					JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, zonaEscolha, zonaEscolha[0]);

			if (zona == 3) {
				cancelar = true;
				saiu = true;
			}

			if (cancelar == true) {
				break;
			}

			// TIPO DA RERSIDENCIA DO CLEINTE
			////////////////////////////////
			Object[] tipoEscolha = { "Casa", "Apartamento", "Cancelar" };

			tipo = JOptionPane.showOptionDialog(null, "Escolha o tipo da habitação:", "Seguradora",
					JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, tipoEscolha, tipoEscolha[0]);

			if (tipo == 2) {
				cancelar = true;
				saiu = true;
			}

		} while (cancelar == false);

	}

	public void calculoSeguroResidencial() {

		seguro += valor_imovel * 0.01;

		if (zona == 0) {
			seguro += valor_imovel * 0.01;
		}

		if (zona == 1) {
			seguro += valor_imovel * 0.025;
		}

		if (tipo == 0) {
			seguro += valor_imovel * 0.005;
		}
	}

	public void salvarCadastro() {
		Pessoa c = new Pessoa(cliente, cpf, seguro, true);

		try {

			FileOutputStream fos = new FileOutputStream(cpf + ".bin");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(c);

			oos.close();
			fos.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Não foi possível realizar o cadastro!");
		}
	}

	public void gerarContrato() {
		NumberFormat f = NumberFormat.getCurrencyInstance();

		try {

			int i;

			for (i = 0; i <= 2; i++) {
				if (zona == i) {
					zonaM = zonaA[i];
				}
			}

			for (i = 0; i <= 1; i++) {
				if (tipo == i) {
					tipoM = tipoA[i];
				}
			}

			int opcao = JOptionPane.showOptionDialog(null, "Clique na operação a qual deseja realizar:", "Operação",
					JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
			if (opcao == 0) {

				FileWriter arq = new FileWriter(cpf + ".txt");
				PrintWriter gravarArq = new PrintWriter(arq);
				gravarArq.printf("**CONTRATO**%n%nNome do cliente: " + cliente + "%nCPF: " + cpf + "%nEndereço: "
						+ endereco + "%nTipo de residência: " + tipoM + "%nZona: " + zonaM + "%nValor do imóvel: "
						+ f.format(valor_imovel) + "%nValor do seguro: " + f.format(seguro));

				JOptionPane.showMessageDialog(null, "Contrato salvo com sucesso como " + cpf + ".txt !");
				arq.close();

			} else if (opcao == 1) {
				JOptionPane.showMessageDialog(null, "Você não gerou o contrato!\nClique em OK para retornar ao menu");
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Erro ao salvar contrato!");
		}
	}

}
