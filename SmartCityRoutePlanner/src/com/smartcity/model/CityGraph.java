
package com.smartcity.model;

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


