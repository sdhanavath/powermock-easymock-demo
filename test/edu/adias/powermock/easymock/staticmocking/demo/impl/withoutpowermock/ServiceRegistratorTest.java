
package edu.adias.powermock.easymock.staticmocking.demo.impl.withoutpowermock;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.powermock.api.easymock.PowerMock.createMock;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.adias.powermock.easymock.staticmocking.demo.osgi.BundleContext;
import edu.adias.powermock.easymock.staticmocking.demo.osgi.ServiceRegistration;

public class ServiceRegistratorTest {
	private ServiceRegistratorWithoutPowerMock tested;

	private BundleContext bundleContextMock;

	private ServiceRegistration serviceRegistrationMock;

	/**
	 * {@inheritDoc}
	 */
	@Before
	public void setUp() {
		tested = new ServiceRegistratorWithoutPowerMock();
		bundleContextMock = EasyMock.createMock(BundleContext.class);
		serviceRegistrationMock = EasyMock.createMock(ServiceRegistration.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@After
	public void tearDown() {
		tested = null;
		bundleContextMock = null;
		serviceRegistrationMock = null;
	}

	/**
	 * Replay all mocks.
	 */
	protected void replayAll() {
		replay(bundleContextMock);
		replay(serviceRegistrationMock);
	}

	/**
	 * Verify all mocks.
	 */
	protected void verifyAll() {
		verify(bundleContextMock);
		verify(serviceRegistrationMock);
	}

	/**
	 * Test for the
	 * {@link ServiceRegistratorWithoutPowerMock#registerService(String, Object)}
	 * method.
	 * 
	 * @throws Exception
	 *             If an error occurs.
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void testRegisterService() throws Exception {
		final String name = "a name";
		final Object object = new Object();
		final long expectedId = 42;

		Method generateIdMethod = ServiceRegistratorWithoutPowerMock.class.getDeclaredMethod("generateId");
		tested = createMock(ServiceRegistratorWithoutPowerMock.class, generateIdMethod);

		Field field = ServiceRegistratorWithoutPowerMock.class.getDeclaredField("bundleContext");
		field.setAccessible(true);
		field.set(tested, bundleContextMock);

		Map<Long, ServiceRegistration> map = new HashMap<Long, ServiceRegistration>();
		field = ServiceRegistratorWithoutPowerMock.class.getDeclaredField("serviceRegistrations");
		field.setAccessible(true);
		field.set(tested, map);

		expect(bundleContextMock.registerService(name, object, null)).andReturn(serviceRegistrationMock);

		expect(tested.generateId()).andReturn(expectedId);

		replayAll();
		replay(tested);

		final long actualId = tested.registerService(name, object);

		verifyAll();
		verify(tested);

		assertEquals(1, map.size());
		final ServiceRegistration serviceRegistration = map.get(expectedId);
		assertNotNull("The id " + actualId + " was not found in the mServiceRegistrations map.", serviceRegistration);
		assertSame(serviceRegistration, serviceRegistrationMock);
	}

	/**
	 * Test for the
	 * {@link ServiceRegistratorWithoutPowerMock#unregisterService(long)}
	 * method.
	 * 
	 * @throws Exception
	 *             If an error occurs.
	 */
	@Test
	public void testUnregisterService() throws Exception {
		Map<Long, ServiceRegistration> map = new HashMap<Long, ServiceRegistration>();
		final long id = 1L;
		map.put(id, serviceRegistrationMock);

		Field field = tested.getClass().getDeclaredField("serviceRegistrations");
		field.setAccessible(true);
		field.set(tested, map);

		serviceRegistrationMock.unregister();
		expectLastCall().times(1);

		replayAll();

		tested.unregisterService(id);

		verifyAll();

		assertTrue("Map should be empty", map.isEmpty());

	}

	/**
	 * Test for the
	 * {@link ServiceRegistratorWithoutPowerMock#unregisterService(long)} method
	 * when the ID doesn't exist.
	 * 
	 * @throws Exception
	 *             If an error occurs.
	 */
	@Test
	public void testUnregisterService_idDoesntExist() throws Exception {
		Map<Long, ServiceRegistration> map = new HashMap<Long, ServiceRegistration>();
		final long id = 1L;

		Field field = tested.getClass().getDeclaredField("serviceRegistrations");
		field.setAccessible(true);
		field.set(tested, map);

		replayAll();

		try {
			tested.unregisterService(id);
			fail("Should throw IllegalStateException");
		} catch (IllegalStateException e) {
			assertEquals("Registration with id " + id + " has already been removed or has never been registered", e.getMessage());
		}

		verifyAll();

		assertTrue("Map should be empty", map.isEmpty());

	}
}
