package moviepack;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import info.movito.themoviedbapi.model.MovieDb;


/**
 * Model to Show the search results of movies in a table.
 *
 * @author Nicholas Pahl
 *
 */
public class MovieModel extends AbstractTableModel {

    private static final long serialVersionUID = 1L;

    /**
     * List of movies in the table.
     */
    private ArrayList<MovieDb> movies;

    /**
     * String array of column names for the table.
     */
    private String[] columnNames = {"Title", "Date"};

    /**
     * Initializes Movie Model class.
     */
    public MovieModel() {
        movies = new ArrayList<MovieDb>();
    }

    /**
     * Returns the number of rows in the table.
     *
     * @return number of rows in table.
     */
    public final int getRowCount() {
        return movies.size();
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
            val = movies.get(row).getTitle();
            break;

        case 1:
            val = movies.get(row).getReleaseDate();
            break;

        default:
            val = 0;
        }
        return val;
    }

    /**
     * Returns the movie of the specified row.
     *
     * @param index row in question.
     * @return Movie of specified row.
     */
    public final MovieDb get(final int index) {
        return movies.get(index);
    }

    /**
     * Adds a movie to the table.
     *
     * @param m movie to be added.
     */
    public final void add(final MovieDb m) {
        if (m != null) {
            movies.add(m);
            fireTableRowsInserted(movies.size() - 1, movies.size() - 1);
        }
    }

    /**
     * Adds a movie to the table at the specified index.
     * @param index where to add the movie.
     * @param m the movie to be added.
     */
    public final void add(final int index, final MovieDb m) {
        if (m != null) {
            movies.add(index, m);
            fireTableRowsInserted(index, index);
        }
    }

    /**
     * Clears the table.
     */
    public final void clear() {
        if (movies.size() > 0) {
            int size = movies.size();
            movies.clear();
            this.fireTableRowsDeleted(0, size - 1);
        }
    }
}
