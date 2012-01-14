/**
   @Author: Alvin Tran
      Date: May 08, 2008
    Period: 5
       Lab: Final Project
      Desc:	Program that runs a game called the "Paper Airplane Game" on the
      		java applet. It makes use of various classes, most of which extend
      		the Sprite class created in this project, to help make various
      		objects in the game move. In the game, the player must control the
      		PaperAirplane through a set of obstacles (platforms) without hitting
      		anything. The PaperAirplane is controlled by the left and right
      		arrow keys on the keyboard, which steer the PaperAirplane left or
      		right. The main aim of the game is to travel as far down as they
      		can and achieve the highest score they can in the least amount of
      		time.
  */

import java.awt.*;
import java.applet.*;
import java.net.*;

public class PaperAirplaneGame extends Applet implements Runnable
{ 
   /********************************ints***************************************/
   private int x_pos;	//The initial x-coordinate for the PaperAirplane
   private int x_speed;	//The initial horizontal speed of the PaperAirplane
   private int y_pos;	//The initial y-coordinate for the PaperAirplane
   private int y_speed;	//The initial vertial speed of the PaperAirplane
   
   private int xClouds1;	//The x-coordinate of the first cloud background
   private int xClouds2;	//The x-coordinate of the second cloud background
   
   private int yBkg1;	//the y-coordinate of the first gameplay background.
   private int yBkg2;	//the y-coordinate of the second gameplay background.
   
   private int numClicks;	//The number of times the user clicked on the mouse
   private int timePassed;	//The time that passed from a given point
   							//(each unit of this is approximately 1/5 of a
   							//second)

   private int prevScore;	//score during the last frame
   private int curScore;	//score during the current frame
   private int eventFrame; //int value that is initially set to zero and is
   						   //often changed to a different number (usually 1) in
   						   //this code to prevent certain events from occurring
   						   //multiple times in one instance. 
   
   private int level;	//Level in which the player is at
   
   private int musicInt;	//used to determine if there should be music or not
   /***************************************************************************/
   /*******************************Strings*************************************/
   //The following are the int values in String form
   private String strScore;
   private String strHighScore;
   private String strTime;
   private String strBestTime;
   private String strLevel;
   /***************************************************************************/
   /*******************************Images**************************************/
   //Images
   private Image dbImage;
   private Image titleScreen;
   private Graphics dbg;
   
   //background images
   private Image gameBkg1;
   private Image gameBkg2;
   private Image gameClouds1;
   private Image gameClouds2;
   
   //Plane Image (used by one PaperAirplane)
   private Image [] planeAngle;	//Array of all images of the PaperAirplane in
   								//different angles.
   
   //Wall Images
   private Image leftWall;	//Image for the left sideWall
   private Image rightWall;	//Image for the right sideWall
   /***************************************************************************/
   /*******************************Audio***************************************/
   //Sounds Effects
   private AudioClip levelSong;	//Sound that is made when the player enters a
   								//new level
   private AudioClip hitWall;	//Sound that is made when the player hits a wall
   
   //Songs
   private AudioClip opening;	//Song that plays when the player enters the
   								//title screen
   private AudioClip stageIntro;//Song that plays when the actual gameplay
   								//begins
   private AudioClip stageSong;	//Song that plays after the stageIntro and
   								//continues to play as the gameplay is still on.
   /***************************************************************************/
   /****************************Gameplay Classes*******************************/
   //Walls
   
   private Wall [] w;		//Is the array of walls in this game.
	
   private SideWall [] sW;	//Is the array of SideWalls in this game.
   
   //Scores
   private Score score;		//The score of the current game in play.
   private Score highScore;	//The best score achieved in the game by the user.
   
   //Timers
   private Timer time;		//The time passed during the current game in play.
   private Timer bestTime;	//The amount of time passed during the time the
   							//user achieved the highest score.
   
   //PaperAirplane (What the user controls)
   private PaperAirplane p;
   
