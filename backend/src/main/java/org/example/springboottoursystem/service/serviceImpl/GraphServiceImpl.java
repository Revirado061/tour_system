package org.example.springboottoursystem.service.serviceImpl;

import jakarta.annotation.Resource;
import org.example.springboottoursystem.domain.*;
import org.example.springboottoursystem.mapper.BuildingMapper;
import org.example.springboottoursystem.mapper.EdgeMapper;
import org.example.springboottoursystem.mapper.FacilityMapper;
import org.example.springboottoursystem.mapper.NodeMapper;
import org.example.springboottoursystem.service.GraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.*;

@Service
public class GraphServiceImpl implements GraphService {

    @Resource
    private final NodeMapper nodeMapper;
    private final EdgeMapper edgeMapper;
    private final BuildingMapper buildingMapper;
    private final FacilityMapper facilityMapper;


    @Autowired
    public GraphServiceImpl(NodeMapper nodeMapper, EdgeMapper edgeMapper, BuildingMapper buildingMapper, FacilityMapper facilityMapper) {
        this.nodeMapper = nodeMapper;
        this.edgeMapper = edgeMapper;
        this.buildingMapper = buildingMapper;
        this.facilityMapper = facilityMapper;
    }


    @Override
    public List<Building> showAllBuildings() {
        return buildingMapper.findAll();
    }

    @Override
    public List<Node> findShortestLength(String start, List<String> terminal) {
        List<Edge> table = edgeMapper.findAll();
<<<<<<< HEAD
        List<Node> allNodes = nodeMapper.findAll();
        // 建立id到index和index到id映射
        Map<Long, Integer> idToIndex = new HashMap<>();
        Map<Integer, Long> indexToId = new HashMap<>();
        for (int i = 0; i < allNodes.size(); i++) {
            idToIndex.put(allNodes.get(i).getId(), i);
            indexToId.put(i, allNodes.get(i).getId());
        }
        int n = allNodes.size();
        double[][] graph = new double[n][n];
        for (Edge edge: table){
            Integer startIdx = idToIndex.get(edge.getStartNode());
            Integer endIdx = idToIndex.get(edge.getEndNode());
            if (startIdx == null || endIdx == null) continue;
            graph[startIdx][endIdx] = edge.getLength() / 10;
            graph[endIdx][startIdx] = edge.getLength() / 10;
        }
        return findShortestRoute(graph, start, terminal, idToIndex, indexToId, allNodes);
=======
        int n = table.size();  //节点个数
        double[][] graph = new double[n][n];
        for (Edge edge: table){
            int startNode = Math.toIntExact(edge.getStartNode());
            int endNode = Math.toIntExact(edge.getEndNode());
            graph[startNode - 1][endNode - 1] = edge.getLength() / 10;
            graph[endNode - 1][startNode - 1] = edge.getLength() / 10;
        }
        return findShortestRoute(graph, start, terminal);
>>>>>>> d0d46300093d1196bd0f7da5ee1bae39e074e655
    }


    @Override
    public List<Node> findShortestTime(String start, List<String> terminal) {
        List<Edge> table = edgeMapper.findAll();
<<<<<<< HEAD
        List<Node> allNodes = nodeMapper.findAll();
        Map<Long, Integer> idToIndex = new HashMap<>();
        Map<Integer, Long> indexToId = new HashMap<>();
        for (int i = 0; i < allNodes.size(); i++) {
            idToIndex.put(allNodes.get(i).getId(), i);
            indexToId.put(i, allNodes.get(i).getId());
        }
        int n = allNodes.size();
        double[][] graph = new double[n][n];
        int speed = 10;
        for (Edge edge: table){
            Integer startIdx = idToIndex.get(edge.getStartNode());
            Integer endIdx = idToIndex.get(edge.getEndNode());
            if (startIdx == null || endIdx == null) continue;
            double crowd = edge.getCrowd();
            graph[startIdx][endIdx] = edge.getLength() / (speed * crowd);
            graph[endIdx][startIdx] = edge.getLength() / (speed * crowd);
        }
        return findShortestRoute(graph, start, terminal, idToIndex, indexToId, allNodes);
=======
        int n = table.size();  //节点个数
        double[][] graph = new double[n][n];
        int speed = 10;
        for (Edge edge: table){
            int startNode = Math.toIntExact(edge.getStartNode());
            int endNode = Math.toIntExact(edge.getEndNode());
            double crowd = edge.getCrowd();
            graph[startNode - 1][endNode - 1] = edge.getLength() / (speed * crowd);
            graph[endNode - 1][startNode - 1] = edge.getLength() / (speed * crowd);
        }
        return findShortestRoute(graph, start, terminal);
>>>>>>> d0d46300093d1196bd0f7da5ee1bae39e074e655
    }


