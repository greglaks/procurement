package com.proc.page;

import com.proc.app.component.ProcurementItemForm;
import com.proc.app.component.ProcurementItemViewTable;
import com.proc.app.component.SearchItemComponent;
import com.vaadin.ui.VerticalLayout;

public class RequestedItemViewPage extends VerticalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6940382825080619626L;

	public RequestedItemViewPage(){
		initPage();
	}

	private void initPage() {
		setSpacing(true);
		ProcurementItemForm itemForm = new ProcurementItemForm(true);
		SearchItemComponent searchComponent = new SearchItemComponent();
		ProcurementItemViewTable itemTable = new ProcurementItemViewTable();
		for(int i=0; i<15; i++){
			itemTable.insertItem(String.valueOf(i), "Unit link"+i, "shortCode"+i, "description"+i, "rob"+i, String.valueOf((13+i)*2));
		}
		
		addComponent(itemForm);
		addComponent(searchComponent);
		addComponent(itemTable);
	}
}
