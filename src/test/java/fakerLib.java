import com.github.javafaker.Faker;
import org.testng.annotations.Test;

public class fakerLib {
    @Test
    void fakerLibrary(){
        Faker faker =new Faker();
        String fullName=faker.name().fullName();
        String firstName=faker.name().firstName();
        String lastName=faker.name().lastName();
        String userName=faker.name().username();
        String passWord=faker.internet().password();
        String phoneNumber=faker.phoneNumber().cellPhone();
        String email=faker.internet().emailAddress();
        System.out.println("fullName is: "+ fullName);
        System.out.println("firstName is: "+ firstName);
        System.out.println("lastName is: "+ lastName);
        System.out.println("userName is: "+ userName);
        System.out.println("passWord is: "+ passWord);
        System.out.println("phoneNumber is: "+ phoneNumber);
        System.out.println("email is: "+ email);
    }
}
