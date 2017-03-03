package edu.adias.powermock.easymock.greeting;

import static org.easymock.EasyMock.expect;
import static org.junit.Assert.assertEquals;
import static org.powermock.api.easymock.PowerMock.mockStatic;
import static org.powermock.api.easymock.PowerMock.replayAll;
import static org.powermock.api.easymock.PowerMock.verifyAll;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;


@RunWith(PowerMockRunner.class)
@PrepareForTest(Greeter.class)
public class MockStaticExampleTest {

	@Test
	public void mockStaticExample() {
		String expectedGreeting = "greeting";
		String nameToGreet = "name";

		mockStatic(Greeter.class);
		expect(Greeter.getGreeting(nameToGreet)).andReturn(expectedGreeting);
		replayAll();

		String actualGreeting = Greeter.getGreeting(nameToGreet);

		verifyAll();
		assertEquals("Expected and actual greeting did not match", expectedGreeting, actualGreeting);
	}
}
