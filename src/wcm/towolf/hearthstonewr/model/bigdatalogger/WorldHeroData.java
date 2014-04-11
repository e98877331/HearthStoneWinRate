package wcm.towolf.hearthstonewr.model.bigdatalogger;

public class WorldHeroData {
	public int roleType;
	public int [] totalGamesArray;
	public int [] winGamesArray;
	
	public WorldHeroData(int roleType, int [] totalArray, int [] winArray)
	{
		this.roleType = roleType;
		totalGamesArray = totalArray;
		winGamesArray = winArray;
	}
}
