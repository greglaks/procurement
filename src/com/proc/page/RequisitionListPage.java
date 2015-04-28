package com.proc.page;

import java.util.List;

import com.proc.app.ProcurementUI;
import com.proc.app.component.CustomPagedTable;
import com.proc.app.component.SearchItemComponent;
import com.proc.app.navigation.NavigationItem;
import com.proc.bean.Requisition;
import com.proc.dao.IData;
import com.proc.dao.IDataService;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class RequisitionListPage extends VerticalLayout implements View{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4774251394314334294L;
	private MainPage requisitionMainPage;

	public RequisitionListPage(){
		this.requisitionMainPage = ((ProcurementUI)UI.getCurrent()).getMainPage();
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
		CustomPagedTable table = new CustomPagedTable();
		HorizontalLayout bottomLayout = createBottomButtonControl();
		table.addComponent(bottomLayout);
		
		addComponent(table);
		List<Requisition> list = new IDataService().getAllRequisition();
		for(Requisition r : list){
			table.insertItem(r);
		}
		setComponentAlignment(table, Alignment.TOP_LEFT);
	}
	
	public HorizontalLayout createBottomButtonControl(){
		 	HorizontalLayout bottomLayout = new HorizontalLayout();
	        bottomLayout.setSpacing(true);
	        
	        HorizontalLayout bottomGroupOneLayout = new HorizontalLayout();
	        Button addButton = new Button("Add");
	        addButton.setPrimaryStyleName("button-primary");
	 
	        bottomGroupOneLayout.addComponent(addButton);
	        bottomLayout.addComponent(bottomGroupOneLayout);

	        addButton.addClickListener(new ClickListener() {
				
				@Override
				public void buttonClick(ClickEvent event) {
					Navigator nav = UI.getCurrent().getNavigator();
					nav.navigateTo(NavigationItem.REQUISITION_ADD_PAGE);
				}
			});
	        return bottomLayout;
	}

	@Override
	public void enter(ViewChangeEvent event) {
		
	}
	
}
