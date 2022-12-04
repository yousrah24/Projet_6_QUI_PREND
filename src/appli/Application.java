package appli;

import static util.Console.clearScreen;

import static util.Console.pause;
import java.util.Scanner;

import jeu.Partie;

public class Application {
	
	/**
	 * saisie du numéro de carte par le joueur
	 * @param j le joueur
	 * @return l'index de la carte saisie dans la liste des cartes du joueur
	 */
	private static int saisir(IJoueur j){
		@SuppressWarnings("resource")
		Scanner clavier = new Scanner(System.in);
		do {
			if(clavier.hasNextInt()) {
				int n = clavier.nextInt();
				int num = j.piocher(n);
				if(num == -1)
					System.out.print("Vous n'avez pas cette carte, saisissez votre choix : ");
				else
					return num;	
			}
			else {
				clavier.next();
				System.out.print("Vous n'avez pas cette carte, saisissez votre choix : ");
			}		
		}while(true);
	}

	public static void main(String[] args){
		Partie jeu = new Partie("src\\..\\config1.txt");
		
		if(jeu.getNbJoueurs() < 2) {
			System.out.println("Il faut au moins 2 joueurs pour pouvoir jouer.");
		}
		else if(jeu.getNbJoueurs() > 10) {
			System.out.println("Il y a trop de joueurs, le maximum est de 10 joueurs.");
		}
		else {
			int n;
			jeu.distribuer();
			jeu.afficherJoueurs();
			do {
				for (int i = 0; i < jeu.getNbJoueurs(); i++) {
					IJoueur j = jeu.getJoueur(i);
					System.out.println("A " + j +" de jouer.");
					pause();
					jeu.afficherSeries();
					j.afficherCartes();
					System.out.print("Saisissez votre choix : ");
					n = saisir(j); // le  joueur saisie une carte
					j.setCarteJouee(j.getCarte(n));
					clearScreen();
				}
				jeu.poser();
				System.out.println(jeu + "ont été posées.");
				System.out.println(jeu.afficherSeries()); 
				System.out.println(jeu.afficherTetesRamassees());
				jeu.nettoyerCartesJouees();
				
			}while(!jeu.estfini());
			//a la fin d'une  partie on affiche le score final
			jeu.afficherScoresfinal();
		}
	}

}
