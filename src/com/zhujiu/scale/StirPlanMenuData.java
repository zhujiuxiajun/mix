package com.zhujiu.scale;

import java.util.Date;

public class StirPlanMenuData {
	private String id;
	private String buildCode;// ��λ���
	private String buildDesc;// ��λ����
	private Float quantity;// ����
	private Integer status;// ״̬
	private String planerName;// �ƻ���
	private Date planTime;// �ƻ�ʱ��
	private String TechnicianName;// ����Ա
	private Integer tempOrder;// ��ʱ˳��
	private Integer poureSeq;// ����˳��
	private String deliveryMan;// ������
	private Float deliveryQty;// ���ⷽ��
	private Date deliveryTime;// ����ʱ��
	private Float factQty;// ��������
	private String tanker;// �޳���
	private String taskId;// taskId
	private String workAreaName;// ����
	private String teamName;// Э����λ
	private String strengthLevel;// ����������
	private Date startTime;// Ԥ�ƽ�����ʼʱ��/������ʼʱ��
	private Date endTime;// Ԥ�ƽ�������ʱ��/��������ʱ��
	private String pouringWay;// ������ʽ
	private String failPoure;// δ�߱�ԭ��
	private Float addQty;// ��������
	private String blendsMan;// �����Ա
	private Date blendsTime;// ���ʱ��
	private String checkMan;// �����
	private Date checkTime;// ���ʱ��
	private String checkNotes;// ������
	private Integer poureable;// �Ƿ�߱���������
	private String notes;

	private String buildPartId;// ��λID

	private Date endExpectTime;// Ԥ�ƽ�������ʱ�䡾��ʱ�á�

	private Integer makeMixing;

	private Float sjQty;// �������

	private Date finishTime;

	private String planerId;

	private String autoDeliveryMan;// �ӿڻ�ȡ

	private Integer isDispatch;// �Ƿ����

	private Integer poreType;// �ɿ׷�ʽ

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBuildCode() {
		return buildCode;
	}

	public void setBuildCode(String buildCode) {
		this.buildCode = buildCode;
	}

	public String getBuildDesc() {
		return buildDesc;
	}

	public void setBuildDesc(String buildDesc) {
		this.buildDesc = buildDesc;
	}

	public Float getQuantity() {
		return quantity;
	}

	public void setQuantity(Float quantity) {
		this.quantity = quantity;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getPlanerName() {
		return planerName;
	}

	public void setPlanerName(String planerName) {
		this.planerName = planerName;
	}

	public Date getPlanTime() {
		return planTime;
	}

	public void setPlanTime(Date planTime) {
		this.planTime = planTime;
	}

	public String getTechnicianName() {
		return TechnicianName;
	}

	public void setTechnicianName(String technicianName) {
		TechnicianName = technicianName;
	}

	public Integer getTempOrder() {
		return tempOrder;
	}

	public void setTempOrder(Integer tempOrder) {
		this.tempOrder = tempOrder;
	}

	public Integer getPoureSeq() {
		return poureSeq;
	}

	public void setPoureSeq(Integer poureSeq) {
		this.poureSeq = poureSeq;
	}

	public String getDeliveryMan() {
		return deliveryMan;
	}

	public void setDeliveryMan(String deliveryMan) {
		this.deliveryMan = deliveryMan;
	}

	public Float getDeliveryQty() {
		return deliveryQty;
	}

	public void setDeliveryQty(Float deliveryQty) {
		this.deliveryQty = deliveryQty;
	}

	public Date getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(Date deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public Float getFactQty() {
		return factQty;
	}

	public void setFactQty(Float factQty) {
		this.factQty = factQty;
	}

	public String getTanker() {
		return tanker;
	}

	public void setTanker(String tanker) {
		this.tanker = tanker;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getWorkAreaName() {
		return workAreaName;
	}

	public void setWorkAreaName(String workAreaName) {
		this.workAreaName = workAreaName;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getStrengthLevel() {
		return strengthLevel;
	}

	public void setStrengthLevel(String strengthLevel) {
		this.strengthLevel = strengthLevel;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getPouringWay() {
		return pouringWay;
	}

	public void setPouringWay(String pouringWay) {
		this.pouringWay = pouringWay;
	}

	public String getFailPoure() {
		return failPoure;
	}

	public void setFailPoure(String failPoure) {
		this.failPoure = failPoure;
	}

	public Float getAddQty() {
		return addQty;
	}

	public void setAddQty(Float addQty) {
		this.addQty = addQty;
	}

	public String getBlendsMan() {
		return blendsMan;
	}

	public void setBlendsMan(String blendsMan) {
		this.blendsMan = blendsMan;
	}

	public Date getBlendsTime() {
		return blendsTime;
	}

	public void setBlendsTime(Date blendsTime) {
		this.blendsTime = blendsTime;
	}

	public String getCheckMan() {
		return checkMan;
	}

	public void setCheckMan(String checkMan) {
		this.checkMan = checkMan;
	}

	public Date getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	public String getCheckNotes() {
		return checkNotes;
	}

	public void setCheckNotes(String checkNotes) {
		this.checkNotes = checkNotes;
	}

	public Integer getPoureable() {
		return poureable;
	}

	public void setPoureable(Integer poureable) {
		this.poureable = poureable;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getBuildPartId() {
		return buildPartId;
	}

	public void setBuildPartId(String buildPartId) {
		this.buildPartId = buildPartId;
	}

	public Date getEndExpectTime() {
		return endExpectTime;
	}

	public void setEndExpectTime(Date endExpectTime) {
		this.endExpectTime = endExpectTime;
	}

	public Integer getMakeMixing() {
		return makeMixing;
	}

	public void setMakeMixing(Integer makeMixing) {
		this.makeMixing = makeMixing;
	}

	public Float getSjQty() {
		return sjQty;
	}

	public void setSjQty(Float sjQty) {
		this.sjQty = sjQty;
	}

	public Date getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

	public String getPlanerId() {
		return planerId;
	}

	public void setPlanerId(String planerId) {
		this.planerId = planerId;
	}

	public String getAutoDeliveryMan() {
		return autoDeliveryMan;
	}

	public void setAutoDeliveryMan(String autoDeliveryMan) {
		this.autoDeliveryMan = autoDeliveryMan;
	}

	public Integer getIsDispatch() {
		return isDispatch;
	}

	public void setIsDispatch(Integer isDispatch) {
		this.isDispatch = isDispatch;
	}

	public Integer getPoreType() {
		return poreType;
	}

	public void setPoreType(Integer poreType) {
		this.poreType = poreType;
	}

}
