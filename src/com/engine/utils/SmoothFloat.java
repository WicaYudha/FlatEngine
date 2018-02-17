package com.engine.utils;

public class SmoothFloat {
	
	private float agility;
	private float target;
	private float actual;
	
	public SmoothFloat(float initialValue, float agility) {
		this.target = this.actual = initialValue;
		this.agility = agility;
	}
	
	public void update(float delta) {
		float offset = target - actual;
		float change = offset * delta * agility;
		actual += change;
	}
	
	public void setTarget(float target) {
		this.target = target;
	}
	
	public void increaseTarget(float dt) {
		this.target += dt;
	}
	
	public float getActual() {
		return actual;
	}
	
	public float getTarget() {
		return target;
	}
}
