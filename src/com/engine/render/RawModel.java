package com.engine.render;

public class RawModel {
	
	private int vaoID;
	private int verticesCount;
	
	public RawModel(int vaoID, int verticesCount) {
		super();
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
