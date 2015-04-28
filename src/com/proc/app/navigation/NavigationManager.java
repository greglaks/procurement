package com.proc.app.navigation;

import com.vaadin.navigator.Navigator;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.UI;
import com.vaadin.ui.Button.ClickEvent;

public class NavigationManager {

	private Button requisitionSupplyButton;
	private Button requisitionAddButton;
	private Button home;

	public NavigationManager(){
		final Navigator n = UI.getCurrent().getNavigator();
		home = new Button("Home");
		requisitionSupplyButton = new Button("Supplies");
		requisitionAddButton = new Button("Home");
		
		requisitionSupplyButton.addClickListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				n.navigateTo(NavigationItem.REQUISITION_SUPPLY_PAGE);
				
			}
		});
		
		
		requisitionAddButton.addClickListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				n.navigateTo(NavigationItem.REQUISITION_ADD_PAGE);
				
			}
		});
	}

	public Button getRequisitionSupplyButton() {
		return requisitionSupplyButton;
	}
	
	public Button getRequisitionAddButton() {
		return requisitionAddButton;
	}

	public Button getHome() {
		return home;
	}	
	
}
