package Student_Model;

import Entity.Student;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Created by Thuan Nguyen on 10/23/2016.
 */
public class StudentModel {
    public void ParseJson(String json) throws ParseException {
        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray = (JSONArray)jsonParser.parse(json);
        for (int i =0;i<jsonArray.size();i++)
        {
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
            String name = (String) jsonObject.get("name");
            String id = (String) jsonObject.get("Id");
            String address = (String )jsonObject.get("address");
            String sex = (String) jsonObject.get("sex");
            double mathPoint = Double.parseDouble(String.valueOf(jsonObject.get("mathPoint")));
            double physicalPoint = Double.parseDouble(String.valueOf(jsonObject.get("mathPoint")));
            double chemistryPoint = Double.parseDouble(String.valueOf(jsonObject.get("mathPoint")));


            Student st = new Student(id,name,sex,address,mathPoint,physicalPoint,chemistryPoint);
            System.out.printf(" %-5s | %-10s  | %-35s  | %-10s | %-14s  | %-10.2f  | %-10.2f  | %10.2f ",i+1, st.getId(), st.getName(),
                    st.getSex(), st.getAddress(), st.getMathPoint(),
                    st.getPhysicalPoint(), st.getChemistryPoint());
            System.out.println();
        }
    }
}
