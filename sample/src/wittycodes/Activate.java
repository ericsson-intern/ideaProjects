package wittycodes;


import org.osgi.framework.*;

public class Activate implements BundleActivator {
    @Override
    public void start(BundleContext context) throws Exception {
        System.out.println("Pulkit is backed by god");
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        System.out.println("aree marja bc");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}
