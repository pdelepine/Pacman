package model;

public abstract class Agent {
	private PositionAgent position;
	
	public Agent(int direction, int x, int y) {
		this.position = new PositionAgent(x, y, direction);
	}

	public void setPosition(PositionAgent position) {
		this.position = position;
	}

	public PositionAgent getPosition() {
		return position;
	}
}
