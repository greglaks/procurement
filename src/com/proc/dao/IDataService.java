package com.proc.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.proc.app.ProcurementUI;
import com.proc.app.component.RequisitionForm;
import com.proc.app.component.RequisitionItemTable;
import com.proc.bean.Requisition;
import com.proc.bean.RequisitionItem;
import com.vaadin.data.Container;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;

public class IDataService {

	public Requisition saveNewRequisition(Map<String, Object> map,
			Table table) {
		Requisition r  = new Requisition();
		try{
			IData data = new IDataImpl();
			r.setDate((Date)map.get(RequisitionForm.DATE));
			r.setDescription((String)map.get(RequisitionForm.DESCRIPTION));
			r.setPriority((Integer) map.get(RequisitionForm.PRIOROTY));
			r.setRemarks((String) map.get(RequisitionForm.REMARK));
			r.setCategory((String) map.get(RequisitionForm.CATEGORY_LINK));
			r.setVesselRefNo((String) map.get(RequisitionForm.VESSEL_NO));
			data.createRequisition(r);
			
			if(table != null){
				Collection<RequisitionItem> list = (Collection<RequisitionItem>) table.getItemIds();
				Container container = table.getContainerDataSource();
				for(RequisitionItem item :list){
					String shortCode = ((TextField) container.getContainerProperty(item, RequisitionItemTable.SHORT_CODE).getValue()).getValue();
					String rob = ((TextField) container.getContainerProperty(item, RequisitionItemTable.ROB).getValue()).getValue();
					String quantity = ((TextField) container.getContainerProperty(item, RequisitionItemTable.QUANTITY).getValue()).getValue();
					String description = ((TextArea) container.getContainerProperty(item, RequisitionItemTable.DESCRIPTION).getValue()).getValue();
					String unitLink = (String)((ComboBox) container.getContainerProperty(item, RequisitionItemTable.UNIT_LINK).getValue()).getValue();
					
					RequisitionItem requsitionItem = new RequisitionItem();
					requsitionItem.setDescription(description);;
					requsitionItem.setQuantity(Integer.valueOf(quantity));
					requsitionItem.setRequisition(r);
					requsitionItem.setRob(rob);
					requsitionItem.setShortCode(shortCode);
					requsitionItem.setUnitLink(unitLink);			
					data.createRequisitionItem(requsitionItem);
				}				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
				
		return r;
	}
	public void saveUpdateRequisition(Long id, Map<String, Object> map,
			Table table) {
		IData data = new IDataImpl();
		Requisition r = null;
		try {
			r = data.getSingleRequisition(id);
			r.setDate((Date)map.get(RequisitionForm.DATE));
			r.setDescription((String)map.get(RequisitionForm.DESCRIPTION));
			r.setPriority((Integer) map.get(RequisitionForm.PRIOROTY));
			r.setRemarks((String) map.get(RequisitionForm.REMARK));
			r.setCategory((String) map.get(RequisitionForm.CATEGORY_LINK));
			r.setVesselRefNo((String) map.get(RequisitionForm.VESSEL_NO));
			data.updateRequisition(r);
			Collection<RequisitionItem> list = (Collection<RequisitionItem>) table.getItemIds();
			Container container = table.getContainerDataSource();
			data.deleteAllRequisitionItem(r);
			for(RequisitionItem item :list){
				String shortCode = ((TextField) container.getContainerProperty(item, RequisitionItemTable.SHORT_CODE).getValue()).getValue();
				String rob = ((TextField) container.getContainerProperty(item, RequisitionItemTable.ROB).getValue()).getValue();
				String quantity = ((TextField) container.getContainerProperty(item, RequisitionItemTable.QUANTITY).getValue()).getValue();
				String description = ((TextArea) container.getContainerProperty(item, RequisitionItemTable.DESCRIPTION).getValue()).getValue();
				String unitLink = (String)((ComboBox) container.getContainerProperty(item, RequisitionItemTable.UNIT_LINK).getValue()).getValue();
				
				RequisitionItem requsitionItem = new RequisitionItem();
				requsitionItem.setDescription(description);;
				requsitionItem.setQuantity(Integer.valueOf(quantity));
				requsitionItem.setRequisition(r);
				requsitionItem.setRob(rob);
				requsitionItem.setShortCode(shortCode);
				requsitionItem.setUnitLink(unitLink);			
				data.createRequisitionItem(requsitionItem);
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}


	public List<RequisitionItem> getRequisitionItem(Long id) {
		IData data = new IDataImpl();
		List<RequisitionItem> items = null;
		try {
			items = data.getRequistionItem(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return items;
	}

	public List<Requisition> getAllRequisition() {
		IData data = new IDataImpl();
		List<Requisition> list = null;
		try {
			list = data.getAllRequisition();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<RequisitionItem> getRequistionItem(Long id) {
		IData data = new IDataImpl();
		List<RequisitionItem> items = null;
		try {
			items = data.getRequistionItem(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return items;
	}
	public Requisition copyRequisition(Requisition requisition) {
		Requisition r  = new Requisition();
		
		r.setDate(requisition.getDate());
		r.setCategory(requisition.getCategory());
		r.setDescription(requisition.getDescription());
		r.setFile(requisition.getFile());
		r.setPriority(requisition.getPriority());
		r.setRemarks(requisition.getRemarks());
		r.setVesselRefNo(requisition.getVesselRefNo());
		
		return r;
	}


}
