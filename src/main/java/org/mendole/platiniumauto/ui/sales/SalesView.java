package org.mendole.platiniumauto.ui.sales;

import javax.annotation.PostConstruct;

import org.mendole.platiniumauto.controllers.UserController;
import org.mendole.platiniumauto.persistence.model.CarMake;
import org.mendole.platiniumauto.persistence.model.CarModel;
import org.mendole.platiniumauto.persistence.model.Sale;
import org.mendole.platiniumauto.ui.utils.listener.InstallBeanValidatorBlurListener;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SpringView(name = SalesView.VIEW_NAME)
public class SalesView extends VerticalLayout implements View {

	public static final String VIEW_NAME = "Sales";

	private TextField fullName;
	private TextArea address;
	private TextField phoneNumber;
	private TextField cin;
	private ComboBox carMake;
	private ComboBox carModel;
	
	private Button validate;

	@Autowired
	UserController userController;

	public SalesView() {
	}

	@PostConstruct
	void init() {
		setSizeFull();
		addStyleName("login-screen");
		FormLayout salesForm = new FormLayout();
		salesForm.addStyleName("login-form");
		salesForm.setSizeUndefined();
		salesForm.setMargin(false);

		buildFieldGroup(salesForm);

		VerticalLayout centeringLayout = new VerticalLayout();
		centeringLayout.setSpacing(true);
		centeringLayout.setStyleName("centering-sales-layout");
		centeringLayout.addComponent(salesForm);
		centeringLayout.setComponentAlignment(salesForm, Alignment.MIDDLE_LEFT);
		addComponent(centeringLayout);
	}

	private FieldGroup buildFieldGroup(FormLayout salesLayout) {
		BeanItem<Sale> saleItem = new BeanItem<Sale>(new Sale());
		FieldGroup group = new FieldGroup(saleItem);

		addFullNameField(salesLayout);

		addAddressField(salesLayout);

		addPhoneNumberField(salesLayout);

		addCinField(salesLayout);

		addCarMakeComboBox(salesLayout);

		addCarModelComboBox(salesLayout);

		bindFields(group);

		addBlurListener();

		addButtons(salesLayout, group);

		return group;

	}

	private void addButtons(FormLayout salesLayout, FieldGroup group) {
		CssLayout buttons = new CssLayout();
		buttons.setStyleName("buttons");
		salesLayout.addComponent(buttons);
		buttons.addComponent(validate = new Button("Register"));
		validate.setDisableOnClick(true);
		validate.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				try {
					group.commit();
					BeanItem<Sale> itemDataSource = (BeanItem<Sale>) group.getItemDataSource();
					Sale sale = itemDataSource.getBean();
					//signUp(user);
				} catch (CommitException e) {
					Notification.show("Please check you input");
				} finally {
					validate.setEnabled(true);
				}
			}
		});
		validate.setClickShortcut(ShortcutAction.KeyCode.ENTER);
		validate.addStyleName(ValoTheme.BUTTON_FRIENDLY);
	}

	private void addFullNameField(FormLayout salesLayout) {
		salesLayout.addComponent(fullName = new TextField("FullName", ""));
		fullName.setWidth(15, Unit.EM);
		fullName.setInputPrompt("Enter your full name...");
		fullName.setNullRepresentation("");
		fullName.setRequired(true);
	}

	private void addAddressField(FormLayout salesLayout) {
		salesLayout.addComponent(address = new TextArea("Address"));
		address.setWidth(15, Unit.EM);
		address.setNullRepresentation("");
		address.setRequired(true);
	}

	private void addPhoneNumberField(FormLayout salesLayout) {
		salesLayout.addComponent(phoneNumber = new TextField("Phone "));
		phoneNumber.setWidth(15, Unit.EM);
		phoneNumber.setNullRepresentation("");
		phoneNumber.setRequired(true);
	}

	private void addCinField(FormLayout salesLayout) {
		salesLayout.addComponent(cin = new TextField("CIN "));
		cin.setWidth(15, Unit.EM);
		cin.setNullRepresentation("");
		cin.setRequired(true);
	}

	private void addCarMakeComboBox(FormLayout salesLayout) {
		salesLayout.addComponent(carMake = new ComboBox("Marque "));
		BeanItemContainer<CarMake> carMakeContainer = new BeanItemContainer<CarMake>(CarMake.class);
		// bloodTypeContainer.addAll(Arrays.asList(BloodType.values()));
		// carMake.setContainerDataSource(bloodTypeContainer);
		carMake.setItemCaptionPropertyId("name");
		carMake.setWidth(15, Unit.EM);
		carMake.setNullSelectionAllowed(false);
		carMake.setRequired(true);
	}

	private void addCarModelComboBox(FormLayout salesLayout) {
		salesLayout.addComponent(carModel = new ComboBox("Model "));
		BeanItemContainer<CarModel> carModelContainer = new BeanItemContainer<CarModel>(CarModel.class);
		// bloodTypeContainer.addAll(Arrays.asList(BloodType.values()));
		// carMake.setContainerDataSource(bloodTypeContainer);
		carMake.setItemCaptionPropertyId("name");
		carMake.setWidth(15, Unit.EM);
		carMake.setNullSelectionAllowed(false);
		carMake.setRequired(true);
	}

	private void bindFields(FieldGroup group) {
		group.bind(fullName, "fullName");
		group.bind(address, "address");
		group.bind(phoneNumber, "telephone");
		group.bind(cin, "cin");
		group.bind(carMake, "carMake");
		group.bind(carModel, "carModel");

		group.setBuffered(true);
	}

	private void addBlurListener() {
		fullName.addBlurListener(new InstallBeanValidatorBlurListener(Sale.class, fullName, "fullName"));
		address.addBlurListener(new InstallBeanValidatorBlurListener(Sale.class, address, "address"));
		phoneNumber.addBlurListener(new InstallBeanValidatorBlurListener(Sale.class, phoneNumber, "telephone"));
		cin.addBlurListener(new InstallBeanValidatorBlurListener(Sale.class, cin, "cin"));
		carMake.addBlurListener(new InstallBeanValidatorBlurListener(Sale.class, carMake, "carMake"));
		carModel.addBlurListener(new InstallBeanValidatorBlurListener(Sale.class, carModel, "carModel"));
	}

	@Override
	public void enter(ViewChangeEvent event) {
	}

}
