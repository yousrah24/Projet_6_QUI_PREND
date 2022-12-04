package jeu;


import java.io.FileInputStream;



import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

import appli.ICarte;
import appli.IJoueur;
import appli.ISerie;



public class Partie {
	
	private LinkedList<IJoueur> joueurs =  new LinkedList<>();;
	private Paquet paquet = new Paquet(104);
	private ISerie[] series;
	private ArrayList<ICarte> cartesJouees = new ArrayList<>();

	public Partie(String file) { 
		setJoueurs(file);
		series = new Serie[3];
		Arrays.fill(series, new Serie());
	}
	/**
	 * ajoute les joueurs donn� dans un fichier dans la liste de joueurs
	 * @param file le fichier
	 */
	private void setJoueurs(String file) {
		try {
			Scanner in = new Scanner(new FileInputStream(file));
			while (in.hasNextLine()) {
				String str = in.nextLine();
				if(str.compareToIgnoreCase("") != 0)
					joueurs.addLast(new Joueur(str));
			}
			in.close();
		} 
		catch (FileNotFoundException e) {
			System.out.println("Impossible d'ouvrir le fichier :" + e.getMessage());
		}
	}
	
	/**
	 * renvoie le joueur � l'index i de la liste des joueurs.
	 * i doit �tre sup�rieur ou egale � 0
	 * @param i l'index du joueur
	 * @return le joueur
	 */
	public IJoueur getJoueur(int i) {
		assert(i>=0 && i < getNbJoueurs());
		return joueurs.get(i);
	}
	
	/**
	 * renvoie le nombre de joueurs qui joue cette partie
	 * @return le nombre de joueurs
	 */
	public int getNbJoueurs() {
		return joueurs.size();
	}
	
	/**
	 * distribue les cartes au joueurs 
	 * et pose sur la table les 4 cartes de chaque s�rie apr�s les avoir melanger
	 */
	public void distribuer() {
		// on melange le paquet de cartes
		paquet.melanger();
		int n = 0, j = 0;
		// on distribue les 10 cartes � chaque joueur 
		while (n < getNbJoueurs()) {
			for (int i = 0; i < 10; i++) {
				getJoueur(n).ajouter(paquet.getCarte(j++));
			}
			n++;
		}
		//on pose les 4 cartes sur la table pour chaque s�rie
		for (int i = 0; i < series.length; i++) {
			series[i].ajouter(paquet.getCarte(j++));
		}
	}
	
	/**
	 * trie les cartes jouees par les joueurs
	 */
	private void trierCartesJouees() {
		for(IJoueur j : joueurs)
			this.cartesJouees.add(j.getCarteJouee());
		Collections.sort(cartesJouees, Carte :: compareNumCarte);
	}
	
	/**
	 * renvoie le joueur qui a jou� cette carte jouee
	 * @param c la carte jouee
	 * @return le joueur
	 */
	private IJoueur indexofPlayer(ICarte c) {
		for(IJoueur j : joueurs) {
			if(j.getCarteJouee().equals(c))
				return j;
		}
		return null;	
	}
	
	/**
	 * renvoie l'index de la s�rie o� on peut poser une carte
	 * @param c la carte
	 * @return l'index de la s�rie
	 * si la carte est plus grande que la derni�re carte de la s�rie
	 * alors on prend son index 
	 * */
	private int plusPetiteSerie(ICarte c) {
		ArrayList<ISerie> tmp = new ArrayList<>(Arrays.asList(series));
		Collections.sort(tmp, Serie :: compareSerie);
		int min = -1;
		for (ISerie s : tmp) {
			if (Carte.compareNumCarte(c, s.getDernierCarte()) > 0)
				min = s.getNum();
		}
		return min;
	}
	
	/**
	 * renvoie le numero de serie que le joueur souhaite ramasser les cartes
	 * @param j le joueur
	 * @param c la carte jou�
	 * @return le num�ro de serie
	 */
	private int saisirSerie(IJoueur j, ICarte c) {
		System.out.println(this + "vont �tre pos�es.");
		System.out.println("Pour poser la carte " + c.getNumCarte() + ", " + j + " doit choisir la s�rie qu'il va ramasser.");
		this.afficherSeries();
		System.out.print("Saisissez votre choix : ");
		@SuppressWarnings("resource")
		Scanner clavier = new Scanner(System.in);
		int n;
		while (true) {
			if(clavier.hasNextInt()) {
				n = clavier.nextInt();
				if (1 <= n && n <= 4)
					return n;
				else
					System.out.print("Ce n'est pas une s�rie valide, saisissez votre choix : ");
				
			}
			else {
				clavier.next();
				System.out.print("Ce n'est pas une s�rie valide, saisissez votre choix : ");
			}
		}

	}
	
