package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import jeu.Carte;
import jeu.Joueur;

class JoueurTest {

	@Test
	void test() {
		Joueur j = new Joueur("Lily");
		Joueur j1 = new Joueur("Sarah");
		
		// test du constructeur de Joueur
		assertTrue(j.getScore() == 0 && j1.getScore() == 0);
		assertTrue(j.getTeteRamassee() == 0 && j1.getTeteRamassee() == 0);
		assertFalse(j.toString() == j1.toString());
		
		//test la méthode plusDeCarte()
		assertTrue(j.plusDeCarte() && j1.plusDeCarte());
		
		// test de la méthode setScore(int)
		j.setScore(20);
		j1.setScore(30);
		assertTrue(j.getScore() == 20 && j1.getScore() == 30);
		
		// test de la méthode compareScore(Joueur, Joueur)
		assertTrue(Joueur.compareScore(j1, j) > 0);
		
		// test de la méthode setTeteRamassee()
		j.setTeteRamassee(2);
		j1.setTeteRamassee(3);
		assertTrue(j.getTeteRamassee() == 2 && j1.getTeteRamassee() == 3);
		
		// test methode compareTeteRamassee(Joueur, Joueur)
		assertTrue(Joueur.compareTeteRamassee(j, j1) < 0);
		
		// test de la méthode initialiserTeteRamassee()
		j1.initialiserTeteRamassee();
		assertTrue(j1.getTeteRamassee() == 0);
		
		// test de la methode ajouter(Carte)
		Carte c = new Carte();
		Carte c1 = new Carte(60);
		j.ajouter(c); j.ajouter(c1);
		j1.ajouter(c1);
		assertFalse(j.plusDeCarte() && j1.plusDeCarte());
		assertEquals(j.getCarte(0), c);
		assertEquals(j1.getCarte(0), c1);
		
		// test methode piocher(int)
		assertEquals(1, j.piocher(60));
		assertEquals(-1, j1.piocher(10));
		
		// test de la methode supprimer(Carte)
		j.supprimer(c);
		assertEquals(j.getCarte(0), c1);
		
		
	}

}
