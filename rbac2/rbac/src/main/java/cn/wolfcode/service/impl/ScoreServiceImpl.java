package cn.wolfcode.service.impl;

import cn.wolfcode.domain.Course;
import cn.wolfcode.domain.QueryObjet;
import cn.wolfcode.domain.Score;
import cn.wolfcode.domain.ScoreQuery;
import cn.wolfcode.mapper.CourseMapper;
import cn.wolfcode.mapper.ScoreMapper;
import cn.wolfcode.service.IScoreService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreServiceImpl implements IScoreService {

    @Autowired
    private ScoreMapper scoreMapper;

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public void addScore(Score score) {
        scoreMapper.addScore(score);
    }

    @Override
    public void updateScore(Score score) {
        scoreMapper.updateScore(score);
    }

    @Override
    public void deleteScore(Long id) {
        if (id != null) {
            scoreMapper.deleteScore(id);
        } else {
            throw new RuntimeException("禁止删除");
        }
    }

    @Override
    public Score getScore(Long id) {
        return scoreMapper.getScore(id);
    }

    @Override
    public PageInfo<Score> selectAllScore(QueryObjet qo) {
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize());
        return new PageInfo<>(scoreMapper.selectAllScore());
    }

    @Override
    public PageInfo<Score> selectScoreByQuery(ScoreQuery query) {
        PageHelper.startPage(query.getCurrentPage(), query.getPageSize());
        return new PageInfo<>(scoreMapper.selectScoreByQuery(query));
    }

    @Override
    public void saveOrUpdate(Score score) {
        if (score == null) {
            throw new RuntimeException("非法参数");
        }
        if (score.getId() == null) {
            this.addScore(score);
        } else {
            this.updateScore(score);
        }
    }

    @Override
    public List<Course> getCourses() {
        return courseMapper.selectAllCourse();
    }
}