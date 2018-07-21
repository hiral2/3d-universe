package com.hiral2.engine3D;

public class Vector3D {
	public float x;
	public float y;
	public float z;

	public Vector3D(){
		
	}
	
	public Vector3D(float x,float y,float z){
		this.x=x;
		this.y=y;
		this.z=z;
		
	}
	
	public float getDistance(Vector3D p2){
		return (float) Math.sqrt((x*p2.x)+(y*p2.y)+(z*p2.z));
	}
	
	public Vector3D getNormalize(){
		float delta = x+y+z;
		if(delta!=0)
			return new Vector3D(x/delta,y/delta,z/delta);
		return this;
	}
	
	
}
