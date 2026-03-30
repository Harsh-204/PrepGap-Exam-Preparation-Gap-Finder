/**
 * Project: PrepGap – Exam Preparation Gap Finder
 * Author: Harsh Sahu (Reg No: 24BAI10561)
 * University: VIT BHOPAL UNIVERSITY
 * Description: Utility class for handling file persistence for subjects and topics.
 */
package src;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {

    public static void saveSubjects(List<Subject> subjects, String filePath) {
        File file = new File(filePath);
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Subject subject : subjects) {
                writer.write("Subject|" + subject.getName());
                writer.newLine();
                for (Topic topic : subject.getTopics()) {
                    writer.write("Topic|" + topic.getName() + "|" + topic.isCompleted() + "|" + topic.getConfidence());
                    writer.newLine();
                }
            }
            System.out.println("Data saved successfully! (Total Subjects: " + subjects.size() + ") to " + filePath);
        } catch (IOException e) {
            System.out.println("Error saving subjects: " + e.getMessage());
        }
    }

    public static List<Subject> loadSubjects(String filePath) {
        List<Subject> subjects = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("No saved data found at " + filePath);
            return subjects;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            Subject currentSubject = null;

            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split("\\|");
                if (parts[0].equalsIgnoreCase("Subject") && parts.length >= 2) {
                    currentSubject = new Subject(parts[1].trim());
                    subjects.add(currentSubject);
                } else if (parts[0].equalsIgnoreCase("Topic") && parts.length >= 4 && currentSubject != null) {
                    try {
                        String name = parts[1].trim();
                        boolean isCompleted = Boolean.parseBoolean(parts[2].trim());
                        // Clean numeric string from common typing errors like trailing dots
                        String confidenceStr = parts[3].trim().replaceAll("[^0-9]", "");
                        int confidence = Integer.parseInt(confidenceStr);
                        
                        Topic t = new Topic(name, isCompleted, confidence);
                        currentSubject.addTopic(t);
                    } catch (NumberFormatException e) {
                        System.out.println("Skipping malformed topic line: " + line);
                    }
                }
            }
            System.out.println("Data loaded successfully! (Total Subjects: " + subjects.size() + ") from " + filePath);
        } catch (IOException e) {
            System.out.println("Error loading subjects: " + e.getMessage());
        }

        return subjects;
    }
}
