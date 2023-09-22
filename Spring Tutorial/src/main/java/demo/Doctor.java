package demo;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/*Using annotation -> just mark spring component*/
@Component


/*defining scope ,
Now as we declared scope to be prototype we will get a new object everytime,
also don't forget to make it a compnent as well [@Component] */
@Scope(scopeName = "prototype")


/*Bean Life Cycle -> BeanNameAware*/
public class Doctor implements Staff, BeanNameAware {

//   public void assist(){
//        System.out.println("Doctor is Assisting");
//    }

    /*Defining properties, injecting properties using spring.xml */
//    private String qualification;
//    public String getQualification() {
//        return qualification;
//    }
//
//    public void setQualification(String qualification) {
//        this.qualification = qualification;
//    }

//----------------------------------------------------------------------
    /*Injecting constructor*/
//    public Doctor(String qualification) {
//        this.qualification = qualification;
//    }
//---------------------------------------------------------------------------
    /*Injecting object*/
//    private Nurse nurse;

//    public Nurse getNurse() {
//        return nurse;
//    }

//    public void setNurse(Nurse nurse) {
//        this.nurse = nurse;
//    }

//---------------------------------------------------------
    /*Using Annotation*/
    public void assist(){
        System.out.println("Doctor is Assisting");
    }
//-----------------------------------------------------

    /*SingleTon scope*/
    private String qualification;

    @Override
    public String toString() {
        return "Doctor{" +
                "qualification='" + qualification + '\'' +
                '}';
    }
    public String getQualification(String qualification) {
        return qualification;
    }
    public void setQualification(String qualification) {
        this.qualification = qualification;
    }
//    -----------------------------------------------------------
    /*Bean LifeCylcle*/
    @Override
    public void setBeanName(String s) {
        System.out.println("Set Bean Name Method is called");
    }

    /*This Method is called at the time of creation of object,
    Now annotate with post construct*/
    @PostConstruct
    public void postConstruct(){
        System.out.println("Post construct method is called");
    }
}
