package com.starter.infra.ctrl;

import com.starter.infra.model.ComponentSpec;
import com.starter.infra.svc.OrchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/infra")
public class OrchCtrl {
    private final OrchService svc;

    public OrchCtrl(OrchService svc) {
        this.svc = svc;
    }

    @GetMapping("/summary")
    public Map<String, Object> summary() {
        return Map.of("total", svc.all().size(), "byCat", svc.byCat().keySet());
    }

    @GetMapping("/components")
    public List<ComponentSpec> list() {
        return svc.all();
    }

    @GetMapping("/categories")
    public Map<String, List<ComponentSpec>> cats() {
        return svc.byCat();
    }

    @GetMapping("/cat/{cat}")
    public List<ComponentSpec> byCat(@PathVariable String cat) {
        return svc.byCat(cat);
    }

    @GetMapping("/components/{name}")
    public ComponentSpec get(@PathVariable String name) {
        return svc.get(name);
    }

    @PostMapping("/components/{name}/start")
    public ComponentSpec start(@PathVariable String name) {
        return svc.start(name);
    }

    @PostMapping("/components/{name}/stop")
    public ComponentSpec stop(@PathVariable String name) {
        return svc.stop(name);
    }

    @PostMapping("/start")
    public List<ComponentSpec> startAll() {
        return svc.startAll();
    }

    @PostMapping("/stop")
    public List<ComponentSpec> stopAll() {
        return svc.stopAll();
    }
}