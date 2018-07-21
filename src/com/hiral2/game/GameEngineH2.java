package com.hiral2.game;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;


public abstract class GameEngineH2 extends JFrame implements Runnable , MouseListener,MouseMotionListener,KeyListener,MouseWheelListener{
	private int timesleep = 5;
	private Thread t;
	private BufferStrategy bf;
	private boolean cont = true;
	private long sleepTime=0;
	private long timedurantiongame=0;
	private int typeGameLoop=0;
	public static final int TYPE_ADVANCE = 0;
	public static final int TYPE_BASIC = 1;
	
	
	public GameEngineH2(String title,int w,int h,int time){
		super(title);
		this.timedurantiongame = System.currentTimeMillis();
		setSize(w,h);
		timesleep = time;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		while(true){
			try{
				createBufferStrategy(2);
				break;
			}catch(Exception e){
				try {
					Thread.sleep(100);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
		}
		bf = getBufferStrategy();
		t = new Thread(this,"Engine");
		t.setPriority(Thread.MAX_PRIORITY);
	
	
	}
	
	public void setTypeGameLoop(int type){
		this.typeGameLoop = type;
	}
	
	public void enableMouseEvent(){
		this.addMouseListener(this);
	}
	
	public void enableMouseMotionEvent(){
		this.addMouseMotionListener(this);
	}
	
	public void enableKeyEvent(){
		this.addKeyListener(this);
	}
	
	public void ActivarWheelEvent(){
		this.addMouseWheelListener(this);
	}
	
	
	public void start(){
		t.start();
	}
	
	public abstract void init();
	
	public abstract void end();
	
	private void advanceGameLoop(){
		short set=0;
		short MaxSet=5;
		long lastDuractionDraw=0;
		
		while(cont){
			long timeini = System.currentTimeMillis();
			update();
			long tsleep = System.currentTimeMillis()-timeini;
			if(((tsleep<timesleep) && lastDuractionDraw<timesleep)  || (set>=MaxSet)){
				set=0;
				long timeinidraw = System.currentTimeMillis();
				draw();
				lastDuractionDraw =  System.currentTimeMillis() - timeinidraw;
		
			}else{
				set++;
			}
			try{
				tsleep = System.currentTimeMillis()-timeini;	
				if (tsleep<timesleep){
					Thread.sleep(timesleep-tsleep);	
				}else{
					Thread.sleep(10);
				}
				sleepTime = tsleep;
			}catch(Exception e){
				// TODO: log this error
			}
			
		}
	
	}
	
	private void basicGameLoop(){
		
		while(cont){
			long timeini = System.currentTimeMillis();
			update();
			draw();
				
			try{
				long tsleep = System.currentTimeMillis()-timeini;	
				if (tsleep<timesleep){
					Thread.sleep(timesleep-tsleep);	
				}else{
					Thread.sleep(10);
				}
				sleepTime = tsleep;
			}catch(Exception e){
				
			}
			
		}
		
	}
	
	@Override
	public void run() {
		init();
		switch (typeGameLoop) {
		case 0:
			advanceGameLoop();
			break;

		default:
			basicGameLoop();
			break;
		}
		this.end();
	}
	
	public abstract void update();
	
	public void ClearScreen(Graphics2D g,Color c){
		g.setColor(c);
		g.fillRect(0,0, getWidth(), getHeight());
		
	}
	
	public void optimizeGraphics2d(Graphics2D g2d){
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
	}
	private void draw(){
		try{
		Graphics2D g = (Graphics2D)bf.getDrawGraphics();
		draw(g);
		g.dispose();
		}finally{
			bf.show();
		}
	}
	
	public long getSleepTime(){
		return sleepTime;
	}
	
	public long getGameTime(){
		return System.currentTimeMillis()-timedurantiongame;
	}
	
	public abstract void draw(Graphics2D g);

	@Override
	public void mousePressed(MouseEvent arg0) {
	
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
	
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
	
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
	
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent arg0) {
	
	}

	
}
