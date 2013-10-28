package wcmlab.towolf.hearthstonewr.model.datatype;

import wcmlab.towolf.hearthstonewr.R;



public class RoleData
{
		
	public RoleType roleType;
	public String roleName;
	public float winRate;
	
	public RoleData(RoleType pRoleType,String pRoleName,float pWinRate)
	{
        roleType = pRoleType;
        roleName = pRoleName;
        winRate = pWinRate;
	}
	
	public int getRoleRes(RoleType pRoleType)
	{
	//	if(pRoleType == RoleType.worrior)
		return R.drawable.ic_launcher;	
	}
	
}