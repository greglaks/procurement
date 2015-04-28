package com.proc.page;

import java.util.List;
import java.util.Map;

import com.proc.app.ProcurementUI;
import com.proc.app.component.RequisitionForm;
import com.proc.app.component.RequisitionItemTable;
import com.proc.app.navigation.NavigationItem;
import com.proc.bean.Requisition;
import com.proc.bean.RequisitionItem;
import com.proc.dao.IData;
import com.proc.dao.IDataService;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Notification.Type;

public class RequisitionMasterDetail extends VerticalLayout{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3987485419145438142L;
	protected boolean isReadOnly;
	protected Requisition requisition;
	protected RequisitionForm requisitionForm;
	protected RequisitionItemTable table;
	

	protected void initPage() {
		setSpacing(true);
		createMasterInfo();
		createItemTable();
	}

	private void createMasterInfo() {
		requisitionForm = new RequisitionForm(requisition, isReadOnly);
		addComponent(requisitionForm);
	}

	private void createItemTable() {
		table = new RequisitionItemTable(isReadOnly);
		addComponent(table);
		Long id = requisition.getId();
		if(id != null){
			List<RequisitionItem> list = new IDataService().getRequistionItem(id);
			for(RequisitionItem i : list){
				table.insertItem(i);
			}			
		}
	}


}
