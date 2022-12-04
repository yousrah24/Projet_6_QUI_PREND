package appli;

import java.util.LinkedList;

public interface ISerie {

	void ajouter(ICarte carte);
	ICarte getDernierCarte();
	LinkedList<ICarte> getCartes();
	int getNum();

}
