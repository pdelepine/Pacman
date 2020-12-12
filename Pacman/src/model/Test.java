package model;



import controleur.ControleurPacmanGame;
import view.ViewMenu;

public class Test{
	public static ControleurPacmanGame cpg;
	
	public static void main(String[] args) {
		
		PacmanGame pacman = new PacmanGame(1000, 3000);
		ViewMenu menu = new ViewMenu(pacman);
		menu.launch();
	}
}
