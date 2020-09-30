package Model;

import Controleur.ControleurSimpleGame;

public interface MyObservable {
	void enrengistrerObservateur(ControleurSimpleGame CG);
	void supprimerObservateur();
	void notifierObservateur();
}
