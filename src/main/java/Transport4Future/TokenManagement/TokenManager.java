/*
 * Created on 20/02/2020
 * author: Hugo Romero
 *
 * Last updated on 25/02/2020
 * author: Khaoula El Mourtaqui
 */

package Transport4Future.TokenManagement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;
import java.util.Date;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.stream.JsonParsingException;

public class TokenManager {

  private void VerificarFormato(TokenRequest request) throws TokenManagementException {
    // este metodo funciona para verificar que los campos tienen un valor valido
    /* comprobar el Device Name */
    if (request.getDeviceName().length() > 20 || request.getDeviceName().length() < 1) {
      throw new TokenManagementException("Error: valor invalido en campo Device Name.");
    }
    /* comprobar el Type Of Device */
    if (!(request.getTypeOfDevice().equalsIgnoreCase("Sensor")
        || request.getTypeOfDevice().equalsIgnoreCase("Actuator"))) {
      throw new TokenManagementException("Error: valor invalido en campo Type of Device.");
    }
    /* comprobar el Driver Version */
    Pattern driverP = Pattern.compile("^\\d{4}.\\d{2}.\\d{2}$");
    if (request.getDriverVersion().length() > 25 || request.getDriverVersion().length() < 1
        || (!driverP.matcher(request.getDriverVersion()).matches())) {
      throw new TokenManagementException("Error: valor invalido en campo Driver Version.");
    }
    /* comprobar el Support Email */
    Pattern mailP = Pattern.compile(
        "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&’*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$");
    if (!mailP.matcher(request.getSupportEmail()).matches()) {
      throw new TokenManagementException("Error: valor invalido en campo Support e-mail.");
    }
    /* comprobar el Serial Number */
    Pattern serialNumberP = Pattern.compile("([A-Za-z0-9-]*4$)");
    if (!serialNumberP.matcher(request.getSerialNumber()).matches()) {
      throw new TokenManagementException("Error: valor invalido en campo Serial Number.");
    }
    /* comprobar el MAC Address */
    Pattern MACP = Pattern.compile("^([a-fA-F0-9]{2}:){5}[a-fA-F0-9]{2}$");
    if (!MACP.matcher(request.getMACAddress()).matches()) {
      throw new TokenManagementException("Error: valor invalido en campo MAC Address.");
    }
  }

  public String TokenRequestGenerationTest(String path) throws TokenManagementException {
    TokenRequest Token;
    String myToken;

    Token = readTokenRequestFromJSON(path);
    myToken = CodeHashMD5(Token);

    return myToken;
  }

  public String TokenRequestGeneration(String path) throws TokenManagementException {
    TokenRequest Token;
    String myToken;

    Token = readTokenRequestFromJSON(path);

    VerificarFormato(Token);

    myToken = CodeHashMD5(Token);

    return myToken;
  }

  public TokenRequest readTokenRequestFromJSON(String path) throws TokenManagementException {

    TokenRequest req = null;
    String fileContents = "";

    String deviceName = "";
    String typeOfDevice = "";
    String driverVersion = "";
    String supportEmail = "";
    String serialNumber = "";
    String macAddress = "";

    BufferedReader reader;
    try {
      reader = new BufferedReader(new FileReader(path));
    } catch (FileNotFoundException e) {
      throw new TokenManagementException("Error:input file not found.");
    }
    String line;
    try {
      while ((line = reader.readLine()) != null) {
        fileContents += line;
      }
    } catch (IOException e) {
      throw new TokenManagementException("Error: input file could not be accessed");
    }

    try {
      reader.close();
    } catch (IOException e) {
      throw new TokenManagementException("Error: input file could not be closed");
    }
    JsonObject jsonLicense = null;
    try {
      StringReader string_reader = new StringReader(fileContents);
      jsonLicense = Json.createReader(string_reader).readObject();
    } catch (Exception e) {
      throw new TokenManagementException("Error: Error de formato");
    }
    // JsonObject jsonLicense = Json.createReader(new StringReader (fileContents)).readObject();

    try {
      deviceName = jsonLicense.getString("Device Name");
      typeOfDevice = jsonLicense.getString("Type of Device");
      driverVersion = jsonLicense.getString("Driver Version");
      supportEmail = jsonLicense.getString("Support e-mail");
      serialNumber = jsonLicense.getString("Serial Number");
      macAddress = jsonLicense.getString("MAC Address");

    } catch (Exception pe) {
      throw new TokenManagementException("Error: invalid input data in JSON structure.");
    }
    req = new TokenRequest(deviceName, typeOfDevice, driverVersion, supportEmail, serialNumber,
        macAddress);
    VerificarFormato(req);
    return req;

  }

  public String CodeHashMD5(TokenRequest myToken) throws TokenManagementException {

    MessageDigest md;
    try {
      md = MessageDigest.getInstance("MD5");
    } catch (NoSuchAlgorithmException e) {
      throw new TokenManagementException("Error: no such hashing algorithm");
    }

    String input = "Stardust" + "-" + myToken.toString();

    md.update(input.getBytes(StandardCharsets.UTF_8));
    byte[] digest = md.digest();

    String hex = String.format("%32x", new BigInteger(1, digest));

    return hex;
  }

