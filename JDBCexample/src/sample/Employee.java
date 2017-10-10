package sample;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.EmptyStackException;

/**
 * Created by michail on 23.09.17.
 */
public class Employee {

    private StringProperty deptName;
    private StringProperty empName;
    private StringProperty empJob;
    private IntegerProperty empMgr;
    private IntegerProperty empSal;
    private IntegerProperty empComm;
    private StringProperty locate;
    private IntegerProperty grade;



    public Employee(String deptName, String empName, String empJob, int empMgr, int empSal, int empComm, String locate, int grade) {
        this.deptName = new SimpleStringProperty(deptName);
        this.empName = new SimpleStringProperty(empName);
        this.empJob = new SimpleStringProperty(empJob);
        this.empMgr = new SimpleIntegerProperty(empMgr);
        this.empSal = new SimpleIntegerProperty(empSal);
        this.empComm = new SimpleIntegerProperty(empComm);
        this.locate = new SimpleStringProperty(locate);
        this.grade = new SimpleIntegerProperty(grade);
    }

    public String getDeptName() {
        return deptName.get();
    }

    public void setDeptName(String deptName) {
        this.deptName.set(deptName);
    }

    public StringProperty deptNameProperty() {
        return deptName;
    }

    public String getEmpName() {
        return empName.get();
    }

    public void setEmpName(String empName) {
        this.empName.set(empName);
    }

    public StringProperty empNameProperty() {
        return empName;
    }

    public String getLocate() {
        return locate.get();
    }

    public void setLocate(String locate) {
        this.locate.set(locate);
    }

    public StringProperty locateProperty() {
        return locate;
    }

    public String getEmpJob() {
        return empJob.get();
    }

    public void setEmpJob(String empJob) {
        this.empJob.set(empJob);
    }

    public StringProperty empJobProperty() {
        return empJob;
    }

    public int getEmpManager() {
        return empMgr.get();
    }

    public void setEmpManager(int empMgr) {
        this.empMgr.set(empMgr);
    }

    public IntegerProperty empMgrProperty() {
        return empMgr;
    }

    public int getEmpSal() {
        return empSal.get();
    }

    public void setEmpSal(int empSal) {
        this.empSal.set(empSal);
    }

    public IntegerProperty empSalProperty() {
        return empSal;
    }

    public int getEmpComm() {
        return empComm.get();
    }

    public void setEmpComm(int empComm) {
        this.empComm.set(empComm);
    }

    public IntegerProperty empCommProperty() {
        return empComm;
    }

    public int getGrade() {
        return grade.get();
    }

    public void setGrade(int grade) {
        this.grade.set(grade);
    }

    public IntegerProperty gradeProperty() {
        return grade;
    }

}
