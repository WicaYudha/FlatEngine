package com.engine.models;

public class RawModel {
	
	private int vaoID;
	private int verticesCount;
	
	public RawModel(int vaoID, int verticesCount) {
		this.vaoID = vaoID;
		this.verticesCount = verticesCount;
	}

	public int getVerticesCount() {
		return this.verticesCount;
	}
	
	public int getVaoID() {
		return this.vaoID;
	}
	
}
