/*
 * Created on 20/02/2020
 * author: Hugo Romero
 *
 * Last updated on 25/02/2020
 * author: Khaoula El Mourtaqui
 */

package Transport4Future.TokenManagement;

public class TokenManagementException extends Exception {
    private static final long serialVersionUID = 1L;
    
    String message;

    public TokenManagementException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
