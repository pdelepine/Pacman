package model;

import controleur.InterfaceControleur;

public interface MyObservable {
	void enrengistrerObservateur(InterfaceControleur CG);
	void supprimerObservateur(InterfaceControleur CG);
	void notifierObservateur();
}
