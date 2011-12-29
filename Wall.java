
public class Wall extends Sprite
{
	//The following values are used to help with the level up process.
	private int distanceToNext;	//Distance tot he next wall
	private int nextWidth;	//The new width of the wall
	
	private int width;	//How wide the wall is
	private int height;	//How high the wall is
	private int arcWidth;	//The width of the arcs of the rounded rectangle
	private int arcHeight;	//The height of the arcs of the rounded rectangle

	//The following are used for the reconstructing method (they are just the
	//values set for the above variables when this class is instantiated).
	private int rW;
	private int rH;
	private int rVS;
	
	/*========================Constructor for Wall==============================
	 *	Is the constructor for the Wall class that sets its getX_Pos () and
	 *	getY_Pos () values, which are technically set in the Sprite (super)
	 *	class. This constructor also sets the width and height of itself with
	 *	the parameters w and h, which are set by the user.
	 *
	 *	@param	x	Is the x-coordinate set by the user.
	 *	@param	y	Is the y-coordinate set by the user.
	 *	@param	w	Is the width of the Wall set by the user.
	 *	@param	h	Is the height of the Wall set by the user.
	 **************************************************************************/
	public Wall (int x, int y, int w, int h)
	{
		super (x, y);	//Superclass called to set x and y
		
		//The following sets the width and height  of the Wall
		width = w;
		height = h;
		
		//The following sets the levelUp variables, which are distanceToNext
		//and nextWidth.
		distanceToNext = 200;
		nextWidth = width;
		
		//The following sets the width and height of the Wall's corner arcs
		arcWidth = 15;
		arcHeight = 15;
		
		//The following sets the reconstructing variables.
		rW = w;
		rH = h;
	}

/*------------------------------Getter Methods----------------------------------
 *	These are methods that set the values of variables in this class.
 *----------------------------------------------------------------------------*/
 
 	/*****************************Method getHeight******************************
	 *	Method to return the value of height
	 *
	 *	@return		height		The height of the rectangular image of the Wall
	 *							that is to be created in the applet via the
	 *							driver class.
	 **************************************************************************/
	public int getHeight ()
	{
		return height;
	}
	
	/*****************************Method getWidth*******************************
	 *	Method to return the value of width
	 *
	 *	@return		width		The width of the rectangular image of the Wall
	 *							that is to be created in the applet via the
	 *							driver class.
	 **************************************************************************/
	public int getWidth ()
	{
		return width;
	}
	
	/***************************Method getArcHeight*****************************
	 *	Method to return the value of arcHeight.
	 *
	 *	@return		arcHeight	The height of the Wall's rectangular image's
	 *							corner arc that is to be created in the applet
	 *							via the driver class.
	 **************************************************************************/
	public int getArcHeight ()
	{
		return arcHeight;
	}
	
	/***************************Method getArcWidth******************************
	 *	Method to return the value of arcWidth.
	 *
	 *	@return		arcWidth	The width of the Wall's rectangular image's
	 *							corner arc that is to be created in the applet
	 *							via the driver class.
	 **************************************************************************/
	public int getArcWidth ()
	{
		return arcWidth;
	}
	
	/***************************Method getLeftSide******************************
	 *	Method to return the x-coordinate of the left side of the wall. Although
	 *	such an x-coordinate is the same as x_pos, making a method like this
	 *	makes it easier to understand that this is getting the left side of the
	 *	wall.
	 *
	 *	@return		getX_Pos ()	The x-coordinate of the left side of the Wall.
	 **************************************************************************/
	public int getLeftSide ()
	{
		return getX_Pos ();
	}
	
	/***************************Method getLeftSide******************************
	 *	Method to return the x-coordinate of the right side of the wall. It is
	 *	essentially the value of x_pos plus the width of the wall.
	 *
	 *	@return		getX_Pos () + width		The x-coordinate of the right side
	 *										of the Wall.
	 **************************************************************************/
	public int getRightSide ()
	{
		return getX_Pos () + width;
	}
	
	/***************************Method getTopSide*******************************
	 *	Method to return the y-coordinate of the top side of the wall. Although
	 *	such a y-coordinate is the same as y_pos, making a method like this
	 *	makes it easier to understand that this is getting the top side of the
	 *	wall.
	 *
	 *	@return		getY_Pos ()	The y-coordinate of the top side of the Wall.
	 **************************************************************************/
	public int getTopSide ()
	{
		return getY_Pos ();
	}
	
