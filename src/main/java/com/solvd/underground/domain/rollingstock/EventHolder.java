package com.solvd.underground.domain.rollingstock;

import java.util.*;

public class EventHolder {

    private static final Map<EventType, List<IAnnounce>> HOLDER = new HashMap<>();

    public static void arrive(IAnnounce obj, EventType type) {
        HOLDER.computeIfAbsent(type, t -> new ArrayList<>());
        HOLDER.get(type).add(obj);
    }

    public static void depart(IAnnounce obj, EventType type) {
//        HOLDER.get(type).removeIf(i -> i.equals(obj));
    }

    public static void announce(EventType type) {
        List<IAnnounce> arrivals = HOLDER.get(type);
        if(arrivals != null) {
            arrivals.forEach(c -> c.onEvent(type));
        }
    }
}