   /***************************************************************************/
   /*******************************Fonts***************************************/
   //used for setting the different fonts used in the text of the game
   private Font fTitle;			//The font mainly used for the game's title
   private Font fPressAny;		//The font used for the "Click Your Mouse to
   								//Start" text.
   private Font fCreditNames;	//The font used in the credits screen.
   private Font fGameOver;		//The font used for the "Game Over" text.
   private Font fScore;			//The font used for the score text.
   /***************************************************************************/
   /****************************Coordinate Arrays******************************/
   //Arrays for vertices (Mainly used to set the coordinates of the
   //PaperAirplane, check if it breaches a wall, and draw a rough version of the
   //PaperAirplane.
   private int[] xPoints;	//The x-coordintes of the PaperAirplane's vertices
   private int[] yPoints;   //The y-coordinates of te PaperAirplane's vertices
   /***************************************************************************/
   
   
   /****************************Method init*************************************
    *	Method to initialize and instantiate several classes and variables in
    *	the driver class, such as the starting coordinates of the PaperAirplane,
    *	the PaperAirplane itself, the background, and the sound effects.
    ***************************************************************************/
   public void init()
   {
	   Toolkit.getDefaultToolkit();
   		
   		//The following sets the int values
   		x_pos = 125;
   		y_pos = 120;
   		
   		x_speed = 5;	//The x_ and y_speed values are only to be used when
		y_speed = 1; 	//the game is in gameplay mode (in other words, when
   						//numClicks = 1)----
   		level = 1;
   		
   		xClouds1 = 0;
		xClouds2 = -500;
		yBkg1 = 0;
		yBkg2 = 600;
   		
   		//The following sets the images
   		titleScreen = getImage (getCodeBase (), "images/PaperAirplane.GIF");
		
		gameBkg1 = getImage (getCodeBase (), "images/bricks.GIF");
		gameBkg2 = getImage (getCodeBase (), "images/bricks.GIF");
		gameClouds1 = getImage (getCodeBase (), "images/clouds.GIF");
		gameClouds2 = getImage (getCodeBase (), "images/clouds.GIF");
		
		planeAngle = new Image [11];	//This sets the planeAngle array to
										//hold 11 elements
		//The following below sets a GIF for each of the elements in the
		//planeAngle array.
		planeAngle [0] = getImage (getCodeBase (), "images/planeLeft5.GIF");
		planeAngle [1] = getImage (getCodeBase (), "images/planeLeft4.GIF");
		planeAngle [2] = getImage (getCodeBase (), "images/planeLeft3.GIF");
		planeAngle [3] = getImage (getCodeBase (), "images/planeLeft2.GIF");
		planeAngle [4] = getImage (getCodeBase (), "images/planeLeft1.GIF");
		planeAngle [5] = getImage (getCodeBase (), "images/planeDown.GIF");
		planeAngle [6] = getImage (getCodeBase (), "images/planeRight1.GIF");
		planeAngle [7] = getImage (getCodeBase (), "images/planeRight2.GIF");
		planeAngle [8] = getImage (getCodeBase (), "images/planeRight3.GIF");
		planeAngle [9] = getImage (getCodeBase (), "images/planeRight4.GIF");
		planeAngle [10] = getImage (getCodeBase (), "images/planeRight5.GIF");
		
		leftWall = getImage (getCodeBase (), "images/leftWall.GIF");
		rightWall = getImage (getCodeBase (), "images/rightWall.GIF");
		
		//The following sets the audio
		levelSong = getAudioClip (getCodeBase (), "audio/level.au");
		hitWall = getAudioClip (getCodeBase (), "audio/hitWall.au");
		opening = getAudioClip (getCodeBase (), "audio/exitbike.au");
		stageIntro = getAudioClip (getCodeBase (), "audio/stageIntro.au");
		stageSong = getAudioClip (getCodeBase (), "audio/stageSong.au");
		
		//The following sets up the GamePlay Classes
		w = new Wall [4];	//sets the Wall array w to have 4 elements
		
		w[0] = new Wall (50, 200, 200, 50);	 //Is a platform located on the left
											 //side of the screen.
		w[1] = new Wall (250, 450, 200, 50); //Is a platform located on the
											 //right side of the screen.
		w[2] = new Wall (50, 700, 200, 50);	 //Is a platform located on the left
											 //side of the screen.
		w[3] = new Wall (250, 950, 200, 50); //Is a platform located on the
											 //right side of the screen.
		
		sW = new SideWall [4];	//sets the SideWall array sW to have 4 elements
		
		sW[0] = new SideWall (0, 0, 50, 600);		//Is the left wall
		sW[1] = new SideWall (450, 0, 50, 600);	//Is the right wall
		sW[2] = new SideWall (0, 600, 50, 600);	//Is the left wall
		sW[3] = new SideWall (450, 600, 50, 600);	//Is the right wall
		
		score = new Score ();
		highScore = new Score ();
		
		time = new Timer ();
		bestTime = new Timer ();
		
   		p = new PaperAirplane (x_pos, y_pos);
		
		//The following sets up the fonts
		fTitle = new Font ("titleStyle", Font.BOLD, 25);
		fPressAny = new Font ("pressStartStyle", Font.PLAIN, 12);
		fGameOver = new Font ("gameOverStyle", Font.ITALIC, 50);
		fScore = new Font ("scoreStyle", Font.BOLD, 15);
		fCreditNames = new Font ("creditStyle", Font.BOLD, 20);
		
		//The following sets up the coordinate arrays
		xPoints = p.getXPoints ();
		yPoints = p.getYPoints ();
   }

