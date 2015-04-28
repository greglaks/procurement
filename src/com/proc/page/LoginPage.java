package com.proc.page;

import com.proc.app.ProcurementUI;
import com.proc.app.menu.DocumentMenuLayout;
import com.proc.app.menu.ReportsMenuLayout;
import com.proc.app.navigation.NavigationItem;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

public class LoginPage extends VerticalLayout implements View{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6837614189006556447L;

	
	public LoginPage(){
		createPage();
	}

	private void createPage() {
		setMargin(true);
		setSpacing(true);
		createLoginForm();
		createLoginChoice();
		createLoginButton();
	}

	private void createLoginForm() {
		FormLayout form = new FormLayout();
		TextField username = new TextField("Username");
		PasswordField password = new PasswordField("Password");
		form.addComponent(username);
		form.addComponent(password);
		addComponent(form);
		
	}

	private void createLoginChoice() {
		OptionGroup loginGroup = new OptionGroup();
		loginGroup.addItems("Auto login until I logout explicity","Save my user name","Always ask for my user name and password");
		addComponent(loginGroup);
		
	}

	private void createLoginButton() {
		Button login = new Button("Login");
		login.setPrimaryStyleName("button-primary");
		addComponent(login);
		login.addClickListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				Navigator nav = UI.getCurrent().getNavigator();
				nav.navigateTo(NavigationItem.REQUISITION_SUPPLY_PAGE);
				
				//TODO:Login action
				createLeftMenuLayout();
			}
		});
		
	}

	protected void createLeftMenuLayout() {
		VerticalLayout l = ((ProcurementUI)UI.getCurrent()).getLeftMenuLayout();
		
		Accordion menu = new Accordion();
		menu.setHeight("100%");
		l.addComponent(menu);
		
		menu.addTab(new DocumentMenuLayout(), "Document");
		menu.addTab(new ReportsMenuLayout(), "Reports");
		menu.addStyleName("left-menu");
		
		
	}

	@Override
	public void enter(ViewChangeEvent event) {
		
	}

}
