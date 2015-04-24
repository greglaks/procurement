package com.proc.page;

import java.util.Calendar;
import java.util.Date;

import com.proc.app.component.CustomPagedTable;
import com.proc.app.component.SearchItemComponent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

public class ProcurementListPage extends VerticalLayout {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4774251394314334294L;
	private MainPage procurementMainPage;

	public ProcurementListPage(MainPage procurementMainPage){
		this.procurementMainPage = procurementMainPage;
		createListPage();
	}

	private void createListPage() {
		setSpacing(true);
		initSearchLayout();
		initTableLayout();

	}
	private void initSearchLayout() {
		SearchItemComponent mainSearchLayout = new SearchItemComponent();
		addComponent(mainSearchLayout);
		setComponentAlignment(mainSearchLayout, Alignment.TOP_LEFT);
		
	}

	private void initTableLayout() {
		CustomPagedTable table = new CustomPagedTable(procurementMainPage);
		HorizontalLayout bottomLayout = createBottomButtonControl();
		table.addComponent(bottomLayout);
		
		addComponent(table);
		for(int i=0;i<50;i++){
			Date dateNow = Calendar.getInstance().getTime();
			table.insertItem(String.valueOf(i), dateNow, "Prior "+i, "Vessel dept "+i, "Category "+i, "Vessel Link "+i, "Description "+i, "Vessel Ref no "+i);
		}
		setComponentAlignment(table, Alignment.TOP_LEFT);
	}
	
	public HorizontalLayout createBottomButtonControl(){
		 	HorizontalLayout bottomLayout = new HorizontalLayout();
	        bottomLayout.setSpacing(true);
	        
	        HorizontalLayout bottomGroupOneLayout = new HorizontalLayout();
	        Button addButton = new Button("Add");
	        Button addGridButton = new Button("Grid Add");
	        Button gridEditButton = new Button("Grid Edit");
	        Button supplyAddButton = new Button("Add supplies/REQUESTED ITEMS");
	        bottomGroupOneLayout.addComponent(addButton);
	        bottomGroupOneLayout.addComponent(addGridButton);
	        bottomGroupOneLayout.addComponent(gridEditButton);
	        
	        bottomLayout.addComponent(bottomGroupOneLayout);
	        bottomLayout.addComponent(supplyAddButton);

	        
	        addButton.addClickListener(new ClickListener() {
				
				@Override
				public void buttonClick(ClickEvent event) {
					procurementMainPage.switchPage(new AddItemPage());
					procurementMainPage.setNavigationLink("Add");
				}
			});
	        return bottomLayout;
	}
	
}