   public void start ()
   { 
     // define a new thread 
     Thread th = new Thread (this);
     // start this thread
     th.start ();

   } 

   public void stop()
   {
	   opening.stop ();	//The opening song is stopped.
   }

   public void destroy() { }
	
	
   /********************************Method run**********************************
    *	Method that runs the java applet as it is open.
    ***************************************************************************/
   public void run ()
   { 
   // lower ThreadPriority 
      Thread.currentThread().setPriority(Thread.MIN_PRIORITY);

      while (true)
      { 
      	//Makes the opening song play if the program is at the title screen
      	//(signified by the numClicks value being 0) and if the song hasn't
      	//played before in the same instance of being at the title screen
      	//(signified by the eventFrame being 0).
      	if (numClicks == 0 && eventFrame == 0 && musicOn ())
      	{
      		opening.play ();	//Plays the opening song
      		eventFrame ++;	//Increases the event frame so the song does not
      						//continuously use the play () method every second.
      	}
      	//Following is mainly used to help make time-based events run the way
      	//They should. For instance, the "Click your mouse to start" text
		//on the Title Screen flashes according to the amount of time passed.
		//Another example is that the time recorded during the actual game
		//is determined by the timePassed value.
		timePassed ++;
		
		//The following is mainly used to compare with the score from the
		//previous frame. This, in turn, is used to ensure that the game does
		//not level up multiple times in one instant, as the score, which
		//determines when to level up, remains the same for a few frames.
		curScore = score.getScore ();
		
      	//The following is what happens when the game is at the Title Screen.
      	//It does nothing, so it is mainly here so that the other scenarios,
      	//such as the Game Over one, do not occur in place of the Title Screen
      	//events. This scenario is signified by numClicks being 0 and if the
      	//PaperAirplane hasnt' hit a wall.
        if (!isGameOver () && numClicks == 0)
        {
        }
        //The following is what happens when the actual gameplay (when the
        //paper airplane starts flying and the user must navigate it) take
        //place. The scenario is signified by numClicks being 1 and if the
        //PaperAirplane hasn't hit a wall.
        else if (!isGameOver () && numClicks == 1)
        {
        	opening.stop ();	//The opening song is stopped.
        	//Makes the intro song of the actual game play.
        	if (timePassed == 1 && eventFrame == 0 && musicOn ())
        	{
        		stageIntro.play ();
        		eventFrame ++;	//Used to prevent the stageIntro from
        						//repeatedly using the play () method.
        	}
        	//Makes the looping song (stageSong) play (using the loop method)
        	//after a certain amount of time, making it so that the intro song
        	//never plays again after the later parts of the song (stageSong)
        	//starts playing, like in most games.
        	if (timePassed == 174 && musicOn ())
        	{
        		stageSong.loop ();
        	}
        	
        	p.move ();	//PaperAirplane p's move () method is used (basically
        				//makes it fall).
        	
        	//The following helps to move the clouds from left to right
        	xClouds1 ++;
        	xClouds2 ++;
        	//The following sets the clouds to reappear from the left when they
        	//go too far to the right, creating the illusion of an endless sky.
        	if (xClouds1 > 500)
        		xClouds1 = xClouds2 - 500;
        	if (xClouds2 > 500)
        		xClouds2 = xClouds1 - 500;
        	
        	//The following deals with change in the wall falling speed and the
        	//bricks motion. Such things are done to create the illusion of the
        	//paper airplane continuously falling, while it is actually staying
        	//in the same y-coordinate for most the the game (when the
        	//y-coordinate of the paper airplane reaches or goes beyond 300).
        	if (p.getY_Pos () >= 300)
        	{
        		//The following sets the VSpeed of the regular Walls of the
        		//game to be the same as the PaperAirplane's
        		/*w1.setVSpeed (p.getVSpeed ());
        		w2.setVSpeed (p.getVSpeed ());
        		w3.setVSpeed (p.getVSpeed ());
        		w4.setVSpeed (p.getVSpeed ());*/
        		for (int i = 0; i < w.length; i ++)
	        		w[i].setVSpeed (p.getVSpeed ());
	        		
        		//The following sets the VSpeed of the SideWalls of the game
        		//to be the same as the PaperAirplane's
        		for (int i = 0; i < sW.length; i ++)
	        		sW[i].setVSpeed (p.getVSpeed ());

        		//The y-coordinates of the backgrounds move at half the speed of
        		//the PaperAirplane in the opposite direction, giving the
        		//illusion of depth in the scenery.
        		
        		yBkg1 -= p.getVSpeed () / 2;
        		yBkg2 -= p.getVSpeed () / 2;
        		
        		//The following causes the backgrounds to reappear from the
        		//bottom if they reach a certain distance (600 units above the
        		//top of the screen) to create the illusion of a continuous
        		//background.
        		if (yBkg1 + 600 < 0)
        			yBkg1 = yBkg2 + 600;
        		if (yBkg2 + 600 < 0)
        			yBkg2 = yBkg1 + 600;
        	}
			
			//The following levels up the game when the user reaches 50 points.
			//To prevent the game from levelling up multiple times in one
			//instance (as the points remain the same for a few seconds), the
			//if statement has it so that the game will only level up if the
			//points is divisible by 50 and if the points from the last frame
			//is not the same as the current points.
        	if (score.getScore () % 50 == 0 && numClicks > 0 && score.getScore () != 0 && curScore != prevScore)
        	{
        		levelUp ();	//uses the levelUp () method from the driver class.
        		levelSong.play ();	//The level up sound plays whenever the
        							//player goes up by a level.
        		System.out.println ("LEVEL UP!");
        		level++;	//The level value increases. This is mainly used
        					//for displaying the game's level on the applet.
        	}
        	//The following moves the Walls. Their speed is determined by the
        	//PaperAirplane's speed.
        	w[0].move (w[3].getBottomSide ());
     		for (int i = 1; i < w.length; i++)
 	       		w[i].move (w[i - 1].getBottomSide ());

        	
        	//The following moves the SideWalls. Their speed is determined by
        	//the PaperAirplane's speed.
        	
        	sW[0].move (sW[2].getHeight ());
        	sW[1].move (sW[3].getHeight ());
        	sW[2].move (sW[0].getHeight ());
        	sW[3].move (sW[1].getHeight ());
        	
        	//The following is used to compare with curScore
        	prevScore = score.getScore ();
        	
        	//The following updates the score
        	score.updateScore (p.getVSpeed ());
		}
		//The following runs the credits screen. Mainly, it deals with the
		//moving clouds in the background. This scenario is signified by
		//numClicks being -2 and if the PaperAirplane hasn't hit a wall.
		else if (!isGameOver () && numClicks == -2)
		{
			//The following is the same as the moving clouds in the actual
			//gameplay.
			xClouds1 ++;
        	xClouds2 ++;
        	if (xClouds1 > 500)
        		xClouds1 = xClouds2 - 500;
        	if (xClouds2 > 500)
        		xClouds2 = xClouds1 - 500;
		}
		//The following happens when the PaperAirplane hits a wall, which only
		//happens during the actual gameplay.
		else
		{
			//Stops all of the songs playing
			stageIntro.stop ();
			stageSong.stop ();
			
			//Creates the sound effect of the paperAirplane crashing, regardless
			//of if musicOn is true or not
			if (eventFrame == 1 || eventFrame == 0 && !musicOn ())
				hitWall.play ();
			eventFrame = 2;	//Sets eventFrame to 2 so that the hitWall song
							//won't use the play () method repeatedly.
			
			numClicks = -1;	//numClicks is set to -1 so that none of the above
							//scenarios take place alongside this one.
			
			//The following changes the high score if the current score (before
			//losing) is higher than or equal to the high score. It will also
			//change the best time in the process so that those who have
			//achieved the same score as the high score can claim the top score
			//by scoring it in less time.
			if (score.getScore () >= highScore.getScore ())
			{
				if (score.getScore () == highScore.getScore () && time.getTotalSeconds () > bestTime.getTotalSeconds ())
				{
					//nothing happens if the score is the same as the highscore
					//and the time in the current play is higher than the best
					//time.
				}
				//If the score is the same as the highScore and the time of the
				//current play is less than the bestTime, the value of bestTime
				//changes to the current gameplay's time.
				else
					bestTime.setTime (time.getTotalSeconds (), time.getDisplayedSeconds (), time.getMinutes ());
				highScore.setScore (score.getScore ());	//the high score is
														//updated.
			}
			timePassed = 0;	//The timePassed variable is reset.
		}
        
        //The following updates the time
        if (numClicks == 1 && timePassed % 40 == 0)
		{
			time.update ();	//The update () method from the Timer class is used
							//to update the time.
		}
         
        repaint();	//repaints the visual image on the applet.
		
        try
        { 
           // Stop thread for 20 milliseconds
           Thread.sleep (20);

        }
        catch (InterruptedException ex)
        { 
        // do nothing

        }

        // set ThreadPriority to maximum value
        Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
      } 
   } 
	
