package hct.restaurant.dao;

import hct.restaurant.vo.GameScore;

import java.util.ArrayList;
import java.util.Map;

public interface GameScoreDao {
	public String add(GameScore score);
	public String delete(GameScore score);
	public void update(GameScore score);
	public ArrayList<GameScore> select(Map<String, String> map);
}
