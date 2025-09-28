package com.smarthome.proxy;
import com.smarthome.models.Device;
import java.util.Map;
import java.util.logging.Logger;
public class DeviceProxy{
    private static final Logger LOG=Logger.getLogger(DeviceProxy.class.getName());
    private final Device device;
    private boolean allowed;
    public DeviceProxy(Device device,boolean allowed){this.device=device;this.allowed=allowed;}
    public int getId(){return device.getId();}
    public String getType(){return device.getType();}
    public Map<String,Object> getState(){return device.getState();}
    public void setPermission(boolean perm){this.allowed=perm;}
    public boolean execute(String command,Object payload){
        if(!allowed){LOG.warning("Access denied to device "+device.getId()); return false;}
        try{return device.perform(command,payload);}catch(Exception e){LOG.severe("Device "+device.getId()+" failed: "+e.getMessage()); return false;}
    }
}
