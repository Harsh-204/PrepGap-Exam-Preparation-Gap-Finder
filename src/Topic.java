/**
 * Project: PrepGap – Exam Preparation Gap Finder
 * Author: Harsh Sahu (Reg No: 24BAI10561)
 * University: VIT BHOPAL UNIVERSITY
 * Description: Data model class representing a specific Topic within a Subject.
 */
package src;

public class Topic {
    private String name;
    private boolean isCompleted;
    private int confidence; // 1-5 scale

    public Topic(String name, boolean isCompleted, int confidence) {
        this.name = name;
        this.isCompleted = isCompleted;
        this.confidence = confidence;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    public int getConfidence() {
        return confidence;
    }

    public void setConfidence(int confidence) {
        this.confidence = confidence;
    }

    @Override
    public String toString() {
        return "Topic{" +
                "name='" + name + '\'' +
                ", isCompleted=" + isCompleted +
                ", confidence=" + confidence +
                '}';
    }
}
