package com.proc.page;

import java.util.List;
import java.util.Map;

import com.proc.app.ProcurementUI;
import com.proc.app.component.RequisitionForm;
import com.proc.app.component.RequisitionItemTable;
import com.proc.bean.Requisition;
import com.proc.bean.RequisitionItem;
import com.proc.dao.IData;
import com.proc.dao.IDataService;
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

public class RequisitionMasterDetail extends VerticalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3987485419145438142L;
	private boolean isReadOnly;
	private Requisition requisition;
	private RequisitionForm requisitionForm;
	private RequisitionItemTable table;
	private MainPage mainPage;
	
	public RequisitionMasterDetail(Requisition requisition, boolean isReadOnly){
		this.isReadOnly = isReadOnly;
		this.requisition = requisition;
		this.mainPage = ((ProcurementUI) UI.getCurrent()).getMainPage();
		initPage();
	}

	private void initPage() {
		setSpacing(true);
		createMasterInfo();
		createItemTable();
		if(requisition.getId() != null){
			createUpdateButton();
		}
		else{
			createAddButton();			
		}
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

	private void createAddButton() {
		Button addButton = new Button("Add");
		addButton.addClickListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				Map<String, Object> map = requisitionForm.getRequisitionFormValue();
				new IDataService().saveNewRequisition(map, table.getTable());
				Notification.show("Requisition is sucessfully copied", Type.HUMANIZED_MESSAGE);
				mainPage.switchPage(new RequisitionListPage());
			}
		});
		
		addButton.setPrimaryStyleName("button-primary");
		addComponent(addButton);
	}

	private void createUpdateButton() {
		Button addButton = new Button("Update");
		addButton.addClickListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				Map<String, Object> map = requisitionForm.getRequisitionFormValue();
				new IDataService().saveUpdateRequisition(requisition.getId(),map, table.getTable());
				Notification.show("Requisition is sucessfully updated", Type.HUMANIZED_MESSAGE);
				mainPage.switchPage(new RequisitionListPage());
			}
		});
		
		addButton.setPrimaryStyleName("button-primary");
		addComponent(addButton);
		
	}

}
