package network;

import java.io.Serializable;

public class LoginRequestObject implements Serializable {
    String productionCompanyName;
    String password;

    public String getProductionCompanyName() {
        return productionCompanyName;
    }

    public void setProductionCompanyName(String productionCompanyName) {
        this.productionCompanyName = productionCompanyName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LoginRequestObject(String productionCompanyName, String password) {
        this.password = password;
        this.productionCompanyName = productionCompanyName;
    }
}
