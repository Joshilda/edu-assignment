package com.smarthome.triggers;
import com.smarthome.hub.Hub;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;
public class TriggerManager{
    public static class Trigger{public final int id;public final int deviceId;public final String sensor;public final String op;public final Number value;public final int actionDeviceId;public final String actionCommand;public final Object actionPayload;public Trigger(int id,int deviceId,String sensor,String op,Number value,int actionDeviceId,String actionCommand,Object actionPayload){this.id=id;this.deviceId=deviceId;this.sensor=sensor;this.op=op;this.value=value;this.actionDeviceId=actionDeviceId;this.actionCommand=actionCommand;this.actionPayload=actionPayload;}}
    private final List<Trigger> triggers=new ArrayList<>();
    private final AtomicInteger next=new AtomicInteger(1);
    private final Hub hub=Hub.getInstance();
    private static final Logger LOG=Logger.getLogger(TriggerManager.class.getName());
    public TriggerManager(){hub.on("deviceStateChanged",this::evaluate);}
    public int addTrigger(int deviceId,String sensor,String op,Number value,int actionDeviceId,String actionCommand,Object actionPayload){
        int id=next.getAndIncrement();
        Trigger t=new Trigger(id,deviceId,sensor,op,value,actionDeviceId,actionCommand,actionPayload);
        triggers.add(t); LOG.info("Trigger "+id+" added"); return id;
    }
    public List<Map<String,Object>> list(){List<Map<String,Object>> out=new ArrayList<>();for(Trigger t:triggers) out.add(Map.of("id",t.id,"deviceId",t.deviceId,"sensor",t.sensor,"op",t.op,"value",t.value,"actionDeviceId",t.actionDeviceId,"actionCommand",t.actionCommand));return out;}
    @SuppressWarnings("unchecked")
    private void evaluate(Map<String,Object> payload){
        Object idObj=payload.get("id");Object stateObj=payload.get("state");
        if(!(idObj instanceof Integer)||!(stateObj instanceof Map)) return;
        int id=(Integer)idObj; Map<String,Object> state=(Map<String,Object>)stateObj;
        for(Trigger t:triggers){if(t.deviceId!=id) continue;Object val=state.get(t.sensor);if(!(val instanceof Number)) continue;double sval=((Number)val).doubleValue();double v=t.value.doubleValue();boolean m=switch(t.op){case \">\"->sval>v;case \"<\"->sval<v;case \"=\"->sval==v;default->false;};if(m){LOG.info(\"Trigger \"+t.id+\" matched\");hub.executeOnDevice(t.actionDeviceId,t.actionCommand,t.actionPayload);}}
    }
}
