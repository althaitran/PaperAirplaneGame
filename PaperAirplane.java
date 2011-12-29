
public class PaperAirplane extends Sprite
{
	private int xDimension1;	//The x-coordinate of the first vertex
	private int xDimension2;	//The x-coordinate of the second vertex
	private int xDimension3;	//The x-coordinate of the third vertex
	
	private int yDimension1;	//The y-coordinate of the first vertex
	private int yDimension2;	//The y-coordinate of the second vertex
	private int yDimension3;	//The y-coordinate of the third vertex
	
	private int[] xPoints;	//An array of ints, mainly intended to hold the
							//xDimensions
	private int[] yPoints;	//An array of ints, mainly intended to hold the
							//yDimensions
							
	private int turnInt; //value to affect the turning of the paper airplane
	
	//The following are used for the reconstructing method (they are just the
	//values set for the above variables when this class is instantiated).
	private int rXD1;
	private int rXD2;
	private int rXD3;
	private int rYD1;
	private int rYD2;
	private int rYD3;
	
	/*=====================Constructor for PaperAirplane========================
	 *	Is the constructor for the PaperAirplane class that sets its getX_Pos ()
	 *	and getY_Pos () values, which are technically set in the Sprite (super)
	 *	class. This constructor mainly uses the x and y values to set the
	 *	dimensions for the PaperAirplane's vertices.
	 *
	 *	@param	x	Is the x-coordinate set by the user.
	 *	@param	y	Is the y-coordinate set by the user.
	 **************************************************************************/
	public PaperAirplane (int x, int y)
	{
		super (x, y);	//Superclass called to set x and y
		
		xPoints = new int [3];	//sets xPoints to have 3 elements
		yPoints = new int [3];	//sets yPoints to have 3 elements
		
		//Using the getX_Pos () set in the superclass, the following sets the
		//xDimensions.
		xDimension1 = getX_Pos () - 23;
		xDimension2 = getX_Pos () - 18;
		xDimension3 = getX_Pos () + 22;
		
		//Using the getY_Pos () set in the superclass, the following sets the
		//yDimensions.
		yDimension1 = getY_Pos() - 2;
		yDimension2 = getY_Pos() - 17;
		yDimension3 = getY_Pos() + 5;
		
		//The following sets the same dimensions from above, but these never
		//change and can be used for reconstruction
		rXD1 = getX_Pos () - 23;
		rXD2 = getX_Pos () - 18;
		rXD3 = getX_Pos () + 22;
		rYD1 = getY_Pos() - 2;
		rYD2 = getY_Pos() - 17;
		rYD3 = getY_Pos() + 5;
	}

/*------------------------------Getter Methods----------------------------------
 *	These are methods that set the values of variables in this class.
 *----------------------------------------------------------------------------*/

	/**************************Method getXPoints********************************
	 *	Method to return an array of the xDimensions declared in this class.
	 *
	 *	@return 	xPoints		an array of the xDimensions of this
	 *							PaperAirplane
	 **************************************************************************/
	public int[] getXPoints ()
	{
		int xPoints [] = {xDimension1, xDimension2, xDimension3};
		return xPoints;
	}
	
	/**************************Method getYPoints********************************
	 *	Method to return an array of the yDimensions declared in this class.
	 *
	 *	@return 	yPoints		an array of the yDimensions of this
	 *							PaperAirplane
	 **************************************************************************/
	public int[] getYPoints ()
	{
		int yPoints [] = {yDimension1, yDimension2, yDimension3};
		return yPoints;
	}
	
	/*******************************Method getXD1*******************************
	 *	Method to return the value of xDimension1
	 *
	 *	@return		xDimension1		The x-coordinate of the first vertex of the
	 *								PaperAirplane.
	 **************************************************************************/
	public int getXD1 ()
	{
		return xDimension1;
	}
	
	/*******************************Method getX21*******************************
	 *	Method to return the value of xDimension2
	 *
	 *	@return		xDimension2		The x-coordinate of the second vertex of the
	 *								PaperAirplane.
	 **************************************************************************/
	public int getXD2 ()
	{
		return xDimension2;
	}
	
	/*******************************Method getXD3*******************************
	 *	Method to return the value of xDimension3
	 *
	 *	@return		xDimension3		The x-coordinate of the third vertex of the
	 *								PaperAirplane.
	 **************************************************************************/
	public int getXD3 ()
	{
		return xDimension3;
	}
	
	/*******************************Method getYD1*******************************
	 *	Method to return the value of yDimension1
	 *
	 *	@return		yDimension1		The y-coordinate of the first vertex of the
	 *								PaperAirplane.
	 **************************************************************************/
	public int getYD1 ()
	{
		return yDimension1;
	}
	
