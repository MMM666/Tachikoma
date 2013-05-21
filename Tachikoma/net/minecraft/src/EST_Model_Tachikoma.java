// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;

import java.util.Random;
import org.lwjgl.opengl.GL11;

public class EST_Model_Tachikoma extends MMM_ModelMultiMMMBase {

	public MMM_ModelRenderer bipedHead;
	public MMM_ModelRenderer bipedBody;
	public MMM_ModelRenderer bipedRightArm;
	public MMM_ModelRenderer bipedLeftArm;
	public MMM_ModelRenderer bipedRightLeg;
	public MMM_ModelRenderer bipedLeftLeg;
	
	public MMM_ModelRenderer head1;
	public MMM_ModelRenderer head2;
	public MMM_ModelRenderer bodyup;
	public MMM_ModelRenderer RightLeg1;
	public MMM_ModelRenderer LeftLeg1;
	public MMM_ModelRenderer backpack;
	public MMM_ModelRenderer Antenna;
	public MMM_ModelRenderer WUnit;
	public MMM_ModelRenderer BUnitL;
	public MMM_ModelRenderer BUnitR;
	public MMM_ModelRenderer ArmBase;

	public boolean isWait;

	public EST_Model_Tachikoma() {
		this(0.0F);
	}
	public EST_Model_Tachikoma(float f) {
		this(f, 0.0F);
	}
	public EST_Model_Tachikoma(float f, float f1) {
		super(f, f1, 64, 32);
	}

