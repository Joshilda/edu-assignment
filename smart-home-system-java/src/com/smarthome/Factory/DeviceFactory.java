package com.smarthome.factory;
import com.smarthome.models.*;
public class DeviceFactory{
    public static Device create(int id,String type,Object... opts){
        switch(type){
            case "light": return new Light(id);
            case "thermostat": int t=(opts!=null && opts.length>0 && opts[0] instanceof Integer)?(Integer)opts[0]:70; return new Thermostat(id,t);
            case "door": return new DoorLock(id);
            default: throw new IllegalArgumentException("Unknown device type "+type);
        }
    }
}
