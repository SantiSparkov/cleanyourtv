package com.gathin.tvhome;

/**
 * dock_only_home: Home shows the dock row alone. Down from the dock reveals the grid of every other app, Up from
 * the grid's first row goes back, and each side remembers the tile it was left on. Positions are the adapter's:
 * the dock is the first {@code columns} of them.
 */
final class DockOnlyHome {
    private DockOnlyHome() { }

    /** How long the grid takes to fade in on Down and out on the way back, in milliseconds. */
    static final int REVEAL_MS = 180, HIDE_MS = 140;

    /**
     * Where Down from a dock tile goes: the grid tile last focused while it is still there, else the first grid
     * row's tile in the same column (the last app when that row is short). -1 when there is no grid.
     */
    static int down(int dockPos, int lastGrid, int columns, int count) {
        if (count <= columns) return -1;
        if (lastGrid >= columns && lastGrid < count) return lastGrid;
        return Math.min(columns + dockPos % columns, count - 1);
    }

    /**
     * Where Up from a grid tile goes: from the first grid row, the dock tile last focused, kept inside the
     * {@code dockFilled} slots that hold an app; from a lower row, the tile above. -1 for a dock tile.
     */
    static int up(int pos, int lastDock, int columns, int dockFilled) {
        if (pos < columns) return -1;
        if (pos >= 2 * columns) return pos - columns;
        int filled = Math.max(1, Math.min(columns, dockFilled));
        return Math.min(lastDock >= 0 ? lastDock : pos - columns, filled - 1);
    }

    /** A tile's alpha while the grid is hidden or shown: the dock always shows. */
    static float alpha(int pos, int columns, boolean revealed) {
        return revealed || pos < columns ? 1f : 0f;
    }
}
