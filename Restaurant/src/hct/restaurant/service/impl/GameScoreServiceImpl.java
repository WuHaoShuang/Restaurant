package hct.restaurant.service.impl;

import hct.restaurant.dao.impl.GameScoreDaoImpl;
import hct.restaurant.service.GameScoreService;
import hct.restaurant.vo.GameScore;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
public class GameScoreServiceImpl implements GameScoreService {
@Autowired
private GameScoreDaoImpl dao ;
	@Override
	public String addScore(GameScore score) {
		// TODO Auto-generated method stub
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		score.setTime(df.format(new Date()));
		return dao.add(score);
	}

	@Override
	public ArrayList<GameScore> selectTop10() {
		// TODO Auto-generated method stub
		return dao.selectTop10();
	}
}
