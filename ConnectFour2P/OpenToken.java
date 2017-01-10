import java.awt.Color;
import java.awt.Graphics;


public class OpenToken {
int x = 0;
int y =0;
boolean filled = false;	//instance variables
boolean yellow = false;
boolean red = false;
boolean won = false;






public boolean isWon() {
	return won;
}
public void setWon(boolean won) {	//sets the token to won
	this.won = won;
}
public boolean isYellow() {
	return yellow;
}
public void setYellow(boolean yellow) {	//getters and setters for setting or getting a token
	this.yellow = yellow;
}
public boolean isRed() {
	return red;
}
public void setRed(boolean red) {
	this.red = red;
}
	public OpenToken(){
		
	}
	public OpenToken(int x, int y){	//constructors with x and y arguments
		this.x=x;
		this.y=y;
	}
	public int getX(){	//getters and setters for x and y position
		return x;
		
	}
	
	public int getY(){
		return y;
		
	}
	public void setFilled(boolean filled){	//fills the token
		this.filled=filled;
	}
	public boolean isFilled(){
		return filled;
	}
}
