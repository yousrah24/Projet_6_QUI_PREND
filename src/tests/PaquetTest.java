package tests;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

import appli.ICarte;
import jeu.Paquet;

class PaquetTest {

	@Test
	void test() {
		Paquet p = new Paquet(104);
		
		// test de la methode getCarte(int)
		ICarte c = p.getCarte(0);
		assertEquals(1, c.getNumCarte());
		ICarte c1 = p.getCarte(30);
		assertEquals(31, c1.getNumCarte());
		
		// test de la methode melanger()
		p.melanger();
		assertFalse(p.getCarte(0).equals(c));
	}

}
