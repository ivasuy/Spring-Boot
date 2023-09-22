package demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/*Java Configeration*/
@Configuration
@ComponentScan(basePackages = "demo")
public class BeanConfig {
    /*Defining Bean rather than component scan,
    Thus we can remove component annotation from Doctor class
    because we are defining bean our self in Configuration

    -> works same as before whenever context try to load BeanConfig,
     here is a method defined as a bean
     doctor will be created by default constructor, thus available in container*/

    /*Thus we can say whatever we can define in xml we can also define it here as well*/
//    @Bean
//    public Doctor doctor(){
    /*returns new object of doctor*/
//        return new Doctor();
//    }
}
