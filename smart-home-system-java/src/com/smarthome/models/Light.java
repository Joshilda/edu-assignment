package com.smarthome.models;
import java.util.logging.Logger;
public class Light extends Device{
    private static final Logger LOG=Logger.getLogger(Light.class.getName());
    public Light(int id){
        super(id,"light");
        state.put("status","off");
    }
    @Override
    public boolean perform(String command,Object payload){
        switch(command){
            case "turnOn": state.put("status","on"); LOG.info("Light "+id+" turned on"); return true;
            case "turnOff": state.put("status","off"); LOG.info("Light "+id+" turned off"); return true;
            default: LOG.warning("Light "+id+" unknown command "+command); return false;
        }
    }
}
