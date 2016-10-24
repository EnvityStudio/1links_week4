package StudentModel;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Created by Thuan Nguyen on 10/19/2016.
 */
public class Server {
    public static void main(String args[]) throws IOException {
            ServerSocket ss = new ServerSocket(9999);// Tao cong 9999 de server lang nghe


            while (true)// Cho client ket noi
            {
                 new ThreadSocket(ss.accept()).start();
            }
            //catch (InterruptedException ie)

        }
    }

