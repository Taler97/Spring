import org.example.config.AppConfig;
import org.example.service.Role;
import org.example.service.rolesInter.GaoCuiLan;
import org.example.service.rolesInter.SunWuKong;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        SunWuKong sun = context.getBean("SunWuKongImp", SunWuKong.class);
        GaoCuiLan proxy = context.getBean("gaoCuiLanProxy", GaoCuiLan.class);
        System.out.println("名称：" + proxy.getName());
        proxy.ability();
        proxy.appearance();
        sun.fight(new Role() {
            @Override
            public String getName() {
                return "猪八戒";
            }

            @Override
            public void appearance() {
                System.out.println("当前样貌：猪八戒");
            }

            @Override
            public void ability() {
                System.out.println("猪八戒能力：无");
            }
        });
        proxy.catchPig();
        context.close();
    }
}