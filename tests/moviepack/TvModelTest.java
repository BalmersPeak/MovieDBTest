package moviepack;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.TmdbTV;
import info.movito.themoviedbapi.model.tv.TvSeries;


public class TvModelTest {

    TvModel tvModel;
    TvSeries damesInDeDop;
    TvSeries Ayuready;
    
    @Before
    public void setup() {
        tvModel = new TvModel();
        TmdbApi tmdbApi = new TmdbApi("811ffa781385ed56e1ec64c193eb93f4");
        TmdbTV tv = tmdbApi.getTvSeries();
        tvModel.add(damesInDeDop = tv.getSeries(5000, "en"));
        tvModel.add(Ayuready = tv.getSeries(5001, "en"));
    }
    
    @Test
    public void init() {
        TvModel tvMod = new TvModel();
        assertEquals(0, tvMod.getRowCount());
    }


    @Test
    public void getRowCount() {
        assertEquals(2, tvModel.getRowCount());
    }

    @Test
    public void getColumnCount() {
        assertEquals(2, tvModel.getColumnCount());
    }
    
    @Test
    public void getColumnName() {
        assertEquals("Title", tvModel.getColumnName(0));
        assertEquals("First Air Date", tvModel.getColumnName(1));
    }
    
    @Test
    public void getValueAt() {
        assertEquals("Dames in de Dop", tvModel.getValueAt(0, 0));
        assertEquals("2007-05-28", tvModel.getValueAt(0, 1));
        assertEquals("Ayuready?", tvModel.getValueAt(1, 0));
        assertEquals(null, tvModel.getValueAt(1, 1));
        assertEquals(0, tvModel.getValueAt(0, 3));
    }


    @Test
    public void get() {
        assertEquals(damesInDeDop, tvModel.get(0));
        assertEquals(Ayuready, tvModel.get(1));
    }

    @Test
    public void add() {
        assertEquals(damesInDeDop, tvModel.get(0));
        tvModel.add(null);
        assertEquals(2, tvModel.getRowCount());
    }

    @Test
    public void clear() {
        tvModel.clear();
        assertEquals(0, tvModel.getRowCount());
        tvModel.clear();
        assertEquals(0, tvModel.getRowCount());
    }

}
