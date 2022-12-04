package jeu;

import java.util.LinkedList;

import appli.ICarte;
import appli.ISerie;

public class Serie implements ISerie{
	private static int num = 1;
	private LinkedList<ICarte> cartes;
	private int numero;
	
	public Serie() {
		numero= Serie.num++;
		cartes =  new LinkedList<>();
	}
	
	/**
	 * renvoie la liste des cartes pos�es d'une s�rie
	 * @return la liste des cartes
	 */
	public LinkedList<ICarte> getCartes() {
		return cartes;
	}
	
	/**
	 * donne la derni�re carte pos� sur une serie
	 * @return la derni�re carte
	 */
	public ICarte getDernierCarte() {
		return cartes.getLast();
	}
	
	/**
	 * ajoute une carte dans la liste des cartes pos�es d'une s�rie
	 * @param iCarte la carte � ajouter
	 */
	public void ajouter(ICarte iCarte) {
		this.cartes.addLast(iCarte);
	}
	
	/**
	 * compare les deux derniere carte de deux s�rie
	 * @param s la s�rie
	 * @param s1 la s�rie � comparer
	 * @return valeur positif si s.getDernierCarte() > s1.getDernierCarte(), 
	 * valeur n�gative s.getDernierCarte() < s1.getDernierCarte()
	 * et 0 si s.getDernierCarte() = s1.getDernierCarte()
	 */
	public static int compareSerie(ISerie s, ISerie s1) {
		return Carte.compareNumCarte(s.getDernierCarte(), s1.getDernierCarte());
	}
	
	/**
	 * affiche les cartes d'une s�rie
	 */
	public String toString() {
		String str = "";
		if(cartes.size() == 1) {
			return cartes.getLast().toString() +"\n";
		}
		else {
			for (int i = 0; i < cartes.size() - 1; i++)
				str+= cartes.get(i) + ", ";
			str+= cartes.getLast() +"\n";
		}
		return str;
	}

	public int getNum() {
		return numero;
	}
	
	
}
