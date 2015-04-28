package com.proc.page;

import com.proc.app.component.NavigationLinkContainer;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

public class MainPage extends HorizontalLayout {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2149160278571938618L;
	private NavigationLinkContainer navigationLinkContainer;
	private VerticalLayout mainLayout = new VerticalLayout();
	private VerticalLayout leftMenuLayout;
	public MainPage(){
		setSizeFull();
		initLeftLayout();
		initPage();
	}
	
	private void initLeftLayout() {
		leftMenuLayout = new VerticalLayout();
		leftMenuLayout.setWidth("200px");
		leftMenuLayout.setHeight("100%");
		addComponent(leftMenuLayout);
	}

	private void initPage() {
		setLoginNavigationLink();
		VerticalLayout rootMainLayout = new VerticalLayout();
		rootMainLayout.addComponent(navigationLinkContainer);
		rootMainLayout.setExpandRatio(navigationLinkContainer, 1.0f);
		rootMainLayout.setMargin(true);
		rootMainLayout.setSpacing(true);
		
		rootMainLayout.addStyleName("main-proc-page");

		rootMainLayout.addComponent(mainLayout);
		rootMainLayout.setExpandRatio(mainLayout, 5.0f);
		addComponent(rootMainLayout);
		setExpandRatio(rootMainLayout, 5.0f);
	}


	private void setLoginNavigationLink() {
		navigationLinkContainer = new NavigationLinkContainer();
	}


	public VerticalLayout getMainLayout() {
		return mainLayout;
	}

	public NavigationLinkContainer getNavigationLinkContainer() {
		return navigationLinkContainer;
	}

	public VerticalLayout getLeftMenuLayout() {
		return leftMenuLayout;
	}

}
