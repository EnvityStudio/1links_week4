package Entity;

/**
 * Created by Thuan Nguyen on 9/27/2016.
 */
public class Student {



    private  String name;
    private String address;
    private String Id,sex;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public double getChemistryPoint() {
        return chemistryPoint;
    }

    public void setChemistryPoint(double chemistryPoint) {
        this.chemistryPoint = chemistryPoint;
    }

    public double getPhysicalPoint() {
        return physicalPoint;
    }

    public void setPhysicalPoint(double physicalPoint) {
        this.physicalPoint = physicalPoint;
    }

    public double getMathPoint() {
        return mathPoint;
    }

    public void setMathPoint(double mathPoint) {
        this.mathPoint = mathPoint;
    }

    private double chemistryPoint;
    private  double physicalPoint;
    private double mathPoint;

    public  Student()
    {


            this(" "," "," "," ",0,0,0);

    }


    public Student(String Id,String name,String sex,String address,double mathPoint,double physicalPoint, double chemistryPoint)

    {

        this.sex = sex;
        this.name = name;
        this.address = address;
        this.Id = Id;
        this.chemistryPoint = chemistryPoint;
        this.physicalPoint= physicalPoint;
        this.mathPoint = mathPoint;
    }


}
