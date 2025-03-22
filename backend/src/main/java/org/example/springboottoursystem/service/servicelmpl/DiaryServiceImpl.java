package org.example.springboottoursystem.service.servicelmpl;

import jakarta.annotation.Resource;
import org.example.springboottoursystem.domain.*;
import org.example.springboottoursystem.domain.Diary;
import org.example.springboottoursystem.repository.DiaryDao;
import org.example.springboottoursystem.repository.HuffmanTreeDao;
import org.example.springboottoursystem.service.DiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


@Service
public class DiaryServiceImpl implements DiaryService {

    @Resource
    private final DiaryDao diaryDao;
    private final HuffmanTreeDao huffmanTreeDao;

    @Autowired
    public DiaryServiceImpl(DiaryDao diaryDao, HuffmanTreeDao huffmanTreeDao) {
        this.diaryDao = diaryDao;
        this.huffmanTreeDao = huffmanTreeDao;
    }


    @Override
    public int getDiaryNumber() {
        List<Diary> table = diaryDao.findAll();
        return table.size();
    }

    @Override
    public Diary updateGrade(long id, double star) {  //打分 star范围是1~10分
        Diary diary = diaryDao.findById(id);
        double grade = diary.getGrade() * 0.9 + star * 0.1;
        if(grade >= 10){
            grade = 10.0;
        }

        /*格式化grade*/
        DecimalFormat df = new DecimalFormat("0.0");
        String formattedNumber = df.format(grade);
        grade = Double.parseDouble(formattedNumber);

        diary.setGrade(grade);
        diaryDao.save(diary);

        return diary;
    }

    @Override
    public Diary updateHeat(long id) {
        Diary diary = diaryDao.findById(id);
        int heat = diary.getHeat();
        heat++;
        diary.setHeat(heat);
        diaryDao.save(diary);
        return diary;
    }

    @Override
    public List<Diary> sortAllByHeat(List<Diary> table) {
        List<Diary> topTable = new ArrayList<>();
        int n = table.size();
        boolean[] chosen = new boolean[n];
        int max = 0;
        int[] pos = new int[n];
        for(int i = 0; i < n; i++){
            max = -1;
            for(int j = 0; j < n; j++){
                if(table.get(j).getHeat() > max && !chosen[j]){
                    max = table.get(j).getHeat();
                    pos[i] = j;
                }
            }
            chosen[pos[i]] = true;
            Diary topN = new Diary();
            topN.setId(table.get(pos[i]).getId());
            topN.setSpot(table.get(pos[i]).getSpot());
            topN.setAuthor(table.get(pos[i]).getAuthor());
            topN.setTitle(table.get(pos[i]).getTitle());
            topN.setDate(table.get(pos[i]).getDate());
            topN.setHeat(table.get(pos[i]).getHeat());
            topN.setGrade(table.get(pos[i]).getGrade());
            topN.setText(table.get(pos[i]).getText());
            topTable.add(topN);
        }
        return topTable;
    }

    @Override
    public List<Diary> sortAllByGrade(List<Diary> table){
        List<Diary> topTable = new ArrayList<>();
        int n = table.size();
        boolean[] chosen = new boolean[n];
        double max = 0;
        int[] pos = new int[n];
        for(int i = 0; i < n; i++){
            max = 0;
            for(int j = 0; j < n; j++){
                if(table.get(j).getGrade() > max && !chosen[j]){
                    max = table.get(j).getGrade();
                    pos[i] = j;
                }
            }
            chosen[pos[i]] = true;
            Diary topN = new Diary();
            topN.setId(table.get(pos[i]).getId());
            topN.setSpot(table.get(pos[i]).getSpot());
            topN.setTitle(table.get(pos[i]).getTitle());
            topN.setAuthor(table.get(pos[i]).getAuthor());
            topN.setDate(table.get(pos[i]).getDate());
            topN.setHeat(table.get(pos[i]).getHeat());
            topN.setGrade(table.get(pos[i]).getGrade());
            topN.setText(table.get(pos[i]).getText());
            topTable.add(topN);
        }
        return topTable;
    }

