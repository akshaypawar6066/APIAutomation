package stepDefinations;

import io.cucumber.java.en.Given;

public class DemoSteps {
	@Given("demo is given")
	public void demo_is_given() {
	   System.out.println("Hello Akshay");
	}
}
