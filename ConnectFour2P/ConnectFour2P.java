import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;


public class ConnectFour2P extends JApplet {

	static JButton start = new JButton("Start Over");	//button to start over
	static JTextField text = new JTextField(30);	//console
	JPanel panel = new JPanel();
public ConnectFour2P(){	//default constructor
	text.setEditable(false);	
	panel.add(text);
		
	panel.add(start);
		
	add(new board());	//adding board into the frame
		add(panel, BorderLayout.SOUTH);
		
		
	}
	

}

 class board extends JPanel implements MouseListener, ActionListener{
ArrayList <YellowToken> yellowList = new ArrayList<YellowToken>();	//each arraylist for each different token
ArrayList <RedToken> redList = new ArrayList<RedToken>();
ArrayList <OpenToken> openList = new ArrayList<OpenToken>();
int end0 =35 ;	//the last row initial places. this will decrement by 7 on each click
int end1 =36 ;
int end2 =37 ;
int end3 =38 ;
int end4 =39 ;
int end5 =40 ;
int end6 =41 ;
String whoseTurn = "R";	//whoseTurn changes with each click
	public board(){	//default constructor for board
		addMouseListener(this);	//adding mouse and actionListeners
		ConnectFour2P.start.addActionListener(this);
		ConnectFour2P.text.setText("Console: Player Red starts first");
		
	}
	
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		
		int x=10;
		int y=10;
		g.setColor(Color.blue);	//grawing the board
		g.fillRect(0,0,getWidth(),getHeight());
		
		g.setColor(Color.white);
		
		for(int i = 0; i<6;i++){
			for(int j = 0;j<7;j++){
				
				g.fillOval(x,y,50,50);
				openList.add(new OpenToken(x,y));
				
				x+=70;
				
			}
			y+=70;
			x=10;
		}
		g.setColor(Color.red);	//drawing each token
		if(redList.size()>0)
		for(int i = 0; i<redList.size();i++)
			g.fillOval(redList.get(i).getX(),redList.get(i).getY(),50,50);
		
		g.setColor(Color.yellow);
		if(yellowList.size()>0)
		for(int i = 0; i<yellowList.size();i++)
			g.fillOval(yellowList.get(i).getX(),yellowList.get(i).getY(),50,50);
			
			if(redList.size()>=4)	//starts to check after 4 token played if anyone has won
			if(isRedWon()){
				ConnectFour2P.text.setText("Console: Red WON");
				
				for(int i = 0; i<openList.size();i++){
					if(openList.get(i).isWon()){
						g.setColor(Color.black);
					
						g.fillOval(openList.get(i).getX(),openList.get(i).getY(),50,50);
					}
				}
			}
			
			if(yellowList.size()>=4)
				if(isYellowWon()){
					ConnectFour2P.text.setText("Console: YELLOW WON");
					
					for(int i = 0; i<openList.size();i++){
						if(openList.get(i).isWon()){
							g.setColor(Color.black);
						
							g.fillOval(openList.get(i).getX(),openList.get(i).getY(),50,50);
						}
					}
				}
		
		
		
		
	}
	public boolean isRedWon(){	//method that checks if red wins
		
	for(int j = 0; j<=35;j+=7){			//horizontal win
		for( int i = 0; i<=3;i++){
			if(openList.get(j+i).isRed()&& openList.get(j+i+1).isRed()&&
					openList.get(j+i+2).isRed() &&openList.get(j+i+3).isRed()){
				openList.get(j+i).setWon(true);
				openList.get(j+i+1).setWon(true);
				openList.get(j+i+2).setWon(true);
				openList.get(j+i+3).setWon(true);
		return true;
			}
		
		}
	}
	for(int j = 0; j<=7;j++){		//vertical win
		for( int i = 0; i<=14;i+=7){		
			if(openList.get(j+i).isRed()&& openList.get(j+i+7).isRed()&&
					openList.get(j+i+14).isRed() &&openList.get(j+i+21).isRed()){
				openList.get(j+i).setWon(true);
				openList.get(j+i+7).setWon(true);
				openList.get(j+i+14).setWon(true);
				openList.get(j+i+21).setWon(true);
				
		return true;
			}
		
		}
	}
	for( int j = 0; j <= 14; j+=7)		//left to right diagonal Win
		for(int i = 0; i<=3;i++){
			if(openList.get(j+i).isRed()&& openList.get(j+i+7+1).isRed()&&
					openList.get(j+i+14+2).isRed() &&openList.get(j+i+21+3).isRed()){
				openList.get(j+i).setWon(true);
				openList.get(j+i+7+1).setWon(true);
				openList.get(j+i+14+2).setWon(true);
				openList.get(j+i+21+3).setWon(true);
					return true;
			}
			
		}
	
	for( int j = 0; j <= 14; j+=7)		//right to left diagonal Win
		for(int i = 6; i>=3;i--){
			if(openList.get(j+i).isRed()&& openList.get(j+i+7-1).isRed()&&
					openList.get(j+i+14-2).isRed() &&openList.get(j+i+21-3).isRed()){
				
				openList.get(j+i).setWon(true);
				openList.get(j+i+7-1).setWon(true);
				openList.get(j+i+14-2).setWon(true);
				openList.get(j+i+21-3).setWon(true);
					return true;
			}
			
		}
									
		return false;
		

		
	}
	public boolean isYellowWon(){	//method that checks if yellow Won
		for(int j = 0; j<=35;j+=7){			//horizontal win
			for( int i = 0; i<=3;i++){
				if(openList.get(j+i).isYellow()&& openList.get(j+i+1).isYellow()&&
						openList.get(j+i+2).isYellow() &&openList.get(j+i+3).isYellow()){
					openList.get(j+i).setWon(true);
					openList.get(j+i+1).setWon(true);
					openList.get(j+i+2).setWon(true);
					openList.get(j+i+3).setWon(true);
			return true;
				}
			
			}
		}
		for(int j = 0; j<=7;j++){		//vertical win
			for( int i = 0; i<=14;i+=7){		
				if(openList.get(j+i).isYellow()&& openList.get(j+i+7).isYellow()&&
						openList.get(j+i+14).isYellow() &&openList.get(j+i+21).isYellow()){
					openList.get(j+i).setWon(true);
					openList.get(j+i+7).setWon(true);
					openList.get(j+i+14).setWon(true);
					openList.get(j+i+21).setWon(true);
					
			return true;
				}
			
			}
		}
		
		for( int j = 0; j <= 14; j+=7)		//left to right diagonal Win
			for(int i = 0; i<=3;i++){
				if(openList.get(j+i).isYellow()&& openList.get(j+i+7+1).isYellow()&&
						openList.get(j+i+14+2).isYellow() &&openList.get(j+i+21+3).isYellow()){
					openList.get(j+i).setWon(true);
					openList.get(j+i+7+1).setWon(true);
					openList.get(j+i+14+2).setWon(true);
					openList.get(j+i+21+3).setWon(true);
						return true;
				}
				
			}
		
		for( int j = 0; j <= 14; j+=7)		//right to left diagonal Win
			for(int i = 6; i>=3;i--){
				if(openList.get(j+i).isYellow()&& openList.get(j+i+7-1).isYellow()&&
						openList.get(j+i+14-2).isYellow() &&openList.get(j+i+21-3).isYellow()){
					
					openList.get(j+i).setWon(true);
					openList.get(j+i+7-1).setWon(true);
					openList.get(j+i+14-2).setWon(true);
					openList.get(j+i+21-3).setWon(true);
						return true;
				}
				
			}
			return false;
		
	}
	public void  setEnd(int end){	//method that updates the end number
		try{
			
			if(openList.get(end).isFilled()==false){	//the first condition is that the token must not be filled
				if(whoseTurn.equals("R")){	//and that its red's turn
			
					redList.add(new RedToken(openList.get(end).getX(),openList.get(end).getY()));
			
					openList.get(end).setRed(true);
			
					whoseTurn="Y";
			
					ConnectFour2P.text.setText("Console: "+ whoseTurn);
				
				}
				else if(whoseTurn.equals("Y")) {	//same thing for yellow
					
					yellowList.add(new YellowToken(openList.get(end).getX(),openList.get(end).getY()));
					
					openList.get(end).setYellow(true);
				
					whoseTurn="R";
				
					ConnectFour2P.text.setText("Console: "+whoseTurn);
				}
				
				openList.get(end).setFilled(true);
				repaint();
			
			}
		}
		catch(Exception e){	//if the board maximum is reached the following message will display on console
			ConnectFour2P.text.setText("Reached the maximum token");
		}
			
	}

	@Override
	public void mouseClicked(MouseEvent e) {	//mouseClicked method that listens the mouse
		PointerInfo a = MouseInfo.getPointerInfo();
  		Point point = new Point(a.getLocation());
  		SwingUtilities.convertPointFromScreen(point, e.getComponent());	//converting the mouseLocation into the panel
  		int x=(int) point.getX();	//x and y positions of the mouse
  		int y=(int) point.getY();
  		if(!isRedWon() && !isYellowWon()){	//if one of the two players wins, no one can click
  		if(x>openList.get(0).getX() && x<openList.get(0).getX()+50){	//these will get the location in which the user clicks on which column
  			
  		setEnd(end0);	//the ends are updated
  		end0-=7;	//the corresponding last row will decrement by 7
  		
  		}
  		else if(x>openList.get(1).getX() && x<openList.get(1).getX()+50){
  			setEnd(end1);
  			end1-=7;
  		}
  		else if(x>openList.get(2).getX() && x<openList.get(2).getX()+50){
  			setEnd(end2);
  			end2-=7;
  		
  		}
  		else if(x>openList.get(3).getX() && x<openList.get(3).getX()+50){
  			setEnd(end3);
  			end3-=7;
  		}
  			
  		else if(x>openList.get(4).getX() && x<openList.get(4).getX()+50){
  			setEnd(end4);
  			end4-=7;
  		}
  		else if(x>openList.get(5).getX() && x<openList.get(5).getX()+50){
  			setEnd(end5);
  			end5-=7;
  		}
  			
  		else if(x>openList.get(6).getX() && x<openList.get(6).getX()+50){
  			
  			setEnd(end6);
  			end6-=7;
  		}
  		}
  		
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {	//each time the person clicks on start over every end is reset so as each arraylist
		if(e.getSource()==ConnectFour2P.start){
			redList.clear();
			yellowList.clear();
			openList.clear();
			repaint();
			 end0 =35 ;
			 end1 =36 ;
			 end2 =37 ;
			 end3 =38 ;
			 end4 =39 ;
			 end5 =40 ;
			 end6 =41 ;
		}
		
	}
}