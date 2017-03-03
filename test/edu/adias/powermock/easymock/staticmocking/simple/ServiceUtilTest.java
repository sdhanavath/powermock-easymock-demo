package edu.adias.powermock.easymock.staticmocking.simple;

import static org.easymock.EasyMock.expect;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ServiceUtil.class)
public class ServiceUtilTest {

	@Test
	public void testGetFullName() {
		String expected = "SAIDA DHANAVATH";

		PowerMock.mockStatic(ServiceUtil.class);
		
		//expect(ServiceUtil.getFirstName()).andReturn("SAIDA");
		
		//expect(ServiceUtil.getLastName()).andReturn("DHANAVATH");
		
		expect(ServiceUtil.getFullName()).andReturn("SAIDA DHANAVATH");
		
		PowerMock.replayAll();
		
		String actual = ServiceUtil.getFullName();
		
		PowerMock.verifyAll();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testGetFullDetails() {
		String expected = "SAIDA DHANAVATH HYDERABAD TELANGANA INDIA";

		PowerMock.mockStatic(ServiceUtil2.class);
		PowerMock.mockStatic(ServiceUtil.class);
		
		expect(ServiceUtil2.getFullAddress()).andReturn("HYDERABAD TELANGANA INDIA");
		
		expect(ServiceUtil.getFullName()).andReturn("SAIDA DHANAVATH");
		
		PowerMock.replayAll();
		
		String actual = ServiceUtil.getFullDetails();
		
		PowerMock.verifyAll();
		
		assertEquals(expected, actual);
	}
	

}
