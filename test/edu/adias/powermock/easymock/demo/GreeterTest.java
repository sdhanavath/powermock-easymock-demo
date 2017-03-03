package edu.adias.powermock.easymock.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.core.classloader.annotations.SuppressStaticInitializationFor;
import org.powermock.modules.junit4.PowerMockRunner;

import static junit.framework.Assert.assertEquals;
import static org.easymock.EasyMock.expect;
import static org.powermock.api.easymock.PowerMock.*;
import static org.powermock.reflect.Whitebox.invokeMethod;

@RunWith(PowerMockRunner.class)
@SuppressStaticInitializationFor("demo.org.powermock.examples.easymock31.SimpleConfig")
@PrepareForTest( { Greeter.class, Logger.class })
public class GreeterTest {

    @Test
    public void testGetMessage() throws Exception {
        mockStatic(SimpleConfig.class);
        expect(SimpleConfig.getGreeting()).andReturn("Hi");
        expect(SimpleConfig.getTarget()).andReturn("All");
        replay(SimpleConfig.class);

        assertEquals("Hi All", invokeMethod(Greeter.class, "getMessage"));

        verify(SimpleConfig.class);
    }



    @Test(expected = IllegalArgumentException.class)
    public void testRunWhenLoggerThrowsUnexpectedRuntimeException() throws Exception {
        expectNew(Logger.class).andThrow(new IllegalArgumentException("Unexpected exeception"));
        replay(Logger.class);

        invokeMethod(new Greeter(), "run", 10, "Hello");

        verify(Logger.class);
    }

   
    @Test
    @PrepareForTest( { SimpleConfig.class })
    public void assertItsOkToInvokeReflectionMethodsOnClasses() throws Exception {
        new SimpleConfig();
    }
}