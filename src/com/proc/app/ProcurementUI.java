package com.proc.app;

import javax.servlet.annotation.WebServlet;

import com.proc.dao.IData;
import com.proc.dao.IDataImpl;
import com.proc.page.MainPage;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

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
		setContent(mainPage);
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

	
	

}