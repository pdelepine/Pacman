package outils;

public class AgentPacman extends Agent{

	public AgentPacman(int direction, int x, int y, Strategy strat) {
		super(direction, x, y, strat);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isPacman() {
		return true;
	}

	

}
