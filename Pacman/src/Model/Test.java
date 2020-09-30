package Model;

import Controleur.ControleurSimpleGame;

public class Test{
	
	public static void main(String[] args) {
		
	 
		SimpleGame simplegame = new SimpleGame(100,4000);
		ControleurSimpleGame CG = new ControleurSimpleGame(simplegame);
		
		CG.start();
		
	    
		
		
		
		
		
	}

}
