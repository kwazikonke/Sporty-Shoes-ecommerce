package com.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="purchase_report")
public class PurchaseReport implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int reportId;
	private String reportProductName;
	private String reportUserEmail;
	private String reportDate;
	private int reportPrice;
	public PurchaseReport() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PurchaseReport(int reportId, String reportProductName, String reportUserEmail, String reportDate,
			int reportPrice) {
		super();
		this.reportId = reportId;
		this.reportProductName = reportProductName;
		this.reportUserEmail = reportUserEmail;
		this.reportDate = reportDate;
		this.reportPrice = reportPrice;
	}
	public int getReportId() {
		return reportId;
	}
	public void setReportId(int reportId) {
		this.reportId = reportId;
	}
	public String getReportProductName() {
		return reportProductName;
	}
	public void setReportProductName(String reportProductName) {
		this.reportProductName = reportProductName;
	}
	public String getReportUserEmail() {
		return reportUserEmail;
	}
	public void setReportUserEmail(String reportUserEmail) {
		this.reportUserEmail = reportUserEmail;
	}
	public String getReportDate() {
		return reportDate;
	}
	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}
	public int getReportPrice() {
		return reportPrice;
	}
	public void setReportPrice(int reportPrice) {
		this.reportPrice = reportPrice;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "PurchaseReport [reportId=" + reportId + ", reportProductName=" + reportProductName
				+ ", reportUserEmail=" + reportUserEmail + ", reportDate=" + reportDate + ", reportPrice=" + reportPrice
				+ "]";
	}
	
	
}
