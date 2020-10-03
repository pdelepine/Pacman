package model;

public abstract class Agent {
	private AgentAction action;
	private PositionAgent position;
	
	public Agent(int direction, int x, int y) {
		this.action = new AgentAction(direction);
		this.position = new PositionAgent(x, y, direction);
	}
}
