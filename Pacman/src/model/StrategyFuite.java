package model;

import java.util.ArrayList;

// Cette strategy implemente la fuite d'un agent à la vue d'un ennemie
public class StrategyFuite implements Strategy{

	@Override
	public AgentAction getAction(Agent agt, Game game) {
		ArrayList<PositionAgent> ennemie_position = new ArrayList<PositionAgent>();
		ArrayList<AgentAction> action_fuite = new ArrayList<AgentAction>();
		ArrayList<AgentAction> action_fuitePossibleHorsFuite = new ArrayList<AgentAction>();
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
							// On crée une action dans la direction opposée à l'ennemie
							if(vectx < 0 && vecty == 0) {// L'ennemie est a gauche
								action = new AgentAction(AgentAction.EAST);
							}else if(vectx > 0 && vecty == 0) { // Ennemie a droite
								action = new AgentAction(AgentAction.WEST);
							}else if(vectx == 0 && vecty < 0) { // Ennemie au-dessus
								action = new AgentAction(AgentAction.SOUTH);
							}else if(vectx == 0 && vecty > 0) { // Ennemie au-dessous
								action = new AgentAction(AgentAction. NORTH);
							}
							if(((PacmanGame)game).isLegalMove(agt, action)) {
								ennemie_position.add(new PositionAgent(vectx,vecty,action.get_direction()));
								action_fuite.add(action);
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
								// Ce n'est pas un mur donc on l'ajout a action_fuitePossibleHorsFuite
								boolean present = false;
								for(AgentAction a : action_fuitePossibleHorsFuite) {
									if(a.get_direction() == action.get_direction()) present = true;
								}
								if(!present) action_fuitePossibleHorsFuite.add(action);
							}
						}
					}
				}
			}
		}
		// On regarde si l'action hors fuite ne va pas vers un ennemi
		for(int i=0; i<(int)action_fuitePossibleHorsFuite.size(); i++) {
			for(Agent agt_ennemi : ((PacmanGame)game).getAgents()) {
				if(agt_ennemi.isPacman() != agt.isPacman()) {
					// Si c'est le cas on l'enleve
					if(agt.getPosition().getX() + action_fuitePossibleHorsFuite.get(i).get_vx() == agt_ennemi.getPosition().getX() && agt.getPosition().getY() + action_fuitePossibleHorsFuite.get(i).get_vy() == agt_ennemi.getPosition().getY()) {
						action_fuitePossibleHorsFuite.remove(i);
					}
				}
			}
		}
		System.out.println("-- Nombre d'action fuite "+ action_fuite.size()+" Nombre d'action normale "+ action_fuitePossibleHorsFuite.size());
		for(AgentAction a : action_fuitePossibleHorsFuite) {
			System.out.print(a.get_direction());
		}
		System.out.println("");
		if(ennemie_position.size() > 0) {
			// On regarde quel ennemie est le plus proche pour fuir celui-ci en priorité
			int indice = 0;
			double distance = Math.sqrt(Math.pow((double)ennemie_position.get(indice).getX(), 2) + Math.pow((double)ennemie_position.get(indice).getY(),2));
			for( int i = 1 ; i < (int)ennemie_position.size(); i++) {
				double distance2 = Math.sqrt(Math.pow((double)ennemie_position.get(i).getX(), 2) + Math.pow((double)ennemie_position.get(i).getY(),2));
				if(distance2 > distance) {
					distance = distance2;
					indice = i;
				}
			}
			System.out.print("-------------- Fuite --------------  ");
			System.out.println(agt.getPosition()+" "+distance);
			// On choisis au hasard si on prend la direction opposé de l'ennemi ou une autre position possible
			int random = (int)(Math.random() * (action_fuitePossibleHorsFuite.size() + 1)) ;
			if(random == 0) {
				System.out.println(action_fuite.get(indice).get_direction());
				return action_fuite.get(indice);
			}else {
				System.out.println(action_fuitePossibleHorsFuite.get(random - 1).get_direction());
				return action_fuitePossibleHorsFuite.get(random - 1); 
			}
		
		}else {
			// Il n'y a pas d'ennemies à proximité donc un choisis une action aléatoire parmi celles possibles
			int random = (int)(Math.random() * action_fuitePossibleHorsFuite.size() ) ;
			System.out.println(action_fuitePossibleHorsFuite.get(random).get_direction());
			return action_fuitePossibleHorsFuite.get(random); 
		}
		
	}

	@Override
	public void afficheStrategy() {
		System.out.println("-- Strtategy Fuite");
		
	}

}
