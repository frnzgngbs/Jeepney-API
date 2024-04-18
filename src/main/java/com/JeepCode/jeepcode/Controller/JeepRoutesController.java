package com.JeepCode.jeepcode.Controller;
import com.JeepCode.jeepcode.Entity.Waypoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.JeepCode.jeepcode.Service.JeepService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")

public class JeepRoutesController {

    @Autowired
    private JeepService jeepService;


    @GetMapping("jeepney")
    public ResponseEntity<?> getWaypointsByCodes(@RequestParam List<String> codes) {
        Map<String, List<Map<String, Object>>> responseMap = new HashMap<>();

        for (String code : codes) {
            List<Waypoint> waypoints = jeepService.findWaypointsByJeepRoutesCode(code);
            List<Map<String, Object>> waypointsInfo = new ArrayList<>();

            for (Waypoint waypoint : waypoints) {
                Map<String, Object> waypointInfo = new HashMap<>();
                waypointInfo.put("id", waypoint.getId());
                waypointInfo.put("name", waypoint.getName());
                waypointsInfo.add(waypointInfo);
            }

            responseMap.put(code, waypointsInfo);
        }

        if (responseMap.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(responseMap);
        }
    }
}
