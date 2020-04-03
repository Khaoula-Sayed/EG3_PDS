package Transport4Future.TokenManagement;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RequestTokenTest {
  
private TokenManager myManager;
private String jsonFilesFolder;

public RequestTokenTest() {
  jsonFilesFolder = System.getProperty("user.dir") + "/JSONFiles/RequestToken/";
  myManager = new TokenManager();
}

  /*Prueba : AS-RF2-V-01
    <Descripcion de prueba>:Comprueba todos los nodos sean correctos,
    <Nodos cubiertos> : nodo 1 de existencia del fichero 
    a continuacion el nodo no terminales 2  3 y 4,
    dentro del nodo 3 comprueba todo los nodos de ese arbol hasta el nodo terminal
    lo mismo con los nodos terminales 5 y 11 
    <IMPORTANTE>: este test cubre todos los test en los que los valores de los campos sean correctos por ellos
    no hace falta hacer un test correcto del valor correcto en los campos TokenRequest,RequestDate y NotificationEmail */
  @DisplayName ("Correct Token Request")
  @Test
  void CorrectTokenRequest() throws TokenManagementException {
    String FilePath = this.jsonFilesFolder + "Correct.json";
    String expectedToken = "NGIwNTc3OGFjNmNkMmE5NGQ1NWYwOWM4NWZmNDAzM2E0ZTE1YTNiYTE5M2VjN2Y4ZjI5OTU3OTFmOTRjNjUyNg==";
    String obtainedToken = myManager.RequestToken(FilePath);
    assertEquals(expectedToken,obtainedToken);
  }
  
  /*Prueba : AS-RF2-NV-02
    <Descripcion de prueba>: fichero vacio,
    <Nodos cubiertos> : Afecta a partir del nodo 2 junto al 3 y el 4
    no afecta al nodo 1 ya que ese es de existencia de fichero */
  @DisplayName ("Fichero vacio")
  @Test
  void FicheroVacio() throws TokenManagementException{
    String FilePath = this.jsonFilesFolder + "EmptyFile.json";
    String expectedToken = "";
    String obtainedToken = myManager.RequestTokenEmpty(FilePath);
    assertEquals(expectedToken,obtainedToken);
  }
  
  /*Prueba : AS-RF2-NV-03
  <Descripcion de prueba>: fichero inexistente,
  <Nodos cubiertos> : afecta al nodo 1 */
  @DisplayName ("No existe fichero")
  @Test
  void FileIsMissingTest () throws TokenManagementException {
    String FilePath = this.jsonFilesFolder + "isdCorrect.json";
    String expectedMessage = "Error:input file not found.";
    TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, ()-> {
      myManager.RequestToken(FilePath);
    });
    
    assertEquals (expectedMessage,ex.getMessage());
  }
  
  /*Prueba : AS-RF2-NV-04
  <Descripcion de prueba>: valor invalido en request date,
  <Nodos cubiertos> :Afecta a Nodo de valor de Request Date es decir al nodo 18 y por consiguiente 
   a todos los nodos de su rama nodos no terminales 35,36,37,38,39,40,41,42,43,44,45 nodos terminales 71,72,73,74,75,76,77,78,79,80 */
  @DisplayName ("Invalid Date")
  @Test
  void InvalidDate () throws TokenManagementException {
    String FilePath = this.jsonFilesFolder + "InvalidDate.json";
    String expectedMessage = "Error: invalid Date";
    TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, ()-> {
      myManager.RequestToken(FilePath);
    });
    
    assertEquals (expectedMessage,ex.getMessage());
  }
  
  /*Prueba : AS-RF2-NV-05
  <Descripcion de prueba>: valor invalido en Token Request,
  <Nodos cubiertos> : afecta al nodo no terminal 28 y a sus nodos
  no terminales 62,63.... y nodos terminales 89,90,91 */
  @DisplayName ("Invalid Device")
  @Test
  void InvalidDevice () throws TokenManagementException {
    String FilePath = this.jsonFilesFolder + "InvalidDevice.json";
    String expectedMessage = "Error: campo Device invalido";
    TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, ()-> {
      myManager.RequestToken(FilePath);
    });
    
    assertEquals (expectedMessage,ex.getMessage());
  }
  
  /*Prueba : AS-RF2-NV-06
  <Descripcion de prueba>: valor invalido en Notification email,
  <Nodos cubiertos> : afecta al nodo no terminal 22, al nodo no terminal arroba (53)
   y al nodo terminal 84  ya que falta*/
  @DisplayName ("Invalid E-mail")
  @Test
  void InvalidEmail () throws TokenManagementException {
    String FilePath = this.jsonFilesFolder + "InvalidEmail.json";
    String expectedMessage = "Error: valor invalido en campo notification e-mail.";
    TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, ()-> {
      myManager.RequestToken(FilePath);
    });
    
    assertEquals (expectedMessage,ex.getMessage());
  }
  
  /*Prueba : AS-RF2-NV-07
  <Descripcion de prueba>: Nodo Apertura (llave) Repetido 2 veces,
  <Nodos cubiertos> : afecta al nodo no terminal numero 2 y por consiguiente al nodo terminal (llave) 5 que se repite 2 veces*/
  @DisplayName ("Nodo Apertura Repetido")
  @Test
  void NodoAperturaRepetido () throws TokenManagementException {
    String FilePath = this.jsonFilesFolder + "NodoAperturaRepetido.json";
    String expectedMessage = "Error: invalid format in JSON file.";
    TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, ()-> {
      myManager.RequestToken(FilePath);
    });
    
    assertEquals (expectedMessage,ex.getMessage());
  }
  /*Prueba : AS-RF2-NV-08
  <Descripcion de prueba>: Nodo Apertura (llave) Cerrado 2 veces,
  <Nodos cubiertos> : afecta al nodo no terminal 4 y por consiguiente al nodo terminal (llave) 11 que se repite 2 veces*/
  @DisplayName ("Nodo Cerradura Repetido")
  @Test
  void NodoCerraduraRepetido () throws TokenManagementException {
    String FilePath = this.jsonFilesFolder + "NodoCerraduraRepetido.json";
    String expectedMessage = "Error: invalid format in JSON file.";
    TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, ()-> {
      myManager.RequestToken(FilePath);
    });
    
    assertEquals (expectedMessage,ex.getMessage());
  }
  /*Prueba : AS-RF2-V-09
  <Descripcion de prueba>: Nodo Request token repetido 2 veces entero,
  <Nodos cubiertos> : afecta al nodo no terminal 8 y todo el arbol de nodos
  que le sucede hasta los nodos terminale
  <AVISO: ESTA PRUEBA CON EXCEPCION NO DA ERROR POR QUE EL PROGRAMA COGE 
  UNO DE LOS DEVICE E IGNORA EL OTRO, POR ELLO HEMOS UTILIZADO UNA PRUEBA CORRECTA*/
  @DisplayName ("Nodo Date Repetido")
  @Test
  void NodoDateRepetido() throws TokenManagementException {
    String FilePath = this.jsonFilesFolder + "NodoDateRepetido.json";
    String expectedToken = "NGIwNTc3OGFjNmNkMmE5NGQ1NWYwOWM4NWZmNDAzM2E0ZTE1YTNiYTE5M2VjN2Y4ZjI5OTU3OTFmOTRjNjUyNg==";
    String obtainedToken = myManager.RequestToken(FilePath);
    assertEquals(expectedToken,obtainedToken);
  }

  /*Prueba : AS-RF2-V-10
  <Descripcion de prueba>: Nodo Token request repetido 2 veces entero,
  <Nodos cubiertos> : afecta al nodo no terminal 6 y todo el arbol de nodos
  que le sucede hasta los nodos terminale
  <AVISO: ESTA PRUEBA CON EXCEPCION NO DA ERROR POR QUE EL PROGRAMA COGE 
  UNO DE LOS DEVICE E IGNORA EL OTRO, POR ELLO HEMOS UTILIZADO UNA PRUEBA CORRECTA*/
  @DisplayName ("Nodo Device Repetido")
  @Test
  void NodoDeviceRepetido() throws TokenManagementException {
    String FilePath = this.jsonFilesFolder + "NodoDeviceRepetido.json";
    String expectedToken = "NGIwNTc3OGFjNmNkMmE5NGQ1NWYwOWM4NWZmNDAzM2E0ZTE1YTNiYTE5M2VjN2Y4ZjI5OTU3OTFmOTRjNjUyNg==";
    String obtainedToken = myManager.RequestToken(FilePath);
    assertEquals(expectedToken,obtainedToken);
  }

  /*Prueba : AS-RF2-V-11
  <Descripcion de prueba>: Nodo Request token repetido 2 veces entero,
  <Nodos cubiertos> : afecta al nodo no terminal 10 y todo el arbol de nodos
  que le sucede hasta los nodos terminale
  <AVISO: ESTA PRUEBA CON EXCEPCION NO DA ERROR POR QUE EL PROGRAMA COGE 
  UNO DE LOS DEVICE E IGNORA EL OTRO, POR ELLO HEMOS UTILIZADO UNA PRUEBA CORRECTA*/
  @DisplayName ("Nodo Email Repetido")
  @Test
  void NodoEmailRepetido() throws TokenManagementException {
    String FilePath = this.jsonFilesFolder + "NodoEmailRepetido.json";
    String expectedToken = "NGIwNTc3OGFjNmNkMmE5NGQ1NWYwOWM4NWZmNDAzM2E0ZTE1YTNiYTE5M2VjN2Y4ZjI5OTU3OTFmOTRjNjUyNg==";
    String obtainedToken = myManager.RequestToken(FilePath);
    assertEquals(expectedToken,obtainedToken);
  }

  /*Prueba : AS-RF2-NV-12
  <Descripcion de prueba>: vvalor vacio en el campo token request,
  <Nodos cubiertos> : afecta al nodo no terminal 28 y a sus nodos
  no terminales 62,63.... y nodos terminales 89,90,91 */
  @DisplayName ("Nodo Device vacio")
  @Test
  void NodoDevicevacio () throws TokenManagementException {
    String FilePath = this.jsonFilesFolder + "NodoDevicevacio.json";
    String expectedMessage = "Error: invalid format in JSON file.";
    TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, ()-> {
      myManager.RequestToken(FilePath);
    });
    
    assertEquals (expectedMessage,ex.getMessage());
  }
  
  /*Prueba : AS-RF2-NV-13
  <Descripcion de prueba>: valor vacio en el campo request date,
  <Nodos cubiertos> :Afecta a Nodo de valor de Request Date es decir al nodo 18 y y a los del valor dentro de comillas
   los nodos no terminales 35,36,37,38,39,40,41,42,43,44,45, nodos terminales 71,72,73,74,75,76,77,78,79,80 */
  @DisplayName ("Nodo Date vacio")
  @Test
  void NodoDatevacio () throws TokenManagementException {
    String FilePath = this.jsonFilesFolder + "NodoDatevacio.json";
    String expectedMessage = "Error: invalid format in JSON file.";
    TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, ()-> {
      myManager.RequestToken(FilePath);
    });
    
    assertEquals (expectedMessage,ex.getMessage());
  }
  
  /*Prueba : AS-RF2-NV-14
  <Descripcion de prueba>: valor vacio en el campo Notification email,
  <Nodos cubiertos> : afecta al nodo no terminal 22, a los nodos no terminales
   52,53 54,55,56 y a los nodos terminales 83,84,85,86,87  ya que faltan*/
  @DisplayName ("Nodo Email vacio")
  @Test
  void NodoEmailDatevacio () throws TokenManagementException {
    String FilePath = this.jsonFilesFolder + "NodoEmailvacio.json";
    String expectedMessage = "Error: valor invalido en campo notification e-mail.";
    TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, ()-> {
      myManager.RequestToken(FilePath);
    });
    
    assertEquals (expectedMessage,ex.getMessage());
  }
  
  /*Prueba : AS-RF2-NV-15
  <Descripcion de prueba>: no hay nodo terminal 'coma' despues de Token Resquest,
  <Nodos cubiertos> : afecta al nodo terminal 15
  <DATO>: esta prueba se haria igual para comprobar el segundo separador es decir la coma
  entre Request date y Notification email*/
  @DisplayName ("No hay nodo terminal Coma")
  @Test
  void NohaynodoterminalComa () throws TokenManagementException {
    String FilePath = this.jsonFilesFolder + "NohaynodoterminalComa.json";
    String expectedMessage = "Error: invalid format in JSON file.";
    TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, ()-> {
      myManager.RequestToken(FilePath);
    });
    
    assertEquals (expectedMessage,ex.getMessage());
  }
  /*Prueba : AS-RF2-NV-15
  <Descripcion de prueba>: el nodo terminal 'coma' que hay despues de Token Resquest esta repetido,
  <Nodos cubiertos> : afecta al nodo terminal 15
  <DATO>: esta prueba se haria igual para comprobar el segundo separador es decir la coma
  entre Request date y Notification email*/
  @DisplayName ("Nodo terminal Coma Repetido")
  @Test
  void NodoterminalComaRepetido () throws TokenManagementException {
    String FilePath = this.jsonFilesFolder + "NodoterminalComaRepetido.json";
    String expectedMessage = "Error: invalid format in JSON file.";
    TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, ()-> {
      myManager.RequestToken(FilePath);
    });
    
    assertEquals (expectedMessage,ex.getMessage());
  }
  /*Prueba : AS-RF2-NV-16
  <Descripcion de prueba>: el nodo terminal 'coma' que hay despues de Token Resquest esta modificado por una barra baja,
  <Nodos cubiertos> : afecta al nodo terminal 15
  <DATO>: esta prueba se haria igual para comprobar el segundo separador es decir la coma
  entre Request date y Notification email*/
  @DisplayName ("Nodo terminal Coma Modificado")
  @Test
  void NodoterminalComaModificado () throws TokenManagementException {
    String FilePath = this.jsonFilesFolder + "NodoterminalComaModificado.json";
    String expectedMessage = "Error: invalid format in JSON file.";
    TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, ()-> {
      myManager.RequestToken(FilePath);
    });
    
    assertEquals (expectedMessage,ex.getMessage());
  }
  
  /*Prueba : AS-RF2-NV-17
  <Descripcion de prueba>: no hay nodo terminal 'igualdad' en el campo Token Request,
  <Nodos cubiertos> : afecta al nodo terminal 26
  <DATO>: esta prueba cubre los casos para la falta de igualdad en los campos Request date y Notification email*/
  @DisplayName ("No hay nodo terminal Igualdad")
  @Test
  void NohaynodoterminalIgualdad () throws TokenManagementException {
    String FilePath = this.jsonFilesFolder + "NohaynodoterminalIgualdad.json";
    String expectedMessage = "Error: invalid format in JSON file.";
    TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, ()-> {
      myManager.RequestToken(FilePath);
    });
    
    assertEquals (expectedMessage,ex.getMessage());
  }
  /*Prueba : AS-RF2-NV-18
  <Descripcion de prueba>: el nodo terminal 'igualdad' esta repetido 2 veces en el campo Token Request,
  <Nodos cubiertos> : afecta al nodo terminal 26
  <DATO>: esta prueba cubre los casos para igualdad duplicada en los campos Request date y Notification email*/
  @DisplayName ("Nodo terminal Igualdad Repetido")
  @Test
  void NodoterminalIgualdadRepetido () throws TokenManagementException {
    String FilePath = this.jsonFilesFolder + "NodoterminalIgualdadRepetido.json";
    String expectedMessage = "Error: invalid format in JSON file.";
    TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, ()-> {
      myManager.RequestToken(FilePath);
    });
    
    assertEquals (expectedMessage,ex.getMessage());
  }
  /*Prueba : AS-RF2-NV-19
  <Descripcion de prueba>: el nodo terminal 'igualdad' esta modificado por un dolar en el campo Token Request,
  <Nodos cubiertos> : afecta al nodo terminal 26
  <DATO>: esta prueba cubre los casos para igualdad modificada en los campos Request date y Notification email*/
  @DisplayName ("Nodo terminal Igualdad Modificado")
  @Test
  void NodoterminalIgualdadModificado () throws TokenManagementException {
    String FilePath = this.jsonFilesFolder + "NodoterminalIgualdadModificado.json";
    String expectedMessage = "Error: invalid format in JSON file.";
    TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, ()-> {
      myManager.RequestToken(FilePath);
    });
    
    assertEquals (expectedMessage,ex.getMessage());
  }
  /*Prueba : AS-RF2-NV-20
  <Descripcion de prueba>: no hay nodo terminal 'comillas' en el campo NotificationEmail,
  <Nodos cubiertos> : afecta al nodo terminal QUE ESTÁ DE CIERRE EN CIERRE COMILLAS DE ETIQUETA_NOTIFICATIONEMAIL
  <DATO>: esta prueba cubre los casos para la falta de comillas en los campos Request date y token request*/
  @DisplayName ("No hay nodo terminal Comillas")
  @Test
  void NohaynodoterminalComillas () throws TokenManagementException {
    String FilePath = this.jsonFilesFolder + "NohaynodoterminalComillas.json";
    String expectedMessage = "Error: invalid format in JSON file.";
    TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, ()-> {
      myManager.RequestToken(FilePath);
    });
    
    assertEquals (expectedMessage,ex.getMessage());
  }
  /*Prueba : AS-RF2-NV-21
  <Descripcion de prueba>:  nodo terminal 'comillas' repetido en el campo NotificationEmail,
  <Nodos cubiertos> : afecta al nodo terminal QUE ESTÁ DE CIERRE EN CIERRE COMILLAS DE ETIQUETA_NOTIFICATIONEMAIL
  <DATO>: esta prueba cubre los casos para la repeticion de comillas en los campos Request date y token request*/
  @DisplayName ("Nodo terminal Comillas Repetido")
  @Test
  void NodoterminalComillasRepetido () throws TokenManagementException {
    String FilePath = this.jsonFilesFolder + "NodoterminalComillasRepetido.json";
    String expectedMessage = "Error: invalid format in JSON file.";
    TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, ()-> {
      myManager.RequestToken(FilePath);
    });
    
    assertEquals (expectedMessage,ex.getMessage());
  }
  /*Prueba : AS-RF2-NV-22
  <Descripcion de prueba>:  nodo terminal 'comillas' modificado en el campo NotificationEmail,
  <Nodos cubiertos> : afecta al nodo terminal QUE ESTÁ DE CIERRE EN CIERRE COMILLAS DE ETIQUETA_NOTIFICATIONEMAIL
  <DATO>: esta prueba cubre los casos para la modificacion de comillas en los campos Request date y token request*/
  @DisplayName ("Nodo terminal Comillas Modificado")
  @Test
  void NodoterminalComillasModificado () throws TokenManagementException {
    String FilePath = this.jsonFilesFolder + "NodoterminalComillasModificado.json";
    String expectedMessage = "Error: invalid format in JSON file.";
    TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, ()-> {
      myManager.RequestToken(FilePath);
    });
    
    assertEquals (expectedMessage,ex.getMessage());
  }
  /*Prueba : AS-RF2-NV-21
  <Descripcion de prueba>: no hay nodo no terminal 'punto' en el campo NotificationEmail,
  <Nodos cubiertos> : afecta al nodo no terminal 55 y como se elimina tambien el nodo terminal 86*/
  @DisplayName ("No hay nodo no terminal Punto")
  @Test
  void NohaynodoNoterminalPunto () throws TokenManagementException {
    String FilePath = this.jsonFilesFolder + "NohaynodoNoterminalPunto.json";
    String expectedMessage = "Error: valor invalido en campo notification e-mail.";
    TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, ()-> {
      myManager.RequestToken(FilePath);
    });
    
    assertEquals (expectedMessage,ex.getMessage());
  }
  /*Prueba : AS-RF2-NV-22
  <Descripcion de prueba>:  nodo  terminal 'punto' repetido en el campo NotificationEmail,
  <Nodos cubiertos> : afecta al nodo no terminal 55 y como se duplica tambien el nodo terminal 86*/
  @DisplayName ("Nodo terminal Punto Repetido")
  @Test
  void NodoterminalPuntoRepetido () throws TokenManagementException {
    String FilePath = this.jsonFilesFolder + "NodoterminalPuntoRepetido.json";
    String expectedMessage = "Error: valor invalido en campo notification e-mail.";
    TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, ()-> {
      myManager.RequestToken(FilePath);
    });
    
    assertEquals (expectedMessage,ex.getMessage());
  }
  /*Prueba : AS-RF2-NV-23
  <Descripcion de prueba>:  nodo  terminal 'punto' modificado en el campo NotificationEmail,
  <Nodos cubiertos> : afecta al nodo no terminal 55 y tambien el nodo terminal 86 que se modifica por el simbolo and*/
  @DisplayName ("Nodo terminal Punto Modificado")
  @Test
  void NodoterminalPuntoModificado () throws TokenManagementException {
    String FilePath = this.jsonFilesFolder + "NodoterminalPuntoModificado.json";
    String expectedMessage = "Error: valor invalido en campo notification e-mail.";
    TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, ()-> {
      myManager.RequestToken(FilePath);
    });
    
    assertEquals (expectedMessage,ex.getMessage());
  }
  /*Prueba : AS-RF2-NV-24
  <Descripcion de prueba>: no hay nodo no terminal 'guion' en el campo Request date,
  <Nodos cubiertos> : afecta al nodo no terminal 36 y como se elimina tambien el nodo terminal 71*/
  @DisplayName ("No hay nodo no terminal guion")
  @Test
  void NohaynodoNoterminalguion () throws TokenManagementException {
    String FilePath = this.jsonFilesFolder + "NohaynodoNoterminalguion.json";
    String expectedMessage = "Error: invalid Date";
    TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, ()-> {
      myManager.RequestToken(FilePath);
    });
    
    assertEquals (expectedMessage,ex.getMessage());
  }
  /*Prueba : AS-RF2-NV-25
  <Descripcion de prueba>:  nodo no terminal 'guion'repetido en el campo Request date,
  <Nodos cubiertos> : afecta al nodo no terminal 36 y como se duplica tambien lo hace el nodo terminal 71*/
  @DisplayName ("Nodo no terminal guion Repetido")
  @Test
  void NodonoterminalguionRepetido () throws TokenManagementException {
    String FilePath = this.jsonFilesFolder + "NodonoterminalguionRepetido.json";
    String expectedMessage = "Error: invalid Date";
    TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, ()-> {
      myManager.RequestToken(FilePath);
    });
    
    assertEquals (expectedMessage,ex.getMessage());
  }
  
  /*Prueba : AS-RF2-NV-26
  <Descripcion de prueba>:  nodo no terminal 'guion'repetido en el campo Request date,
  <Nodos cubiertos> : afecta al nodo terminal 71*/
  @DisplayName ("Nodo terminal guion Modificacion")
  @Test
  void NodoterminalguionModificacion () throws TokenManagementException {
    String FilePath = this.jsonFilesFolder + "NodoterminalguionModificacion.json";
    String expectedMessage = "Error: invalid Date";
    TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, ()-> {
      myManager.RequestToken(FilePath);
    });
    
    assertEquals (expectedMessage,ex.getMessage());
  }
}

