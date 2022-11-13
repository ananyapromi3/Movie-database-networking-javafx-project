package network;

import java.io.IOException;
import java.io.Serializable;

public class ClientInfo implements Serializable {
    private NetworkUtil networkUtil;
    private int productionCompanyId = -1;

    public NetworkUtil getNetworkUtil() {
        return networkUtil;
    }

    public void setNetworkUtil(NetworkUtil networkUtil) {
        this.networkUtil = networkUtil;
    }

    public int getProductionCompanyId() {
        return this.productionCompanyId;
    }

    public void setProductionCompanyId(int productionCompanyId) {
        this.productionCompanyId = productionCompanyId;
    }

    public ClientInfo(NetworkUtil networkUtil) {
        this.networkUtil = networkUtil;
        this.productionCompanyId = -1;
    }

    public ClientInfo(NetworkUtil networkUtil, int productionCompanyId) {
        this.networkUtil = networkUtil;
        this.productionCompanyId = productionCompanyId;
    }

    public void write(Object o){
        try {
            networkUtil.write(o);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
