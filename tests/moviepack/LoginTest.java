package moviepack;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.model.core.SessionToken;

public class LoginTest {

    TmdbApi tmdbApi;
    
    @Before
    public void setup() {
        tmdbApi = new TmdbApi("811ffa781385ed56e1ec64c193eb93f4");
    }
    
    @Test
    public void init() {
        Login login = new Login(tmdbApi);
    }
    
    @Test
    public void getTokenSession() {
        Login login = new Login(tmdbApi);
        String passString = "pass";
        char[] pass = passString.toCharArray();
        SessionToken sessionToken = login.getSessionToken("pahlni", pass);
        assertEquals(true, sessionToken != null);
       
    }
    
}
