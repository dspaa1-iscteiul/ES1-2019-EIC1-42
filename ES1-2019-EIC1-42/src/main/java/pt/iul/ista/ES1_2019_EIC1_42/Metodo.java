package pt.iul.ista.ES1_2019_EIC1_42;

/**
 * Classe que representa um Metodo
 * 
 * @author rmdca
 *
 */
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

	/**
	 * Construtor
	 * 
	 * @param methodID        - correspondente à identificação do método do projeto
	 *                        Java
	 * @param pacote          - correspondente à identificação do package onde se
	 *                        encontra o método
	 * @param classe          -correspondente à identificação da class onde se
	 *                        encontra o método;
	 * @param method          - correspondente ao nome do metodo
	 * @param loc             - número de linhas de código do método
	 * @param cyclo           - complexidade ciclomática do método
	 * @param atfd            - acessos que o método faz a métodos de outras classes
	 * @param laa             - acessos que o metodo faz a atributos da propria
	 *                        classe
	 * @param is_long_method  - correspondente à classificação correta do defeito
	 *                        is_long_method no método, ou seja, se o defeito long
	 *                        method se encontra presente ou não no método
	 * @param iplasma         - correspondente à classificação que a ferramenta
	 *                        iPlasma faz sobre a presença (ou não) do defeito
	 *                        long_method no método
	 * @param pmd             - correspondente à classificação que a ferramenta PMD
	 *                        faz sobre a presença (ou não) do defeito long_method
	 *                        no método
	 * @param is_feature_envy - is_ feature_envy , correspondente à classificação
	 *                        correta do defeito feature_envy no método, ou seja, se
	 *                        o defeito feature_envy se encontra presente ou não no
	 *                        método.
	 */
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

	/**
	 * 
	 * @return getter para o is_long_method
	 */
	public boolean isIs_long_method() {
		return is_long_method;
	}

	/**
	 * 
	 * @return getter para o is_feature_envy
	 */
	public boolean isIs_feature_envy() {
		return is_feature_envy;
	}

	/**
	 * 
	 * @return getter para o methodID
	 */
	public int getMethodID() {
		return methodID;
	}

	/**
	 * 
	 * @return getter para o loc
	 */
	public int getLoc() {
		return loc;
	}

	/**
	 * 
	 * @return getter para o cyclo
	 */
	public int getCyclo() {
		return cyclo;
	}

	/**
	 * 
	 * @return getter para o atfd
	 */
	public int getAtfd() {
		return atfd;
	}

	/**
	 * 
	 * @return getter para o laa
	 */
	public double getLaa() {
		return laa;
	}

	/**
	 * 
	 * @return gettet para o iPlasma
	 */
	public boolean isIplasma() {
		return iplasma;
	}

	/**
	 * 
	 * @return getter para o pmd
	 */
	public boolean isPmd() {
		return pmd;
	}
}