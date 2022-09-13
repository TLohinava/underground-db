package com.solvd.underground.structure;

import java.util.List;

public class Station {

    private long id;
    private String name;
    private List<TicketBox> ticketBoxes;
    private List<Turnstile> turnstiles;
    private List<Platform> platforms;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TicketBox> getTicketBoxes() {
        return ticketBoxes;
    }

    public void setTicketBoxes(List<TicketBox> ticketBoxes) {
        this.ticketBoxes = ticketBoxes;
    }

    public List<Turnstile> getTurnstiles() {
        return turnstiles;
    }

    public void setTurnstiles(List<Turnstile> turnstiles) {
        this.turnstiles = turnstiles;
    }

    public List<Platform> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(List<Platform> platforms) {
        this.platforms = platforms;
    }
}