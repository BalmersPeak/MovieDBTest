import javax.swing.table.*;
import java.util.*;
import java.io.*;

import java.util.Iterator;

import info.movito.themoviedbapi.*;
import info.movito.themoviedbapi.model.Multi.MediaType;
import info.movito.themoviedbapi.TmdbSearch.*;
import info.movito.themoviedbapi.model.*;
import info.movito.themoviedbapi.model.changes.*;
import info.movito.themoviedbapi.model.config.*;
import info.movito.themoviedbapi.model.core.*;
import info.movito.themoviedbapi.model.keywords.*;
import info.movito.themoviedbapi.model.people.*;
import info.movito.themoviedbapi.model.tv.*;
import info.movito.themoviedbapi.tools.*;

public class MovieModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	
	private ArrayList<MovieDb> movies;
	
	private String[] columnNames = {"Title", "Date", "Genre", "Rating"};
	 
	public MovieModel(){
		movies = new ArrayList<MovieDb>();
	}
	
	public int getRowCount(){
		return movies.size();
	}
	
	public int getColumnCount(){
		return columnNames.length;
	}
	
	public String getColumnName(int col){
		return columnNames[col];
	}
	
	public Object getValueAt(int row, int col) {
        Object val = null;
        switch (col) {
            case 0:
                val = movies.get(row).getTitle();
                break;

            case 1:
                val = movies.get(row).getReleaseDate();
                break;

            case 2:
                val = movies.get(row).getGenres().toString();
                break;

            case 3:
                val = movies.get(row).getUserRating();
                break;
        }
        return val;
    }
	
	
	public MovieDb get(int index) {
        return movies.get(index);
    }

    public int indexOf(MovieDb b) {
        return movies.indexOf(b);
    }
    
    public void add(MovieDb b) {
        if (b != null) {
        	movies.add(b);
            fireTableRowsInserted(movies.size() - 1, movies.size() - 1);
        }
    }

    public void add(int index, MovieDb b) {
        if (b != null) {
        	movies.add(index, b);
            fireTableRowsInserted(index, index);
        }
    }

    public void remove(int index) {
    	movies.remove(index);
        fireTableRowsDeleted(index, index);
        return;
    }

    public void remove(MovieDb b) {
        remove(indexOf(b));
    }

    public void clear() {
        if (movies.size() > 0) {
            int size = movies.size();
            movies.clear();
            this.fireTableRowsDeleted(0, size - 1);
        }
    }
}
