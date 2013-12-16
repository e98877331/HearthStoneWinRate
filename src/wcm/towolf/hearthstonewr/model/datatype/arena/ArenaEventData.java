package wcm.towolf.hearthstonewr.model.datatype.arena;

import java.util.ArrayList;

import wcm.towolf.hearthstonewr.R;
import wcm.towolf.hearthstonewr.model.ArenaEventDataProvider;
import wcm.towolf.hearthstonewr.model.RoleDataProvider;
import wcm.towolf.hearthstonewr.model.datatype.RoleData;
import wcm.towolf.hearthstonewr.model.datatype.RoleEnemyData;
import wcm.towolf.hearthstonewr.model.datatype.RoleGame;
import wcm.towolf.hearthstonewr.model.datatype.RoleType;
import android.util.Log;

public class ArenaEventData {

	private static final String TAG = "ArenaEventData";
	// passing between activity purpose
	private static RoleData mPassingData;
   
	//now use enemy data by now
	//ArrayList<RoleEnemyData> mRoleEnemyDataList;
	
	public static final int NAME_LENGTH = 40;
	
	public int eventID;
	public int roleType;

	public float winRate;

	public int win, count;



	public ArenaEventData(int pEventID, int pRoleType) {
		eventID = pEventID;
		roleType = pRoleType;


	}

	
	public void initWithRoleGames(ArrayList<RoleGame> gameList) {
       // mRoleEnemyDataList = RoleEnemyData.generateDataList();
		
		int win = 0, count = 0;
		for (int i = 0; i < gameList.size(); i++) {
			RoleGame rg = gameList.get(i);
			if (rg.getRoleID() == eventID) {
				++count;
				if (rg.getIsWin())
					++win;
				
	//			mRoleEnemyDataList.get(rg.getEnemyRoleType()).addGame(rg.getIsWin());
			}

		}
		this.win = win;
		this.count = count;

		// winRate = (float)win/count;
	}

	
	
	public void addGame(int enemyType, boolean isWin) {
		++count;
		if (isWin)
			++win;
		//not use enemy type by now
	//	mRoleEnemyDataList.get(enemyType).addGame(isWin);

		// sync to db
		ArenaEventDataProvider edp = new ArenaEventDataProvider();
		edp.addGame(this.eventID, enemyType, isWin);
	}


	public void deleteLastGame()
	{
		ArenaEventDataProvider edp = new ArenaEventDataProvider();
		RoleGame rg = edp.deleteLastGame(this.eventID);
		
		if(rg == null)
			return;
		
		invalidate();
	}
	
	/*
	 * getter and setter
	 */

	public int getRoleRes() {
		// if(pRoleType == RoleType.worrior)
		// return R.drawable.ic_launcher;
		switch (roleType) {
		case RoleType.DRUID:
			return R.drawable.druid;
		case RoleType.HUNTER:
			return R.drawable.hunter;
		case RoleType.MAGE:
			return R.drawable.mage;
		case RoleType.PALADIN:
			return R.drawable.paladin;
		case RoleType.PRIEST:
			return R.drawable.priest;
		case RoleType.ROGUE:
			return R.drawable.rogue;
		case RoleType.SHAMAN:
			return R.drawable.shaman;
		case RoleType.WARLOCK:
			return R.drawable.warlock;
		case RoleType.WARRIOR:
			return R.drawable.warrior;
		default:
			return R.drawable.ic_launcher;
		}

	}
	
	public int getRoleStoneRes() {
		// if(pRoleType == RoleType.worrior)
		// return R.drawable.ic_launcher;
		switch (roleType) {
		case RoleType.DRUID:
			return R.drawable.druid_stone;
		case RoleType.HUNTER:
			return R.drawable.hunter_stone;
		case RoleType.MAGE:
			return R.drawable.mage_stone;
		case RoleType.PALADIN:
			return R.drawable.paladin_stone;
		case RoleType.PRIEST:
			return R.drawable.priest_stone;
		case RoleType.ROGUE:
			return R.drawable.rogue_stone;
		case RoleType.SHAMAN:
			return R.drawable.shaman_stone;
		case RoleType.WARLOCK:
			return R.drawable.warlock_stone;
		case RoleType.WARRIOR:
			return R.drawable.warrior_stone;
		default:
			return R.drawable.ic_launcher;
		}

	}

	public int getWin() {
		return win;
	}

	public int getTotalGame() {
		return count;
	}

	public int getLose() {
		return count - win;
	}

	public float getWinRate() {
		return (count == 0) ? (winRate = -1) : ((float) win / count);
	}
	
	public int getRoleType() {
		return roleType;
	}
	
//  not use by now
//	public RoleEnemyData getRoleEnemyData(int pRoleType)
//	{
//		return mRoleEnemyDataList.get(pRoleType);
//	}

	/*
	 * private method
	 */
	
	private void invalidate()
	{
		RoleDataProvider rdp = new RoleDataProvider();
		initWithRoleGames(rdp.getAllGames());
	}
	
	/*
	 * static method
	 */
	public static void setPassingData(RoleData pData) {
		mPassingData = pData;
	}

	public static RoleData getPassingData() {

		if (mPassingData != null)
			return mPassingData;
		else {
			Log.e(TAG, "mPassingData == null");
			return null;
		}
	}

	
	
}
