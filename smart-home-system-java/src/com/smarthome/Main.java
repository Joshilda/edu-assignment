package com.smarthome;
import com.smarthome.factory.DeviceFactory;
import com.smarthome.hub.Hub;
import com.smarthome.proxy.DeviceProxy;
import com.smarthome.scheduler.Scheduler;
import com.smarthome.triggers.TriggerManager;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
public class Main{
    private static final Logger LOG=Logger.getLogger(Main.class.getName());
    public static void main(String[] args)throws Exception{
        Hub hub=Hub.getInstance();
        Scheduler scheduler=new Scheduler();
        TriggerManager tm=new TriggerManager();
        var light=DeviceFactory.create(1,"light");
        var thermo=DeviceFactory.create(2,"thermostat",70);
        var door=DeviceFactory.create(3,"door");
        var pLight=new DeviceProxy(light,true);
        var pThermo=new DeviceProxy(thermo,true);
        var pDoor=new DeviceProxy(door,true);
        hub.registerDevice(pLight);
        hub.registerDevice(pThermo);
        hub.registerDevice(pDoor);
        hub.executeOnDevice(1,"turnOn",null);
        hub.executeOnDevice(3,"unlock",null);
        scheduler.addTask(new Scheduler.Task(2,nowPlusMinutes(0),"setTemperature",77));
        tm.addTrigger(2,"temperature",">",75,1,"turnOff",null);
        Thread.sleep(3000);
        hub.executeOnDevice(2,"setTemperature",78);
        Thread.sleep(2000);
        LOG.info("Status Report");for(var d:hub.listDevices())System.out.println(Map.of("id",d.getId(),"type",d.getType(),"state",d.getState()));
        LOG.info("Scheduled Tasks");List<Map<String,Object>> tasks=scheduler.listTasks();System.out.println(tasks);
        LOG.info("Triggers");System.out.println(tm.list());
        scheduler.shutdown();
    }
    private static String nowPlusMinutes(int m){java.time.ZonedDateTime d=java.time.ZonedDateTime.now().plusMinutes(m);return String.format("%02d:%02d",d.getHour(),d.getMinute());}
}
