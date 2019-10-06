package ua.mycompany;

import ua.mycompany.init.Menu;
import ua.mycompany.view.CustomerViewInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ConsoleApplication {
    private Menu menu;
    private CustomerViewInfo customerViewInfo;

    @Autowired
    public ConsoleApplication(Menu menu, CustomerViewInfo customerViewInfo) {
        this.menu = menu;
        this.customerViewInfo = customerViewInfo;
    }

    public static void main(String[] args) {

        ApplicationContext ctx =
                new AnnotationConfigApplicationContext("ua.mycompany");
        ConsoleApplication main = ctx.getBean(ConsoleApplication.class);
        main.menu.run();
        main.customerViewInfo.run();
    }
}
