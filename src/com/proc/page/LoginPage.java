package com.proc.page;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

public class LoginPage extends VerticalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6837614189006556447L;
	private MainPage mainPage;
	
	public LoginPage(MainPage mainPage){
		this.mainPage = mainPage;
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
				mainPage.switchPage(new ProcurementListPage(mainPage));
			}
		});
		
	}

}
