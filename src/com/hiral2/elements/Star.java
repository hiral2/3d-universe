package com.hiral2.elements;

import java.awt.Color;

import com.hiral2.engine3D.Vector3D;
import com.hiral2.engine3D.Render3DEngine;


public class Star {
	Vector3D position= new Vector3D();
	private float velocidad;
	private float tamano=1;
	private float anguloxy=0;
	private float anguloyz = 0;
	private float deltax=0;
	private float deltay=0;
	private float deltaz=0;
	public Color c;

	public Star(){
		c = new Color((float)Math.random(),(float)Math.random(),(float)Math.random());
		anguloxy = (float) (2*Math.PI*Math.random());
		anguloyz = (float) (2*Math.PI*Math.random());
		velocidad=(float) (Math.random()*2);
		tamano = (float)(Math.random()*3+1);
		setSpeed(velocidad);
	}
	
	
	
	public Star(float x, float y, float z, float velocidad, float tamano,
			float anguloyz, float anguloxy, Color c) {
		super();
		this.position.x = x;
		this.position.y = y;
		this.position.z = z;
		this.tamano = tamano;
		this.anguloxy = anguloxy;
		this.anguloyz = anguloyz;
		setSpeed(velocidad);
		this.c = c;
	}



	private void update(){
		position.x+=deltax;
		position.y+=deltay;
		position.z+=deltaz;
		//deltay-=0.3f;
		if(position.y<0)deltay = Math.abs(deltay);
	}
	
	public void setSpeed(float s){
		velocidad = s;
		deltaz = (float) (Math.sin(anguloyz)*velocidad);
		float deltav = (float) (Math.cos(anguloyz)*velocidad);
		deltax = (float) (Math.cos(anguloxy)*deltav);
		deltay = (float) (Math.sin(anguloxy)*deltav);
	}
	
	private void updateUsingDelta(){
		position.x+=deltax;
		position.y+=deltay;
		position.z+=deltaz;
	}
	
	public void draw(Render3DEngine r3d){
		updateUsingDelta();
		
		r3d.setG2d(c);
		r3d.drawRect( position.x, position.y, position.z,tamano);
	}
}
