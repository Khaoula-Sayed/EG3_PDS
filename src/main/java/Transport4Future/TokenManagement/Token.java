package Transport4Future.TokenManagement;

import java.util.Date;

  public class Token {
  private String alg;
  private String typ;
  private String device;
  private Date requestDate;
  private String notificationEmail;
  private long iat;
  private long exp;
  private String signature;
  private String tokenValue;

  public Token(String Device, Date RequestDate, String NotificationEmail) {
    this.alg = "HS256";
    this.typ = "PDS";
    this.device = Device;
    this.requestDate = RequestDate;
    this.notificationEmail = NotificationEmail;
    // this.iat= System.currentTimeMillis();
    this.iat = 1583780309000L;
    this.exp = this.iat + 6048000001l;
    this.signature = null;
    this.tokenValue = null;
  }

  
  /*
   * Metodo to string de un token
   */
  public Token() {
    
  } 
  public String toString1() {
    return "";
   
  }
  public String toString() {
    return "TokenRequest {\n\tAlg: " + this.alg + ",\n\tTyp: " + this.typ + ",\n\tDevice: " + this.device
        + ",\n\trequestDate: " + this.requestDate + ",\n\tNotification Email: " + this.notificationEmail
        + ",\n\tIAT: " + this.iat + ",\n\tEXP: " + this.exp + "\n}";
  }

  public boolean isGranted() {
    if (this.iat < System.currentTimeMillis()) {
      return true;
    } else {
      return false;
    }
  }

  public boolean isExpired() {
    if (this.exp > System.currentTimeMillis()) {
      return false;
    } else {
      return true;
    }
  }

  public String getAlg() {
    return alg;
  }

  public void setAlg(String alg) {
    this.alg = alg;
  }

  public String getTyp() {
    return typ;
  }

  public void setTyp(String typ) {
    this.typ = typ;
  }

  public String getDevice() {
    return device;
  }

  public void setDevice(String device) {
    this.device = device;
  }

  public Date getRequestDate() {
    return requestDate;
  }

  public void setRequestDate(Date requestDate) {
    this.requestDate = requestDate;
  }

  public String getNotificationEmail() {
    return notificationEmail;
  }

  public void setNotificationEmail(String notificationEmail) {
    this.notificationEmail = notificationEmail;
  }

  public long getIat() {
    return iat;
  }

  public void setIat(long iat) {
    this.iat = iat;
  }

  public long getExp() {
    return exp;
  }

  public void setExp(long exp) {
    this.exp = exp;
  }

  public String getSignature() {
    return signature;
  }

  public void setSignature(String signature) {
    this.signature = signature;
  }

  public String getTokenValue() {
    return tokenValue;
  }

  public void setTokenValue(String tokenValue) {
    this.tokenValue = tokenValue;
  }
}