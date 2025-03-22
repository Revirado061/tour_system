package org.example.springboottoursystem.service;

import org.example.springboottoursystem.domain.Building;
import org.example.springboottoursystem.domain.Facility;
import org.example.springboottoursystem.domain.Node;

import java.util.List;
import java.util.Stack;

public interface GraphService {
    List<Building> showAllBuildings();
    List<Node> findShortestLength(String start, List<String> terminal);
    List<Node> findShortestRoute(double[][] graph, String start, List<String> terminal);
    List<Node> findShortestTime(String start, List<String> terminal);
    List<Node> dijkstra(double[][] graph, int start, int terminal);
    List<Facility> showAllFacilities();
    List<Facility> findNearbyFacilities(double radius, String buildingName, String category);
    List<Facility> sortFacilities(List<Facility> table);
}
