package cn.enilu.flash.api.controller.system;

import cn.enilu.flash.api.controller.BaseController;
import cn.enilu.flash.bean.constant.factory.PageFactory;
import cn.enilu.flash.bean.entity.system.LoginLog;
import cn.enilu.flash.bean.vo.front.Rets;
import cn.enilu.flash.bean.vo.query.SearchFilter;
import cn.enilu.flash.service.system.LoginLogService;
import cn.enilu.flash.utils.BeanUtil;
import cn.enilu.flash.utils.DateUtil;
import cn.enilu.flash.utils.StringUtils;
import cn.enilu.flash.utils.factory.Page;
import cn.enilu.flash.warpper.LogWarpper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 登录日志controller
 *
 * @author enilu
 * @version 2018/10/5 0005
 */
@RestController
@RequestMapping("/loginLog")
public class LoginLogController extends BaseController {
    @Autowired
    private LoginLogService loginlogService;
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public Object list(@RequestParam(required = false) String beginTime, @RequestParam(required = false) String endTime, @RequestParam(required = false) String logname) {
        Page<LoginLog> page = new PageFactory<LoginLog>().defaultPage();
        if(StringUtils.isNotEmpty(beginTime)){
            page.addFilter(SearchFilter.build("createTime", SearchFilter.Operator.GTE, DateUtil.parseDate(beginTime)));
        }
        if(StringUtils.isNotEmpty(endTime)){
            page.addFilter(SearchFilter.build("createTime", SearchFilter.Operator.LTE, DateUtil.parseDate(endTime)));
        }
        if(StringUtils.isNotEmpty(logname)){
            page.addFilter(SearchFilter.build("logname", SearchFilter.Operator.LIKE, logname));
        }
        Page pageResult = loginlogService.queryPage(page);
        pageResult.setRecords((List<LoginLog>) new LogWarpper(BeanUtil.objectsToMaps(pageResult.getRecords())).warp());
        return Rets.success(pageResult);

    }


    /**
     * 清空日志
     */
    @RequestMapping(method = RequestMethod.DELETE)
    public Object delLog() {
        loginlogService.clear();
        return Rets.success();
    }
}
