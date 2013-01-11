package net.minecraft.src;

import org.lwjgl.opengl.GL11;

public class EST_RenderPlayerFormTachikoma extends RenderPlayer {
/*
//	protected ModelBiped modelBipedMain;
//	protected ModelBiped modelArmorChestplate;
//	protected ModelBiped modelArmor;
	
	protected MMM_ModelArmors modelMain;
	protected MMM_ModelArmors modelArmor;

	public EST_RenderPlayerFormTachikoma() {
		super();

		// ƒ‚ƒfƒ‹‚Ì’u‚«Š·‚¦
		modelBipedMain = new EST_ModelTachikoma(0.0F);
		modelArmorChestplate = new EST_ModelTachikoma(1.0F);
		modelArmor = new EST_ModelTachikoma(0.5F);
		mainModel = modelBipedMain;

		try {
			ModLoader.setPrivateValue(RenderPlayer.class, this, 0,
					modelBipedMain);
			ModLoader.setPrivateValue(RenderPlayer.class, this, 1,
					modelArmorChestplate);
			ModLoader.setPrivateValue(RenderPlayer.class, this, 2, modelArmor);
		} catch (Exception exception) {
		}
	}

	@Override
	protected int setArmorModel(EntityPlayer entityplayer, int i, float f) {
		// –Ê“|‚¾‚©‚çŠZ‚Í”ñ•\Ž¦
		return -1;
	}

	@Override
	public void renderPlayer(EntityPlayer entityplayer, double d, double d1,
			double d2, float f, float f1) {

		if (!mod_EST_Tachikoma.playerSkin.isEmpty()) {
			entityplayer.skinUrl = null;
			entityplayer.texture = mod_EST_Tachikoma.playerSkin;
		}
		modelArmorChestplate.isRiding = modelArmor.isRiding = modelBipedMain.isRiding = entityplayer
				.isRiding();
		if (entityplayer.isRiding()) {
			d1 += 0.5D;
		}
		super.renderPlayer(entityplayer, d, d1 - 0.2D, d2, f, f1);
		modelArmorChestplate.isRiding = modelArmor.isRiding = modelBipedMain.isRiding = false;
		;
	}

	@Override
	protected void renderSpecials(EntityPlayer par1EntityPlayer, float par2) {
		// ‚Ü‚ñ‚Ü
		// super.renderEquippedItems(par1EntityPlayer, par2);
		ItemStack itemstack = par1EntityPlayer.inventory.armorItemInSlot(3);

		if (itemstack != null && itemstack.getItem().shiftedIndex < 256) {
			GL11.glPushMatrix();
			modelBipedMain.bipedHead.postRender(0.0625F);

			if (RenderBlocks.renderItemIn3d(Block.blocksList[itemstack.itemID]
					.getRenderType())) {
				float f = 0.625F;
				GL11.glTranslatef(0.0F, -0.25F, 0.0F);
				GL11.glRotatef(180F, 0.0F, 1.0F, 0.0F);
				GL11.glScalef(f, -f, f);
			}

			renderManager.itemRenderer.renderItem(par1EntityPlayer, itemstack,
					0);
			GL11.glPopMatrix();
		}

		if (par1EntityPlayer.username.equals("deadmau5")
				&& loadDownloadableImageTexture(par1EntityPlayer.skinUrl, null)) {
			for (int i = 0; i < 2; i++) {
				float f1 = (par1EntityPlayer.prevRotationYaw + (par1EntityPlayer.rotationYaw - par1EntityPlayer.prevRotationYaw)
						* par2)
						- (par1EntityPlayer.prevRenderYawOffset + (par1EntityPlayer.renderYawOffset - par1EntityPlayer.prevRenderYawOffset)
								* par2);
				float f2 = par1EntityPlayer.prevRotationPitch
						+ (par1EntityPlayer.rotationPitch - par1EntityPlayer.prevRotationPitch)
						* par2;
				GL11.glPushMatrix();
				GL11.glRotatef(f1, 0.0F, 1.0F, 0.0F);
				GL11.glRotatef(f2, 1.0F, 0.0F, 0.0F);
				GL11.glTranslatef(0.375F * (float) (i * 2 - 1), 0.0F, 0.0F);
				GL11.glTranslatef(0.0F, -0.375F, 0.0F);
				GL11.glRotatef(-f2, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(-f1, 0.0F, 1.0F, 0.0F);
				float f7 = 1.333333F;
				GL11.glScalef(f7, f7, f7);
				modelBipedMain.renderEars(0.0625F);
				GL11.glPopMatrix();
			}
		}

		if (loadDownloadableImageTexture(par1EntityPlayer.playerCloakUrl, null)) {
			GL11.glPushMatrix();
			GL11.glTranslatef(0.0F, 0.0F, 0.125F);
			double d = (par1EntityPlayer.field_20066_r + (par1EntityPlayer.field_20063_u - par1EntityPlayer.field_20066_r)
					* (double) par2)
					- (par1EntityPlayer.prevPosX + (par1EntityPlayer.posX - par1EntityPlayer.prevPosX)
							* (double) par2);
			double d1 = (par1EntityPlayer.field_20065_s + (par1EntityPlayer.field_20062_v - par1EntityPlayer.field_20065_s)
					* (double) par2)
					- (par1EntityPlayer.prevPosY + (par1EntityPlayer.posY - par1EntityPlayer.prevPosY)
							* (double) par2);
			double d2 = (par1EntityPlayer.field_20064_t + (par1EntityPlayer.field_20061_w - par1EntityPlayer.field_20064_t)
					* (double) par2)
					- (par1EntityPlayer.prevPosZ + (par1EntityPlayer.posZ - par1EntityPlayer.prevPosZ)
							* (double) par2);
			float f10 = par1EntityPlayer.prevRenderYawOffset
					+ (par1EntityPlayer.renderYawOffset - par1EntityPlayer.prevRenderYawOffset)
					* par2;
			double d3 = MathHelper.sin((f10 * (float) Math.PI) / 180F);
			double d4 = -MathHelper.cos((f10 * (float) Math.PI) / 180F);
			float f12 = (float) d1 * 10F;

			if (f12 < -6F) {
				f12 = -6F;
			}

			if (f12 > 32F) {
				f12 = 32F;
			}

			float f13 = (float) (d * d3 + d2 * d4) * 100F;
			float f14 = (float) (d * d4 - d2 * d3) * 100F;

			if (f13 < 0.0F) {
				f13 = 0.0F;
			}

			float f15 = par1EntityPlayer.prevCameraYaw
					+ (par1EntityPlayer.cameraYaw - par1EntityPlayer.prevCameraYaw)
					* par2;
			f12 += MathHelper
					.sin((par1EntityPlayer.prevDistanceWalkedModified + (par1EntityPlayer.distanceWalkedModified - par1EntityPlayer.prevDistanceWalkedModified)
							* par2) * 6F)
					* 32F * f15;

			if (par1EntityPlayer.isSneaking()) {
				f12 += 25F;
			}

			GL11.glRotatef(6F + f13 / 2.0F + f12, 1.0F, 0.0F, 0.0F);
			GL11.glRotatef(f14 / 2.0F, 0.0F, 0.0F, 1.0F);
			GL11.glRotatef(-f14 / 2.0F, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(180F, 0.0F, 1.0F, 0.0F);
			modelBipedMain.renderCloak(0.0625F);
			GL11.glPopMatrix();
		}

		ItemStack itemstack1 = par1EntityPlayer.inventory.getCurrentItem();

		if (itemstack1 != null) {
			GL11.glPushMatrix();
			modelBipedMain.bipedRightArm.postRender(0.0625F);
			GL11.glTranslatef(-0.0625F, 0.4375F, 0.0625F);
			// GL11.glTranslatef(-0.0625F, 0.4375F + 0.1F, 0.0625F - 0.2F);

			if (par1EntityPlayer.fishEntity != null) {
				itemstack1 = new ItemStack(Item.stick);
			}

			EnumAction enumaction = null;

			if (par1EntityPlayer.getItemInUseCount() > 0) {
				enumaction = itemstack1.getItemUseAction();
			}

			if (itemstack1.itemID < 256
					&& RenderBlocks
							.renderItemIn3d(Block.blocksList[itemstack1.itemID]
									.getRenderType())) {
				((EST_ModelTachikoma) mainModel).equippedBlockPosition();
				float f3 = 0.5F;
				GL11.glTranslatef(0.0F, 0.1875F, -0.3125F);
				f3 *= 0.75F;
				GL11.glRotatef(20F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(45F, 0.0F, 1.0F, 0.0F);
				GL11.glScalef(f3, -f3, f3);
			}
			// else if (itemstack1.itemID == Item.bow.shiftedIndex)
			else if (itemstack1.getItem() instanceof ItemBow) {
				((EST_ModelTachikoma) mainModel).equippedItemBow();
				float f4 = 0.625F;
				GL11.glTranslatef(0.0F, 0.125F, 0.3125F);
				GL11.glRotatef(-20F, 0.0F, 1.0F, 0.0F);
				GL11.glScalef(f4, -f4, f4);
				GL11.glRotatef(-100F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(45F, 0.0F, 1.0F, 0.0F);
			} else if (Item.itemsList[itemstack1.itemID].isFull3D()) {
				((EST_ModelTachikoma) mainModel).equippedItemPosition3D();
				float f5 = 0.625F;

				if (Item.itemsList[itemstack1.itemID]
						.shouldRotateAroundWhenRendering()) {
					GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
					GL11.glTranslatef(0.0F, -0.125F, 0.0F);
				}

				if (par1EntityPlayer.getItemInUseCount() > 0
						&& enumaction == EnumAction.block) {
					GL11.glTranslatef(0.05F, 0.0F, -0.1F);
					GL11.glRotatef(-50F, 0.0F, 1.0F, 0.0F);
					GL11.glRotatef(-10F, 1.0F, 0.0F, 0.0F);
					GL11.glRotatef(-60F, 0.0F, 0.0F, 1.0F);
				}

				GL11.glTranslatef(0.0F, 0.1875F, 0.0F);
				GL11.glScalef(f5, -f5, f5);
				GL11.glRotatef(-100F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(45F, 0.0F, 1.0F, 0.0F);
			} else {
				((EST_ModelTachikoma) mainModel).equippedItemPosition();
				float f6 = 0.375F;
				GL11.glTranslatef(0.25F, 0.1875F, -0.1875F);
				GL11.glScalef(f6, f6, f6);
				GL11.glRotatef(60F, 0.0F, 0.0F, 1.0F);
				GL11.glRotatef(-90F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(20F, 0.0F, 0.0F, 1.0F);
			}

			if (itemstack1.getItem().func_46058_c()) {
				for (int j = 0; j <= 1; j++) {
					int k = itemstack1.getItem().getColorFromDamage(
							itemstack1.getItemDamage(), j);
					float f8 = (float) (k >> 16 & 0xff) / 255F;
					float f9 = (float) (k >> 8 & 0xff) / 255F;
					float f11 = (float) (k & 0xff) / 255F;
					GL11.glColor4f(f8, f9, f11, 1.0F);
					renderManager.itemRenderer.renderItem(par1EntityPlayer,
							itemstack1, j);
				}
			} else {
				renderManager.itemRenderer.renderItem(par1EntityPlayer,
						itemstack1, 0);
			}

			GL11.glPopMatrix();
		}
	}
*/
}
