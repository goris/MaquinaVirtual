package main.pkg;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class MyPanel extends JPanel {

	/**
	 * Random
	 */
	private static final long serialVersionUID = -7896858517075259750L;
	public ArrayList<Square> cuadrados;
	public ArrayList<Poligono> poligonos;
	public ArrayList<Linea> lineas;

	public MyPanel() {
		cuadrados = new ArrayList<Square>();
		poligonos = new ArrayList<Poligono>();
		lineas = new ArrayList<Linea>();

		setBorder(BorderFactory.createLineBorder(Color.black));
	}

	public Dimension getPreferredSize() {
		return new Dimension(800,600);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);       

		for(int i = 0; i < cuadrados.size(); i++) {
			(cuadrados.get(i)).paintSquare(g);
		}

		for(int i = 0; i < poligonos.size(); i++) {
			(poligonos.get(i)).paintPoligono(g);
		}
		
		for(int i = 0; i < lineas.size(); i++) {
			(lineas.get(i)).paintLinea(g);
		}
	}  
}

class Linea{
	private int x1Pos;
	private int y1Pos;
	private int x2Pos;
	private int y2Pos;

	Linea(int x1, int y1, int x2, int y2){
		this.x1Pos = x1;
		this.y1Pos = y1;
		this.setX2Pos(x2);
		this.setY2Pos(y2);
	}

	public void paintLinea(Graphics g){
		g.setColor(Color.BLACK);
		g.drawLine(x1Pos, y1Pos, x2Pos, y2Pos);
	}

	public int getX2Pos() {
		return x2Pos;
	}

	public void setX2Pos(int x2Pos) {
		this.x2Pos = x2Pos;
	}
	
	private void setY2Pos(int y2) {
		this.y2Pos = y2;		
	}
	
	public int gety2Pos() {
		return y2Pos;
	}
}

class Poligono{
	private int[] x = {1, 2, 3};
	private int[] y = {2, 3, 4};
	private int size = 3;

	Poligono(int[] x, int[] y, int size){
		this.x = x;
		this.y = y;
		this.size = size;
	}

	public void paintPoligono(Graphics g){
		g.setColor(Color.BLUE);
		g.drawPolygon(x, y, size);
		g.setColor(Color.YELLOW);
		g.fillPolygon(x, y, size);
	}

	public void setX(int[] x){ 
		this.x = x;
	}

	public int[] getX(){
		return x;
	}

	public void setY(int[] y){
		this.y = y;
	}

	public int[] getY(){
		return y;
	}
}
class Elipse {
	private int xPos = 0;
	private int yPos = 0;
	private int width = 1;
	private int height = 1;

	Elipse(int xPos, int yPos, int width, int height){
		this.xPos = xPos;
		this.yPos = yPos;
		this.width = width;
		this.height = height;
	}

	public void setX(int xPos){ 
		this.xPos = xPos;
	}

	public int getX(){
		return xPos;
	}

	public void setY(int yPos){
		this.yPos = yPos;
	}

	public int getY(){
		return yPos;
	}

	public int getWidth(){
		return width;
	} 

	public int getHeight(){
		return height;
	}

	public void paintElipse(Graphics g){
		g.setColor(Color.YELLOW);
		g.fillOval(xPos,yPos,width,height);
		g.setColor(Color.ORANGE);
		g.drawOval(xPos,yPos,width,height); 
		
	}
}
class Square{

	private int xPos = 0;
	private int yPos = 0;
	private int width = 1;
	private int height = 1;

	Square(int xPos, int yPos, int width, int height){
		this.xPos = xPos;
		this.yPos = yPos;
		this.width = width;
		this.height = height;
	}

	public void setX(int xPos){ 
		this.xPos = xPos;
	}

	public int getX(){
		return xPos;
	}

	public void setY(int yPos){
		this.yPos = yPos;
	}

	public int getY(){
		return yPos;
	}

	public int getWidth(){
		return width;
	} 

	public int getHeight(){
		return height;
	}

	public void paintSquare(Graphics g){
		g.setColor(Color.RED);
		g.fillRect(xPos,yPos,width,height);
		g.setColor(Color.BLACK);
		g.drawRect(xPos,yPos,width,height); 
		
	}
}
