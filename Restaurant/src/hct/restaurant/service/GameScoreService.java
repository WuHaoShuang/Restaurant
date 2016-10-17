package hct.restaurant.service;

import java.util.ArrayList;
import java.util.Map;

import hct.restaurant.vo.GameScore;

public interface GameScoreService {
public String addScore(GameScore score);
public ArrayList<GameScore> selectTop10();
}