   /*****************************Method paint***********************************
    *	Method to draw the visuals of the program on the applet.
    ***************************************************************************/
   public void paint (Graphics g)
   { 
      //The following is the Title Screen
      if (numClicks == 0)
      {
      	  g.drawImage (titleScreen, 65, 130, this);	//draws the Paper Airplane
      	  											//GIF on the title Screen.
      	  //The following makes the text "PAPER AIRPLANE GAME" with red text
      	  //and with the fTitle font.
      	  g.setColor (Color.red);
      	  g.setFont (fTitle);
      	  g.drawString ("PAPER AIRPLANE GAME", 100, 270);
      	  
      	  //The following creates the illusion of the words "CLICK YOUR MOUSE
      	  //TO START" flashing.
      	  if (timePassed > 50 && timePassed <= 80)
      	  	g.setColor (Color.white);	//sets the text to white (makes it
      	  								//visible in the black photo.
      	  else if (timePassed <= 50)
      	  	g.setColor (Color.black);	//sets the text to black (makes it
      	  								//invisible in the black photo.
      	  else
      	  	timePassed = 20;	//resets the time to 20 if the timePassed value
      	  						//exceeds 80 (mainly done to waste space, since
      	  						//the timePassed value would eventually reach a
      	  						//really high value.
      	  g.setFont (fPressAny);
      	  g.drawString ("CLICK YOUR MOUSE TO START", 160, 430);
      	  
      	  //The following creates the Credits Button at the bottom of the
      	  //screen.
      	  g.setColor (Color.blue);	//Sets the color to blue.
      	  g.drawRoundRect (200, 540, 100, 50, 10, 10);	//creates the box/.
      	  g.drawString ("CREDITS", 225, 570);	//creates the "CREDITS" text.
      }
      //The following is the Credits Screen
      else if (numClicks == -2)
      {
      	//Draws the sky background
      	g.drawImage (gameClouds1, xClouds1, 0, this);
      	g.drawImage (gameClouds2, xClouds2, 0, this);
      	
      	//List the credit titles
      	g.setColor (Color.red);
      	g.setFont (fTitle);
      	g.drawString ("PRODUCER", 180, 100);
      	g.drawString ("ARTIST", 203, 180);
      	g.drawString ("PROGRAMMING TEAM", 120, 260);
      	g.drawString ("ORIGINAL CONCEPT BY", 105, 340);
      	
      	//List the people under the credit titles
      	g.setColor (Color.black);
      	g.setFont (fCreditNames);
      	g.drawString ("Alvin Tran", 200, 130);
      	g.drawString ("Alvin Tran", 200, 210);
      	g.drawString ("Alvin Tran", 200, 290);
      	g.drawString ("NINTENDO", 200, 370);
      }
      //The following is the actual game
      else
      {
      	  //Draws the sky behind the brick background
      	  g.drawImage (gameClouds1, xClouds1, 0, this);
      	  g.drawImage (gameClouds2, xClouds2, 0, this);
      	  //Draws the brick background
	   	  g.drawImage (gameBkg1, 0, yBkg1, this);
	      g.drawImage (gameBkg2, 0, yBkg2, this);
	      
	      //Draws PaperAirplane
	      
	      //The following drew the beta image of the PaperAirplane created with
	      //the fillPolygon method. It is only here to show how it was once
	      //rendered. It used the xPoints [] and yPoints [] from the
	      //PaperAirplane class to draw the PaperAirplane
	      
	      //g.setColor (Color.red);
	      //g.fillPolygon (p.getXPoints (), p.getYPoints (), 3);
	      
	      
	      //The following is how the final PaperAirplane image is created in
	      //the game. It draws different images of the plane depending on its
	      //hSpeed. The range of the speed is -5 to 5, and from 1 to 5, the
	      //PaperAirplane faces right, while when it is from -1 to -5, it faces
	      //left (angle of the plane varies according to hSpeed). When the speed
	      //is at 0, the PaperAirplane faces staright down.
	      
		  if (p.getHSpeed () >= -5 && p.getHSpeed () < 0)
		 	g.drawImage (planeAngle [p.getHSpeed () + 5], p.getXD3 (), p.getYD2 (), this);
		  else if (p.getHSpeed () == 0)
		  	g.drawImage (planeAngle [p.getHSpeed () + 5], p.getXD1 (), p.getYD1 (), this);
		  else
		  	g.drawImage (planeAngle [p.getHSpeed () + 5], p.getXD1 (), p.getYD2 (), this);
		  	
	   	  //Draws Wall obstacles using the fillRoundRect () method.
	   	  g.setColor (Color.gray);	//Sets the walls to be gray.
	   	  for (int i = 0; i < w.length; i++)
		   	  g.fillRoundRect (w[i].getX_Pos (), w[i].getY_Pos (), w[i].getWidth (), w[i].getHeight (), w[i].getArcWidth (), w[i].getArcHeight ());
		  
		  //Draws SideWalls
		  
		  //The following was the method of drawing the walls earlier in
		  //development. The method to do this was similar to how the Walls
		  //were drawn.
		  
		  //g.fillRoundRect (sW[0].getX_Pos (), sW[0].getY_Pos (), sW[0].getWidth (), sW[0].getHeight (), sW[0].getArcWidth (), sW[0].getArcHeight ());
		  //g.fillRoundRect (sW[1].getX_Pos (), sW[1].getY_Pos (), sW[1].getWidth (), sW[1].getHeight (), sW[1].getArcWidth (), sW[1].getArcHeight ());
		  //g.fillRoundRect (sW[2].getX_Pos (), sW[2].getY_Pos (), sW[2].getWidth (), sW[2].getHeight (), sW[2].getArcWidth (), sW[2].getArcHeight ());
		  //g.fillRoundRect (sW[3].getX_Pos (), sW[3].getY_Pos (), sW[3].getWidth (), sW[3].getHeight (), sW[3].getArcWidth (), sW[3].getArcHeight ());
		  
		  
		  //The following draws the SideWalls by using GIFs of drawn walls.
		  //Left walls and right walls have different GIFs.	
		  g.drawImage (leftWall, sW[0].getX_Pos (), sW[0].getY_Pos(), this);
		  g.drawImage (rightWall, sW[1].getX_Pos (), sW[1].getY_Pos(), this);
		  g.drawImage (leftWall, sW[2].getX_Pos (), sW[2].getY_Pos(), this);
		  g.drawImage (rightWall, sW[3].getX_Pos (), sW[3].getY_Pos(), this);
	  }
	  //The following is the Game Over screen
      if (numClicks == -1)
      {
      	g.setColor (Color.red);	//Sets color to red
      	g.setFont (fGameOver);	//Sets font to fGameOver
      	g.drawString ("GAME", 170, 240);//draws the word "GAME"
      	g.drawString ("OVER", 170, 300);//draws the word "OVER below "GAME"
      }
      
      //The following make the current score display and the current time
      //display, which is always present.
      strScore = "SCORE: " + String.valueOf(score.getScore ());
      strTime = "TIME: "+ String.valueOf (time.getMinutes()) + ":" + String.valueOf (time.getDisplayedSeconds () / 10) + String.valueOf (time.getDisplayedSeconds () % 10);
      
      //if (numClicks == 0)
      	g.setColor (Color.green);
      //else
      //	g.setColor (Color.white);
      g.setFont (fScore);
      g.drawString (strScore, 116, 50);
      g.drawString (strTime, 310, 50);
      
      //The following makes the high score display and the best time display,
      //which is always present.
      strHighScore = "HIGH SCORE: " + String.valueOf (highScore.getScore ());
      strBestTime = "BEST TIME: " + String.valueOf (bestTime.getMinutes ()) + ":" + String.valueOf (bestTime.getDisplayedSeconds () / 10) + String.valueOf (bestTime.getDisplayedSeconds () % 10);
      
      g.setColor (Color.green);
      g.drawString (strHighScore, 74, 65);
      g.drawString (strBestTime, 310, 65);
      
      //The following makes the level display for the game.
      strLevel = "Level: " + String.valueOf (level);
      
      g.setColor (Color.red);
      g.drawString (strLevel, 220, 20);
      
      //The following makes the music on/off button for the game
      g.fillRoundRect (460, 560, 40, 40, 5, 5);
      //The following makes the text within the music on/off button in the game
      //(it's either "ON" or "OFF")
      g.setColor (Color.white);
      if (musicOn ())	//If musicOn () is true, the text will say "ON"
      	g.drawString ("ON", 465, 585);
      else				//Otherwise, it will say "OFF"
      	g.drawString ("OFF", 465, 585);
   } 

