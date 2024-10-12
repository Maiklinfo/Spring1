package org.example;

import org.springframework.web.bind.annotation.*;

@RestController
public class CompassController {
    private final CompassService compassService;


    public CompassController(CompassService compassService) {
        this.compassService = compassService;
    }

    @PostMapping("/ranges")
    public void setRanges(@RequestBody CompassRanges ranges) {
        compassService.setRanges(ranges);
    }

    @GetMapping("/side")
    public CompassSide getSide(@RequestParam int degree) {
        return compassService.getSide(degree);
    }
}
