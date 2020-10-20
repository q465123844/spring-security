import com.leo.databaselogin.auth.utils.ApplicationContextUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Mytest.class)
public class Mytest {

    @Resource
    PasswordEncoder passwordEncoder;
    @Test
    public void contextLoads(){
        ApplicationContext application = ApplicationContextUtil.getApplication();
        PasswordEncoder bean = application.getBean(PasswordEncoder.class);
        System.out.println(bean);
    }
}
