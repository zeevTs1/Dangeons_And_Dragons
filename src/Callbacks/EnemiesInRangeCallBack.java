package Callbacks;

import Tiles.Enemy;

import java.util.List;

public interface EnemiesInRangeCallBack {
    List<Enemy> getEnemies(int range);
}
