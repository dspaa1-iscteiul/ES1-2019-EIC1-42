package pt.iul.ista.ES1_2019_EIC1_42;

public class Metodo {

	private int methodID;
	private String pacote;
	private String classe;
	private String method;
	private int loc;
	private int cyclo;
	private int atfd;
	private int laa;
	private boolean is_long_method;
	private boolean iplasma;
	private boolean pmd;
	private boolean is_feature_envy;

	public int getMethodID() {
		return methodID;
	}

	public String getClasse() {
		return classe;
	}

	public String getMethod() {
		return method;
	}

	public int getLoc() {
		return loc;
	}

	public int getCyclo() {
		return cyclo;
	}

	public int getAtfd() {
		return atfd;
	}

	public int getLaa() {
		return laa;
	}

	public boolean isLongMethod() {
		return is_long_method;
	}

	public boolean isIplasma() {
		return iplasma;
	}

	public boolean isPmd() {
		return pmd;
	}

	public boolean isFeatureEnvy() {
		return is_feature_envy;
	}

}
