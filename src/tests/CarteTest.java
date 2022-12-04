package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import jeu.Carte;

class CarteTest {

	@Test
	void test() {
		Carte c = new Carte();
		Carte c1 = new Carte();
		Carte c2 = new Carte(55);
		
		// test des constructeur et des getter
		assertFalse(c.equals(c1));
		assertEquals(1, c.getTeteBoeuf());
		assertEquals(c1.getNumCarte(), 2);
		assertEquals(c2.getNumCarte(), 55);
		assertEquals(7, c2.getTeteBoeuf());
		
		// test de la comparaison de deux cartes
		assertTrue(Carte.compareNumCarte(c, c1) < 0);
		assertFalse(Carte.compareNumCarte(c, c2) > 0);

	}

}
