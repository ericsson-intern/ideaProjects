package hbeat;

import org.osgi.framework.*;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashSet;

public class Activator implements BundleActivator {


    @Override
    public void start(BundleContext context) throws Exception{

        ArrayList SCACHE;
//        Cache remote;
//
//        remote = new Cache(2000);
//        remote.startSyncThread();


        System.out.println("Pulkittd asdais ddjindaa");




        ////////////////////////////////// TEASTING /////////////////////////////
//        Bundle a = remote.loadBundle("org.apache.aries.util");
//        if(a != null){
//            System.out.println("-- FOUND");
//            System.out.println(remote.getJson(a));
//        }
//        else{
//            System.out.println("-- NOT FOUND");
//        }
        /////////////////////////////////////////////////////////////////////////




        for (Bundle B : context.getBundles())
        {
            SCACHE = new ArrayList();
            for (ServiceReference S : B.getRegisteredServices()) {
                SCACHE.add(S.toString());
            }
            System.out.println("---------------------------------");
            System.out.println(SCACHE);
        }

    }

    @Override
    public void stop(BundleContext context) throws Exception{

        System.out.println("margyaaa bc!");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }



}




