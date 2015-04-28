package com.proc.app;

import java.util.Stack;

import javax.servlet.annotation.WebServlet;

import com.proc.app.navigation.NavigationItem;
import com.proc.app.navigation.NavigationManager;
import com.proc.app.navigation.NavigatorWrapper;
import com.proc.dao.IData;
import com.proc.dao.IDataImpl;
import com.proc.page.LoginPage;
import com.proc.page.MainPage;
import com.proc.page.RequestedItemViewPage;
import com.proc.page.RequisitionCopyPage;
import com.proc.page.RequisitionListPage;
import com.proc.page.RequisitionMasterDetail;
import com.proc.page.RequisitionUpdatePage;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.ErrorEvent;
import com.vaadin.server.ErrorHandler;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
@Theme("procurement")
public class ProcurementUI extends UI {
	
	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = ProcurementUI.class, widgetset = "com.proc.app.widgetset.ProcurementWidgetset")
	public static class Servlet extends VaadinServlet {
	}

	private MainPage mainPage = new MainPage();
	
	@Override
	protected void init(VaadinRequest request) {
		NavigatorWrapper navigator = new NavigatorWrapper(this, mainPage.getMainLayout(), mainPage.getNavigationLinkContainer());
		setNavigator(navigator);
		loadNavigations();
		setContent(mainPage);
		navigator.navigateTo(NavigationItem.LOGIN_PAGE);
		
	}

	private void loadNavigations() {
		NavigationManager navigationManager  = new NavigationManager();
		NavigatorWrapper n = (NavigatorWrapper) getNavigator();
		
		n.addView(getLoginPageNavItem(navigationManager));
		n.addView(getRequisitionSupplyNavItem(navigationManager));
		n.addView(getRequisitionViewNavItem(navigationManager));
		n.addView(getRequisitionEditNavItem(navigationManager));
		n.addView(getRequisitionCopyNavItem(navigationManager));
		
	}
	
	private NavigationItem getRequisitionCopyNavItem(
			NavigationManager navigationManager) {
		NavigationItem loginPageNavItem  = new NavigationItem();
		
		loginPageNavItem.setNavigationCaption("Copy");
		loginPageNavItem.setNavigationId(NavigationItem.REQUISITION_COPY_PAGE);
		loginPageNavItem.setNavigationClass(RequisitionCopyPage.class);
		
		Stack<Button> s  = new Stack<Button>();
		s.add(navigationManager.getHome());
		s.add(navigationManager.getRequisitionSupplyButton());
		loginPageNavItem.setButtonNavigations(s);
		
		return loginPageNavItem;
	}

	private NavigationItem getRequisitionEditNavItem(
			NavigationManager navigationManager) {
		NavigationItem loginPageNavItem  = new NavigationItem();
		
		loginPageNavItem.setNavigationCaption("Update");
		loginPageNavItem.setNavigationId(NavigationItem.REQUISITION_EDIT_PAGE);
		loginPageNavItem.setNavigationClass(RequisitionUpdatePage.class);
		
		Stack<Button> s  = new Stack<Button>();
		s.add(navigationManager.getHome());
		s.add(navigationManager.getRequisitionSupplyButton());
		loginPageNavItem.setButtonNavigations(s);
		
		return loginPageNavItem;
	}

	private NavigationItem getRequisitionViewNavItem(
			NavigationManager navigationManager) {
		NavigationItem loginPageNavItem  = new NavigationItem();
		
		loginPageNavItem.setNavigationCaption("REQUESTED ITEM");
		loginPageNavItem.setNavigationId(NavigationItem.REQUISITION_VIEW_PAGE);
		loginPageNavItem.setNavigationClass(RequestedItemViewPage.class);
		
		Stack<Button> s  = new Stack<Button>();
		s.add(navigationManager.getHome());
		s.add(navigationManager.getRequisitionSupplyButton());
		loginPageNavItem.setButtonNavigations(s);
		
		return loginPageNavItem;
	}

	private NavigationItem getLoginPageNavItem(NavigationManager navManager){
		NavigationItem loginPageNavItem  = new NavigationItem();
		
		loginPageNavItem.setNavigationCaption("Login");
		loginPageNavItem.setNavigationId(NavigationItem.LOGIN_PAGE);
		loginPageNavItem.setNavigationClass(LoginPage.class);
		
		Stack<Button> s  = new Stack<Button>();
		s.add(navManager.getHome());
		loginPageNavItem.setButtonNavigations(s);
		
		return loginPageNavItem;
	}
	
	private NavigationItem getRequisitionSupplyNavItem(NavigationManager navManager){
		NavigationItem loginPageNavItem  = new NavigationItem();
		
		loginPageNavItem.setNavigationCaption(null);
		loginPageNavItem.setNavigationId(NavigationItem.REQUISITION_SUPPLY_PAGE);
		loginPageNavItem.setNavigationClass(RequisitionListPage.class);
		
		Stack<Button> s  = new Stack<Button>();
		s.add(navManager.getHome());
		s.add(navManager.getRequisitionSupplyButton());
		loginPageNavItem.setButtonNavigations(s);
		
		return loginPageNavItem;
	}

	public String getPriority(int priority) {
		String p = null;
		switch (priority){
		case 1: p = "Urgent";break;
		case 2: p = "Normal";break;
		case 3: p = "Low";break;
		default: p = "Normal";break;
		}
		return p;
	}

	public MainPage getMainPage() {
		return mainPage;
	}

	public void setMainPage(MainPage mainPage) {
		this.mainPage = mainPage;
	}
	
	public VerticalLayout getLeftMenuLayout(){
		return mainPage.getLeftMenuLayout();
	}

	
	

}