package edu.hitsz.scoretable;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * 数据访问对象接口
 * @author hitsz
 */
public interface ScoreDao {
    public void getScoreTable(Score score);
}
