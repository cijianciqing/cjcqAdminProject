package com.ns.cjcq;

import com.ns.cjcq.security.dao.CJUserRepository;
import com.ns.cjcq.security.domain.CJUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CjcqApplicationTests {
    @Autowired
    CJUserRepository cjUserRepository;

    @Test
   public void contextLoads() {
        CJUser cjUser = new CJUser();
        cjUser.setUsername("account");
        cjUser.setShowname("用户");
        cjUser.setEmail("email");
        cjUser.setPassword("password");
        cjUser.setTelephoneNo("tel");
        cjUserRepository.save(cjUser);
    }

    @Test
    @Rollback(false)
    public void  test01(){
        List<CJUser> cjUsers =  new ArrayList<>();
        for(int i=1; i<100; i++){
            CJUser cjUser = new CJUser();
            cjUser.setId((long) i);
            cjUser.setUsername("account"+i);
            cjUser.setShowname("用户"+i);
            cjUser.setEmail("email"+i);
            cjUser.setPassword("password"+i);
            cjUser.setTelephoneNo("tel"+i);
            cjUsers.add(cjUser);
        }
        cjUserRepository.saveAll(cjUsers);
    }

}
