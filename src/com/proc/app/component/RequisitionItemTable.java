package com.proc.app.component;

import com.proc.dao.Data;
import com.proc.dao.IDataImpl;
import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.event.FieldEvents.BlurEvent;
import com.vaadin.event.FieldEvents.BlurListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class RequisitionItemTable extends VerticalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6105863126227001375L;
	private static final String DELETE_COMPONENT = "delete-item";
	private static final String UNIT_LINK = "unit-link";
	private static final String SHORT_CODE = "short-code";
	private static final String ROB = "rob";
	private static final String DESCRIPTION = "description";
	private static final String QUANTITY = "quantity";
	private IndexedContainer container;
	private Table table = new Table();
	private boolean isReadOnly;
	private Button addRowButton;
	
	public RequisitionItemTable(boolean isReadOnly){
		this.isReadOnly = isReadOnly;
		initTable();
		initFooterButton();
	}
	
	public void removeFooter(){
		removeComponent(addRowButton);
	}
	
	private void initFooterButton() {
		if(isReadOnly)
			return;
		
		addRowButton = new Button("Add Blank Row");
		addRowButton.addClickListener(new ClickListener(){

			@Override
			public void buttonClick(ClickEvent event) {	
				int latestId = table.getItemIds().size() + 1;
				insertItem(latestId);
			}
			
		});
		addComponent(addRowButton);
	}

	private void initTable() {
		table.setHeight("200px");
		table.addStyleName("procurement-detail-table");
		container = new IndexedContainer();
		container.addContainerProperty(DELETE_COMPONENT, Button.class, null);
		container.addContainerProperty(UNIT_LINK, ComboBox.class, null);
		container.addContainerProperty(SHORT_CODE, TextField.class, null);
		container.addContainerProperty(DESCRIPTION, TextArea.class, null);
		container.addContainerProperty(ROB, TextField.class, null);
		container.addContainerProperty(QUANTITY, TextField.class, null);
		table.setContainerDataSource(container);
		
		table.setColumnHeader(DELETE_COMPONENT, "");
		table.setColumnHeader(UNIT_LINK, "Unit Link");
		table.setColumnHeader(SHORT_CODE, "Short Code");
		table.setColumnHeader(DESCRIPTION, "Description");
		table.setColumnHeader(ROB, "ROB");
		table.setColumnHeader(QUANTITY, "Quantity");
		addComponent(table);
	}
	
	public void insertItem(final Object id){
		Item item = table.addItem(id);
		ComboBox unitLink = createUnitLinkComboBox();
		TextField shortCode = new TextField();
		TextArea description = new TextArea();
		TextField rob = new TextField();
		TextField quantity = new TextField();
		Button deleteButton = new Button("Delete");
		
		unitLink.setWidth("90px");
		shortCode.setWidth("90px");
		rob.setHeight("24px");
		shortCode.setHeight("24px");
		quantity.setHeight("24px");
		
		
		description.setRows(1);
		description.setColumns(20);
		description.addStyleName("textarea-resizeable");
		
		quantity.setWidth("50px");
		rob.setWidth("50px");
		
		item.getItemProperty(DELETE_COMPONENT).setValue(deleteButton);
		item.getItemProperty(UNIT_LINK).setValue(unitLink);
		item.getItemProperty(SHORT_CODE).setValue(shortCode);
		item.getItemProperty(DESCRIPTION).setValue(description);
		item.getItemProperty(ROB).setValue(rob);
		item.getItemProperty(QUANTITY).setValue(quantity);
		
		unitLink.setReadOnly(isReadOnly);
		shortCode.setReadOnly(isReadOnly);
		description.setReadOnly(isReadOnly);
		rob.setReadOnly(isReadOnly);
		quantity.setReadOnly(isReadOnly);
		
		if(!isReadOnly){
			unitLink.focus();			
		}
		shortCode.addBlurListener(new BlurListener() {
			
			@Override
			public void blur(BlurEvent event) {
				TextField textField = (TextField) event.getSource();
				String value = textField.getValue();				
				Data data = new IDataImpl().getData(value);
				
				ComboBox unitLinkComboBox = (ComboBox) container.getContainerProperty(id, UNIT_LINK).getValue();
				TextArea descriptionTextField = (TextArea) container.getContainerProperty(id, DESCRIPTION).getValue();
				
				if(data != null){
					descriptionTextField.setValue(data.getDescription());
					unitLinkComboBox.select(data.getUom());					
				}
				else{
					descriptionTextField.setValue("");
					unitLinkComboBox.select(null);
				}
			}
		});
		
		deleteButton.addClickListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				table.removeItem(id);
				
			}
		});
		
	}

	private ComboBox createShortCodeComboBox() {
		ComboBox cb = new ComboBox();
		return cb;
	}

	private ComboBox createUnitLinkComboBox() {
		ComboBox cb = new ComboBox();
		cb.setNullSelectionAllowed(true);
		String[] unitLinks = {"BAG","BAL","BAR","BCH","BDL","BKT","BOX","BTL","C/L","C/S","C/T","CAN","CTN","CUP","CYL",
							  "DOZ","DRM","GRS","HNK","JAR","KEG","KGS","KIT","LGH","LOV","LTR","M3","MTR","PAD","PAK",
							  "PCS","PKG","PKT","PRS","RLS","SET","SHT","SPL","TAB","TIM","TIN","TON","TUB","VOL"};
		for(String s : unitLinks){
			cb.addItem(s);
		}
		return cb;
	}

}
