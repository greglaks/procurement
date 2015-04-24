package com.proc.app.component;

import java.util.Date;

import com.proc.app.Util;
import com.proc.app.component.PagedTable.PagedTableChangeEvent;
import com.proc.page.MainPage;
import com.proc.page.ProcurementMasterDetail;
import com.proc.page.RequestedItemViewPage;
import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

public class CustomPagedTable extends VerticalLayout {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4461027634122046066L;
	private static final String COL_UTIL_1 = "col1";
	private static final String COL_UTIL_2 = "col2";
	private static final String COL_UTIL_3 = "col3";
	private static final String COL_UTIL_4 = "col4";
	
	private static final String RECEIVED_DATE = "Received Date";
	private static final String PRIORITY = "Priority";
	private static final String VESSEL_DEPT = "Vessel Dept";
	private static final String CATEGORY_LINK = "Category Link";
	private static final String VESSEL_LINK = "Vessel Link";
	private static final String DESCRIPTION = "Description";
	private static final String VESSEL_REF_NO = "Vessel Ref No";
	private IndexedContainer container;
	private PagedTable table = new PagedTable();
	private MainPage procurementMainPage;
	
	public CustomPagedTable(MainPage procurementMainPage){
		this.procurementMainPage = procurementMainPage;

		initTable();
	}
	
	private void initTable() {

		addStyleName("posinline");
		container = new IndexedContainer();
        container.addContainerProperty(COL_UTIL_1, Component.class, null);
        container.addContainerProperty(COL_UTIL_2, Component.class, null);
        container.addContainerProperty(COL_UTIL_3, Component.class, null);
        container.addContainerProperty(COL_UTIL_4, Component.class, null);
        container.addContainerProperty(RECEIVED_DATE, Date.class, null);
        container.addContainerProperty(PRIORITY, String.class, null);
        container.addContainerProperty(VESSEL_DEPT, String.class, null);
        container.addContainerProperty(CATEGORY_LINK, String.class, null);
        container.addContainerProperty(VESSEL_LINK, String.class, null);
        container.addContainerProperty(DESCRIPTION, String.class, null);
        container.addContainerProperty(VESSEL_REF_NO, String.class, null);
        table.setColumnHeader(COL_UTIL_1, " ");
        table.setColumnHeader(COL_UTIL_2, " ");
        table.setColumnHeader(COL_UTIL_3, " ");
        table.setColumnHeader(COL_UTIL_4, " ");
        table.setColumnWidth(COL_UTIL_1, 60);
        table.setColumnWidth(COL_UTIL_2, 60);
        table.setColumnWidth(COL_UTIL_3, 60);
        table.setContainerDataSource(container);
        addComponent(table);
        addComponent(table.createControls());
	}
	
	public void insertItem(String id, Date receivedDate, String priority, String vesselDept,
						   String categoryLink, String vesselLink, String description,
						   String vesselRefNo){
		Item item = container.addItem(id);
		SplitButton splitButton = createSplitButton(id);
		item.getItemProperty(COL_UTIL_1).setValue(new Button(new ThemeResource(Util.SEARCH_ICON)));
		item.getItemProperty(COL_UTIL_2).setValue(new Button(new ThemeResource(Util.EDIT_ICON)));
		item.getItemProperty(COL_UTIL_3).setValue(new Button(new ThemeResource(Util.COPY_ICON)));
		item.getItemProperty(COL_UTIL_4).setValue(splitButton);
		item.getItemProperty(RECEIVED_DATE).setValue(receivedDate);
		item.getItemProperty(PRIORITY).setValue(priority);
		item.getItemProperty(VESSEL_DEPT).setValue(vesselDept);
		item.getItemProperty(CATEGORY_LINK).setValue(categoryLink);
		item.getItemProperty(VESSEL_LINK).setValue(vesselLink);
		item.getItemProperty(DESCRIPTION).setValue(description);
		item.getItemProperty(VESSEL_REF_NO).setValue(vesselRefNo);
		
	}



	private SplitButton createSplitButton(String id) {
		SplitButton button = new SplitButton("REQUESTED ITEMS(0)");
		button.setButtonId(id);

		Button view = new Button("Master/Detail View");
		Button copy = new Button("Master/Detail Copy");
		Button main = new Button("REQUESTED ITEMS(0)");
		main.addClickListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				
				procurementMainPage.switchPage(new RequestedItemViewPage());
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
				procurementMainPage.switchPage(new ProcurementMasterDetail(false));
				procurementMainPage.setNavigationLink("Copy");
				
			}
		});
		
		button.addButton(main);
		button.addButton(view);
		button.addButton(copy);
		
		return button;
	}

	public PagedTable getTable() {
		return table;
	}

	public void setTable(PagedTable table) {
		this.table = table;
	}
	
	
}
