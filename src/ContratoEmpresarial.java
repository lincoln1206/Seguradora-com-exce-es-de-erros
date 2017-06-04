import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.NumberFormat;

import javax.swing.JOptionPane;

public class ContratoEmpresarial extends Cliente implements Interface {
	ChecarEntrada checar = new ChecarEntrada();
	ValidarCPF_CNPJ vCNPJ = new ValidarCPF_CNPJ();

	private long numero_funcionarios;
	private long numero_visitas;
	private int ramo;
	private String cnpj;
	private String ramoM;

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
				} catch (DigitouNada e) {
				}
			} while (ok == false);

			if (cancelar == true) {
				break;
			}

			ok = false;

			// CNPJ DO CLIENTE
			//////////////////
			do {
				try {
					cnpj = JOptionPane.showInputDialog("Digite o CNPJ do cliente:");

					File check = new File(cnpj + ".txt");
					if (check.exists() == true) {
						throw new IOException();
					} else if (vCNPJ.isValidCNPJ(cnpj) == true && cnpj != null && cnpj.length() > 0) {
						ok = true;
					} else if (vCNPJ.isValidCPF(cnpj) == false && cnpj.equals("") == false) {
						throw new CNPJinvalido();
					} else if (cnpj.length() == 0) {
						throw new DigitouNada();
					}
				} catch (CNPJinvalido ex) {
				} catch (IOException ex) {
					JOptionPane.showMessageDialog(null, "J� existe um contrato para esse CNPJ!");
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
				} catch (DigitouNada e) {
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
					valor_imovel = Float.parseFloat(JOptionPane.showInputDialog("Digite o valor do imovel:"));

					check = String.valueOf(valor_imovel);

					if (check != null && check.length() > 0 && checar.isCurrency(check) == true) {
						ok = true;
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Voc� digitou um valor n�o aceito ou n�o digitou nada!");
				} catch (NullPointerException ex) {
					cancelar = true;
					saiu = true;
					break;
				}
			} while (ok == false);

			if (cancelar == true) {
				break;
			}

			ok = false;

			// NUMERO DE FUNCIONARIOS DA EMPRESA DO CLIENTE
			//////////////////////////////////////////////
			do {
				try {
					numero_funcionarios = Integer
							.parseInt(JOptionPane.showInputDialog("Digite o numero de funcionarios:"));

					check = String.valueOf(numero_funcionarios);

					if (check != null && check.length() > 0 && checar.isNumeric(check) == true) {
						ok = true;
					}

				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Voc� digitou um valor n�o aceito ou n�o digitou nada!");
				} catch (NullPointerException ex) {
					cancelar = true;
					saiu = true;
					break;
				}
			} while (ok == false);

			if (cancelar == true) {
				break;
			}

			ok = false;

			// NUMERO DE VISITAS DIARIAS A EMPRESA DO CLEINTE
			////////////////////////////////////////////////
			do {
				try {
					numero_visitas = Integer.parseInt(JOptionPane.showInputDialog("Digite o numero de visitas:"));

					check = String.valueOf(numero_visitas);

					if (check != null && check.length() > 0 && checar.isNumeric(check) == true) {
						ok = true;
					}

				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Voc� digitou um valor n�o aceito ou n�o digitou nada!");
				} catch (NullPointerException ex) {
					cancelar = true;
					saiu = true;
					break;
				}
			} while (ok == false);

			if (cancelar == true) {
				break;
			}

			// RAMO DE ATUACAO DA EMPRESA DO CLIENTE
			///////////////////////////////////////
			Object[] ramoEscolha = { "Industria", "Comercio", "Agropecuaria", "Cancelar" };

			ramo = JOptionPane.showOptionDialog(null, "Escolha o ramo de atua��o da empresa:", "Seguradora",
					JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, ramoEscolha, ramoEscolha[0]);

			if (ramo == 3) {
				cancelar = true;
				saiu = true;
			}

			cancelar = true;

		} while (cancelar == false);

	}

	public void calculoSeguroEmpresarial() {
		seguro += valor_imovel * 0.04;

		int i;
		double acPorcentagem = 0, porFunc = 0, porVis = 0;

		if (numero_funcionarios >= 10) {
			porFunc = 0.002;
		}

		if (numero_visitas >= 200) {
			porVis = 0.003;
		}

		for (i = 1; i <= numero_funcionarios; i++) {
			if (i % 10 == 0) {
				porFunc += 0.002;
			}
		}

		for (i = 1; i <= numero_visitas; i++) {
			if (i % 200 == 0) {
				porFunc += 0.003;
			}
		}

		acPorcentagem = porFunc + porVis;

		seguro += valor_imovel * acPorcentagem;

		if (ramo == 0) {
			seguro += valor_imovel * 0.01;
		}

		if (ramo == 1) {
			seguro += valor_imovel * 0.005;
		}
	}

	public void salvarContrato() {
		NumberFormat f = NumberFormat.getCurrencyInstance();

		try {
			if (ramo == 1) {
				ramoM = "Industria";
			}
			if (ramo == 2) {
				ramoM = "Comercio";
			}
			if (ramo == 3) {
				ramoM = "Agropecuaria";
			}

			int opcao2 = JOptionPane.showOptionDialog(null, "Clique na opera��o a qual deseja realizar:", "Opera��o",
					JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
			if (opcao2 == 0) {

				FileWriter arq = new FileWriter(cnpj + ".txt");
				PrintWriter gravarArq = new PrintWriter(arq);
				gravarArq.printf("**CONTRATO**%n%nNome do cliente: " + cliente + "%nCNPJ: " + cnpj + "%nEndere�o: "
						+ endereco + "%nRamo: " + ramoM + "%nValor do im�vel: " + f.format(valor_imovel)
						+ "%nN�mero de Funcion�rios: " + numero_funcionarios + "%nN�mero de visitas: " + numero_visitas
						+ "%nValor do seguro: " + f.format(seguro));

				JOptionPane.showMessageDialog(null, "Contrato salvo com sucesso como " + cnpj + ".txt !");
				arq.close();

			} else if (opcao2 == 1) {
				JOptionPane.showMessageDialog(null, "Voc� n�o gerou o contrato!\nClique em OK para retornar ao menu");

			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Erro ao salvar contrato!");
		}

	}
}
