package com.example.countryborders.services.impl;

import com.example.countryborders.models.Root;
import com.example.countryborders.services.IRouteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service("routeService")
public class RouteServiceImpl implements IRouteService {

    private static Logger LOGGER = LoggerFactory.getLogger(RouteServiceImpl.class);
    RestTemplate restTemplate;

    public RouteServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private final static String URL = "https://raw.githubusercontent.com/mledoze/countries/master/countries.json";
    
    @Override
    public Set<String> getRoutesForBorders(String orig, String dest) throws RuntimeException {
        ResponseEntity<List<Root>> routes = restTemplate.exchange(URL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {});

        if(routes.getStatusCode().is4xxClientError() ||  routes.getStatusCode().is5xxServerError()) {
            LOGGER.error("Error getting information from URL = {} with statusCode  = {}", URL, routes.getStatusCode());
            throw new RuntimeException("Internal Server Error");
        }

        if(routes.getStatusCode() == HttpStatus.OK && routes.getBody() != null && !CollectionUtils.isEmpty(routes.getBody()))
        {
            Optional<Root> origin = routes.getBody().stream().filter(root -> root.cioc.equalsIgnoreCase(orig)).findAny();
            Optional<Root> destination = routes.getBody().stream().filter(root -> root.cioc.equalsIgnoreCase(dest)).findAny();

            List<String> originBorders = origin.map(org -> org.borders).orElse(Collections.emptyList());
            List<String> destinationBorders = destination.map(dst -> dst.borders).orElse(Collections.emptyList());

            if(!(CollectionUtils.isEmpty(originBorders) && CollectionUtils.isEmpty(destinationBorders))) {
                Set<String> retSet = new LinkedHashSet<>(Arrays.asList(orig));
                var abc = getIntersection(originBorders, destinationBorders);
                retSet.addAll(abc);
                retSet.add(dest);
                return retSet;
            }
        }

        return Collections.EMPTY_SET;
    }
    

    private Set<String> getIntersection(List<String> first, List<String> second) {
        return first.stream()
                .distinct()
                .filter(second::contains)
                .collect(Collectors.toSet());
    }
}
