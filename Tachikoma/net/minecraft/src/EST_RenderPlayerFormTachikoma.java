package net.minecraft.src;

import static net.minecraft.src.MMM_IModelCaps.*;
import org.lwjgl.opengl.GL11;

public class EST_RenderPlayerFormTachikoma extends RenderPlayer {
	
	protected MMM_ModelBaseDuo modelMain;
	protected MMM_ModelBaseDuo modelArmor;
	protected MMM_TextureBox textureBox;
	


	public EST_RenderPlayerFormTachikoma() {
		super();
		// モデルの置き換え
		modelMain = new MMM_ModelBaseDuo(this);
		modelArmor = new MMM_ModelBaseDuo(this);
		textureBox = MMM_TextureManager.getTextureBox(mod_EST_Tachikoma.playerModel);
		modelMain.modelInner = textureBox.models[0];
		modelMain.textureInner = new String[] {textureBox.getTextureName(mod_EST_Tachikoma.playerColor), "", "", ""};
		modelMain.capsLink = modelArmor;
		modelArmor.modelInner = textureBox.models[1];
		modelArmor.modelOuter = textureBox.models[2];
		modelArmor.textureInner = new String[4];
		modelArmor.textureOuter = new String[4];
		mainModel = modelMain;
	}

	@Override
	protected int setArmorModel(EntityPlayer par1EntityPlayer, int par2, float par3) {
		// アーマーの表示設定
		modelArmor.renderParts = par2;
		ItemStack is = par1EntityPlayer.getCurrentArmor(par2);
		if (is != null && is.stackSize > 0) {
			modelArmor.showArmorParts(par2);
			modelArmor.textureInner[par2] = textureBox.getArmorTextureName(true, is);
			modelArmor.textureOuter[par2] = textureBox.getArmorTextureName(false, is);
			return is.isItemEnchanted() ? 15 : 1;
		}
		
		return -1;
	}

	@Override
	public void renderPlayer(EntityPlayer par1EntityPlayer, double par2, double par4, double par6, float par8, float par9) {
		modelMain.setRender(this);
		modelMain.setEntityCaps(EST_EntityCaps.instance);
		EST_EntityCaps.instance.player = par1EntityPlayer;
		
		float var10 = 1.0F;
		GL11.glColor3f(var10, var10, var10);
		ItemStack var11 = par1EntityPlayer.inventory.getCurrentItem();
		modelMain.setCapsValue(caps_heldItemRight, var11 != null ? 1 : 0);
		
		if (var11 != null && par1EntityPlayer.getItemInUseCount() > 0) {
			EnumAction var12 = var11.getItemUseAction();
			
			if (var12 == EnumAction.block) {
				modelMain.setCapsValue(caps_heldItemRight, 3);
			} else if (var12 == EnumAction.bow) {
				modelMain.setCapsValue(caps_aimedBow, true);
			}
		}
		
		modelMain.setCapsValue(caps_isSneak, par1EntityPlayer.isSneaking());
		double var14 = par4 - (double)textureBox.getYOffset();
		
//		if (par1EntityPlayer.isSneaking() && !(par1EntityPlayer instanceof EntityPlayerSP)) {
//			var14 -= 0.125D;
//		}
		
		super.doRenderLiving(par1EntityPlayer, par2, var14, par6, par8, par9);
		modelMain.setCapsValue(caps_aimedBow, false);
		modelMain.setCapsValue(caps_isSneak, false);
		modelMain.setCapsValue(caps_heldItemRight, 0);
	}

