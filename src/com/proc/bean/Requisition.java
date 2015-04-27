package com.proc.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name="Requisition")
public class Requisition implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1440915107310210356L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="RequisitionId",unique=true, nullable=false)
	private Long id;

	@Column(name="Priority", nullable=false)
	private int priority;
	
	@Column(name="Date", nullable=false)
	private Date date;
	
	@Column(name="Category", nullable=false, length=40)
	private String category;
	
	@Column(name="Description", nullable=true, length=100)
	private String description;
	
	@Column(name="Remarks", nullable=true, length=100)
	private String remarks;
	
	@Column(name="VesselRefNo", nullable=false, length=30)
	private String vesselRefNo;
	
	@Lob
	private byte[] file;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getVesselRefNo() {
		return vesselRefNo;
	}
	public void setVesselRefNo(String vesselRefNo) {
		this.vesselRefNo = vesselRefNo;
	}
	public byte[] getFile() {
		return file;
	}
	public void setFile(byte[] file) {
		this.file = file;
	}
	
	

}
