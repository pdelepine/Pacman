package model;

import view.Observateur;

public interface MyObservable {
	void enrengistrerObservateur(Observateur CG);
	void supprimerObservateur(Observateur CG);
	void notifierObservateur();
	
}
