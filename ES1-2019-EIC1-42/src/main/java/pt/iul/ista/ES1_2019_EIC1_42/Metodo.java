package pt.iul.ista.ES1_2019_EIC1_42;

public class Metodo {

	private int methodID;
	@SuppressWarnings("unused")
	private String pacote;
	@SuppressWarnings("unused")
	private String classe;
	@SuppressWarnings("unused")
	private String method;
	private int loc;
	private int cyclo;
	private int atfd;
	private double laa;
	private boolean is_long_method;
	private boolean iplasma;
	private boolean pmd;
	private boolean is_feature_envy;
	
	

	public Metodo(int methodID, String pacote, String classe, String method, int loc, int cyclo, int atfd, double laa,
			boolean is_long_method, boolean iplasma, boolean pmd, boolean is_feature_envy) {
		this.methodID = methodID;
		this.pacote = pacote;
		this.classe = classe;
		this.method = method;
		this.loc = loc;
		this.cyclo = cyclo;
		this.atfd = atfd;
		this.laa = laa;
		this.is_long_method = is_long_method;
		this.iplasma = iplasma;
		this.pmd = pmd;
		this.is_feature_envy = is_feature_envy;
	}

	public boolean isIs_long_method() {
		return is_long_method;
	}

	public boolean isIs_feature_envy() {
		return is_feature_envy;
	}

	public int getMethodID() {
		return methodID;
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
	
	public double getLaa() {
		return laa;
	}

	public boolean isIplasma() {
		return iplasma;
	}

	public boolean isPmd() {
		return pmd;
	}

}
