package jeu;

import appli.ICarte;

public class Carte implements ICarte{
	
	private static int cptCarte = 1;
	private int numCarte;
	private int teteBoeuf;
	

	public Carte() {
		this.numCarte = cptCarte++;
		this.setTeteBoeuf();
		
	}
	
	public Carte(int num) {
		if(num < 0) {
			throw new IllegalArgumentException("ERROR : Numéro de carte négatif ou égale à 0");
		}
		this.numCarte = num;
		this.setTeteBoeuf();
	}

	/**
	 * donne la valeur de tete de boeuf d'une carte selon son numero de carte
	 */
	private void setTeteBoeuf() {
		// si son numero de carte est le meme inverse et qu'il se termine par 5 alors mettre 7 en tete de boeufs
		if(this.getNumCarte() % 11 == 0 && this.getNumCarte() % 10 == 5 )
	        this.teteBoeuf = 7;
		// sinon si son numero de carte est le meme inverse alors mettre 5 en tete de boeufs
		else if(this.getNumCarte() % 11 == 0 )
			this.teteBoeuf = 5;
		// sinon si son numero de carte se termine par 5 alors mettre 2 en tete de boeufs
		else if(this.getNumCarte() % 10 == 5) 
			this.teteBoeuf = 2;
		// sinon si son numero de carte se termine par 0 alors mettre 3 en tête de boeufs
		else if(this.getNumCarte() % 10 == 0) 
			this.teteBoeuf = 3;
		// sinon mettre 1 en tete de boeufs
		else
			this.teteBoeuf = 1;
	}
	/**
	 * affiche une carte 
	 */
	public String toString() {
		if (this.getTeteBoeuf() != 1) {
			return this.getNumCarte() + " (" + this.getTeteBoeuf() + ")";
		}
		else
			return Integer.toString(this.getNumCarte());
	}
	
	/**
	 * renvoie le numero d'une carte
	 * @return le numero de la carte
	 */
	public int getNumCarte() {
		return numCarte;
	}
	
	/**
	 * renvoie la valeur de tete de boeuf d'une carte 
	 * @return la valeur de tete de boeuf de la carte
	 */
	public int getTeteBoeuf() {
		return teteBoeuf;
	}
	
	/**
	 * compare deux carte 
	 * et renvoie 0 si ils ont le meme numero, 
	 * un valeur positive si la premiere est plus grande que la deuxieme
	 * un valeur negative si la premiere est plus petite que la deuxieme
	 * @return valeur positif si c > c1, valeur négative c < c1 et 0 si c = c1
	 */
	public static int compareNumCarte(ICarte c, ICarte c1) {
		return c.getNumCarte() - c1.getNumCarte();
	}
	
	/**
	 * compare deux cartes pour voir s'ils ont le même numéro de carte
	 * @param c la carte à comparer
	 * @return vrai ou faux
	 */
	public boolean equals(Carte c) {
		return this.getNumCarte() == c.getNumCarte();
	}
}
