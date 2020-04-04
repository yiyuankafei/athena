package athena.cipher.jasypt.application.test;

import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DecodeTest {
 
    @Autowired
    private StringEncryptor stringEncryptor;
 
    @Test
    public void test(){
 
        //String encrypt = stringEncryptor.encrypt("root");
    	String encrypt = "EJXFr6s9AccDk27rWx9GbHwIvBd9cviKN";
        System.out.println(encrypt);
        System.out.println(stringEncryptor.decrypt(encrypt));
 
    }
 
}
