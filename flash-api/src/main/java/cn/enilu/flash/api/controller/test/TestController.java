package cn.enilu.flash.api.controller.test;

import cn.enilu.flash.service.system.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试工具
 * 启动api服务后，通过swagger地址：http://localhost:8082/swagger-ui.html#!/test-controller/testUsingGET 来做一些测试
 * @Author enilu
 * @Date 2021/6/2 15:23
 * @Version 1.0
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private TestService testService;
    @GetMapping
    public void test(@RequestParam("key") String key){
        if("lock".equals(key)){
            testService.lock();
        }
        if(key.startsWith("trans")){
            if(key.contains("yes")){
                testService.userTrans();
            }
            if(key.contains("no")){
                testService.noTrans();
            }

        }
    }
}
