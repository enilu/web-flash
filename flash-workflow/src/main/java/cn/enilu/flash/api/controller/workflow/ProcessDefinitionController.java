package cn.enilu.flash.api.controller.workflow;

import cn.enilu.flash.api.controller.BaseController;
import cn.enilu.flash.bean.constant.factory.PageFactory;
import cn.enilu.flash.bean.core.BussinessLog;
import cn.enilu.flash.bean.vo.front.Ret;
import cn.enilu.flash.bean.vo.front.Rets;
import cn.enilu.flash.bean.vo.workflow.ProcessDefinitionVo;
import cn.enilu.flash.service.workflow.ProcessDefinitionService;
import cn.enilu.flash.utils.factory.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 流程定义controller
 *
 * @Author enilu
 * @Date 2021/7/21 17:27
 * @Version 1.0
 */
@RestController
@RequestMapping("/workflow/process/definition")
public class ProcessDefinitionController extends BaseController {
    @Autowired
    private ProcessDefinitionService processDefinitionService;

    @GetMapping(value = "/list")
    public Ret list() {
        Page<ProcessDefinitionVo> page = new PageFactory<ProcessDefinitionVo>().defaultPage();
        page = processDefinitionService.queryPage(page);
        return Rets.success(page);
    }
    @GetMapping(value = "/queryAll")
    public Ret queryAll() {
        List<ProcessDefinitionVo> list = processDefinitionService.queryAll();
        return Rets.success(list);
    }

    /**
     * 上传流程流程定义
     *
     * @param multipartFile
     * @return
     * @throws IOException
     */

    @BussinessLog(value = "上传流程流程定义")
    @PostMapping(value = "/upload")
    public Ret upload(@RequestParam("processFile") MultipartFile multipartFile) throws IOException {

        if (!multipartFile.isEmpty()) {
            String fileName = processDefinitionService.uploadAndDeploy(multipartFile);
            return Rets.success(fileName);
        }
        return Rets.failure("上传文件为空");
    }


    /**
     * 通过stringBPMN添加流程定义
     *
     * @param stringBPMN
     * @return
     */
    @BussinessLog(value = "通过stringBPMN添加流程定义")
    @PostMapping(value = "/addDeploymentByString")
    public Ret addDeploymentByString(@RequestParam("stringBPMN") String stringBPMN) {
        processDefinitionService.deploymentByString(stringBPMN);
        return Rets.success();

    }


    /**
     * 获取流程定义XML
     *
     * @param response
     * @param deploymentId
     * @param resourceName
     */
    @GetMapping(value = "/getDefinitionXML")
    public void getProcessDefineXML(HttpServletResponse response,
                                    @RequestParam("deploymentId") String deploymentId,
                                    @RequestParam("resourceName") String resourceName) throws IOException {

        processDefinitionService.getProcessDefineXML(response, deploymentId, resourceName);
    }


}
