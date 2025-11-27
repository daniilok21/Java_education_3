package io.github.some_example_name;

import com.badlogic.gdx.utils.TimeUtils;

import java.util.ArrayList;

import io.github.some_example_name.managers.MemoryManager;


public class GameSession {

    public GameState state;
    long nextTrashSpawnTime;
    long nextAsteroidSpawnTime;
    long sessionStartTime;
    long pauseStartTime;
    private int score;
    int destructedTrashNumber;
    int missedTrashNumber;

    public GameSession() {
    }

    public void startGame() {
        state = GameState.PLAYING;
        score = 0;
        destructedTrashNumber = 0;
        missedTrashNumber = 0;
        sessionStartTime = TimeUtils.millis();
        nextTrashSpawnTime = sessionStartTime + (long) (GameSettings.STARTING_TRASH_APPEARANCE_COOL_DOWN
            * getTrashPeriodCoolDown());
        nextAsteroidSpawnTime = sessionStartTime + (long) (GameSettings.STARTING_ASTEROIDS_APPEARANCE_COOL_DOWN
            * getTrashPeriodCoolDown());
    }

    public void pauseGame() {
        state = GameState.PAUSED;
        pauseStartTime = TimeUtils.millis();
    }

    public void resumeGame() {
        state = GameState.PLAYING;
        sessionStartTime += TimeUtils.millis() - pauseStartTime;
    }

    public void endGame() {
        updateScore();
        state = GameState.ENDED;
        ArrayList<Integer> recordsTable = MemoryManager.loadRecordsTable();
        if (recordsTable == null) {
            recordsTable = new ArrayList<>();
        }
        int foundIdx = 0;
        for (; foundIdx < recordsTable.size(); foundIdx++) {
            if (recordsTable.get(foundIdx) < getScore()) break;
        }
        recordsTable.add(foundIdx, getScore());
        MemoryManager.saveTableOfRecords(recordsTable);
        MemoryManager.putTotalScore(score);
    }

    public void destructionRegistration() {
        destructedTrashNumber += 1;
    }
    public void missedRegistration() {
        missedTrashNumber += 1;
    }

    public void updateScore() {
        score = (int) (TimeUtils.millis() - sessionStartTime) / 100 + destructedTrashNumber * 100 - missedTrashNumber * 100;
        if (score < 0) {
            score = 0;
        }
    }

    public int getScore() {
        return score;
    }

    public boolean shouldSpawnTrash() {
        if (nextTrashSpawnTime <= TimeUtils.millis()) {
            nextTrashSpawnTime = TimeUtils.millis() + (long) (GameSettings.STARTING_TRASH_APPEARANCE_COOL_DOWN
                * getTrashPeriodCoolDown());
            return true;
        }
        return false;
    }

    public boolean shouldSpawnAsteroid() {
        if (nextAsteroidSpawnTime <= TimeUtils.millis()) {
            nextAsteroidSpawnTime = TimeUtils.millis() + (long) (GameSettings.STARTING_ASTEROIDS_APPEARANCE_COOL_DOWN
                * getTrashPeriodCoolDown());
            return true;
        }
        return false;
    }

    private float getTrashPeriodCoolDown() {
        return (float) Math.exp(-0.001 * (TimeUtils.millis() - sessionStartTime + 1) / 1000);
    }
}
