public class PessoaJuridica extends Cliente{
	private long numero_funcionarios;
	private long numero_visitas;
	private int ramo;
	private String cnpj;
	
	public long getNumero_funcionarios() {
		return numero_funcionarios;
	}
	public void setNumero_funcionarios(long numero_funcionarios) {
		this.numero_funcionarios = numero_funcionarios;
	}
	public long getNumero_visitas() {
		return numero_visitas;
	}
	public void setNumero_visitas(long numero_visitas) {
		this.numero_visitas = numero_visitas;
	}
	public int getRamo() {
		return ramo;
	}
	public void setRamo(int ramo) {
		this.ramo = ramo;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
}

