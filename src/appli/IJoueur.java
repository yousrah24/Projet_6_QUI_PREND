package appli;


public interface IJoueur {

	int getScore();

	ICarte getCarteJouee();

	void setTeteRamassee(int teteBoeuf);

	void setScore(int teteBoeuf);

	boolean plusDeCarte();

	int getTeteRamassee();

	int piocher(int n);

	void initialiserTeteRamassee();

	void ajouter(ICarte carte);
	
	ICarte getCarte(int n);

	void supprimer(ICarte c);

	void afficherCartes();

	void setCarteJouee(ICarte carte);

	

	
}
