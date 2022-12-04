package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import jeu.Carte;
import jeu.Serie;

class SerieTest {

	@Test
	void test() {
		Carte c = new Carte();
		Carte c1 = new Carte(10);
		Serie s = new Serie();
		
		// test de la methode ajouter(Carte)
		s.ajouter(c);
		s.ajouter(c1);
		assertTrue(s.getCartes().contains(c) && s.getCartes().contains(c1));

		// test de la methode getDernierCarte()
		assertEquals( c1, s.getDernierCarte());

		Serie s1 = new Serie();
		s1.ajouter(new Carte(30));		
		
		// test de la methode compareSerie(Serie, Serie)
		assertTrue(Serie.compareSerie(s, s1) < 0);
		assertFalse(Serie.compareSerie(s1, s) < 0);
		
	}

}
