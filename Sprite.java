
public class Sprite
{
	private int x_pos;	//The x-coordinate of the Sprite
	private int y_pos;	//The y-coordinate of the Sprite
		
	private int hSpeed;	//The horizontal speed of the Sprite
	private int vSpeed;	//The vertical speed of the Sprite
	
	//The following are used for the reconstructing method (they are just the
	//values set for the above variables when this class is instantiated).
	private int rX;
	private int rY;
	private int rHS;
	private int rVS;
	
	/*========================Constructor for Sprite===========================
	 *	Is the constructor for the Sprite class that mainly sets its x_pos and
	 *	y_pos values, along with its rX and rY values that are essentially the
	 *	same thing.
	 *
	 *	@param	x	Is the x-coordinate set by the user.
	 *	@param	y	Is the y-coordinate set by the user.
	 **************************************************************************/
	public Sprite (int x, int y)
	{
		x_pos = x;
		y_pos = y;
		
		rX = x;
		rY = y;
	}
	
/*------------------------------Getter Methods----------------------------------
 *	These are methods that return the values of variables in this class. They
 *	are intended for the use of outside classes like the driver class and the
 *	subclasses like the PaperAirplane class.
 *----------------------------------------------------------------------------*/
	
	/***************************Method getX_Pos*********************************
	 *	Method to get the x_pos value from this class.
	 *	
	 *	@return		x_pos	The x_pos value of this class, which is the
	 *						x-coordinate of the Sprite.
	 **************************************************************************/
	public int getX_Pos ()
	{
		return x_pos;
	}
	
	/***************************Method getY_Pos*********************************
	 *	Method to get the y_pos value from this class.
	 *	
	 *	@return		y_pos	The y_pos value of this class, which is the
	 *						y-coordinate of the Sprite.
	 **************************************************************************/
	public int getY_Pos ()
	{
		return y_pos;
	}
	
	/*************************Method getHSpeed**********************************
	 *	Method to get the value of hSpeed from this class.
	 *
	 *	@return		hSpeed	The hSpeed value of this class, which is the
	 *						horizontal speed at which the Sprite travels at.
	 **************************************************************************/
	public int getHSpeed ()
	{
		return hSpeed;
	}

	/*************************Method getVSpeed**********************************
	 *	Method to get the value of vSpeed from this class.
	 *
	 *	@return		vSpeed	The vSpeed value of this class, which is the
	 *						vertical speed at which the Sprite travels at.
	 **************************************************************************/	
	public int getVSpeed ()
	{
		return vSpeed;
	}
	
/*------------------------------Setter Methods----------------------------------
 *	These are methods that set the values of variables in this class.
 *----------------------------------------------------------------------------*/
 
	/***************************Method setX_Pos*********************************
	 *	Method to set the x_pos value from this class.
	 *	
	 *	@param		newX_Pos	The new x-coordinate for the Sprite set by the
	 *							user.
	 **************************************************************************/
	public void setX_Pos (int newX_Pos)
	{
		x_pos = newX_Pos;
	}
	
	/***************************Method setY_Pos*********************************
	 *	Method to set the y_pos value from this class.
	 *	
	 *	@param		newY_Pos	The new y-coordinate for the Sprite set by the
	 *							user.
	 **************************************************************************/
	public void setY_Pos (int newY_Pos)
	{
		y_pos = newY_Pos;
	}
	
	/***************************Method setHSpeed********************************
	 *	Method to set the hSpeed value from this class.
	 *	
	 *	@param		newHSpeed	The new horizontal speed for the Sprite set by
	 *							the user.
	 **************************************************************************/
	public void setHSpeed (int newHSpeed)
	{
		hSpeed = newHSpeed;
	}
	
	/***************************Method setVSpeed********************************
	 *	Method to set the vSpeed value from this class.
	 *	
	 *	@param		newVSpeed	The new vertical speed for the Sprite set by
	 *							the user.
	 **************************************************************************/
	public void setVSpeed (int newVSpeed)
	{
		vSpeed = newVSpeed;
	}

/*-----------------------------------Other Methods------------------------------
 *	All the other methods that don't fit in the previous categories.
 *----------------------------------------------------------------------------*/
 
	/*************************Method reconstruct********************************
	 *	Method to set the variables of this class (such as x_pos and y_pos)
	 *	back to their original values when this class was first instantiated.
	 *	This is done by setting the variables to the values of the r- variables
	 *	like rX and rY.
	 **************************************************************************/
	public void reconstruct ()
	{
		x_pos = rX;
		y_pos = rY;
		
		hSpeed = rHS;
		vSpeed = rVS;
	}
}
