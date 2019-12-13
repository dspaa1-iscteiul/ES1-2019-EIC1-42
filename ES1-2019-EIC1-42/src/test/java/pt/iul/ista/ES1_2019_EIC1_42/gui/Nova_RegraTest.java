/**
 * 
 */
package pt.iul.ista.ES1_2019_EIC1_42.gui;

import static org.junit.Assert.*;

import java.awt.Dialog.ModalityType;
import java.awt.event.MouseEvent;

import javax.swing.JDialog;
import org.junit.BeforeClass;
import org.junit.Test;

import pt.iul.ista.ES1_2019_EIC1_42.Logic_And_Or;
import pt.iul.ista.ES1_2019_EIC1_42.Metrica;
import pt.iul.ista.ES1_2019_EIC1_42.Regra;
import pt.iul.ista.ES1_2019_EIC1_42.RegrasModel;

/**
 * @author dariop
 *
 */
public class Nova_RegraTest {

	private static Nova_Regra nr;
	private static Regra r;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		nr = new Nova_Regra();
		r = new Regra("Teste", Metrica.LOC, Metrica.CYCLO, 1, 2, Logic_And_Or.AND);
		RegrasModel.getInstance().addRegra(r);
	}

	/**
	 * Test method for
	 * {@link pt.iul.ista.ES1_2019_EIC1_42.gui.Nova_Regra#Nova_Regra()}.
	 */
	@Test
	public void testNova_Regra() {
		nr = new Nova_Regra();
		assertNotNull(nr);
		assertEquals(nr.getSize().getWidth(), 490, 0.1);
		assertEquals(nr.getSize().getHeight(), 320, 0.1);
		assertEquals(nr.getTitle(), "Nova Regra");
		assertEquals(nr.getDefaultCloseOperation(), JDialog.DISPOSE_ON_CLOSE);
		assertEquals(nr.getModalityType(), ModalityType.APPLICATION_MODAL);
		nr.getTipo().setSelectedIndex(1);
		nr.getTipo().setSelectedIndex(0);
		nr.getmApagar().doClick();
		nr.getmEditar().doClick();
	}

	/**
	 * Test method for
	 * {@link pt.iul.ista.ES1_2019_EIC1_42.gui.Nova_Regra#editarRegra()}.
	 */
	@Test
	public void testEditarRegra() {
		nr.getRegrasList().setSelectedIndex(0);
		nr.getRegrasList().dispatchEvent(new MouseEvent(nr.getRegrasList(), MouseEvent.MOUSE_CLICKED,
				System.currentTimeMillis(), 0, 150, 150, 2, false));
		nr.getmEditar().doClick();
	}

	/**
	 * Test method for
	 * {@link pt.iul.ista.ES1_2019_EIC1_42.gui.Nova_Regra#preencher(pt.iul.ista.ES1_2019_EIC1_42.Regra)}.
	 */
	@Test
	public void testPreencher() {
		nr.preencher(r);
	}

	/**
	 * Test method for {@link pt.iul.ista.ES1_2019_EIC1_42.gui.Nova_Regra#reset()}.
	 */
	@Test
	public void testReset() {
		nr.reset();
		assertEquals(nr.getTitle(), "Nova Regra");
	}

	/**
	 * Test method for
	 * {@link pt.iul.ista.ES1_2019_EIC1_42.gui.Nova_Regra#removeActionListenersFromSaveButton()}.
	 */
	@Test
	public void testRemoveActionListenersFromSaveButton() {
		nr.removeActionListenersFromSaveButton();
		assertEquals(0, nr.getSave().getActionListeners().length);
	}
}
