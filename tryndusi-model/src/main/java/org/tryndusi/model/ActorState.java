package org.tryndusi.model;

public enum ActorState {

	IDLE("green"), WAIT("yellow"), PARKED("red"), PROCESS("blue");

	private final String color;

	ActorState(String color) {
		this.color = color;
	}

	public String color() {
		return color;
	}
}
