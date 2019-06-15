package io.github.mivek.command.metar;

import io.github.mivek.internationalization.Messages;
import io.github.mivek.model.Metar;
import io.github.mivek.model.RunwayInfo;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;

/**
 * @author mivek
 */
public class RunwayCommandTest {

    private RunwayCommand command;

    @Before public void setUp() {
        command = new RunwayCommand();
    }

    @Test public void testExecuteSimple() {
        String riString = "R26/0600U";
        Metar m = new Metar();
        command.execute(m, riString);

        assertThat(m.getRunways(), hasSize(1));
        RunwayInfo ri = m.getRunways().get(0);
        assertNotNull(ri);
        assertEquals("26", ri.getName());
        assertEquals(600, ri.getMinRange());
        assertEquals(Messages.getInstance().getString("Converter.U"), ri.getTrend());
    }

    @Test public void testParseRunWaysComplex() {
        String riString = "R26L/0550V700U";
        Metar m = new Metar();

        command.execute(m, riString);
        assertThat(m.getRunways(), hasSize(1));
        RunwayInfo ri = m.getRunways().get(0);
        assertNotNull(ri);
        assertEquals("26L", ri.getName());
        assertEquals(550, ri.getMinRange());
        assertEquals(700, ri.getMaxRange());
        assertEquals(Messages.getInstance().getString("Converter.U"), ri.getTrend());
    }

    @Test public void testParseRunWayNull() {
        String riString = "R26R/AZEZFDFS";

        Metar m = new Metar();

        command.execute(m, riString);
        assertThat(m.getRunways(), hasSize(0));
    }
}
