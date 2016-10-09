package com.xiaoyu.shbookstore.domain;

import java.util.List;


/**
 * 发票信息
 */
public class InvoiceInfo {
	private List<InvoiceProperty> perlist;
	/**
	 * 
	 * 发票类型
	 *
	 */
	private String invoicetype;
	/**
	 * 发票Id
	 */
	private String id;


	/**
	 * 发票抬头
	 */
	private String title;

	/**
	 * 发票内容
	 */
	private String content;

	/**
	 * @roseuid 534B82140127
	 */
	public InvoiceInfo() {

	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public String getInvoicetype() {
		return invoicetype;
	}

	public void setInvoicetype(String invoicetype) {
		this.invoicetype = invoicetype;
	}

	public List<InvoiceProperty> getPerlist() {
		return perlist;
	}

	public void setPerlist(List<InvoiceProperty> perlist) {
		this.perlist = perlist;
	}
	
}
