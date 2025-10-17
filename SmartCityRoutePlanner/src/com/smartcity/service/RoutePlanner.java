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

public void handleAddLocation(String name) {
        if (locationTree.locationExists(name)) {
            System.out.println("Error: Location '" + name + "' already exists.");
            return;
        }
        locationTree.addLocation(name);
        cityMap.addLocation(name);
        System.out.println("Successfully added location: " + name);
}
public void handleRemoveLocation(String name) {
        if (!locationTree.locationExists(name)) {
            System.out.println("Error: Location '" + name + "' does not exist.");
            return;
        }
        // For a full implementation, you'd add remove to the AVL tree as well.
        // locationTree.removeLocation(name);
        cityMap.removeLocation(name);
    }


