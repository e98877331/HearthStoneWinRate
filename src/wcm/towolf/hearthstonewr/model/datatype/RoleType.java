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
	
	public static int getRoleTypeString(int type) {
		switch(type) {
		case 0:
			return R.string.common_warrior;
		case 1:
			return R.string.common_hunter;
		case 2:
			return R.string.common_druid;
		case 3:
			return R.string.common_mage;
		case 4:
			return R.string.common_paladin;
		case 5:
			return R.string.common_priest;
		case 6:
			return R.string.common_rogue;
		case 7:
			return R.string.common_shaman;
		case 8:
			return R.string.common_warlock;
		default:
			return 0;
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
