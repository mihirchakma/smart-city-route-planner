package com.smartcity.model;

import java.util.*;

/**
 * Represents the city's transport network using a graph (adjacency list).
 * Keys are location names (vertices), and values are lists of connected locations (edges).
 */
public class CityGraph {
    private final Map<String, List<String>> adjList;

    public CityGraph() {
        this.adjList = new HashMap<>();
    }

    // Add a location (vertex) to the graph
    public void addLocation(String location) {
        adjList.putIfAbsent(location, new LinkedList<>());
    }

    // Add a road (undirected edge) between two locations
    public void addRoad(String loc1, String loc2) {
        // Ensure both locations exist in the graph first
        adjList.putIfAbsent(loc1, new LinkedList<>());
        adjList.putIfAbsent(loc2, new LinkedList<>());

        // Add edge from loc1 to loc2
        adjList.get(loc1).add(loc2);
        // Add edge from loc2 to loc1 for an undirected graph
        adjList.get(loc2).add(loc1);
    }

    // Remove a road (undirected edge)
    public void removeRoad(String loc1, String loc2) {
        if (adjList.containsKey(loc1) && adjList.containsKey(loc2)) {
            adjList.get(loc1).remove(loc2);
            adjList.get(loc2).remove(loc1);
            System.out.println("Successfully removed road between " + loc1 + " and " + loc2);
        } else {
            System.out.println("Error: One or both locations not found.");
        }
    }

    // Remove a location (vertex) and all associated roads
    public void removeLocation(String location) {
        if (adjList.containsKey(location)) {
            // Remove all edges pointing to this location from other nodes
            for (String neighbor : adjList.get(location)) {
                adjList.get(neighbor).remove(location);
            }
            // Remove the location vertex itself
            adjList.remove(location);
            System.out.println("Successfully removed location '" + location + "' and all its roads.");
        } else {
            System.out.println("Error: Location '" + location + "' not found.");
        }
    }

    // Display all connections in the graph [cite: 8]
    public void displayConnections() {
        System.out.println("\n--- City Road Connections ---");
        if (adjList.isEmpty()) {
            System.out.println("No connections to display.");
        } else {
            for (Map.Entry<String, List<String>> entry : adjList.entrySet()) {
                System.out.println(entry.getKey() + " -> " + entry.getValue());
            }
        }
        System.out.println("---------------------------\n");
    }

    // Requirement: Use a Queue for traversal (BFS) [cite: 9]
    public void performBfs(String startNode) {
        if (!adjList.containsKey(startNode)) {
            System.out.println("Start location for traversal not found.");
            return;
        }

        System.out.println("--- Breadth-First Traversal starting from " + startNode + " ---");
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.add(startNode);
        visited.add(startNode);

        while (!queue.isEmpty()) {
            String currentLocation = queue.poll();
            System.out.print(currentLocation + " ");

            for (String neighbor : adjList.get(currentLocation)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
        System.out.println("\n----------------------------------------------------");
    }
}