	/*******************************Method update*******************************
	 *	Method that implements double buffering so that the objects in this game
	 *	do not "flicker." This method was taken straight from
	 *	javaboutique.internet.com
	 **************************************************************************/
	public void update (Graphics g)
	{
		// initialize buffer
		if (dbImage == null)
		{
			dbImage = createImage (this.getSize().width, this.getSize().height);
			dbg = dbImage.getGraphics ();
		}
		
		// clear screen in background
		dbg.setColor (getBackground ());
		dbg.fillRect (0, 0, this.getSize().width, this.getSize().height);
		
		// draw elements in background
		dbg.setColor (getForeground());
		paint (dbg);
		
		// draw image on the screen
		g.drawImage (dbImage, 0, 0, this);
		
	}
	
	/*****************************Method mousedown******************************
	 *	Method that performs various actions in this game when the user clicks
	 *	on a button on the mouse. Mainly, it adds onto the value of numClicks,
	 *	which in turn changes the game's status to such states as gameplay or
	 *	title screen.
	 **************************************************************************/
	public boolean mouseDown (Event e, int x, int y) 
	{
		//The following deals with clicks to the music on/off button
		if (x > 460 && x < 500 && y > 560 && y < 600)
		{
			//the following will cause the musicOn () method to return false
			if (musicInt == 0)
			{
				musicInt ++;
				
				//All songs are stopped
				opening.stop ();
				stageIntro.stop ();
				stageSong.stop ();
			}
			//The following will cause the musicOn () method to return true
			else
			{
				musicInt --;
				//the opening song will play again if the game is at the title
				//screen
				if (numClicks == 0)
					opening.play ();
				//if the player is in the actual gameplay, the stageSong will
				//play again upon turning the music back on, given that
				//timepassed has exceeded 174 (so the song does not play more
				//than once, creating an awkward song).
				else if (timePassed > 174 && numClicks == 1 && eventFrame == 1)
					stageSong.loop ();
			}
		}
		//The following deals with clicks on the Credits or Game Over Screens
		else if (numClicks == -1 || numClicks == -2)
		{
			reset ();	//various parts of the game are reset, such as the
						//positioning of certain objects and the values of
						//variables.
		}
		//The following deals with clicks on the Title Screen
		else if (numClicks == 0)
		{
			//The following occurs if the user clicks on the Credits rectangle
			//area.
			if (x > 200 && x < 300 && y > 540 && y < 590)
				numClicks = -2;
			//The following occurs if the user clicks anywhere else on the
			//title screen
			else
			{
				//sets the horizontal and vertical speed of the PaperAirplane
				//to be the same as the x_speed and y_speed set in this class.
				p.setHSpeed (x_speed);
				p.setVSpeed (y_speed);
				
				//Resets timePassed and eventFrame so they can be used in
				//during the actual gameplay.
				timePassed = 0;
				eventFrame = 0;
				
				//numClicks increases so the game can transition into the game
				//play portion.
				numClicks ++;
			}
		}
		return true; //Is just here to make the method work.
	} 
	
