package ssm.pojectTest.design.factory.abstractFactory;

import ssm.pojectTest.design.factory.abstractFactory.iml.SpringComboBox;
import ssm.pojectTest.design.factory.abstractFactory.iml.SummerButton;
import ssm.pojectTest.design.factory.abstractFactory.iml.SummerTextField;

/**
 * 夏天风格主题
 * @author msi
 * @date 2019年5月9日
 */
public class SummerSkinFactory implements SkinFactory{

	@Override
	public Button createButton() {
		// TODO Auto-generated method stub
		return new SummerButton();
	}

	@Override
	public TextField createTextField() {
		// TODO Auto-generated method stub
		return new SummerTextField();
	}

	@Override
	public ComboBox createComboBox() {
		// TODO Auto-generated method stub
		return new SpringComboBox();
	}

}
