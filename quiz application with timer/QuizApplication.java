import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class QuizApplication {
    private static List<Question> questions;
    private static int currentQuestionIndex;
    private static int score;
    private static Timer timer;
    private static boolean answered;

    public static void main(String[] args) {
        initializeQuestions();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Quiz Application!");

        // Display each question with options and handle user input
        for (Question question : questions) {
            displayQuestion(question);
            startTimer(10); // Timer set to 10 seconds for each question

            answered = false;
            while (!answered) {
                System.out.print("Enter your answer (1-" + question.getOptions().size() + "): ");
                if (scanner.hasNextInt()) {
                    int answerIndex = scanner.nextInt();
                    if (answerIndex >= 1 && answerIndex <= question.getOptions().size()) {
                        checkAnswer(question, answerIndex - 1);
                    } else {
                        System.out.println("Invalid input. Please enter a number between 1 and " + question.getOptions().size() + ".");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.next(); // Consume invalid input
                }
            }
            cancelTimer(); // Stop timer for the next question
        }

        scanner.close();

        // Display final score and summary
        System.out.println("\nQuiz Completed!");
        System.out.println("Your Final Score: " + score + " out of " + questions.size());
        System.out.println("Correct Answers: " + score);
        System.out.println("Incorrect Answers: " + (questions.size() - score));
    }

    private static void initializeQuestions() {
        questions = new ArrayList<>();
        // Add questions with options and correct answers
        Question q1 = new Question("What is the capital of France?");
        q1.addOption("London");
        q1.addOption("Paris", true);
        q1.addOption("Berlin");
        q1.addOption("Madrid");
        questions.add(q1);

        Question q2 = new Question("Which planet is known as the Red Planet?");
        q2.addOption("Earth");
        q2.addOption("Mars", true);
        q2.addOption("Jupiter");
        q2.addOption("Saturn");
        questions.add(q2);

        Question q3 = new Question("Who painted the Mona Lisa?");
        q3.addOption("Leonardo da Vinci", true);
        q3.addOption("Pablo Picasso");
        q3.addOption("Vincent van Gogh");
        q3.addOption("Michelangelo");
        questions.add(q3);
        
        // Add more questions as needed
    }

    private static void displayQuestion(Question question) {
        System.out.println("\n" + question.getQuestion());
        List<String> options = question.getOptions();
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ". " + options.get(i));
        }
    }

    private static void startTimer(int seconds) {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("\nTime's up!");
                answered = true; // Automatically mark as answered
                cancelTimer();
            }
        }, seconds * 1000);
    }

    private static void cancelTimer() {
        if (timer != null) {
            timer.cancel();
            timer.purge();
        }
    }

    private static void checkAnswer(Question question, int answerIndex) {
        if (!answered) {
            answered = true;
            if (question.isCorrectAnswer(answerIndex)) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Incorrect!");
            }
        }
    }
}

class Question {
    private String question;
    private List<String> options;
    private int correctAnswerIndex;

    public Question(String question) {
        this.question = question;
        options = new ArrayList<>();
    }

    public String getQuestion() {
        return question;
    }

    public void addOption(String option) {
        options.add(option);
    }

    public void addOption(String option, boolean correct) {
        options.add(option);
        if (correct) {
            correctAnswerIndex = options.size() - 1;
        }
    }

    public List<String> getOptions() {
        return options;
    }

    public boolean isCorrectAnswer(int index) {
        return index == correctAnswerIndex;
    }
}
