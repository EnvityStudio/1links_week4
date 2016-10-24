package View;

import Student_Model.StudentModel;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by Thuan Nguyen on 10/20/2016.
 */


public class Main {
    public static void main(String args[]) throws IOException {
       View view = new View();
        try {
            String cau1;// Cau duoc gui toi server
            String ketQua;//Cau duoc server xu ly va tra lai la in hoa

            BufferedReader inFormUser= new BufferedReader(new InputStreamReader(System.in));// Tao input stream
            Socket clientSocket= new Socket("127.0.0.1",9999);// Tao clinent socket de ket noi toi server
            DataOutputStream sendToServer= new DataOutputStream(clientSocket.getOutputStream());// Tao output stream ket noi toi socket
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
       //     String json = "{\"request\":\"2\"}";
          //  String json = view.AddStudent();
            StudentModel studentModel = new StudentModel();
            while(true)
            {
                view.SelectMenuMain();


                ketQua = inFromServer.readLine();// Nhan lai tu server
                System.out.println("FROM SERVER: " +ketQua);
            }


        } catch (IOException e) {
            System.out.println("Exception Client: "+e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
