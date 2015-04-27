package com.proc.dao;

import java.util.List;
import java.util.Map;

import com.proc.bean.Requisition;
import com.proc.bean.RequisitionItem;

public interface IData {
	public Data getData(String code);
		
	public List<Requisition> getAllRequisition() throws Exception;
	public Requisition getSingleRequisition(long reqId) throws Exception;
	public void createRequisition(Requisition requisition) throws Exception;
	public void updateRequisition(Requisition requisition) throws Exception;
	public void deleteRequisition(Requisition requisition) throws Exception;
	
	public List<RequisitionItem> getRequistionItem(long requisionItem) throws Exception;
	public RequisitionItem getSingleRequisitionItem(long requisitionId) throws Exception;
	public void createRequisitionItem(RequisitionItem requisitionItem) throws Exception;
	public void updateRequisitionItem(RequisitionItem requisitionItem) throws Exception;
	public void deleteRequisitionItem(RequisitionItem requisitionItem) throws Exception;
	public void deleteAllRequisitionItem(Requisition requisition) throws Exception;
}
