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

public class PeopleModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	
	private ArrayList<Person> people;
	
	private String[] columnNames = {"Name"};
	 
	public PeopleModel(){
		people = new ArrayList<Person>();
	}
	
	public int getRowCount(){
		return people.size();
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
                val = people.get(row).getName();
                break;
        }
        return val;
    }
	
	
	public Person get(int index) {
        return people.get(index);
    }

    public int indexOf(Person b) {
        return people.indexOf(b);
    }
    
    public void add(Person b) {
        if (b != null) {
        	people.add(b);
            fireTableRowsInserted(people.size() - 1, people.size() - 1);
        }
    }

    public void add(int index, Person b) {
        if (b != null) {
        	people.add(index, b);
            fireTableRowsInserted(index, index);
        }
    }

    public void remove(int index) {
    	people.remove(index);
        fireTableRowsDeleted(index, index);
        return;
    }

    public void remove(PersonPeople b) {
        remove(indexOf(b));
    }

    public void clear() {
        if (people.size() > 0) {
            int size = people.size();
            people.clear();
            this.fireTableRowsDeleted(0, size - 1);
        }
    }
}
