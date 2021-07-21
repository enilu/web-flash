package cn.enilu.flash.controller;

import cn.enilu.flash.api.controller.BaseController;
import cn.enilu.flash.bean.constant.factory.PageFactory;
import cn.enilu.flash.bean.vo.front.Ret;
import cn.enilu.flash.bean.vo.front.Rets;
import cn.enilu.flash.bean.vo.workflow.ProcessDefinitionVo;
import cn.enilu.flash.service.workflow.ProcessDefinitionService;
import cn.enilu.flash.utils.factory.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 流程定义controller
 *
 * @Author enilu
 * @Date 2021/7/21 17:27
 * @Version 1.0
 */
@RestController("processDefinition")
public class ProcessDefinitionController extends BaseController {
    @Autowired
    private ProcessDefinitionService processDefinitionService;
    @GetMapping(value = "/list")
    public Ret list() {
        Page<ProcessDefinitionVo> page = new PageFactory<ProcessDefinitionVo>().defaultPage();
        page = processDefinitionService.queryPage(page);
        return Rets.success(page);
    }

    @PostMapping(value = "/uploadAndDeploy")
    public Ret uploadStreamAndDeployment(@RequestParam("file") MultipartFile file)     {
         return Rets.success();

    }

}
