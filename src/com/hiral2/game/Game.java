package com.hiral2.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import com.hiral2.elements.Star;
import com.hiral2.engine3D.Render3DEngine;


public class Game extends GameEngineH2 implements KeyListener {
	Render3DEngine r2d = new Render3DEngine();
	
	private ArrayList<Star> starsG = new ArrayList<Star>();
	private ArrayList<Star> starsW = new ArrayList<Star>();
	
	public Game() {
		super("Universe", 800, 600,50);
		this.start();
	}

	@Override
	public void init() {
		this.enableMouseEvent();
		this.enableMouseMotionEvent();
		this.enableKeyEvent();
		r2d.X=this.getWidth()/2;
		r2d.Y=this.getHeight()/2;
		colorArray(starsG,Color.GRAY,500);
		colorArray(starsW,Color.WHITE,500);
	}
	
	public void colorArray(ArrayList<Star> stars,Color c,int count){
		for(int i=0;i<count;i++){
			Star b = new Star();
			b.c = c;
			stars.add(b);
		}
	}

	public void initOval(ArrayList<Star> stars,Color c,float add){
		for(float i=0;i<Math.PI;i+=add){
			for(float j=0;j<2*Math.PI;j+=add){
				Star b = new Star(0, 0, 0,5, 1, j,i, c);
				b.c = c;
				stars.add(b);
			}
		}
	}
	
	@Override
	public void end() {
	}

	@Override
	public void update() {
		//r2d.rotZ+=Math.PI/100;
		//r2d.rotX+=Math.PI/100;
		//r2d.rotZ+=Math.PI/100;
	}

	public void drawArray(ArrayList<Star> stars){
		//Star antes= null;
		
		for(Star b:stars){
			b.draw(r2d);
		}
	}
	
	@Override
	public void draw(Graphics2D g) {
		//int longs = 30;
		optimizeGraphics2d(g);
		ClearScreen(g,new Color(0,0,0,255));
		g.setColor(Color.white);
		g.drawString("Sleep time by frame : "+String.valueOf(this.getSleepTime()), 30, 60);
		g.drawString("Game Time : "+String.valueOf(((this.getGameTime()/1000)/60/60)%24)+":"+String.valueOf(((this.getGameTime()/1000)/60)%60)+":"+String.valueOf((this.getGameTime()/1000)%60), 30, 100);
		g.drawString("Rotate(Up,Down,Left,Right,Z,X), Move(A,S,D,W), Zoom(Q,E)", 30, 120);
		g.setColor(Color.YELLOW);
		g.drawString("By Hiraldo", 30, 140);
		
		r2d.setG2d(g);
		drawArray(starsG);
		drawArray(starsW);
		r2d.drawEjes();
	}

	public void drawCube(Graphics2D g){
		for(int x=-250;x<250;x+=50){
			for(int y=-250;y<250;y+=50){
				for(int z=-250;z<250;z+=50){
					Color c =new Color((float)(x+250)/500f,(float)(y+250)/500f,(float)(z+250)/500f);
					g.setColor(c);
					r2d.drawPixel(x, y, z);						
				}
			}
		}
	}
	
	public void drawCubeEmpty(Graphics2D g){
		r2d.drawLine( -250, -250, -250, 250, -250, -250);
		r2d.drawLine( -250, -250, -250, -250, 250, -250);
		r2d.drawLine( -250, 250, -250, 250, 250, -250);
		r2d.drawLine( 250, -250, -250, 250, 250, -250);
		
		r2d.drawLine( -250, -250, 250, 250, -250, 250);
		r2d.drawLine( -250, -250, 250, -250, 250, 250);
		r2d.drawLine( -250, 250, 250, 250, 250, 250);
		r2d.drawLine( 250, -250, 250, 250, 250, 250);
			
		r2d.drawLine( -250, -250, 250, -250, -250, -250);
		r2d.drawLine( -250, 250, 250, -250, 250, -250);
		r2d.drawLine( 250, -250, 250, 250,- 250, -250);
		r2d.drawLine( 250, 250, 250, 250, 250, -250);
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseMoved(MouseEvent e){
		
		
	}
	
	@Override
	public void mouseDragged(MouseEvent e){
		
	}
	
	@Override
	public void mouseReleased(MouseEvent arg0){
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		switch (arg0.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				this.r2d.rotY-=Math.PI/100;
				break;
	
			case KeyEvent.VK_RIGHT:
				this.r2d.rotY+=Math.PI/100;
				break;
			case KeyEvent.VK_UP:
				this.r2d.rotX-=Math.PI/100;
				break;
	
			case KeyEvent.VK_DOWN:
				this.r2d.rotX+=Math.PI/100;
				break;
			case KeyEvent.VK_Z:
				this.r2d.rotZ-=Math.PI/100;
				break;
	
			case KeyEvent.VK_X:
				this.r2d.rotZ+=Math.PI/100;
				break;
				//------
			case KeyEvent.VK_A:
				this.r2d.X-=0.5f;
				break;
	
			case KeyEvent.VK_D:
				this.r2d.X+=0.5f;
				break;
			case KeyEvent.VK_W:
				this.r2d.Y+=0.5f;
				break;
	
			case KeyEvent.VK_S:
				this.r2d.Y-=0.5f;
				break;
			case KeyEvent.VK_Q:
				this.r2d.Z/=1.2f;
				break;
	
			case KeyEvent.VK_E:
				this.r2d.Z*=1.2f;
				break;
		}
	}
}
