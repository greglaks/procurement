package com.proc.page;

import java.util.List;

import com.proc.app.ProcurementUI;
import com.proc.app.component.RequisitionForm;
import com.proc.app.component.RequisitionItemViewTable;
import com.proc.app.component.SearchItemComponent;
import com.proc.bean.Requisition;
import com.proc.bean.RequisitionItem;
import com.proc.dao.IData;
import com.proc.dao.IDataService;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class RequestedItemViewPage extends VerticalLayout implements View{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6940382825080619626L;
	private Requisition requisition;
	private List<RequisitionItem> requisitionItem;

	public RequestedItemViewPage(){
		
	}

	private void initPage() {
		setSpacing(true);
		RequisitionForm itemForm = new RequisitionForm(requisition, true);
		SearchItemComponent searchComponent = new SearchItemComponent();
		RequisitionItemViewTable itemTable = new RequisitionItemViewTable();
		
		for(RequisitionItem r: requisitionItem){
			itemTable.insertItem(r);
		}
		
		addComponent(itemForm);
		addComponent(searchComponent);
		addComponent(itemTable);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		String param = event.getParameters();
		Long id = Long.parseLong(param);
		IDataService dataService = new IDataService();
		
		this.requisition = dataService.getRequisitionById(id);
		this.requisitionItem = dataService.getRequistionItem(id);
		initPage();
	}
}
