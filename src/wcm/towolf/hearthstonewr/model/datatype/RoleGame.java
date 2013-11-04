package wcm.towolf.hearthstonewr.model.datatype;


public class RoleGame
{
	int mGameID;
    int mRoleID;
    int mRoleType;
    boolean mIsWin;
    
    public RoleGame(int gameID, int roleID,int roleType,boolean isWin)
    {
      mGameID = gameID;
      mRoleID = roleID;
      mRoleType = roleType;
      mIsWin = isWin;
    }
    
    public int getGameID(){
    	return mGameID;
    }
    
    public int getRoleID(){
    	return mRoleID;
    }
    
    public int getEnemyRoleType(){
    	return mRoleType;
    }
    
    public boolean getIsWin()
    {
    	return mIsWin;
    }
	
}
