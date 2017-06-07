import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.NumberFormat;

import javax.swing.JOptionPane;

public class ContratoEmpresarial extends Cliente implements Interface {
	ChecarEntrada checar = new ChecarEntrada();

	private long numero_funcionarios;
	private long numero_visitas;
	private int ramo;
	private String cnpj;
	private String[] ramoA = { "Industria", "Comercio", "Agropecuaria" };
	private String ramoM;
	int i;

	public void cadastro() {
		do {
			cancelar = false;
			ok = false;
			saiu = false;
			/* Entrada do nome do cliente */
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

			/* Entrada do CNPJ do cliente */
			do {
				try {
					cnpj = JOptionPane.showInputDialog("Digite o CNPJ do cliente:");

					File check = new File(cnpj + ".txt");
					if (check.exists() == true) {
						throw new IOException();
					} else if (checar.isValidCNPJ(cnpj) == true && cnpj != null && cnpj.length() > 0) {
						ok = true;
					} else if (cnpj != null && checar.isValidCPF(cnpj) == false) {
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

			/* Entrada do endere�o do cliente */
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

			/* Entrada do valor do im�vel do cliente */
			do {
				try {
					check = JOptionPane.showInputDialog("Digite o valor do imovel:");

					if (checar.isCurrency(check) == true) {
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

			ok = false;

			/* Entrada do n� de funcion�rios da empresa do cliente */
			do {
				try {
					check = JOptionPane.showInputDialog("Digite o numero de funcionarios:");

					if (checar.isNumeric(check) == true) {
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
				numero_funcionarios = Integer.parseInt(check);
				System.out.println(numero_funcionarios);
			}

			ok = false;

			/* Entrada do n� de visitas di�rias a empresa do cliente */
			do {
				try {
					check = JOptionPane.showInputDialog("Digite o numero de visitas:");

					if (checar.isNumeric(check) == true) {
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
				numero_visitas = Integer.parseInt(check);
				System.out.println(numero_visitas);
			}

			/* Entrada para ramo de atua��o da empresa do cliente */
			Object[] ramoEscolha = { "Industria", "Comercio", "Agropecuaria", "Cancelar" };

			ramo = JOptionPane.showOptionDialog(null, "Escolha o ramo de atua��o da empresa:", "Seguradora",
					JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, ramoEscolha, ramoEscolha[0]);

			/*
			 * Verifica qual ramo o usu�rio escolheu e busca na String ramoA[] a
			 * String correspondente
			 */
			for (i = 0; i <= 2; i++) {
				if (ramo == i) {
					ramoM = ramoA[i];
				}
			}

			if (ramo == 3) {
				cancelar = true;
				saiu = true;
			}

			break;

		} while (cancelar == false);

	}

	/* Calcula o valor do seguro empresarial */
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

	/* Salva cadastro em ".bin" */
	public void salvarCadastro() {
		PessoaJuridica c = new PessoaJuridica(cliente, cnpj, endereco, seguro, numero_funcionarios, numero_funcionarios,
				ramoM, seguro, fezContrato);

		try {

			FileOutputStream fos = new FileOutputStream(cnpj + ".PessoaJuridica.bin");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(c);

			oos.close();
			fos.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "N�o foi poss�vel realizar o cadastro!");
		}
	}

	/* Gerador de contrato */
	public void gerarContrato() {
		/* Formata o valor para nossa moeda */
		NumberFormat f = NumberFormat.getCurrencyInstance();

		try {
			/* Pergunta se o usu�rio quer gerar o contrato */
			int opcao = JOptionPane.showOptionDialog(null, "Clique na opera��o a qual deseja realizar:", "Opera��o",
					JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
			/* if salva arquivo */
			if (opcao == 0) {

				FileWriter arq = new FileWriter(cnpj + ".txt");
				PrintWriter gravarArq = new PrintWriter(arq);
				gravarArq.printf("**CONTRATO**%n%nNome do cliente: " + cliente + "%nCNPJ: " + cnpj + "%nEndere�o: "
						+ endereco + "%nRamo: " + ramoM + "%nValor do im�vel: " + f.format(valor_imovel)
						+ "%nN�mero de Funcion�rios: " + numero_funcionarios + "%nN�mero de visitas: " + numero_visitas
						+ "%nValor do seguro: " + f.format(seguro));

				JOptionPane.showMessageDialog(null, "Contrato salvo com sucesso como " + cnpj + ".txt !");
				fezContrato = true;
				arq.close();

			} else if (opcao == 1) {/*
									 * if n�o salva arquivo e retorna ao menu
									 * principal
									 */
				JOptionPane.showMessageDialog(null, "Voc� n�o gerou o contrato!\nClique em OK para retornar ao menu");

			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Erro ao salvar contrato!");
		}

	}
}
