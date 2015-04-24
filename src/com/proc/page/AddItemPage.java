package com.proc.page;

import com.proc.app.component.ProcurementItemForm;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;

public class AddItemPage extends VerticalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4048180009986615034L;

	public AddItemPage(){
		createPage();
	}

	private void createPage() {
		setSpacing(true);
		ProcurementItemForm form = new ProcurementItemForm(false);
		Button addButton = new Button("Add");
		addButton.setPrimaryStyleName("button-primary");
		addComponent(form);
		addComponent(addButton);
	}
}
