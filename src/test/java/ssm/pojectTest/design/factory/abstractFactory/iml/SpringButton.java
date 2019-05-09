package ssm.pojectTest.design.factory.abstractFactory.iml;

import ssm.pojectTest.design.factory.abstractFactory.Button;

public class SpringButton implements Button{

	@Override
	public void display() {
		System.out.println("显示浅绿色按钮");
	}

}
