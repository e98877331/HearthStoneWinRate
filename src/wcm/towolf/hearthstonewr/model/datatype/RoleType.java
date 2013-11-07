package wcm.towolf.hearthstonewr.model.datatype;

import wcm.towolf.hearthstonewr.R;

public class RoleType {
	
	public static final int WARRIOR = 0;
	public static final int HUNTER = 1;
	public static final int DRUID = 2;
	public static final int MAGE =3;
	public static final int PALADIN = 4;
	public static final int PRIEST = 5;
	public static final int ROGUE = 6;
	public static final int SHAMAN = 7;
	public static final int WARLOCK = 8;
	//worrior(0),hunter(1);
	
	public static String getRoleTypeString(int type) {
		switch(type) {
		case 0:
			return "WARRIOR";
		case 1:
			return "HUNTER";
		case 2:
			return "DRUID";
		case 3:
			return "MAGE";
		case 4:
			return "PALADIN";
		case 5:
			return "PRIEST";
		case 6:
			return "ROGUE";
		case 7:
			return "SHAMAN";
		case 8:
			return "WARLOCK";
		default:
			return null;
		}
	}
	
	public static int getRoleRes(int roleType) {
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

}
