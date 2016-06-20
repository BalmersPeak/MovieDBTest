package moviepack;

import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.TmdbAuthentication;
import info.movito.themoviedbapi.model.config.TokenAuthorisation;
import info.movito.themoviedbapi.model.config.TokenSession;
import info.movito.themoviedbapi.model.core.SessionToken;

/**
 * Class for login into tmdb.
 *
 * @author James Jetton
 *
 */
public class Login {

    /**
     * tmdbApi class.
     */
    private static TmdbApi tmdbApi;

    /**
     * Initialize login.
     *
     * @param tmdb
     *            tmdb key.
     */
    public Login(final TmdbApi tmdb) {
        Login.tmdbApi = tmdb;

    }

    /**
     * Logs in a user.
     * @param username username of account.
     * @param password password of account.
     * @return SessionToken of the account login.
     */
    public final SessionToken getSessionToken(final String username,
            final char[] password) {
        // There are two ways to generate session id

        // Generating session id using only API calls (requires username and
        // password)

        // TJett themoviedb1

        TmdbAuthentication tmdbAuth = tmdbApi.getAuthentication();
        TokenAuthorisation tokenAuth = tmdbAuth.getLoginToken(
                tmdbAuth.getAuthorisationToken(), username,
                String.valueOf(password));
        TokenSession tokenSession = tmdbAuth.getSessionToken(tokenAuth);
        // TokenSession tokenSession = tmdbAuth.getGuestSessionToken();
        String sessionId = tokenSession.getSessionId();
        SessionToken sessionToken = new SessionToken(sessionId);

        return sessionToken;
    }
}
