package network;

import java.io.IOException;

public class ReadThreadServer implements Runnable {
    private Thread thr;
    private NetworkUtil networkUtil;
    WriteThreadServer writeThreadServer;

    public ReadThreadServer(NetworkUtil networkUtil, WriteThreadServer writeThreadServer) {
        this.networkUtil = networkUtil;
        this.writeThreadServer = writeThreadServer;
        this.thr = new Thread(this);
        thr.start();
    }

    @Override
    public void run() {
        try {
            while (true) {
                Object o = networkUtil.read();
                System.out.println(o);

                if (o instanceof LoginRequestObject) {
                    new Thread(() -> writeThreadServer.login((LoginRequestObject) o, networkUtil)).start();
                }

                if (o instanceof SignUpRequestObject) {
                    new Thread(() -> writeThreadServer.signUp((SignUpRequestObject) o, networkUtil)).start();
                }

                if (o instanceof TransferRequestObject) {
                    new Thread(() -> writeThreadServer.transfer((TransferRequestObject) o)).start();
                }

                if (o instanceof AddMovieRequestObject) {
                    new Thread(() -> writeThreadServer.receive((AddMovieRequestObject) o)).start();
                }
                if (o instanceof AddMovieObject) {
                    new Thread(() -> writeThreadServer.addMovie((AddMovieObject) o)).start();
                }

                if (o instanceof LogoutRequestObject) {
                    new Thread(() -> writeThreadServer.logout(networkUtil)).start();
                }

                if (o instanceof TerminateServerObject) {
                    new Thread(() -> writeThreadServer.logout(networkUtil)).start();
                    networkUtil.write(o);
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
