package ssm.pojectTest.design.factory.abstractFactory;

import ssm.pojectTest.design.factory.abstractFactory.iml.SpringButton;
import ssm.pojectTest.design.factory.abstractFactory.iml.SpringComboBox;
import ssm.pojectTest.design.factory.abstractFactory.iml.SpringTextField;

/**
 * 春天风格主题
 * @author msi
 * @date 2019年5月9日
 */
public class SpringSkinFactory implements SkinFactory{

	@Override
	public Button createButton() {
		
		return new SpringButton();
	}

	@Override
	public TextField createTextField() {
		// TODO Auto-generated method stub
		return new SpringTextField();
	}

	@Override
	public ComboBox createComboBox() {
		// TODO Auto-generated method stub
		return new SpringComboBox();
	}

}
