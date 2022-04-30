package edu.hitsz.scoretable;

import java.util.LinkedList;
import java.util.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
/**
 * 分数表实现类
 * @author hitsz
 */
public class ScoreTable implements ScoreDao{
    private LinkedList<Score> scores = new LinkedList<>();
    private String nameOfFile;
    public ScoreTable(String nameOfFile){
        this.nameOfFile = nameOfFile;
    }
    /**
     * 从文件读入排行榜
     */
    @Override
    public void read(String filename)throws IOException{
        InputStream inputStream = new FileInputStream(filename);
        Reader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader in = new BufferedReader(inputStreamReader);
        String string;
        while((string = in.readLine()) != null) {
            Score score = readLine(string);
            scores.add(score);
        }
    }

    /**
     * 将一行数据划分为Score格式
     */
    private Score readLine(String str){
        String[] strings = str.split("\\*",7);
        String name = strings[1];
        int grade = Integer.parseInt(strings[2]);
        int month = Integer.parseInt(strings[3]);
        int day = Integer.parseInt(strings[4]);
        int hour = Integer.parseInt(strings[5]);
        int minute = Integer.parseInt(strings[6]);
        Score scoreRead = new Score(name,month,day,hour,minute,grade);
        return scoreRead;
    }

    /**
     * 将排序后的结果写入文件
     */
    @Override
    public void write(String filename)throws IOException{
        var writer = new FileWriter(filename, StandardCharsets.UTF_8, false);
        for(Score score:sort()) {
            writeLine(writer, score);
        }
        writer.close();
    }
    private void writeLine(FileWriter writer, Score score)throws IOException{
        writer.write((scores.indexOf(score)+1) + "*" + score.getUserName() + "*" + score.getScore() + "*" + score.getMonth() + "*" + score.getDay() + "*" + score.getHour() + "*" + score.getMinute());
        writer.write(System.getProperty("line.separator"));
    }

    /**
     * 按分数从大到小排序
     */
    private LinkedList<Score> sort(){
        scores.sort(Comparator.comparing(Score::getScore).reversed());
        return scores;
    }

    @Override
    public void getScoreTable(Score score){
        scores.add(score);
        try{
            read(nameOfFile);
            write(nameOfFile);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void clear(File file){
        try{
            FileWriter fileWriter = new FileWriter(file,false);
            fileWriter.write("");
            fileWriter.flush();
            fileWriter.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public LinkedList<Score> getScores(){
        return scores;
    }
}
