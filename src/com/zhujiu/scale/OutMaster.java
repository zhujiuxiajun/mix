package com.zhujiu.scale;

import java.util.Date;

public class OutMaster {
	private String id;
    //ԭʼ����id
    private Integer deliveryId;
    //��λ����
    private String partName;
    //��ϱ�����
    private String RecipeNo;
    //���ⷽ��
    private Float deliveryQty;
    //����ʱ��
    private Date startTime;
    //����ʱ��
    private Long endTime;
    //�޳��� ֱ��ʹ�ñ��
    private String tanker;
    //������������ ֱ���ı�
    private String deliveryMan;
    //���⳵�����
    private Integer outCount;
    //��ǰ�ۼƷ���
    private Float endCount;
    //�ϴ�����ʱ��
    private Date updateTime;
    //����ͬ������ʽ����״̬  0 �� Ĭ�ϣ�-1 �� ͬ��ʧ�ܣ�1 ��ͬ���ɹ�
    private Integer syncStatus;
    //ͬ��ʱ�� ����ʽ��ĳ����
    private Date syncTime;
    //��ע
    private String notes;

    //----------���γ�  ����ͳ����Ϣ-------------
    //-------��ϱ���ƺ���
    //ʯ�ӣ�5-16�� ��λKg
    private Float rcpstone;
    //ʯ�ӣ�16-25�� ��λKg
    private Float rcpstone25;
    //ɰ ����ɰ  ��λKg
    private Float rcpsand;
    //ɰ ��Ȼɰ  ��λKg
    private Float rcpsandNature;
    //ˮ�� ��λKg
    private Float rcpcement;
    //��Ӽ� ��λKg
    private Float rcpadditive;
    //ˮ ��λKg
    private Float rcpwater;
    //��ú��
    private Float rcpflyash;
    //oth1
    private Float rcpoth1;
    //oth2
    private Float rcpoth2;

    //---------ʵ�ʺ���
    //ʯ�ӣ�5-16�� ��λKg
    private Float actualstone;
    //ʯ�ӣ�16-25�� ��λKg
    private Float actualstone25;
    //ɰ ����ɰ  ��λKg
    private Float actualsand;
    //ɰ ��Ȼɰ  ��λKg
    private Float actualsandNature;
    //ˮ�� ��λKg
    private Float actualcement;
    //��Ӽ� ��λKg
    private Float actualadditive;
    //ˮ ��λKg
    private Float actualwater;
    //��ú��
    private Float actualflyash;
    //oth1
    private Float actualoth1;
    //oth2
    private Float actualoth2;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getDeliveryId() {
		return deliveryId;
	}
	public void setDeliveryId(Integer deliveryId) {
		this.deliveryId = deliveryId;
	}
	public String getPartName() {
		return partName;
	}
	public void setPartName(String partName) {
		this.partName = partName;
	}
	public String getRecipeNo() {
		return RecipeNo;
	}
	public void setRecipeNo(String recipeNo) {
		RecipeNo = recipeNo;
	}
	public Float getDeliveryQty() {
		return deliveryQty;
	}
	public void setDeliveryQty(Float deliveryQty) {
		this.deliveryQty = deliveryQty;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Long getEndTime() {
		return endTime;
	}
	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}
	public String getTanker() {
		return tanker;
	}
	public void setTanker(String tanker) {
		this.tanker = tanker;
	}
	public String getDeliveryMan() {
		return deliveryMan;
	}
	public void setDeliveryMan(String deliveryMan) {
		this.deliveryMan = deliveryMan;
	}
	public Integer getOutCount() {
		return outCount;
	}
	public void setOutCount(Integer outCount) {
		this.outCount = outCount;
	}
	public Float getEndCount() {
		return endCount;
	}
	public void setEndCount(Float endCount) {
		this.endCount = endCount;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getSyncStatus() {
		return syncStatus;
	}
	public void setSyncStatus(Integer syncStatus) {
		this.syncStatus = syncStatus;
	}
	public Date getSyncTime() {
		return syncTime;
	}
	public void setSyncTime(Date syncTime) {
		this.syncTime = syncTime;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public Float getRcpstone() {
		return rcpstone;
	}
	public void setRcpstone(Float rcpstone) {
		this.rcpstone = rcpstone;
	}
	public Float getRcpstone25() {
		return rcpstone25;
	}
	public void setRcpstone25(Float rcpstone25) {
		this.rcpstone25 = rcpstone25;
	}
	public Float getRcpsand() {
		return rcpsand;
	}
	public void setRcpsand(Float rcpsand) {
		this.rcpsand = rcpsand;
	}
	public Float getRcpsandNature() {
		return rcpsandNature;
	}
	public void setRcpsandNature(Float rcpsandNature) {
		this.rcpsandNature = rcpsandNature;
	}
	public Float getRcpcement() {
		return rcpcement;
	}
	public void setRcpcement(Float rcpcement) {
		this.rcpcement = rcpcement;
	}
	public Float getRcpadditive() {
		return rcpadditive;
	}
	public void setRcpadditive(Float rcpadditive) {
		this.rcpadditive = rcpadditive;
	}
	public Float getRcpwater() {
		return rcpwater;
	}
	public void setRcpwater(Float rcpwater) {
		this.rcpwater = rcpwater;
	}
	public Float getRcpflyash() {
		return rcpflyash;
	}
	public void setRcpflyash(Float rcpflyash) {
		this.rcpflyash = rcpflyash;
	}
	public Float getRcpoth1() {
		return rcpoth1;
	}
	public void setRcpoth1(Float rcpoth1) {
		this.rcpoth1 = rcpoth1;
	}
	public Float getRcpoth2() {
		return rcpoth2;
	}
	public void setRcpoth2(Float rcpoth2) {
		this.rcpoth2 = rcpoth2;
	}
	public Float getActualstone() {
		return actualstone;
	}
	public void setActualstone(Float actualstone) {
		this.actualstone = actualstone;
	}
	public Float getActualstone25() {
		return actualstone25;
	}
	public void setActualstone25(Float actualstone25) {
		this.actualstone25 = actualstone25;
	}
	public Float getActualsand() {
		return actualsand;
	}
	public void setActualsand(Float actualsand) {
		this.actualsand = actualsand;
	}
	public Float getActualsandNature() {
		return actualsandNature;
	}
	public void setActualsandNature(Float actualsandNature) {
		this.actualsandNature = actualsandNature;
	}
	public Float getActualcement() {
		return actualcement;
	}
	public void setActualcement(Float actualcement) {
		this.actualcement = actualcement;
	}
	public Float getActualadditive() {
		return actualadditive;
	}
	public void setActualadditive(Float actualadditive) {
		this.actualadditive = actualadditive;
	}
	public Float getActualwater() {
		return actualwater;
	}
	public void setActualwater(Float actualwater) {
		this.actualwater = actualwater;
	}
	public Float getActualflyash() {
		return actualflyash;
	}
	public void setActualflyash(Float actualflyash) {
		this.actualflyash = actualflyash;
	}
	public Float getActualoth1() {
		return actualoth1;
	}
	public void setActualoth1(Float actualoth1) {
		this.actualoth1 = actualoth1;
	}
	public Float getActualoth2() {
		return actualoth2;
	}
	public void setActualoth2(Float actualoth2) {
		this.actualoth2 = actualoth2;
	}


	
}
