package wcm.towolf.hearthstonewr.model.datatype;

import java.util.ArrayList;

public class RoleEnemyData {

	private int mWin = 0 ;
	private int mTotal = 0;
	private int mEnemyRoleType;
	public RoleEnemyData(int pRoleType)
	{
		mEnemyRoleType = pRoleType;
	}
	
	public int getWin(){
		return mWin;
	}
	public int getLost(){
		return mTotal - mWin;
	}
	
	public float getWinRate()
	{
		return (float)mWin/mTotal;
	}
	public int getType(){
		return mEnemyRoleType;
	}
	
	
	public void addGame(boolean isWin){
		++mTotal;
		if(isWin)
			++mWin;
	}
	
	public void deleteGame(boolean isWin)
	{
		--mTotal;
		if(isWin)
		 --mWin;
	}
	
	public static ArrayList<RoleEnemyData> generateDataList()
	{
		ArrayList<RoleEnemyData> list = new ArrayList<RoleEnemyData>();
		list.add(new RoleEnemyData(0));
		list.add(new RoleEnemyData(1));
		list.add(new RoleEnemyData(2));
		list.add(new RoleEnemyData(3));
		list.add(new RoleEnemyData(4));
		list.add(new RoleEnemyData(5));
		list.add(new RoleEnemyData(6));
		list.add(new RoleEnemyData(7));
		list.add(new RoleEnemyData(8));
		return list;
	}
}
