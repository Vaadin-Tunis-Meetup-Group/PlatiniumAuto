package org.mendole.platiniumauto.ui.users.components;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;

import org.mendole.platiniumauto.persistence.model.User;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.converter.StringToDateConverter;
import com.vaadin.data.util.converter.StringToEnumConverter;
import com.vaadin.data.util.filter.Or;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Grid;
import com.vaadin.ui.renderers.DateRenderer;
import com.vaadin.ui.renderers.HtmlRenderer;

public class UsersGrid extends Grid {

	public UsersGrid() {
		setSizeFull();

		setSelectionMode(SelectionMode.SINGLE);

		BeanItemContainer<User> container = new BeanItemContainer<User>(
				User.class);
		container.removeContainerProperty("password");
		container.removeContainerProperty("id");
		setContainerDataSource(container);

		setColumnOrder("name", "email");


//		getColumn("birthDate").setRenderer(
//				new DateRenderer(new SimpleDateFormat("dd/MM/yyyy")));

	}

	//
	// private StringToDateConverter birthdateConverter = new
	// StringToDateConverter() {
	// @Override
	// public String convertToPresentation(Date value,
	// Class<? extends String> targetType, Locale locale)
	// throws com.vaadin.data.util.converter.Converter.ConversionException {
	// String text = super
	// .convertToPresentation(value, targetType, locale);
	//
	// return "";
	// }
	// };

//	private StringToEnumConverter genderTypeConverter = new StringToEnumConverter() {
//		@Override
//		public String convertToPresentation(Enum gender,
//				Class<? extends String> targetType, Locale locale)
//				throws com.vaadin.data.util.converter.Converter.ConversionException {
//			String text = super.convertToPresentation(gender, targetType,
//					locale);
//			String iconCode = "";
//			if (Gender.FEMALE == gender) {
//				iconCode = "<span class=\"v-icon\" style=\"font-family: "
//						+ FontAwesome.FEMALE.getFontFamily()
//						+ ";color:"
//						+ "#b5165f"
//						+ "\">&#x"
//						+ Integer
//								.toHexString(FontAwesome.FEMALE.getCodepoint())
//						+ ";</span>";
//
//			} else if (Gender.MALE == gender) {
//				iconCode = "<span class=\"v-icon\" style=\"font-family: "
//						+ FontAwesome.MALE.getFontFamily() + ";color:"
//						+ "#1bb1dd" + "\">&#x"
//						+ Integer.toHexString(FontAwesome.MALE.getCodepoint())
//						+ ";</span>";
//			} else {
//				return "-";
//			}
//
//			return iconCode + " " + text;
//		}
//	};
//	private StringToEnumConverter bloodTypeConverter = new StringToEnumConverter() {
//		@Override
//		public String convertToPresentation(Enum bloodType,
//				Class<? extends String> targetType, Locale locale)
//				throws com.vaadin.data.util.converter.Converter.ConversionException {
//
//			String text = super.convertToPresentation(bloodType, targetType,
//					locale);
//			String icon = "";
//
//			if (BloodType.A_NEGATIVE == bloodType) {
//				icon = "bloodIcons/a_negative.png";
//			} else if (BloodType.A_POSITIVE == bloodType) {
//				icon = "bloodIcons/a_positive.png";
//			} else if (BloodType.AB_NEGATIVE == bloodType) {
//				icon = "bloodIcons/ab_negative.png";
//			} else if (BloodType.AB_POSITIVE == bloodType) {
//				icon = "bloodIcons/ab_positive.png";
//			} else if (BloodType.B_NEGATIVE == bloodType) {
//				icon = "bloodIcons/b_negative.png";
//			} else if (BloodType.B_POSITIVE == bloodType) {
//				icon = "bloodIcons/b_positive.png";
//			} else if (BloodType.O_NEGATIVE == bloodType) {
//				icon = "bloodIcons/o_negative.png";
//			} else if (BloodType.O_POSITIVE == bloodType) {
//				icon = "bloodIcons/o_positive.png";
//			}
//
//			return icon + text;
//		}
//	};

	/**
	 * Filter the grid based on a search string that is searched for in the user
	 * name, address, email, bloodType, gender and telephone.
	 *
	 * @param filterString
	 *            string to look for
	 */
	public void setFilter(String filterString) {
		getContainer().removeAllContainerFilters();
		if (filterString.length() > 0) {
			SimpleStringFilter fullNameFilter = new SimpleStringFilter(
					"name", filterString, true, false);
			SimpleStringFilter emailFilter = new SimpleStringFilter("email",
					filterString, true, false);
		}

	}

	private BeanItemContainer<User> getContainer() {
		return (BeanItemContainer<User>) super.getContainerDataSource();
	}

	@Override
	public User getSelectedRow() throws IllegalStateException {
		return (User) super.getSelectedRow();
	}

	public void removeUser(User user) {
		getContainer().removeItem(user);
	}

	public void setUsers(Collection<User> users) {
		getContainer().removeAllItems();
		getContainer().addAll(users);
	}
}
