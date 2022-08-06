package pro.sky.course_project_2.service;

import pro.sky.course_project_2.model.Question;

import java.util.Collection;

public interface ExaminerService {

    Collection<Question> getQuestions(int amount);
}
