package com.nbcc;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class DrawEnv extends JPanel implements ActionListener, KeyListener
{
            Timer t = new Timer(15, this);
            
// <editor-fold defaultstate="collapsed" desc="image intialize">
	public BufferedImage imggrass = null; //buffer sky image
	public BufferedImage imgsky = null; //buffer grass image
	public BufferedImage imgfireballright = null; //buffer fireball image (right)
	public BufferedImage imgfireballleft = null; //buffer fireball image (left)
	public BufferedImage imgtarget = null; //buffer target image
	public BufferedImage imgtitle = null; //buffer title image
	public BufferedImage imgnext = null; //buffer advance image
	public BufferedImage imginst = null; //buffer instructions image
	public BufferedImage imgui = null; //buffer UI image
	public BufferedImage imgheart = null;
	public BufferedImage imgblink = null;
	public BufferedImage imgblinkcd = null;
	public BufferedImage imggameover = null;
	public BufferedImage imgblueright = null; 
	public BufferedImage imgblueleft = null; 
	public BufferedImage imgredright = null;
	public BufferedImage imgredleft = null; 
	public BufferedImage imggreenright = null; 
	public BufferedImage imggreenleft = null; 
	public BufferedImage imgpurpleright = null; 
	public BufferedImage imgpurpleleft = null; 
	public BufferedImage imgcheater = null;
	public BufferedImage imgcomplete = null;     
// </editor-fold>
        
	     
// <editor-fold defaultstate="collapsed" desc="variable intialize">
	String direction = "right"; //set player direction
	String firedirection = "right"; //set fire ball direction
	int jump = 0; //start jump at 0 (able to jump)
	int fireball = 0; //start fireball at 0 (able to shoot)
	int x = 0; //player's starting x 
	int y = 455; //player's starting y 
	int playerx = 0; //player's x position in array (uninitialized)
	int playery = 0; //player's y position in array (uninitialized)
	int halfblocky = 0; //variable that will check if a player is halfway through a block
	int firex = x + 40; //fireball's x position (player's x + 40)
	int firey = y; //fireball's y position (player's y + 20)
	int firecount = 0; //fireball counter set at 0
	int target1 = 0, target2 = 0, target3 = 0, target4 = 0, target5 = 0, target6 = 0, target7 = 0, target8 = 0; //targets are set to alive (0)
	int target1x = 815, target2x = 270, target3x = 550, target4x = 420, target5x = 950, target6x = 0, target7x = 0, target8x = 0; //set target x values
	int target1y = 325, target2y = 65, target3y = 196, target4y = 196, target5y = 196, target6y = 0, target7y = 0, target8y = 0; //set target y values
	int lives = 3; //lives
	static public int points = 0; //points
	int start = 0; //print title screen or start game
	int instructions = 0; //show instructions
	int gameover = 0; //game over is 0, so game is not over
	char color = 'b'; //color of player sprite
	int blink = 0; //start blink off cooldown
	int quit = 0; //prompt are you sure message
	int wins = 0;
	int complete = 0;
	int level = 1; //current level
// </editor-fold>
        
  
// <editor-fold defaultstate="collapsed" desc="maps">
	int[][]map = {//current map array
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,1,1,1,1,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,1,1,1,1,0,0,0,1,1,1,0,0,0,0,0,0,0},  
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{1,1,1,1,1,1,1,0,1,1,1,1,1,0,0,1,1,1,2,1,1,1,1},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};
	int[][]map1 = {//map1 array
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,1,1,1,1,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,1,1,1,1,0,0,0,1,1,1,0,0,0,0,0,0,0},  
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{1,1,1,1,1,1,1,0,1,1,1,1,1,0,0,1,1,1,2,1,1,1,1},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};
	int[][]map2 = {//map2 array
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,1,1,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,0,0,0,0},  
			{0,0,0,0,0,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,1,0,0,0,0,0,0,0,0,0,1,1,0,1,1,1,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{1,1,1,1,1,0,0,0,1,0,0,0,0,1,1,1,1,1,2,1,1,1,1},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};
	int[][]map3 = {//map3 array
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,1,1,1,0,1,0,1,0,1,0,1,0,1,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,1,0,0,0,1,0,1,0,1,0,0,0,0,0,0,0,0},  
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,2,1,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};    
// </editor-fold>
	
	
//~~~~~~~~~~~~~~~~~~~~~~~~~ Start Timer / Load Images / Constructor
	public DrawEnv()
	{
		t.start();
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		
		try   // load the images 
		{
                    // <editor-fold defaultstate="collapsed" desc="loading images">
			imgsky=ImageIO.read(new File("sky.png"));//load sky image
			imggrass=ImageIO.read(new File("grassblock.png"));//load grass image
			imgfireballright=ImageIO.read(new File("fireballright.png"));//load fireball image (right)
			imgfireballleft=ImageIO.read(new File("fireballleft.png")); //load fireball image (left)
			imgtarget=ImageIO.read(new File("target.png")); //load target image
			imgtitle=ImageIO.read(new File("title.png")); //load title image
			imgnext=ImageIO.read(new File("next.png")); //load advance image
			imginst=ImageIO.read(new File("instructions.png")); //load instructions image
			imgui=ImageIO.read(new File("ui.png")); //load UI image
			imgheart=ImageIO.read(new File("heart1.png"));
			imgblink=ImageIO.read(new File("blink1.png"));
			imgblinkcd=ImageIO.read(new File("blinkcd.png"));
			imggameover=ImageIO.read(new File("gameover.png"));
			imgblueright=ImageIO.read(new File("wizardright.png"));
			imgblueleft=ImageIO.read(new File("wizardleft.png"));
			imgredright=ImageIO.read(new File("redright.png"));
			imgredleft=ImageIO.read(new File("redleft.png"));
			imggreenright=ImageIO.read(new File("greenright.png"));
			imggreenleft=ImageIO.read(new File("greenleft.png"));
			imgpurpleright=ImageIO.read(new File("purpleright.png"));
			imgpurpleleft=ImageIO.read(new File("purpleleft.png"));
			imgcheater=ImageIO.read(new File("cheater.png"));
			imgcomplete=ImageIO.read(new File("complete.png"));
                        // </editor-fold>
		}
		catch(IOException e)  		//catching if the image was not there
		{
			System.out.println("Could not find image(s)");//error message for missing image
		}	
	}

