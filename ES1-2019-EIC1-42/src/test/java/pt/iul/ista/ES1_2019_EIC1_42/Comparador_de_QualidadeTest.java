package pt.iul.ista.ES1_2019_EIC1_42;

import static org.junit.Assert.*;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.junit.BeforeClass;
import org.junit.Test;

import pt.iul.ista.ES1_2019_EIC1_42.gui.Comparador_de_Qualidade;

public class Comparador_de_QualidadeTest {
	static Comparador_de_Qualidade c;
	static Regra r;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		new Thread(new Runnable() {

			public void run() {
				try {
					Robot r = new Robot();
					r.delay(1000);
					r.keyPress(KeyEvent.VK_ESCAPE);
				} catch (AWTException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}).start();
		c = Comparador_de_Qualidade.getInstance();
		
		r = new Regra("regra",Metrica.LOC,Metrica.LOC, 10,10, Logic_And_Or.AND);
	}

	@Test
	public void constructorTest() {
		assertTrue(c!=null);
	}
	
	@Test
	public void addRegraTest() {
		c.addRegra(r);
	}

}
