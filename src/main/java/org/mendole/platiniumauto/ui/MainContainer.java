package org.mendole.platiniumauto.ui;

import org.mendole.platiniumauto.persistence.mapping.GeoPosition;
import org.mendole.platiniumauto.ui.authentication.CurrentUser;
import org.mendole.platiniumauto.ui.dashboard.DashboardView;
import org.mendole.platiniumauto.ui.sales.SalesView;
import org.mendole.platiniumauto.ui.users.UsersView;

import com.vaadin.addon.touchkit.extensions.Geolocator;
import com.vaadin.addon.touchkit.extensions.PositionCallback;
import com.vaadin.addon.touchkit.gwt.client.vcom.Position;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;

/**
 * Content of the UI when the user is logged in.
 * 
 * 
 */
public class MainContainer extends HorizontalLayout implements PositionCallback {
	private Menu menu;

	public MainContainer(Navigator navigator, CssLayout viewContainer) {

		setStyleName("main-screen");

		menu = new Menu(navigator);
		// menu.addView(new SampleCrudView(), SampleCrudView.VIEW_NAME,
		// SampleCrudView.VIEW_NAME, FontAwesome.EDIT);
		menu.addView(new DashboardView(), DashboardView.VIEW_NAME, DashboardView.VIEW_NAME, FontAwesome.DASHBOARD);
		menu.addView(new SalesView(), SalesView.VIEW_NAME, SalesView.VIEW_NAME, FontAwesome.BOOK);
		menu.addView(new UsersView(), UsersView.VIEW_NAME, UsersView.VIEW_NAME, FontAwesome.USERS);
		// menu.addView(new AboutView(), AboutView.VIEW_NAME,
		// AboutView.VIEW_NAME,
		// FontAwesome.INFO_CIRCLE);
		// menu.addView(new TestUserView(), TestUserView.VIEW_NAME,
		// TestUserView.VIEW_NAME, FontAwesome.USERS);

		navigator.addViewChangeListener(viewChangeListener);

		addComponent(menu);
		addComponent(viewContainer);
		setExpandRatio(viewContainer, 1);
		setSizeFull();
	}

	// notify the view menu about view changes so that it can display which view
	// is currently active
	ViewChangeListener viewChangeListener = new ViewChangeListener() {

		@Override
		public boolean beforeViewChange(ViewChangeEvent event) {
			return true;
		}

		@Override
		public void afterViewChange(ViewChangeEvent event) {
			menu.setActiveView(event.getViewName());
		}

	};

	@Override
	public void attach() {
		super.attach();
		Geolocator.detect(this);

	};

	@Override
	public void onSuccess(Position position) {
		GeoPosition geoPosition = new GeoPosition();
		geoPosition.setCurrentPosition(position);
		// CurrentUser.get().setGeoPosition(geoPosition);
	}

	@Override
	public void onFailure(int errorCode) {
		Notification.show("Geolocation request failed. You must grant access for geolocation requests.",
				Type.ERROR_MESSAGE);

	}
}
