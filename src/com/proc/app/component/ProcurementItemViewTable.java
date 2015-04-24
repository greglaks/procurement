package com.proc.app.component;

import java.util.Date;

import com.proc.app.Util;
import com.proc.page.MainPage;
import com.proc.page.ProcurementMasterDetail;
import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class ProcurementItemViewTable extends VerticalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 847230619849433239L;
	private static final String COL_UTIL_1 = "col1";
	private static final String COL_UTIL_2 = "col2";
	private static final String COL_UTIL_3 = "col3";
	private static final String COL_UTIL_4 = "col4";
	
	private static final String UNIT_LINK = "unit-link";
	private static final String SHORT_CODE = "short-code";
	private static final String DESCRIPTION = "description";
	private static final String ROB = "rob";
	private static final String QUANTITY = "quantity";
	
	private IndexedContainer container;
	private PagedTable table = new PagedTable();

	
	public ProcurementItemViewTable(){

		initTable();
	}
	
	private void initTable() {

		addStyleName("posinline");
		container = new IndexedContainer();
        container.addContainerProperty(COL_UTIL_1, Component.class, null);
        container.addContainerProperty(COL_UTIL_2, Component.class, null);
        container.addContainerProperty(COL_UTIL_3, Component.class, null);
        container.addContainerProperty(COL_UTIL_4, Component.class, null);
        container.addContainerProperty(UNIT_LINK, String.class, null);
        container.addContainerProperty(SHORT_CODE, String.class, null);
        container.addContainerProperty(DESCRIPTION, String.class, null);
        container.addContainerProperty(ROB, String.class, null);
        container.addContainerProperty(QUANTITY, String.class, null);

        table.setColumnHeader(COL_UTIL_1, " ");
        table.setColumnHeader(COL_UTIL_2, " ");
        table.setColumnHeader(COL_UTIL_3, " ");
        table.setColumnHeader(COL_UTIL_4, " ");
        
        table.setColumnWidth(COL_UTIL_2, 60);
        table.setColumnWidth(COL_UTIL_3, 60);
        table.setColumnWidth(COL_UTIL_4, 60);
        
        table.setColumnHeader(UNIT_LINK, "Unit Link");
        table.setColumnHeader(SHORT_CODE, "Short Code");
        table.setColumnHeader(DESCRIPTION, "Description");
        table.setColumnHeader(ROB, "ROB");
        table.setColumnHeader(QUANTITY, "Quantity");
        
        table.setContainerDataSource(container);
        addComponent(table);
        addComponent(table.createControls());
        
        HorizontalLayout footerLayout = createFooterButtonLayout();
        addComponent(footerLayout);
	}
	
	private HorizontalLayout createFooterButtonLayout() {
		HorizontalLayout mainLayout = new HorizontalLayout();
		mainLayout.setSpacing(true);
		
		HorizontalLayout editLayout = new HorizontalLayout();
		Button addButton = new Button("Inline Add");
		Button editButton = new Button("Grid Edit");
		Button selectedUpdateButton = new Button("Update selected Records");
		
		editLayout.addComponent(addButton);
		editLayout.addComponent(editButton);
		mainLayout.addComponent(editLayout);
		mainLayout.addComponent(selectedUpdateButton);
		
		return mainLayout;
	}

	public void insertItem(String id, String unitLink, String shortCode, String description, String rob, String quantity){
		Item item = container.addItem(id);

		item.getItemProperty(COL_UTIL_1).setValue(new CheckBox());
		item.getItemProperty(COL_UTIL_2).setValue(new Button(new ThemeResource(Util.EDIT_ICON)));
		item.getItemProperty(COL_UTIL_3).setValue(new Button(new ThemeResource(Util.CREATE_ICON)));
		item.getItemProperty(COL_UTIL_4).setValue(new Button(new ThemeResource(Util.DELETE_ICON)));
		item.getItemProperty(UNIT_LINK).setValue(unitLink);
		item.getItemProperty(SHORT_CODE).setValue(shortCode);
		item.getItemProperty(DESCRIPTION).setValue(description);
		item.getItemProperty(ROB).setValue(rob);
		item.getItemProperty(QUANTITY).setValue(quantity);
		
	}



	private SplitButton createSplitButton(String id) {
		SplitButton button = new SplitButton("REQUESTED ITEMS(0)");
		button.setButtonId(id);
		
		
		return button;
	}

	public PagedTable getTable() {
		return table;
	}

	public void setTable(PagedTable table) {
		this.table = table;
	}
	
	

}
