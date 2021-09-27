package com.api.website.models;


public class MinerDashboard {
    private MinerStatistics[] statistics;
    private Workers[] workers;
    private CurrentStatistics currentStatistics;
    private Settings settings;

    public MinerDashboard(){}

    public MinerStatistics[] getStatistics() {
        return statistics;
    }

    public void setStatistics(MinerStatistics[] statistics) {
        this.statistics = statistics;
    }

    public Workers[] getWorkers() {
        return workers;
    }

    public void setWorkers(Workers[] workers) {
        this.workers = workers;
    }

    public CurrentStatistics getCurrentStatistics() {
        return currentStatistics;
    }

    public void setCurrentStatistics(CurrentStatistics currentStatistics) {
        this.currentStatistics = currentStatistics;
    }

    public Settings getSettings() {
        return settings;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }
}
