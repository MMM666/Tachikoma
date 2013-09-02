package net.minecraft.src;

public class EST_EntityTachikoma extends EntitySpider implements MMM_ITextureEntity {

	/**
	 * 腕を上に上げているかのフラグ、バージョンアップ時にフラグが使われていないかを確認すること。
	 */
	public static int flags_aimedBow = 7;

//	public int textureIndex[] = new int[1];
//	public MMM_TextureBoxBase textureBox[] = new MMM_TextureBoxBase[1];
//	public ResourceLocation textures[] = new ResourceLocation[] {null, null};
//	public int color;
	public MMM_IModelCaps entityCaps;

	public MMM_TextureData textureData;

	public EST_EntityTachikoma(World world) {
		super(world);
		
		String lname = mod_EST_Tachikoma.getRandomTexture();
//		textureIndex[0] = MMM_TextureManager.getIndexTextureBoxServer(this, lname);
		// ダミー設定
		entityCaps = new MMM_EntityCaps(this);
		textureData = new MMM_TextureData(this, entityCaps);
		textureData.setTextureInit(lname);
//		textureBox[0] = MMM_TextureManager.instance.getTextureBox(lname);
//		color = textureBox[0].getRandomContractColor(rand);
//		textures[0] = ((MMM_TextureBox)textureBox[0]).getTextureName(color);
//		textures[1] = ((MMM_TextureBox)textureBox[0]).getTextureName(color + MMM_TextureManager.tx_eye);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataWatcher.addObject(19, Byte.valueOf((byte)0xff));
		dataWatcher.addObject(20, Integer.valueOf(-1));
	}

	@Override
	public EntityLivingData func_110161_a(EntityLivingData par1EntityLivingData) {
		String lname = mod_EST_Tachikoma.getRandomTexture();
//		textureIndex[0] = MMM_TextureManager.instance.getIndexTextureBoxServer(this, lname);
//		textureBox[0] = MMM_TextureManager.instance.getTextureBoxServer(textureIndex[0]);
//		color = textureBox[0].getRandomContractColor(rand);
//		setTexturePackIndex(color, textureIndex);
		return super.func_110161_a(par1EntityLivingData);
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbttagcompound) {
		super.writeEntityToNBT(nbttagcompound);
		textureData.writeToNBT(nbttagcompound);
//		nbttagcompound.setByte("TextureColor", (byte)color);
//		nbttagcompound.setString("TextureName", textureBox[0].textureName);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbttagcompound) {
		super.readEntityFromNBT(nbttagcompound);
		textureData.readToNBT(nbttagcompound);
//		color = nbttagcompound.getByte("TextureColor");
//		String lname = nbttagcompound.getString("TextureName");
//		if (lname == null || lname.isEmpty()) {
//			lname = mod_EST_Tachikoma.textures[0];
//		}
//		textureIndex[0] = MMM_TextureManager.instance.getIndexTextureBoxServer(this, lname);
		setTexturePackIndex(textureData.getColor(), textureData.getTextureIndex());
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
		return textureData.getTextureBox()[0].getMountedYOffset(entityCaps);
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
			
			riddenByEntity.setPosition(posX + d,
					posY + getMountedYOffset() + riddenByEntity.getYOffset(), posZ + d1);
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
		if (mod_EST_Tachikoma.isRideSpider && !super.interact(entityplayer)) {
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
			int ltex[] = new int[] {ltextureindex};
			lflag = textureData.updateTexture(lcolor, ltex);
			if (lflag) {
				MMM_TextureManager.instance.postGetTexturePack(this, ltex);
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
		textureData.setTexturePackIndex(pColor, pIndex);
//		color = pColor;
//		textureIndex[0] = pIndex[0];
//		textureBox[0] = MMM_TextureManager.instance.getTextureBoxServer(textureIndex[0]);
		dataWatcher.updateObject(19, (byte)pColor);
		dataWatcher.updateObject(20, pIndex[0]);
//		if (mod_EST_Tachikoma.changeMobSize) {
//			setSize(textureBox[0].getWidth(entityCaps), textureBox[0].getHeight(entityCaps));
//		}
	}

	@Override
	public void setTexturePackName(MMM_TextureBox[] pTextureBox) {
		// Client
		textureData.setTexturePackName(pTextureBox);
//		textureBox[0] = pTextureBox[0];
//		textures[0] = pTextureBox[0].getTextureName(color);
//		textures[1] = pTextureBox[0].getTextureName(0x60 | color);
	}

	@Override
	public void setColor(int pColor) {
		textureData.setColor(pColor);
	}

	@Override
	public int getColor() {
		return textureData.getColor();
	}

	@Override
	public void setContract(boolean pContract) {
//		textureData.setContract(pContract);
	}

	@Override
	public boolean isContract() {
//		return textureData.isContract();
		return true;
	}

	@Override
	public void setTextureBox(MMM_TextureBoxBase[] pTextureBox) {
//		textureBox = pTextureBox;
		textureData.setTextureBox(pTextureBox);
	}

	@Override
	public MMM_TextureBoxBase[] getTextureBox() {
		return textureData.getTextureBox();
//		return textureBox;
	}

	@Override
	public void setTextureIndex(int[] pTextureIndex) {
//		textureIndex = pTextureIndex;
		textureData.setTextureIndex(pTextureIndex);
	}

	@Override
	public int[] getTextureIndex() {
		return textureData.getTextureIndex();
//		return textureIndex;
	}

	@Override
	public void setTextures(int pIndex, ResourceLocation[] pNames) {
		textureData.setTextures(pIndex, pNames);
//		if (pIndex == 0) textures = pNames;
	}

	@Override
	public ResourceLocation[] getTextures(int pIndex) {
		return textureData.getTextures(pIndex);
//		return pIndex == 0 ? textures : null;
	}

	@Override
	public MMM_TextureData getTextureData() {
		return textureData;
	}

}
