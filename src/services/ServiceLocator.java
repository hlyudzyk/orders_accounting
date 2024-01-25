package services;

import java.util.HashMap;

public class ServiceLocator {
    private static ServiceLocator locator;
    private HashMap<String,Service> services = new HashMap<>();

    private ServiceLocator(){}
    public static ServiceLocator getLocator(){
        if(locator == null) locator = new ServiceLocator();
        return locator;
    }

    public void addService(String serviceName, Service service){
        this.services.put(serviceName,service);
    }
    public Service getService(String serviceName){
        return this.services.get(serviceName);
    }

}
