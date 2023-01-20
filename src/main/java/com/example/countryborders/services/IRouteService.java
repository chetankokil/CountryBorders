package com.example.countryborders.services;

import java.util.Set;

public interface IRouteService {
    Set<String> getRoutesForBorders(String origin, String destination) throws RuntimeException;
}
