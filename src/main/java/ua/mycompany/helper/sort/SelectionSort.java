package ua.mycompany.helper.sort;

import ua.mycompany.domain.Student;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public final class SelectionSort {

    private SelectionSort() {

    }

    public static ArrayList<Student> sort(ArrayList<Student> students) {

        for (int left = 0; left < students.size(); left++) {
            int minInd = left;
            for (int i = left; i < students.size(); i++) {
                if (students.get(i).compareTo(students.get(minInd)) < 0) {
                    minInd = i;
                }
            }
            Utility.swap(students, left, minInd);
        }
        return students;
    }
}
