package pt.iul.ista.ES1_2019_EIC1_42;

public class Metodo {

	private int methodID;
	private String pacote;
	private String classe;
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

	public Metodo(int methodID, String pacote, String classe, String method, int loc, int cyclo, int atfd, int laa,
			boolean is_long_method, boolean iplasma, boolean pmd, boolean is_feature_envy) {
		super();
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

	public String getPacote() {
		return pacote;
	}

	public void setPacote(String pacote) {
		this.pacote = pacote;
	}

	public boolean isIs_long_method() {
		return is_long_method;
	}

	public void setIs_long_method(boolean is_long_method) {
		this.is_long_method = is_long_method;
	}

	public boolean isIs_feature_envy() {
		return is_feature_envy;
	}

	public void setIs_feature_envy(boolean is_feature_envy) {
		this.is_feature_envy = is_feature_envy;
	}

	public int getMethodID() {
		return methodID;
	}

	public void setMethodID(int methodID) {
		this.methodID = methodID;
	}

	public String getClasse() {
		return classe;
	}

	public void setClasse(String classe) {
		this.classe = classe;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public int getLoc() {
		return loc;
	}

	public void setLoc(int loc) {
		this.loc = loc;
	}

	public int getCyclo() {
		return cyclo;
	}

	public void setCyclo(int cyclo) {
		this.cyclo = cyclo;
	}

	public int getAtfd() {
		return atfd;
	}

	public void setAtfd(int atfd) {
		this.atfd = atfd;
	}

	public double getLaa() {
		return laa;
	}

	public void setLaa(double laa) {
		this.laa = laa;
	}

	public boolean isIplasma() {
		return iplasma;
	}

	public void setIplasma(boolean iplasma) {
		this.iplasma = iplasma;
	}

	public boolean isPmd() {
		return pmd;
	}

	public void setPmd(boolean pmd) {
		this.pmd = pmd;
	}

}
