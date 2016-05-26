package moviepack;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import info.movito.themoviedbapi.model.people.Person;

/**
 * Model to Show the search results of movies in a table.
 *
 * @author Nicholas Pahl
 *
 */
public class PeopleModel extends AbstractTableModel {

    private static final long serialVersionUID = 1L;

    /**
     * List of movies in the table.
     */
    private ArrayList<Person> people;

    /**
     * String array of column names for the table.
     */
    private String[] columnNames = {"Name"};

    /**
     * Initializes People Model class.
     */
    public PeopleModel() {
        people = new ArrayList<Person>();
    }

    /**
     * Returns the number of rows in the table.
     *
     * @return number of rows in table.
     */
    public final int getRowCount() {
        return people.size();
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
        Object val = null;
        switch (col) {
        case 0:
            val = people.get(row).getName();
            break;
        default:
            val = 0;
        }
        return val;
    }

    /**
     * Returns the person of the specified row.
     *
     * @param index row in question.
     * @return Person of specified row.
     */
    public final Person get(final int index) {
        return people.get(index);
    }

    /**
     * Adds a person to the table.
     *
     * @param p person to be added.
     */
    public final void add(final Person p) {
        if (p != null) {
            people.add(p);
            fireTableRowsInserted(people.size() - 1, people.size() - 1);
        }
    }

    /**
     * Adds a person to the table at the specified index.
     * @param index where to add the movie.
     * @param p the person to be added.
     */
    public final void add(final int index, final Person p) {
        if (p != null) {
            people.add(index, p);
            fireTableRowsInserted(index, index);
        }
    }

    /**
     * Clears the table.
     */
    public final void clear() {
        if (people.size() > 0) {
            int size = people.size();
            people.clear();
            this.fireTableRowsDeleted(0, size - 1);
        }
    }
}
