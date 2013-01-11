package net.minecraft.src;

public class EST_Client {

	public MMM_ModelBiped textureModel0;
	public MMM_ModelBiped textureModel1;
	public MMM_ModelBiped textureModel2;

	public EntityLiving owner;


	public EST_Client(EntityLiving pentity) {
		owner = pentity;
	}

	public void setModel(String pTextureName) {
		MMM_TextureBox ltb = MMM_TextureManager.getTextureBox(pTextureName);
		textureModel0 = ltb.models[0];
		textureModel1 = ltb.models[1];
		textureModel2 = ltb.models[2];
	}
	
}
