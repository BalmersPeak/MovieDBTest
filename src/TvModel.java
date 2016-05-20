import javax.swing.table.*;
import java.util.*;
import java.io.*;

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

public class TvModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	
	private ArrayList<TvSeries> tvseries;
	
	private String[] columnNames = {"Title", "First Air Date", "Genre", "Rating"};
	 
	public TvModel(){
		tvseries = new ArrayList<TvSeries>();
	}
	
	public int getRowCount(){
		return tvseries.size();
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
                val = tvseries.get(row).getOriginalName();
                break;

            case 1:
                val = tvseries.get(row).getFirstAirDate();
                break;

            case 2:
                val = tvseries.get(row).getGenres().toString();
                break;

            case 3:
                val = tvseries.get(row).getUserRating();
                break;
        }
        return val;
    }
	
	
	public TvSeries get(int index) {
        return tvseries.get(index);
    }

    public int indexOf(TvSeries b) {
        return tvseries.indexOf(b);
    }
    
    public void add(TvSeries b) {
        if (b != null) {
        	tvseries.add(b);
            fireTableRowsInserted(tvseries.size() - 1, tvseries.size() - 1);
        }
    }

    public void add(int index, TvSeries b) {
        if (b != null) {
        	tvseries.add(index, b);
            fireTableRowsInserted(index, index);
        }
    }

    public void remove(int index) {
    	tvseries.remove(index);
        fireTableRowsDeleted(index, index);
        return;
    }

    public void remove(TvSeries b) {
        remove(indexOf(b));
    }

    public void clear() {
        if (tvseries.size() > 0) {
            int size = tvseries.size();
            tvseries.clear();
            this.fireTableRowsDeleted(0, size - 1);
        }
    }
}
