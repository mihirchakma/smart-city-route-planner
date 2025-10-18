
package com.smartcity.model;

public class CityGraph 
{
    private final Map<String, List<String>> adjList;

    public CityGraph() 
    {
        this.adjList = new HashMap<>();
    }
}
