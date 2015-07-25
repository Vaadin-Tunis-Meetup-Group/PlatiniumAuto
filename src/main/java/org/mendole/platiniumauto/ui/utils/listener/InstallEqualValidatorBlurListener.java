package org.mendole.platiniumauto.ui.utils.listener;

import org.mendole.platiniumauto.ui.utils.ValidatorUtils;
import org.mendole.platiniumauto.ui.utils.validators.EqualValueValidator;

import com.vaadin.event.FieldEvents.BlurEvent;
import com.vaadin.event.FieldEvents.BlurListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;

public class InstallEqualValidatorBlurListener implements BlurListener {

	Field field1;
	Field field2;

	public InstallEqualValidatorBlurListener(Field field1, Field field2) {
		this.field1 = field1;
		this.field2 = field2;
	}

	@Override
	public void blur(BlurEvent event) {
		field1.removeAllValidators();
		ValidatorUtils.installFieldValidator(field1, new EqualValueValidator(
				field1, field2));

	}

}
