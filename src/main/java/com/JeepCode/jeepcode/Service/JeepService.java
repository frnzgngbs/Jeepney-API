package com.JeepCode.jeepcode.Service;
import com.JeepCode.jeepcode.Controller.JeepRoutesController;
import com.JeepCode.jeepcode.Entity.JeepRoutes;
import com.JeepCode.jeepcode.Entity.RouteWaypoint;
import com.JeepCode.jeepcode.Entity.Waypoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.JeepCode.jeepcode.Repository.JeepRoutesRepo;

import java.util.ArrayList;
import java.util.List;


@Service
public class JeepService {

    @Autowired
    private JeepRoutesRepo jeepRoutesRepo;


    public List<Waypoint> findWaypointsByJeepRoutesCode(String code) {
        List<JeepRoutes> jeepRoutesList = jeepRoutesRepo.findByCode(code);

        List<Waypoint> waypoints = new ArrayList<>();
        for (JeepRoutes jeepRoutes : jeepRoutesList) {
            List<RouteWaypoint> routeWaypoints = jeepRoutes.getRouteWaypoints();
            for (RouteWaypoint routeWaypoint : routeWaypoints) {
                waypoints.add(routeWaypoint.getWaypoint());
            }
        }
        return waypoints;
    }
}

