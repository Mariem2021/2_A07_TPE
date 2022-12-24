import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ApplicationSession {
  private static final long SESSION_TIMEOUT = 30 * 60 * 1000; // 30 minutes
  private static final Map<String, ApplicationSession> SESSION_CACHE = new ConcurrentHashMap<>();

  private String sessionId;
  private long creationTime;
  private String username;

  private ApplicationSession(String sessionId, String username) {
    this.sessionId = sessionId;
    this.creationTime = System.currentTimeMillis();
    this.username = username;
  }

  public static void createSession(String sessionId, String username) {
    ApplicationSession session = new ApplicationSession(sessionId, username);
    SESSION_CACHE.put(sessionId, session);
  }

  public static boolean isValidSession(String sessionId) {
    ApplicationSession session = SESSION_CACHE.get(sessionId);
    if (session == null) {
      return false;
    }
    long currentTime = System.currentTimeMillis();
    if (currentTime - session.creationTime > SESSION_TIMEOUT) {
      SESSION_CACHE.remove(sessionId);
      return false;
    }
    session.creationTime = currentTime;
    return true;
  }

}

/*
 * Créez une classe de session d'application qui gère les informations d'identification de l'utilisateur et le moment où la session a été créée. Vous pouvez utiliser une clé de session générée aléatoirement pour identifier chaque session.

Définissez un délai d'expiration de session d'application dans votre code. Par exemple, vous pouvez définir le délai d'expiration à 30 minutes.

Dans votre code de connexion, lorsqu'un utilisateur se connecte avec succès, créez une nouvelle instance de la classe de session d'application et lui attribuez une nouvelle clé de session. Stockez cette instance de session dans un cache ou une base de données, en associant la clé de session à l'instance de session.

Dans votre code de vérification de session, vérifiez si une session existe pour l'utilisateur actuel en utilisant la clé de session fournie dans la requête HTTP. Si une session existe, vérifiez si elle a expiré en comparant le moment de création de la session avec le délai d'expiration de session. Si la session n'a pas expiré, mettez à jour le moment de création de la session et continuez à autoriser l'accès de l'utilisateur. Si la session a expiré, demandez à l'utilisateur de se reconnecter.
*/