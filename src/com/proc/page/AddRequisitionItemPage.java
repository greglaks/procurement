package com.proc.page;

import java.util.Map;

import com.proc.app.ProcurementUI;
import com.proc.app.component.RequisitionForm;
import com.proc.app.navigation.NavigationItem;
import com.proc.bean.Requisition;
import com.proc.dao.IDataService;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Notification.Type;

public class AddRequisitionItemPage extends VerticalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4048180009986615034L;
	private Requisition requisitionItem;

	public AddRequisitionItemPage(Requisition requisitionItem){
		this.requisitionItem = requisitionItem;
		createPage();
	}

	private void createPage() {
		setSpacing(true);
		final RequisitionForm form = new RequisitionForm(requisitionItem, false);
		Button addButton = new Button("Add");
		addButton.addClickListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				Map<String, Object> map = form.getRequisitionFormValue();
				Requisition r = new IDataService().saveNewRequisition(map,null);
				if(r != null){					
					Notification.show("Requisition is sucessfully created", Type.HUMANIZED_MESSAGE);
					UI.getCurrent().getNavigator().navigateTo(NavigationItem.REQUISITION_SUPPLY_PAGE);
				}
				
			}
		});
		addButton.setPrimaryStyleName("button-primary");
		addComponent(form);
		addComponent(addButton);
	}
}
