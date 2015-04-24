package com.proc.page;

import com.proc.app.component.ProcurementItemForm;
import com.proc.app.component.RequisitionItemTable;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class ProcurementMasterDetail extends VerticalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3987485419145438142L;
	private boolean isReadOnly;
	
	public ProcurementMasterDetail(boolean isReadOnly){
		this.isReadOnly = isReadOnly;
		initPage();
	}

	private void initPage() {
		setSpacing(true);
		createMasterInfo();
		createItemTable();
		createAddButton();
	}

	private void createMasterInfo() {
		addComponent(new ProcurementItemForm(isReadOnly));
	}

	private void createItemTable() {
		RequisitionItemTable table = new RequisitionItemTable(isReadOnly);
		addComponent(table);
		for(int i=0;i<5; i++){
			table.insertItem(i);
		}
	}

	private void createAddButton() {
		Button addButton = new Button("Add");
		addButton.setPrimaryStyleName("button-primary");
		addComponent(addButton);
	}

}
