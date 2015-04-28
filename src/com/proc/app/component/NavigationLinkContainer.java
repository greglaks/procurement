package com.proc.app.component;

import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

public class NavigationLinkContainer extends HorizontalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 755701730671610701L;
	
	public NavigationLinkContainer(){
		addStyleName("procurement-mainpage-nav");
		setSizeUndefined();
	}
	
	public void addButtonLinks(Button button){
		button.setPrimaryStyleName("nav-link");
		
		Label homeSeparator = new Label("/");
		homeSeparator.addStyleName("procurement-nav-standard");
		addComponent(button);
		addComponent(homeSeparator);
	}

	@Override
	public void setCaption(String caption) {
		if(caption != null){
			Label navigationCaption = new Label(caption);
			navigationCaption.addStyleName("procurement-nav-standard");
			addComponent(navigationCaption);			
		}
	}
	
	

}