	@Override
	public void initModel(float psize, float pyoffset) {
		// Arms
		bipedHead = new MMM_ModelRenderer(this, 0, 0);
		bipedHead.addBox(-3.5F, -3.5F, -3.5F, 7, 7, 7, psize - 1.7F);
		bipedHead.setRotationPoint(0F, -2F, -4F);
		bipedHead.addChild(HeadMount);
		bipedHead.addChild(HeadTop);
		HeadTop.setRotationPoint(0F, -7F, 0F);

		head1 = new MMM_ModelRenderer(this, 0, 0);
		head1.addBox(-3.5F, -3.5F, -3.5F, 7, 7, 7, psize - 1.9F);
		head1.setRotationPoint(4F, -2F, 1F);
		
		head2 = new MMM_ModelRenderer(this, 0, 0);
		head2.addBox(-3.5F, -3.5F, -3.5F, 7, 7, 7, psize - 1.9F);
		head2.setRotationPoint(-4F, -2F, 1F);
		
		bipedBody = new MMM_ModelRenderer(this, 0, 16);
		bipedBody.addBox(-4F, -2.5F, -4F, 8, 5, 8, psize + 1.2F);
		bipedBody.setRotationPoint(0F, 0F, 0F);
		
		bodyup = new MMM_ModelRenderer(this, 1, 17);
		bodyup.addBox(-3.5F, -3F, -3.5F, 7, 2, 7, psize + 1.2F);
		bodyup.setRotationPoint(0F, -0.5F, 0F);
		bipedBody.addChild(bodyup);
		
		Arms[0] = (new MMM_ModelRenderer(this)).setRotationPoint(-0.5F, 5F, 0F);
		Arms[1] = (new MMM_ModelRenderer(this)).setRotationPoint(0.5F, 5F, 0F);
		
		bipedRightArm = new MMM_ModelRenderer(this, 32, 23);
		bipedRightArm.addBox(-2F, -0.5F, -1F, 2, 7, 2, psize);
		bipedRightArm.setRotationPoint(-2F, -0.5F, 0F);// -6F);
		bipedRightArm.addChild(Arms[0]);
		
		bipedLeftArm = new MMM_ModelRenderer(this, 32, 23);
		bipedLeftArm.setMirror(true);
		bipedLeftArm.addBox(0F, -0.5F, -1F, 2, 7, 2, psize);
		bipedLeftArm.setRotationPoint(2F, 2.5F, 0F);// -6F);
		bipedLeftArm.addChild(Arms[1]);
		
		ArmBase = new MMM_ModelRenderer(this);
		ArmBase.setRotationPoint(0F, 0F, -6F);
		ArmBase.addChild(bipedRightArm);
		ArmBase.addChild(bipedLeftArm);
		
		bipedRightLeg = new MMM_ModelRenderer(this, 40, 19);
		bipedRightLeg.addBox(-1.5F, 0F, -1.5F, 3, 10, 3, psize);
		bipedRightLeg.setRotationPoint(-5F, 3F, -2F);
		
		bipedLeftLeg = new MMM_ModelRenderer(this, 40, 19);
		bipedLeftLeg.setMirror(true);
		bipedLeftLeg.addBox(-1.5F, 0F, -1.5F, 3, 10, 3, psize);
		bipedLeftLeg.setRotationPoint(5F, 3F, -2F);
		
		RightLeg1 = new MMM_ModelRenderer(this, 40, 19);
		RightLeg1.addBox(-1.5F, 0F, -1.5F, 3, 10, 3, psize);
		RightLeg1.setRotationPoint(-5F, 3F, 3F);
		
		LeftLeg1 = new MMM_ModelRenderer(this, 40, 19);
		LeftLeg1.setMirror(true);
		LeftLeg1.addBox(-1.5F, 0F, -1.5F, 3, 10, 3, psize);
		LeftLeg1.setRotationPoint(5F, 3F, 3F);
		
		backpack = new MMM_ModelRenderer(this, 32, 0);
		backpack.addBox(-3F, -7.8F, 6.5F, 6, 8, 6, psize + 1.3F);
		backpack.setRotationPoint(0F, -2.3F, 0F);
		backpack.setRotateAngleX(0.122F);
		bodyup.addChild(backpack);
		
		Antenna = new MMM_ModelRenderer(this, 0, 19);
		Antenna.addBox(-0.5F, 0F, -0.5F, 1, 4, 1, psize);
		Antenna.setRotationPoint(0F, -8F, 0F);
		bodyup.addChild(Antenna);
		
		WUnit = new MMM_ModelRenderer(this, 24, 16);
		WUnit.addBox(-1.5F, -1.5F, -3F, 3, 3, 3, psize - 0.5F);
		WUnit.setRotationPoint(0F, 3F, -4F);
		WUnit.setRotateAngleZ(0.785F);
		bodyup.addChild(WUnit);
		
		BUnitL = new MMM_ModelRenderer(this, 50, 0);
		BUnitL.addBox(4F, -6F, 7F, 1, 2, 2, psize);
		BUnitL.setRotationPoint(0F, -2.5F, 0F);
		BUnitL.setMirror(true);
		bodyup.addChild(BUnitL);
		
		BUnitR = new MMM_ModelRenderer(this, 50, 0);
		BUnitR.addBox(-5F, -6F, 7F, 1, 2, 2, psize);
		BUnitR.setRotationPoint(0F, -2.5F, 0F);
		bodyup.addChild(BUnitR);
		
		mainFrame = new MMM_ModelRenderer(this, 0, 0);
		mainFrame.setRotationPoint(0F, 8F + pyoffset, 0F);
		mainFrame.addChild(bipedHead);
		mainFrame.addChild(head1);
		mainFrame.addChild(head2);
		mainFrame.addChild(bipedBody);
		mainFrame.addChild(ArmBase);
		mainFrame.addChild(bipedRightLeg);
		mainFrame.addChild(bipedLeftLeg);
		mainFrame.addChild(RightLeg1);
		mainFrame.addChild(LeftLeg1);
	}

	@Override
	public void showAllParts() {
		bipedHead.showModel = true;
		head1.showModel = true;
		head2.showModel = true;
		bipedBody.showModel = true;
		ArmBase.showModel = true;
		bipedRightLeg.showModel = true;
		bipedLeftLeg.showModel = true;
		RightLeg1.showModel = true;
		LeftLeg1.showModel = true;
	}
	
	@Override
	public int showArmorParts(int parts, int index) {
		// 鎧の表示用
		boolean f;
		// 兜
		f = parts == 3 ? true : false;
		bipedHead.showModel = f;
		head1.showModel = f;
		head2.showModel = f;
		// 鎧
		f = parts == 2 ? true : false;
		bipedBody.showModel = f;
		ArmBase.showModel = f;
		// 脚甲
		f = parts == 1 ? true : false;
		bipedRightLeg.showModel = f;
		bipedLeftLeg.showModel = f;
		// 臑当
		f = parts == 0 ? true : false;
		RightLeg1.showModel = f;
		LeftLeg1.showModel = f;
		
		return -1;
	}

