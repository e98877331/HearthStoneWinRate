package wcmlab.towolf.hearthstonewr.model.datatype;

import java.util.ArrayList;

import wcmlab.towolf.hearthstonewr.R;
import android.util.Log;



public class RoleData
{
		
	private static final String TAG = "RoleData";
	//passing between activity purpose
	private static RoleData mPassingData;
	
	public int roleID;
	public int roleType;
	public String roleName;
	public float winRate;
	
	public int win,count;
	
	public RoleData(int pRoleType,String pRoleName)
	{
        roleType = pRoleType;
        roleName = pRoleName;
      
	}
	public RoleData(int pRoleID,int pRoleType,String pRoleName)
	{
		roleID = pRoleID;
        roleType = pRoleType;
        roleName = pRoleName;
      
	}
	
	public void initWithRoleGames(ArrayList<RoleGame> gameList)
	{
		//TODO: generate all enemy vs data
		
		int win = 0,count = 0;
		for(int i = 0; i < gameList.size();i++)
		{
			RoleGame rg = gameList.get(i);
			if(rg.mRoleID == roleID)
			{
				++count; 
				if(rg.mIsWin)
					++win;
			}
		}
		this.win = win;
		this.count = count;
		winRate = (count == 0) ? (winRate = -1):((float)win/count);
			
		//winRate = (float)win/count;
	}
	
	public int getRoleRes(int pRoleType)
	{
	//	if(pRoleType == RoleType.worrior)
		return R.drawable.ic_launcher;	
	}
	
	
	
	/*
	 * static method
	 */
	public static void setPassingData(RoleData pData)
	{
		mPassingData = pData;
	}
	
    public static RoleData getPassingData()
    {
    	
    	if(mPassingData != null)
           return mPassingData;
    	else
    	{
    	Log.e(TAG,"mPassingData == null");
    	return null;
    	}
    }
	
	
}