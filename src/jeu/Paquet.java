package jeu;

import java.util.Arrays;
import java.util.Collections;

import appli.ICarte;


public class Paquet {
	
	/** l'attribut de la classe Paquet est la liste des cartes */
	private ICarte[] cartes;
	
	/**
	 * constructeur du paquet de 104 cartes 
	 */
	public Paquet(int n) {
		cartes =  new ICarte[n];
		Arrays.fill(cartes, new Carte());
		Arrays.sort(cartes);
	}
	
	/**
	 * mélange les cartes d'un paquet de cartes
	 */
	public void melanger() {
		Collections.shuffle(Arrays.asList(cartes));
	}
	
	/**
	 * renvoie la carte à l'index i d'un paquet de cartes
	 * @param i, l'index
	 * @return la carte
	 */
	public ICarte getCarte(int i) {
		assert(i>=0 && i < getTaille());
		return cartes[i];
	}

	private int getTaille() {
		return cartes.length;
	}

}
