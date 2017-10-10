package entity;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by michail on 17.09.17.
 */

@XmlType(propOrder = {"subject", "average"})
public class Student {
    private String groupNumber;
    private String lastName;
    private String firstName;
    private String average;
    private Subject[] subject;

    public Subject[] getSubject() {
        return subject;
    }

    public void setSubject(Subject[] subject) {
        this.subject = subject;
    }

    public String getAverage() {
        return average;
    }

    public void setAverage(String average) {
        this.average = average;
    }

    public String getFirstName() {
        return firstName;
    }

    @XmlAttribute(name = "firstname", required = true)
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @XmlAttribute(name = "lastname", required = true)
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getGroupNumber() {
        return groupNumber;
    }

    @XmlAttribute(name = "groupnumber", required = true)
    public void setGroupNumber(String groupNumber) {
        this.groupNumber = groupNumber;
    }
}
