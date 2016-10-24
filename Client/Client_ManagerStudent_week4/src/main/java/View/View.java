package View;

import Entity.Student;
import Student_Model.StudentModel;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Thuan Nguyen on 10/21/2016.
 */
public class View {
    public static void MenuMain()
    {
        System.out.println("QUẢN LÝ SINH VIÊN");
        System.out.println("1.Thêm mới sinh viên");
        System.out.println("2.Xem danh sách");
        System.out.println("3.Sắp xếp ");
        System.out.println("4.Tìm kiếm");
        System.out.println("5.Thống kê");
        System.out.println("6.Thoát");
    }

    public void MenuViewStudent()
    {
        System.out.println("1.Sửa thông tin sinh viên");
        System.out.println("2.Xóa sinh viên");
        System.out.println("3.Quay lại");
    }

    public void MenuSort()
    {
        System.out.println("1.Sắp xếp theo tên");
        System.out.println("2.Sắp xếp theo ID");
        System.out.println("3.Sắp xếp theo tổng điểm");
        System.out.println("4.Quay lại");
    }

    public void SelectMenuMain() throws Exception {
        /*MenuMain();
        System.out.print("Bạn chọn: ");*/
        Scanner input = new Scanner(System.in);
        int choice = 0;
        //choice=input.nextInt();
        do {
            MenuMain();
            System.out.print("Bạn chọn: ");
            choice = input.nextInt();
            switch (choice)
            {
                case 1: AddStudent(); break;
                case 2: ViewStudent(); break;
                case 3: SortStudent();break;
                case 4: break;
                case 5: StatisticalStudent();break;
                case 6: break;
                //default:
                // System.out.println("Bạn phải nhập số từ 1-->7"); break;
            }
        }while(choice !=6);
    }
    public void AddStudent () throws IOException {
        Scanner input = new Scanner(System.in);
        System.out.println("Nhập id: ");
        String id = input.nextLine();
        System.out.println("Nhập tên: ");
        String name = input.nextLine();
        System.out.println("Giới tính: ");
        String sex = input.nextLine();
        System.out.println("Địa chỉ: ");
        String address = input.nextLine();
        System.out.println("Điểm toán: ");
        double mathPoint = input.nextDouble();
        System.out.println("Điểm lý: ");
        double physicalPoint=input.nextDouble();
        System.out.println("Điểm hóa: ");
        double chemistry = input.nextDouble();
        Student student = new Student(id, name,sex,address,mathPoint,physicalPoint,chemistry);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("{");
        stringBuffer.append("\"Id\":\"" + student.getId() + "\"");
        stringBuffer.append(",");
        stringBuffer.append("\"name\":\"" +student.getName()+"\"");
        stringBuffer.append(",");
        stringBuffer.append("\"sex\":\"" +student.getSex()+"\"");
        stringBuffer.append(",");
        stringBuffer.append("\"address\":\"" +student.getAddress()+"\"");
        stringBuffer.append(",");
        stringBuffer.append("\"mathPoint\":\"" +student.getMathPoint()+"\"");
        stringBuffer.append(",");
        stringBuffer.append("\"physicalPoint\":\"" +student.getPhysicalPoint()+"\"");
        stringBuffer.append(",");
        stringBuffer.append("\"chemistryPoint\":\"" +student.getChemistryPoint()+"\"");
        stringBuffer.append(",");
        stringBuffer.append("\"request\":\"" +"1" +"\"");
        stringBuffer.append(",");
        stringBuffer.append("}");
      //  return stringBuffer.toString();
        Socket clientSocket= new Socket("127.0.0.1",9999);// Tao clinent socket de ket noi toi server
        DataOutputStream sendToServer= new DataOutputStream(clientSocket.getOutputStream());// Tao output stream ket noi toi socket
        sendToServer.writeBytes(stringBuffer.toString() + "\n");
    }
    public void ViewStudent() throws Exception {
        String json = "{\"request\":\"2\"}";
        Socket clientSocket= new Socket("127.0.0.1",9999);// Tao clinent socket de ket noi toi server
        DataOutputStream sendToServer= new DataOutputStream(clientSocket.getOutputStream());// Tao output stream ket noi toi socket
        sendToServer.writeBytes(json+ "\n");
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String result = inFromServer.readLine();
        StudentModel studentModel = new StudentModel();
        studentModel.ParseJson(result);
        SelectMenuViewStudent();


    }
    public void DeleteStudent() throws IOException {
        Scanner input = new Scanner(System.in);
        System.out.println("Nhập ID mà bạn muốn xóa");
        String id = input.nextLine();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("{");
        stringBuffer.append("\"request\":\"" +"22" + "\"");
        stringBuffer.append(",");
        stringBuffer.append("\"Id\":\"" + id + "\"");
        stringBuffer.append(",");
        stringBuffer.append("}");
        Socket clientSocket= new Socket("127.0.0.1",9999);// Tao clinent socket de ket noi toi server
        DataOutputStream sendToServer= new DataOutputStream(clientSocket.getOutputStream());// Tao output stream ket noi toi socket
        sendToServer.writeBytes(stringBuffer.toString() + "\n");
    }
    public void EditStudent() throws IOException {
        Scanner input = new Scanner(System.in);
        System.out.println("Nhập id: ");
        String id = input.nextLine();
        System.out.println("Nhập tên: ");
        String name = input.nextLine();
        System.out.println("Giới tính: ");
        String sex = input.nextLine();
        System.out.println("Địa chỉ: ");
        String address = input.nextLine();
        System.out.println("Điểm toán: ");
        double mathPoint = input.nextDouble();
        System.out.println("Điểm lý: ");
        double physicalPoint=input.nextDouble();
        System.out.println("Điểm hóa: ");
        double chemistry = input.nextDouble();
        Student student = new Student(id, name,sex,address,mathPoint,physicalPoint,chemistry);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("{");
        stringBuffer.append("\"Id\":\"" + student.getId() + "\"");
        stringBuffer.append(",");
        stringBuffer.append("\"name\":\"" +student.getName()+"\"");
        stringBuffer.append(",");
        stringBuffer.append("\"sex\":\"" +student.getSex()+"\"");
        stringBuffer.append(",");
        stringBuffer.append("\"address\":\"" +student.getAddress()+"\"");
        stringBuffer.append(",");
        stringBuffer.append("\"mathPoint\":\"" +student.getMathPoint()+"\"");
        stringBuffer.append(",");
        stringBuffer.append("\"physicalPoint\":\"" +student.getPhysicalPoint()+"\"");
        stringBuffer.append(",");
        stringBuffer.append("\"chemistryPoint\":\"" +student.getChemistryPoint()+"\"");
        stringBuffer.append(",");
        stringBuffer.append("\"request\":\"" +"21" +"\"");
        stringBuffer.append(",");
        stringBuffer.append("}");
        Socket clientSocket= new Socket("127.0.0.1",9999);// Tao clinent socket de ket noi toi server
        DataOutputStream sendToServer= new DataOutputStream(clientSocket.getOutputStream());// Tao output stream ket noi toi socket
        sendToServer.writeBytes(stringBuffer.toString() + "\n");
    }
    public void StatisticalStudent(){}
    public void SelectMenuViewStudent() throws Exception {

        Scanner input = new Scanner(System.in);
        int choice;
        do {
            MenuViewStudent();
            System.out.print("Bạn chọn: ");
            choice = input.nextInt();
            switch (choice)
            {
                case 1: EditStudent(); break;
                case 2: DeleteStudent(); break;
                case 3: break;
                //  default:
                //    System.out.println("Nhập lại");
            }
        }while (choice !=3 );
    }


