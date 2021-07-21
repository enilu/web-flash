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

//        if (!multipartFile.isEmpty()) {
//            String fileName = processDefinitionService.upload(multipartFile);
//            return AjaxResult.success("操作成功", fileName);
//
//        }
//        return AjaxResult.error("不允许上传空文件！");
        return Rets.success();
    }


    /**
     * 通过stringBPMN添加流程定义
     *
     * @param stringBPMN
     * @return
     */
    @BussinessLog(value = "上传流程流程定义")
    @PostMapping(value = "/通过stringBPMN添加流程定义")
    public Ret addDeploymentByString(@RequestParam("stringBPMN") String stringBPMN) {
//        processDefinitionService.addDeploymentByString(stringBPMN);
//        return AjaxResult.success();
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
