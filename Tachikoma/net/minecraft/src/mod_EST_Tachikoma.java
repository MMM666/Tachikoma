package net.minecraft.src;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.print.attribute.standard.MediaSize.ISO;

public class mod_EST_Tachikoma extends BaseMod {

	@MLProp(info = "Replace SpiderForm.")
	public static boolean isSpiderForm = true;
	@MLProp(info = "Replace PlayerForm.")
	public static boolean isPlayerForm = false;
	@MLProp(info = "Change Spider size is 1.0w x 1.7h")
	public static boolean changeMobSize = false;
	@MLProp
	public static String LivingSound = "mob.spider";
	@MLProp
	public static String HurtSound = "mob.spider";
	@MLProp
	public static String DeathSound = "mob.spiderdeath";
	@MLProp(info = "PlayerModel replace is enabled when.")
	public static String playerModel = "CV_Tachikoma";
	@MLProp(info = "PlayerModel color.")
	public static int playerColor = 0;
	@MLProp(info = "using spider's textures.")
	public static String selectModels = "CV_Tachikoma,OptCam_Tachikoma";
	@MLProp(info = "default spider's textures.")
	public static String defaultModel = "CV_Tachikoma";
	@MLProp
	public static boolean isArrowsStuck = true;
	
	public static String[] textures;



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
	public String getVersion() {
		return "1.5.2-1";
	}

	@Override
	public void load() {
		// MMMLibのRevisionチェック
		MMM_Helper.checkRevision("2");
		
		// タチコマ系のモデルを読み込み
		textures = selectModels.split(",");
		MMM_FileManager.getModFile("Tachikoma", "Tachikoma");
		MMM_TextureManager.addSearch("Tachikoma", "/mob/Tachikoma/", "EST_Model_");
		if (isSpiderForm) {
			// 置換え
			MMM_Helper.replaceEntityList(EntitySpider.class, EST_EntityTachikoma.class);
		}
	}

	@Override
	public void modsLoaded() {
		// デフォルトモデルの設定
		MMM_TextureManager.setDefaultTexture(EST_EntityTachikoma.class, MMM_TextureManager.getTextureBox(defaultModel));
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
