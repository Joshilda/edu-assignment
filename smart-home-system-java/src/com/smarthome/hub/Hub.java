package com.smarthome.hub;
import com.smarthome.proxy.DeviceProxy;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
public class Hub{
    private static Hub instance;
    private final Map<Integer,DeviceProxy> devices=new ConcurrentHashMap<>();
    private final Map<String,List<Consumer<Map<String,Object>>>> listeners=new ConcurrentHashMap<>();
    private Hub(){}
    public static synchronized Hub getInstance(){
        if(instance==null) instance=new Hub();
        return instance;
    }
    public void registerDevice(DeviceProxy proxy){devices.put(proxy.getId(),proxy); emit("deviceRegistered",Map.of("id",proxy.getId()));}
    public void removeDevice(int id){if(devices.remove(id)!=null) emit("deviceRemoved",Map.of("id",id));}
    public DeviceProxy getDevice(int id){return devices.get(id);}
    public Collection<DeviceProxy> listDevices(){return Collections.unmodifiableCollection(devices.values());}
    public void on(String event,Consumer<Map<String,Object>> cb){listeners.computeIfAbsent(event,k->new ArrayList<>()).add(cb);}
    public void emit(String event,Map<String,Object> payload){for(Consumer<Map<String,Object>> cb:listeners.getOrDefault(event,Collections.emptyList())){try{cb.accept(payload);}catch(Exception ignored){}}}
    public boolean executeOnDevice(int id,String command,Object payload){
        DeviceProxy p=devices.get(id); if(p==null) return false;
        boolean r=p.execute(command,payload);
        emit("deviceStateChanged",Map.of("id",id,"state",p.getState()));
        return r;
    }
}
