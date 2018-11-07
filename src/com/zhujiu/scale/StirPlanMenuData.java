package com.zhujiu.scale;

import java.util.Date;

public class StirPlanMenuData {
	private String id;
	private String buildCode;// 部位编号
	private String buildDesc;// 部位描述
	private Float quantity;// 数量
	private Integer status;// 状态
	private String planerName;// 计划人
	private Date planTime;// 计划时间
	private String TechnicianName;// 技术员
	private Integer tempOrder;// 临时顺序
	private Integer poureSeq;// 浇筑顺序
	private String deliveryMan;// 出库人
	private Float deliveryQty;// 出库方量
	private Date deliveryTime;// 出库时间
	private Float factQty;// 浇筑方量
	private String tanker;// 罐车号
	private String taskId;// taskId
	private String workAreaName;// 工区
	private String teamName;// 协作单位
	private String strengthLevel;// 混凝土类型
	private Date startTime;// 预计浇筑开始时间/浇筑开始时间
	private Date endTime;// 预计浇筑结束时间/浇筑结束时间
	private String pouringWay;// 浇筑方式
	private String failPoure;// 未具备原因
	private Float addQty;// 补方数量
	private String blendsMan;// 配比人员
	private Date blendsTime;// 配比时间
	private String checkMan;// 审核人
	private Date checkTime;// 审核时间
	private String checkNotes;// 审核意见
	private Integer poureable;// 是否具备浇筑条件
	private String notes;

	private String buildPartId;// 部位ID

	private Date endExpectTime;// 预计浇筑结束时间【临时用】

	private Integer makeMixing;

	private Float sjQty;// 设计数量

	private Date finishTime;

	private String planerId;

	private String autoDeliveryMan;// 接口获取

	private Integer isDispatch;// 是否调度

	private Integer poreType;// 成孔方式

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
