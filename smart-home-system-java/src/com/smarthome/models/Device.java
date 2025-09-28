package com.smarthome.models;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
public abstract class Device {
    protected final int id;
    protected final String type;
    protected final Map<String,Object> state;
    protected Device(int id,String type){
        this.id=id;
        this.type=type;
        this.state=new HashMap<>();
    }
    public int getId(){return id;}
    public String getType(){return type;}
    public Map<String,Object> getState(){
        return Collections.unmodifiableMap(new HashMap<>(state));
    }
    public abstract boolean perform(String command,Object payload) throws Exception;
}