	/*****************************Method keyDown********************************
	 *	Method that causes the PaperAirplane to turn (or change its angle) when
	 *	a key is pressed. Mainly, this method changes the horizontal speed of
	 *	the Paper Airplane, and the angle of the plane changes according to
	 *	that. Pressing left shifts the horizontal speed leftwards, while
	 *	pressing right shifts the horizontal speed rightward.
	 **************************************************************************/
	public boolean keyDown (Event e, int key) 
	{ 
		if (numClicks == 1)
		{
			// user presses left cursor key. It only does something if the
			//hSpeed of the PaperAirplane is greater than -5, preventing the
			//PaperAirplane from turning excessively (and going upwards).
			if (key == Event.LEFT && p.getHSpeed () > -5)
			{ 
				p.turnLeft ();	//Uses the method turnLeft () in the
								//PaperAirplane class, which decreases the value
								//of hSpeed by 1.
			}
			// user presses right cursor key. It only does something if the
			//hSpeed of the PaperAirplane is greater than 5, preventing the
			//PaperAirplane from turning excessively (and going upwards). 
			else if (key == Event.RIGHT && p.getHSpeed () < 5)
			{ 
				p.turnRight ();	//Uses the method turnRight () in the
								//PaperAirplane class, which increases the value
								//of hSpeed by 1.
			}
			//user presses any other key.
			else
			{ 
				//Pressing any other key causes the method to print out the
				//key value of your key (so that it may possibly be used in
				//future coding). 
				System.out.println ("Charakter: " + (char)key + " Integer Value: " + key);
		
			}
		}
		return true;	//Is just here to make the method work.
	}
	
