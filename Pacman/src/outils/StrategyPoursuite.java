package outils;

import java.util.ArrayList;

import model.Game;
import model.PacmanGame;
// Cette stratégie permet à un agent de poursuivre ses ennemies proches
public class StrategyPoursuite implements Strategy{
	boolean ennemieNear ;
	StrategyAetoile sA = new StrategyAetoile();
	@SuppressWarnings("unchecked")
	@Override
	public AgentAction getAction(Agent agt, Game game) {
		ArrayList<PositionAgent> ennemie_position = new ArrayList<PositionAgent>();
		ArrayList<AgentAction> action_poursuite = new ArrayList<AgentAction>();
		ArrayList<AgentAction> action_poursuitePossibleHorspoursuite = new ArrayList<AgentAction>();
		ennemieNear = false;
		
		
		int x=0,y=-1; // Test case au-dessus
		System.out.println("---- NORTH ----");
		ArrayList<Object> liste_obj = new ArrayList<Object>();
		liste_obj = remplissageListeAction(agt, game,x,y, ennemie_position, action_poursuite, action_poursuitePossibleHorspoursuite);
		ennemie_position = ((ArrayList<PositionAgent>)liste_obj.get(0));
		action_poursuite = ((ArrayList<AgentAction>)liste_obj.get(1));
		action_poursuitePossibleHorspoursuite = ((ArrayList<AgentAction>)liste_obj.get(2));
		
		System.out.println("---- EAST ----");
		x=-1;y=0; // Test case à gauche
		liste_obj = remplissageListeAction(agt, game,x,y, ennemie_position, action_poursuite, action_poursuitePossibleHorspoursuite);
		ennemie_position = ((ArrayList<PositionAgent>)liste_obj.get(0));
		action_poursuite = ((ArrayList<AgentAction>)liste_obj.get(1));
		action_poursuitePossibleHorspoursuite = ((ArrayList<AgentAction>)liste_obj.get(2));
		
		System.out.println("---- WEST ----");
		x=1;y=0; // Test case à droite
		liste_obj = remplissageListeAction(agt, game,x,y, ennemie_position, action_poursuite, action_poursuitePossibleHorspoursuite);
		ennemie_position = ((ArrayList<PositionAgent>)liste_obj.get(0));
		action_poursuite = ((ArrayList<AgentAction>)liste_obj.get(1));
		action_poursuitePossibleHorspoursuite = ((ArrayList<AgentAction>)liste_obj.get(2));
		
		System.out.println("---- SOUTH ----");
		x=0;y=1; // Test case au-dessous
		liste_obj = remplissageListeAction(agt, game,x,y, ennemie_position, action_poursuite, action_poursuitePossibleHorspoursuite);
		ennemie_position = ((ArrayList<PositionAgent>)liste_obj.get(0));
		action_poursuite = ((ArrayList<AgentAction>)liste_obj.get(1));
		action_poursuitePossibleHorspoursuite = ((ArrayList<AgentAction>)liste_obj.get(2));
		
		
		// On regarde si l'action hors poursuite ne va pas vers un ennemi
		for(int i=0; i<(int)action_poursuitePossibleHorspoursuite.size(); i++) {
			for(Agent agt_ennemi : ((PacmanGame)game).getAgents()) {
				if(action_poursuitePossibleHorspoursuite.size() > i) {
					if(agt_ennemi.isPacman() != agt.isPacman()) {
						// SI c'est le cas on l'enleve car doublon
						if(agt.getPosition().getX() + action_poursuitePossibleHorspoursuite.get(i).get_vx() == agt_ennemi.getPosition().getX() && agt.getPosition().getY() + action_poursuitePossibleHorspoursuite.get(i).get_vy() == agt_ennemi.getPosition().getY()) {
							action_poursuitePossibleHorspoursuite.remove(i);
						}
					}
				}				
			}
		}
		
		System.out.println("-- Nombre d'action poursuite "+ action_poursuite.size()+" Nombre d'action hors poursuite "+ action_poursuitePossibleHorspoursuite.size());
		System.out.print("Actions poursuite : ");
		for(AgentAction a : action_poursuite) {
			System.out.print(a.get_direction());
		}
		System.out.print(" Actions hors poursuite : ");
		for(AgentAction a : action_poursuitePossibleHorspoursuite) {
			System.out.print(a.get_direction());
		}
		System.out.println("");
		
		if(action_poursuite.size() > 0) {
			System.out.println("-------------- Action de Poursuite possible --------------");	
			// On choisis une action poursuite au hasard parmis celles possibles 
			int random = (int)(Math.random() * (action_poursuite.size())) ;
			System.out.println("Action choisis parmi celle possible : "+action_poursuite.get(random).get_direction()+"\n");
			return action_poursuite.get(random);
			
		}else {
			if(ennemieNear) {
				System.out.println("-------------- Pas de poursuite immédiate possible --------------  ");
				// Il n'y a pas d'ennemies à proximité donc un choisis une action aléatoire parmi celles possibles
				if(action_poursuitePossibleHorspoursuite.size() > 0) {
					int random = (int)(Math.random() * action_poursuitePossibleHorspoursuite.size() ) ;
					System.out.println("Action choisis parmi celle possible : "+action_poursuitePossibleHorspoursuite.get(random).get_direction()+"\n");
					return action_poursuitePossibleHorspoursuite.get(random); 
				}else {
					return new AgentAction(AgentAction.STOP); 
				}
			}else {
				if(agt.isPacman()) {
					System.out.println("-------------- Recherche de Nourriture --------------");
					// Il n'y a pas d'ennemies à proximité donc on change la strategie en recherche de nourriture
					sA.afficheStrategy();
					return sA.getAction(agt, game);
				}else {
					int random = (int) Math.random();					
					if(random == 0) {// On choisi au hasard parmi les deux stratégies
						StrategyPriorité sp = new StrategyPriorité();
						sp.afficheStrategy();
						return sp.getAction(agt, game);
					}else {
						StrategyAleatoire sa = new StrategyAleatoire();
						sa.afficheStrategy();
						return sa.getAction(agt, game);
					}
					
					
				}
			}
			
			
		}
	}