//~~~~~~~~~~~~~~~~~~~~~~~~~	Paint Component
	public void paintComponent(Graphics g)
	//draws all fore and background images
    {
		super.paintComponent(g);			
		if(gameover == 0)//if the game is not over
		{
			if(start == 0)//if the player has not pressed Enter to leave title screen
			{
				g.drawImage(imgtitle, 0,0,1300,820,this); //draw title screen
			}
			else if(start == 1)//start
			{
				if(instructions == 0)//instructions
				{	
					g.drawImage(imginst,0,0,1360,820,this);//draw instructions if
				}
				else if(instructions == 1)//if instructions are skipped
				{
					for(int i = 0; i < 9; i++)//y axis (9 rows)
					{
						for(int j = 0; j < 20; j++)//x axis (20 columns)
						{
							if(map[i][j] == 0)//if cell in map array is 0, load sky
							{	
								g.drawImage(imgsky,(j*68),(i*65),68,65,this);//draw sky image
							}//end sky if
							else if(map[i][j] == 1)//if cell in map array is 1, load grass
							{
								g.drawImage(imggrass,(j*68),(i*65),68,65,this);//draw grass image
							}//end grass if
							else if(map[i][j] == 2)//if cell in map array is 2 draw next block
							{
								g.drawImage(imgnext,(j*68),(i*65),68,65,this); //draw level+ portal block
							}
						}//end j for
					}//end i for
					
					//~~~~~~~~~~~~~~~~~~~~~~~~~ Targets
					if(level == 1)
					{
						if(target1 == 0)//4
							g.drawImage(imgtarget, target1x,target1y,68,65,this); //draw target 1
						if(target2 == 0)//1
							g.drawImage(imgtarget, target2x,target2y,68,65,this); //draw target 2
						if(target3 == 0)//3
							g.drawImage(imgtarget, target3x,target3y,68,65,this); //draw target 3
						if(target4 == 0)//2
							g.drawImage(imgtarget, target4x,target4y,68,65,this); //draw target 4
						if(target5 == 0)//5
							g.drawImage(imgtarget, target5x,target5y,68,65,this); //draw target 5
					}
					else if(level == 2)
					{
						if(target1 == 0)
							g.drawImage(imgtarget, target1x,target1y,68,65,this); //draw target 1
						if(target2 == 0)
							g.drawImage(imgtarget, target2x,target2y,68,65,this); //draw target 2
						if(target3 == 0)
							g.drawImage(imgtarget, target3x,target3y,68,65,this); //draw target 3
						if(target4 == 0)
							g.drawImage(imgtarget, target4x,target4y,68,65,this); //draw target 4
						if(target5 == 0)
							g.drawImage(imgtarget, target5x,target5y,68,65,this); //draw target 5
					}
					else if(level == 3)
					{
						if(target1 == 0)
							g.drawImage(imgtarget, target1x,target1y,68,65,this); //draw target 1
						if(target2 == 0)
							g.drawImage(imgtarget, target2x,target2y,68,65,this); //draw target 2
						if(target3 == 0)
							g.drawImage(imgtarget, target3x,target3y,68,65,this); //draw target 3
						if(target4 == 0)
							g.drawImage(imgtarget, target4x,target4y,68,65,this); //draw target 4
						if(target5 == 0)
							g.drawImage(imgtarget, target5x,target5y,68,65,this); //draw target 5
						if(target6 == 0)
							g.drawImage(imgtarget, target6x,target6y,68,65,this); //draw target 6
						if(target7 == 0)
							g.drawImage(imgtarget, target7x,target7y,68,65,this); //draw target 7
						if(target8 == 0)
							g.drawImage(imgtarget, target8x,target8y,68,65,this); //draw target 8
							
					}
					
					//different character sprites for different colors
					if(direction == "right" && color == 'b') //check direction
						g.drawImage(imgblueright, x,y,68,65,this);//draw player sprite (right)
					if(direction == "left" && color == 'b') //check direction
						g.drawImage(imgblueleft, x,y,68,65,this);//draw player sprite (left)
					if(direction == "right" && color == 'r') //check direction
						g.drawImage(imgredright, x,y,68,65,this);//draw player sprite (right)
					if(direction == "left" && color == 'r') //check direction
						g.drawImage(imgredleft, x,y,68,65,this);//draw player sprite (left)
					if(direction == "right" && color == 'g') //check direction
						g.drawImage(imggreenright, x,y,68,65,this);//draw player sprite (right)
					if(direction == "left" && color == 'g') //check direction
						g.drawImage(imggreenleft, x,y,68,65,this);//draw player sprite (left)
					if(direction == "right" && color == 'p') //check direction
						g.drawImage(imgpurpleright, x,y,68,65,this);//draw player sprite (right)
					if(direction == "left" && color == 'p') //check direction
						g.drawImage(imgpurpleleft, x,y,68,65,this);//draw player sprite (left)
					
					//~~~~~~~~~~~~~~~~~~~~~~~~~ UI Building
					g.drawImage(imgui,0,585,1360,200,this); //draw UI panel
					Font myFont = new Font("Arial Bold", Font.PLAIN,20);  //Set the font to a bigger size
					g.setFont(myFont);//set the font to the new font
					g.drawString("Lives",90,630); //write lives
					if(lives == 1 || lives == 2 || lives == 3) //show first heart if lives is 1 2 or 3
						g.drawImage(imgheart,20,640,65,65,this); //draw heart 1
					if(lives == 2 || lives == 3) //show heart 2 if lives is 2 or 3
						g.drawImage(imgheart,85,640,65,65,this); //draw heart 2
					if(lives == 3) //show heart 3 if lives is 3
						g.drawImage(imgheart,150,640,65,65,this); //draw heart 3
	
					g.setColor(Color.BLUE);
					g.drawString("Score: "+points,230,630);
					g.setColor(Color.YELLOW);
					g.drawString("Level "+level,370,630);
					if(blink > 0) //if blink is above 0, it is on cooldown
					{
						g.setColor(Color.RED);
						g.drawString("Blink: UNAVAILABLE",500,630); //print blink availability
						g.drawImage(imgblinkcd,550,640,65,68,this);
					}
					else if(blink <= 0) //if blink is under 0, it is off cooldown
					{
						g.setColor(Color.GREEN);
						g.drawString("Blink: AVAILABLE",500,630); //print blink availability
						g.drawImage(imgblink,550,640,65,68,this); //draw blink icon
					}
					g.setColor(Color.WHITE);
					Font myFont2 = new Font("Arial Bold", Font.PLAIN,60);  //Set the font to a bigger size
					g.setFont(myFont2);//set the font to the new font
					g.drawString(""+wins,1200,700);
					g.setFont(myFont);//set the font to the new font
					
					if(fireball == 1)
					{
						firey = y + 20;  //initialize y value of fireball
						if(firedirection == "right") //check direction of character (right)
							g.drawImage(imgfireballright, firex,firey,49,34,this);//draw fireball sprite (right)
						else if(firedirection == "left") //check direction of character (left)
							g.drawImage(imgfireballleft, firex,firey,49,34,this);//draw fireball sprite (left)
					}
					if(lives <= 0) //if lives is 0 or below
					{
						gameover = 1;
					}
					if(quit == 1) //if q is pressed once
					{
						g.setColor(Color.BLACK);
						g.drawString("Are you sure you want to quit? Q for yes / N for no", 350,750); //quit confirm message			
					}
					if(level == 1 && points >= 25) //if level 1 is complete
					{
						g.setColor(Color.BLACK);
						g.drawString("Step onto the portal to move on!",730,630); //level complete message 1
					}
					else if(level == 2 && points >= 50) //if level 2 is complete
					{
						g.setColor(Color.BLACK);
						g.drawString("Head to the portal!",730,630); //level complete message 2
					}
					else if(level == 3 && points >= 90) //if level 3 is complete
					{
						g.setColor(Color.BLACK);
						g.drawString("The portal is open!",730,630); //level complete message 3
					}
				}//end instructions
			}//end start
		}//end game over
		else if(gameover == 1)//if the game is over
		{
			if(points > 90)
				g.drawImage(imgcheater,0,0,1300,820,this); //draw game over screen
			else if(complete == 1)
				g.drawImage(imgcomplete,0,0,1300,820,this); //draw game over screen
			else
				g.drawImage(imggameover,0,0,1300,820,this); //draw game over screen

		}
    }//end paint component	

