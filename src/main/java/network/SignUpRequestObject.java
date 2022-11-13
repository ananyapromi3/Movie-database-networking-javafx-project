package network;

import java.io.Serializable;

public class SignUpRequestObject implements Serializable {
    String productionCompanyName;
    String password;

    public SignUpRequestObject(String productionCompanyName, String password) {
        this.productionCompanyName = productionCompanyName;
        this.password = password;
    }

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
}
