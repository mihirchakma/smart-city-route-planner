package com.smartcity.service;

import com.smartcity.model.CityGraph;

import com.smartcity.model.LocationAVLTree;

// (Member 2: Management Logic)

public class RoutePlanner {
    private final LocationAVLTree locationTree;
    private final CityGraph cityMap;

    public RoutePlanner() {
        this.locationTree = new LocationAVLTree();
        this.cityMap = new CityGraph();
}

// Handles adding a new location
public void handleAddLocation(String name) {
        if (locationTree.locationExists(name)) {
            System.out.println("Error: Location '" + name + "' already exists.");
            return;
        }
        locationTree.addLocation(name);
        cityMap.addLocation(name);
        System.out.println("Successfully added location: " + name);
}
// Handles removing a Location
public void handleRemoveLocation(String name) {
        if (!locationTree.locationExists(name)) {
            System.out.println("Error: Location '" + name + "' does not exist.");
            return;
        }
        
        cityMap.removeLocation(name);
    }
    // Handles adding a road
    public void handleAddRoad(String loc1, String loc2) {
        if (!locationTree.locationExists(loc1) || !locationTree.locationExists(loc2)) {
            System.out.println("Error: One or both locations do not exist. Please add them first.");
            return;
        }
        cityMap.addRoad(loc1, loc2);
        System.out.println("Successfully added a road between " + loc1 + " and " + loc2);
    }

    //Handles removing a road
    public void handleRemoveRoad(String loc1, String loc2) {
        cityMap.removeRoad(loc1, loc2);
    }

    // Wrapper to display all locations from the tree
    public void displayAllLocations() {
        locationTree.displayAllLocations();
    }

    // Wrapper to display all connections from the graph
    public void displayAllConnections() {
        cityMap.displayConnections();
    }
}



