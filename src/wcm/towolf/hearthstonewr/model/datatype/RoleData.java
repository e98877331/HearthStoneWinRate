package wcm.towolf.hearthstonewr.model.datatype;

import java.util.ArrayList;

import wcm.towolf.hearthstonewr.R;
import wcm.towolf.hearthstonewr.model.RoleDataProvider;
import android.util.Log;

public class RoleData {

	private static final String TAG = "RoleData";
	// passing between activity purpose
	private static RoleData mPassingData;

	public int roleID;
	public int roleType;
	public String roleName;
	public float winRate;

	public int win, count;

	public RoleData(int pRoleType, String pRoleName) {
		roleType = pRoleType;
		roleName = pRoleName;

	}

	public RoleData(int pRoleID, int pRoleType, String pRoleName) {
		roleID = pRoleID;
		roleType = pRoleType;
		roleName = pRoleName;

	}

	public void initWithRoleGames(ArrayList<RoleGame> gameList) {
		// TODO: generate all enemy vs data

		int win = 0, count = 0;
		for (int i = 0; i < gameList.size(); i++) {
			RoleGame rg = gameList.get(i);
			if (rg.mRoleID == roleID) {
				++count;
				if (rg.mIsWin)
					++win;
			}
		}
		this.win = win;
		this.count = count;
		calculateWinRate();

		// winRate = (float)win/count;
	}

	public void addGame(int enemyType, boolean isWin) {
		++count;
		if (isWin)
			++win;

		calculateWinRate();

		// sync to db
		RoleDataProvider edp = new RoleDataProvider();
		edp.addGame(this.roleID, enemyType, isWin);
	}

	public void changeRoleName(String pName)
	{
		RoleDataProvider edp = new RoleDataProvider();
		edp.changeRoleName(this.roleID, pName);
	}
	
	public void deleteLastGame()
	{
		RoleDataProvider edp = new RoleDataProvider();
		RoleGame rg = edp.deleteLastGame(this.roleID);
		
		if(rg == null)
			return;
		
		--count;
		if(rg.getIsWin())
			--win;
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
		return winRate;
	}


	/*
	 * private method
	 */
	private void calculateWinRate() {
		winRate = (count == 0) ? (winRate = -1) : ((float) win / count);
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