//~~~~~~~~~~~~~~~~~~~~~~~~~ Action Performed
	public void actionPerformed(ActionEvent e)
	//executes contents upon action
	{
		blink--;
		hitTest();
		if(jump == 0) //checks to see if possible jump variable is 0 (jumpable)
			gravity(); //implement gravity
		if(jump == 1) //if jump variable equals 1, implement jumping method
			jumping(); //jumping method
		if(fireball == 1) //if space has been pushed
			fireball(); //activate fireball method
		if(level == 1 && points >= 25 && map[playery + 1][playerx] == 2) //if the player is on level 1, with 25 pts and is standing on portal block
		{
			level++; //add 1 to level
			targetSetup(); //set up new targets
			x = 0; //reset x
			y = 455; //reset y
			map = map2; //load new level / map
		}
		else if(level == 2 && points >= 50 && map[playery + 1][playerx] == 2) //if the player is on level 2, with 50 pts and is standing on portal block
		{
			level++; //add 1 to level
			targetSetup(); //set up new targets
			x = 0; //reset x
			y = 455; //reset y
			map = map3; //load new level / map
		}
		else if(level == 3 && points >= 90 && map[playery + 1][playerx] == 2) //if the player is on level 3, with 90 pts and is standing on portal block
		{
			x = 0;
			wins++;
			gameover = 1;
			complete = 1;
		}
		// refreshes the screen every time an action is performed
		repaint(); //performs any code in paint
	}
	
