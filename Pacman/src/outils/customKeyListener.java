package outils;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import model.Game;
import model.PacmanGame;

public class customKeyListener implements KeyListener{
	private PacmanGame game;
	
	public customKeyListener(Game game) {
		this.game = (PacmanGame)game;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
			case KeyEvent.VK_UP:
				System.out.println("Up 1 pressed");
				game.actionInteractive("up",1);
				break;
			case KeyEvent.VK_DOWN:
				System.out.println("Down 1 pressed");
				game.actionInteractive("down",1);
				break;
			case KeyEvent.VK_RIGHT:
				System.out.println("Right 1 pressed");
				game.actionInteractive("right",1);
				break;
			case KeyEvent.VK_LEFT:
				System.out.println("Left 1 pressed");
				game.actionInteractive("left",1);
				break;
				
			case KeyEvent.VK_Z:
				System.out.println("Up 2 pressed");
				game.actionInteractive("up",2);
				break;
			case KeyEvent.VK_S:
				System.out.println("Down 2 pressed");
				game.actionInteractive("down",2);
				break;
			case KeyEvent.VK_D:
				System.out.println("Right 2 pressed");
				game.actionInteractive("right",2);
				break;
			case KeyEvent.VK_Q:
				System.out.println("Left 2 pressed");
				game.actionInteractive("left",2);
				break;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