	@Override
	public void afficheStrategy() {
		System.out.println("-- Strategy Poursuite --");
		
	}

	private ArrayList<Object> remplissageListeAction(Agent agt, Game game,int x, int y, ArrayList<PositionAgent> ennemie_position, ArrayList<AgentAction> action_poursuite, ArrayList<AgentAction> action_poursuitePossibleHorspoursuite) {
		boolean ennemiePresent = false;
		
		for(Agent ennemie : ((PacmanGame)game).getAgents()) {			
			// Si un agent se trouve aux coordonnées x,y et que c'est un ennemie 			
			if(ennemie.getPosition().getX() == agt.getPosition().getX() + x && ennemie.getPosition().getY() == agt.getPosition().getY() + y && ennemie.isPacman() != agt.isPacman()) {
				ennemiePresent =true ;
				ennemieNear = true;
				System.out.println("Ennemie en face ("+ennemie.getPosition().getX()+","+ennemie.getPosition().getY()+")");
				
				AgentAction action = new AgentAction(AgentAction.STOP);
				// On crée une action vers l'ennemie
				if(x < 0 && y == 0) {// L'ennemie est a gauche
					action = new AgentAction(AgentAction.WEST);
				}else if(x > 0 && y == 0) { // Ennemie a droite
					action = new AgentAction(AgentAction.EAST);
				}else if(x == 0 && y < 0) { // Ennemie au-dessus
					action = new AgentAction(AgentAction.NORTH);
				}else if(x == 0 && y > 0) { // Ennemie au-dessous
					action = new AgentAction(AgentAction. SOUTH);
				}
				
				if(((PacmanGame)game).isLegalMove(agt, action)&& action.get_direction() != AgentAction.STOP) {					
					ennemie_position.add(new PositionAgent(x,y,action.get_direction()));
					boolean present = false;
					for(AgentAction a : action_poursuite) {
						if(a.get_direction() == action.get_direction()) present = true;
					}
					if(present == false) {
						System.out.println("Poursuite : ("+agt.getPosition().getX()+","+agt.getPosition().getY()+") + ("+x+","+y+") -> "+action.get_direction());
						action_poursuite.add(action);
					}
				}
			}
		}
		if(!ennemiePresent){
			// La case n'a pas d'ennemie , on test si c'est une position possible pour l'agent ( pas un mur )
			AgentAction action = new AgentAction(AgentAction.STOP);
			if(x < 0 && y == 0) {
				action = new AgentAction(AgentAction.WEST);
			}else if(x > 0 && y == 0) { 
				action = new AgentAction(AgentAction.EAST);
			}else if(x == 0 && y < 0) { 
				action = new AgentAction(AgentAction.NORTH);
			}else if(x == 0 && y > 0) { 
				action = new AgentAction(AgentAction. SOUTH);
			}
			if(((PacmanGame)game).isLegalMove(agt, action)&& action.get_direction() != AgentAction.STOP) {					
				// Ce n'est pas un mur donc on l'ajout a action_poursuitePossibleHorspoursuite
				boolean present = false;
				for(AgentAction a : action_poursuitePossibleHorspoursuite) {
					if(a.get_direction() == action.get_direction()) present = true;
				}
				if(present == false) {
					System.out.println("Hors poursuite : ("+x+","+y+") -> "+action.get_direction());
					action_poursuitePossibleHorspoursuite.add(action);
				}
			}
		}
		ArrayList<Object> liste = new ArrayList<Object>();
		liste.add(ennemie_position);
		liste.add(action_poursuite);
		liste.add(action_poursuitePossibleHorspoursuite);
		return liste;
	}
}