  private void VerificarFormato2(Token TokenToVerify) throws TokenManagementException {
    /* check de device */
    Pattern deviceP = Pattern.compile("[A-Fa-f0-9]*");
    if (!deviceP.matcher(TokenToVerify.getDevice()).matches()) {
      throw new TokenManagementException("Error: campo Device invalido");
    }

    /* e-mail verificacion */
    Pattern mailP = Pattern.compile(
        "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&’*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$");
    if (!mailP.matcher(TokenToVerify.getNotificationEmail()).matches()) {
      throw new TokenManagementException("Error: valor invalido en campo notification e-mail.");
    }
 

  }

  public String RequestToken(String InputFile) throws TokenManagementException {
    Token req = null;
    String fileContents = "";
    String tokenRequest = "";
    String email = "";
    Date date;
    String date_string = "";
    BufferedReader reader;
    try {
      reader = new BufferedReader(new FileReader(InputFile));
    } catch (FileNotFoundException e) {
      throw new TokenManagementException("Error:input file not found.");
    }
    String line;
    try {
      while ((line = reader.readLine()) != null) {
        fileContents += line;
      }
    } catch (IOException e) {
      throw new TokenManagementException("Error: input file could not be accessed");
    }

    try {
      reader.close();
    } catch (IOException e) {
      throw new TokenManagementException("Error: input file could not be closed");
    }
    JsonObject jsonLicense = null;
    try {
      jsonLicense = Json.createReader(new StringReader(fileContents)).readObject();
    } catch (JsonParsingException ex) {
      throw new TokenManagementException("Error: invalid format in JSON file.");
    }
    try {
      tokenRequest = jsonLicense.getString("Token Request");
      email = jsonLicense.getString("Notification e-mail");
      date_string = jsonLicense.getString("Request Date");
    } catch (Exception pe) {
      throw new TokenManagementException("Error: invalid input data in JSON structure.");
    }
    try {
      SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
      date = format.parse(date_string);
    } catch (Exception pe) {
      throw new TokenManagementException("Error: invalid Date");
    }
    /* hay ue crear en la clase token un metodo de esos tres campos */
    req = new Token(tokenRequest, date, email);
    VerificarFormato2(req);

    String encodedString = encodeStringCodeSHA256(req);
    req.setTokenValue(encodedString);

    TokenStore myStore = new TokenStore();
    myStore.Add(req);

    return req.getTokenValue();
  }

  private boolean isValid(Token tokenFound) {
    if ((!tokenFound.isExpired()) && (tokenFound.isGranted())) {
      return true;
    } else {
      return false;
    }
  }

  public boolean VerifyToken(String Token) throws TokenManagementException {
    TokenStore myStore = new TokenStore();
    boolean result = false;

    Token tokenFound = myStore.Find(Token);
    if (tokenFound != null) {
      result = isValid(tokenFound);
    } else {
      throw new TokenManagementException("Token is not found in TokenStore");
    }
    return result;
  }

  private String encodeStringCodeSHA256(Token token) throws TokenManagementException {
    MessageDigest md;
    try {
      md = MessageDigest.getInstance("SHA-256");
    } catch (NoSuchAlgorithmException e) {
      throw new TokenManagementException("Error: no suchhashing algorithm.");
    }
    String input = "Stardust" + " " + token.toString();

    md.update(input.getBytes(StandardCharsets.UTF_8));
    byte[] digest = md.digest();

    String signature = String.format("%64x", new BigInteger(1, digest));
    token.setSignature(signature);
    String signatureToEncode = token.getSignature();
    String encodedString = Base64.getUrlEncoder().encodeToString(signatureToEncode.getBytes());
    return encodedString;
  }

  public String TokenRequestGenerationEmpty(String path) throws TokenManagementException {

    TokenRequest Token;

    Token = ReadTokenRequestEmptyFromJSON(path);

    return Token.toString1();
  }

  public TokenRequest ReadTokenRequestEmptyFromJSON(String path) throws TokenManagementException {

    TokenRequest CK = new TokenRequest();

    File newFile = new File(path);
    if (newFile.length() == 0) {
      return CK;

    } else {

      throw new TokenManagementException("Error: File is not Empty");
    }

  }
  public String RequestTokenEmpty(String path) throws TokenManagementException {

    Token Token;

    Token = ReadRequestTokenEmptyFromJSON(path);

    return Token.toString1();
  }

  public Token ReadRequestTokenEmptyFromJSON(String path) throws TokenManagementException {

    Token CK = new Token();

    File newFile = new File(path);
    if (newFile.length() == 0) {
      return CK;

    } else {

      throw new TokenManagementException("Error: File is not Empty");
    }

  }

}
