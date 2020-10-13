package model;

import java.util.ArrayList;

public class AgentPacman extends Agent{

	public AgentPacman(int direction, int x, int y, Strategy strat) {
		super(direction, x, y, strat);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isPacman() {
		return true;
	}

	@Override
	public AgentAction comportementAgentSimple(Maze maze) {
		
		ArrayList<PositionAgent> positionFood = new ArrayList<PositionAgent>();
		if(maze.isFood(position.getX()+1, position.getY())) {
			positionFood.add(new PositionAgent(position.getX()+1, position.getY(), AgentAction.EAST));
		}
		if(maze.isFood(position.getX()-1, position.getY())) {
			positionFood.add(new PositionAgent(position.getX()-1, position.getY(), AgentAction.WEST));
		}
		if(maze.isFood(position.getX(), position.getY() + 1)) {
			positionFood.add(new PositionAgent(position.getX(), position.getY() + 1, AgentAction.SOUTH));
		}
		if(maze.isFood(position.getX(), position.getY() - 1)) {
			positionFood.add(new PositionAgent(position.getX(), position.getY() - 1, AgentAction.NORTH));
		}
		
		// Si la nourriture est en face on la choisis en priorité
		for(PositionAgent p : positionFood) {
			if(position.getDir() == p.getDir()) {
				return new AgentAction(p.getDir());
			}
		}
		// Si pas de nourriture en face , on choisis aléatoiremet parmis les autres 
		int choixAlteatoire = 4 ;
		if(positionFood.size() != 0) {
			choixAlteatoire = (int) (Math.random() * positionFood.size());
			return new AgentAction(positionFood.get(choixAlteatoire).getDir());
		}else {
			return null;
		}
		
	}

}
