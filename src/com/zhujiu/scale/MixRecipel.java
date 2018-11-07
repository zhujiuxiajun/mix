package com.zhujiu.scale;


public class MixRecipel {
    //部位名称
    private String partName;
    //
    private Long deliveryDate;
    //原始出库id
    private Integer deliveryId;
    //配合比名称
    private String RecipeNo;
    //石子（5-16） 单位Kg
    private Float stone;
    //石子（16-25） 单位Kg
    private Float stone25;
    //砂 机制砂  单位Kg
    private Float sand;
    //砂 天然砂  单位Kg
    private Float sandNature;
    //水泥 单位Kg
    private Float cement;
    //添加剂 单位Kg
    private Float additive;
    //水 单位Kg
    private Float water;
    //粉煤灰
    private Float flyash;
    //oth1
    private Float oth1;
    //oth2
    private Float oth2;
    //坍落度
    private String tld;
	public String getPartName() {
		return partName;
	}
	public void setPartName(String partName) {
		this.partName = partName;
	}
	public Long getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(Long deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public Integer getDeliveryId() {
		return deliveryId;
	}
	public void setDeliveryId(Integer deliveryId) {
		this.deliveryId = deliveryId;
	}
	public String getRecipeNo() {
		return RecipeNo;
	}
	public void setRecipeNo(String recipeNo) {
		RecipeNo = recipeNo;
	}
	public Float getStone() {
		return stone;
	}
	public void setStone(Float stone) {
		this.stone = stone;
	}
	public Float getStone25() {
		return stone25;
	}
	public void setStone25(Float stone25) {
		this.stone25 = stone25;
	}
	public Float getSand() {
		return sand;
	}
	public void setSand(Float sand) {
		this.sand = sand;
	}
	public Float getSandNature() {
		return sandNature;
	}
	public void setSandNature(Float sandNature) {
		this.sandNature = sandNature;
	}
	public Float getCement() {
		return cement;
	}
	public void setCement(Float cement) {
		this.cement = cement;
	}
	public Float getAdditive() {
		return additive;
	}
	public void setAdditive(Float additive) {
		this.additive = additive;
	}
	public Float getWater() {
		return water;
	}
	public void setWater(Float water) {
		this.water = water;
	}
	public Float getFlyash() {
		return flyash;
	}
	public void setFlyash(Float flyash) {
		this.flyash = flyash;
	}
	public Float getOth1() {
		return oth1;
	}
	public void setOth1(Float oth1) {
		this.oth1 = oth1;
	}
	public Float getOth2() {
		return oth2;
	}
	public void setOth2(Float oth2) {
		this.oth2 = oth2;
	}
	public String getTld() {
		return tld;
	}
	public void setTld(String tld) {
		this.tld = tld;
	}
    
    
    
}
