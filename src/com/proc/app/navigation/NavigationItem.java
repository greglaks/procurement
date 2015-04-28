package com.proc.app.navigation;

import java.util.Stack;

import com.vaadin.navigator.View;
import com.vaadin.ui.Button;

public class NavigationItem {

	private Class<? extends View> navigationClass;
	private String navigationId;
	private String navigationCaption;
	private Stack<Button> buttonNavigations;

	public static final String REQUISITION_SUPPLY_PAGE = "requisition-supply";
	public static final String REQUISITION_ADD_PAGE = "requisition-add";
	public static final String REQUISITION_VIEW_PAGE = "requisition-view";
	public static final String REQUISITION_COPY_PAGE = "requisition-copy";
	public static final String REQUISITION_EDIT_PAGE = "requisition-edit";
	public static final String LOGIN_PAGE = "main";
	
	public Class<? extends View> getNavigationClass() {
		return navigationClass;
	}
	public void setNavigationClass(Class<? extends View> navigationClass) {
		this.navigationClass = navigationClass;
	}
	public String getNavigationId() {
		return navigationId;
	}
	public void setNavigationId(String navigationId) {
		this.navigationId = navigationId;
	}
	public String getNavigationCaption() {
		return navigationCaption;
	}
	public void setNavigationCaption(String navigationCaption) {
		this.navigationCaption = navigationCaption;
	}
	public Stack<Button> getButtonNavigations() {
		return buttonNavigations;
	}
	public void setButtonNavigations(Stack<Button> buttonNavigations) {
		this.buttonNavigations = buttonNavigations;
	}
	
	

	
}
