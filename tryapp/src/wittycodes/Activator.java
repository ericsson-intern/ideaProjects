package wittycodes;


import wittycodes.service.JServer;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;


public class Activator implements BundleActivator {

    private ServiceRegistration registration;

    @Override
    public void start(BundleContext context) throws Exception {

        JServer k = new JServer();
        k.start();

        System.out.println("Registering service: " + JServer.class);
        registration = context.registerService(JServer.class, new JServer(), null);
        new JServer();
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        System.out.println("Un-registering service: " + JServer.class);
        registration.unregister();
    }
}