	/**
	 * supprime les cartes d'une s�rie qui ont �t� ramass� par un joueur
	 * @param i l'index du la s�rie
	 * @param j le joueur 
	 */
	private void supprimerCartesRamassees(int i, IJoueur j) {
		for (ICarte c : series[i].getCartes()) {
			j.setTeteRamassee(c.getTeteBoeuf()); //on ajoute la tete de boeuf de la carte ramass� par le joueur
			j.setScore(c.getTeteBoeuf()); // on ajoute la tete de boeuf de la carte au score du joueur
		}
		series[i].getCartes().clear(); // on supprime tous les cartes de la s�rie
	}
	
	/**
	* pose les cartes jou�es dans l'ordre croissante 
	*si la carte � poser est plus petit que tous les cartes des series 
	* alors le joueur choisie une serie qu'il va ramasser les cartes 
	* avant de poser sa carte 
	* sinon si la serie dispose de 5 cartes 
	* alors le joueur ramass�s les cartes avant de poser la carte
	* on pose la carte sur la s�rie 
	* et on supprime la carte de la pioche du joueur 
	* */
	public void poser() {
		this.trierCartesJouees();
		for (ICarte c : cartesJouees) {
			int s = this.plusPetiteSerie(c); // index de la serie o� on peut poser la carte
			IJoueur j = this.indexofPlayer(c); // index du joueur qui a jou� la carte
			if (s == -1) {
				int n = this.saisirSerie(j, c);
				this.supprimerCartesRamassees(n - 1, j);
				s = n - 1;
			}
			else if (series[s].getCartes().size() == 5) 
				this.supprimerCartesRamassees(s, j);
			series[s].ajouter(c);
			j.supprimer(c);
		}
	}
	
	/**
	 * supprime le contenu de la liste des cartes jou�es apres avoir ete pose
	 */
	public void nettoyerCartesJouees() {
		this.cartesJouees.clear();
	}
		
	/**
	 * determine si une partie est fini si aucun joueur ne dispose de cartes dans sa pioche
	 * @return true ou false
	 */
	public boolean estfini() {
		for (IJoueur j : joueurs) {
			if (!j.plusDeCarte()) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * affiche les joueurs d'une partie
	 */
	public String afficherJoueurs() {
		// on affiche les joueurs
		String str = "Les " + getNbJoueurs() + " joueurs sont ";
		for (int k = 0; k < getNbJoueurs() - 2; k++) {
			str+= getJoueur(k) + ", ";
		}
		return str+= getJoueur(getNbJoueurs() - 2) + " et " + getJoueur(getNbJoueurs() - 1) + ". Merci de jouer � 6 qui prend !\n";
	}

	/**
	 * affiche les s�ries avec leurs cartes
	 */
	public String afficherSeries() {
		String str = "";
		for (int i = 0; i < 4; i++) {
			str+= "- s�rie n� " + (i + 1) + " : " + series[i].toString();
		}
		return str;
	}
	
	/**
	 * affiche le nombre de t�te de boeufs ramass�es � chaque tour par joueur
	 */
	public String afficherTetesRamassees() {
		ArrayList<IJoueur> tetes = new ArrayList<>(joueurs);
		Collections.sort(tetes, Joueur :: compareTeteRamassee); // trie les joueurs selon leur nombre de tete de boeufs ramass�e
		boolean trouve = false;
		for (IJoueur j : tetes) {
			if (j.getTeteRamassee() == 1) {
				trouve = true;
				return j.toString() + " a ramass� " + j.getTeteRamassee() + " t�te de boeufs\n";
			} 
			else if (j.getTeteRamassee() != 0 && j.getTeteRamassee() != 1) {
				trouve = true;
				return j.toString() + " a ramass� " + j.getTeteRamassee() + " t�tes de boeufs\n";
			}
			j.initialiserTeteRamassee();
		}
		if (!trouve) {
			return "Aucun joueur ne ramasse de t�te de boeufs.\n";
		}
		return "";
	}
	
	/**
	 * affiche le score final des joueurs � la fin d'une partie
	 */
	public void afficherScoresfinal() {
		ArrayList<IJoueur> scores = new ArrayList<>(this.joueurs);
		Collections.sort(scores, Joueur :: compareScore); // trie les joueurs selon leur score
		System.out.println("** Score final");
		for (IJoueur j : scores) {
			if (j.getScore() == 1 || j.getScore() == 0)
				System.out.println(j + " a ramass� " + j.getScore() + " t�te de boeufs");
			else
				System.out.println(j + " a ramass� " + j.getScore() + " t�tes de boeufs");
		}
	}
	
	/**
	 * affiche les cartes jou�es par joueur pendant un tour dans l'ordre croissant 
	 */
	public String toString() {
		String str = "Les cartes ";
		for (int i = 0; i < cartesJouees.size() - 2; i++) {
			ICarte c = cartesJouees.get(i);
			IJoueur j = this.indexofPlayer(c);
			str+= c.getNumCarte() + " (" + j + ")" + ", ";
		}
		int i = cartesJouees.size();
		return str+= cartesJouees.get(i - 2).getNumCarte() + " (" + this.indexofPlayer(cartesJouees.get(i - 2)) + ") et " + cartesJouees.get(i - 1).getNumCarte() + " (" + this.indexofPlayer(cartesJouees.get(i - 1)) + ")";
	}
}
