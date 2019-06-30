package cn.enilu.flash.api.controller.system;

import cn.enilu.flash.api.controller.BaseController;
import cn.enilu.flash.bean.constant.factory.PageFactory;
import cn.enilu.flash.bean.constant.state.BizLogType;
import cn.enilu.flash.bean.entity.system.OperationLog;
import cn.enilu.flash.bean.vo.front.Rets;
import cn.enilu.flash.bean.vo.query.SearchFilter;
import cn.enilu.flash.service.system.OperationLogService;
import cn.enilu.flash.utils.BeanUtil;
import cn.enilu.flash.utils.DateUtil;
import cn.enilu.flash.utils.StringUtils;
import cn.enilu.flash.utils.factory.Page;
import cn.enilu.flash.warpper.LogWarpper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * LogController
 *
 * @author enilu
 * @version 2018/10/5 0005
 */
@RestController
@RequestMapping("/log")
public class LogController extends BaseController {
    @Autowired
    private OperationLogService operationLogService;
    /**
     * 查询操作日志列表
     */
    @RequestMapping("/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) String beginTime,
                       @RequestParam(required = false) String endTime,
                       @RequestParam(required = false) String logName,
                       @RequestParam(required = false) Integer logType) {
        Page<OperationLog> page = new PageFactory<OperationLog>().defaultPage();
        if(StringUtils.isNotEmpty(beginTime)){
            page.addFilter(SearchFilter.build("createTime", SearchFilter.Operator.GTE, DateUtil.parseDate(beginTime)));
        }
        if(StringUtils.isNotEmpty(endTime)){
            page.addFilter(SearchFilter.build("createTime", SearchFilter.Operator.LTE, DateUtil.parseDate(endTime)));
        }
        if(StringUtils.isNotEmpty(logName)){
            page.addFilter(SearchFilter.build("logname", SearchFilter.Operator.LIKE, logName));
        }
        if(logType!=null){
            page.addFilter(SearchFilter.build("logtype", SearchFilter.Operator.EQ,  BizLogType.valueOf(logType)));
        }
        page   = operationLogService.queryPage(page);
        page.setRecords((List<OperationLog>) new LogWarpper(BeanUtil.objectsToMaps(page.getRecords())).warp());
        return Rets.success(page);
    }


    /**
     * 清空日志
     */
    @RequestMapping(method = RequestMethod.DELETE)
    public Object delLog() {
        operationLogService.clear();
        return Rets.success();
    }
}