//~~~~~~~~~~~~~~~~~~~~~~~~~ Methods
	public void hitTest()
	{
		int d1a = ((target1x - firex)*(target1x - firex)) + ((target1y - firey)*(target1y - firey)); //distance test
		double d1b = (double)Math.sqrt(d1a); //find square root
		if(d1b < 45 && target1 == 0) //if distance if less than 45 and target is alive
		{
			points = points + 5; //add 5 points
			fireball = 0; //deactivate fireball
			firecount = 0; //reset firecount
			firex = x + 40; //reset fireball's x
			target1 = 1; //kill target
		}
		int d2a = ((target2x - firex)*(target2x - firex)) + ((target2y - firey)*(target2y - firey)); //distance test
		double d2b = (double)Math.sqrt(d2a); //find square root
		if(d2b < 45 && target2 == 0) //if distance if less than 45 and target is alive
		{
			points = points + 5; //add 5 points
			fireball = 0; //deactivate fireball
			firecount = 0; //reset firecount
			firex = x + 40; //reset fireball's x
			target2 = 1; //kill target
		}
		int d3a = ((target3x - firex)*(target3x - firex)) + ((target3y - firey)*(target3y - firey)); //distance test
		double d3b = (double)Math.sqrt(d3a); //find square root
		if(d3b < 45 && target3 == 0) //if distance if less than 45 and target is alive
		{
			points = points + 5; //add 5 points
			fireball = 0; //deactivate fireball
			firecount = 0; //reset firecount
			firex = x + 40; //reset fireball's x
			target3 = 1; //kill target
		}
		int d4a = ((target4x - firex)*(target4x - firex)) + ((target4y - firey)*(target4y - firey)); //distance test
		double d4b = (double)Math.sqrt(d4a); //find square root
		if(d4b < 45 && target4 == 0) //if distance if less than 45 and target is alive
		{
			points = points + 5; //add 5 points
			fireball = 0; //deactivate fireball
			firecount = 0; //reset firecount
			firex = x + 40; //reset fireball's x
			target4 = 1; //kill target
		}
		int d5a = ((target5x - firex)*(target5x - firex)) + ((target5y - firey)*(target5y - firey)); //distance test
		double d5b = (double)Math.sqrt(d5a); //find square root
		if(d5b < 45 && target5 == 0) //if distance if less than 45 and target is alive
		{
			points = points + 5; //add 5 points
			fireball = 0; //deactivate fireball
			firecount = 0; //reset firecount
			firex = x + 40; //reset fireball's x
			target5 = 1; //kill target
		}
		int d6a = ((target6x - firex)*(target6x - firex)) + ((target6y - firey)*(target6y - firey)); //distance test
		double d6b = (double)Math.sqrt(d6a); //find square root
		if(d6b < 45 && target6 == 0) //if distance if less than 45 and target is alive
		{
			points = points + 5; //add 5 points
			fireball = 0; //deactivate fireball
			firecount = 0; //reset firecount
			firex = x + 40; //reset fireball's x
			target6 = 1; //kill target
		}
		int d7a = ((target7x - firex)*(target7x - firex)) + ((target7y - firey)*(target7y - firey)); //distance test
		double d7b = (double)Math.sqrt(d7a); //find square root
		if(d7b < 45 && target7 == 0) //if distance if less than 45 and target is alive
		{
			points = points + 5; //add 5 points
			fireball = 0; //deactivate fireball
			firecount = 0; //reset firecount
			firex = x + 40; //reset fireball's x
			target7 = 1; //kill target

		}
		int d8a = ((target8x - firex)*(target8x - firex)) + ((target8y - firey)*(target8y - firey)); //distance test
		double d8b = (double)Math.sqrt(d8a); //find square root
		if(d8b < 45 && target8 == 0) //if distance if less than 45 and target is alive
		{
			points = points + 5; //add 5 points
			fireball = 0; //deactivate fireball
			firecount = 0; //reset firecount
			firex = x + 40; //reset fireball's x
			target8 = 1; //kill target
		}
	}
	
	public void gravity()
	//gravity method
	{
		playerx = ((x+34) / 68); //determine player's x location in map array
		playery = y / 65; //determine player's y location in map array
		halfblocky = ((y+32) / 65);
		if(map[playery + 1][playerx] == 0 || map[halfblocky][playerx] == 1) //check to see if block under player is sky (0) or if you are half way through a block
		{
			y = y + 12; //if so, fall
			if(y > 600)//if player falls outside map..
			{
				if(lives > 0)
				{
					y = 455; //reset player y
					lives--; //subtract 1 from lives
					x = 0; //reset to original position
				}
			}
		}//end solid ground check if
	}//end gravity

	public void jumping()
	//jumping method
	{
		playerx = x / 68; //determine player's x location in map array
		playery = y / 65; //determine player's y location in map array
		y = y - 12; //jump
		FinalProject.jumpcount++; //add one to jump counter
		if(FinalProject.jumpcount == 16) //once jump counter reaches max
		{
			jump = 0; //reset jump to 0
			FinalProject.jumpcount = 0; //reset jump count to 0
		}//end jump count check
	}//end jumping

	public void fireball()
	//fireball method
	{
		if(firedirection == "right") //check direction of character (right)
			firex = firex + 20; //make fireball travel right
		else if(firedirection == "left") //check direction of character (left)
			firex = firex - 20; //make fireball travel left
		firecount++; //add 1 to fireball counter
		if(firecount > 15) //once fireball counter reaches 20...
		{
			firex = x; //reset fireball's x position
			fireball = 0; //reset fireball to 0
			firecount = 0; //reset fireball counter to 0
		}
	}//end of fireball
	
	public void targetSetup()
	{
		if(gameover == 1)
		{
			//sets all targets in level to 0 (alive) 
			target1 = 0; 
			target2 = 0; 
			target3 = 0; 
			target4 = 0; 
			target5 = 0; 
			target6 = 0;
			target7 = 0;
			target8 = 0;
			//sets all x and y values
			target1x = 815; 
			target2x = 270; 
			target3x = 550; 
			target4x = 420;
			target5x = 950; 
			target1y = 325; 
			target2y = 65;
			target3y = 196; 
			target4y = 196; 
			target5y = 196; 
		}//end gameover check
		else if(level == 2 && points >= 25)
		{
			//sets all targets in level to 0 (alive)
			target1 = 0;
			target2 = 0;
			target3 = 0;
			target4 = 0;
			target5 = 0;
			target6 = 0;
			target7 = 0;
			target8 = 0;
			//sets all x and y values
			target1x = 880;
			target1y = 325;
			target2x = 130;
			target2y = 65;
			target3x = 620;
			target3y = 196;
			target4x = 410;
			target4y = 130;
			target5x = 955;
			target5y = 130;
		}//end level 1 - 2 check
		else if(level == 3 && points >= 50)
		{
			//sets all targets in level to 0 (alive)
			target1 = 0;
			target2 = 0;
			target3 = 0;
			target4 = 0;
			target5 = 0;
			target6 = 0;
			target7 = 0;
			target8 = 0;
			//sets all x and y values
			target1x = 820;
			target1y = 196;
			target2x = 1015;
			target2y = 65;
			target3x = 685;
			target3y = 196;
			target4x = 1165;
			target4y = 65;
			target5x = 955;
			target5y = 196;
			target6x = 885;
			target6y = 65;
			target7x = 755;
			target7y = 65;
			target8x = 625;
			target8y = 65;
		}//end level 2 - 3 check
	}
	
