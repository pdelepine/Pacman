package outils;

import java.util.ArrayList;

import model.Game;
import model.PacmanGame;

// Cette strategy implemente la fuite d'un agent à la vue d'un ennemie
public class StrategyFuite implements Strategy{
	boolean ennemieNear ;
	StrategyAetoile sA = new StrategyAetoile();
	
	@SuppressWarnings("unchecked")
	@Override
	public AgentAction getAction(Agent agt, Game game) {
		ArrayList<PositionAgent> ennemie_position = new ArrayList<PositionAgent>();
		ArrayList<AgentAction> action_fuite = new ArrayList<AgentAction>();
		ArrayList<AgentAction> action_fuitePossibleHorsFuite = new ArrayList<AgentAction>();
		ennemieNear = false;

		int x=0,y=-1; // Test case au-dessus
		System.out.println("---- NORTH ----");
		ArrayList<Object> liste_obj = new ArrayList<Object>();
		liste_obj = remplissageListeAction(agt, game,x,y, ennemie_position, action_fuite, action_fuitePossibleHorsFuite);
		//ennemie_position = ((ArrayList<PositionAgent>)liste_obj.get(0));
		action_fuite = ((ArrayList<AgentAction>)liste_obj.get(1));
		action_fuitePossibleHorsFuite = ((ArrayList<AgentAction>)liste_obj.get(2));
		
		System.out.println("---- EAST ----");
		x=-1;y=0; // Test case à gauche
		liste_obj = remplissageListeAction(agt, game,x,y, ennemie_position, action_fuite, action_fuitePossibleHorsFuite);
		//ennemie_position = ((ArrayList<PositionAgent>)liste_obj.get(0));
		action_fuite = ((ArrayList<AgentAction>)liste_obj.get(1));
		action_fuitePossibleHorsFuite = ((ArrayList<AgentAction>)liste_obj.get(2));
		
		System.out.println("---- WEST ----");
		x=1;y=0; // Test case à droite
		liste_obj = remplissageListeAction(agt, game,x,y, ennemie_position, action_fuite, action_fuitePossibleHorsFuite);
		//ennemie_position = ((ArrayList<PositionAgent>)liste_obj.get(0));
		action_fuite = ((ArrayList<AgentAction>)liste_obj.get(1));
		action_fuitePossibleHorsFuite = ((ArrayList<AgentAction>)liste_obj.get(2));
		
		System.out.println("---- SOUTH ----");
		x=0;y=1; // Test case au-dessous
		liste_obj = remplissageListeAction(agt, game,x,y, ennemie_position, action_fuite, action_fuitePossibleHorsFuite);
		//ennemie_position = ((ArrayList<PositionAgent>)liste_obj.get(0));
		action_fuite = ((ArrayList<AgentAction>)liste_obj.get(1));
		action_fuitePossibleHorsFuite = ((ArrayList<AgentAction>)liste_obj.get(2));		
		
		// On regarde si l'action hors fuite ne va pas vers un ennemi
		for(int i=0; i<(int)action_fuitePossibleHorsFuite.size(); i++) {
			for(Agent agt_ennemi : ((PacmanGame)game).getAgents()) {
				if(action_fuitePossibleHorsFuite.size() > i) {
					if(agt_ennemi.isPacman() != agt.isPacman()) {
						// Si c'est le cas on l'enleve
						if(agt.getPosition().getX() + action_fuitePossibleHorsFuite.get(i).get_vx() == agt_ennemi.getPosition().getX() && agt.getPosition().getY() + action_fuitePossibleHorsFuite.get(i).get_vy() == agt_ennemi.getPosition().getY()) {
							action_fuitePossibleHorsFuite.remove(i);
						}
					}
				}
				
			}
		}
		// On regarde si l'action fuite ne va pas vers un ennemi
				for(int i=0; i<(int)action_fuite.size(); i++) {
					for(Agent agt_ennemi : ((PacmanGame)game).getAgents()) {
						if(action_fuite.size() > i) {
							if(agt_ennemi.isPacman() != agt.isPacman()) {
								// Si c'est le cas on l'enleve
								if(agt.getPosition().getX() + action_fuite.get(i).get_vx() == agt_ennemi.getPosition().getX() && agt.getPosition().getY() + action_fuite.get(i).get_vy() == agt_ennemi.getPosition().getY()) {
									action_fuite.remove(i);
								}
							}
						}
						
					}
				}
		System.out.println("-- Nombre d'action fuite "+ action_fuite.size()+" Nombre d'action normale "+ action_fuitePossibleHorsFuite.size());
		System.out.print("Actions fuite : ");
		for(AgentAction a : action_fuite) {
			System.out.print(a.get_direction());
		}
		System.out.print(" Actions hors fuite : ");
		for(AgentAction a : action_fuitePossibleHorsFuite) {
			System.out.print(a.get_direction());
		}
		System.out.println("");
		if(action_fuite.size() > 0) {
			System.out.println("-------------- Action de Fuite possible --------------");	
			// On choisis au hasard si on prend la direction opposé de l'ennemi ou une autre position possible
			int random = (int)(Math.random() * (action_fuitePossibleHorsFuite.size() + action_fuite.size())) ;
			if(random < action_fuite.size()) {
				System.out.println("Action choisis parmi celle possible : "+action_fuite.get(random).get_direction());
				return action_fuite.get(random);
			}else {
				System.out.println("Action choisis parmi celle possible : "+action_fuitePossibleHorsFuite.get(random - action_fuite.size()).get_direction()+"\n");
				return action_fuitePossibleHorsFuite.get(random - action_fuite.size()); 
			}
		
		}else {
			if(ennemieNear) {
				System.out.println("-------------- Pas d'action fuite immédiate possible --------------");
				// Il y a un ennemie à proximité mais pas d'action de fuite opposé possible
				if(action_fuitePossibleHorsFuite.size() > 0) {
					int random = (int)(Math.random() * action_fuitePossibleHorsFuite.size() ) ;
					System.out.println("Action choisis parmi celle possible : "+action_fuitePossibleHorsFuite.get(random).get_direction()+"\n");
					return action_fuitePossibleHorsFuite.get(random); 
				}else {
					System.out.println("Il n'y a plus d'échappatoire et Pacaman meurt (╥﹏╥)");
					return new AgentAction(AgentAction.STOP);

				}
			}else {
				if(agt.isPacman()) {
					System.out.println("-------------- Recherche de Nourriture --------------");
					// Il n'y a pas d'ennemies à proximité donc on change la strategie en recherche de nourriture
					//StrategyAetoile sa = new StrategyAetoile();
					//StrategyNourriture sn = new StrategyNourriture();
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
		System.out.println("-- Strategy Fuite --");
		
	}
	
	private ArrayList<Object> remplissageListeAction(Agent agt, Game game,int x, int y, ArrayList<PositionAgent> ennemie_position, ArrayList<AgentAction> action_fuite, ArrayList<AgentAction> action_fuitePossibleHorsFuite) {
		boolean ennemiePresent = false;
		for(Agent ennemie : ((PacmanGame)game).getAgents()) {
			// Si un agent se trouve aux coordonnées x,y et que c'est un ennemie 
			if(ennemie.getPosition().getX() == agt.getPosition().getX() + x && ennemie.getPosition().getY() == agt.getPosition().getY() +y && ennemie.isPacman() != agt.isPacman()) {
				ennemiePresent =true;
				ennemieNear = true;
				System.out.println("Ennemi en face ("+ennemie.getPosition().getX()+","+ennemie.getPosition().getY()+")");
				AgentAction action = new AgentAction(AgentAction.STOP);
				// On crée une action dans la direction opposée à l'ennemie
				if(x < 0 && y == 0) {// L'ennemie est a gauche
					action = new AgentAction(AgentAction.EAST);
				}else if(x > 0 && y == 0) { // Ennemie a droite
					action = new AgentAction(AgentAction.WEST);
				}else if(x == 0 && y < 0) { // Ennemie au-dessus
					action = new AgentAction(AgentAction.SOUTH);	
				}else if(x == 0 && y > 0) { // Ennemie au-dessous
					action = new AgentAction(AgentAction. NORTH);
				}
				System.out.print("Fuite à l'opposé de l'ennemie -> ");
				// On ajoute l'action de fuire seulement si elle n'est pas déjà dans la liste d'action_fuite et que cette action est valide
				if(((PacmanGame)game).isLegalMove(agt, action)  && action.get_direction() != AgentAction.STOP) {
					//ennemie_position.add(new PositionAgent(x,y,action.get_direction()));
					boolean present = false;
					for(AgentAction a : action_fuite) {
						if(a.get_direction() == action.get_direction()) present = true;
					}
					if(!present)action_fuite.add(action);
					System.out.println(action.get_direction());
				}else {
					if(!((PacmanGame)game).isLegalMove(agt, action)) {
						System.out.println("La fuite mène vers un mur");
					}
				}
			}
		}
		// Si il n'y a pas d'ennemie sur la case on la rajoute au action hors fuite
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
			if(((PacmanGame)game).isLegalMove(agt, action) && action.get_direction() != AgentAction.STOP) {
				
				// Ce n'est pas un mur donc on l'ajout a action_fuitePossibleHorsFuite
				boolean present = false;
				for(AgentAction a : action_fuitePossibleHorsFuite) {
					if(a.get_direction() == action.get_direction()) {
						present = true;
					}
				}
				if(present == false) {
					System.out.println("Hors fuite : ("+x+","+y+") -> "+action.get_direction());
					action_fuitePossibleHorsFuite.add(action);
				}
			}
		}
		ArrayList<Object> liste = new ArrayList<Object>();
		liste.add(ennemie_position);
		liste.add(action_fuite);
		liste.add(action_fuitePossibleHorsFuite);
		return liste;
	}

}
