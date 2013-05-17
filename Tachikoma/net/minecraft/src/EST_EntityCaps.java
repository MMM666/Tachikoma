package net.minecraft.src;

import static net.minecraft.src.MMM_IModelCaps.*;
import java.util.HashMap;
import java.util.Map;

public class EST_EntityCaps implements MMM_IModelCaps {

	public static EST_EntityCaps instance = new EST_EntityCaps();
	public EntityPlayer player;
	
	public static Map<String, Integer> fcaps= new HashMap<String, Integer>();


	static {
		fcaps.put("Items", caps_Items);
		fcaps.put("Items", caps_Items);
		fcaps.put("Items", caps_Items);
		fcaps.put("Items", caps_Items);
		fcaps.put("Items", caps_Items);
		fcaps.put("Items", caps_Items);
		fcaps.put("Items", caps_Items);
		fcaps.put("Items", caps_Items);
		fcaps.put("Items", caps_Items);
		fcaps.put("Items", caps_Items);
		fcaps.put("Items", caps_Items);
		fcaps.put("Items", caps_Items);
		fcaps.put("Items", caps_Items);
		fcaps.put("Items", caps_Items);
		fcaps.put("Items", caps_Items);
		fcaps.put("Items", caps_Items);
		fcaps.put("Items", caps_Items);
		fcaps.put("Items", caps_Items);
	}

	@Override
	public Map<String, Integer> getModelCaps() {
		return fcaps;
	}

	@Override
	public Object getCapsValue(int pIndex, Object... pArg) {
		player.isBlocking();
		return null;
	}

	@Override
	public boolean setCapsValue(int pIndex, Object... pArg) {
		// TODO Auto-generated method stub
		return false;
	}

}
