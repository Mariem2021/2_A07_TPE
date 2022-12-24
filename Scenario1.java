import java.util.Set;

public class Scenario1 {
  private Set<String> knownPasswords;

  public PasswordValidator(Set<String> knownPasswords) {
    this.knownPasswords = knownPasswords;
  }

  public boolean isValid(String password) {
    // Vérifier la longueur du mot de passe
    if (password.length() < 8) {
      return false;
    }

    // Vérifier si le mot de passe contient au moins une lettre majuscule, une lettre minuscule et un chiffre
    boolean hasUpper = false;
    boolean hasLower = false;
    boolean hasDigit = false;
    for (int i = 0; i < password.length(); i++) {
      char c = password.charAt(i);
      if (Character.isUpperCase(c)) {
        hasUpper = true;
      } else if (Character.isLowerCase(c)) {
        hasLower = true;
      } else if (Character.isDigit(c)) {
        hasDigit = true;
      }
      if (hasUpper && hasLower && hasDigit) {
        break;
      }
    }
    if (!hasUpper || !hasLower || !hasDigit) {
      return false;
    }

    // Vérifier si le mot de passe est dans la liste des mots de passe connus
    if (knownPasswords.contains(password)) {
      return false;
    }

    return true;
  }
}

