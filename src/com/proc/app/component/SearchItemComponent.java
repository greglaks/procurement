package com.proc.app.component;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class SearchItemComponent extends VerticalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2631054990176020296L;
	
	public SearchItemComponent(){
		initSearchPage();
	}

	private void initSearchPage() {
		setCaption("Search");
		setSizeUndefined();
		addStyleName("procurement-mainpage-search");
		
		HorizontalLayout searchFormLayout = new HorizontalLayout();
		HorizontalLayout searchTextLayout = new HorizontalLayout();
		
		HorizontalLayout criteriaLayout = new HorizontalLayout();
		
		setMargin(true);
		setSpacing(true);
		
		searchFormLayout.setSpacing(true);
		criteriaLayout.setSpacing(true);
		
		addComponent(searchFormLayout);
		addComponent(criteriaLayout);
		
		searchFormLayout.addComponent(searchTextLayout);
		
		TextField searchText = new TextField();
		searchText.setInputPrompt("Search");
		Button searchButton = new Button("Search");
		Button showAllButton = new Button("Show all");
		
		searchTextLayout.addComponent(searchText);
		searchTextLayout.addComponent(searchButton);
		searchFormLayout.addComponent(showAllButton);
		
		searchButton.setPrimaryStyleName("button-primary");
		
		OptionGroup single = new OptionGroup();
		single.addStyleName("horizontal");
		single.addItems("Exact pharse","All words","Any words");
		criteriaLayout.addComponent(single);
		
		
	}

}