	/**************************Method getBottomSide*****************************
	 *	Method to return the y-coordinate of the bottom side of the wall. It is
	 *	essentially the value of y_pos plus the height of the wall.
	 *
	 *	@return		getX_Pos () + height	The x-coordinate of the bottom side
	 *										of the Wall.
	 **************************************************************************/
	public int getBottomSide ()
	{
		return getY_Pos () + height;
	}

/*------------------------------Setter Methods----------------------------------
 *	These are methods that set the values of variables in this class.
 *----------------------------------------------------------------------------*/
 
 	/****************************Method setWidth********************************
 	 *	Method to set the value of width to nWidth, which is a parameter set by
 	 *	the user.
 	 *	
 	 *	@param		nWidth		The new width set by the user.
 	 **************************************************************************/
	public void setWidth (int nWidth)
	{
		width = nWidth;
	}
	
	/****************************Method setHeight*******************************
 	 *	Method to set the value of height to nHeight, which is a parameter set
 	 *	by the user.
 	 *	
 	 *	@param		nHeight		The new height set by the user.
 	 **************************************************************************/
	public void setHeight (int nHeight)
	{
		height = nHeight;
	}
	
/*-----------------------------------Other Methods------------------------------
 *	All the other methods that don't fit in the previous categories.
 *----------------------------------------------------------------------------*/
 
 	/*****************************Method move***********************************
	 *	Method to make the Wall's x_pos and y_pos change in value, thus making
	 *	the illusion that the wall is moving in the applet. This method also
	 *	deals with the Wall respawning if it moves too far off the applet's
	 *	view.
	 *	
	 *	@param	respawnBehind	The reference point entered by the user where
	 *							this Wall will respawn behind by the distance
	 *							of distanceToNext
	 **************************************************************************/
	public void move (int respawnBehind)
	{
		//The Wall respawns if it's base is beyond or on the y-coordinate 0.
		if (getY_Pos () + height <= 0)
		{
			setY_Pos (respawnBehind + distanceToNext);	//The y-coordinate of
														//the Wall is set
														//behind the point
														//respawnBehind by
														//distanceToNext
			setWidth (nextWidth);	//This width only changes when the game
									//levels beyond level 5.
			//The following moves the Wall left if it is located on the right
			//side and its width causes it to penetrate the right SideWall. 
			if (getX_Pos () + width > 450)
				setX_Pos (getX_Pos () - 20);
		}
		//The wall's y-coordinate decreases by the value of vSpeed, making it
		//look like it's moving up.
		setY_Pos (getY_Pos () - getVSpeed ());
	}
	
	/****************************Method levelUp1********************************
	 *	Method to level up the game by means of shortening the distanceToNext
	 *	value.
	 **************************************************************************/
	public void levelUp1 ()
	{
		distanceToNext -= 20;
	}
	
	/****************************Method levelUp2********************************
	 *	Method to level up the game by means of increasing the width of the
	 *	wall.
	 **************************************************************************/
	public void levelUp2 ()
	{
		nextWidth += 20;
	}
	
	/*****************************Method isPenetrated***************************
	 *	Method to check if the coordinates of the a PaperAirplane's vertexes are
	 *	within this wall.
	 *
	 *	@return		true	when the coordinates of one of more of the 
	 *						PaperAirplane's coordinates are within the sides of
	 *						this wall.
	 *
	 *	@return		false	when the coordinates of one of more of the 
	 *						PaperAirplane's coordinates are not within the sides
	 *						of this wall.
	 **************************************************************************/
	public boolean isPenetrated (int [] xPoints, int [] yPoints)
	{
		for (int j = 0; j < 3; j++)
		{
			if (xPoints [j] >= getLeftSide () && xPoints [j] <= getRightSide () && yPoints [j] >= getTopSide () && yPoints [j] <= getBottomSide ())
				return true;
		}
		return false;
	}
	
	/*****************************Method reconstruct****************************
	 *	Method to reset the variables in this class back to their original
	 *	values using the r- variables set in the constructor. It also calls the
	 *	reconstruct method of the Sprite class to reset the values set there.
	 **************************************************************************/
	public void reconstruct ()
	{
		super.reconstruct ();	//calls the superclass's reconstruct method
		width = rW;
		height = rH;
		
		distanceToNext = 200;
		nextWidth = width;
	}
}
