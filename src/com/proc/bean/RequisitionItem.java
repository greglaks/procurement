package com.proc.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="RequisitionItem")
public class RequisitionItem implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8423805762949721423L;

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="RequisitionId",unique=true, nullable=false)
	private Long id;
	
	@Column(name="ShortCode", length=10)
	private String shortCode;
	
	@Column(name="Description", length=BeanUtil.DESCRIPTION_FIELD, nullable=true)
	private String description;
	
	@Column(name="UnitLink", length=BeanUtil.SHORT_CODE, nullable=false)
	private String unitLink;
	
	@Column(name="Rob", length=BeanUtil.ROB, nullable=false)
	private String rob;
	
	@Column(name="Quantity", nullable=false)
	private int quantity;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="Requisition")
	private Requisition requisition;

	public String getShortCode() {
		return shortCode;
	}
	public void setShortCode(String shortCode) {
		this.shortCode = shortCode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUnitLink() {
		return unitLink;
	}
	public void setUnitLink(String unitLink) {
		this.unitLink = unitLink;
	}
	public String getRob() {
		return rob;
	}
	public void setRob(String rob) {
		this.rob = rob;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Requisition getRequisition() {
		return requisition;
	}
	public void setRequisition(Requisition requisition) {
		this.requisition = requisition;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	
	
		
}
