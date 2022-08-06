package pro.sky.course_project_2.service.impl;

import org.springframework.stereotype.Service;
import pro.sky.course_project_2.exception.QuestionsNotEnough;
import pro.sky.course_project_2.model.Question;
import pro.sky.course_project_2.service.ExaminerService;
import pro.sky.course_project_2.service.QuestionService;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        if (0 >= amount || amount > questionService.getAll().size()) {
            throw new QuestionsNotEnough();
        }
        Set<Question> result = new HashSet<>(amount);
        while (result.size() < amount) {
            result.add(questionService.getRandomQuestion());
        }
        return result;
    }
}
