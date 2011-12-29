
public class Timer
{
	private int minutes;	//The time value in minutes
	private int displayedSeconds;	//The time value in seconds as shown in the
									//actual gameplay
	private int totalSeconds;	//The total number of seconds passed in the
								//game
	
	/*------------------------Constructor for Timer-----------------------------
	 *	Is the constructor for the Timer class. It doesn't really do anything.
	 **************************************************************************/
	public Timer ()
	{
	}
	
	/******************************Method update********************************
	 *	Method to update the time in this class. It does this by adding onto the
	 *	totalSeconds value and getting the displayed seconds from it by using
	 *	% 60 on it (yielding numbers between 0 and 59). It also gets the
	 *	minutes by dividing totalSeconds by 60.
	 **************************************************************************/
	public void update ()
	{
		totalSeconds ++;	//Total Seconds is added onto.
		displayedSeconds = totalSeconds % 60;	//This gets the value of the
												//seconds that is to be
												//displayed in the game.
		minutes = totalSeconds / 60;	//This gets the minutes passed in the
										//game.
	}

/*------------------------------Getter Methods----------------------------------
 *	These are methods that set the values of variables in this class.
 *----------------------------------------------------------------------------*/

	/**************************Method getMinutes********************************
	 *	Method to return the int minutes from this class.
	 *	
	 *	@return		minutes		int that is meant to represent the record of
	 *							time in this class expressed in minutes.
	 **************************************************************************/
	public int getMinutes()
	{
		return minutes;
	}

	/***********************Method getDisplayedSeconds**************************
	 *	Method to return the int displayedSeconds from this class.
	 *	
	 *	@return		displayedSeconds	int that is meant to represent the
	 *									record of time in this class expressed
	 *									in seconds from 0-59.
	 **************************************************************************/	
	public int getDisplayedSeconds ()
	{
		return displayedSeconds;
	}
	/***********************Method getTotalSeconds******************************
	 *	Method to return the int displayedSeconds from this class.
	 *	
	 *	@return		totalSeconds		int that is meant to represent the
	 *									record of time in this class expressed
	 *									in seconds.
	 **************************************************************************/
	public int getTotalSeconds ()
	{
		return totalSeconds;
	}
	
	/****************************Method setTime*********************************
	 *	Method to change the value of the time recorded in this class. The total
	 *	seconds, displayed seconds, and minutes must be set in this method.
	 *	
	 *	@param		nTotalSeconds			int entered by the user that is 
	 *										meant to represent the record of
	 *										time in this class expressed in
	 *										seconds.
	 *
	 *	@param		nDisplayedSeconds		int entered by the user that is
	 *										meant to represent the	record of
	 *										time in this class expressed in
	 *										seconds from 0-59.
	 *	
	 *	@param		nMinutes				int entered by the user that is
	 *										meant to represent the record of
	 *										time in this class expressed in
	 *										minutes.
	 **************************************************************************/
	public void setTime (int nTotalSeconds, int nDisplayedSeconds, int nMinutes)
	{
		totalSeconds = nTotalSeconds;
		displayedSeconds = nDisplayedSeconds;
		minutes = nMinutes;
	}
	/*****************************Method resetTime******************************
	 *	Method to reset the time values in this class  (minutes,
	 *	displayedSeconds, and totalSeconds) back to 0.
	 **************************************************************************/
	public void resetTime ()
	{
		minutes = 0;
		displayedSeconds = 0;
		totalSeconds = 0;
	}
}
