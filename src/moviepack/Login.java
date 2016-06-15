package moviepack;

import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.TmdbAuthentication;
import info.movito.themoviedbapi.model.config.TokenAuthorisation;
import info.movito.themoviedbapi.model.config.TokenSession;
import info.movito.themoviedbapi.model.core.SessionToken;

public class Login {

	private static TmdbApi tmdbApi;

	public Login(final TmdbApi tmdbApi){
		Login.tmdbApi = tmdbApi;

	}

	public final SessionToken getSessionToken(String username, char[] password) {
		// There are two ways to generate session id

		// Generating session id using only API calls (requires username and password)

		//TJett themoviedb1
		
		TmdbAuthentication tmdbAuth = tmdbApi.getAuthentication();
		TokenAuthorisation tokenAuth = tmdbAuth.getLoginToken(tmdbAuth.getAuthorisationToken(), username, String.valueOf(password));
		password = null;
		TokenSession tokenSession = tmdbAuth.getSessionToken(tokenAuth);
		//TokenSession tokenSession = tmdbAuth.getGuestSessionToken();
		String sessionId = tokenSession.getSessionId();
		SessionToken sessionToken = new SessionToken(sessionId);

		
		// Generating session id via the website (user interaction involved)
		// Step 1: create a new request token
		//		http://api.themoviedb.org/3/authentication/token/new?api_key=your-api-key
		//		(note down the request_token from the response)
		// Step 2: ask the user for permission
		//		https://www.themoviedb.org/authenticate/request_token
		// Step 3: create a session id
		//		http://api.themoviedb.org/3/authentication/session/new?api_key=api-key&request_token=request-token
		//		(use session-id value in the response to set the value for sessionId variable in the code below
		//String sessionId = "session-id";
		//SessionToken sessionToken = new SessionToken(sessionId);

		return sessionToken;
	}

	
	public final SessionToken getSessionToken() {
		// There are two ways to generate session id

		// Generating session id using only API calls (requires username and password)

		//TJett themoviedb1
		
		TmdbAuthentication tmdbAuth = tmdbApi.getAuthentication();
		//TokenAuthorisation tokenAuth = tmdbAuth.getLoginToken(tmdbAuth.getAuthorisationToken(), username, String.valueOf(password));
		//TokenSession tokenSession = tmdbAuth.getSessionToken(tokenAuth);
		TokenSession tokenSession = tmdbAuth.getGuestSessionToken();
		String sessionId = tokenSession.getGuestSessionId();
		SessionToken sessionToken = new SessionToken(sessionId);

		
		// Generating session id via the website (user interaction involved)
		// Step 1: create a new request token
		//		http://api.themoviedb.org/3/authentication/token/new?api_key=your-api-key
		//		(note down the request_token from the response)
		// Step 2: ask the user for permission
		//		https://www.themoviedb.org/authenticate/request_token
		// Step 3: create a session id
		//		http://api.themoviedb.org/3/authentication/session/new?api_key=api-key&request_token=request-token
		//		(use session-id value in the response to set the value for sessionId variable in the code below
		//String sessionId = "session-id";
		//SessionToken sessionToken = new SessionToken(sessionId);

		return sessionToken;
	}
}
