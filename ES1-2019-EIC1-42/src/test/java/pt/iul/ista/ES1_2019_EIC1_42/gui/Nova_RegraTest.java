package pt.iul.ista.ES1_2019_EIC1_42.gui;

import static org.junit.Assert.assertNotNull;

import java.awt.AWTException;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;

import org.junit.Before;
import org.junit.Test;

public class Nova_RegraTest {
	Nova_Regra n;
	
	
	
	@Test
	public void testNova_Regra() {
		
		new Thread(new Runnable() {
			public void run() {
				try {
					Point p = n.getSave().getLocationOnScreen();
				    Robot r = new Robot();
				    r.delay(1000);
				    r.mouseMove(p.x + n.getSave().getWidth() / 2, p.y +n.getSave().getHeight() / 2);
				    r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
					Thread.sleep(250);
//					n.getNome().setText("regra");
//					n.getSave().doClick();
					r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (AWTException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}).start();
		n=new Nova_Regra();
		
		assertNotNull(n);
	}

//	@Test
//	public void testSalvarRegra() {
////		n.salvarRegra();
//		
//		
//	}

}
