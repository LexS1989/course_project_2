package pro.sky.course_project_2.service.impl;

import org.springframework.stereotype.Service;
import pro.sky.course_project_2.exception.QuestionAlreadyExistException;
import pro.sky.course_project_2.exception.QuestionNotFoundException;
import pro.sky.course_project_2.model.Question;
import pro.sky.course_project_2.service.QuestionService;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService {

    private final Set<Question> questions;
    private final Random random;

    public JavaQuestionService() {
        this.questions = new HashSet<>();
        this.random = new Random();
    }

    @Override
    public Question add(String question, String answer) {
        Question questionAnswer = new Question(question, answer);
        if (questions.contains(questionAnswer)) {
            throw new QuestionAlreadyExistException();
        }
        questions.add(questionAnswer);
        return questionAnswer;
    }

    @Override
    public Question add(Question question) {
        if (questions.contains(question)) {
            throw new QuestionAlreadyExistException();
        }
        questions.add(question);
        return question;
    }

    @Override
    public Question remove(Question question) {
        if (!questions.contains(question)) {
            throw new QuestionNotFoundException();
        }
        questions.remove(question);
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return Collections.unmodifiableCollection(questions);
    }

    @Override
    public Question getRandomQuestion() {
        ArrayList<Question> randomQuestions = new ArrayList<>(questions);
        return randomQuestions.get(random.nextInt(questions.size()));
    }
}
