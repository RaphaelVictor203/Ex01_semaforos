package controller;

import java.awt.Rectangle;
import java.util.concurrent.Semaphore;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Pessoa extends Thread{
	
	private int movX, movY;
	private String[] frames;
	private int frameAtual = 0;
	private JLabel porta;
	private String[] framesPorta;
	private JLabel label;
	private Semaphore semaforo;
	
	public Pessoa(int movX, int movY, String[] frames, JLabel porta, String[] framesPorta, JLabel label, Semaphore semaforo) {
		this.movX = movX;
		this.movY = movY;
		this.frames = frames;
		this.porta = porta;
		this.label = label;
		this.semaforo = semaforo;
		this.framesPorta = framesPorta;
	}
	
	public void run() {
		correr();
	};
	
	private void correr() {
		int vl = (int)((Math.random()*3) + 4);
		if(movX != 0) {
			if(movX < 0) {
				while(label.getBounds().x > (porta.getBounds().x + 100)) {
					mover(vl*(-1), 0);
				}
			}else {
				while(label.getBounds().x < (porta.getBounds().x - 100)) {
					mover(vl, 0);
				}
			}
		}else 
		if(movY != 0){
			if(movY < 0) {
				while(label.getBounds().y < (porta.getBounds().y - 50)) {
					mover(0, vl);
				}
			}else {
				while(label.getBounds().y > (porta.getBounds().y + 50)) {
					mover(0, vl*(-1));
				}
			}
		}
		try {
			semaforo.acquire();
			andarParaPorta();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			semaforo.release();
		}
	}
	
	private void mover(int x, int y) {
		label.setLocation(label.getBounds().x + x, label.getBounds().y + y);
		try {
			if(frameAtual < (frames.length-1)) {
				frameAtual++;
			}else {
				frameAtual = 0;
			}
			label.setIcon(new ImageIcon(frames[frameAtual]));
			sleep(150);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void andarParaPorta() {
		int vl = (int)((Math.random()*3) + 4);
		if(movY != 0) {
			if(movY < 0) {
				while(label.getBounds().y < porta.getBounds().y) {
					mover(0, vl);
				}
			}else {
				while(label.getBounds().y > (porta.getBounds().y)+25) {
					mover(0, vl*(-1));
				}
			}
		}else 
		if(movX != 0){
			if(movX < 0) {
				while(label.getBounds().x > porta.getBounds().x) {
					mover(vl*(-1), 0);
				}
			}else {
				while(label.getBounds().x < porta.getBounds().x) {
					mover(vl, 0);
				}
			}
		}
		try {
			sleep(((int)(Math.random()*2)+1)*1000);
			abrirPorta();
			label.setIcon(new ImageIcon(""));
			fecharPorta();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void abrirPorta() {
		for(int i=0; i<framesPorta.length; i++) {
			ImageIcon imgPorta = new ImageIcon(framesPorta[i]);
			imgPorta.setImage(imgPorta.getImage().getScaledInstance(37, 39, 100));
			porta.setIcon(imgPorta);
			try {
				sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void fecharPorta() {
		for(int i=framesPorta.length-1; i>=0; i--) {
			ImageIcon imgPorta = new ImageIcon(framesPorta[i]);
			imgPorta.setImage(imgPorta.getImage().getScaledInstance(37, 39, 100));
			porta.setIcon(imgPorta);
			try {
				sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
