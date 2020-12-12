package outils;

public class AgentFantome extends Agent{

	public AgentFantome(int direction, int x, int y, Strategy strat) {
		super(direction, x, y, strat);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isPacman() {
		return false;
	}



}
