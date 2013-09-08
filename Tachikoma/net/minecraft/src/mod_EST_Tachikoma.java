package net.minecraft.src;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.print.attribute.standard.MediaSize.ISO;

public class mod_EST_Tachikoma extends BaseMod {

	public static String[] cfg_comment = {
		"isSpiderForm = Replace SpiderForm.",
		"changeMobSize = Change Spider size is 1.0w x 1.7h",
		"LivingSound = Change Spider Living Sound.",
		"HurtSound = Change Spider Hurt Sound.",
		"DeathSound = Change Spider Death Sound.",
		"selectModels = using spider's textures.(first writen is default.)",
		"isRideSpider = can rideon Spider."
	};

//	@MLProp(info = "Replace SpiderForm.")
	public static boolean cfg_isSpiderForm = true;
//	@MLProp(info = "Change Spider size is 1.0w x 1.7h")
	public static boolean cfg_changeMobSize = false;
//	@MLProp
	public static String cfg_LivingSound = "mob.spider";
//	@MLProp
	public static String cfg_HurtSound = "mob.spider";
//	@MLProp
	public static String cfg_DeathSound = "mob.spiderdeath";
//	@MLProp(info = "PlayerModel replace is enabled when.")
//	public static String cfg_playerModel = "CV_Tachikoma";
//	@MLProp(info = "using spider's textures.(first writen is default.)")
	public static String cfg_selectModels = "CV_Tachikoma,OptCam_Tachikoma";
//	@MLProp
//	public static boolean isArrowsStuck = true;
//	@MLProp
	public static boolean cfg_isRideSpider = false;
	
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
		return "1.6.2-2";
	}

	@Override
	public void load() {
		// MMMLibのRevisionチェック
		MMM_Helper.checkRevision("5");
		MMM_Config.checkConfig(this.getClass());
		
		// タチコマ系のモデルを読み込み
		textures = cfg_selectModels.split(",");
		MMM_FileManager.getModFile("Tachikoma", "Tachikoma");
		MMM_TextureManager.instance.addSearch("Tachikoma", "/assets/minecraft/textures/entity/Tachikoma/", "EST_Model_");
		if (cfg_isSpiderForm) {
			// 置換え
			MMM_Helper.replaceEntityList(EntitySpider.class, EST_EntityTachikoma.class);
		}
	}

	@Override
	public void modsLoaded() {
		// デフォルトモデルの設定
		MMM_TextureManager.instance.setDefaultTexture(EST_EntityTachikoma.class, MMM_TextureManager.instance.getTextureBox(textures[0]));
	}

	@Override
	public void addRenderer(Map map) {
		try {
			if (cfg_isSpiderForm) {
				map.put(net.minecraft.src.EST_EntityTachikoma.class, new EST_RenderTachikoma());
				System.out.println("Tachikoma replace Spider renderer.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getRandomTexture() {
		return textures[(new Random()).nextInt(textures.length)];
	}

}
