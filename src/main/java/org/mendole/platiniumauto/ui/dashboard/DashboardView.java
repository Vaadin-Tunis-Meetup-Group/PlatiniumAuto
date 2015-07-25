package org.mendole.platiniumauto.ui.dashboard;

import javax.annotation.PostConstruct;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ThemeResource;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;

@SpringView(name = DashboardView.VIEW_NAME)
public class DashboardView extends GridLayout implements View {
	public static final String VIEW_NAME = "Dashboard";

	public DashboardView() {
	}

	@PostConstruct
	void init() {
		setSizeFull();
		setColumns(3);
		setRows(3);
		addStyleName("dashboard-view");
		Image image = new Image(null, new ThemeResource("img/logo.jpg"));
		image.setWidth(300, Unit.PERCENTAGE);
		image.setHeight(300, Unit.PERCENTAGE);
		addComponent(image);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub

	}

}
