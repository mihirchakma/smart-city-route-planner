
package com.smartcity.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CityGraph 
{
    private final Map<String, List<String>> adjList;

    public CityGraph() 
    {
        this.adjList = new HashMap<>();
    }
}

public void addLocation(String location) 
{
    adjList.putIfAbsent(location, new LinkedList<>());
}

public void addRoad(String loc1, String loc2)

{
    adjList.putIfAbsent(loc1, new LinkedList<>());
    adjList.putIfAbsent(loc2, new LinkedList<>());
    adjList.get(loc1).add(loc2);
    adjList.get(loc2).add(loc1);
}


public void removeRoad(String loc1, String loc2) 
{
    if (adjList.containsKey(loc1) && adjList.containsKey(loc2)) 
    {
        adjList.get(loc1).remove(loc2);
        adjList.get(loc2).remove(loc1);
        System.out.println("Successfully removed road between " + loc1 + " and " + loc2);
    } 
    
    else 
    {
        System.out.println("Error: One or both locations not found.");
    }
}

public void removeLocation(String location) 
{
    if (adjList.containsKey(location)) 
    {
        for (String neighbor : adjList.get(location)) 
        {
            adjList.get(neighbor).remove(location);
        }
        adjList.remove(location);
        System.out.println("Successfully removed location '" + location + "' and all its roads.");
    } 
    
    else 
    {
        System.out.println("Error: Location '" + location + "' not found.");
    }
}

public void displayConnections() 
{
    System.out.println("\n--- City Road Connections ---");

    if (adjList.isEmpty())
     {
        System.out.println("No connections to display.");
    } 

    else 
    {
        for (Map.Entry<String, List<String>> entry : adjList.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }
    System.out.println("---------------------------\n");

}


public void performBfs(String startNode)
 {
    if (!adjList.containsKey(startNode)) 
    {
        System.out.println("Start location for traversal not found.");
        return;
    }

    System.out.println("--- Breadth-First Traversal starting from " + startNode + " ---");
    Queue<String> queue = new LinkedList<>();
    Set<String> visited = new HashSet<>();

    queue.add(startNode);
    visited.add(startNode);

    while (!queue.isEmpty()) 
    {
        String currentLocation = queue.poll();
        System.out.print(currentLocation + " ");

        for (String neighbor : adjList.get(currentLocation))
         {
            if (!visited.contains(neighbor)) 
            
            {
                visited.add(neighbor);
                queue.add(neighbor);
            }
        }
    }

    System.out.println("\n----------------------------------------------------");
}






