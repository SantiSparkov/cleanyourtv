package com.gathin.tvhome;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DockOnlyHomeTest {
    @Test public void downFromTheDockGoesBackToTheGridTileLeftLast() {
        assertEquals(17, DockOnlyHome.down(2, 17, 6, 20));
    }

    @Test public void downWithNoGridTileYetTakesTheSameColumn() {
        assertEquals(9, DockOnlyHome.down(3, -1, 6, 20));
        // A grid tile that is gone (the app was removed) counts as none.
        assertEquals(6, DockOnlyHome.down(0, 25, 6, 20));
    }

    @Test public void downIntoAShortFirstRowStopsAtTheLastApp() {
        assertEquals(7, DockOnlyHome.down(5, -1, 6, 8));
    }

    @Test public void downWithNoAppsBelowTheDockGoesNowhere() {
        assertEquals(-1, DockOnlyHome.down(1, -1, 6, 6));
        assertEquals(-1, DockOnlyHome.down(1, -1, 6, 4));
    }

    @Test public void upFromTheFirstGridRowGoesBackToTheDockTileLeftLast() {
        assertEquals(4, DockOnlyHome.up(6, 4, 6, 6));
        assertEquals(4, DockOnlyHome.up(11, 4, 6, 6));
    }

    @Test public void upWithNoDockTileYetTakesTheSameColumnInsideTheFilledDock() {
        assertEquals(2, DockOnlyHome.up(8, -1, 6, 6));
        // Five dock apps and an empty slot: the empty slot takes no focus.
        assertEquals(4, DockOnlyHome.up(11, -1, 6, 5));
        assertEquals(4, DockOnlyHome.up(6, 5, 6, 5));
    }

    @Test public void upFromALowerRowIsTheTileAbove() {
        assertEquals(9, DockOnlyHome.up(15, 4, 6, 6));
    }

    @Test public void upFromTheDockIsNotHandledHere() {
        assertEquals(-1, DockOnlyHome.up(3, 1, 6, 6));
    }

    @Test public void onlyTheDockShowsWhileTheGridIsHidden() {
        assertEquals(1f, DockOnlyHome.alpha(5, 6, false), 0f);
        assertEquals(0f, DockOnlyHome.alpha(6, 6, false), 0f);
        assertEquals(1f, DockOnlyHome.alpha(6, 6, true), 0f);
    }
}
