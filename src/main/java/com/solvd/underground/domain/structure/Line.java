package com.solvd.underground.domain.structure;

import java.util.List;

public class Line {

    private Long id;
    private String name;
    private List<Station> stations;
    private Depot depot;

    public static LineBuilder builder() {
        return new LineBuilder(new Line());
    }

    public LineBuilder toBuilder() {
        return new LineBuilder(this);
    }

    public static class LineBuilder {

        private final Line line;

        public LineBuilder(Line line) {
            this.line = line;
        }

        public LineBuilder id (Long id) {
            this.line.id = id;
            return this;
        }

        public LineBuilder name (String name) {
            this.line.name = name;
            return this;
        }

        public LineBuilder stations (List<Station> stations) {
            this.line.stations = stations;
            return this;
        }

        public LineBuilder depot (Depot depot) {
            this.line.depot = depot;
            return this;
        }

        public Line build() {
            return line;
        }
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Station> getStations() {
        return stations;
    }

    public Depot getDepot() {
        return depot;
    }
}