/**
 * Project: PrepGap – Exam Preparation Gap Finder
 * Author: Harsh Sahu (Reg No: 24BAI10561)
 * University: VIT BHOPAL UNIVERSITY
 * Description: Main entry point for the PrepGap application.
 */
package src;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<Subject> subjects = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static PrepAnalyzer analyzer = new PrepAnalyzer();
    private static final String DATA_FILE = "data/subjects.txt";

    public static void main(String[] args) {
        System.out.println("Welcome to PrepGap – Exam Preparation Gap Finder!");
        
        // Auto-load data at startup
        subjects = FileHandler.loadSubjects(DATA_FILE);
        
        boolean running = true;
        while (running) {
            System.out.println("\n=== PrepGap Menu (Subjects in memory: " + subjects.size() + ") ===");
            System.out.println("1. Add Subject");
            System.out.println("2. Add Topic to Subject");
            System.out.println("3. View Subjects & Topics");
            System.out.println("4. Analyze Subject");
            System.out.println("5. Save Data (Write current memory to file)");
            System.out.println("6. Load Data (Reload from disk - wipes unsaved changes)");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            
            String choice = scanner.nextLine().trim();
            
            switch (choice) {
                case "1":
                    addSubject();
                    break;
                case "2":
                    addTopic();
                    break;
                case "3":
                    viewData();
                    break;
                case "4":
                    analyzeSubject();
                    break;
                case "5":
                    FileHandler.saveSubjects(subjects, DATA_FILE);
                    break;
                case "6":
                    System.out.print("Are you sure? This will replace your current memory with the saved file. (y/n): ");
                    if (scanner.nextLine().trim().equalsIgnoreCase("y")) {
                        subjects = FileHandler.loadSubjects(DATA_FILE);
                    }
                    break;
                case "7":
                    running = false;
                    System.out.println("Exiting PrepGap. Good luck with your preparation!");
                    break;
                default:
                    System.out.println("Invalid option. Please enter a number from 1 to 7.");
            }
        }
        scanner.close();
    }

    private static void addSubject() {
        System.out.print("Enter Subject name: ");
        String name = scanner.nextLine().trim();
        if (!name.isEmpty()) {
            subjects.add(new Subject(name));
            System.out.println("Subject added successfully!");
        } else {
            System.out.println("Subject name cannot be empty.");
        }
    }

    private static void addTopic() {
        if (subjects.isEmpty()) {
            System.out.println("No subjects available. Please add a subject first.");
            return;
        }

        System.out.println("Select a subject to add a topic to:");
        for (int i = 0; i < subjects.size(); i++) {
            System.out.println((i + 1) + ". " + subjects.get(i).getName());
        }
        System.out.print("Choice: ");
        
        try {
            int subChoice = Integer.parseInt(scanner.nextLine().trim());
            if (subChoice < 1 || subChoice > subjects.size()) {
                System.out.println("Invalid choice.");
                return;
            }
            Subject subject = subjects.get(subChoice - 1);

            System.out.print("Enter Topic name: ");
            String topicName = scanner.nextLine().trim();
            if (topicName.isEmpty()) {
                System.out.println("Topic name cannot be empty.");
                return;
            }

            System.out.print("Is it completed? (y/n): ");
            String completedInput = scanner.nextLine().trim().toLowerCase();
            boolean isCompleted = completedInput.equals("y") || completedInput.equals("yes");

            System.out.print("Confidence level (1-5): ");
            int confidence = Integer.parseInt(scanner.nextLine().trim());
            if (confidence < 1 || confidence > 5) {
                System.out.println("Confidence must be between 1 and 5.");
                return;
            }

            subject.addTopic(new Topic(topicName, isCompleted, confidence));
            System.out.println("Topic added successfully to " + subject.getName() + "!");
            
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter valid numbers.");
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private static void viewData() {
        if (subjects.isEmpty()) {
            System.out.println("No data available.");
            return;
        }
        
        System.out.println("\n--- All Subjects and Topics ---");
        for (Subject subject : subjects) {
            System.out.println("Subject: " + subject.getName());
            if (subject.getTopics().isEmpty()) {
                System.out.println("  No topics added yet.");
            } else {
                for (Topic t : subject.getTopics()) {
                    System.out.println("  - " + t.getName() + " [Completed: " + t.isCompleted() + ", Confidence: " + t.getConfidence() + "]");
                }
            }
        }
        System.out.println("-------------------------------");
    }

    private static void analyzeSubject() {
        if (subjects.isEmpty()) {
            System.out.println("No subjects available to analyze.");
            return;
        }

        System.out.println("Select a subject to analyze:");
        for (int i = 0; i < subjects.size(); i++) {
            System.out.println((i + 1) + ". " + subjects.get(i).getName());
        }
        System.out.print("Choice: ");
        
        try {
            int subChoice = Integer.parseInt(scanner.nextLine().trim());
            if (subChoice < 1 || subChoice > subjects.size()) {
                System.out.println("Invalid choice.");
                return;
            }
            Subject subject = subjects.get(subChoice - 1);
            
            if (subject.getTopics().isEmpty()) {
                System.out.println("Subject has no topics to analyze.");
                return;
            }
            
            analyzer.showSummary(subject);
            
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
