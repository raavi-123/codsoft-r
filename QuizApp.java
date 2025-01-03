import java.util.*;

public class QuizApp {
    static class Question {
        String questionText;
        String[] options;
        int correctOption;

        Question(String questionText, String[] options, int correctOption) {
            this.questionText = questionText;
            this.options = options;
            this.correctOption = correctOption;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Question> questions = new ArrayList<>();
        questions.add(new Question("What is the capital of France?",
                new String[]{"1. Berlin", "2. Madrid", "3. Paris", "4. Rome"}, 3));
        questions.add(new Question("Which programming language is known as platform-independent?",
                new String[]{"1. Python", "2. C", "3. Java", "4. JavaScript"}, 3));
        questions.add(new Question("Which planet is known as the Red Planet?",
                new String[]{"1. Venus", "2. Mars", "3. Jupiter", "4. Saturn"}, 2));

        int score = 0;
        List<String> results = new ArrayList<>();
        final int TIME_LIMIT = 10;

        System.out.println("\n--- Welcome to the Quiz App! ---\n");
        System.out.println("You will have " + TIME_LIMIT + " seconds to answer each question.\n");

        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            System.out.println("Question " + (i + 1) + ": " + question.questionText);

            for (String option : question.options) {
                System.out.println(option);
            }

            long startTime = System.currentTimeMillis();
            int userAnswer = -1;

            System.out.print("\nEnter your answer (1-4): ");
            while ((System.currentTimeMillis() - startTime) < TIME_LIMIT * 1000) {
                if (scanner.hasNextInt()) {
                    userAnswer = scanner.nextInt();
                    break;
                }
            }

            if (userAnswer == question.correctOption) {
                System.out.println("Correct!\n");
                score++;
                results.add("Question " + (i + 1) + ": Correct");
            } else {
                System.out.println("Incorrect or Timeout! Correct answer: " + question.correctOption + "\n");
                results.add("Question " + (i + 1) + ": Incorrect");
            }
        }

        System.out.println("\n--- Quiz Completed! ---");
        System.out.println("Your final score: " + score + " / " + questions.size());
        System.out.println("\n--- Summary ---");
        for (String result : results) {
            System.out.println(result);
        }

        System.out.println("\nThank you for playing!");
    }
}
