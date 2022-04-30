package edu.hitsz.swing;

import edu.hitsz.application.ImageManager;
import edu.hitsz.scoretable.Score;
import edu.hitsz.scoretable.ScoreDao;
import edu.hitsz.scoretable.ScoreTable;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.time.LocalDateTime;
import java.util.LinkedList;

/**
 * 得分榜界面
 * @author hitsz
 */
public class Table {
    private int newScore;
    private JTable scoreTable;
    private JTextField tableTitle;
    private JButton deleteButton;
    private JScrollPane scoreTableFrame;
    private JPanel tableFrame;
    private JTextField mode;
    private String inputContent;
    private String fileName;
    private int deleteOption;
    private LinkedList<Score> scores = new LinkedList<>();

    public Table(int grade,int chosenMode) throws IOException{
        newScore = grade;
        inputContent = JOptionPane.showInputDialog(
                null,
                "输入你的名字:",
                "默认内容"
        );
        if(chosenMode == 1){
            fileName = "src/scoreEasy.txt";
            mode.setText("简单模式");
        }
        else if(chosenMode == 2){
            fileName = "src/scoreCommon.txt";
            mode.setText("普通模式");
        }
        else{
            fileName = "src/scoreHard.txt";
            mode.setText("困难模式");
        }
        String[] columnName = {"名次", "玩家名", "得分", "时间"};
        LocalDateTime time = LocalDateTime.now();
        ScoreDao scoreDao = new ScoreTable(fileName);
        Score scoreList = new Score(inputContent,time.getMonthValue(),time.getDayOfMonth(),time.getHour(),time.getMinute(),newScore);
        scoreDao.getScoreTable(scoreList);
        scores = ((ScoreTable) scoreDao).getScores();
        String[][]tableData = new String[scores.size()][4];
        int i = 0;
        for(Score score :scores){
            tableData[i][0] = "第"+(scores.indexOf(score)+1)+"名";
            tableData[i][1] = score.getUserName();
            tableData[i][2] = String.valueOf(score.getScore());
            tableData[i][3] = score.getMonth() + "-" + score.getDay() + "-" + score.getHour() + ":" + score.getMinute();
            i++;
        }
        //表格模型
        DefaultTableModel model = new DefaultTableModel(tableData, columnName){
            @Override
            public boolean isCellEditable(int row, int col){
                return false;
            }
        };

        //JTable并不存储自己的数据，而是从表格模型那里获取它的数据
        scoreTable.setModel(model);
        scoreTableFrame.setViewportView(scoreTable);

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 deleteOption = JOptionPane.showConfirmDialog(
                         null,
                        "确认删除？",
                        "提示",
                        JOptionPane.YES_NO_CANCEL_OPTION
                );
                int row = scoreTable.getSelectedRow();
                if(deleteOption == JOptionPane.YES_OPTION) {
                    if (row != -1) {
                        model.removeRow(row);
                        try{
                            scores = ((ScoreTable) scoreDao).getScores();
                            scores.clear();
                            scoreDao.read(fileName);
                            File file = new File(fileName);
                            scoreDao.clear(file);
                            scores = ((ScoreTable) scoreDao).getScores();
                            scores.remove(row);
                            scoreDao.write(fileName);
                        }catch(Exception i){
                            i.printStackTrace();
                        }
                    }
                }
            }
        });
    }


    public JPanel getPanel() {
        return tableFrame;
    }
}
