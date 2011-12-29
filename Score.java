
public class Score
{
	private int distance;	//Record of the distance travelled in the game.
	
	/*------------------------Constructor for Score-----------------------------
	 *	Is the constructor for the Score class. It doesn't really do anything.
	 **************************************************************************/
	public Score ()
	{
	}
	
	/****************************Method getScore********************************
	 *	Method to get the score from this class by means of dividing the
	 *	value of distance by 100.
	 *
	 *	@return		distance / 100		The score that is to be used for display
	 *									in the actual game. It is the distance
	 *									divided by 100 because it yields
	 *									smaller numbers, making it so that the
	 *									player does not receive such high
	 *									scores for travelling little distance
	 *									in the game.
	 **************************************************************************/
	public int getScore ()
	{
		return distance / 100;
	}
	
	/*****************************Method setScore*******************************
	 *	Method to change the distance in this class using the parameter nScore.
	 *	
	 *	@param		nScore		in that is to be the new score entered by the
	 *							user. It is technically the score that is to be
	 *							displayed rather than the true value of the
	 *							distance, as it will be multiplied by 100 in
	 *							this method for the purpose of using getScore
	 *							properly.
	 **************************************************************************/
	public void setScore (int nScore)
	{
		distance = nScore * 100;
	}
	
	/***************************Method updateScore******************************
	 *	Method to update the score of the game from this class. It technically
	 *	adds onto the distance with the distanceAdder, so the actual score does
	 *	not change until it passes another 100 units of the distance (which is
	 *	why distance is divided by 100 in the getScore method and multiplied by
	 *	100 in the setScore method).
	 *	
	 *	@param		distanceAdder		int that is to be added onto the value
	 *									of distance in this method.
	 **************************************************************************/
	public void updateScore (int distanceAdder)
	{
		distance += distanceAdder;
	}
	
	/******************************Method reconstruct***************************
	 *	Method to reset the value of distance to 0
	 **************************************************************************/
	public void reconstruct ()
	{
		distance = 0;
	}
}
