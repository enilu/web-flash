package cn.enilu.flash.service.workflow;

import cn.enilu.flash.bean.vo.workflow.ProcessDefinitionVo;
import cn.enilu.flash.utils.Lists;
import cn.enilu.flash.utils.factory.Page;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.zip.ZipInputStream;

/**
 * descript
 *
 * @Author enilu
 * @Date 2021/7/21 17:31
 * @Version 1.0
 */
@Service
public class ProcessDefinitionService {
    @Autowired
    private RepositoryService repositoryService;
    public Page<ProcessDefinitionVo> queryPage(Page<ProcessDefinitionVo> page){
        ProcessDefinitionQuery processDefinitionQuery = repositoryService
                .createProcessDefinitionQuery()
                .orderByProcessDefinitionId()
                .desc();
        List<ProcessDefinition> processDefinitions = processDefinitionQuery.listPage(page.getOffset(),page.getLimit());
        Long count = processDefinitionQuery.count();
        if(count>0) {
            page.setTotal(count.intValue());

        }
        List<ProcessDefinitionVo> processDefinitionVos = Lists.newArrayList();
        for(ProcessDefinition processDefinition:processDefinitions){
            ProcessDefinitionVo vo = new ProcessDefinitionVo(processDefinition);
            processDefinitionVos.add(vo);
        }
        page.setRecords(processDefinitionVos);

        return page;
    }
    public List<ProcessDefinitionVo> queryAll(){
        ProcessDefinitionQuery processDefinitionQuery = repositoryService
                .createProcessDefinitionQuery()
                .orderByProcessDefinitionId()
                .desc();
        List<ProcessDefinition> processDefinitions = processDefinitionQuery.list();
        List<ProcessDefinitionVo> processDefinitionVos = Lists.newArrayList();
        for(ProcessDefinition processDefinition:processDefinitions){
            ProcessDefinitionVo vo = new ProcessDefinitionVo(processDefinition);
            processDefinitionVos.add(vo);
        }
        return processDefinitionVos;
    }

    public void getProcessDefineXML(HttpServletResponse response, String deploymentId, String resourceName) throws IOException {
        InputStream inputStream = repositoryService.getResourceAsStream(deploymentId, resourceName);
        int count = inputStream.available();
        byte[] bytes = new byte[count];
        response.setContentType("text/xml");
        OutputStream outputStream = response.getOutputStream();
        while (inputStream.read(bytes) != -1) {
            outputStream.write(bytes);
        }
        inputStream.close();
    }


    public String upload(MultipartFile file) throws IOException {
        // 获取上传的文件名
        String fileName = file.getOriginalFilename();
        // 得到输入流（字节流）对象
        InputStream fileInputStream = file.getInputStream();
        // 文件的扩展名
        String extension = FilenameUtils.getExtension(fileName);

        if (extension.equals("zip")) {
            ZipInputStream zip = new ZipInputStream(fileInputStream);
            repositoryService.createDeployment()//初始化流程
                    .addZipInputStream(zip)
                    .deploy();
        } else {
            repositoryService.createDeployment()//初始化流程
                    .addInputStream(fileName, fileInputStream)
                    .deploy();
        }
        return fileName;
    }
    public String uploadAndDeploy(MultipartFile file) throws IOException {
        // 获取上传的文件名
        String fileName = file.getOriginalFilename();
        // 得到输入流（字节流）对象
        InputStream fileInputStream = file.getInputStream();
        // 文件的扩展名
        String extension = FilenameUtils.getExtension(fileName);

        if (extension.equals("zip")) {
            ZipInputStream zip = new ZipInputStream(fileInputStream);
            repositoryService.createDeployment()//初始化流程
                    .addZipInputStream(zip)
                    .deploy();
        } else {
            repositoryService.createDeployment()//初始化流程
                    .addInputStream(fileName, fileInputStream)
                    .deploy();
        }
        return fileName;
    }

    public void deploymentByString(String stringBPMN) {
        repositoryService.createDeployment()
                .addString("CreateWithBPMNJS.bpmn", stringBPMN)
                .deploy();
    }
}
