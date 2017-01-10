import javax.swing.*;



import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Line2D;
public class AnimateHangman extends JFrame {	//class hangMan
	AnimateHangman(){	//default constructor
		
		add(new PanelHang());	//adding PanelHang into the frame
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {	//main method
		AnimateHangman frame = new AnimateHangman();
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setSize(300,300);
		frame.setLocationRelativeTo(null);
		// TODO Auto-generated method stub

	}

}
 class PanelHang extends JPanel implements ActionListener, KeyListener{	//PanelHang class extending Jpanel implementing ActionListener and KeyListener
	  int  delay = 15;
	  Timer timer = new Timer(delay,this);	//timer
	 int angle = 1;	//the angle used, that is incremented
	 int lengthOfRope = 25;	//length of teh rope
	 
	 double x1 = 170;	//initial x and y value of the animation
	 double y1 = 70;
	 
	 double x2Rope = 0;	//initializing the end points of each part of body
	 double y2Rope = 0;
	 
	 double x2Body = 0;
	 double y2Body = 0;
	 
	 double x2rightA = 0;
	 double y2rightA = 0;
	 double x2leftA = 0;
	 double y2leftA = 0;
	 
	 double x2rightL= 0;
	 double y2rightL = 0;
	 
	 double x2leftL = 0;
	 double y2leftL = 0;
	 
	 public PanelHang(){	//default constructor
		 
		 addKeyListener(this);
			setFocusable(true);
	 }
	
	 int unit = 1;	//unit to increment or decrement angle
	 protected void paintComponent(Graphics g){	//paint component
		 super.paintComponent(g);
		
		 int width = getWidth();	//getting the width and height
		 int height = getHeight();
		 
		Graphics2D g2 = (Graphics2D) g;	//graphics2D to use double for each position for max precision
		 
		 
		
		 
		timer.start();	//starting timer
		g2.draw(new Line2D.Double(x1, y1, x2Rope,y2Rope));	//rope
		g.drawOval((int)x2Rope-10, (int)y2Rope, 20, 20);	//head
		
		g2.draw(new Line2D.Double(x2Rope, y2Rope+20, x2Body,y2Body));
		g2.draw(new Line2D.Double(x2Body, y2Body,x2rightL+10 ,y2rightL)); //right leg
		g2.draw(new Line2D.Double(x2Body, y2Body,x2leftL-10 ,y2leftL)); //left leg
		 
		g2.draw(new Line2D.Double(x2Rope, y2Rope+20,x2rightA+10 ,y2rightA)); //right arm
		g2.draw(new Line2D.Double(x2Rope, y2Rope+20,x2leftA-10 ,y2leftA)); //left arm
	
		g.drawArc(20, height-50, 100, 50, 0, 180); //the feet
		g.drawLine(70,height-50,70,30);
		g.drawLine(70, 30, 170, 30);
		g.drawLine(170, 30, 170, 70);
	 }
	 

	@Override
	public void actionPerformed(ActionEvent e) {	//actionPerformed method for timer
		// TODO Auto-generated method stub
		
		angle+=unit;	//incrementing the angle with unit
		x2Rope=x1+(lengthOfRope*Math.sin(Math.toRadians(angle)));	//changing each end points of each part of body with each change of angle
		y2Rope =y1+ (lengthOfRope*Math.cos(Math.toRadians(angle)));
		
		x2Body=x2Rope+(50*Math.sin(Math.toRadians(angle)));
		y2Body =30+y2Rope+ (50*Math.cos(Math.toRadians(angle)));
		
		 x2rightL= x2Body+(30*Math.sin(Math.toRadians(angle)));
		 y2rightL =y2Body+(30*Math.cos(Math.toRadians(angle)));
		 
		 x2leftL= x2Body+(30*Math.sin(Math.toRadians(angle)));
		 y2leftL =y2Body+(30*Math.cos(Math.toRadians(angle)));
		 
		 
		 x2rightA= x2Rope+(40*Math.sin(Math.toRadians(angle)));
		 y2rightA = 20+y2Rope+(40*Math.cos(Math.toRadians(angle)));
		 
		x2leftA= x2Rope+(40*Math.sin(Math.toRadians(angle)));
		y2leftA = 20+y2Rope+(40*Math.cos(Math.toRadians(angle)));
		
		
		
		if(angle ==45){	//if angle reaches 45 degrees, the unit sign changes
			unit=-unit;
			
		}
		if(angle ==-45){	//if angle reaches -45(left side) the sign changes again(loop)
			unit=-unit;
			
		}
		
		
			repaint();
	}


	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_UP && delay>1){	//delay gets smaller when user presses UP key, minimum delay is 1
			
			timer.setDelay(delay--);
		
			System.out.println(delay);
	
		}
		if(e.getKeyCode()==KeyEvent.VK_DOWN ){//delay gets bigger when user presses DOWN key
			
			timer.setDelay(delay++);
				
			
		}
		if(e.getKeyCode()==KeyEvent.VK_R){	//timer is resumed when user pressed R
			timer.start();
			
			
		}
		if(e.getKeyCode()==KeyEvent.VK_S){	//timer is stopped when user presses S
			timer.stop();
			
			//System.out.println("stopped");
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
		
		public void setDelay(int delay){	//setDelay method
			this.delay=delay;
		}
				
	
	
	
}