	/*******************************Method getYD1*******************************
	 *	Method to return the value of yDimension1
	 *
	 *	@return		yDimension2		The y-coordinate of the second vertex of the
	 *								PaperAirplane.
	 **************************************************************************/
	public int getYD2 ()
	{
		return yDimension2;
	}
	
	/*******************************Method getYD3******************************
	 *	Method to return the value of yDimension3
	 *
	 *	@return		yDimension3		The y-coordinate of the third vertex of the
	 *								PaperAirplane.
	 **************************************************************************/
	public int getYD3 ()
	{
		return yDimension3;
	}
	
/*-----------------------------------Other Methods------------------------------
 *	All the other methods that don't fit in the previous categories.
 *----------------------------------------------------------------------------*/

	/**********************************Method move******************************
	 *	Method to change the x- and y-coordinates of the PaperAirplane using
	 *	the hSpeed and the vSpeed set in the superclass. Doing this makes the
	 *	PaperAirplane (in the applet) look like it is "moving,"
	 **************************************************************************/
	public void move ()
	{
		//Let's try this...
		changeFallSpeed (turnInt);
		
		
		setX_Pos (getX_Pos () + getHSpeed ());	//hSpeed is added onto x_pos
		//The xDimensions are updated by having hSpeed added onto them.
		xDimension1 += getHSpeed ();
		xDimension2 += getHSpeed ();
		xDimension3 += getHSpeed ();
		
		//The if statement below ensures that the PaperAirplane does not move
		//after reaching the 300 y point. The walls around the PaperAirplane,
		//however, start to move up, creating the illusion that the
		//PaperAirplane is still falling.
		if (getY_Pos () < 300)
		{
			setY_Pos (getY_Pos () + getVSpeed ());//vSpeed is added onto y_pos
			//The yDimensions are updated by having vSpeed added onto them.
			yDimension1 += getVSpeed ();
			yDimension2 += getVSpeed ();
			yDimension3 += getVSpeed ();
		}
	}
	//Let's try this...
	public void noPress ()
	{
		//changeFallSpeed (0);
		turnInt = 0;
	}
	
	/******************************Method turnLeft******************************
	 *	Method to turn the paperAirplane towards the left using the
	 *	changeFallSpeed method.
	 **************************************************************************/
	public void turnLeft ()
	{
		//changeFallSpeed (-1);
		turnInt = -1;
	}
	
	/******************************Method turnLeft******************************
	 *	Method to turn the paperAirplane towards the right using the
	 *	changeFallSpeed method.
	 **************************************************************************/
	public void turnRight ()
	{
		//changeFallSpeed (1);
		turnInt = 1;
	}
	
	/*************************Method changeFallSpeed****************************
	 *	Method to change the horizontal and vertical speed of the PaperAirplane
	 *	according the the parameter aChange.
	 *
	 *	@param	aChange		The value that is to be added onto the hSpeed
	 **************************************************************************/
	public void changeFallSpeed (int aChange)
	{
		//Only change the horizontal speed if the resulting new speed is
		//between -5 and 5.
		if (( getHSpeed () + aChange )<=5 && ( getHSpeed() + aChange ) >= -5)
		{
			setHSpeed (getHSpeed () + aChange);	//hSpeed is changed by having
											//aChange added onto it.
		}
		
		//The following changes the vertical speed of the plane according to
		//the horizontal speed if the horizontal speed is not 5 or -5.
		if (Math.abs (getHSpeed()) != 5)
		{
			setVSpeed ((int)(1.3 * (6 - Math.abs (getHSpeed()))));
			changeAngle (getHSpeed());	//This is meant to change the x and y
										//coordinates of the PaperAirplane's
										//vertices
		}
		//The following sets the vertical speed of the plane to 1 if the hSpeed
		//of the plane is -5 or 5
		else
		{
			setVSpeed (1);
			changeAngle (getHSpeed());	//This is meant to change the x and y
										//coordinates of the PaperAirplane's
										//vertices
		}
	}
	
