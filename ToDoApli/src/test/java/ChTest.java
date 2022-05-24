import dto.Check;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;

public class ChTest {

    @Test
    public void isEmpty() {

        Check check = new Check();

        boolean isEmpty = check.checkIfEmpty("", null, null);

        assertTrue(isEmpty);


    }

    @Test
    public void isNotEmpty() {

        Check check = new Check();

        boolean isNotEmpty = check.checkIfEmpty("test", LocalDate.parse( "2022-05-11"), LocalDate.parse("2022-05-12"));

        assertFalse(isNotEmpty);

    }

    @Test
    public void isNotEmpty2() {

        Check check = new Check();

        boolean isNotEmpty = check.checkIfEmpty("test", LocalDate.parse( "2022-05-21"), LocalDate.parse("2022-05-22"));

        assertFalse(isNotEmpty);

    }

    @Test
    public void isNotEmpty3() {

        Check check = new Check();

        boolean isNotEmpty = check.checkIfEmpty("test", LocalDate.parse( "2022-05-17"), LocalDate.parse("2022-05-25"));

        assertFalse(isNotEmpty);

    }

    @Test
    public void dateIsOk() {

        Check check = new Check();

        boolean isCorrect = check.checkDates(LocalDate.parse("2022-05-15"), LocalDate.parse("2022-05-16"));

        assertFalse(isCorrect);

    }

    @Test
    public void dateIsOk2() {

        Check check = new Check();

        boolean isCorrect = check.checkDates(LocalDate.parse("2022-05-18"), LocalDate.parse("2022-05-18"));

        assertFalse(isCorrect);

    }

    @Test
    public void dateIsOk3() {

        Check check = new Check();

        boolean isCorrect = check.checkDates(LocalDate.parse("2022-05-22"), LocalDate.parse("2022-05-23"));

        assertFalse(isCorrect);

    }

    @Test
    public void dateIsWrong() {

        Check check = new Check();

        boolean isWrong = check.checkDates(LocalDate.parse("2022-05-17"), LocalDate.parse("2022-05-16"));

        assertTrue(isWrong);

    }

    @Test
    public void dateIsWrong2() {

        Check check = new Check();

        boolean isWrong = check.checkDates(LocalDate.parse("2022-05-21"), LocalDate.parse("2022-05-20"));

        assertTrue(isWrong);

    }

    @Test
    public void dateIsWrong3() {

        Check check = new Check();

        boolean isWrong = check.checkDates(LocalDate.parse("2022-05-19"), LocalDate.parse("2022-05-18"));

        assertTrue(isWrong);

    }

    @Test
    public void isBusy() {
        Check check = new Check();

        boolean isBusy = check.checkIfEmpty("Test", LocalDate.parse("2022-05-21"), LocalDate.parse("2022-05-22"));

        assertFalse(isBusy);
     }


}
