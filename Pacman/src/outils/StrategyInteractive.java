package outils;


import model.Game;
import model.PacmanGame;

public class StrategyInteractive implements Strategy {
	private String key = "vide";

	@Override
	public AgentAction getAction(Agent agt, Game game) {
		PacmanGame pgame = (PacmanGame) game;
		AgentAction action = new AgentAction(AgentAction.STOP);
		System.out.println("--> Choix de direction en attente");
		do {
			/*
			try {
				Thread.sleep(1000);
			}catch (InterruptedException exp) {
				System.out.println("Thread interrupted !");
				System.out.println(exp.getMessage());
			}
			*/
			
			if(key.equals("up")) action = new AgentAction(AgentAction.NORTH);
			if(key.equals("down")) action = new AgentAction(AgentAction.SOUTH);
			if(key.equals("right")) action = new AgentAction(AgentAction.EAST);
			if(key.equals("left")) action = new AgentAction(AgentAction.WEST);
		}while(key.equals("vide") || (pgame.isLegalMove(agt, action) && action.get_direction() != AgentAction.STOP) == false);
		System.out.println("  Direction choisis : "+key);
		System.out.println("  Legal move "+(pgame.isLegalMove(agt, action) && action.get_direction() != AgentAction.STOP));
		System.out.println("  Key vide: "+key.equals("vide"));
		System.out.println("  Action possible -> "+(!(key.equals("vide") || (pgame.isLegalMove(agt, action) && action.get_direction() != AgentAction.STOP) == false))+"\n");
		System.out.println("--> Action effectuer : "+key);
		key = "vide";
		return action;
	}

	@Override
	public void afficheStrategy() {
		System.out.println("-- Strategy Interactive --");
		
	}
	
	// Est appelé dans pacmanGame pour changé l'action à effectuer lors du mode interactif
	public void setKey(String s) {
		System.out.println("Key changed : "+s);
		key = s;
	}

	

}
