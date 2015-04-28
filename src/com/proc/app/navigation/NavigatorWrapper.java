package com.proc.app.navigation;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import com.proc.app.component.NavigationLinkContainer;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.UI;

public class NavigatorWrapper extends Navigator implements ViewChangeListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4668461480436798945L;
	private Map<String, NavigationItem> navMap = new HashMap<String, NavigationItem>();
	private NavigationLinkContainer linkLayout;

	public NavigatorWrapper(UI ui, ComponentContainer container, NavigationLinkContainer linkLayout) {
		super(ui, container);
		this.linkLayout = linkLayout;
		addViewChangeListener(this);
	}

	public void addView(NavigationItem navigationItem) {
		super.addView(navigationItem.getNavigationId(), navigationItem.getNavigationClass());
		navMap.put(navigationItem.getNavigationId(), navigationItem);
	}

	@Override
	public boolean beforeViewChange(ViewChangeEvent event) {

		return true;
	}

	@Override
	public void afterViewChange(ViewChangeEvent event) {
		String viewId = event.getViewName();
		NavigationItem item = navMap.get(viewId);
		
		linkLayout.removeAllComponents();
		Stack<Button> links = item.getButtonNavigations();
		for(Button b: links){
			linkLayout.addButtonLinks(b);
		}
		linkLayout.setCaption(item.getNavigationCaption());
		
	}
	
	

}
