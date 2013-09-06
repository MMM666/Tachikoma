package net.minecraft.src;

import static net.minecraft.src.MMM_IModelCaps.*;
import org.lwjgl.opengl.GL11;

public class EST_RenderTachikoma extends RenderSpider {

	public MMM_ModelBaseSolo modelMain;
	
	public EST_RenderTachikoma() {
		super();
		modelMain = new MMM_ModelBaseSolo(this);
		modelMain.isModelAlphablend = true;
		mainModel = modelMain;
		setRenderPassModel(modelMain);
	}

	@Override
	protected float setSpiderDeathMaxRotation(EntitySpider entityspider) {
		return 90F;
	}

	@Override
	protected int setSpiderEyeBrightness(EntitySpider par1EntitySpider, int par2, float par3) {
		if (par2 != 0) {
			return -1;
		} else {
			ResourceLocation ls = ((EST_EntityTachikoma)par1EntitySpider).getTextureData().textures[0][1];
			if (ls != null) {
				func_110776_a(ls);
				float var4 = 1.0F;
				GL11.glEnable(GL11.GL_BLEND);
				GL11.glDisable(GL11.GL_ALPHA_TEST);
				GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
				
				if (par1EntitySpider.isInvisible()) {
					GL11.glDepthMask(false);
				} else {
					GL11.glDepthMask(true);
				}
				
				char var5 = 61680;
				int var6 = var5 % 65536;
				int var7 = var5 / 65536;
				OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit,
						(float) var6 / 1.0F, (float) var7 / 1.0F);
				GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
				GL11.glColor4f(1.0F, 1.0F, 1.0F, var4);
			}
			return 1;
		}
	}

	public void doRenderTachikoma(EST_EntityTachikoma entity, double d, double d1, double d2, float f, float f1) {
		if (entity instanceof MMM_ITextureEntity) {
			MMM_ITextureEntity ltentity = (MMM_ITextureEntity)entity;
			modelMain.model = ltentity.getTextureData().textureModel[0];
			modelMain.textures = ltentity.getTextures(0);
		}
		modelMain.setEntityCaps(entity.entityCaps);
		modelMain.setRender(this);
		modelMain.showAllParts();
		modelMain.lighting = entity.getBrightnessForRender(f);
		
//		modelMain.model = entity.textureData.textureModel[0];
//		modelMain.modelInner = MMM_TextureManager.instance.getTextureBox(entity.textureBox[0]).models[0];
		modelMain.setCapsValue(caps_aimedBow, entity.getAimedBow());
		modelMain.setCapsValue(caps_isSneak, entity.isSneaking());
		modelMain.setCapsValue(caps_isRiding, entity.isRiding());
		modelMain.setCapsValue(caps_heldItemRight, 0);
		modelMain.setCapsValue(caps_heldItemLeft, 0);
		modelMain.isAlphablend = true;
		modelMain.isRendering = true;
		
		super.doRender(entity, d, d1 - 0.2D, d2, f, f1);
	}

	@Override
	public void doRender(Entity entity, double d, double d1, double d2, float f, float f1) {
		doRenderTachikoma((EST_EntityTachikoma)entity, d, d1, d2, f, f1);
	}

	@Override
	protected void renderEquippedItems(EntityLivingBase par1EntityLiving, float par2) {
		// ハードポイントの描画
		modelMain.renderItems(par1EntityLiving, this);
		renderArrowsStuckInEntity(par1EntityLiving, par2);
	}

	@Override
	protected void renderArrowsStuckInEntity(EntityLivingBase par1EntityLiving, float par2) {
		MMM_Client.renderArrowsStuckInEntity(par1EntityLiving, par2, this, modelMain.model);
	}


	@Override
	protected void renderModel(EntityLivingBase par1EntityLiving, float par2,
			float par3, float par4, float par5, float par6, float par7) {
		if (!par1EntityLiving.isInvisible()) {
			modelMain.setArmorRendering(true);
		} else {
			modelMain.setArmorRendering(false);
		}
		// アイテムのレンダリング位置を獲得するためrenderを呼ぶ必要がある
		mainModel.render(par1EntityLiving, par2, par3, par4, par5, par6, par7);
	}

	@Override
	protected ResourceLocation func_110775_a(Entity par1Entity) {
		// テクスチャリソースを返すところだけれど、基本的に使用しない。
		return null;
//		return ((EST_EntityTachikoma)par1Entity).textureData.textures[0][0];
	}

}
