package com.smarthome.scheduler;
import com.smarthome.hub.Hub;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.*;
import java.util.logging.Logger;
public class Scheduler{
    private static final Logger LOG=Logger.getLogger(Scheduler.class.getName());
    public static class Task{public final int deviceId;public final String time;public final String command;public final Object payload;public Task(int deviceId,String time,String command,Object payload){this.deviceId=deviceId;this.time=time;this.command=command;this.payload=payload;}}
    private final List<Task> tasks=new CopyOnWriteArrayList<>();
    private final ScheduledExecutorService svc=Executors.newScheduledThreadPool(2);
    private final Hub hub=Hub.getInstance();
    private final DateTimeFormatter TF=DateTimeFormatter.ofPattern("HH:mm");
    public boolean addTask(Task t){
        try{LocalTime.parse(t.time,TF);}catch(Exception e){LOG.severe("Invalid time format");return false;}
        tasks.add(t); schedule(t); LOG.info("Scheduled "+t.command+" for "+t.deviceId+" at "+t.time); return true;
    }
    private void schedule(Task t){
        LocalTime lt=LocalTime.parse(t.time,TF);
        ZonedDateTime now=ZonedDateTime.now();
        ZonedDateTime next=now.withHour(lt.getHour()).withMinute(lt.getMinute()).withSecond(0).withNano(0);
        if(!next.isAfter(now)) next=next.plusDays(1);
        long delay=Duration.between(now,next).toMillis();
        svc.schedule(()->{hub.executeOnDevice(t.deviceId,t.command,t.payload); schedule(t);},delay,TimeUnit.MILLISECONDS);
    }
    public List<Map<String,Object>> listTasks(){List<Map<String,Object>> out=new ArrayList<>();for(Task t:tasks) out.add(Map.of("deviceId",t.deviceId,"time",t.time,"command",t.command));return out;}
    public void shutdown(){svc.shutdown();}
}
