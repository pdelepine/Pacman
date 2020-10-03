package model;

import controleur.ControleurPacmanGame;
import controleur.ControleurSimpleGame;

public class Test{
	
	public static void main(String[] args) {
		
	 
		//SimpleGame simplegame = new SimpleGame(100,4000);
		//ControleurSimpleGame CG = new ControleurSimpleGame(simplegame);
		
		PacmanGame pacman = new PacmanGame(100, 3000);
		ControleurPacmanGame cpg = new ControleurPacmanGame(pacman);
		
		//CG.start();
		cpg.start();
		
	    
		
		
		
		
		
	}

}
