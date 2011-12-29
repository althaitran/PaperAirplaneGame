public class LevelWall extends Wall
{
	public int distanceToNextLevel;
	
	public LevelWall (int x, int y, int w, int h, int vSp, int dToNextL)
	{
		super (x, y, w, h);
		distanceToNextLevel = dToNextL;
	}
	
	public void move ()
	{
		if (getY_Pos() + getHeight() <= 0)
		{
			int nY_Pos = getY_Pos () + getHeight() + distanceToNextLevel;
			setY_Pos (nY_Pos);
		}
		int nY_Pos = getY_Pos () - getVSpeed ();
		setY_Pos (nY_Pos);
	}
}
