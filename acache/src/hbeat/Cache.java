package hbeat;
import java.lang.reflect.*;
import java.util.*;

import com.google.gson.*;
import org.osgi.framework.*;

public class Cache {
    public HashMap<String,Bundle> CACHE;
    public long TTL;
    public HashMap<Integer,String> STATES;

    Cache(int timeout){

        TTL = timeout;
        CACHE = new HashMap();
        STATES = new HashMap();
        this.configure_states();

    }

    public Bundle loadBundle(String SymbolicName) {
        if(!CACHE.containsKey(SymbolicName)){
            return null;
        }
        else {
            Bundle a = CACHE.get(SymbolicName);
            return a;
        }
    }

    private void configure_states(){
        for (Field f : Bundle.class.getDeclaredFields()) {
            if (Modifier.isStatic(f.getModifiers())) {

                f.setAccessible(true);
                try {
                    STATES.put(f.getInt(null),f.getName());
                }
                catch (Exception E){
                    E.printStackTrace();
                }
            }
        }
    }




///////////////////////// FORMATTING ///////////////////

    public String getJson(Bundle a){

        Gson j = new Gson();
        HashMap d = new HashMap();
        d.put("Bundle", a.getSymbolicName());
        d.put("bundleVersion", a.getVersion());
        d.put("id",a.getBundleId());
        d.put("state",STATES.get((Integer)a.getState()));
        d.put("lastModified",a.getLastModified());

        return j.toJson(d);
    }

/////////////////////////////////////////////////////////





////////////////////////// HEARTBEAT /////////////////////////////

    public boolean heartbeat() {


        for (Bundle a : FrameworkUtil.getBundle(Cache.class).getBundleContext().getBundles() ) {
//                System.out.println(a);
            this.CACHE.put(a.getSymbolicName(), a);
        }
//        System.out.println("heartbeat..");
        return true;
    }


    public class Process implements java.lang.Runnable{

        long TTL;
        Process(long ttl){
            TTL = ttl;
        }

        @Override
        public void run() {
            while(true) {
                heartbeat();
                try {
                    Thread.sleep(TTL);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    public Thread startSyncThread(){
        heartbeat();
        Runnable sync = new Process(this.TTL);
        Thread T = new Thread(sync);
        T.start();
        return T;
    }
////////////////////////// HEARTBEAT /////////////////////////////

}