	//following is used to test new way of making paper airplane turn
	public boolean keyUp (Event e, int key)
	{
		if (numClicks == 1)
		{
			if (key == Event.LEFT || key == Event.RIGHT)
				p.noPress ();
			return true;
		}
		else
		{
			return false;
		}
	} 
	
	
	/*============================Method isGameOver=============================
	 *	Method to indicate if the game is over according to if the paperAirplane
	 *	flew into a wall.
	 **************************************************************************/
	public boolean isGameOver ()
	{
		//Each Wall class uses teh isPenetrated () method to check if the
		//PaperAirplane's vertices have breached the sides of the walls.	
		for (int i = 0; i < w.length; i++)
		{
			if (w[i].isPenetrated (p.getXPoints (), p.getYPoints ()))
				return true;
		}
		
		//Each SideWall class uses the isPenetrated () method to check if the
		//PaperAirplane's vertices have breached the sides of the walls.
		for (int i = 0; i < sW.length; i++)
		{
			if (sW[i].isPenetrated (p.getXPoints (), p.getYPoints ()))
				return true;
		}
		return false;
	}
	
	/******************************Method levelUp*******************************
	 *	Method to change the format of the stage to make it more difficult for
	 *	the user to navigate through, thus "levelling" the game. For the first
	 *	five levelUps, the game decreases the distance between walls. For the
	 *	4 levelUps after that, the walls start to increase in terms of
	 *	horizontal length, forcing the user to turn at different times.
	 *	Afterwards, as the game is hard enough, the game stops levelling and
	 *	starts to become about endurance.
	 **************************************************************************/
	public boolean levelUp ()
	{
		//For the first five levelUps, the game uses the first levelUp method
		//from the wall class, which decreases the distance between the walls.
		if (level < 5)
		{
			for (int i = 0; i < w.length; i++)
				w[i].levelUp1 ();
		}
		//For the four levelUps after the first five, the game uses the second
		//levelUp method from the wall class, which increases the horizontal
		//length of the walls.
		else if (level >= 5 && level < 9)
		{
			for (int i = 0; i < w.length; i++)
			w[i].levelUp2 ();
		}
		return true;	//Is mainly here to make the method work.
	}
	
