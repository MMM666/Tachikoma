package net.minecraft.src;

public class EST_Client {

	public MMM_ModelMultiBase textureModel0;
	public MMM_ModelMultiBase textureModel1;
	public MMM_ModelMultiBase textureModel2;

	public EntityLiving owner;


	public EST_Client(EntityLiving pentity) {
		owner = pentity;
	}

	public void setModel(MMM_TextureBox pTextureBox) {
		textureModel0 = pTextureBox.models[0];
		textureModel1 = pTextureBox.models[1];
		textureModel2 = pTextureBox.models[2];
	}

}