    @Override
<<<<<<< HEAD
    public List<Node> findShortestRoute(double[][] graph, String start, List<String> terminal, Map<Long, Integer> idToIndex, Map<Integer, Long> indexToId, List<Node> allNodes) {
        Node s = nodeMapper.findByName(start);
        int startIdx = idToIndex.get(s.getId());
        boolean[] isArrived = new boolean[allNodes.size() + 1];
=======
    public List<Node> findShortestRoute(double[][] graph, String start, List<String> terminal) {
        Node s = nodeMapper.findByName(start);
        int startId = Math.toIntExact(s.getId());
        boolean[] isArrived = new boolean[100];
>>>>>>> d0d46300093d1196bd0f7da5ee1bae39e074e655
        List<Node> result = new ArrayList<>();

        while(true){
            boolean flag = false;
            for(String tNode : terminal){
                Node t = nodeMapper.findByName(tNode);
<<<<<<< HEAD
                int terminalIdx = idToIndex.get(t.getId());
                if(!isArrived[terminalIdx]){
=======
                int terminalId = Math.toIntExact(t.getId());
                if(!isArrived[terminalId]){
>>>>>>> d0d46300093d1196bd0f7da5ee1bae39e074e655
                    flag = true;
                    break;
                }
            }
            if(!flag){
                break;
            }
<<<<<<< HEAD
=======
            /*找出s到每一个t的最短路径，取最短*/
>>>>>>> d0d46300093d1196bd0f7da5ee1bae39e074e655
            double minLen = 0;
            int closestNode = 0;
            List<Node> shortestRoute = new ArrayList<>();
            for(String tNode : terminal){
                Node t = nodeMapper.findByName(tNode);
<<<<<<< HEAD
                int terminalIdx = idToIndex.get(t.getId());
                if(!isArrived[terminalIdx]){
                    List<Node> route = dijkstra(graph, startIdx, terminalIdx, indexToId, allNodes);
                    double len = 0;
                    for(int j = 0; j < route.size() - 1; j++){
                        len += graph[idToIndex.get(route.get(j).getId())][idToIndex.get(route.get(j + 1).getId())];
                    }
                    if(minLen == 0 || len < minLen){
                        minLen = len;
                        closestNode = terminalIdx;
=======
                int terminalId = Math.toIntExact(t.getId());
                if(!isArrived[terminalId]){
                    List<Node> route = dijkstra(graph, startId - 1, terminalId - 1);
                    /*算路径长度*/
                    double len = 0;
                    for(int j = 0; j < route.size() - 1; j++){
                        len += graph[(int) (route.get(j).getId() - 1)][(int) (route.get(j + 1).getId() - 1)];
                    }
                    if(minLen == 0 || len < minLen){
                        minLen = len;
                        closestNode = terminalId;
>>>>>>> d0d46300093d1196bd0f7da5ee1bae39e074e655
                        shortestRoute = route;
                    }
                }
            }
            isArrived[closestNode] = true;
            result.addAll(shortestRoute);
<<<<<<< HEAD
            startIdx = closestNode;
=======
            startId = closestNode;  //更新起点
>>>>>>> d0d46300093d1196bd0f7da5ee1bae39e074e655
        }
        for (int i = 0; i < result.size() - 1; i++) {
            Node node = result.get(i);
            Node nextNode = result.get(i+1);
            if(node.getId()==nextNode.getId()){
                result.remove(node);
            }
        }
        return result;
    }


    @Override
<<<<<<< HEAD
    public List<Node> dijkstra(double[][] graph, int start, int terminal, Map<Integer, Long> indexToId, List<Node> allNodes) {
        int n = graph.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++){
                if(graph[i][j] == 0){
                    graph[i][j] = 5000000;
                }
            }
        }
        boolean[] vis = new boolean[n];
        double[] value = new double[n];
        Arrays.fill(value, 0x3f);
        int[] pre = new int[n];
        Arrays.fill(pre, -1);
        value[start] = 0;
        for (int i = 0; i < n; i++) {
            int k = 0;
            for (int j = 0; j < n; j++) {
=======
    public List<Node> dijkstra(double[][] graph, int start, int terminal) {
        int n = graph.length; // 节点个数
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++){
                if(graph[i][j] == 0){
                    graph[i][j] = 5000000; // 也可以一开始就写好
                }
            }
        }

        boolean[] vis = new boolean[n];  // 标记数组
        double[] value = new double[n];  // 起点s到其他点的距离
        Arrays.fill(value, 0x3f);  // 初始化为最大值
        int[] pre = new int[n];  // 存储每个点的前驱节点
        Arrays.fill(pre, -1);  // 初始化为-1

        // 初始化起点s
        value[start] = 0;  // 起点s到s距离是0
        // 循环n次，就可以将所有点都加入到集合里
        for (int i = 0; i < n; i++) {
            // 寻找当前最短路径
            // 在未获取的顶点中心找到vs
            int k = 0;  // 用来记录，不在S集合中，距离最近的点
            for (int j = 0; j < n; j++) {  // 在没有确定最短路中的所有点找出距离最短的那个点
>>>>>>> d0d46300093d1196bd0f7da5ee1bae39e074e655
                if (!vis[j] && (k == 0 || value[j] <= value[k])) {
                    k = j;
                }
            }
<<<<<<< HEAD
            vis[k] = true;
            for (int j = 0; j < n; j++) {
                if (!vis[j] && value[j] > value[k] + graph[k][j]) {
                    value[j] = value[k] + graph[k][j];
                    pre[j] = k;
                }
            }
        }
=======
            // 循环结束k存的是没有确定节点中离起点s最近点的编号
            // 标记k为获取的最短路径，加入集合S中
            vis[k] = true;

            // 修正当前最短路径和前驱顶点
            // 当已经得到顶点k的最短路径之后，更新未获取的顶点的最短路径和前驱顶点
            for (int j = 0; j < n; j++) {
                if (!vis[j] && value[j] > value[k] + graph[k][j]) {
                    value[j] = value[k] + graph[k][j];
                    pre[j] = k;  // 存储前驱节点
                }
            }
        }

        // 输出最短路径和经过的点
>>>>>>> d0d46300093d1196bd0f7da5ee1bae39e074e655
        List<Node> route = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        int current = terminal;
        while (current != -1) {
            stack.push(current);
<<<<<<< HEAD
            Long nodeId = indexToId.get(current);
            Node node = null;
            for (Node nObj : allNodes) {
                if (nObj.getId().equals(nodeId)) {
                    node = nObj;
                    break;
                }
            }
=======
            Node node = nodeMapper.findById((long) (current + 1)).orElse(null);
>>>>>>> d0d46300093d1196bd0f7da5ee1bae39e074e655
            route.add(node);
            current = pre[current];
        }
        Collections.reverse(route);
        return route;
    }

    @Override
    public List<Facility> showAllFacilities() {
        List<Facility> allFacilities= facilityMapper.findAll();
        return allFacilities;
    }

    @Override
    public List<Facility> findNearbyFacilities(double radius, String buildingName, String category) {
        List<Facility> result = new ArrayList<>();
        List<Facility> allFacilities= facilityMapper.findAll();
        Building building = buildingMapper.findByName(buildingName);
        double buildingX = building.getX();
        double buildingY = building.getY();
        for(Facility facility : allFacilities){
            double facilityX = facility.getX();
            double facilityY = facility.getY();
            double distance = Math.sqrt((facilityX - buildingX) * (facilityX - buildingX) +
                    (facilityY - buildingY) * (facilityY - buildingY));
            /*格式化distance*/
            DecimalFormat df = new DecimalFormat("0.0");
            String formattedNumber = df.format(distance);
            distance = Double.parseDouble(formattedNumber);
            if(distance <= radius){
                facility.setDistance(distance);
                result.add(facility);
            }
        }
        result = this.sortFacilities(result);
        if(Objects.equals(category, "all")){
            return result;
        }
        else{  //筛选类别
            List<Facility> finalResult = new ArrayList<>();
            for(Facility facility : result){
                if(Objects.equals(facility.getCategory(), category)){
                    finalResult.add(facility);
                }
            }
            return finalResult;
        }
    }

    @Override
    public List<Facility> sortFacilities(List<Facility> table) {
        List<Facility> result = new ArrayList<>();
        int n = table.size();
        boolean[] chosen = new boolean[n];
        double min = 100000;
        int[] pos = new int[n];
        for(int i = 0; i < n; i++){
            min = 100000;
            for(int j = 0; j < n; j++){
                if(table.get(j).getDistance() < min && !chosen[j]){
                    min = table.get(j).getDistance();
                    pos[i] = j;
                }
            }
            chosen[pos[i]] = true;
            Facility topN = new Facility(table.get(pos[i]).getId(), table.get(pos[i]).getCategory(), table.get(pos[i]).getName(),
                    table.get(pos[i]).getX(), table.get(pos[i]).getY(), table.get(pos[i]).getDistance());
            result.add(topN);
        }
        return result;
    }
}
