package edu.hitsz.scoretable;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * 数据访问对象接口
 * @author hitsz
 */
public interface ScoreDao {
    void getScoreTable(Score score);
    void read(String filename)throws IOException;
    void write(String filename)throws IOException;
    void clear(File file);
}
