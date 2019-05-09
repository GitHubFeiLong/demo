package ssm.pojectTest.design.factory.abstractFactory;


/**
 * 主题
 * @author msi
 * @date 2019年5月9日
 */
public interface SkinFactory {
	Button createButton();
	TextField createTextField();
	ComboBox createComboBox();
}
