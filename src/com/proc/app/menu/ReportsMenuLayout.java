package com.proc.app.menu;

import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;

public class ReportsMenuLayout extends VerticalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7922833327670827098L;

	public ReportsMenuLayout(){
		insertButtons();
	}

	private void insertButtons() {
		Button item1 = new Button("RFQ Volumes");
		Button item2 = new Button("P.O. Volumes");
		Button item3 = new Button("Buyer Performance");
		Button item4 = new Button("Response Time");
		Button item5 = new Button("RFQ Response Time");
		Button item6 = new Button("Supplier Win Rate");
		Button item7 = new Button("Orders By Country");
		Button item8 = new Button("On-Time Performance");
		
		addComponent(item1);
		addComponent(item2);
		addComponent(item3);
		addComponent(item4);
		addComponent(item5);
		addComponent(item6);
		addComponent(item7);
		addComponent(item8);
		
		item1.setPrimaryStyleName("menu-childern");
		item2.setPrimaryStyleName("menu-childern");
		item3.setPrimaryStyleName("menu-childern");
		item4.setPrimaryStyleName("menu-childern");
		item5.setPrimaryStyleName("menu-childern");
		item6.setPrimaryStyleName("menu-childern");
		item7.setPrimaryStyleName("menu-childern");
		item8.setPrimaryStyleName("menu-childern");
		
	}
	
}
