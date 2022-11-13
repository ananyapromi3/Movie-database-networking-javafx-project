package network;

import com.example.copy.UpdateFromNetwork;

import java.io.IOException;

public class ReadThreadClient implements Runnable {
    private Thread thr;
    private NetworkUtil networkUtil;
    private UpdateFromNetwork updateFromNetwork;

    public void setUpdateFromReadThread(UpdateFromNetwork updateFromNetwork) {
        this.updateFromNetwork = updateFromNetwork;
    }

    public ReadThreadClient(NetworkUtil networkUtil) {
        this.networkUtil = networkUtil;
        this.thr = new Thread(this);
        thr.start();
    }

    public void run() {
        try {
            while (true) {
                Object o = networkUtil.read();

                if (o instanceof SignUpResponseObject) {
                    updateFromNetwork.signUpAction((SignUpResponseObject) o);
                } else if (o instanceof LoginResponseObject) {
                    updateFromNetwork.loginAction((LoginResponseObject) o);
                }
                if (o instanceof UpdateRespond) {
                    updateFromNetwork.updateFromServerRespond((UpdateRespond) o);
                }
                if (o instanceof TerminateServerObject) {
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                networkUtil.closeConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
