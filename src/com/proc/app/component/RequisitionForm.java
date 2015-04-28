package com.proc.app.component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.proc.bean.Requisition;
import com.vaadin.server.Page;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.Upload.SucceededListener;

public class RequisitionForm extends HorizontalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4123837418676250990L;
	private boolean isReadOnly;
	private Requisition requisition;
	
	public static final String VESSEL_NO = "vessel-no";
	public static final String PRIOROTY = "priority";
	public static final String DATE = "date";
	public static final String CATEGORY_LINK = "category-link";
	public static final String DESCRIPTION = "description";
	public static final String REMARK = "remark";
	
	private TextField vesselNo;
	private ComboBox priority;
	private DateField dateField;
	private ComboBox categoryLink;
	private TextField description;
	private TextArea remarks;
	private FormLayout form1;

	public RequisitionForm(Requisition requisition, boolean isReadOnly){
		this.isReadOnly = isReadOnly;
		this.requisition = requisition;
		setMargin(true);
		addStyleName("procurement-copy-form");
		initForm();
	}

	private void initForm() {
		FormLayout form1 = createForm1();
		FormLayout form2 = createForm2();
		addComponent(form1);
		addComponent(form2);
		insertValue();
	}
	

	private void insertValue() {
		if(requisition != null){
			priority.setReadOnly(false);
			dateField.setReadOnly(false);
			categoryLink.setReadOnly(false);
			description.setReadOnly(false);
			remarks.setReadOnly(false);
			vesselNo.setReadOnly(false);
			
			vesselNo.setValue(requisition.getVesselRefNo());
			priority.select(requisition.getPriority());
			dateField.setValue(requisition.getDate());
			categoryLink.setValue(requisition.getCategory());
			description.setValue(requisition.getDescription());
			remarks.setValue(requisition.getRemarks());
		}
		
		priority.setReadOnly(isReadOnly);
		dateField.setReadOnly(isReadOnly);
		categoryLink.setReadOnly(isReadOnly);
		description.setReadOnly(isReadOnly);
		remarks.setReadOnly(isReadOnly);
		vesselNo.setReadOnly(isReadOnly);
	}

	private FormLayout createForm2() {
		FormLayout form = new FormLayout();
		vesselNo = new TextField("Vessel Ref No"); 
		vesselNo.setReadOnly(isReadOnly);
		form.addComponent(vesselNo);
		
		TextField uploadedFileName = new TextField("File");
		uploadedFileName.setWidth("200px");
		uploadedFileName.setReadOnly(true);
		form.addComponent(uploadedFileName);
		
		UploadAttachmentReceiver attachmentReceiver = new UploadAttachmentReceiver(uploadedFileName);
		Upload attachDoc = new Upload();
		attachDoc.setImmediate(true);
		attachDoc.setReadOnly(isReadOnly);
		attachDoc.setReceiver(attachmentReceiver);
		attachDoc.addSucceededListener(attachmentReceiver);
		form.addComponent(attachDoc);
		
		return form;
	}

	private FormLayout createForm1() {
		form1 = new FormLayout();
		priority = new ComboBox("Priority");
		dateField = new DateField("Date");
		categoryLink = new ComboBox("Category Link");
		
		Date currentDate = UI.getCurrent().getPage().getWebBrowser().getCurrentDate();
		dateField.setValue(currentDate);
		
		insertCatecoryLinkItems(categoryLink);
		insertPriorityValue(priority);
		
		description = new TextField("Description");
		remarks = new TextArea("Remarks");
		
		description.setWidth("300px");
		
		remarks.addStyleName("textarea-resizeable");
		remarks.setRows(3);
		remarks.setColumns(23);
		categoryLink.setRequired(true);
		
		priority.setRequired(true);
		dateField.setRequired(true);
		
		form1.addComponent(priority);
		form1.addComponent(dateField);
		form1.addComponent(categoryLink);
		form1.addComponent(description);
		form1.addComponent(remarks);

		
		
		return form1;
		
	}

	private void insertPriorityValue(ComboBox priority) {
		priority.addItem(1);
		priority.addItem(2);
		priority.addItem(3);
		priority.setItemCaption(1, "Urgent");
		priority.setItemCaption(2, "Normal");
		priority.setItemCaption(3, "Low");
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
	
	public Map<String, Object> getRequisitionFormValue(){
		Map<String, Object> m = new HashMap<String, Object>();
		m.put(VESSEL_NO,vesselNo.getValue());
		m.put(PRIOROTY,priority.getValue());
		m.put(DATE, dateField.getValue());
		m.put(CATEGORY_LINK, categoryLink.getValue());
		m.put(DESCRIPTION,description.getValue());
		m.put(REMARK,remarks.getValue());
		return m;
	}
	
	 
	
	public FormLayout getForm1() {
		return form1;
	}

	public void setForm1(FormLayout form1) {
		this.form1 = form1;
	}

	public Requisition getRequisition() {
		return requisition;
	}

	private class UploadAttachmentReceiver implements Receiver, SucceededListener{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private TextField fileNameField;
		private String filename;
		
		public UploadAttachmentReceiver(TextField fileNameField){
			this.fileNameField = fileNameField;
		}

		@Override
		public void uploadSucceeded(SucceededEvent event) {
			fileNameField.setReadOnly(false);
			fileNameField.setValue(filename);
			fileNameField.setReadOnly(true);
		}

		@Override
		public OutputStream receiveUpload(String filename, String mimeType) {
			File file = null;
			FileOutputStream fos = null;
			this.filename = filename;
	        try {
	            file = new File(filename);
	            fos = new FileOutputStream(file);
	        } catch (final java.io.FileNotFoundException e) {
	            new Notification("Could not open file<br/>",
	                             e.getMessage(),
	                             Notification.Type.ERROR_MESSAGE)
	                .show(Page.getCurrent());
	            return null;
	        }
	        return fos; 
		}
		
	}
}
