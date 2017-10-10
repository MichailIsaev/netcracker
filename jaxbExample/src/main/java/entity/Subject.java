package entity;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import java.util.regex.Pattern;

/**
 * Created by michail on 17.09.17.
 */
@XmlType(propOrder = {"title", "mark"})
public class Subject {
    private int mark;
    private String title;

    public String getTitle() {
        return title;
    }

    @XmlAttribute(required = true)
    public void setTitle(String title) {
        this.title = title;
    }

    public int getMark() {
        return mark;
    }

    @XmlAttribute(required = true)
    public void setMark(int mark) throws NumberFormatException {
        if (!Pattern.compile("^[1-5]$").matcher(String.valueOf(mark)).matches())
            throw new NumberFormatException("Wrong mark value.");
        this.mark = mark;
    }


}
