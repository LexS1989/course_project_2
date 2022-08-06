package pro.sky.course_project_2.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.course_project_2.exception.QuestionsNotEnough;
import pro.sky.course_project_2.model.Question;
import pro.sky.course_project_2.service.QuestionService;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {

    @Mock
    private QuestionService questionService;

    @InjectMocks
    private ExaminerServiceImpl out;

    @Test
    public void getQuestionsNegativeTest() {
        assertThatExceptionOfType(QuestionsNotEnough.class)
                .isThrownBy(() -> out.getQuestions(0));

        assertThatExceptionOfType(QuestionsNotEnough.class)
                .isThrownBy(() -> out.getQuestions(1));
    }

    @Test
    public void getQuestionsPositiveTest() {
        List<Question> questions = new ArrayList<>(List.of(
                new Question("QuestionText1", "QuestionAnswer1"),
                new Question("QuestionText2", "QuestionAnswer2"),
                new Question("QuestionText3", "QuestionAnswer3"),
                new Question("QuestionText4", "QuestionAnswer4"),
                new Question("QuestionText5", "QuestionAnswer5")
        ));

        when(questionService.getAll()).thenReturn(questions);

        when(questionService.getRandomQuestion())
                .thenReturn(questions.get(0), questions.get(1), questions.get(1), questions.get(3));
        assertThat(out.getQuestions(3))
                .containsExactlyInAnyOrder(questions.get(0), questions.get(1), questions.get(3));

        when(questionService.getRandomQuestion())
                .thenReturn(questions.get(0), questions.get(1), questions.get(1), questions.get(3),questions.get(2));
        assertThat(out.getQuestions(4))
                .containsExactlyInAnyOrder(questions.get(0), questions.get(1), questions.get(2), questions.get(3));
    }
}