	/****************************Method musicOn*********************************
	 *	Method to help determine if there should be music in the game or not by
	 *	returning true for if there should be music and returning false if there
	 *	shouldn't. The musicInt variable is used to assist in making the return.
	 *
	 *	@return		true	The game will have music.
	 *	@return		false	The game won't have music.
	 **************************************************************************/
	public boolean musicOn ()
	{
		if (musicInt == 0)
			return true;
		else
			return false;
	}
	
	/*****************************Method reset**********************************
	 *	Method to reset the Sprites of the game to their original starting
	 *	positions. This is mainly used to make the game start over when the
	 *	player reaches a game over and wants to play again.
	 **************************************************************************/
	public void reset ()
	{
		//The PaperAirplane and the walls (both types) use their reconstruct ()
		//method to set themselves back to their starting points.
		p.reconstruct ();
		for (int i = 0; i < w.length; i++)
			w[i].reconstruct ();
		
		for (int i = 0; i < sW.length; i++)
			sW[i].reconstruct ();
			
		//the time is reset using the resetTime () method from the Timer class.
		time.resetTime ();
		
		//the score is reset using the reconstruct method from the Score class.
		score.reconstruct ();
		
		//The following variables are set back to their original values set in
		//the driver class.
		timePassed = 0;	
		numClicks = 0;
		eventFrame = 0;
		level = 1;
		xClouds1 = 0;
		xClouds2 = -500;
		yBkg1 = 0;
		yBkg2 = 600;		
	}
}

	