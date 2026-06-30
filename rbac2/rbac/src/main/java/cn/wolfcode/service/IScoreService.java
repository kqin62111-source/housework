package cn.wolfcode.service;

import cn.wolfcode.domain.Course;
import cn.wolfcode.domain.QueryObjet;
import cn.wolfcode.domain.Score;
import cn.wolfcode.domain.ScoreQuery;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IScoreService {
    void addScore(Score score);
    void updateScore(Score score);
    void deleteScore(Long id);
    Score getScore(Long id);
    PageInfo<Score> selectAllScore(QueryObjet qo);
    PageInfo<Score> selectScoreByQuery(ScoreQuery query);
    void saveOrUpdate(Score score);
    List<Course> getCourses();
}