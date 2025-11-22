package io.github.some_example_name.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.utils.Json;

import java.util.ArrayList;

public class MemoryManager {

    private static final Preferences preferences = Gdx.app.getPreferences("User saves");

    public static void saveSoundSettings(boolean isOn) {
        preferences.putBoolean("isSoundOn", isOn);
        preferences.flush();
    }

    public static boolean loadIsSoundOn() {
        return preferences.getBoolean("isSoundOn", true);
    }

    public static void saveMusicSettings(boolean isOn) {
        preferences.putBoolean("isMusicOn", isOn);
        preferences.flush();
    }

    public static boolean loadIsMusicOn() {
        return preferences.getBoolean("isMusicOn", true);
    }

    public static void saveTableOfRecords(ArrayList<Integer> table) {

        Json json = new Json();
        String tableInString = json.toJson(table);
        preferences.putString("recordTable", tableInString);
        preferences.flush();
    }

    public static ArrayList<Integer> loadRecordsTable() {
        if (!preferences.contains("recordTable"))
            return null;

        String scores = preferences.getString("recordTable");
        Json json = new Json();
        ArrayList<Integer> table = json.fromJson(ArrayList.class, scores);
        return table;
    }

    public static int getDamageLevel() {
        return preferences.getInteger("upgrade_damage", 1);
    }

    public static int getFireRateLevel() {
        return preferences.getInteger("upgrade_fire_rate", 1);
    }

    public static int getHealthLevel() {
        return preferences.getInteger("upgrade_health", 1);
    }

    public static int getTotalScore() {
        return preferences.getInteger("upgrade_total_score", 0);
    }
    public static void putDamageLevel() {
        preferences.putInteger("upgrade_damage", getDamageLevel() + 1);
        preferences.flush();
    }
    public static void putFireRateLevel() {
        preferences.putInteger("upgrade_fire_rate", getFireRateLevel() + 1);
        preferences.flush();
    }
    public static void putHealthLevel() {
        preferences.putInteger("upgrade_health", getHealthLevel() + 1);
        preferences.flush();
    }
    public static void putTotalScore(int totalScore) {
        preferences.putInteger("upgrade_total_score", getTotalScore() + totalScore);
        preferences.flush();
    }
    public static void pickUpTotalScore(int totalScore) {
        preferences.putInteger("upgrade_total_score", getTotalScore() - totalScore);
        preferences.flush();
    }
    public static void clearAllData() {
        preferences.clear();
        preferences.flush();
    }
}
