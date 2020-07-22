package mods.immibis.chunkloader;

import java.util.UUID;

import com.mojang.authlib.GameProfile;

public class Owner {
	public static final String SERVER_OWNER_STRING = "1";
	public static final String DATA_LOST_STRING = "3";
	public static final String NON_PLAYER_STRING = "4";
	
	public static String getPlayerOwnerString(GameProfile profile) {
		return "5" + profile.getId().toString() + ":" + profile.getName();
	}

	public static String getDisplayString(String owner) {
		if(owner.equals("")) return "invalid";
		
		switch(owner.charAt(0)) {
		case '1': return "<server>";
		case '2': return "<break and replace to fix; "+owner.substring(1)+">";
		case '3': return "<data lost; report this bug>";
		case '4': return "<non-player>";
		case '5': return owner.substring(owner.indexOf(':')+1);
		default: return "invalid:"+owner;
		}
	}
	
	public static UUID getGameProfileID(String owner) {
		if(owner.length() > 0 && owner.charAt(0) == '5' && owner.indexOf(':') >= 0)
			return UUID.fromString(owner.substring(1, owner.indexOf(':')));
		else
			return null;
	}

	public static String getLastUsername(String owner) {
		if(owner.length() > 0 && owner.charAt(0) == '5' && owner.indexOf(':') >= 0)
			return owner.substring(owner.indexOf(':')+1);
		else
			return null;
	}
	
	public static GameProfile getGameProfile(String owner) {
		if(owner.length() > 0 && owner.charAt(0) == '5') {
			UUID id = getGameProfileID(owner);
			String username = getLastUsername(owner);
			if(id != null && username != null)
				return new GameProfile(id, username);
		}
		return null;
	}

	public static String getNonUUIDPlayerOwnerString(String username) {
		return "2" + username;
	}
}
