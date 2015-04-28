package com.proc.page;

import java.util.Map;

import com.proc.app.navigation.NavigationItem;
import com.proc.bean.Requisition;
import com.proc.dao.IDataService;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification.Type;

public class RequisitionCopyPage extends RequisitionMasterDetail implements View {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5244111002752325270L;

	
	private void createAddButton() {
		Button addButton = new Button("Add");
		addButton.addClickListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				Map<String, Object> map = requisitionForm.getRequisitionFormValue();
				Requisition r = new IDataService().saveNewRequisition(map, table.getTable());
				if(r != null){
					Notification.show("Requisition is sucessfully copied", Type.HUMANIZED_MESSAGE);					
					UI.getCurrent().getNavigator().navigateTo(NavigationItem.REQUISITION_SUPPLY_PAGE);
				}
				
			}
		});
		
		addButton.setPrimaryStyleName("button-primary");
		addComponent(addButton);
	}


	@Override
	public void enter(ViewChangeEvent event) {
		String params = event.getParameters();
		String[] paramValues = params.split("/");
		
		String requisitionId = paramValues[0];
		
		IDataService dataService = new IDataService();
		this.requisition = dataService.getRequisitionById(Long.parseLong(requisitionId));
		this.isReadOnly = false;
		
		initPage();
		createAddButton();
	}
}