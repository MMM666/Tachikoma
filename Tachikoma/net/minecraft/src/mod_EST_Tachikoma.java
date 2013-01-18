package net.minecraft.src;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.print.attribute.standard.MediaSize.ISO;

public class mod_EST_Tachikoma extends BaseMod {

//	public static List<String> texturelist = new ArrayList<String>();
	public static String entityName = "Spider";
	public static String[] textures;

	@MLProp(info = "Replace SpiderForm.")
	public static boolean isSpiderForm = true;
	@MLProp(info = "Replace PlayerForm.")
	public static boolean isPlayerForm = false;
//	@MLProp(info = "To enable OptCam.")
//	public static boolean isSpider_OptCam = false;
	@MLProp(info = "Change Spider size is 1.0w x 1.7h")
	public static boolean changeMobSize = false;
	@MLProp
	public static String LivingSound = "mob.spider";
	@MLProp
	public static String HurtSound = "mob.spider";
	@MLProp
	public static String DeathSound = "mob.spiderdeath";
	@MLProp(info = "PlayerSkin replace is enabled when.")
	public static String playerSkin = "/mob/Tachikoma/CV_Tachikoma/Tachikoma_3b.png";
	@MLProp(info = "using textures.")
	public static String selectModels = "CV_Tachikoma,OptCam_Tachikoma";
	
	
	@Override
	public String getVersion() {
		return "1.4.7-1";
	}

	@Override
	public String getName() {
		return "Tachikoma";
	}

	@Override
	public String getPriorities() {
		// MMMLibを要求
		return "required-after:mod_MMM_MMMLib";
	}

	@Override
	public void load() {
		// タチコマ系のモデルを読み込み
		textures = selectModels.split(",");
		MMM_FileManager.getModFile("Tachikoma", "Tachikoma");
		MMM_TextureManager.addSearch("Tachikoma", "/mob/Tachikoma/", "EST_Model_");
	}

	@Override
	public void modsLoaded() {
		if (isSpiderForm) {
			// 置換え
			Entity entity = EntityList.createEntityByName(entityName, null);
			int id = EntityList.getEntityID(entity);
			ModLoader.registerEntityID(EST_EntityTachikoma.class, entityName, id);
			
			// バイオームのスポーンリストを置き換え
			for (int i = 0; i < BiomeGenBase.biomeList.length; i++) {
				if (BiomeGenBase.biomeList[i] != null) {
					List<SpawnListEntry> list1 = BiomeGenBase.biomeList[i].spawnableMonsterList;
					for (int j = 0; j < list1.size(); j++) {
						if (list1.get(j).entityClass == entity.getClass()) {
							list1.get(j).entityClass = EST_EntityTachikoma.class;
						}
					}
				}
			}
			System.out.println("Tachikoma replace " + entityName + " entity.");
		}
	}

	@Override
	public void addRenderer(Map map) {
		try {
			if (isSpiderForm) {
				map.put(net.minecraft.src.EST_EntityTachikoma.class, new EST_RenderTachikoma());
				System.out.println("Tachikoma replace Spider renderer.");
			}
			
			if (isPlayerForm) {
				map.put(net.minecraft.src.EntityPlayerSP.class, new EST_RenderPlayerFormTachikoma());
				System.out.println("Tachikoma replace Player renderer.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getRandomTexture() {
		return textures[(new Random()).nextInt(textures.length)];
	}

}
