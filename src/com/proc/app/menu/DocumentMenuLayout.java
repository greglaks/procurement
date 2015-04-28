package com.proc.app.menu;

import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;

public class DocumentMenuLayout extends VerticalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1070285916596193694L;
	
	public DocumentMenuLayout(){
		addStyleName("menu-content");
		insertButtons();
	}

	private void insertButtons() {
		Button item1 = new Button("All Documents");
		Button item2 = new Button("New Document");
		Button item3 = new Button("Requisitions");
		Button item4 = new Button("Request for Quotation");
		Button item5 = new Button("Quotations");
		Button item6 = new Button("Purchase Orders");
		
		addComponent(item1);
		addComponent(item2);
		addComponent(item3);
		addComponent(item4);
		addComponent(item5);
		addComponent(item6);
		
		item1.setPrimaryStyleName("menu-childern");
		item2.setPrimaryStyleName("menu-childern");
		item3.setPrimaryStyleName("menu-childern");
		item4.setPrimaryStyleName("menu-childern");
		item5.setPrimaryStyleName("menu-childern");
		item6.setPrimaryStyleName("menu-childern");
		
	}
	
	
	

}
