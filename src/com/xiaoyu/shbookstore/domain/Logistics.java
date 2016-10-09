package com.xiaoyu.shbookstore.domain;

import java.util.List;

public class Logistics {

	/**
	 * 物流追踪列表
	 */
	private List<String> list;
	/**
	 * 送货方式
	 */
	private String expressway;
	/**
	 * 运单编号
	 */
	private String logisticsid;
	/**
	 * 物流公司
	 */
	private String logisticscorp;
	
	public List<String> getList() {
		return list;
	}
	public void setList(List<String> list) {
		this.list = list;
	}
	public String getExpressway() {
		return expressway;
	}
	public void setExpressway(String expressway) {
		this.expressway = expressway;
	}
	public String getLogisticsid() {
		return logisticsid;
	}
	public void setLogisticsid(String logisticsid) {
		this.logisticsid = logisticsid;
	}
	public String getLogisticscorp() {
		return logisticscorp;
	}
	public void setLogisticscorp(String logisticscorp) {
		this.logisticscorp = logisticscorp;
	}
	@Override
	public String toString() {
		return "Logistics [list=" + list + ", expressway=" + expressway
				+ ", logisticsid=" + logisticsid + ", logisticscorp="
				+ logisticscorp + "]";
	}
	
}
