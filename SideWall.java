
public class SideWall extends Wall
{
	/*========================Constructor for Wall==============================
	 *	Is the constructor for the SideWall class. It is exactly the same as
	 *	the constructor for the Wall class.
	 *
	 *	@param	x	Is the x-coordinate set by the user.
	 *	@param	y	Is the y-coordinate set by the user.
	 *	@param	w	Is the width of the Wall set by the user.
	 *	@param	h	Is the height of the Wall set by the user.
	 **************************************************************************/
	public SideWall (int x, int y, int w, int h)
	{
		super (x, y, w, h);
	}
	
	/*****************************Method move***********************************
	 *	Method to make the SideWall's x_pos and y_pos change in value, thus
	 *	making the illusion that the wall is moving in the applet. This method
	 *	also deals with the SideWall respawning if it moves too far off the
	 *	applet's view.
	 *	
	 *	@param	otherHeight		The reference point entered by the user where
	 *							this SideWall will respawn. It is generally the
	 *							height of the other SideWall on the same
	 *							x-coordinate as this SideWall.
	 **************************************************************************/
	public void move (int otherHeight)
	{
		if (getY_Pos() + getHeight() <= 0)
			setY_Pos (getY_Pos () + otherHeight + getHeight());	//Causes the
																//SideWall to
																//Respawn below
																//the SideWall
																//on the same
																//x-coordinate.
		setY_Pos (getY_Pos () - getVSpeed ());	//subtracts the value of this
												//SideWall's y_pos, making it
												//move up.
	}
}
