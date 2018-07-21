package com.hiral2.engine3D;

import java.awt.Color;
import java.awt.Graphics2D;

public class Render3DEngine {
	public float X=0;
	public float Y=0;
	public float Z=1f;
	public float rotX=0;
	public float rotY=0;
	public float rotZ=0;
	float rescalar = 1f;
	private Graphics2D g2d;
	
	public void setG2d(Graphics2D g2d){
		this.g2d = g2d;
	}
	
	public void setG2d(Color c){
		this.g2d.setColor(c);
	}
	
	public static Vector3D getSpeed(float rotx,float roty,float rotz,float velocidad){
		Vector3D Velocidad = new Vector3D();
		Vector3D p = getrotX(velocidad, Velocidad.y, Velocidad.z,rotx);
		Velocidad.x = p.x;
		p = getrotY(Velocidad.x,velocidad,Velocidad.y,roty);
		Velocidad.y = p.y;
		p = getrotZ(Velocidad.x,Velocidad.y,velocidad,rotz);
		Velocidad.z = p.z;
		return Velocidad;
	}
	
	private Vector3D getrotX(float x,float y,float z){
		Vector3D p = new Vector3D();
		p.x = x;
		p.y = (float)(y*Math.cos(rotX) - (z*Math.sin(rotX)));
		p.z = (float)(y*Math.sin(rotX) + (z*Math.cos(rotX)));
		return p;
	}
	
	
	
	private static Vector3D getrotY(float x,float y,float z,float rot){
		Vector3D p = new Vector3D();
		p.x = (float)(z*Math.sin(rot) + (x*Math.cos(rot)));
		p.y = y;
		p.z = (float)(z*Math.cos(rot) - (x*Math.sin(rot)));
		return p;
	}
	
	private static Vector3D getrotZ(float x,float y,float z,float rot){
		Vector3D p = new Vector3D();
		p.z = z;
		p.x = (float)(x*Math.cos(rot) - (y*Math.sin(rot)));
		p.y = (float)(x*Math.sin(rot) + (y*Math.cos(rot)));
		return p;
	}
	
	
	private static Vector3D getrotX(float x,float y,float z,float rot){
		Vector3D p = new Vector3D();
		p.x = x;
		p.y = (float)((y*Math.cos(rot)) - (z*Math.sin(rot)));
		p.z = (float)((y*Math.sin(rot)) + (z*Math.cos(rot)));
		return p;
	}
	
	private Vector3D getrotY(float x,float y,float z){
		Vector3D p = new Vector3D();
		p.x = (float)(((x*Math.cos(rotY))+(z*Math.sin(rotY))));
		p.y = y;
		p.z = (float)(((x*Math.sin(rotY))+(z*Math.cos(rotY))));
		
		return p;
	}
	
	private Vector3D getrotZ(float x,float y,float z){
		Vector3D p = new Vector3D();
		
		p.x = (float)((x*Math.cos(rotZ)) - (y*Math.sin(rotZ)));
		p.y = (float)((x*Math.sin(rotZ)) + (y*Math.cos(rotZ)));
		p.z = z;
		return p;
	}
	
	private Vector3D getPoint(float x,float y,float z){
		Vector3D p = getrotX(x, y, z);
		p = getrotY(p.x,p.y,p.z);
		p = getrotZ(p.x,p.y,p.z);
		
		
		return p;
	}
	
	private Vector3D getVector3D(float x,float y,float z){
		Vector3D p = getPoint(x, y, z);
		if(Z!=0){	
			 p.x/= Z;
			 p.y/= Z;
		}
		
		return p;
	}
	
	public void drawPixel(float x,float y,float z){
		Vector3D p = getVector3D(x, y, z);
		g2d.drawRect((int)(X+(p.x*rescalar)), (int)(Y+(p.y*rescalar)),(int)(1*rescalar),(int)(1*rescalar));
	}
	
	public void drawOval(float x,float y,float z,float r){
		Vector3D p = getVector3D(x, y, z);
		g2d.fillOval((int)(X+((p.x-(r/2))*rescalar)), (int)(Y+((p.y-(r/2))*rescalar)),(int)(r*rescalar),(int)(r*rescalar));
	}
	
	public void drawRect(float x,float y,float z,float t){
		Vector3D p = getVector3D(x, y, z);
		g2d.fillRect((int)(X+((p.x-(t/2))*rescalar)), (int)(Y+((p.y-(t/2))*rescalar)),(int)(t*rescalar),(int)(t*rescalar));
	}
	
	public void drawEjes(){
		int Tamano = 20;
		
		Vector3D p2 = getVector3D(Tamano, 0, 0);
		g2d.setColor(Color.red);
		g2d.drawLine((int)(X),(int)(Y), (int)(X+(p2.x*rescalar)), (int)(Y+(p2.y*rescalar)));
		
		p2 = getVector3D(0,Tamano, 0);
		g2d.setColor(Color.GREEN);
		g2d.drawLine((int)(X),(int)(Y), (int)(X+(p2.x*rescalar)), (int)(Y+(p2.y*rescalar)));
		
		p2 = getVector3D(0, 0, Tamano);
		g2d.setColor(Color.BLUE);
		g2d.drawLine((int)(X),(int)(Y), (int)(X+(p2.x*rescalar)), (int)(Y+(p2.y*rescalar)));
		
	}
	
	public void drawLine(float x,float y,float z,float x2,float y2,float z2){
		

		Vector3D p1 = getVector3D(x, y, z);
		Vector3D p2 = getVector3D(x2, y2, z2);
	
		g2d.drawLine((int)(X+(p1.x*rescalar)),(int)(Y+(p1.y*rescalar)), (int)(X+(p2.x*rescalar)), (int)(Y+(p2.y*rescalar)));
	}
}
