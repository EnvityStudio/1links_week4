package StudentModel;

import Database.Database;
import Entity.Student;
import com.google.gson.Gson;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by Thuan Nguyen on 10/21/2016.
 */
public class StudentModel_Server {


    public void SendListStudent(String json,Socket socket) throws IOException {
        DataOutputStream sendToClient= new DataOutputStream(socket.getOutputStream());// Tao output stream
        sendToClient.writeBytes(json+"\n");
    }
    public void JsonString(Socket socket) throws IOException {
        Database database = new Database();
        ArrayList<Student>listStudent = database.SelectDatabase();
        JSONArray jsonArray = new JSONArray();
        jsonArray.addAll(listStudent);
        String json = new Gson().toJson(listStudent);
        DataOutputStream sendToClient= new DataOutputStream(socket.getOutputStream());// Tao output stream
        sendToClient.writeBytes(json+"\n");
    }

    public int ParseJSON(String jsonString) throws ParseException {
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject)jsonParser.parse(jsonString);
        int request = Integer.parseInt((String)jsonObject.get("request"));
        return request;
    }
    public void AddStudent(String jsonString) throws ParseException {
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject)jsonParser.parse(jsonString);
        Database database = new Database();
        String name = (String) jsonObject.get("name") ;
        String id = (String) jsonObject.get("Id");
            String address = (String) jsonObject.get("address");
            String sex = (String) jsonObject.get("sex");
            double mathPoint = Double.parseDouble(String.valueOf(jsonObject.get("mathPoint")));
            double physicalPoint = Double.parseDouble(String.valueOf(jsonObject.get("mathPoint")));
            double chemistryPoint = Double.parseDouble(String.valueOf(jsonObject.get("mathPoint")));
            Student st = new Student(id, name, sex, address, mathPoint, physicalPoint, chemistryPoint);
            String abc = " INSERT INTO STUDENT (ID,Name,Sex,Address,MathPoint,PhysicalPoint,ChemistryPoint )";
            String str = abc + "VALUES( '" + st.getId() + "'," + "'" + st.getName() + "'," + "'" + st.getSex() + "'," + "'" + st.getAddress() + "',"
                    + st.getMathPoint() + "," + st.getPhysicalPoint() + "," + st.getChemistryPoint() + "); ";
            database.InsertDatabase(str);
    }
    public void DeleteStudent(String jsonString,Socket socket) throws ParseException, IOException {
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject)jsonParser.parse(jsonString);
        Database database = new Database();
        String id = (String)jsonObject.get("Id");
        database.DeleteDatabase(id);
        DataOutputStream sendToClient= new DataOutputStream(socket.getOutputStream());// Tao output stream
        sendToClient.writeBytes("Deleted"+"\n");
    }
    public void UpdateDatabase(String jsonString) throws ParseException {
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject)jsonParser.parse(jsonString);
        Database database = new Database();
        String name = (String) jsonObject.get("name") ;
        String id = (String) jsonObject.get("Id");
        String address = (String) jsonObject.get("address");
        String sex = (String) jsonObject.get("sex");
        double mathPoint = Double.parseDouble(String.valueOf(jsonObject.get("mathPoint")));
        double physicalPoint = Double.parseDouble(String.valueOf(jsonObject.get("mathPoint")));
        double chemistryPoint = Double.parseDouble(String.valueOf(jsonObject.get("mathPoint")));
        Student st = new Student(id, name, sex, address, mathPoint, physicalPoint, chemistryPoint);
        String str = "UPDATE STUDENT SET Name = '" + st.getName() + "',Address = '" + st.getAddress()+"',Sex ='" + st.getSex() +
                "',MathPoint =" + st.getMathPoint() + ",PhysicalPoint = "
                +st.getPhysicalPoint()+",ChemistryPoint = " + st.getChemistryPoint() + " WHERE ID ="+st.getId()+ ";";
        database.UpdateDatabase(str);

    }
    public void SortByName()
    {

    }
}