package net.minecraft.src;

public class EST_EntityTachikoma extends EntitySpider {

	/**
	 * 腕を上に上げているかのフラグ、バージョンアップ時にフラグが使われていないかを確認すること。
	 */
	public static int flags_aimedBow = 7;

	public int textureIndex;
	public String textureEye;
	public String textureName;
	public int color;
	public EST_Client client;


	public EST_EntityTachikoma(World world) {
		super(world);
		textureName = "CV_Tachikoma";
		textureIndex = MMM_TextureManager.getStringToIndex(textureName);
		color = MMM_TextureManager.getRandomContractColor(textureIndex, rand);
		texture = MMM_TextureManager.getTextureName(textureName, color);
		textureEye = MMM_TextureManager.getTextureName(textureName, 0x60 | color);
		
		if (world.isRemote) {
			client = new EST_Client(this);
			client.setModel(textureName);
		}
		// TODO:マルチ判定しれ
//		if (mod_EST_Tachikoma.changeMobSize) {
//			setSize(EST_RenderTachikoma.modelWidth, EST_RenderTachikoma.modelHeight);
//		}
	}

	@Override
	public void onUpdate() {
		super.onUpdate();

		if (worldObj.isRemote) {
			// Client
			boolean lflag = false;
			int lcolor = (int)dataWatcher.getWatchableObjectByte(19) & 0x00ff;
			if (color != lcolor) {
				color = lcolor;
				lflag = MMM_TextureManager.getTextureBox(textureName).hasColor(lcolor);
			}
			int ltextureindex = dataWatcher.getWatchableObjectInt(20);
			if (textureIndex != ltextureindex) {
				String ls = MMM_TextureManager.getIndexToString(ltextureindex);
				if (ls != null) {
					if (!ls.isEmpty()) {
						lflag = true;
						textureIndex = ltextureindex;
						textureName = ls;
					}
				}
			}
			if (lflag) {
				texture = MMM_TextureManager.getTextureName(textureName, color);
				textureEye = MMM_TextureManager.getTextureName(textureName, 0x60 | color);
				client.setModel(textureName);
			}
		} else {
			int lcolor = (int)dataWatcher.getWatchableObjectByte(19) & 0x00ff;
			if (color != lcolor) {
				dataWatcher.updateObject(19, (byte)color);
			}
			int ltextureindex = dataWatcher.getWatchableObjectInt(20);
			if (textureIndex != ltextureindex) {
				dataWatcher.updateObject(20, textureIndex);
			}
			boolean laimedBow = getAITarget() != null;
			if (getAimedBow() != laimedBow) {
				setFlag(flags_aimedBow, laimedBow);
			}
		}
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataWatcher.addObject(19, Byte.valueOf((byte)color));
		dataWatcher.addObject(20, Integer.valueOf(textureIndex));
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbttagcompound) {
		super.writeEntityToNBT(nbttagcompound);
		nbttagcompound.setByte("TextureColor", (byte)color);
		nbttagcompound.setString("TextureName", textureName);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbttagcompound) {
		super.readEntityFromNBT(nbttagcompound);
		color = nbttagcompound.getByte("TextureColor");
		textureName = nbttagcompound.getString("TextureName");
		textureIndex = MMM_TextureManager.getStringToIndex(textureName);
		// サーバー側では要らない
//		texture = MMM_TextureManager.getTextureName(textureName, color);
//		textureEye = MMM_TextureManager.getTextureName(textureName, 0x60 | color);
	}

	@Override
	protected String getLivingSound() {
		return mod_EST_Tachikoma.LivingSound;
	}

	@Override
	protected String getHurtSound() {
		return mod_EST_Tachikoma.HurtSound;
	}

	@Override
	protected String getDeathSound() {
		return mod_EST_Tachikoma.DeathSound;
	}

	@Override
	public double getMountedYOffset() {
		// 搭乗高
		return super.getMountedYOffset() + 0.85F;
	}

	@Override
	public void updateRiderPosition() {
		// 搭乗位置
		if (riddenByEntity == null) {
			return;
		} else {
			double d2 = ((double) (renderYawOffset - 90F) * 3.1415926535897931D) / 180D;
			double d = Math.cos(d2) * 0.25D;
			double d1 = Math.sin(d2) * 0.25D;
			
			riddenByEntity.setPosition(posX + d, posY + getMountedYOffset()
					+ riddenByEntity.getYOffset(), posZ + d1);
			return;
		}
	}

	@Override
	protected Entity findPlayerToAttack() {
		// 搭乗者は索敵対象外
		Entity entity = super.findPlayerToAttack();
		if (entity == riddenByEntity) {
			entity = null;
		}
		return entity;
	}

	@Override
	public boolean interact(EntityPlayer entityplayer) {
		if (!super.interact(entityplayer)) {
			if (worldObj.isRemote) {
				// RIDE-ON
				if (getAITarget() != entityplayer) {
					entityplayer.mountEntity(this);
					return true;
				}
			}
		}
		return false;
	}


	/**
	 * 腕を上に上げる
	 */
	public boolean getAimedBow() {
		return getFlag(flags_aimedBow);
	}

}