    @Override
    public List<Diary> searchBySpot(String keyword) {
        List<Diary> table = diaryDao.findAll();
        List<Diary> result = new ArrayList<>();
        for (Diary diary : table) {
            if(diary.getSpot().contains(keyword)){
                result.add(diary);
            }
        }
        return result;
    }

    @Override
    public List<Diary> searchByTitle(String keyword) {
        List<Diary> table = diaryDao.findAll();
        List<Diary> result = new ArrayList<>();
        for (Diary diary : table) {
            if(diary.getTitle().contains(keyword)){
                result.add(diary);
            }
        }
        return result;
    }

    @Override
    public List<Diary> searchBySpotAndTitle(String spotKeyword, String TitleKeyword) {
        List<Diary> table = diaryDao.findAll();
        List<Diary> result = new ArrayList<>();
        for (Diary diary : table) {
            if(diary.getSpot().contains(spotKeyword) && diary.getTitle().contains(TitleKeyword)){
                result.add(diary);
            }
        }
        return result;
    }

    @Override
    public List<Diary> findAllDiary() {
        return diaryDao.findAll();
    }

    @Override
    public Diary writeDiary(String title, String spot, String author, String text) {

        /*获取当前时间*/
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String time = now.format(formatter);
        int heat = 0;
        double grade = 7.0;
        Diary diary = new Diary(title, spot, author, time, heat, grade, text);
        diaryDao.save(diary);
        diary.setText(this.compressDiary(diary, diary.getText()));
        diaryDao.save(diary);
        diary.setText(text);
        return diary;
    }

    @Override
    public Diary readDiary(long id) {
        Diary diary = diaryDao.findById(id);
        String text = uncompressDiary(diary);
        diary.setText(text);
        return diary;
    }

    @Override
    public String compressDiary(Diary diary, String inputText) {
        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (char c : inputText.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }
        PriorityQueue<HuffmanNode> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(n -> n.frequency));
        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
            priorityQueue.add(new HuffmanNode(entry.getKey(), entry.getValue()));
        }

        while (priorityQueue.size() > 1) {
            HuffmanNode left = priorityQueue.poll();
            HuffmanNode right = priorityQueue.poll();
            HuffmanNode parent = new HuffmanNode('\0', left.frequency + right.frequency, left, right);
            priorityQueue.add(parent);
        }

        HuffmanNode root = priorityQueue.poll();
        Map<Character, String> huffmanCodes = new HashMap<>();
        generateHuffmanCodes(root, "", huffmanCodes);

        for (Map.Entry<Character, String> entry : huffmanCodes.entrySet()) {
            HuffmanTree huffmanTree = new HuffmanTree(diary.getId(), entry.getKey(), entry.getValue());
            huffmanTreeDao.save(huffmanTree);
        }

        // 将霍夫曼编码后的数据转换为字符串
        StringBuilder encodedText = new StringBuilder();
        for (char c : inputText.toCharArray()) {
            encodedText.append(huffmanCodes.get(c));
        }

        String text = encodedText.toString();
        return text;
    }

    public void generateHuffmanCodes(HuffmanNode node, String code, Map<Character, String> huffmanCodes) {
        if (node == null) {
            return;
        }
        if (node.left == null && node.right == null) {
            huffmanCodes.put(node.character, code);
        }
        generateHuffmanCodes(node.left, code + "0", huffmanCodes);
        generateHuffmanCodes(node.right, code + "1", huffmanCodes);
    }

    @Override
    public String uncompressDiary(Diary diary) {
        String huffmanCodes = diary.getText();
        List<HuffmanTree> huffmanTrees = huffmanTreeDao.findByTree(diary.getId());
        String text = new String();
        while(!Objects.equals(huffmanCodes, "")){
            for(HuffmanTree huffmanTree : huffmanTrees){
                if(huffmanCodes.startsWith(huffmanTree.getCode())){  //是前缀
                    text+=huffmanTree.getWord();
                    huffmanCodes = huffmanCodes.replaceFirst("^" + huffmanTree.getCode(), "");
                }
            }
        }
        return text;
    }


}