	/***************************changeAngle method******************************
	 *	Method to change the angle in which the PaperAirplane is turned
	 *
	 *	@param	horizontalSpeed		the horizontal Speed of the PaperAirplane
	 *								which determines what angle the
	 *								PaperAirplane is facing, which in turn is
	 *								determined by the x- and y-coordinates of
	 *								the plane's vertices.
	 **************************************************************************/	
	public void changeAngle (int horizontalSpeed)
	{
		//The following makes the PaperAirplane face as far right as it can
		//(but it still faces downward slightly).
		if (horizontalSpeed >= 5)
		{
			xDimension1 = getX_Pos () - 23;
			xDimension2 = getX_Pos () - 18;
			xDimension3 = getX_Pos () + 22;
			
			yDimension1 = getY_Pos () - 2;
			yDimension2 = getY_Pos () - 17;
			yDimension3 = getY_Pos () + 5;
		}
		//When the horizontal speed progresses from 5 to 0, the plane faces
		//further downward, which is shown by the if statements below. Likewise,
		//as the horizontal speed progresses from 0 to 5, the plane faces
		//further right.
		else if (horizontalSpeed == 4)
		{
			xDimension1 = getX_Pos () - 20;
			xDimension2 = getX_Pos () - 12;
			xDimension3 = getX_Pos () + 20;
			
			yDimension1 = getY_Pos () - 7;
			yDimension2 = getY_Pos () - 19;
			yDimension3 = getY_Pos () + 11;
		}
		else if (horizontalSpeed == 3)
		{
			xDimension1 = getX_Pos () - 15;
			xDimension2 = getX_Pos () - 5;
			xDimension3 = getX_Pos () + 14;
			
			yDimension1 = getY_Pos () - 12;
			yDimension2 = getY_Pos () - 22;
			yDimension3 = getY_Pos () + 13;
		}
		else if (horizontalSpeed == 2)
		{
			xDimension1 = getX_Pos () - 10;
			xDimension2 = getX_Pos () + 2;
			xDimension3 = getX_Pos () + 12;
			
			yDimension1 = getY_Pos () - 17;
			yDimension2 = getY_Pos () - 22;
			yDimension3 = getY_Pos () + 18;
		}
		else if (horizontalSpeed == 1)
		{
			xDimension1 = getX_Pos () - 10;
			xDimension2 = getX_Pos () + 4;
			xDimension3 = getX_Pos () + 6;
			
			yDimension1 = getY_Pos () - 18;
			yDimension2 = getY_Pos () - 20;
			yDimension3 = getY_Pos () + 20;
		}
		//The following makes the plane face straight down.
		else if (horizontalSpeed == 0)
		{
			xDimension1 = getX_Pos () - 7;
			xDimension2 = getX_Pos ();
			xDimension3 = getX_Pos () + 7;
			
			yDimension1 = getY_Pos () - 20;
			yDimension2 = getY_Pos () + 24;
			yDimension3 = getY_Pos () - 20;
		}
		//When the horizontal speed progresses from -5 to 0, the plane faces
		//further downward, which is shown by the if statements below. Likewise,
		//as the horizontal speed progresses from 0 to -5, the plane faces
		//further left.
		else if (horizontalSpeed == -1)
		{
			xDimension1 = getX_Pos () + 10;
			xDimension2 = getX_Pos () - 4;
			xDimension3 = getX_Pos () - 6;
			
			yDimension1 = getY_Pos () - 18;
			yDimension2 = getY_Pos () - 20;
			yDimension3 = getY_Pos () + 20;
		}
		else if (horizontalSpeed == -2)
		{
			xDimension1 = getX_Pos () + 10;
			xDimension2 = getX_Pos () - 2;
			xDimension3 = getX_Pos () - 12;
			
			yDimension1 = getY_Pos () - 17;
			yDimension2 = getY_Pos () - 22;
			yDimension3 = getY_Pos () + 18;
		}
		else if (horizontalSpeed == -3)
		{
			xDimension1 = getX_Pos () + 15;
			xDimension2 = getX_Pos () + 5;
			xDimension3 = getX_Pos () - 14;
			
			yDimension1 = getY_Pos () - 12;
			yDimension2 = getY_Pos () - 22;
			yDimension3 = getY_Pos () + 13;
		}
		else if (horizontalSpeed == -4)
		{
			xDimension1 = getX_Pos () + 20;
			xDimension2 = getX_Pos () + 12;
			xDimension3 = getX_Pos () - 20;
			
			yDimension1 = getY_Pos () - 7;
			yDimension2 = getY_Pos () - 19;
			yDimension3 = getY_Pos () + 11;
		}
		//The following makes the PaperAirplane face as far left as it can
		//(but it still faces downward slightly).
		else if (horizontalSpeed <= -5)
		{
			xDimension1 = getX_Pos () + 23;
			xDimension2 = getX_Pos () + 18;
			xDimension3 = getX_Pos () - 22;
			
			yDimension1 = getY_Pos () - 2;
			yDimension2 = getY_Pos () - 17;
			yDimension3 = getY_Pos () + 5;
		}
	}

	/*****************************Method reconstruct****************************
	 *	Method to reset the variables in this class back to their original
	 *	values using the r- variables set in the constructor. It also calls the
	 *	reconstruct method of the Sprite class to reset the values set there.
	 **************************************************************************/
	public void reconstruct ()
	{
		super.reconstruct ();	//calls the superclass's reconstruct method
		xDimension1 = rXD1;
		xDimension2 = rXD2;
		xDimension3 = rXD3;
		
		yDimension1 = rYD1;
		yDimension2 = rYD2;
		yDimension3 = rYD3;
		
		turnInt = 0;
	}
	
}