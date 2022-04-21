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

    /**
     * 从文件读入排行榜
     */
    private void read()throws IOException{
        InputStream inputStream = new FileInputStream("src/score.txt");
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
        String[] strings = str.split("\\*",6);
        String name = strings[0];
        int grade = Integer.parseInt(strings[1]);
        int month = Integer.parseInt(strings[2]);
        int day = Integer.parseInt(strings[3]);
        int hour = Integer.parseInt(strings[4]);
        int minute = Integer.parseInt(strings[5]);
        Score scoreRead = new Score(name,month,day,hour,minute,grade);
        return scoreRead;
    }

    /**
     * 将排序后的结果写入文件
     */
    private void write()throws IOException{
        var writer = new FileWriter("src/score.txt", StandardCharsets.UTF_8, false);
        for(Score score:sort()) {
            writeLine(writer, score);
        }
        writer.close();
    }
    private void writeLine(FileWriter writer, Score score)throws IOException{
        writer.write(score.getUserName() + "*" + score.getScore() + "*" + score.getMonth() + "*" + score.getDay() + "*" + score.getHour() + "*" + score.getMinute());
        writer.write(System.getProperty("line.separator"));
    }

    /**
     * 按分数排序
     */
    private LinkedList<Score> sort(){
        scores.sort(Comparator.comparing(Score::getScore));
        return scores;
    }

    @Override
    public void getScoreTable(Score score){
        scores.add(score);
        try{
            read();
            write();
        }catch (IOException e) {
            e.printStackTrace();
        }
        ListIterator scoresList = scores.listIterator(scores.size());
        int i = 1;
        while(scoresList.hasPrevious()) {
            Score writeScore = (Score)scoresList.previous();
            System.out.printf("第"+"%d"+"名:",i);
            System.out.println(writeScore.getUserName() + "," + writeScore.getScore() + "," + writeScore.getMonth() + "-" + writeScore.getDay() + "," + writeScore.getHour() + ":" + writeScore.getMinute() + "\n");
            i = i + 1;
        }
    }
}
