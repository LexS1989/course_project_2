package pro.sky.course_project_2.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pro.sky.course_project_2.exception.QuestionAlreadyExistException;
import pro.sky.course_project_2.exception.QuestionNotFoundException;
import pro.sky.course_project_2.model.Question;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

class JavaQuestionServiceTest {

    private final JavaQuestionService out = new JavaQuestionService();

    @ParameterizedTest
    @MethodSource("paramsString")
    public void addStringParamsPositiveAndNegativeTest(String question, String answer) {
        Question expected = new Question(question, answer);
        assertThat(out.add(question, answer))
                .isEqualTo(expected);

        assertThatExceptionOfType(QuestionAlreadyExistException.class)
                .isThrownBy(() -> out.add(question, answer));
    }

    @ParameterizedTest
    @MethodSource("paramsQuestion")
    public void addQuestionParamsPositiveAndNegativeTest(Question question) {
        Question expected = question;
        assertThat(out.add(question))
                .isEqualTo(expected);

        assertThatExceptionOfType(QuestionAlreadyExistException.class)
                .isThrownBy(() -> out.add(question));
    }

    @ParameterizedTest
    @MethodSource("paramsQuestion")
    public void removeQuestionParamsPositiveAndNegativeTest(Question question) {
        Question expected = question;
        out.add(question);
        assertThat(out.remove(question))
                .isEqualTo(expected);

        assertThat(out.getAll())
                .isEmpty();

        assertThatExceptionOfType(QuestionNotFoundException.class)
                .isThrownBy(() -> out.remove(question));
    }

    @ParameterizedTest
    @MethodSource("paramsQuestion")
    public void getAllQuestionParamsTest(Question question) {
        Question expected = question;
        out.add(question);
        assertThat(out.getAll())
                .containsExactlyInAnyOrder(expected);
    }

    @Test
    public void getRandomQuestion() {
        Set<Question> params = new HashSet<>(Set.of(
                new Question("QuestionText1", "QuestionAnswer1"),
                new Question("QuestionText2", "QuestionAnswer2"),
                new Question("QuestionText3", "QuestionAnswer3"),
                new Question("QuestionText4", "QuestionAnswer4"),
                new Question("QuestionText5", "QuestionAnswer5"))
        );
        params.forEach(question -> out.add(question));
        assertThat(out.getRandomQuestion()).isIn(params);
    }

    public static Stream<Arguments> paramsString() {
        return Stream.of(
                Arguments.of("QuestionText1", "QuestionAnswer1"),
                Arguments.of("QuestionText2", "QuestionAnswer2"),
                Arguments.of("QuestionText3", "QuestionAnswer3"),
                Arguments.of("QuestionText4", "QuestionAnswer4"),
                Arguments.of("QuestionText5", "QuestionAnswer5")
        );
    }

    public static Stream<Arguments> paramsQuestion() {
        return Stream.of(
                Arguments.of(new Question("QuestionText1", "QuestionAnswer1")),
                Arguments.of(new Question("QuestionText2", "QuestionAnswer2")),
                Arguments.of(new Question("QuestionText3", "QuestionAnswer3")),
                Arguments.of(new Question("QuestionText4", "QuestionAnswer4")),
                Arguments.of(new Question("QuestionText5", "QuestionAnswer5"))
        );
    }
}