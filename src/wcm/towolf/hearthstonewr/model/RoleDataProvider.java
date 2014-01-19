package wcm.towolf.hearthstonewr.model;

import java.util.ArrayList;

import wcm.towolf.hearthstonewr.db.DBHelper;
import wcm.towolf.hearthstonewr.db.DBTBRoleGames;
import wcm.towolf.hearthstonewr.db.DBTBRoleList;
import wcm.towolf.hearthstonewr.model.datatype.RoleData;
import wcm.towolf.hearthstonewr.model.datatype.RoleGame;

public class RoleDataProvider {
	
	DBTBRoleList mRoleListTB;
	DBTBRoleGames mRoleGamesTB;
	
	public RoleDataProvider()
	{
		mRoleListTB = new DBTBRoleList(DBHelper.sharedInstance());
		mRoleGamesTB = new DBTBRoleGames(DBHelper.sharedInstance());
	}
	
	
	public ArrayList<RoleData> getAllRoleData()
	{
		ArrayList<RoleData> roleData = new ArrayList<RoleData>();
		ArrayList<RoleGame> roleGame = new ArrayList<RoleGame>(); 

		//get all role
        roleData = mRoleListTB.getAllRole();
        //get all game
        roleGame = mRoleGamesTB.getAllGames();
        
        //init role data with games
        for(int i = 0 ;i < roleData.size() ; i++)
        {
        	roleData.get(i).initWithRoleGames(roleGame);
        }
        return roleData;
	}
	
	public ArrayList<RoleGame> getAllGames()
	{
		
		
		return mRoleGamesTB.getAllGames();
	}
	
    public void addRole(String roleName, int roleType)
    {
    	mRoleListTB.addRole(roleName,roleType);
    }
    
    public void addGame(int roleID,int enemyRoleType, boolean isWin)
    {
    	mRoleGamesTB.addGame(roleID, enemyRoleType, isWin);
    }
    
    public void changeRoleName(int pRoleID,String pName)
    {
    	mRoleListTB.changeRoleName(pRoleID, pName);
    }
    
    public RoleGame deleteLastGame(int roleID)
    {
    	//TODO: specify role id
    	return mRoleGamesTB.deleteLastGame(roleID);
    	
    }
    
    public void deleteRole(int roleID)
    {
    	mRoleListTB.deleteRole(roleID);
    	mRoleGamesTB.deleteRole(roleID);
    }
    
    
    public void swapPostion(RoleData a, RoleData b)
    {
    	mRoleListTB.swapPosition(a, b);
    }
}
