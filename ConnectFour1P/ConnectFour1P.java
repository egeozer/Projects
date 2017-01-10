package test;
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


public class ConnectFour1P extends JApplet {

	static JButton start = new JButton("Start Over");//button to start over
	static JTextField text = new JTextField(30); //console
	JPanel panel = new JPanel();
public ConnectFour1P(){
	text.setEditable(false);	
	panel.add(text);
		
	panel.add(start);
		
	add(new board1());//adding board into the frame
		add(panel, BorderLayout.SOUTH);
		
		
	}
	

}

 class board1 extends JPanel implements MouseListener, ActionListener{
ArrayList <YellowToken> yellowList = new ArrayList<YellowToken>();
ArrayList <RedToken> redList = new ArrayList<RedToken>();
ArrayList <OpenToken> openList = new ArrayList<OpenToken>();
int end0 =35 ;
int end1 =36 ;
int end2 =37 ;
int end3 =38 ;
int end4 =39 ;
int end5 =40 ;
int end6 =41 ;
String whoseTurn = "R";
	public board1(){
		addMouseListener(this);
		ConnectFour1P.start.addActionListener(this);
		ConnectFour1P.text.setText("Console: Player Red starts first");
		
	}
	
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		
		int x=10;
		int y=10;
		g.setColor(Color.blue);
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
		g.setColor(Color.red);
		if(redList.size()>0)
		for(int i = 0; i<redList.size();i++)
			g.fillOval(redList.get(i).getX(),redList.get(i).getY(),50,50);
		
		g.setColor(Color.yellow);
		if(yellowList.size()>0)
		for(int i = 0; i<yellowList.size();i++)
			g.fillOval(yellowList.get(i).getX(),yellowList.get(i).getY(),50,50);
			
			if(redList.size()>=4)
			if(isRedWon()){
				ConnectFour1P.text.setText("Console: Red WON");
				
				for(int i = 0; i<openList.size();i++){
					if(openList.get(i).isWon()){
						g.setColor(Color.black);
					
						g.fillOval(openList.get(i).getX(),openList.get(i).getY(),50,50);
					}
				}
			}
			
			if(yellowList.size()>=4)
				if(isYellowWon()){
					ConnectFour1P.text.setText("Console: YELLOW WON");
					
					for(int i = 0; i<openList.size();i++){
						if(openList.get(i).isWon()){
							g.setColor(Color.black);
						
							g.fillOval(openList.get(i).getX(),openList.get(i).getY(),50,50);
						}
					}
				}
			if(redList.size()>=4){	//it will start checking if both red or yellow are winning if token size reaches 4 or more
				if(RedWinning()!=-1){
		  			RedWinning();
		  			repaint();
		  			
		  			
		  			
		  		}
				else if(YellowWinning()!=-1 && whoseTurn=="Y"){
					YellowWinning();
					
					repaint();
				}
					
			}

		
			
		
		
	}
	public void setCustomEnd(int end){	//method to set a custom end number
		 if((end)==end0)
			 end0-=7;
		 else if((end)==end1)
			 end1-=7;
		 else if((end)==end2)
				
			 end2-=7;
		
		 else if((end)==end3)
					 
			 end3-=7;
					
		 else if((end)==end4)
						
			 end4-=7;
						
		 else if((end)==end5)
							
			 end5-=7;
							
		 else if((end)==end6)
								
			 end6-=7;
	}
	public int GetCustomEnd(int end){	//getter for the method above
		 if((end)==end0)
			return end0;
		 else if((end)==end1)
			return end1;
		 else if((end)==end2)
				
			 return end2;
		
		 else if((end)==end3)
					 
			 return end3;
					
		 else if((end)==end4)
						
			 return end4;
						
		 else if((end)==end5)
							
			 return end5;
							
		 else if((end)==end6)
								
			 return end6;
		 return -1;
	}
	
	public int RedWinning(){	//method that checks if Red is winning(defensive AI)
		if(!isRedWon() && !isYellowWon() && whoseTurn != "R" ){
		
			for(int j = 0; j<=35;j+=7){			//check horizontally
			for( int i = 0; i<=3;i++){
				if((openList.get(j+i).isRed()&& openList.get(j+i+1).isRed()&&	//check horizontal winning right
						openList.get(j+i+2).isRed() && !openList.get(j+i+3).isYellow() ) && 
						(openList.get(j+i+3+7).isFilled() || j==35)){
							
				
						yellowList.add(new YellowToken(openList.get(j+i+3).getX(),openList.get(j+i+3).getY()));
						whoseTurn = "R";
					 openList.get(j+i+3).setYellow(true);
					 openList.get(j+i+3).setFilled(true);
					 setCustomEnd(j+i+3);
					System.out.println(yellowList.size());
					return  (0);
			
				}
				else if((!openList.get(j+i).isYellow()&& openList.get(j+i+1).isRed()&&	//check horizontal winning left
						openList.get(j+i+2).isRed() && openList.get(j+i+3).isRed() )
						&&(openList.get(j+i+7).isFilled()|| j==35)){
						
							yellowList.add(new YellowToken(openList.get(j+i).getX(),openList.get(j+i).getY()));
							whoseTurn = "R";
							openList.get(j+i).setYellow(true);
							 openList.get(j+i).setFilled(true);
							 setCustomEnd(j+i);
							 System.out.println("in");
							 return 1;
				}
				else if(openList.get(j+i).isRed() && !openList.get(j+i+1).isFilled() && openList.get(j+i+2).isRed() &&	//check horizontal winning 2nd token
						openList.get(j+i+3).isRed() &&(openList.get(j+i+1+7).isFilled()|| j==35)){
					yellowList.add(new YellowToken(openList.get(j+i+1).getX(),openList.get(j+i+1).getY()));
					whoseTurn = "R";
					openList.get(j+i+1).setYellow(true);
					 openList.get(j+i+1).setFilled(true);
					 setCustomEnd(j+i+1);
					 return 2;
					
				}
				else if(openList.get(j+i).isRed() && openList.get(j+i+1).isRed() && !openList.get(j+i+2).isFilled() && //check horizontal winning 3rd token
						openList.get(j+i+3).isRed() &&(openList.get(j+i+2+7).isFilled()|| j==35)){
					yellowList.add(new YellowToken(openList.get(j+i+2).getX(),openList.get(j+i+2).getY()));
					whoseTurn = "R";
					openList.get(j+i+2).setYellow(true);
					 openList.get(j+i+2).setFilled(true);
					 setCustomEnd(j+i+2);
					 return 3;
					
				}
				

			
			}
			
		}
		for(int j = 0; j<=7;j++){		//check vertically
			for( int i = 0; i<=14;i+=7){		
				if(!openList.get(j+i).isYellow()&& openList.get(j+i+7).isRed()&&
						openList.get(j+i+14).isRed() &&openList.get(j+i+21).isRed()){
					yellowList.add(new YellowToken(openList.get(j+i).getX(),openList.get(j+i).getY()));
					whoseTurn = "R";
					openList.get(j+i).setYellow(true);
					 openList.get(j+i).setFilled(true);
					 setCustomEnd(j+i);
					 System.out.println(yellowList.size());
					 return 4;
				}
				
			
			}
		}
		for( int j = 0; j <= 14; j+=7){		//up left to right diagonal down check
			for(int i = 0; i<=3;i++){
				if(openList.get(j+i).isRed()&& openList.get(j+i+7+1).isRed()&&
						openList.get(j+i+14+2).isRed() &&!openList.get(j+i+21+3).isYellow() &&( openList.get(j+i+21+3+7).isFilled()|| j==14)){
				
					 yellowList.add(new YellowToken(openList.get(j+i+21+3).getX(),openList.get(j+i+21+3).getY()));
					 whoseTurn = "R";
					 openList.get(j+i+21+3).setYellow(true);
					 openList.get(j+i+21+3).setFilled(true);
					 setCustomEnd(j+i+21+3);
					 System.out.println("ind");
					 return 5;
				}
				else if(openList.get(j+i).isRed()&& !openList.get(j+i+7+1).isYellow()&&
						openList.get(j+i+14+2).isRed() &&openList.get(j+i+21+3).isRed() && openList.get(j+i+7+1+7).isFilled()){
					yellowList.add(new YellowToken(openList.get(j+i+7+1).getX(),openList.get(j+i+7+1).getY()));
					whoseTurn = "R";
					 openList.get(j+i+7+1).setYellow(true);
					 openList.get(j+i+7+1).setFilled(true);
					 setCustomEnd(j+i+7+1);
					 System.out.println("ind");
					 return 6;
					
				}
				else if(openList.get(j+i).isRed()&& openList.get(j+i+7+1).isRed()&&
						!openList.get(j+i+14+2).isYellow() &&openList.get(j+i+21+3).isRed() && openList.get(j+i+14+2+7).isFilled()){
					yellowList.add(new YellowToken(openList.get(j+i+14+2).getX(),openList.get(j+i+14+2).getY()));
					whoseTurn = "R";
					 openList.get(j+i+14+2).setYellow(true);
					 openList.get(j+i+14+2).setFilled(true);
					 setCustomEnd(j+i+14+2);
					 System.out.println("ind");
					 return 7;
					
				}
				else if(!openList.get(j+i).isYellow()&& openList.get(j+i+7+1).isRed()&&	//left to right diagoal up check
						openList.get(j+i+14+2).isRed() &&openList.get(j+i+21+3).isRed() && openList.get(j+i+7).isFilled() ){
					
					 yellowList.add(new YellowToken(openList.get(j+i).getX(),openList.get(j+i).getY()));
					 whoseTurn = "R";
					 openList.get(j+i).setYellow(true);
					 openList.get(j+i).setFilled(true);
					 setCustomEnd(j+i);
					 System.out.println("ind");
					 return 8;
					
				}
				
				
			}
		}
		for( int j = 0; j <= 14; j+=7){		//up right to left diagonal Win
			for(int i = 6; i>=3;i--){
				if(!openList.get(j+i).isYellow()&& openList.get(j+i+7-1).isRed()&&
						openList.get(j+i+14-2).isRed() &&openList.get(j+i+21-3).isRed() && openList.get(j+i+7).isFilled()){
				
					 yellowList.add(new YellowToken(openList.get(j+i).getX(),openList.get(j+i).getY()));
					 whoseTurn = "R";
					 openList.get(j+i).setYellow(true);
					 openList.get(j+i).setFilled(true);
					 setCustomEnd(j+i);
					 System.out.println("ind");
					 return 8;
				}
				
				else if(openList.get(j+i).isRed()&& !openList.get(j+i+7-1).isYellow()&&
						openList.get(j+i+14-2).isRed() &&openList.get(j+i+21-3).isRed() && openList.get(j+i+7-1+7).isFilled()){
					 yellowList.add(new YellowToken(openList.get(j+i+7-1).getX(),openList.get(j+i+7-1).getY()));
					 whoseTurn = "R";
					 openList.get(j+i+7-1).setYellow(true);
					 openList.get(j+i+7-1).setFilled(true);
					 setCustomEnd(j+i+7-1);
					 System.out.println("ind");
					 return 8;
					
				}
				else if(openList.get(j+i).isRed()&& openList.get(j+i+7-1).isRed()&&
						!openList.get(j+i+14-2).isYellow() &&openList.get(j+i+21-3).isRed() && openList.get(j+i+14-2+7).isFilled()){
					 yellowList.add(new YellowToken(openList.get(j+i+14-2).getX(),openList.get(j+i+14-2).getY()));
					 whoseTurn = "R";
					 openList.get(j+i+14-2).setYellow(true);
					 openList.get(j+i+14-2).setFilled(true);
					 setCustomEnd(j+i+14-2);
					 System.out.println("ind");
					 return 8;
					
				}
				
				
				else if(openList.get(j+i).isRed()&& openList.get(j+i+7-1).isRed()&&
						openList.get(j+i+14-2).isRed() &&!openList.get(j+i+21-3).isYellow() && (openList.get(j+i+21-3+7).isFilled() || j==14)){
					
					yellowList.add(new YellowToken(openList.get(j+i+21-3).getX(),openList.get(j+i+21-3).getY()));
					whoseTurn = "R";
					 openList.get(j+i+21-3).setYellow(true);
					 openList.get(j+i+21-3).setFilled(true);
					 setCustomEnd(j+i+21-3);
					 System.out.println("ind2");
					 return 8;
					
				}
				
					
					
				
				
			}
		}
		
	
			
		
		}
		return -1;
		
	}
	public int YellowWinning(){	//method that checks if the AI is winning(Agresive AI)
		if(!isRedWon() && !isYellowWon() && whoseTurn != "R" ){
			
			for(int j = 0; j<=35;j+=7){			//check horizontally
			for( int i = 0; i<=3;i++){
				if((openList.get(j+i).isYellow()&& openList.get(j+i+1).isYellow()&&	//check horizontal winning right
						openList.get(j+i+2).isYellow() && !openList.get(j+i+3).isRed() ) && 
						(openList.get(j+i+3+7).isFilled() || j==35)){
							
				
						yellowList.add(new YellowToken(openList.get(j+i+3).getX(),openList.get(j+i+3).getY()));
						whoseTurn = "R";
					 openList.get(j+i+3).setYellow(true);
					 openList.get(j+i+3).setFilled(true);
					 setCustomEnd(j+i+3);
					System.out.println(yellowList.size());
					return  (0);
			
				}
				else if((!openList.get(j+i).isRed()&& openList.get(j+i+1).isYellow()&&	//check horizontal winning left
						openList.get(j+i+2).isYellow() && openList.get(j+i+3).isYellow() )
						&&(openList.get(j+i+7).isFilled()|| j==35)){
						
							yellowList.add(new YellowToken(openList.get(j+i).getX(),openList.get(j+i).getY()));
							whoseTurn = "R";
							openList.get(j+i).setYellow(true);
							 openList.get(j+i).setFilled(true);
							 setCustomEnd(j+i);
							 System.out.println("in");
							 return 1;
				}
				else if(openList.get(j+i).isYellow() && !openList.get(j+i+1).isFilled() && openList.get(j+i+2).isYellow() &&	//check horizontal winning 2nd token
						openList.get(j+i+3).isYellow() &&(openList.get(j+i+1+7).isFilled()|| j==35)){
					yellowList.add(new YellowToken(openList.get(j+i+1).getX(),openList.get(j+i+1).getY()));
					whoseTurn = "R";
					openList.get(j+i+1).setYellow(true);
					 openList.get(j+i+1).setFilled(true);
					 setCustomEnd(j+i+1);
					 return 2;
					
				}
				else if(openList.get(j+i).isYellow() && openList.get(j+i+1).isYellow() && !openList.get(j+i+2).isFilled() && //check horizontal winning 3rd token
						openList.get(j+i+3).isYellow() &&(openList.get(j+i+2+7).isFilled()|| j==35)){
					yellowList.add(new YellowToken(openList.get(j+i+2).getX(),openList.get(j+i+2).getY()));
					whoseTurn = "R";
					openList.get(j+i+2).setYellow(true);
					 openList.get(j+i+2).setFilled(true);
					 setCustomEnd(j+i+2);
					 return 3;
					
				}
				

			
			}
			
		}
		for(int j = 0; j<=7;j++){		//check vertically
			for( int i = 0; i<=14;i+=7){		
				if(!openList.get(j+i).isRed()&& openList.get(j+i+7).isYellow()&&
						openList.get(j+i+14).isYellow() &&openList.get(j+i+21).isYellow()){
					yellowList.add(new YellowToken(openList.get(j+i).getX(),openList.get(j+i).getY()));
					whoseTurn = "R";
					openList.get(j+i).setYellow(true);
					 openList.get(j+i).setFilled(true);
					 setCustomEnd(j+i);
					 System.out.println(yellowList.size());
					 return 4;
				}
				
			
			}
		}
		for( int j = 0; j <= 14; j+=7){		//up left to right diagonal down check
			for(int i = 0; i<=3;i++){
				if(openList.get(j+i).isYellow()&& openList.get(j+i+7+1).isYellow()&&
						openList.get(j+i+14+2).isYellow() &&!openList.get(j+i+21+3).isRed() &&( openList.get(j+i+21+3+7).isFilled()|| j==14)){
				
					 yellowList.add(new YellowToken(openList.get(j+i+21+3).getX(),openList.get(j+i+21+3).getY()));
					 whoseTurn = "R";
					 openList.get(j+i+21+3).setYellow(true);
					 openList.get(j+i+21+3).setFilled(true);
					 setCustomEnd(j+i+21+3);
					 System.out.println("ind");
					 return 5;
				}
				else if(openList.get(j+i).isYellow()&& !openList.get(j+i+7+1).isRed()&&
						openList.get(j+i+14+2).isYellow() &&openList.get(j+i+21+3).isYellow() && openList.get(j+i+7+1+7).isFilled()){
					yellowList.add(new YellowToken(openList.get(j+i+7+1).getX(),openList.get(j+i+7+1).getY()));
					whoseTurn = "R";
					 openList.get(j+i+7+1).setYellow(true);
					 openList.get(j+i+7+1).setFilled(true);
					 setCustomEnd(j+i+7+1);
					 System.out.println("ind");
					 return 6;
					
				}
				else if(openList.get(j+i).isYellow()&& openList.get(j+i+7+1).isYellow()&&
						!openList.get(j+i+14+2).isRed() &&openList.get(j+i+21+3).isYellow() && openList.get(j+i+14+2+7).isFilled()){
					yellowList.add(new YellowToken(openList.get(j+i+14+2).getX(),openList.get(j+i+14+2).getY()));
					whoseTurn = "R";
					 openList.get(j+i+14+2).setYellow(true);
					 openList.get(j+i+14+2).setFilled(true);
					 setCustomEnd(j+i+14+2);
					 System.out.println("ind");
					 return 7;
					
				}
				else if(!openList.get(j+i).isRed()&& openList.get(j+i+7+1).isYellow()&&	//left to right diagoal up check
						openList.get(j+i+14+2).isYellow() &&openList.get(j+i+21+3).isYellow() && openList.get(j+i+7).isFilled() ){
					
					 yellowList.add(new YellowToken(openList.get(j+i).getX(),openList.get(j+i).getY()));
					 whoseTurn = "R";
					 openList.get(j+i).setYellow(true);
					 openList.get(j+i).setFilled(true);
					 setCustomEnd(j+i);
					 System.out.println("ind");
					 return 8;
					
				}
				
				
			}
		}
		for( int j = 0; j <= 14; j+=7){		//up right to left diagonal Win
			for(int i = 6; i>=3;i--){
				if(!openList.get(j+i).isRed()&& openList.get(j+i+7-1).isYellow()&&
						openList.get(j+i+14-2).isYellow() &&openList.get(j+i+21-3).isYellow() && openList.get(j+i+7).isFilled()){
				
					 yellowList.add(new YellowToken(openList.get(j+i).getX(),openList.get(j+i).getY()));
					 whoseTurn = "R";
					 openList.get(j+i).setYellow(true);
					 openList.get(j+i).setFilled(true);
					 setCustomEnd(j+i);
					 System.out.println("ind");
					 return 8;
				}
				
				else if(openList.get(j+i).isYellow()&& !openList.get(j+i+7-1).isRed()&&
						openList.get(j+i+14-2).isYellow() &&openList.get(j+i+21-3).isYellow() && openList.get(j+i+7-1+7).isFilled()){
					 yellowList.add(new YellowToken(openList.get(j+i+7-1).getX(),openList.get(j+i+7-1).getY()));
					 whoseTurn = "R";
					 openList.get(j+i+7-1).setYellow(true);
					 openList.get(j+i+7-1).setFilled(true);
					 setCustomEnd(j+i+7-1);
					 System.out.println("ind");
					 return 8;
					
				}
				else if(openList.get(j+i).isYellow()&& openList.get(j+i+7-1).isYellow()&&
						!openList.get(j+i+14-2).isRed() &&openList.get(j+i+21-3).isYellow() && openList.get(j+i+14-2+7).isFilled()){
					 yellowList.add(new YellowToken(openList.get(j+i+14-2).getX(),openList.get(j+i+14-2).getY()));
					 whoseTurn = "R";
					 openList.get(j+i+14-2).setYellow(true);
					 openList.get(j+i+14-2).setFilled(true);
					 setCustomEnd(j+i+14-2);
					 System.out.println("ind");
					 return 8;
					
				}
				
				
				else if(openList.get(j+i).isYellow()&& openList.get(j+i+7-1).isYellow()&&
						openList.get(j+i+14-2).isYellow() &&!openList.get(j+i+21-3).isRed() && (openList.get(j+i+21-3+7).isFilled() || j==14)){
					
					yellowList.add(new YellowToken(openList.get(j+i+21-3).getX(),openList.get(j+i+21-3).getY()));
					whoseTurn = "R";
					 openList.get(j+i+21-3).setYellow(true);
					 openList.get(j+i+21-3).setFilled(true);
					 setCustomEnd(j+i+21-3);
					 System.out.println("ind2");
					 return 8;
					
				}
				
					
					
				
				
			}
		}
		
	
			
		
		}
		return -1;
		
		
	}
	

	public boolean isRedWon(){
		
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
	public boolean isYellowWon(){
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
	public void  setEnd(int end){
		try{
			//int random = (int)(Math.random()*10%7);
			
			if(openList.get(end).isFilled()==false){
				if(whoseTurn.equals("R")){
			
					redList.add(new RedToken(openList.get(end).getX(),openList.get(end).getY()));
			
					openList.get(end).setRed(true);
			
					
			
					ConnectFour1P.text.setText("Console: "+ whoseTurn);
				
			
					
				}
				
				openList.get(end).setFilled(true);
				repaint();
			
			}
		}
		catch(Exception e){
			ConnectFour1P.text.setText("Reached the maximum token");
		}
			
	}
	
	public void  setEndPC(int end){	//sets end for AI
		try{
			if(GetCustomEnd(end)<=0)	//if the token comes to a maximum capacity, it will regenerate a random number until its another coloumn
				setRandom();
			
					yellowList.add(new YellowToken(openList.get(end).getX(),openList.get(end).getY()));
			
					openList.get(end).setYellow(true);
			
					
			
					ConnectFour1P.text.setText("Console: "+ whoseTurn);
				
			
					
			
				
				openList.get(end).setFilled(true);
				whoseTurn = "R";
				repaint();
			
			
		}
		catch(Exception e){
			ConnectFour1P.text.setText("Reached the maximum token");
		}

	}
	public void setRandom(){	//method that puts a token in a random location if isRedWinning or isYellowWinning are not the case
		if(!isRedWon() && !isYellowWon()){
		int random = (int)(Math.random()*10%7);
		switch(random){
  		case 0 :setEndPC(end0);
  		end0-=7;
  		break;
  		case 1:setEndPC(end1);
  		end1-=7;
  		break;
  		case 2:setEndPC(end2);
  		end2-=7;
  		break;
  		case 3:setEndPC(end3);
  		end3-=7;
  		break;
  		case 4:setEndPC(end4);
  		end4-=7;
  		break;
  		case 5:setEndPC(end5);
  		end5-=7;
  		break;
  		case 6:setEndPC(end6);
  		end6-=7;
  		break;
		}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		PointerInfo a = MouseInfo.getPointerInfo();
  		Point point = new Point(a.getLocation());
  		SwingUtilities.convertPointFromScreen(point, e.getComponent());	//converting the mouseLocation into the panel
  		int x=(int) point.getX();	//x and y positions of the mouse
  		int y=(int) point.getY();
  	
  		if(!isRedWon() && !isYellowWon()){
  		if(x>openList.get(0).getX() && x<openList.get(0).getX()+50 && end0>=0){
  			
  		setEnd(end0);
  		end0-=7;
  		
  		
  			whoseTurn = "Y";
  			
  		if(RedWinning()==-1 && YellowWinning()==-1)
  		setRandom();
  		
  		
  		
  		
  
  		
  		}
  		else if(x>openList.get(1).getX() && x<openList.get(1).getX()+50&& end1>=0){
  			setEnd(end1);
  			end1-=7;
  			whoseTurn = "Y";
  	  			
  			if(RedWinning()==-1&& YellowWinning()==-1)
  	  		 setRandom();
  		}
  		else if(x>openList.get(2).getX() && x<openList.get(2).getX()+50&& end2>=0){
  			setEnd(end2);
  			end2-=7;
  			whoseTurn = "Y";
  			if(RedWinning()==-1&& YellowWinning()==-1)
  			 setRandom();
  		
  		}
  		else if(x>openList.get(3).getX() && x<openList.get(3).getX()+50&& end3>=0){
  			setEnd(end3);
  			end3-=7;
  			whoseTurn = "Y";
  			if(RedWinning()==-1&& YellowWinning()==-1)
  		 setRandom();
  		}
  			
  		else if(x>openList.get(4).getX() && x<openList.get(4).getX()+50&& end4>=0){
  			setEnd(end4);
  			end4-=7;
  			whoseTurn = "Y";
  			if(RedWinning()==-1&& YellowWinning()==-1)
  			setRandom();
  		}
  		else if(x>openList.get(5).getX() && x<openList.get(5).getX()+50&& end5>=0){
  			setEnd(end5);
  			end5-=7;
  			whoseTurn = "Y";
  			if(RedWinning()==-1&& YellowWinning()==-1)
  		 setRandom();
  		}
  			
  		else if(x>openList.get(6).getX() && x<openList.get(6).getX()+50&& end6>=0){
  			
  			setEnd(end6);
  			end6-=7;
  			whoseTurn = "Y";
  			if(RedWinning()==-1&& YellowWinning()==-1)
  		 setRandom();
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
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==ConnectFour1P.start){
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