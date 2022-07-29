package cn.enilu.flash.api.controller.cms;

import cn.enilu.flash.api.controller.BaseController;
import cn.enilu.flash.bean.core.BussinessLog;
import cn.enilu.flash.bean.entity.cms.Channel;
import cn.enilu.flash.bean.enumeration.Permission;
import cn.enilu.flash.bean.vo.front.Rets;
import cn.enilu.flash.service.cms.ChannelService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 栏目管理
 */
@RestController
@RequestMapping("/channel")
public class ChannelMgrController extends BaseController {
    @Autowired
    private ChannelService channelService;

    @PostMapping
    @BussinessLog(value = "编辑栏目", key = "name")
    @RequiresPermissions(value = {Permission.CHANNEL_EDIT})
    public Object save(@RequestBody @Valid Channel channel) {
        if (channel.getId() == null) {
            channelService.insert(channel);
        } else {
            channelService.update(channel);
        }
        return Rets.success();
    }

    @DeleteMapping
    @BussinessLog(value = "删除栏目", key = "id")
    @RequiresPermissions(value = {Permission.CHANNEL_DEL})
    public Object remove(Long id) {
        channelService.delete(id);
        return Rets.success();
    }

    @GetMapping(value = "/list")
    @RequiresPermissions(value = {Permission.CHANNEL})
    public Object list() {
        List<Channel> list = channelService.queryAll();
        return Rets.success(list);
    }
}
