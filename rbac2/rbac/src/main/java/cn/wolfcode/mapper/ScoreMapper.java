package cn.wolfcode.mapper;

import cn.wolfcode.domain.Score;
import cn.wolfcode.domain.ScoreQuery;

import java.util.List;

public interface ScoreMapper {
    void addScore(Score score);
    void updateScore(Score score);
    void deleteScore(Long id);
    Score getScore(Long id);
    List<Score> selectAllScore();
    List<Score> selectScoreByQuery(ScoreQuery query);
    void deleteByStudentId(Long studentId);
    void deleteByCourseId(Long courseId);
}