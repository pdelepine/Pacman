package model;

import java.util.ArrayList;
// Cette stratégie permet à un agent de poursuivre ses ennemies proches
public class StrategyPoursuite implements Strategy{
	@Override
	public AgentAction getAction(Agent agt, Game game) {
		ArrayList<PositionAgent> ennemie_position = new ArrayList<PositionAgent>();
		ArrayList<AgentAction> action_poursuite = new ArrayList<AgentAction>();
		ArrayList<AgentAction> action_poursuitePossibleHorspoursuite = new ArrayList<AgentAction>();
		for(int x = -1; x <=1 ; x++) {
			for(int y = -1; y <= 1 ; y++) {
				// Détection seulement sur l'axe vertical et horizontal
				if((x == 0 || y == 0) && !(x == 0 && y == 0)) {
					for(Agent ennemie : ((PacmanGame)game).getAgents()) {
						// Si un agent se trouve aux coordonnées x,y et que c'est un ennemie 
						if(ennemie.getPosition().getX() == agt.getPosition().getX() + x && ennemie.getPosition().getY() == agt.getPosition().getY() +y && ennemie.isPacman() != agt.isPacman()) {
							int vectx = ennemie.getPosition().getX() - agt.getPosition().getX();
							int vecty = ennemie.getPosition().getY() - agt.getPosition().getY();
							AgentAction action = new AgentAction(AgentAction.STOP);
							// On crée une action vers l'ennemie
							if(vectx < 0 && vecty == 0) {// L'ennemie est a gauche
								action = new AgentAction(AgentAction.WEST);
							}else if(vectx > 0 && vecty == 0) { // Ennemie a droite
								action = new AgentAction(AgentAction.EAST);
							}else if(vectx == 0 && vecty < 0) { // Ennemie au-dessus
								action = new AgentAction(AgentAction.NORTH);
							}else if(vectx == 0 && vecty > 0) { // Ennemie au-dessous
								action = new AgentAction(AgentAction. SOUTH);
							}
							if(((PacmanGame)game).isLegalMove(agt, action)) {
								ennemie_position.add(new PositionAgent(vectx,vecty,action.get_direction()));
								action_poursuite.add(action);
							}
						}else{
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
							if(((PacmanGame)game).isLegalMove(agt, action)) {
								System.out.println("("+x+","+y+")");
								// Ce n'est pas un mur donc on l'ajout a action_poursuitePossibleHorspoursuite
								boolean present = false;
								for(AgentAction a : action_poursuitePossibleHorspoursuite) {
									if(a.get_direction() == action.get_direction()) present = true;
								}
								if(!present) action_poursuitePossibleHorspoursuite.add(action);
							}
						}
					}
				}
			}
		}
		// On regarde si l'action hors poursuite ne va pas vers un ennemi
		for(int i=0; i<(int)action_poursuitePossibleHorspoursuite.size(); i++) {
			for(Agent agt_ennemi : ((PacmanGame)game).getAgents()) {
				if(agt_ennemi.isPacman() != agt.isPacman()) {
					// SI c'est le cas on l'enleve car doublon
					if(agt.getPosition().getX() + action_poursuitePossibleHorspoursuite.get(i).get_vx() == agt_ennemi.getPosition().getX() && agt.getPosition().getY() + action_poursuitePossibleHorspoursuite.get(i).get_vy() == agt_ennemi.getPosition().getY()) {
						action_poursuitePossibleHorspoursuite.remove(i);
					}
				}
				
			}
		}
		System.out.println("-- Nombre d'action poursuite "+ action_poursuite.size()+" Nombre d'action normale "+ action_poursuitePossibleHorspoursuite.size());
		for(AgentAction a : action_poursuitePossibleHorspoursuite) {
			System.out.print(a.get_direction());
		}
		System.out.println("");
		if(ennemie_position.size() > 0) {
			// On regarde quel ennemie est le plus proche pour pousuivre celui-ci en priorité
			int indice = 0;
			double distance = Math.sqrt(Math.pow((double)ennemie_position.get(indice).getX(), 2) + Math.pow((double)ennemie_position.get(indice).getY(),2));
			for( int i = 1 ; i < (int)ennemie_position.size(); i++) {
				double distance2 = Math.sqrt(Math.pow((double)ennemie_position.get(i).getX(), 2) + Math.pow((double)ennemie_position.get(i).getY(),2));
				if(distance2 > distance) {
					distance = distance2;
					indice = i;
				}
			}
			System.out.print("-------------- Poursuite --------------  ");
			System.out.println(agt.getPosition()+" "+distance);			
			System.out.println(action_poursuite.get(indice).get_direction());
			
			return action_poursuite.get(indice);
			
		}else {
			// Il n'y a pas d'ennemies à proximité donc un choisis une action aléatoire parmi celles possibles
			int random = (int)(Math.random() * action_poursuitePossibleHorspoursuite.size() ) ;
			System.out.println(action_poursuitePossibleHorspoursuite.get(random).get_direction());
			return action_poursuitePossibleHorspoursuite.get(random); 
		}
	}

	@Override
	public void afficheStrategy() {
		System.out.println("-- Strategy Poursuite");
		
	}

}
