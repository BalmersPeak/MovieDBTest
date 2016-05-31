package moviepack;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.TmdbPeople;
import info.movito.themoviedbapi.model.people.Person;



public class PeopleModelTest {

    
    PeopleModel peopleModel;
    Person norihiroIsoda;
    Person joopVanHulzen;

    
    @Before
    public void setup() {
        peopleModel = new PeopleModel();
        TmdbApi tmdbApi = new TmdbApi("811ffa781385ed56e1ec64c193eb93f4");
        TmdbPeople people = tmdbApi.getPeople();
        peopleModel.add(norihiroIsoda = people.getPersonInfo(5000));
        peopleModel.add(joopVanHulzen = people.getPersonInfo(5002));
    }
    
    @Test
    public void init() {
        PeopleModel peopleMod = new PeopleModel();
        assertEquals(0, peopleMod.getRowCount());
    }


    @Test
    public void getRowCount() {
        assertEquals(2, peopleModel.getRowCount());
    }

    @Test
    public void getColumnCount() {
        assertEquals(1, peopleModel.getColumnCount());
    }
    
    @Test
    public void getColumnName() {
        assertEquals("Name", peopleModel.getColumnName(0));
    }
    
    @Test
    public void getValueAt() {
        assertEquals("Norihiro Isoda", peopleModel.getValueAt(0, 0));
        assertEquals("Joop van Hulzen", peopleModel.getValueAt(1, 0));
        assertEquals(0, peopleModel.getValueAt(0, 2));
    }


    @Test
    public void get() {
        assertEquals(norihiroIsoda, peopleModel.get(0));
        assertEquals(joopVanHulzen, peopleModel.get(1));
    }

    @Test
    public void add() {
        assertEquals(norihiroIsoda, peopleModel.get(0));
        peopleModel.add(null);
        assertEquals(2, peopleModel.getRowCount());
    }

    @Test
    public void clear() {
        peopleModel.clear();
        assertEquals(0, peopleModel.getRowCount());
        peopleModel.clear();
        assertEquals(0, peopleModel.getRowCount());
    }

}
