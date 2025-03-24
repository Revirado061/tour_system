package org.example.springboottoursystem.service.servicelmpl;

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
        int n = table.size();  //节点个数
        double[][] graph = new double[n][n];
        for (Edge edge: table){
            int startNode = Math.toIntExact(edge.getStartNode());
            int endNode = Math.toIntExact(edge.getEndNode());
            graph[startNode - 1][endNode - 1] = edge.getLength() / 10;
            graph[endNode - 1][startNode - 1] = edge.getLength() / 10;
        }
        return findShortestRoute(graph, start, terminal);
    }


    @Override
    public List<Node> findShortestTime(String start, List<String> terminal) {
        List<Edge> table = edgeMapper.findAll();
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
    }


    @Override
    public List<Node> findShortestRoute(double[][] graph, String start, List<String> terminal) {
        Node s = nodeMapper.findByName(start);
        int startId = Math.toIntExact(s.getId());
        boolean[] isArrived = new boolean[100];
        List<Node> result = new ArrayList<>();

        while(true){
            boolean flag = false;
            for(String tNode : terminal){
                Node t = nodeMapper.findByName(tNode);
                int terminalId = Math.toIntExact(t.getId());
                if(!isArrived[terminalId]){
                    flag = true;
                    break;
                }
            }
            if(!flag){
                break;
            }
            /*找出s到每一个t的最短路径，取最短*/
            double minLen = 0;
            int closestNode = 0;
            List<Node> shortestRoute = new ArrayList<>();
            for(String tNode : terminal){
                Node t = nodeMapper.findByName(tNode);
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
                        shortestRoute = route;
                    }
                }
            }
            isArrived[closestNode] = true;
            result.addAll(shortestRoute);
            startId = closestNode;  //更新起点
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
                if (!vis[j] && (k == 0 || value[j] <= value[k])) {
                    k = j;
                }
            }
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
        List<Node> route = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        int current = terminal;
        while (current != -1) {
            stack.push(current);
            Node node = nodeMapper.findById((long) (current + 1)).orElse(null);
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
