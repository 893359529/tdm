import com.cjdx.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class test {
    @Autowired
    private UserService userService;

    @Test
    public void queryUserByName(){
        System.out.println(userService.queryUserByName("test"));
    }
}
