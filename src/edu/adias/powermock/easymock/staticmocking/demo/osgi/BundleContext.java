package edu.adias.powermock.easymock.staticmocking.demo.osgi;

/**
 * Copied method declaration from the OSGi BundleContext interface. It's just
 * here for demonstration purposes.
 */
public interface BundleContext {

	ServiceRegistration registerService(String name, Object serviceImplementation, String filter);

}
