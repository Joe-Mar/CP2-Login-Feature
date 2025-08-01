/*
 * Programmer: A1101 GROUP 9 | Cocal, V., Franco, C., Jardeliza, L.
 * Date: March 2025
 * Project: MotorPH Payroll System
 */
package motorph;

public class Employee {

    private String empNo;
    private String lastName;
    private String firstName;
    private String birthday;
    private String address;
    private String phoneNo;
    private String sssNo;
    private String philHealthNo;
    private String tinNo;
    private String pagibigNo;
    private String status;
    private String position;
    private String supervisor;
    private double basicSalary;
    private double riceSubsidy;
    private double phoneAllowance;
    private double clothingAllowance;
    private double semiMonthlyRate;
    private double hourlyRate;

    /* 
     * OOP PRINCIPLE: ENCAPSULATION
     * The private access modifier ensures that these attributes cannot be accessed directly from outside the class.
     */

    public Employee(Employee other) {
        this.empNo = other.empNo;
        this.firstName = other.firstName;
        this.lastName = other.lastName;
        this.birthday = other.birthday;
        this.address = other.address;
        this.phoneNo = other.phoneNo;
        this.sssNo = other.sssNo;
        this.philHealthNo = other.philHealthNo;
        this.tinNo = other.tinNo;
        this.pagibigNo = other.pagibigNo;
        this.status = other.status;
        this.position = other.position;
        this.supervisor = other.supervisor;
        this.basicSalary = other.basicSalary;
        this.riceSubsidy = other.riceSubsidy;
        this.phoneAllowance = other.phoneAllowance;
        this.clothingAllowance = other.clothingAllowance;
        this.semiMonthlyRate = other.semiMonthlyRate;
        this.hourlyRate = other.hourlyRate;
    }
    
    public Employee() {
        
    }

    // GETTER & SETTER METHODS
    public String getEmpNo() {
        return empNo;
    }

    public void setEmpNo(String empNo) {
        this.empNo = empNo;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getSssNo() {
        return sssNo;
    }

    public void setSssNo(String sssNo) {
        this.sssNo = sssNo;
    }

    public String getPhilHealthNo() {
        return philHealthNo;
    }

    public void setPhilHealthNo(String philHealthNo) {
        this.philHealthNo = philHealthNo;
    }

    public String getTinNo() {
        return tinNo;
    }

    public void setTinNo(String tinNo) {
        this.tinNo = tinNo;
    }

    public String getPagibigNo() {
        return pagibigNo;
    }

    public void setPagibigNo(String pagibigNo) {
        this.pagibigNo = pagibigNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    public double getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(double basicSalary) {
        this.basicSalary = basicSalary;
    }

    public double getRiceSubsidy() {
        return riceSubsidy;
    }

    public void setRiceSubsidy(double riceSubsidy) {
        this.riceSubsidy = riceSubsidy;
    }

    public double getPhoneAllowance() {
        return phoneAllowance;
    }

    public void setPhoneAllowance(double phoneAllowance) {
        this.phoneAllowance = phoneAllowance;
    }

    public double getClothingAllowance() {
        return clothingAllowance;
    }

    public void setClothingAllowance(double clothingAllowance) {
        this.clothingAllowance = clothingAllowance;
    }

    public double getSemiMonthlyRate() {
        return semiMonthlyRate;
    }

    public void setSemiMonthlyRate(double semiMonthlyRate) {
        this.semiMonthlyRate = semiMonthlyRate;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }
    /* 
     * OOP PRINCIPLE: ENCAPSULATION
     * Provides controlled access to private variables without exposing it directly.
     */
}
