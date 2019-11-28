package pt.iul.ista.ES1_2019_EIC1_42.gui;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class Nova_RegraTest {
	Nova_Regra n;
	
	
	
	@Test
	public void testNova_Regra() {
		n=new Nova_Regra();
		assertNotNull(n);
		n.dispose();
	}

//	@Test
//	public void testSalvarRegra() {
////		n.salvarRegra();
//		
//		
//	}

}
