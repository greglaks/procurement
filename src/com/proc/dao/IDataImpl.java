package com.proc.dao;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.proc.bean.Requisition;
import com.proc.bean.RequisitionItem;

public class IDataImpl implements IData {

	private static InputStream test;


	@Override
	public Data getData(String code) {
		InputStream inputStrem = IDataImpl.class.getResourceAsStream("IMPA5_MTMLUoM updated 1301.xls");
		HSSFWorkbook wb;
		Data data = null;
		if(code.equals(""))
			return data;
		try {
			wb = new HSSFWorkbook(inputStrem);
			HSSFSheet sheet = wb.getSheetAt(0);
			Row r = findRow(sheet, code);
			if(r != null){
				data = new Data();
				data.setPartNo(code);
				data.setDescription(r.getCell(4).getStringCellValue());
				data.setUom(r.getCell(5).getStringCellValue());
			}

		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				inputStrem.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return data;
	}


	private Row findRow(HSSFSheet sheet, String cellContent) {
		Row r = null;
	    for (Row row : sheet) {
	    	Cell cell = row.getCell(3);
	        if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
	            if (cell.getRichStringCellValue().getString().trim().equals(cellContent)) {
	                    r = row;  
	             }
	         }
	        
	    }
	    return r;
	}


	@Override
	public List<Requisition> getAllRequisition() throws Exception{
		EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		Query q = em.createQuery("SELECT r FROM Requisition r");
		List<Requisition> list = q.getResultList();
		em.close();
		
		return list;
	}


	@Override
	public Requisition getSingleRequisition(long reqId) throws Exception{
		EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		Requisition r = em.find(Requisition.class, reqId);
		em.close();
		return r;
	}


	@Override
	public void createRequisition(Requisition requisition) throws Exception{
		EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		em.getTransaction().begin();
		em.persist(requisition);
		em.getTransaction().commit();
		em.close();	
	}

	@Override
	public void updateRequisition(Requisition requisition) throws Exception{
		EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		em.getTransaction().begin();
		Requisition r  = em.find(Requisition.class, requisition.getId());
		em.merge(r);
		em.getTransaction().commit();
		em.close();
	}


	@Override
	public void deleteRequisition(Requisition requisition) {
		EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		em.getTransaction().begin();
		Requisition r  = em.find(Requisition.class, requisition.getId());
		em.remove(r);
		em.getTransaction().commit();
		em.close();		
	}


	@Override
	public List<RequisitionItem> getRequistionItem(long requisionItem) throws Exception{
		EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		Query q = em.createQuery("SELECT i FROM RequisitionItem i");
		List<RequisitionItem> items = q.getResultList();
		em.close();
		
		return items;
	}

	@Override
	public void createRequisitionItem(RequisitionItem requisitionItem) throws Exception{
		EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		em.getTransaction().begin();
		em.persist(requisitionItem);
		em.getTransaction().commit();
		em.close();
		
	}


	@Override
	public void updateRequisitionItem(RequisitionItem requisitionItem) throws Exception{
//		EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
//		em.getTransaction().begin();
//		RequisitionItem r = em.find(RequisitionItem.class, requisitionItem.getId());
//		em.merge(r);
//		em.getTransaction().commit();
//		em.close();
		
	}


	@Override
	public void deleteRequisitionItem(RequisitionItem requisitionItem) throws Exception{
//		EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
//		em.getTransaction().begin();
//		RequisitionItem r = em.find(RequisitionItem.class, requisitionItem.getId());
//		em.remove(r);
//		em.getTransaction().commit();
//		em.close();		
	}


	@Override
	public RequisitionItem getSingleRequisitionItem(long requisitionId)
			throws Exception {
		EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		RequisitionItem item = em.find(RequisitionItem.class, requisitionId);
		em.close();
		
		return item;
	}


	@Override
	public void deleteAllRequisitionItem(Requisition requisition)
			throws Exception {
		EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		em.getTransaction().begin();
		Query q = em.createQuery("DELETE FROM RequisitionItem i WHERE i.requisition = :requisition");
		q.setParameter("requisition", requisition);
		q.executeUpdate();
		em.getTransaction().commit();
		em.close();
		
	}


}
