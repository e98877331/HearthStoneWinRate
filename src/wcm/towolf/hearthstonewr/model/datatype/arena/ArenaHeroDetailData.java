package wcm.towolf.hearthstonewr.model.datatype.arena;

import java.util.ArrayList;

import wcm.towolf.hearthstonewr.model.datatype.RoleGame;
import wcm.towolf.hearthstonewr.model.datatype.main.RoleGameList;

public class ArenaHeroDetailData extends RoleGameList{
private static int NUMBER_OF_HERO_TYPE =9;
	
	
	int mRoleType;
	ArrayList<ArenaVSHeroData> mVSHeroList;
	public ArenaHeroDetailData(int roleType)
	{
		
		mRoleType = roleType;
		mGames = new ArrayList<RoleGame>();
		initVSHeroList();
	}
	
	public int getRoleType()
	{
		return mRoleType;
	}
	
	public void addGame(RoleGame game)
	{
		super.addGame(game);
	    mVSHeroList.get(game.getEnemyRoleType()).addGame(game);
	}
	
	public ArrayList<ArenaVSHeroData> getVsHeroList()
	{
		return mVSHeroList;
	}
	

	
	
	private void initVSHeroList()
	{
		mVSHeroList = new ArrayList<ArenaHeroDetailData.ArenaVSHeroData>();
		for(int i = 0; i< NUMBER_OF_HERO_TYPE;i++)
			mVSHeroList.add(new ArenaVSHeroData(i));
		
	}
	
	public static ArrayList<ArenaHeroDetailData> generateEmptyDataList()
	{
		ArrayList<ArenaHeroDetailData> dataList = new ArrayList<ArenaHeroDetailData>();
		
		for(int i = 0 ; i< NUMBER_OF_HERO_TYPE ;i++)
			dataList.add(new ArenaHeroDetailData(i));
		
		return dataList;
			
	}
	
	public class ArenaVSHeroData extends RoleGameList
	{
		
		int mVSRoleType;
		
		public ArenaVSHeroData(int vsRoleType)
		{
			mVSRoleType = vsRoleType;
			mGames = new ArrayList<RoleGame>();
		}
		
		public int getVSRoleType()
		{
			return mVSRoleType;
		}
		
	}
}
