package wcm.towolf.hearthstonewr.model;

import java.util.ArrayList;

import wcm.towolf.hearthstonewr.db.DBTBArenaEventList;
import wcm.towolf.hearthstonewr.db.DBTBArenaGames;
import wcm.towolf.hearthstonewr.model.datatype.RoleGame;
import wcm.towolf.hearthstonewr.model.datatype.arena.ArenaEventData;

public class ArenaEventDataProvider {
	

	DBTBArenaEventList mEventListTB;
	DBTBArenaGames mGameTB;
	
	public ArenaEventDataProvider()
	{
		mEventListTB = new DBTBArenaEventList();
		mGameTB = new DBTBArenaGames();
		
	}
	
	
	public ArrayList<ArenaEventData> getAllArenaEventData()
	{
		ArrayList<ArenaEventData> eventData = new ArrayList<ArenaEventData>();
		ArrayList<RoleGame> roleGame = new ArrayList<RoleGame>(); 

		//get all role
		eventData = mEventListTB.getAllEvents();
        //get all game
        roleGame = mGameTB.getAllGames();
        
        //init role data with games
        for(int i = 0 ;i < eventData.size() ; i++)
        {
        	eventData.get(i).initWithRoleGames(roleGame);
        }
        return eventData;
	}
	
	public ArrayList<RoleGame> getAllGames()
	{
		
		
		return mGameTB.getAllGames();
	}
	
    public void addRole(String roleName, int roleType)
    {
    	mEventListTB.addEvent(roleType);
    }
    
    public void addGame(int roleID,int enemyRoleType, boolean isWin)
    {
    	mGameTB.addGame(roleID, enemyRoleType, isWin);
    }

    public RoleGame deleteLastGame(int roleID)
    {
    	//TODO: specify role id
    	return mGameTB.deleteLastGame(roleID);
    	
    }
    
    public void deleteRole(int roleID)
    {
    	mEventListTB.deleteEvent(roleID);
    	mGameTB.deleteRole(roleID);
    }
    
}
