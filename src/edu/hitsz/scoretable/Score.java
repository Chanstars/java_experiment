package edu.hitsz.scoretable;

/**
 * 分数数值对象类
 * @author hitsz
 */
public class Score {
    private String userName;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private int score;
    public Score(String userName, int month, int day, int hour, int minute, int score){
        this.userName = userName;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.score = score;
    }
    public String getUserName(){
        return userName;
    }

    public int getMonth(){
        return month;
    }

    public int getDay(){
        return day;
    }

    public int getHour(){
        return hour;
    }

    public int getMinute(){
        return minute;
    }

    public int getScore(){
        return score;
    }
}
