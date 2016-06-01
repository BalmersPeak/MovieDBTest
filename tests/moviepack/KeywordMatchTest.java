package moviepack;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import info.movito.themoviedbapi.TmdbApi;

public class KeywordMatchTest {


    TmdbApi tmdbApi = new TmdbApi("811ffa781385ed56e1ec64c193eb93f4");
    MovieModel keywordModel = new MovieModel();
    KeywordMatch keyword;

    @Before
    public void setup() {
        keyword = new KeywordMatch(tmdbApi, keywordModel);
    }
    
    @Test
    public void searchKeyword() {
        keyword.searchKeyword("action");
        assertEquals(true, 0 < keywordModel.getRowCount());
        keyword.searchKeyword("marvel, comic");
        assertEquals(true, 0 < keywordModel.getRowCount());
        keyword.searchKeyword("marvel, comic, war");
        assertEquals(true, 0 < keywordModel.getRowCount());
        keyword.searchKeyword("marvel, comic, war, civil war");
        assertEquals(true, 0 < keywordModel.getRowCount());
        keyword.searchKeyword("marvel, comic, war, civil war, extra");
        assertEquals(true, 0 < keywordModel.getRowCount());
        keyword.searchKeyword("this is a really bad keyword");
        assertEquals(true, 0 == keywordModel.getRowCount());
    }

}
