package com.smartcity.model;

/**
 * Represents a node in the LocationAVLTree.
 * Each node stores the name of a location.
 */

public class LocationNode {
    String locationName;
    int height;
    LocationNode left;
    LocationNode right;

    public LocationNode(String name) {
        this.locationName = name;
        this.height = 1; // Initial height of a new node is 1
        this.left = null;
        this.right = null;
    }
}

