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
	
	
	//TODO:slow
	public ArenaEventData getEvent(int id)
	{
		
		ArenaEventData aed = mEventListTB.getEvent(id);
		
		aed.initWithRoleGames(mGameTB.getAllGames());
		return aed;
	}
	
    public long addEvent(int roleType)
    {
    	return mEventListTB.addEvent(roleType);
    }
    
    public void addGame(int eventID,int enemyRoleType, boolean isWin)
    {
    	mGameTB.addGame(eventID, enemyRoleType, isWin);
    }

    public RoleGame deleteLastGame(int roleID)
    {
    	//TODO: specify role id
    	return mGameTB.deleteLastGame(roleID);
    	
    }
    
    public void deleteEvent(int eventID)
    {
    	mEventListTB.deleteEvent(eventID);
    	mGameTB.deleteRole(eventID);
    }
    
}
