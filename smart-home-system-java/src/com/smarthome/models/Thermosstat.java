package com.smarthome.models;
import java.util.logging.Logger;
public class Thermostat extends Device{
    private static final Logger LOG=Logger.getLogger(Thermostat.class.getName());
    public Thermostat(int id,int initialTemp){
        super(id,"thermostat");
        state.put("temperature",initialTemp);
    }
    @Override
    public boolean perform(String command,Object payload) throws Exception{
        if("setTemperature".equals(command)){
            int t=(payload instanceof Number)?((Number)payload).intValue():Integer.parseInt(payload.toString());
            state.put("temperature",t);
            LOG.info("Thermostat "+id+" set to "+t);
            return true;
        }
        LOG.warning("Thermostat "+id+" unknown command "+command);
        return false;
    }
    public int getTemperature(){return ((Number)state.get("temperature")).intValue();}
}
