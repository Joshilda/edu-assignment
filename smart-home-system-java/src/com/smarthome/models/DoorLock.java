package com.smarthome.models;
import java.util.logging.Logger;
public class DoorLock extends Device{
    private static final Logger LOG=Logger.getLogger(DoorLock.class.getName());
    public DoorLock(int id){
        super(id,"door");
        state.put("locked",true);
    }
    @Override
    public boolean perform(String command,Object payload){
        switch(command){
            case "lock": state.put("locked",true); LOG.info("Door "+id+" locked"); return true;
            case "unlock": state.put("locked",false); LOG.info("Door "+id+" unlocked"); return true;
            default: LOG.warning("Door "+id+" unknown command "+command); return false;
        }
    }
}
