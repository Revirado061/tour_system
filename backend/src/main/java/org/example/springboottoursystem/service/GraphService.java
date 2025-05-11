package org.example.springboottoursystem.service;

import org.example.springboottoursystem.domain.Building;
import org.example.springboottoursystem.domain.Facility;
import org.example.springboottoursystem.domain.Node;

import java.util.List;
<<<<<<< HEAD
import java.util.Map;
=======
>>>>>>> d0d46300093d1196bd0f7da5ee1bae39e074e655
import java.util.Stack;

public interface GraphService {
    List<Building> showAllBuildings();
    List<Node> findShortestLength(String start, List<String> terminal);
<<<<<<< HEAD
    List<Node> findShortestTime(String start, List<String> terminal);
    List<Facility> showAllFacilities();
    List<Facility> findNearbyFacilities(double radius, String buildingName, String category);
    List<Facility> sortFacilities(List<Facility> table);
    List<Node> findShortestRoute(double[][] graph, String start, List<String> terminal, java.util.Map<Long, Integer> idToIndex, java.util.Map<Integer, Long> indexToId, java.util.List<org.example.springboottoursystem.domain.Node> allNodes);
    List<Node> dijkstra(double[][] graph, int start, int terminal, java.util.Map<Integer, Long> indexToId, java.util.List<org.example.springboottoursystem.domain.Node> allNodes);
=======
    List<Node> findShortestRoute(double[][] graph, String start, List<String> terminal);
    List<Node> findShortestTime(String start, List<String> terminal);
    List<Node> dijkstra(double[][] graph, int start, int terminal);
    List<Facility> showAllFacilities();
    List<Facility> findNearbyFacilities(double radius, String buildingName, String category);
    List<Facility> sortFacilities(List<Facility> table);
>>>>>>> d0d46300093d1196bd0f7da5ee1bae39e074e655
}
