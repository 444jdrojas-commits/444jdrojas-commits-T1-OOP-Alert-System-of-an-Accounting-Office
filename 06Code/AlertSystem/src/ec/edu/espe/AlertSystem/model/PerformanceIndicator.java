/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.AlertSystem.model;

/**
 *
 * @author Paulo Ramos
 */
public class PerformanceIndicator {
    private String period;
    private Assistant assistant;
    private double completionRate;
    private double averageDelay;
    private double efficiencyPerDay;
    private double finalScore;
    private String comments;

    public PerformanceIndicator(String period, Assistant assistant, double completionRate, double averageDelay, double efficiencyPerDay, double finalScore, String comments) {
        this.period = period;
        this.assistant = assistant;
        this.completionRate = completionRate;
        this.averageDelay = averageDelay;
        this.efficiencyPerDay = efficiencyPerDay;
        this.finalScore = finalScore;
        this.comments = comments;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public void setAssistant(Assistant assistant) {
        this.assistant = assistant;
    }

    public void setCompletionRate(double completionRate) {
        this.completionRate = completionRate;
    }

    public void setAverageDelay(double averageDelay) {
        this.averageDelay = averageDelay;
    }

    public void setEfficiencyPerDay(double efficiencyPerDay) {
        this.efficiencyPerDay = efficiencyPerDay;
    }

    public void setFinalScore(double finalScore) {
        this.finalScore = finalScore;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
    
    
    
    public String getPeriod() {
        return period;
    }

    public Assistant getAssistant() {
        return assistant;
    }

    public double getCompletionRate() {
        return completionRate;
    }

    public double getAverageDelay() {
        return averageDelay;
    }

    public double getEfficiencyPerDay() {
        return efficiencyPerDay;
    }

    public double getFinalScore() {
        return finalScore;
    }

    public String getComments() {
        return comments;
    }
}
