package com.example.countryborders.controllers;

import com.example.countryborders.exceptions.RouteNotFoundException;
import com.example.countryborders.services.IRouteService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class RouteController {

    IRouteService routeService;

    public RouteController(IRouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping(value = "/routing/{origin}/{destination}", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "http://localhost:8080")
    public Set<String> getRoutes(@PathVariable String origin, @PathVariable String destination) throws RouteNotFoundException {
        return routeService.getRoutesForBorders(origin, destination);
    }

}