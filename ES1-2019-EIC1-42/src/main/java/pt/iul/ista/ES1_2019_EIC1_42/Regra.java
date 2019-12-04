package pt.iul.ista.ES1_2019_EIC1_42;

/**
 * Classe que representa uma regra que foi definida pelo utilizador
 * @author dariop
 *
 */
public class Regra {

	private Metrica metrica_1, metrica_2;
	private Number valor_1, valor_2;
	private Logic_And_Or logico;
	private String nome;

	public Regra(String nome, Metrica metrica_1, Metrica metrica_2, Number valor_1, Number valor_2,
			Logic_And_Or logico) {
		this.nome = nome;
		this.metrica_1 = metrica_1;
		this.metrica_2 = metrica_2;
		this.valor_1 = valor_1;
		this.valor_2 = valor_2;
		this.logico = logico;
	}

	public Metrica getMetrica_1() {
		return metrica_1;
	}

	public void setMetrica_1(Metrica metrica_1) {
		this.metrica_1 = metrica_1;
	}

	public Metrica getMetrica_2() {
		return metrica_2;
	}

	public void setMetrica_2(Metrica metrica_2) {
		this.metrica_2 = metrica_2;
	}

	public Number getValor_1() {
		return valor_1;
	}

	public void setValor_1(Number valor_1) {
		this.valor_1 = valor_1;
	}

	public Number getValor_2() {
		return valor_2;
	}

	public void setValor_2(Number valor_2) {
		this.valor_2 = valor_2;
	}

	public Logic_And_Or getLogico() {
		return logico;
	}

	public void setLogico(Logic_And_Or logico) {
		this.logico = logico;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((logico == null) ? 0 : logico.hashCode());
		result = prime * result + ((metrica_1 == null) ? 0 : metrica_1.hashCode());
		result = prime * result + ((metrica_2 == null) ? 0 : metrica_2.hashCode());
		result = prime * result + ((valor_1 == null) ? 0 : valor_1.hashCode());
		result = prime * result + ((valor_2 == null) ? 0 : valor_2.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Regra other = (Regra) obj;
		if (logico != other.logico)
			return false;
		if (metrica_1 != other.metrica_1)
			return false;
		if (metrica_2 != other.metrica_2)
			return false;
		if (valor_1 == null) {
			if (other.valor_1 != null)
				return false;
		} else if (!valor_1.equals(other.valor_1))
			return false;
		if (valor_2 == null) {
			if (other.valor_2 != null)
				return false;
		} else if (!valor_2.equals(other.valor_2))
			return false;
		return true;
	}

	@Override
	public String toString() {
		String parte1 = "Regra '" + nome + "' [" + metrica_1 + ">" + valor_1 + " " + logico.name() + " " + metrica_2;
		String sinal = ">";
		if(metrica_2==Metrica.LAA)
			sinal = "<";
		return parte1 + sinal + valor_2 + "]";
			
	}
	
	 

}