	@Override
	public float[] getArmorModelsSize() {
		return new float[] {0.1F, 0.5F};
	}

	@Override
	public void setRotationAngles(float par1, float par2, float pTicksExisted,
			float pHeadYaw, float pHeadPitch, float par6, MMM_IModelCaps pEntityCaps) {
		bipedHead.rotateAngleY = pHeadYaw / 57.29578F;
		bipedHead.rotateAngleX = pHeadPitch / 57.29578F;
		head1.rotateAngleY = pHeadYaw / 57.29578F - 0.785F;
		head2.rotateAngleY = pHeadYaw / 57.29578F + 0.785F;
		head1.rotateAngleX = head2.rotateAngleX = bipedHead.rotateAngleX;
		
		bipedRightArm.rotateAngleX = mh_cos(par1 * 0.6662F + 3.141593F) * 2.0F * par2 * 0.5F;// - 0.5F;
		bipedLeftArm.rotateAngleX = mh_cos(par1 * 0.6662F) * 2.0F * par2 * 0.5F;// - 0.5F;
		bipedRightArm.rotateAngleY = 0.0F;
		bipedLeftArm.rotateAngleY = 0.0F;
		bipedRightArm.rotateAngleZ = 0.0F;
		bipedLeftArm.rotateAngleZ = 0.0F;
		
		bipedRightLeg.rotateAngleX = LeftLeg1.rotateAngleX =
				mh_cos(par1 * 0.6662F) * 1.4F * par2;
		bipedLeftLeg.rotateAngleX = RightLeg1.rotateAngleX =
				mh_cos(par1 * 0.6662F + 3.141593F) * 1.4F * par2;
		
		bipedRightLeg.rotateAngleY = RightLeg1.rotateAngleY = 0.0F;
		bipedLeftLeg.rotateAngleY = LeftLeg1.rotateAngleY = 0.0F;
		
		bipedRightLeg.rotateAngleZ = RightLeg1.rotateAngleZ = 0.698F;
		bipedLeftLeg.rotateAngleZ = LeftLeg1.rotateAngleZ = -0.698F;
		
		LeftLeg1.rotateAngleX += 0.474F;
		RightLeg1.rotateAngleX += 0.474F;
		
		if (heldItemLeft != 0) {
			bipedLeftArm.rotateAngleX =
					bipedLeftArm.rotateAngleX * 0.5F - 0.3141593F * (float) heldItemLeft;
		}
		if (heldItemRight != 0) {
			bipedRightArm.rotateAngleX =
					bipedRightArm.rotateAngleX * 0.5F - 0.3141593F * (float) heldItemRight;
		}
		bipedRightArm.rotateAngleX -= 0.5F;
		bipedLeftArm.rotateAngleX -= 0.5F;
		
		float lonGround = Math.max(onGrounds[0], onGrounds[1]);
		if (lonGround > -9990F && !aimedBow) {
			// 腕振り
			float f6 = lonGround;
			bipedBody.rotateAngleY = mh_sin(mh_sqrt_float(f6) * 3.141593F * 2.0F) * 0.2F;
			
			bipedRightArm.rotationPointZ = mh_sin(bipedBody.rotateAngleY) * 4F;// -6F;
			bipedRightArm.rotationPointX = -mh_cos(bipedBody.rotateAngleY) * 4F + 2.5F;
			bipedLeftArm.rotationPointZ = -mh_sin(bipedBody.rotateAngleY) * 4F;// -6F;
			bipedLeftArm.rotationPointX = mh_cos(bipedBody.rotateAngleY) * 4F - 2.5F;
			
			bipedRightArm.rotateAngleY += bipedBody.rotateAngleY;
			bipedLeftArm.rotateAngleY += bipedBody.rotateAngleY;
			bipedLeftArm.rotateAngleX += bipedBody.rotateAngleY;
			
			f6 = 1.0F - lonGround;
			f6 *= f6;
			f6 *= f6;
			f6 = 1.0F - f6;
			float f7 = mh_sin(f6 * 3.141593F);
			float f8 = mh_sin(lonGround * 3.141593F)
					* -(bipedHead.rotateAngleX - 0.7F) * 0.75F;
			bipedRightArm.rotateAngleX -= (double) f7 * 1.2D + (double) f8;
			bipedRightArm.rotateAngleY += bipedBody.rotateAngleY * 2.0F;
			bipedRightArm.rotateAngleZ = mh_sin(lonGround * 3.141593F) * -0.4F;
		}
		
		if (isSneak) {
			// しゃがみ
			bipedHead.rotationPointY = head1.rotationPointY = head2.rotationPointY = 0.5F;
			bipedBody.rotationPointY = 4F;
			bodyup.rotateAngleX = -0.5F;
			bipedRightArm.rotationPointY = 6F;
			bipedLeftArm.rotationPointY = 6F;
			bipedRightLeg.rotationPointY = RightLeg1.rotationPointY = 5F;
			bipedLeftLeg.rotationPointY = LeftLeg1.rotationPointY = 5F;
			
			bipedBody.rotateAngleX = 0.5F;
			bipedRightArm.rotateAngleX += 0.4F;
			bipedLeftArm.rotateAngleX += 0.4F;
			bipedRightLeg.rotationPointZ = -3F;
			bipedLeftLeg.rotationPointZ = -3F;
			
			bipedRightLeg.rotateAngleZ += 0.3F;
			RightLeg1.rotateAngleZ += 0.3F;
			bipedLeftLeg.rotateAngleZ -= 0.3F;
			LeftLeg1.rotateAngleZ -= 0.3F;
		} else {
			// 通常立ち
			bipedHead.rotationPointY = head1.rotationPointY = head2.rotationPointY = -0.5F;
			bipedBody.rotationPointY = 3F;
			bodyup.rotateAngleX = 0.0F;
			bipedRightArm.rotationPointY = 5F;
			bipedLeftArm.rotationPointY = 5F;
			bipedRightLeg.rotationPointY = RightLeg1.rotationPointY = 4F;
			bipedLeftLeg.rotationPointY = LeftLeg1.rotationPointY = 4F;
			
			bipedBody.rotateAngleX = 0F;
			bipedRightLeg.rotationPointZ = -3F;
			bipedLeftLeg.rotationPointZ = -3F;
		}
		if (isRiding) {
			// 乗り物に乗っている
			bipedHead.rotationPointY = head1.rotationPointY = head2.rotationPointY = 1.5F;
			bipedBody.rotationPointY = 5F;
			bipedRightArm.rotationPointY = 7F;
			bipedLeftArm.rotationPointY = 7F;
			bipedRightLeg.rotationPointY = RightLeg1.rotationPointY = 7F;
			bipedLeftLeg.rotationPointY = LeftLeg1.rotationPointY = 7F;
			
			bipedRightArm.rotateAngleX = -0.6F;
			bipedLeftArm.rotateAngleX = -0.6F;
			
			bipedRightLeg.rotateAngleX = -1.4F;
			bipedLeftLeg.rotateAngleX = -1.4F;
			bipedRightLeg.rotateAngleY = 0.2F;
			bipedLeftLeg.rotateAngleY = -0.2F;
		}
		
		if (isWait) {
			// 待機状態の特別表示
			bipedRightArm.rotateAngleX = mh_sin(pTicksExisted * 0.067F) * 0.05F - 0.7F;
			bipedRightArm.rotateAngleY = 0.0F;
			bipedRightArm.rotateAngleZ = -0.4F;
			bipedLeftArm.rotateAngleX = mh_sin(pTicksExisted * 0.067F) * 0.05F - 0.7F;
			bipedLeftArm.rotateAngleY = 0.0F;
			bipedLeftArm.rotateAngleZ = 0.4F;
		} else {
			if (aimedBow) {
				// 弓構え
				float f6 = mh_sin(lonGround * 3.141593F);
				float f7 = mh_sin((1.0F - (1.0F - lonGround) * (1.0F - lonGround)) * 3.141593F);
				bipedRightArm.rotateAngleZ = 0.0F;
				bipedLeftArm.rotateAngleZ = 0.0F;
				bipedRightArm.rotateAngleY = -(0.1F - f6 * 0.6F) + bipedHead.rotateAngleY;
				bipedLeftArm.rotateAngleY = 0.1F - f6 * 0.6F + bipedHead.rotateAngleY;
				bipedRightArm.rotateAngleX = -1.570796F + bipedHead.rotateAngleX;
				bipedLeftArm.rotateAngleX = -1.570796F + bipedHead.rotateAngleX;
				bipedRightArm.rotateAngleX -= f6 * 1.2F - f7 * 0.4F;
				bipedLeftArm.rotateAngleX -= f6 * 1.2F - f7 * 0.4F;
				bipedRightArm.rotateAngleZ += mh_cos(pTicksExisted * 0.09F) * 0.05F + 0.05F;
				bipedLeftArm.rotateAngleZ -= mh_cos(pTicksExisted * 0.09F) * 0.05F + 0.05F;
				bipedRightArm.rotateAngleX += mh_sin(pTicksExisted * 0.067F) * 0.05F;
				bipedLeftArm.rotateAngleX -= mh_sin(pTicksExisted * 0.067F) * 0.05F;
			} else {
				// 通常
				bipedRightArm.rotateAngleZ += 0.5F;
				bipedLeftArm.rotateAngleZ -= 0.5F;
				bipedRightArm.rotateAngleZ += mh_cos(pTicksExisted * 0.09F) * 0.05F + 0.05F;
				bipedLeftArm.rotateAngleZ -= mh_cos(pTicksExisted * 0.09F) * 0.05F + 0.05F;
				bipedRightArm.rotateAngleX += mh_sin(pTicksExisted * 0.067F) * 0.05F;
				bipedLeftArm.rotateAngleX -= mh_sin(pTicksExisted * 0.067F) * 0.05F;
			}
		}

		// bipedRightArm.rotationPointZ = 0F;

	}

