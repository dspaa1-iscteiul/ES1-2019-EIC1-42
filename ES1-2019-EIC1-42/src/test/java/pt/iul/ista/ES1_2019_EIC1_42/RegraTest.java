package pt.iul.ista.ES1_2019_EIC1_42;

import static org.junit.Assert.*;

import org.junit.Test;

public class RegraTest {

	Regra r= new Regra("regra", Metrica.ATFD, Metrica.LAA, 20, 30, Logic_And_Or.AND);
	@Test
	public void testRegra() {
		assertNotNull(r);
	}
	
	@Test
	public void testGetMetrica_1() {
		assertSame(Metrica.ATFD,r.getMetrica_1());
	}
	
	@Test 
	public void testSetMetrica_1() {
		r.setMetrica_1(Metrica.CYCLO);
		assertSame(Metrica.CYCLO,r.getMetrica_1());
	}
	
	@Test
	public void testGetMetrica_2() {
		assertSame(Metrica.LAA,r.getMetrica_2());
	}
	
	@Test 
	public void testSetMetrica_2() {
		r.setMetrica_2(Metrica.LOC);
		assertSame(Metrica.LOC,r.getMetrica_2());
	}
	
	@Test 
	public void testGetValor_1() {
		assertEquals(20,r.getValor_1());
	}
	
	@Test 
	public void testSetValor_1() {
		r.setValor_1(40);
		assertEquals(40,r.getValor_1());
	}
	
	@Test
	public void testGetValor_2() {
		assertEquals(30,r.getValor_2());
	}
	
	@Test
	public void testSetValor_2() {
		r.setValor_2(50);
		assertEquals(50,r.getValor_2());
	}
	
	@Test
	public void testGetLogico() {
		assertSame(Logic_And_Or.AND, r.getLogico());
	}
	
	@Test
	public void testSetLogico() {
		r.setLogico(Logic_And_Or.OR);
		assertSame(Logic_And_Or.OR,r.getLogico());
	}
	
	@Test
	public void testGetNome() {
		assertEquals("regra",r.getNome());
	}
	
	@Test
	public void testSetNome() {
		r.setNome("regra1");
		assertEquals("regra1",r.getNome());
	}
	
	@Test
	public void testEquals() {
		Regra s= new Regra("regra", Metrica.ATFD, Metrica.LAA, null, 30, Logic_And_Or.AND);
		Regra q= new Regra("regra", Metrica.ATFD, Metrica.LAA, 20, 30, Logic_And_Or.AND);
		assertTrue(r.equals(r));
		assertFalse(r.equals(null));
		q= new Regra("regra", Metrica.ATFD, Metrica.LAA, 20, 30, Logic_And_Or.OR);
		assertFalse(r.equals(q));
		q= new Regra("regra", Metrica.CYCLO, Metrica.LAA, 20, 30, Logic_And_Or.AND);
		assertFalse(r.equals(q));
		q=new Regra("regra", Metrica.ATFD, Metrica.LOC, 20, 30, Logic_And_Or.AND);
		assertFalse(r.equals(q));
		q=new Regra("regra", Metrica.ATFD, Metrica.LAA, 20, 30, Logic_And_Or.AND);
		assertFalse(s.equals(q));
		q=new Regra("regra", Metrica.ATFD, Metrica.LAA, 30, 30, Logic_And_Or.AND);
		assertFalse(r.equals(q));
		s= new Regra("regra", Metrica.ATFD, Metrica.LAA, 20, null, Logic_And_Or.AND);
		q= new Regra("regra", Metrica.ATFD, Metrica.LAA, 20, 30, Logic_And_Or.AND);
		assertFalse(s.equals(q));
		q=new Regra("regra", Metrica.ATFD, Metrica.LAA, 20, 40, Logic_And_Or.AND);
		assertFalse(r.equals(q));
		q=new Regra("regra", Metrica.ATFD, Metrica.LAA, 20, 30, Logic_And_Or.AND);
		assertTrue(r.equals(q));
		s=new Regra("regra", Metrica.ATFD, Metrica.LAA, null, 30, Logic_And_Or.AND);
		q=new Regra("regra", Metrica.ATFD, Metrica.LAA, null, 30, Logic_And_Or.AND);
		assertTrue(s.equals(q));
		s=new Regra("regra", Metrica.ATFD, Metrica.LAA, 20, null, Logic_And_Or.AND);
		q=new Regra("regra", Metrica.ATFD, Metrica.LAA, 20, null, Logic_And_Or.AND);
		assertTrue(s.equals(q));
		int i =2;
		assertFalse(r.equals(i));
		
	}
	
	@Test
	public void testToString() {
		String s="Regra '" + r.getNome() + "' [" + r.getMetrica_1() + ">" + r.getValor_1() + " " + r.getLogico().name() + " " + r.getMetrica_2() + "<"
				+ r.getValor_2() + "] - feature_envy";
		assertEquals(s,r.toString());
	}
	
	@Test
	public void testIsLongMethodRule() {
		assertFalse(r.isLongMethodRule());
		r.setMetrica_1(Metrica.LOC);
		r.setMetrica_2(Metrica.CYCLO);
		assertTrue(r.isLongMethodRule());
	}
	
	@Test
	public void testIsFeatureEnvyRule() {
		assertTrue(r.isFeautureEnvyRule());
		r.setMetrica_1(Metrica.LOC);
		r.setMetrica_2(Metrica.CYCLO);
		assertFalse(r.isFeautureEnvyRule());
	}
	
	@Test
	public void testHashCode() {
		Regra s=new Regra("regra", Metrica.ATFD, Metrica.LAA, 20, 30, Logic_And_Or.AND);
		Regra t=new Regra("regra1",Metrica.LOC, Metrica.CYCLO, 22,33, Logic_And_Or.OR);
		assertEquals(r.hashCode(), s.hashCode());
		assertNotEquals(r.hashCode(), t.hashCode());
	}

}	
