package ssm.pojectTest.design.factory.abstractFactory;

import ssm.pojectTest.design.factory.abstractFactory.utils.XMLUtil;

public class ClientTest {
	public static void main(String [] args) throws Exception {
		SkinFactory factory;
		Button bt;
		TextField tf;
		ComboBox cb;
		
		factory = (SkinFactory) XMLUtil.getBeen();
		bt = factory.createButton();
		tf = factory.createTextField();
		cb = factory.createComboBox();
		
		bt.display();
		tf.display();
		cb.display();
	}
}
