package com.example.countryborders.services;

import com.example.countryborders.exceptions.RouteNotFoundException;

import java.util.Set;

public interface IRouteService {
    Set<String> getRoutesForBorders(String origin, String destination) throws RouteNotFoundException;
}
