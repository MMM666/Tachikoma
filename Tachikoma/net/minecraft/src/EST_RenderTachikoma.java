package net.minecraft.src;

import static net.minecraft.src.MMM_IModelBiped.*;
import org.lwjgl.opengl.GL11;

public class EST_RenderTachikoma extends RenderSpider {

//	public static float modelHeight;
//	public static float modelWidth;
	public MMM_ModelArmors modelMain;
	
	public EST_RenderTachikoma() {
		super();
		modelMain = new MMM_ModelArmors(this);
		modelMain.isModelAlphablend = true;
		mainModel = modelMain;
		setRenderPassModel(modelMain);
//		modelHeight = ((EST_ModelTachikoma) mainModel).getHeight();
//		modelWidth = ((EST_ModelTachikoma) mainModel).getWidth();
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
			this.loadTexture(((EST_EntityTachikoma)par1EntitySpider).textureEye);
			float var4 = 1.0F;
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glDisable(GL11.GL_ALPHA_TEST);
			GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
			
			if (par1EntitySpider.getHasActivePotion()) {
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
			return 1;
		}
	}

	@Override
	public void doRender(Entity entity, double d, double d1, double d2, float f, float f1) {
		modelMain.modelArmorInner = ((EST_EntityTachikoma)entity).client.textureModel0;
		modelMain.setCapsValue(caps_aimedBow, ((EntityCreature) entity).entityToAttack != null);
		modelMain.setCapsValue(caps_isSneak, ((EntityCreature) entity).isSneaking());
		modelMain.setCapsValue(caps_isRiding, ((EntityCreature) entity).isRiding());
		modelMain.isAlphablend = true;
		
//		GL11.glEnable(3042 /* GL_BLEND */);
//		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		super.doRender(entity, d, d1 - 0.2D, d2, f, f1);
	}

}
