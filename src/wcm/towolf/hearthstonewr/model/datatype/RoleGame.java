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
	
}
