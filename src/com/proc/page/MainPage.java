package com.proc.page;

import java.util.Calendar;
import java.util.Date;

import javax.swing.plaf.basic.BasicBorders.RadioButtonBorder;

import com.proc.app.component.CustomPagedTable;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class MainPage extends HorizontalLayout {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2149160278571938618L;
	private HorizontalLayout linkLayout;
	private VerticalLayout mainLayout = new VerticalLayout();
	public MainPage(){
		setSizeFull();
		initLeftLayout();
		initPage();
	}
	
	private void initLeftLayout() {
		VerticalLayout leftLayout = new VerticalLayout();
		leftLayout.setWidth("200px");
		leftLayout.setHeight("100%");
		addComponent(leftLayout);
		setExpandRatio(leftLayout, 1.0f);
	}

	public void switchPage(Component switchComponent){
		mainLayout.removeAllComponents();
		setMainNavigationLink();
		mainLayout.addComponent(switchComponent);
		mainLayout.setExpandRatio(switchComponent, 4.0f);
		mainLayout.markAsDirty();
	}

	private void initPage() {
		mainLayout.addStyleName("main-proc-page");
		mainLayout.setMargin(true);
		mainLayout.setSpacing(true);
		mainLayout.addStyleName("procurement-main-page");;
		setLoginNavigationLink();
		LoginPage loginPage = new LoginPage(this);
		mainLayout.addComponent(loginPage);
		mainLayout.setExpandRatio(loginPage, 4.0f);
		addComponent(mainLayout);
		setExpandRatio(mainLayout, 5.0f);
	}


	private void setLoginNavigationLink() {
		linkLayout = new HorizontalLayout();
		linkLayout.setSizeUndefined();
		linkLayout.addStyleName("procurement-mainpage-nav");
		mainLayout.addComponent(linkLayout);
		mainLayout.setExpandRatio(linkLayout, 1.0f);
		
		Button homeButton = new Button("Home");
		Label homeSeparator = new Label("/");
		Label login = new Label("Login");
		
		homeButton.setPrimaryStyleName("nav-link");
		login.addStyleName("procurement-nav-standard");
		homeSeparator.addStyleName("procurement-nav-standard");
		
		linkLayout.addComponent(homeButton);
		linkLayout.addComponent(homeSeparator);
		linkLayout.addComponent(login);
	}

	private void setMainNavigationLink() {
		linkLayout = new HorizontalLayout();
		linkLayout.setSizeUndefined();
		linkLayout.addStyleName("procurement-mainpage-nav");
		mainLayout.addComponent(linkLayout);
		mainLayout.setComponentAlignment(linkLayout, Alignment.TOP_LEFT);
		
		Button homeButton = new Button("Home");
		Button supplierButton = new Button("supplies");
		Label homeSeparator = new Label("/");
		homeSeparator.addStyleName("procurement-nav-standard");
		
		homeButton.setPrimaryStyleName("nav-link");
		supplierButton.setPrimaryStyleName("nav-link");
		
		supplierButton.addClickListener(new ClickListener(){

			@Override
			public void buttonClick(ClickEvent event) {
				switchPage(new ProcurementListPage(MainPage.this));
			}
			
		});
		
		linkLayout.addComponent(homeButton);
		linkLayout.addComponent(homeSeparator);
		linkLayout.addComponent(supplierButton);
	}
	
	public void setNavigationLink(String caption){
		Label copyLabel = new Label(caption);
		Label separator = new Label("/");
		
		separator.addStyleName("procurement-nav-standard");
		copyLabel.addStyleName("procurement-nav-standard");
		
		linkLayout.addComponent(separator);
		linkLayout.addComponent(copyLabel);
		linkLayout.markAsDirty();
	}

	private void initMainNavigationLink() {

		
		
	}



}
