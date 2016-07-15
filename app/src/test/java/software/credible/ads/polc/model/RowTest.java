package software.credible.ads.polc.model;

import org.junit.Test;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RowTest {

    @Test
    public void testRowWithFiveColsIsValid() {
        assertTrue(new Row(1, 2, 3, 4, 5).isValid());
    }

    @Test
    public void testRowWithFourColsIsInvalid() {
        assertFalse(new Row(1, 2, 3, 4).isValid());
    }

    @Test
    public void testRowWithSixColsIsValid() {
        assertTrue(new Row(1, 2, 3, 4, 5, 6).isValid());
    }

    @Test
    public void testRowWith100ColsIsValid() {

        int[] oneHundredCols = new int [100];
        for(int i = 0; i < oneHundredCols.length; i++) {
            oneHundredCols[i] = ThreadLocalRandom.current().nextInt(10);
        }
        assertTrue(new Row(oneHundredCols).isValid());

    }

    @Test
    public void testRowWith101ColsIsInvalid() {
        int[] oneHundredOneCols = new int [101];
        for(int i = 0; i < oneHundredOneCols.length; i++) {
            oneHundredOneCols[i] = ThreadLocalRandom.current().nextInt(10);
        }
        assertFalse(new Row(oneHundredOneCols).isValid());
    }

}