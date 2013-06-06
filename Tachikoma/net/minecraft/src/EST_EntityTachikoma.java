package net.minecraft.src;

public class EST_EntityTachikoma extends EntitySpider implements MMM_ITextureEntity {

	/**
	 * 腕を上に上げているかのフラグ、バージョンアップ時にフラグが使われていないかを確認すること。
	 */
	public static int flags_aimedBow = 7;

	public int textureIndex[] = new int[1];
	public MMM_TextureBoxBase textureBox[] = new MMM_TextureBoxBase[1];
	public String textureEye;
	public int color;


	public EST_EntityTachikoma(World world) {
		super(world);
		
		String lname = mod_EST_Tachikoma.getRandomTexture();
//		textureIndex[0] = MMM_TextureManager.getIndexTextureBoxServer(this, lname);
		// ダミー設定
		textureBox[0] = MMM_TextureManager.instance.getTextureBox(lname);
		color = textureBox[0].getRandomContractColor(rand);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataWatcher.addObject(19, Byte.valueOf((byte)0xff));
		dataWatcher.addObject(20, Integer.valueOf(-1));
	}

	@Override
	public void initCreature() {
		super.initCreature();
		String lname = mod_EST_Tachikoma.getRandomTexture();
		textureIndex[0] = MMM_TextureManager.instance.getIndexTextureBoxServer(this, lname);
		textureBox[0] = MMM_TextureManager.instance.getTextureBoxServer(textureIndex[0]);
		color = textureBox[0].getRandomContractColor(rand);
		setTexturePackIndex(color, textureIndex);
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbttagcompound) {
		super.writeEntityToNBT(nbttagcompound);
		nbttagcompound.setByte("TextureColor", (byte)color);
		nbttagcompound.setString("TextureName", textureBox[0].textureName);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbttagcompound) {
		super.readEntityFromNBT(nbttagcompound);
		color = nbttagcompound.getByte("TextureColor");
		String lname = nbttagcompound.getString("TextureName");
		if (lname == null || lname.isEmpty()) {
			lname = mod_EST_Tachikoma.defaultModel;
		}
		textureIndex[0] = MMM_TextureManager.instance.getIndexTextureBoxServer(this, lname);
		setTexturePackIndex(color, textureIndex);
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
		return textureBox[0].getMountedYOffset();
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
			if (!worldObj.isRemote) {
				// RIDE-ON
				if (getAITarget() != entityplayer || getEntityToAttack() != entityplayer) {
					entityplayer.mountEntity(this);
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		
		if (worldObj.isRemote) {
			// Client
			boolean lflag = false;
			int lcolor = (int)dataWatcher.getWatchableObjectByte(19) & 0x00ff;
			int ltextureindex = dataWatcher.getWatchableObjectInt(20);
			if (color != lcolor) {
				color = lcolor;
				lflag = ((MMM_TextureBox)textureBox[0]).hasColor(lcolor);
			}
			if (textureIndex[0] != ltextureindex) {
				textureIndex[0] = ltextureindex;
				lflag = true;
			}
			if (lflag) {
				MMM_TextureManager.instance.postGetTexturePack(this, textureIndex);
			}
		} else {
			boolean laimedBow = getAITarget() != null || getEntityToAttack() != null;
			if (getAimedBow() != laimedBow) {
				setFlag(flags_aimedBow, laimedBow);
			}
		}
	}


	/**
	 * 腕を上に上げる
	 */
	public boolean getAimedBow() {
		return getFlag(flags_aimedBow);
	}

	@Override
	public void setWorld(World par1World) {
		super.setWorld(par1World);
	}

	@Override
	public void setTexturePackIndex(int pColor, int[] pIndex) {
		// Server
		color = pColor;
		textureIndex[0] = pIndex[0];
		textureBox[0] = MMM_TextureManager.instance.getTextureBoxServer(textureIndex[0]);
		dataWatcher.updateObject(19, (byte)color);
		dataWatcher.updateObject(20, textureIndex[0]);
//		setSize(-1, -1);
		setSize(textureBox[0].getWidth(), textureBox[0].getHeight());
	}

	@Override
	public void setTexturePackName(MMM_TextureBox[] pTextureBox) {
		// Client
		textureBox[0] = pTextureBox[0];
//		texture = MMM_TextureManager.getTextureName(pTextureBox[0], color);
//		textureEye = MMM_TextureManager.getTextureName(pTextureBox[0], 0x60 | color);
		texture = pTextureBox[0].getTextureName(color);
		textureEye = pTextureBox[0].getTextureName(0x60 | color);
	}

}
