import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {


    @PostMapping("/valid")
    public String valid(@Valid @RequestBody ValidList<User> userList) {
        System.out.println(JSON.toJSONString(userList));
        testValid(userList);
        return "send success";
    }

    public void testValid(List<User> users) {
        System.out.println(JSON.toJSONString(users));
    }
}
