package com.proc.app.component;

import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

public class ProcurementItemForm extends FormLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4123837418676250990L;
	private boolean isReadOnly;

	public ProcurementItemForm(boolean isReadOnly){
		this.isReadOnly = isReadOnly;
		createForm();
	}

	private void createForm() {
		ComboBox priority = new ComboBox("Priority");
		ComboBox vesselDept = new ComboBox("Vessel Dept");
		ComboBox categoryLink = new ComboBox("Category Link");
		
		insertCatecoryLinkItems(categoryLink);
		
		TextField description = new TextField("Description");
		TextArea remarks = new TextArea("Remarks");
		TextField vesselNo = new TextField("Vessel Ref No");
		
		remarks.addStyleName("textarea-resizeable");
		remarks.setRows(7);
		remarks.setColumns(25);
		categoryLink.setRequired(true);
		
		addComponent(priority);
		addComponent(vesselDept);
		addComponent(categoryLink);
		addComponent(description);
		addComponent(remarks);
		addComponent(vesselNo);
		if(!isReadOnly){
			Button attachButton = new Button("Attach doc");
			addComponent(attachButton);
		}
		addStyleName("procurement-copy-form");
		
		priority.setReadOnly(isReadOnly);
		vesselDept.setReadOnly(isReadOnly);
		categoryLink.setReadOnly(isReadOnly);
		description.setReadOnly(isReadOnly);
		remarks.setReadOnly(isReadOnly);
		vesselNo.setReadOnly(isReadOnly);
		
	}

	private void insertCatecoryLinkItems(ComboBox categoryLink) {
		categoryLink.addItem("Provisions");
		categoryLink.addItem("Cabin Stores");
		categoryLink.addItem("Clothing");
		categoryLink.addItem("Deck Stores");
		categoryLink.addItem("Engine Stores");
		categoryLink.addItem("Marine Paint");
		categoryLink.addItem("Safety Stores");
		categoryLink.addItem("Charts");
		categoryLink.addItem("Nautical Equipment");
		categoryLink.addItem("Medicine");
		categoryLink.addItem("Stationery");
		categoryLink.addItem("Electrical Stores");
		categoryLink.addItem("Lubricants");
		
	}
}