    public void SortStudent() throws Exception {
        MenuSort();
        System.out.print("Bạn chọn: ");
        Scanner input = new Scanner(System.in);
        int choice;
        do {
            choice = input.nextInt();
            switch (choice )
            {
                case 1: SortByName(); break;
                case 2: SortByID(); break;
                case 3: SortByToTalPoint(); break;
                case 4: break;
                //   default:
                //     System.out.println("Nhập lại");
            }
        }while (choice !=4);
    }
    public void SortByName(){
        String json = "{\"request\":\"1\"}";

    }
    public void SortByID(){}
    public void SortByToTalPoint(){}



    public  void SearchByName() throws IOException {
        System.out.println("Nhập tên tìm kiếm");
        Scanner input = new Scanner(System.in);
        String name = input.nextLine();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("{");
        stringBuffer.append("\"request\":\"" +"22" + "\"");
        stringBuffer.append(",");
        stringBuffer.append("\"name\":\"" + name+ "\"");
        stringBuffer.append(",");
        stringBuffer.append("}");
        Socket clientSocket= new Socket("127.0.0.1",9999);// Tao clinent socket de ket noi toi server
        DataOutputStream sendToServer= new DataOutputStream(clientSocket.getOutputStream());// Tao output stream ket noi toi socket
        sendToServer.writeBytes(stringBuffer.toString() + "\n");
    }
    public void SearchByid(){}
    public void SearchByPoint(){}
}
