package wcm.towolf.hearthstonewr.model.datatype;

public class AddDateRoleGame extends RoleGame{

	String mAddDate = null;
	public AddDateRoleGame(int gameID, int roleID, int roleType, boolean isWin, String addDate) {
		super(gameID, roleID, roleType, isWin);
		// TODO Auto-generated constructor stub
		mAddDate = addDate;
	}

	
	public String getAddDate()
	{
		return mAddDate;
	}
}
