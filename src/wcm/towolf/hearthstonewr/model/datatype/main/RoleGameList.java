package wcm.towolf.hearthstonewr.model.datatype.main;

import java.util.ArrayList;

import wcm.towolf.hearthstonewr.model.datatype.RoleGame;

public class RoleGameList {
	
	public ArrayList<RoleGame> mGames;
	public RoleGameList()
	{
		mGames = new ArrayList<RoleGame>();
	}

	public void addGame(RoleGame game)
	{
		mGames.add(game);
	}
	
	public int getWins()
	{
		int winCount = 0;
		for(int i = 0; i< mGames.size();i++)
		{
			if(mGames.get(i).getIsWin())
				winCount++;
		}
		return winCount;
	}
	
	public int getTotal()
	{
	   return mGames.size();
	}
	
	public int getLoses()
	{
		return getTotal() - getWins();
	}
	
	
}