//~~~~~~~~~~~~~~~~~~~~~~~~~ Controls	
	@Override
	 public void keyPressed(KeyEvent e) 
	{
		int code = e.getKeyCode();
		switch(code)
		{
                
                    case KeyEvent.VK_D:		
                    case KeyEvent.VK_RIGHT:  //change x location right
			if(x+17 < 1215) //if player is not at the edge of the map...
			{
				if(map[playery + 1][playerx] == 0) //if player is jumping...
				{
					direction = "right"; //set direction to right
					x=x+34;	 //move 34 pixels
				}
				else if(map[playery + 1][playerx] == 1) //if the player is walking
				{
					direction = "right"; //set direction to right
					x=x+17;	 //move 17 pixels
				}
			}
			break;
                  
                case KeyEvent.VK_A:
		case KeyEvent.VK_LEFT:  //change x location left
			if(x-17 >= 0) //if player is not at the edge of the map...
			{
				if(map[playery + 1][playerx] == 0) //if player is jumping...
				{
					direction = "left"; //set direction to left
					x=x-34; //move 34 pixels
				}
				else if(map[playery + 1][playerx] == 1) //if the player is walking
				{
					direction = "left"; //set direction to left
					x=x-17; //move 17 pixels
				}
			}
			break;
                        
                case KeyEvent.VK_W:       
		case KeyEvent.VK_UP:  //jump
			if(jump == 0 && map[playery + 1][playerx] == 1) //can only jump if block beneath is solid ground
				jump = 1; //activate jumping
			break;
			
		case KeyEvent.VK_SPACE: //fireball
			if(fireball == 0) //if a fireball is not being shot...
			{	
				firedirection = direction;
				firex = x; //set fireball's x position
				fireball = 1; //activate fireball function
			}
			break;
		case KeyEvent.VK_ENTER: //exit title screen
			if(start == 0)
				start = 1; //close title
			else if(start == 1)
				instructions = 1; //close instructions
			if(gameover == 1) //if the game is over when enter is pressed
			{
				x = 0;
				y = 455;
				lives = 3; //reset lives 
				level = 1; //reset level
				points = 0; //reset points
				blink = 0; //reset blink cd
				map = map1; //reset map to level 1
				targetSetup(); //reset level 1 targets
				complete = 0;
				gameover = 0; //turn game over off
			}
			break;
		case KeyEvent.VK_Q: //attempt to quit
			if(quit == 0) //if quit has not already been attempted..
				quit = 1; //attempt to quit
			else if(quit == 1) //if the are you sure message is already displayed
				System.exit(0); //close window
			break;
		case KeyEvent.VK_N: //N is to cancel the quit
			quit = 0; //close quit message
			break;
		case KeyEvent.VK_B: //B to blink
			if(direction == "right" && blink <= 0) //check direction and cooldown
			{
				blink = 300; //reset blink cooldown
				x += 130; //teleport right
			}
			else if(direction == "left" && blink <= 0) //check direction and cooldown
			{
				blink = 500; //reset blink cooldown
				x -= 130; //teleport left
			}
			break;
		case KeyEvent.VK_C:
			if(color == 'b')
				color = 'r';
			else if(color =='r')
				color = 'g';
			else if(color == 'g')
				color = 'p';
			else if(color == 'p')
				color = 'b';
			break;
		}//end switch
	}
	@Override
	 public void keyReleased(KeyEvent arg0) {}
	@Override
	 public void keyTyped(KeyEvent arg0) {}
		
}//end of class