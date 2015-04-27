package com.proc.app.component;

import java.util.Date;

import com.proc.app.ProcurementUI;
import com.proc.app.Util;
import com.proc.app.component.PagedTable.PagedTableChangeEvent;
import com.proc.bean.Requisition;
import com.proc.dao.IData;
import com.proc.dao.IDataService;
import com.proc.page.MainPage;
import com.proc.page.RequisitionMasterDetail;
import com.proc.page.RequestedItemViewPage;
import com.vaadin.data.Item;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

public class CustomPagedTable extends VerticalLayout {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4461027634122046066L;
	private static final String COL_UTIL_1 = "col1";
	
	private static final String RECEIVED_DATE = "Received Date";
	private static final String PRIORITY = "Priority";
	private static final String CATEGORY_LINK = "Category Link";
	private static final String DESCRIPTION = "Description";
	private static final String VESSEL_REF_NO = "Vessel Ref No";
	private IndexedContainer container;
	private PagedTable table = new PagedTable();
	private MainPage procurementMainPage;
	private ValueChangeListener requesitionTableselectionListener = new ValueChangeListener() {
		
		@Override
		public void valueChange(ValueChangeEvent event) {
			
		}
	};
	
	public CustomPagedTable(){
		this.procurementMainPage = ((ProcurementUI) UI.getCurrent()).getMainPage();
		setSpacing(true);
		initTable();
	}
	
	private void initTable() {
		table.setWidth("950px");
		table.setMultiSelect(false);
		table.setSelectable(true);
		table.setImmediate(true);
		table.addValueChangeListener(requesitionTableselectionListener );
		addStyleName("posinline");
		container = new IndexedContainer();
        container.addContainerProperty(COL_UTIL_1, Component.class, null);
        container.addContainerProperty(RECEIVED_DATE, Date.class, null);
        container.addContainerProperty(PRIORITY, String.class, null);
        container.addContainerProperty(CATEGORY_LINK, String.class, null);
        container.addContainerProperty(DESCRIPTION, String.class, null);
        container.addContainerProperty(VESSEL_REF_NO, String.class, null);
        table.setColumnHeader(COL_UTIL_1, " ");
        table.setContainerDataSource(container);
        addComponent(table);
        addComponent(table.createControls());
	}
	
	public void insertItem(Requisition requisition){
		String prior = ((ProcurementUI)UI.getCurrent()).getPriority(requisition.getPriority());
		Item item = container.addItem(requisition);
		SplitButton splitButton = createSplitButton(requisition);
		item.getItemProperty(COL_UTIL_1).setValue(splitButton);
		item.getItemProperty(RECEIVED_DATE).setValue(requisition.getDate());
		item.getItemProperty(PRIORITY).setValue(prior);
		item.getItemProperty(CATEGORY_LINK).setValue(requisition.getCategory());
		item.getItemProperty(DESCRIPTION).setValue(requisition.getDescription());
		item.getItemProperty(VESSEL_REF_NO).setValue(requisition.getVesselRefNo());
		
	}



	private SplitButton createSplitButton(final Requisition requisition) {
		SplitButton button = new SplitButton("REQUESTED ITEMS(0)");
		button.setButtonId(requisition);

		Button view = new Button("Master/Detail View");
		Button copy = new Button("Master/Detail Copy");
		Button edit = new Button("Master/Detail Edit");
		Button main = new Button("REQUESTED ITEMS(0)");
		main.addClickListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				
				procurementMainPage.switchPage(new RequestedItemViewPage(requisition));
				procurementMainPage.setNavigationLink("REQUESTED ITEM");
			}
		});
		view.addClickListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				//procurementMainPage.removeAllComponents();
				//procurementMainPage.addComponent(new );
				
			}
		});
		
		copy.addClickListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				Requisition r = new IDataService().copyRequisition(requisition);
				procurementMainPage.switchPage(new RequisitionMasterDetail(r, false));
				procurementMainPage.setNavigationLink("Copy");
				
			}
		});
		
		edit.addClickListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				procurementMainPage.switchPage(new RequisitionMasterDetail(requisition, false));
				procurementMainPage.setNavigationLink("Update");
			}
		});
		
		button.addButton(main);
		button.addButton(view);
		button.addButton(copy);
		button.addButton(edit);
		
		return button;
	}

	public PagedTable getTable() {
		return table;
	}

	public void setTable(PagedTable table) {
		this.table = table;
	}
	
	
}
