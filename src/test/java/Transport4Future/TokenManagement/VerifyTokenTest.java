package Transport4Future.TokenManagement;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class VerifyTokenTest {
    private TokenManager myManager;
    
    public VerifyTokenTest () {
      myManager = new TokenManager();
    }
  @DisplayName ("Verify is true, token found, is granted is valid and not expired /token 1 primera posicion")
  @Test
  void CorrectVerifyTokenTestFistPosition() throws TokenManagementException  {
    boolean excepted = true;
    String tokenToVerify ="1GIwNTc3OGFjNmNkMmE5NGQ1NWYwOWM4NWZmNDAzM2E0ZTE1YTNiYTE5M2VjN2Y4ZjI5OTU3OTFmOTRjNjUyNg==";
    boolean obtained = myManager.VerifyToken (tokenToVerify);
    assertEquals(excepted,obtained); 
  }
  @DisplayName ("Verify is true, token found, is granted is valid and not expired /token 3 token intermedio")
  @Test
  void CorrectVerifyTokenTestTerceraPosicion() throws TokenManagementException  {
    boolean excepted = true;
    String tokenToVerify ="3MIwNTc3OGFjNmNkMmE5NGQ1NWYwOWM4NWZmNDAzM2E0ZTE1YTNiYTE5M2VjN2Y4ZjI5OTU3OTFmOTRjNjUyNg==";
    boolean obtained = myManager.VerifyToken (tokenToVerify);
    assertEquals(excepted,obtained); 
  }
  @DisplayName ("Verify is true, token found, is granted is valid uand not expired /token 6 ultima posicion")
  @Test
  void CorrectVerifyTokenTestUltimaPosicion() throws TokenManagementException  {
    boolean excepted = true;
    String tokenToVerify ="6rIwNTc3OGFjNmNkMmE5NGQ1NWYwOWM4NWZmNDAzM2E0ZTE1YTNiYTE5M2VjN2Y4ZjI5OTU3OTFmOTRjNjUyNg==";
    boolean obtained = myManager.VerifyToken (tokenToVerify);
    assertEquals(excepted,obtained); 
  }
  @DisplayName ("token is not found en el TokenStore")
  @Test
  void TokenisMissing() throws TokenManagementException  {
    String tokenToVerify ="OGY2OGExN2ZhZmVmYjBhODA4ZTAyNjQ2NzI1ODFmN2U4NzllNzhhY2VlMjkyYWM0ODIxZTUyZmNhYjc3ZDlmNg\u003d\u003d";
    String expectedMessage = "Token is not found in TokenStore";
    TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, ()-> {
      myManager.VerifyToken (tokenToVerify);
    });
    
    assertEquals (expectedMessage,ex.getMessage()); 
  }
  
  @DisplayName ("Token found, not expired but 'Invalid IAT valor Bigger than System.currentTimeMillis()'/ segundo token")
  @Test
  void InvalidIATvalorBiggerthanCurrentTime() throws TokenManagementException  {
    boolean excepted = false;
    String tokenToVerify ="2FIwNTc3OGFjNmNkMmE5NGQ1NWYwOWM4NWZmNDAzM2E0ZTE1YTNiYTE5M2VjN2Y4ZjI5OTU3OTFmOTRjNjUyNg==";
    boolean obtained = myManager.VerifyToken (tokenToVerify);
    assertEquals(excepted,obtained); 
  }
  @DisplayName ("Token found, IAT lower than current time but 'Invalid EXP valor Lower than System.currentTimeMillis()'/cuarto token")
  @Test
  void InvalidIATvalorLowerthanCurrentTime() throws TokenManagementException  {
    boolean excepted = false;
    String tokenToVerify ="4qIwNTc3OGFjNmNkMmE5NGQ1NWYwOWM4NWZmNDAzM2E0ZTE1YTNiYTE5M2VjN2Y4ZjI5OTU3OTFmOTRjNjUyNg==";
    boolean obtained = myManager.VerifyToken (tokenToVerify);
    assertEquals(excepted,obtained); 
  }  
  @DisplayName ("Token found, but 'Invalid IAT bigger than current time' AND  'Invalid EXP valor Lower than System.currentTimeMillis()'/quinto token")
  @Test
  void InvalidIATandEXP() throws TokenManagementException  {
    boolean excepted = false;
    String tokenToVerify ="5PIwNTc3OGFjNmNkMmE5NGQ1NWYwOWM4NWZmNDAzM2E0ZTE1YTNiYTE5M2VjN2Y4ZjI5OTU3OTFmOTRjNjUyNg==";
    boolean obtained = myManager.VerifyToken (tokenToVerify);
    assertEquals(excepted,obtained); 
  } 
  
}
