package tests;


import static org.junit.jupiter.api.Assertions.*;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import appli.IJoueur;
import jeu.Partie;

class PartieTest {

	@Test
	void test() {
		Partie p = new Partie("src//..//config1.txt");
		// test de la methode getNbJoueurs
		int n = nbLignes("src//..//config1.txt");
		assertEquals(n, p.getNbJoueurs());
		
		// test de la methode getJoueur
		IJoueur j = p.getJoueur(0);
		assertEquals(j, p.getJoueur(0));
		
		// test de la méthode distribuer()
		p.distribuer();
		assertFalse(j.plusDeCarte());
		
		/*
		 * les autres méthode ne peuvent pas être testées
		 * car ils ont besoin de saisie au clavier de la part de l'utilisateur
		 */
	}

	private int nbLignes(String file) {
		int n = 0;
		try {
			Scanner in = new Scanner(new FileInputStream(file));
			while (in.hasNextLine()) {
				String str = in.nextLine();
				if(str.compareToIgnoreCase("") !=0 )
					n++;
			}
			in.close();
		} 
		catch (FileNotFoundException e) {
			System.out.println("Impossible d'ouvrir le fichier");
		}
		return n;
	}

}
