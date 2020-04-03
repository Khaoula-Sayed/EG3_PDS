/*
 * Created on 20/02/2020
 * author: Hugo Romero
 *
 * Last updated on 25/02/2020
 * author: Khaoula El Mourtaqui
 */

package Transport4Future.TokenManagement;

import java.util.Date;

public class TokenRequest {
  
  private String deviceName;
  
  private String typeOfDevice;
  
  private String driverVersion;
  
  private String supportEmail;
  
  private String serialNumber;
  
  private String macAddress;
  
  public TokenRequest() {
    
  }
  public String toString1() {
      return "";
     
    }
  public TokenRequest(String deviceName, String typeOfDevice,String driverVersion,String supportEmail,
      String serialNumber, String macAddress) {
    this.deviceName = deviceName;
    this.typeOfDevice = typeOfDevice;
    this.driverVersion = driverVersion;
    this.supportEmail = supportEmail;
    this.serialNumber = serialNumber;
    this.macAddress = macAddress;
  }
  

public String toString() {
    return "TokenRequest [\n\tDevice Name="
      + this.deviceName + ",\n\tType of Device=" 
      + this.typeOfDevice + ",\n\tDriver Version="
      + this.driverVersion+ ",\n\tSupport e-mail="
      + this.supportEmail +",\n\tSerial Number="
      + this.serialNumber + ",\n\tMAC Address=" 
      + this.macAddress + "\n]";
   
  }
public String getDeviceName() {
  return deviceName;
}

public String getTypeOfDevice() {
  return typeOfDevice;
}

public String getDriverVersion() {
  return driverVersion;
}

public String getSupportEmail() {
  return supportEmail;
}

public String getSerialNumber() {
  return serialNumber;
}

public String getMACAddress() {
  return macAddress;
}
}