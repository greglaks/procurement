package com.proc.app.component;

import org.vaadin.hene.popupbutton.PopupButton;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

public class SplitButton extends PopupButton {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3722718112971928904L;
	private VerticalLayout content = new VerticalLayout();
	private Object buttonId;
	private float width = 165;
	
	public Object getButtonId() {
		return buttonId;
	}

	public void setButtonId(Object buttonId) {
		this.buttonId = buttonId;
	}

	public SplitButton(String caption){
		setCaption("REQUESTED ITEMS(0)");
		content.setWidth(width, Unit.PIXELS);
		initButton();
	}
	
	private void initButton() {
		setContent(content);
	}
	
	
	public void addButton(Button button){
		button.setPrimaryStyleName("popupbutton-childern");
		button.setWidth("100%");
		content.addComponent(button);
	}
}
