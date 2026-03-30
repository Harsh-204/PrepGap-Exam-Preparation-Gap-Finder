/**
 * Project: PrepGap – Exam Preparation Gap Finder
 * Author: Harsh Sahu (Reg No: 24BAI10561)
 * University: VIT BHOPAL UNIVERSITY
 * Description: Data model class representing an academic Subject.
 */
package src;

import java.util.ArrayList;

public class Subject {
    private String name;
    private ArrayList<Topic> topics;

    public Subject(String name) {
        this.name = name;
        this.topics = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Topic> getTopics() {
        return topics;
    }

    public void addTopic(Topic t) {
        this.topics.add(t);
    }

    @Override
    public String toString() {
        return "Subject{" +
                "name='" + name + '\'' +
                ", topicsCount=" + topics.size() +
                '}';
    }
}
