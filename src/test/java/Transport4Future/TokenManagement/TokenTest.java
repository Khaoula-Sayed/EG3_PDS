package Transport4Future.TokenManagement;

import static org.junit.jupiter.api.Assertions.*;

import javax.json.Json;
import javax.json.JsonObject;

import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
 

class TokenTest {
  private TokenManager myManager;
  private String jsonFilesFolder;
  
  public TokenTest () {
    jsonFilesFolder = System.getProperty("user.dir") + "/JSONFiles/TokenRequest/";
    
    myManager = new TokenManager ();
  }
  
  @DisplayName ("No existe fichero")
  @Test
  void FileIsMissingTest () throws TokenManagementException {
    String FilePath = this.jsonFilesFolder + "isdCorrect.json";
    String expectedMessage = "Error:input file not found.";
    TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, ()-> {
      myManager.TokenRequestGeneration(FilePath);
    });
    
    assertEquals (expectedMessage,ex.getMessage());
  }
   
  /*<INFORMACION>: este test correcto cubre los test validos de campo existente y valor correcto para todos los campos
   * es decir para las pruebas -> CE-RF1-V-05, CE-RF1-V-10, CE-RF1-V-16, CE-RF1-V-19, CE-RF1-V-20, CE-RF1-V-25, CE-RF1-V-28, CE-RF1-V-30*/
  
  @DisplayName ("correct token Generation")
  @Test
  void correctTokenGenerationTest() throws TokenManagementException{
    String FilePath = this.jsonFilesFolder + "Correct.json";
    String expectedToken = "b2093a3002f6e66d8bff5fe478805772";
    String obtainedToken = myManager.TokenRequestGenerationTest(FilePath);
    assertEquals(expectedToken,obtainedToken);
  }
    
  @DisplayName ("Sintaxis Incorrecta Falta el valor dentro del campo Device Name")
  @Test
  void InCorrectSintaxisDevice() throws TokenManagementException{
    String FilePath = this.jsonFilesFolder + "CampoDeviceNameVacio.json";
    String expectedMessage = "Error: valor invalido en campo Device Name.";
    TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, ()-> {
      myManager.TokenRequestGeneration(FilePath);
    });
    
    assertEquals (expectedMessage,ex.getMessage());
  }
  
  @DisplayName ("Sintaxis Incorrecta Falta el valor dentro del campo Type Of Device")
  @Test
  void InCorrectSintaxisTypeOfDevice() throws TokenManagementException{
    String FilePath = this.jsonFilesFolder + "CampoTypeOfDeviceVacio.json";
    String expectedMessage = "Error: valor invalido en campo Type of Device.";
    TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, ()-> {
      myManager.TokenRequestGeneration(FilePath);
    });
    
    assertEquals (expectedMessage,ex.getMessage());
  }
  @DisplayName ("Sintaxis Incorrecta Falta el valor dentro del campo Driver Version")
  @Test
  void InCorrectSintaxisDriverVersion() throws TokenManagementException{
    String FilePath = this.jsonFilesFolder + "CampoDriverVersionVacio.json";
    String expectedMessage = "Error: valor invalido en campo Driver Version.";
    TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, ()-> {
      myManager.TokenRequestGeneration(FilePath);
    });
    
    assertEquals (expectedMessage,ex.getMessage());
  }
  
  @DisplayName ("Sintaxis Incorrecta Falta el valor dentro del campo Serial Number")
  @Test
  void InCorrectSintaxisSerialNumber() throws TokenManagementException{
    String FilePath = this.jsonFilesFolder + "CampoSerialNumberVacio.json";
    String expectedMessage = "Error: valor invalido en campo Serial Number.";
    TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, ()-> {
      myManager.TokenRequestGeneration(FilePath);
    });
    
    assertEquals (expectedMessage,ex.getMessage());
  }
  @DisplayName ("Sintaxis Incorrecta Falta el valor dentro del campo Support Email")
  @Test
  void InCorrectSintaxisSupportEmail() throws TokenManagementException{
    String FilePath = this.jsonFilesFolder + "CampoSupportEmailVacio.json";
    String expectedMessage = "Error: valor invalido en campo Support e-mail.";
    TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, ()-> {
      myManager.TokenRequestGeneration(FilePath);
    });
    
    assertEquals (expectedMessage,ex.getMessage());
  }
  @DisplayName ("Sintaxis Incorrecta Falta el valor dentro del campo MAC Address")
  @Test
  void InCorrectSintaxisMACAddress() throws TokenManagementException{
    String FilePath = this.jsonFilesFolder + "CampoMACAddressVacio.json";
    String expectedMessage = "Error: valor invalido en campo MAC Address.";
    TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, ()-> {
      myManager.TokenRequestGeneration(FilePath);
    });
    
    assertEquals (expectedMessage,ex.getMessage());
  }
  @DisplayName ("Fichero vacio")
  @Test
  void FicheroVacio() throws TokenManagementException{
    String FilePath = this.jsonFilesFolder + "EmptyFile.json";
    String expectedToken = "";
    String obtainedToken = myManager.TokenRequestGenerationEmpty(FilePath);
    assertEquals(expectedToken,obtainedToken);
  }

  public final void SintaxisIncorrectaFaltaCampoDevice() {
      String FilePath = this.jsonFilesFolder + "WithoutDeviceName.json";
      String expectedMessage = "Error: invalid input data in JSON structure.";
      TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, ()-> {
        myManager.TokenRequestGeneration(FilePath);
      });
      
      assertEquals (expectedMessage,ex.getMessage());
    }
  @Test
  public final void SintaxisIncorrectaEnDeviceName() {
    String FilePath = this.jsonFilesFolder + "invalidDeviceName.json";
    String expectedMessage = "Error: valor invalido en campo Device Name.";
    TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, ()-> {
      myManager.TokenRequestGeneration(FilePath);
    });
    
    assertEquals (expectedMessage,ex.getMessage());
  }

  @Test
  public final void SintaxisIncorrectaFaltaCampoTypeOfDevice() {
      String FilePath = this.jsonFilesFolder + "WithoutTypeOfDevice.json";
      String expectedMessage = "Error: invalid input data in JSON structure.";
      TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, ()-> {
        myManager.TokenRequestGeneration(FilePath);
      });
      
      assertEquals (expectedMessage,ex.getMessage());
    }

  @Test
  public final void SintaxisIncorrectaEnTypeOfDevice() {
      String FilePath = this.jsonFilesFolder + "invalidTypeOfDevice.json";
      String expectedMessage = "Error: valor invalido en campo Type of Device.";
      TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, ()-> {
        myManager.TokenRequestGeneration(FilePath);
      });
      
      assertEquals (expectedMessage,ex.getMessage());
    }
  
  @Test
  public final void SintaxisIncorrectaFaltaCampoDriverVersion() {
      String FilePath = this.jsonFilesFolder + "WithoutDriverVersion.json";
      String expectedMessage = "Error: invalid input data in JSON structure.";
      TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, ()-> {
        myManager.TokenRequestGeneration(FilePath);
      });
      
      assertEquals (expectedMessage,ex.getMessage());
    }
  @Test
  public final void SintaxisIncorrectaEnDriverVersion() {
    String FilePath = this.jsonFilesFolder + "invalidDriverVersion.json";
    String expectedMessage = "Error: valor invalido en campo Driver Version.";
    TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, ()-> {
      myManager.TokenRequestGeneration(FilePath);
    });
    
    assertEquals (expectedMessage,ex.getMessage());
  }
  
  
  @Test
  public final void SintaxisIncorrectaFaltaCampoSerialNumber() {
      String FilePath = this.jsonFilesFolder + "WithoutSerialNumber.json";
      String expectedMessage = "Error: invalid input data in JSON structure.";
      TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, ()-> {
        myManager.TokenRequestGeneration(FilePath);
      });
      
      assertEquals (expectedMessage,ex.getMessage());
    }
  @Test
  public final void SintaxisIncorrectaEnSerialNumber() {
    String FilePath = this.jsonFilesFolder + "invalidSerialNumber.json";
    String expectedMessage = "Error: valor invalido en campo Serial Number.";
    TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, ()-> {
      myManager.TokenRequestGeneration(FilePath);
    });
    
    assertEquals (expectedMessage,ex.getMessage());
  }
  
  @Test
  public final void SintaxisIncorrectaFaltaCampoSupportEmail() {
    String FilePath = this.jsonFilesFolder + "WithoutSupportEmail.json";
    String expectedMessage = "Error: invalid input data in JSON structure.";
    TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, ()-> {
      myManager.TokenRequestGeneration(FilePath);
    });
    
    assertEquals (expectedMessage,ex.getMessage());
  }
  @Test
  public final void SintaxisIncorrectaEnSupportEmail() {
    String FilePath = this.jsonFilesFolder + "invalidSupportEmail.json";
    String expectedMessage = "Error: valor invalido en campo Support e-mail.";
    TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, ()-> {
      myManager.TokenRequestGeneration(FilePath);
    });
    
    assertEquals (expectedMessage,ex.getMessage());
  }
  
  @Test
  public final void SintaxisIncorrectaFaltaCampoMACAddress() {
    String FilePath = this.jsonFilesFolder + "withoutMACAddress.json";
    String expectedMessage = "Error: invalid input data in JSON structure.";
    TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, ()-> {
      myManager.TokenRequestGeneration(FilePath);
    });
    
    assertEquals (expectedMessage,ex.getMessage());
  }
  @Test
  public final void SintaxisIncorrectaEnMACAddress() {
    String FilePath = this.jsonFilesFolder + "invalidMACAddress.json";
    String expectedMessage = "Error: valor invalido en campo MAC Address.";
    TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, ()-> {
      myManager.TokenRequestGeneration(FilePath);
    });
    
    assertEquals (expectedMessage,ex.getMessage());
  }
  @DisplayName ("ValorLimiteDeviceNameLong1")
  @Test
  void ValorLimiteDeviceNameLong1() throws TokenManagementException{
    String FilePath = this.jsonFilesFolder + "ValorLimiteDeviceNameLong1.json";
    String expectedToken = "faf72eb0b51f359198d44dd191ff49f5";
    String obtainedToken = myManager.TokenRequestGenerationTest(FilePath);
    assertEquals(expectedToken,obtainedToken);
  } 
  @DisplayName ("ValorLimiteDeviceNameLong20")
  @Test
  void ValorLimiteDeviceNameLong20() throws TokenManagementException{
    String FilePath = this.jsonFilesFolder + "ValorLimiteDeviceNameLong20.json";
    String expectedToken = "6cf2447206d713078528c974211eaef8";
    String obtainedToken = myManager.TokenRequestGenerationTest(FilePath);
    assertEquals(expectedToken,obtainedToken);
  } 
  @DisplayName ("ValorLimiteDeviceNameLong19")
  @Test
  void ValorLimiteDeviceNameLong19() throws TokenManagementException{
    String FilePath = this.jsonFilesFolder + "ValorLimiteDeviceNameLong19.json";
    String expectedToken = "d0af27c0d0c4761cc62af7f7f26e5fb4";
    String obtainedToken = myManager.TokenRequestGenerationTest(FilePath);
    assertEquals(expectedToken,obtainedToken);
  } 
  @DisplayName ("ValorLimiteDeviceNameLong21")
  @Test
  void ValorLimiteDeviceNameLong21() throws TokenManagementException{
    String FilePath = this.jsonFilesFolder + "ValorLimiteDeviceNameLong21.json";
    String expectedMessage = "Error: valor invalido en campo Device Name.";
    TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, ()-> {
      myManager.TokenRequestGeneration(FilePath);
    });
    
    assertEquals (expectedMessage,ex.getMessage());
  }
  @DisplayName ("ValorLimiteDriverVersion20200302")
  @Test
  void ValorLimiteDriverVersion20200302() throws TokenManagementException{
    String FilePath = this.jsonFilesFolder + "ValorLimiteDriverVersion20200302.json";
    String expectedMessage = "Error: valor invalido en campo Driver Version.";
    TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, ()-> {
      myManager.TokenRequestGeneration(FilePath);
    });
    
    assertEquals (expectedMessage,ex.getMessage());
  }
  @DisplayName ("ValorLimiteDriverVersion0000.00.00")
  @Test
  void ValorLimiteDriverVersion00000000() throws TokenManagementException{
    String FilePath = this.jsonFilesFolder + "ValorLimiteDriverVersion0000.00.00.json";
    String expectedToken = "c39e1e39e0214628b6903a2b4b07aaac";
    String obtainedToken = myManager.TokenRequestGenerationTest(FilePath);
    assertEquals(expectedToken,obtainedToken);
  }
  @DisplayName ("ValorLimiteDriverVersion2020.03.02")
  @Test
  void ValorLimiteDriverVersion20200302ConPunto() throws TokenManagementException{
    String FilePath = this.jsonFilesFolder + "ValorLimiteDriverVersion2020.03.02.json";
    String expectedToken = "d092719797f4a672b21b6c2cb0f59160";
    String obtainedToken = myManager.TokenRequestGenerationTest(FilePath);
    assertEquals(expectedToken,obtainedToken);
  }
  @DisplayName ("ValorLimiteSerialNumber72")
  @Test
  void ValorLimiteSerialNumber72() throws TokenManagementException{
    String FilePath = this.jsonFilesFolder + "ValorLimiteSerialNumber72.json";
    String expectedMessage = "Error: valor invalido en campo Serial Number.";
    TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, ()-> {
      myManager.TokenRequestGeneration(FilePath);
    });
    
    assertEquals (expectedMessage,ex.getMessage());
  }
  @DisplayName ("ValorLimiteSerialNumber798856245944")
  @Test
  void ValorLimiteSerialNumber798856245944() throws TokenManagementException{
    String FilePath = this.jsonFilesFolder + "ValorLimiteSerialNumber798856245944.json";
    String expectedToken = "a823352c6db08b279d7a3eedab003e50";
    String obtainedToken = myManager.TokenRequestGenerationTest(FilePath);
    assertEquals(expectedToken,obtainedToken);
  }
  @DisplayName ("ValorLimiteSerialNumber79845ESPACIO62459")
  @Test
  void ValorLimiteSerialNumber79845ESPACIO62459() throws TokenManagementException{
    String FilePath = this.jsonFilesFolder + "ValorLimiteSerialNumber79845ESPACIO62459.json";
    String expectedMessage = "Error: valor invalido en campo Serial Number.";
    TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, ()-> {
      myManager.TokenRequestGeneration(FilePath);
    });
    
    assertEquals (expectedMessage,ex.getMessage());
  }
  @DisplayName ("ValorLimiteTypeOfDeviceEqualsActuator")
  @Test
  void ValorLimiteTypeOfDeviceEqualsActuator() throws TokenManagementException{
    String FilePath = this.jsonFilesFolder + "ValorLimiteTypeOfDeviceEqualsActuator.json";
    String expectedToken = "2f80f6012b14c475ad89bdc8703fd3a2";
    String obtainedToken = myManager.TokenRequestGenerationTest(FilePath);
    assertEquals(expectedToken,obtainedToken);
  }
  @DisplayName ("ValorLimiteTypeOfDeviceEqualsSensor")
  @Test
  void ValorLimiteTypeOfDeviceEqualsSensor() throws TokenManagementException{
    String FilePath = this.jsonFilesFolder + "ValorLimiteTypeOfDeviceEqualsSensor.json";
    String expectedToken = "b2093a3002f6e66d8bff5fe478805772";
    String obtainedToken = myManager.TokenRequestGenerationTest(FilePath);
    assertEquals(expectedToken,obtainedToken);
  }
  @DisplayName ("ValorLimiteTypeOfDeviceEqualsDIFERENT")
  @Test
  void ValorLimiteTypeOfDeviceEqualsDIFERENT() throws TokenManagementException{
    String FilePath = this.jsonFilesFolder + "ValorLimiteTypeOfDeviceEqualsDIFERENT.json";
    String expectedMessage = "Error: valor invalido en campo Type of Device.";
    TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, ()-> {
      myManager.TokenRequestGeneration(FilePath);
    });
    
    assertEquals (expectedMessage,ex.getMessage());
  }
  @DisplayName ("Incorrect")
  @Test
  void Incorrect() throws TokenManagementException{
    String FilePath = this.jsonFilesFolder + "Incorrect.json";
    String expectedMessage = "Error: invalid input data in JSON structure.";
    TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class, ()-> {
      myManager.TokenRequestGeneration(FilePath);
    });
    
    assertEquals (expectedMessage,ex.getMessage());
  }
}		
	