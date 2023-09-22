package demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        /*
        Lot of ways to get spring context but here we use XML configuration.
        We created context from spring.xml, so whatever beans are defined in spring.xml
        we can get it from context.
 */
//        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
//
//        Doctor doc = context.getBean(Doctor.class);
//        Nurse nur = context.getBean(Nurse.class);
//---------------------------------------------------------------------------------------
        /* Getting value from id instead of class*/
//        Nurse nur = (Nurse) context.getBean("nurse");
//        doc.assist();
//        nur.assist();
//---------------------------------------------------------------------------------------
        /*Now getting values using staff interface*/
//        Staff staff = context.getBean(Doctor.class);
//        Staff staff1 = context.getBean(Nurse.class);
//        staff.assist();
//        staff1.assist();
        /*We Decoupled the entire application we are not creating or injecting objects,
        we are getting everything from spring container*/
//---------------------------------------------------------------------------------------
        /*fetching properties of doctor class from xml file*/
//        Doctor doc = context.getBean(Doctor.class);
        /*we injected propertie using setter injection*/
//        System.out.println(doc.getQualification());

        /*Constructor Injection*/
//        System.out.println(doc.getQualification());
//---------------------------------------------------------------------------------------
        /*Using annotaion*/
//        Doctor doctor = context.getBean(Doctor.class);
//        doctor.assist();
//---------------------------------------------------------------------------------------

        /*Using Java Configeration*/
        ApplicationContext context = new AnnotationConfigApplicationContext(BeanConfig.class);
//        Doctor doctor = context.getBean(Doctor.class);
//        doctor.assist();
//        doctor.setQualification("MBBS");
//---------------------------------------------------------------------------------------

        /*Singleton Scope*/
//        Doctor doctor = context.getBean(Doctor.class);
//        doctor.assist();
//        doctor.setQualification("MBBS");
//        System.out.println(doctor);
        /*over here we get the same object back,
        because has spring created only one object for the entire application

        But after declaring the scope to prototype we get two different objects*/
//        Doctor doctor1 = context.getBean(Doctor.class);
//        System.out.println(doctor1);


        /*Bean Life Cycle
        we can see before creating the object setBean Method was called of BeanNameAware Interface,
        This way we can implement all aware interface which are available to
        modify the behaviour of the Life Cycle*/
//        Doctor doctor = context.getBean(Doctor.class);
//        doctor.assist();
        /*Another thing we can do is use the annotations,
        Suppose once the object is created at that particular time if we want to do anything*/
        Doctor doctor = context.getBean(Doctor.class);
        doctor.assist();
    }

}
