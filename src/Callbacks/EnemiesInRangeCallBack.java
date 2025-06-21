package Callbacks;

import Tiles.Enemies.Enemy;

import java.util.List;

public interface EnemiesInRangeCallBack {
    List<Enemy> getEnemies(int range);
}
