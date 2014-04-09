package wcm.towolf.hearthstonewr.model.bigdatalogger;

import java.util.ArrayList;

import wcm.towolf.hearthstonewr.model.api.ApiHelper;

public class ArenaBigDataLogger {
	
	private static ArenaBigDataLogger mInstance = new ArenaBigDataLogger();
	
	private ArrayList<Game> mGames;
	private long lastEnqueueTime = 0;
	
	public ArenaBigDataLogger() {
		// TODO Auto-generated constructor stub
		
		mGames = new ArrayList<ArenaBigDataLogger.Game>();
	}
	
	public void enqueueGame(int roleType, int vsRoleType, boolean isWin)
	{
		long currentTime = System.currentTimeMillis();
		
		if((currentTime - lastEnqueueTime < 5) && lastEnqueueTime != 0)
			return;
		
		lastEnqueueTime = currentTime;
		mGames.add(new Game(roleType, vsRoleType, isWin));
	}
	
	public void dequeueAllToServer()
	{
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
				for(int i = 0 ;i< mGames.size();i++)
				{
					ApiHelper.sendArenaLog(mGames.get(i));
				}
				mGames.clear();
			}
		}).start();
	}
	
	
	
	public static ArenaBigDataLogger sharedInstance()
	{
		return mInstance;
	}
	
	public static class Game
	{
		public int roleType;
		public int vsRoleType;
		public boolean isWin;
		
		public Game(int pRoleType, int pVsRoleType, boolean pIsWin)
		{
			roleType = pRoleType;
			vsRoleType = pVsRoleType;
			isWin = pIsWin;
		}
	}
}
