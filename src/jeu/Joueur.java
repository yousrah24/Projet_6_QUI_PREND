package jeu;

import java.util.ArrayList;

import appli.ICarte;
import appli.IJoueur;


public class Joueur implements IJoueur{

	private String prenom;
	private int score;
	private int teteRamassee;
	private ICarte carteJouee;
	private ArrayList<ICarte> cartes;
	
	public Joueur(String prenom) {
		if(prenom.length() > 1)
			prenom = prenom.substring(0,1).toUpperCase() +  prenom.substring(1).toLowerCase();
		this.prenom = prenom;
		this.setScore(0);
		this.setTeteRamassee(0);
		this.cartes = new ArrayList<>();
	}
	
	/**
	 * affiche le prenom du joueur
	 */
	public String toString() {
		return this.prenom;
	}
	
	/**
	 * ajoute le nombre de tete ramassee pendant un tour 
	 * @param score le nombre de tete ramassee
	 */
	public void setScore(int score) {
		assert(score >= 0);
		this.score+= score;
	}
	
	/**
	 * renvoie le score d'une partie 
	 * @return le score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * ajoute le nombre de tete ramassee pendant un tour
	 * @param teteRamassee le nombre de tete ramassée
	 */
	public void setTeteRamassee(int teteRamassee) {
		assert(teteRamassee >= 0);
		this.teteRamassee += teteRamassee;
	}
	
	/**
	 * renvoie le nombre de tête de boeufs ramassées pendant un tour
	 * @return le nombre de tête de boeufs
	 */
	public int getTeteRamassee() {
		return teteRamassee;
	}
	
	/**
	 * renvoie la carte posée pendant un tour
	 * @return la carte
	 */
	public ICarte getCarteJouee() {
		return carteJouee;
	}

	/**
	 * attribue la carte posée pendant un tour
	 * @param carteJouee la carte
	 */
	public void setCarteJouee(ICarte carteJouee) {
		this.carteJouee = carteJouee;
	}
	
	/**
	 * remet à 0 le compteur de tête de bouefs ramassées d'un joueur pendant un tour
	 */
	public void initialiserTeteRamassee() {
		this.teteRamassee = 0;
	}
	
	/**
	 * ajoute une carte dans la pioche du joueur
	 * @param Carte la carte
	 */
	public void ajouter(ICarte Carte) {
		this.cartes.add((Carte) Carte);
	}
	
	/**
	 * renvoie la carte à l'index i
	 * @param i l'index de la carte
	 * @pre i doit être positif 
	 * @return la carte
	 */
	public ICarte getCarte(int i) {
		assert(i>=0);
		if(!this.plusDeCarte())
			return cartes.get(i);
		return null;
	}
	
	/**
	 * supprime une carte joué dans la pioche du joueur
	 * @param c la carte
	 */
	public void supprimer(ICarte c) {
		this.cartes.remove(c);
	}
	
	/**
	 * vérifie si un joueur n'a plus de cartes à piocher
	 * @return true ou false
	 */
	public boolean plusDeCarte() {
		return cartes.size() == 0;
	}
	
	/**
	 * vérifie si une carte appartient à un joueur 
	 * et renvoie l'index de la carte
	 * @param n le numéro  de la carte
	 * @return index de la carte ou -1 si la carte n'appartient pas au joueur
	 */
	private int isCardMine(int n) {
		for (int i = 0; i < cartes.size(); i++) {
			if(getCarte(i).getNumCarte() == n)
				return i;
		}
		return -1;
	}
	
	/**
	 * le joueur pioche une carte dans ses cartes 
	 * en saisissant le numéro de la carte
	 * @see #isCardMine(int)
	 * @return le numero de carte saisie
	 */
	public int piocher(int n) {
		return isCardMine(n);
	}
	
	/**
	 * compare deux joueurs selon leur prenom soit donc l'ordre lexicographique
	 * @param j le premier joueur
	 * @param j1 le deuxieme joueur
	 * @return un entier positif si j.prenom > j1.prenom 
	 *  négatif si j.prenom < j1.prenom 
	 *  et 0 si j.prenom = j1.prenom
	 */
	private static  int comparePrenom(IJoueur j, IJoueur j1) {
		return j.toString().compareTo(j1.toString());
	}
	
	/**
	 * compare deux joueurs selon leur nombre de tete de boeufs ramassées 
	 * et s'ils ont le meme nombre on compare leur prenom
	 * @see #comparePrenom(Joueur, Joueur)
	 * @param j le premier joueur
	 * @param j1 le deuxieme joueur
	 * @return un entier positif si j > j1, négatif si j < j1
	 * et 0 si j = j1
	 */
	public static int compareTeteRamassee(IJoueur j, IJoueur j1) {
		if (j.getTeteRamassee() == j1.getTeteRamassee())
            return comparePrenom(j, j1);
		return j.getTeteRamassee() - j1.getTeteRamassee();
	}
	
	
	/**
	 * compare deux joueurs selon leur score
	 * et s'ils ont le meme score on compare leur prenom
	 * @see #comparePrenom(Joueur, Joueur)
	 * @param j le premier joueur
	 * @param j1 le deuxieme joueur
	 * @return un entier positif si j > j1, négatif si j < j1
	 * et 0 si j = j1
	 */
	public static int compareScore(IJoueur j, IJoueur j1) {
		if (j.getScore() == j1.getScore())
            return comparePrenom(j, j1);
		return j.getScore() - j1.getScore();
	}
	
	/**
	 * affiche les cartes d'un joueur
	 */
	public void afficherCartes() {
		System.out.print("- Vos cartes : ");
		if(cartes.size() == 1) {
			System.out.println(getCarte(0));
		}
		else {
			for (int i = 0; i < cartes.size() - 1; i++) {
				System.out.print(getCarte(i) + ", ");
			}
			System.out.println(getCarte(cartes.size() - 1));
		}
	}

	
}
