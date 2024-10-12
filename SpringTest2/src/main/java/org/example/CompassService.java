package org.example;


import org.springframework.stereotype.Service;

@Service
public class CompassService {

    private CompassRanges ranges;

    public void setRanges(CompassRanges ranges) {
        this.ranges = ranges;
    }

    public CompassSide getSide(int degree) {
        if (ranges == null) {
            throw new IllegalStateException("Ranges not set");
        }

        if (degree >= parseRange(ranges.getNorth()) || degree < parseRange(ranges.getSouth())) {
            return new CompassSide("North");
        } else if (degree >= parseRange(ranges.getEast()) && degree < parseRange(ranges.getNorth())) {
            return new CompassSide("East");
        } else if (degree >= parseRange(ranges.getSouth()) && degree < parseRange(ranges.getWest())) {
            return new CompassSide("South");
        } else {
            return new CompassSide("West");
        }
    }

    private int parseRange(String range) {
        String[] parts = range.split("-");
        return Integer.parseInt(parts[0]);
    }


}
