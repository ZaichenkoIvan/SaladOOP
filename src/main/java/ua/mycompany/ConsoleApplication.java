package ua.mycompany;

import ua.mycompany.init.Menu;
import ua.mycompany.view.StudentViewInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ConsoleApplication {
    private Menu menu;
    private StudentViewInfo studentViewInfo;

    @Autowired
    public ConsoleApplication(Menu menu, StudentViewInfo studentViewInfo) {
        this.menu = menu;
        this.studentViewInfo = studentViewInfo;
    }

    public static void main(String[] args) {

        ApplicationContext ctx =
                new AnnotationConfigApplicationContext("ua.mycompany");
        ConsoleApplication main = ctx.getBean(ConsoleApplication.class);
        main.menu.run();
        main.studentViewInfo.run();
    }
}
