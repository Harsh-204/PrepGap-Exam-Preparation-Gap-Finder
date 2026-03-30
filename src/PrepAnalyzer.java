/**
 * Project: PrepGap – Exam Preparation Gap Finder
 * Author: Harsh Sahu (Reg No: 24BAI10561)
 * University: VIT BHOPAL UNIVERSITY
 * Description: Core logic class for analyzing preparation data and identifying gaps.
 */
package src;

import java.util.List;
import java.util.ArrayList;

public class PrepAnalyzer {

    public double calculateReadiness(Subject subject) {
        List<Topic> topics = subject.getTopics();
        if (topics.isEmpty()) {
            return 0.0;
        }

        int completed = 0;
        for (Topic t : topics) {
            if (t.isCompleted()) {
                completed++;
            }
        }
        return ((double) completed / topics.size()) * 100;
    }

    public void showMissingTopics(Subject subject) {
        List<Topic> missing = new ArrayList<>();
        for (Topic t : subject.getTopics()) {
            if (!t.isCompleted()) {
                missing.add(t);
            }
        }

        if (missing.isEmpty()) {
            System.out.println("  None");
        } else {
            for (Topic t : missing) {
                System.out.println("  - " + t.getName());
            }
        }
    }

    public void showWeakTopics(Subject subject) {
        List<Topic> weak = new ArrayList<>();
        for (Topic t : subject.getTopics()) {
            if (t.isCompleted() && t.getConfidence() <= 2) {
                weak.add(t);
            }
        }

        if (weak.isEmpty()) {
            System.out.println("  None");
        } else {
            for (Topic t : weak) {
                System.out.println("  - " + t.getName() + " (Confidence: " + t.getConfidence() + ")");
            }
        }
    }

    public void showSummary(Subject subject) {
        System.out.println("\n--- Analysis Result ---");
        System.out.println("Subject: " + subject.getName());
        System.out.printf("Readiness: %.1f%%\n", calculateReadiness(subject));
        
        System.out.println("Missing Topics:");
        showMissingTopics(subject);
        
        System.out.println("Weak Topics:");
        showWeakTopics(subject);
        System.out.println("-----------------------");
    }
}
