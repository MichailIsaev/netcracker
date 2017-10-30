import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;
import entity.Group;
import entity.Student;
import entity.Subject;

import javax.xml.bind.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        double sum = 0;
        final String PATTERN = "^(\\/\\w+)*\\w+\\.{1}xml$";
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String inputFilePath, outputFilePath;
        try {
            while (true) {
                System.out.println("Введите путь входного файла\n>");
                inputFilePath = bufferedReader.readLine();
                File inFile = new File(inputFilePath);
                if (!inFile.isFile() || !inFile.exists() || !Pattern.compile(PATTERN).matcher(inputFilePath).matches()) {
                    System.out.println("Ложный формат пути к файлу!");
                    continue;
                }
                break;
            }

            while (true) {
                System.out.println("Введите путь выходного файла\n>");
                outputFilePath = bufferedReader.readLine();
                if (!Pattern.compile(PATTERN).matcher(inputFilePath).matches()) {
                    System.out.println("Ложный формат пути к файлу!");
                    continue;
                }
                break;
            }

            Parser parser = new JaxbParser();
            Group group = (Group) parser.getObject(new File(inputFilePath), Group.class);
            for (Student student : group.getStudent()) {
                if (!Objects.isNull(student.getSubject())) {
                    for (Subject subject : student.getSubject()) {
                        sum += subject.getMark();
                    }
                    if (student.getAverage() == null || Double.valueOf(student.getAverage()) != sum / student.getSubject().length) {
                        student.setAverage(String.valueOf(sum / student.getSubject().length));

                    }
                    sum = 0;
                } else {
                    if (student.getAverage() != null) {
                        student.setAverage("0");
                    }
                    continue;
                }
            }

            parser.saveObject(new File(outputFilePath), group);

        } catch (IOException | JAXBException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private static void averageCorrect() {

    }

}


