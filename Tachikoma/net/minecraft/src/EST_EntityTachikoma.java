package net.minecraft.src;

public class EST_EntityTachikoma extends EntitySpider implements MMM_ITextureEntity {

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
//		textureName = mod_EST_Tachikoma.getRandomTexture();
//		textureIndex = MMM_TextureManager.getStringToIndex(textureName);
//		color = MMM_TextureManager.getRandomContractColor(textureIndex, rand);
//		texture = MMM_TextureManager.getTextureName(textureName, color);
//		textureEye = MMM_TextureManager.getTextureName(textureName, 0x60 | color);

		
		textureName = mod_EST_Tachikoma.getRandomTexture();
		textureIndex = MMM_TextureManager.getIndexTextureBoxServer(this, textureName);
		color = MMM_TextureManager.getRandomContractColor(textureIndex, rand);
		
		if (MMM_Helper.isClient) {
			client = new EST_Client(this);
			client.setModel(MMM_TextureManager.getTextureBox(textureName));
		}
		
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
				textureIndex = ltextureindex;
				lflag = true;
			}
			if (lflag) {
				MMM_TextureManager.postGetTexturePack(this, new int[] { textureIndex });
			}
		} else {
			boolean laimedBow = getAITarget() != null || getEntityToAttack() != null;
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
		System.out.println(String.format("EST_RD_remort:%b", worldObj.isRemote));
		super.readEntityFromNBT(nbttagcompound);
		color = nbttagcompound.getByte("TextureColor");
		textureName = nbttagcompound.getString("TextureName");
		if (textureName == null || textureName.isEmpty()) {
			textureName = mod_EST_Tachikoma.defaultModel;
		}
		textureIndex = MMM_TextureManager.getIndexTextureBoxServer(this, textureName);
		// 獲得したインデックスで再定義
//		textureName = MMM_TextureManager.getTextureBoxServer(textureIndex).textureName;
		setTexturePackIndex(color, new int[] {textureIndex});
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


	/**
	 * 腕を上に上げる
	 */
	public boolean getAimedBow() {
		return getFlag(flags_aimedBow);
	}

	@Override
	public void initCreature() {
		super.initCreature();
		textureName = mod_EST_Tachikoma.getRandomTexture();
		textureIndex = MMM_TextureManager.getIndexTextureBoxServer(this, textureName);
		color = MMM_TextureManager.getRandomContractColor(textureIndex, rand);
		setTexturePackIndex(color, new int[] {textureIndex});
	}

	@Override
	public void setWorld(World par1World) {
		super.setWorld(par1World);
	}

	@Override
	public void setTexturePackIndex(int pColor, int[] pIndex) {
		// Server
		color = pColor;
		textureIndex = pIndex[0];
		MMM_TextureBoxServer lbox = MMM_TextureManager.getTextureBoxServer(textureIndex);
		textureName = lbox.textureName;
		dataWatcher.updateObject(19, (byte)color);
		dataWatcher.updateObject(20, textureIndex);
//		setSize(-1, -1);
		setSize(lbox.modelWidth, lbox.modelHeight);
	}

	@Override
	public void setTexturePackName(MMM_TextureBox[] pTextureBox) {
		// Client
		texture = MMM_TextureManager.getTextureName(pTextureBox[0], color);
		textureEye = MMM_TextureManager.getTextureName(pTextureBox[0], 0x60 | color);
		client.setModel(pTextureBox[0]);
	}

}