	@Override
	public void renderItems(MMM_IModelCaps pEntityCaps) {
		// 手持ちの表示
		GL11.glPushMatrix();
		
		int ldominant = MMM_ModelCapsHelper.getCapsValueInt(pEntityCaps, caps_dominantArm);
		// R
		Arms[0].loadMatrix().renderItems(this, pEntityCaps, false, ldominant);
		// L
//		Arms[1].loadMatrix().renderItems(this, false, 1);
		
		// 頭部装飾品
		boolean lcamo = MMM_ModelCapsHelper.getCapsValueBoolean(pEntityCaps, caps_isCamouflage);
		boolean lplant = MMM_ModelCapsHelper.getCapsValueBoolean(pEntityCaps, caps_isPlanter);
		if (lcamo || lplant) {
			HeadMount.loadMatrix();
			if (lplant) {
				HeadTop.loadMatrix().renderItemsHead(this, pEntityCaps);
			} else {
				HeadMount.loadMatrix().renderItemsHead(this, pEntityCaps);
			}
		}
		
		GL11.glPopMatrix();
		
	}

	@Override
	public void renderFirstPersonHand(MMM_IModelCaps pEntityCaps) {
		// TODO Auto-generated method stub
		
	}

	public float getHeight() {
		return 1.7F;
	}

	public float getWidth() {
		return 1.0F;
	}

	@Override
	public float getyOffset() {
		return 1.53F;
	}

	@Override
	public float getMountedYOffset() {
		return 1.0F;
	}

	public void equippedBlockPosition() {
		// 手持ちブロックの表示位置
		GL11.glTranslatef(0.0F, 0.1275F, -0.3125F);
	}

	public void equippedItemPosition3D() {
		// 手持ち３Dアイテムの表示位置
		GL11.glTranslatef(0.05F, 0.1000F, -0.30F);
	}

	public void equippedItemPosition() {
		// 手持ちアイテムの表示位置
		GL11.glTranslatef(0.05F, 0.1200F, -0.0875F);
	}

	public void equippedItemBow() {
		// 手持ち弓の表示位置
		GL11.glTranslatef(-0.05F, 0.085F, -0.4F);
	}

}