	@Override
	protected void renderSpecials(EntityPlayer par1EntityPlayer, float par2) {
		float var3 = 1.0F;
		GL11.glColor3f(var3, var3, var3);
//		super.renderEquippedItems(par1EntityPlayer, par2);
		renderArrowsStuckInEntity(par1EntityPlayer, par2);
		
		modelMain.renderItems(par1EntityPlayer, this);
/*
		ItemStack var4 = par1EntityPlayer.inventory.armorItemInSlot(3);

		if (var4 != null) {
			GL11.glPushMatrix();
			this.modelBipedMain.bipedHead.postRender(0.0625F);
			float var5;
			
			if (var4.getItem().itemID < 256) {
				if (RenderBlocks.renderItemIn3d(Block.blocksList[var4.itemID].getRenderType())) {
					var5 = 0.625F;
					GL11.glTranslatef(0.0F, -0.25F, 0.0F);
					GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
					GL11.glScalef(var5, -var5, -var5);
				}
				
				this.renderManager.itemRenderer.renderItem(par1EntityPlayer, var4, 0);
			} else if (var4.getItem().itemID == Item.skull.itemID) {
				var5 = 1.0625F;
				GL11.glScalef(var5, -var5, -var5);
				String var6 = "";
				
				if (var4.hasTagCompound() && var4.getTagCompound().hasKey("SkullOwner")) {
					var6 = var4.getTagCompound().getString("SkullOwner");
				}
				
				TileEntitySkullRenderer.skullRenderer.func_82393_a(-0.5F, 0.0F, -0.5F, 1, 180.0F, var4.getItemDamage(), var6);
			}
			
			GL11.glPopMatrix();
		}

        float var7;
        float var8;


        float var11;


        ItemStack var21 = par1EntityPlayer.inventory.getCurrentItem();

        if (var21 != null)
        {
            GL11.glPushMatrix();
            this.modelBipedMain.bipedRightArm.postRender(0.0625F);
            GL11.glTranslatef(-0.0625F, 0.4375F, 0.0625F);

            if (par1EntityPlayer.fishEntity != null)
            {
                var21 = new ItemStack(Item.stick);
            }

            EnumAction var23 = null;

            if (par1EntityPlayer.getItemInUseCount() > 0)
            {
                var23 = var21.getItemUseAction();
            }

            if (var21.itemID < 256 && RenderBlocks.renderItemIn3d(Block.blocksList[var21.itemID].getRenderType()))
            {
                var7 = 0.5F;
                GL11.glTranslatef(0.0F, 0.1875F, -0.3125F);
                var7 *= 0.75F;
                GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScalef(-var7, -var7, var7);
            }
            else if (var21.itemID == Item.bow.itemID)
            {
                var7 = 0.625F;
                GL11.glTranslatef(0.0F, 0.125F, 0.3125F);
                GL11.glRotatef(-20.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScalef(var7, -var7, var7);
                GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
            }
            else if (Item.itemsList[var21.itemID].isFull3D())
            {
                var7 = 0.625F;

                if (Item.itemsList[var21.itemID].shouldRotateAroundWhenRendering())
                {
                    GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
                    GL11.glTranslatef(0.0F, -0.125F, 0.0F);
                }

                if (par1EntityPlayer.getItemInUseCount() > 0 && var23 == EnumAction.block)
                {
                    GL11.glTranslatef(0.05F, 0.0F, -0.1F);
                    GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glRotatef(-60.0F, 0.0F, 0.0F, 1.0F);
                }

                GL11.glTranslatef(0.0F, 0.1875F, 0.0F);
                GL11.glScalef(var7, -var7, var7);
                GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
            }
            else
            {
                var7 = 0.375F;
                GL11.glTranslatef(0.25F, 0.1875F, -0.1875F);
                GL11.glScalef(var7, var7, var7);
                GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
                GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
            }

            float var10;
            int var27;
            float var28;

            if (var21.getItem().requiresMultipleRenderPasses())
            {
                for (var27 = 0; var27 <= 1; ++var27)
                {
                    int var26 = var21.getItem().getColorFromItemStack(var21, var27);
                    var28 = (float)(var26 >> 16 & 255) / 255.0F;
                    var10 = (float)(var26 >> 8 & 255) / 255.0F;
                    var11 = (float)(var26 & 255) / 255.0F;
                    GL11.glColor4f(var28, var10, var11, 1.0F);
                    this.renderManager.itemRenderer.renderItem(par1EntityPlayer, var21, var27);
                }
            }
            else
            {
                var27 = var21.getItem().getColorFromItemStack(var21, 0);
                var8 = (float)(var27 >> 16 & 255) / 255.0F;
                var28 = (float)(var27 >> 8 & 255) / 255.0F;
                var10 = (float)(var27 & 255) / 255.0F;
                GL11.glColor4f(var8, var28, var10, 1.0F);
                this.renderManager.itemRenderer.renderItem(par1EntityPlayer, var21, 0);
            }

            GL11.glPopMatrix();
        }
        */
    }

	@Override
	public void renderFirstPersonArm(EntityPlayer par1EntityPlayer) {
		float var2 = 1.0F;
		GL11.glColor3f(var2, var2, var2);
		modelMain.setCapsValue(caps_onGround, 0.0F);
		modelMain.setRotationAngles(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, par1EntityPlayer);
		
//		this.modelBipedMain.bipedRightArm.render(0.0625F);
	}

	@Override
	protected void renderArrowsStuckInEntity(EntityLiving par1EntityLiving, float par2) {
		MMM_Client.renderArrowsStuckInEntity(par1EntityLiving, par2, this, modelMain.modelInner);
	}

/*


	@Override
	protected void renderSpecials(EntityPlayer par1EntityPlayer, float par2) {
		// まんま
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
