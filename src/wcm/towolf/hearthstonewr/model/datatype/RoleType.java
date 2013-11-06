package wcm.towolf.hearthstonewr.model.datatype;

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
}
