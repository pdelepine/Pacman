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
				System.out.println("Up pressed");
				game.actionInteractive("up");
				break;
			case KeyEvent.VK_DOWN:
				System.out.println("Down pressed");
				game.actionInteractive("down");
				break;
			case KeyEvent.VK_RIGHT:
				System.out.println("Right pressed");
				game.actionInteractive("right");
				break;
			case KeyEvent.VK_LEFT:
				System.out.println("Left pressed");
				game.actionInteractive("left");
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
