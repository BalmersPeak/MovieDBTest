package moviepack;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import info.movito.themoviedbapi.model.tv.TvSeries;

/**
 * Model to Show the search results of Tv Series in a table.
 * @author Nicholas Pahl
 *
 */
public class TvModel extends AbstractTableModel {
    private static final long serialVersionUID = 1L;

    /**
     * List of Tv Series in the table.
     */
    private ArrayList<TvSeries> tvseries;

    /**
     * String array of column names for the table.
     */
    private String[] columnNames = {"Title", "First Air Date"};

    /**
     * Initializes Tv Model class.
     */
    public TvModel() {
        tvseries = new ArrayList<TvSeries>();
    }

    /**
     * Returns the number of rows in the table.
     *
     * @return number of rows in table.
     */
    public final int getRowCount() {
        return tvseries.size();
    }

    /**
     * Returns the number of columns.
     *
     * @return number of columns.
     */
    public final int getColumnCount() {
        return columnNames.length;
    }

    /**
     * Get column name of a column.
     *
     * @param col index of column.
     * @return Name of column.
     */
    public final String getColumnName(final int col) {
        return columnNames[col];
    }

    /**
     * Gets value at a cell in the table.
     *
     * @param row row in question.
     * @param col column in question.
     * @return Object of the cell in table.
     */
    public final Object getValueAt(final int row, final int col) {
        Object val = new String();
        switch (col) {
        case 0:
            val = tvseries.get(row).getOriginalName();
            break;

        case 1:
            val = tvseries.get(row).getFirstAirDate();
            break;
        default:
            val = 0;
        }
        return val;
    }

    /**
     * Returns the Tv Series of the specified row.
     *
     * @param index row in question.
     * @return Tv Series of specified row.
     */
    public final TvSeries get(final int index) {
        return tvseries.get(index);
    }

    /**
     * Adds a tv series to the table.
     *
     * @param t tv series to be added.
     */
    public final void add(final TvSeries t) {
        if (t != null) {
            tvseries.add(t);
            fireTableRowsInserted(tvseries.size() - 1, tvseries.size() - 1);
        }
    }

    /**
     * Adds a tv series to the table at the specified index.
     * @param index where to add the tv series.
     * @param t the tv series to be added.
     */
    public final void add(final int index, final TvSeries t) {
        if (t != null) {
            tvseries.add(index, t);
            fireTableRowsInserted(index, index);
        }
    }

    /**
     * Clears the table.
     */
    public final void clear() {
        if (tvseries.size() > 0) {
            int size = tvseries.size();
            tvseries.clear();
            this.fireTableRowsDeleted(0, size - 1);
        }
    }
}
