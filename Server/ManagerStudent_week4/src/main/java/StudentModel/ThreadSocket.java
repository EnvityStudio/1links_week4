package StudentModel;

import Database.Database;
import Entity.Student;
import StudentController.StudentController;
import com.google.gson.Gson;
import org.json.simple.JSONArray;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by Thuan Nguyen on 10/19/2016.
 */
public class ThreadSocket extends Thread{
    Socket socket =null;
    ServerSocket serverSocket = null;
    public ThreadSocket(Socket socket) throws IOException {
        this.socket = socket;
    }
    public void run()
    {
        try {
            DataOutputStream sendToClient= new DataOutputStream(socket.getOutputStream());// Tao output stream
            BufferedReader fromClient= new BufferedReader(new InputStreamReader(socket.getInputStream()));//Tao input stream
            StudentModel_Server studentModelServer = new StudentModel_Server();
            while (true)
            {
                String sentence=fromClient.readLine();// Chuỗi nhận được từ Client


                System.out.println("FROM CLIENT: " + sentence);
                int request = studentModelServer.ParseJSON(sentence);
                if(request == 2)
                {
                    studentModelServer.JsonString(socket);
                }
                if(request == 1)
                {
                    studentModelServer.AddStudent(sentence);
                    sendToClient.writeBytes("Added" +"\n");
                }
                if(request == 22)
                {
                    studentModelServer.DeleteStudent(sentence,socket);
                }
                if(request == 21)
                {
                    studentModelServer.UpdateDatabase(sentence);
                }

            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


