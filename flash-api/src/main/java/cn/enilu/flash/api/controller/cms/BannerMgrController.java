package cn.enilu.flash.api.controller.cms;

import cn.enilu.flash.api.controller.BaseController;
import cn.enilu.flash.bean.core.BussinessLog;
import cn.enilu.flash.bean.entity.cms.Banner;
import cn.enilu.flash.bean.enumeration.Permission;
import cn.enilu.flash.bean.vo.front.Rets;
import cn.enilu.flash.bean.vo.query.SearchFilter;
import cn.enilu.flash.service.cms.BannerService;
import cn.enilu.flash.utils.StringUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * banner管理
 */
@RestController
@RequestMapping("/banner")
public class BannerMgrController extends BaseController {
    @Autowired
    private BannerService bannerService;

    @PostMapping
    @BussinessLog(value = "编辑banner", key = "title")
    @RequiresPermissions(value = {Permission.BANNER_EDIT})
    public Object save(@RequestBody @Valid Banner banner) {
        if (banner.getId() == null) {
            bannerService.insert(banner);
        } else {
            bannerService.update(banner);
        }
        return Rets.success();
    }

    @DeleteMapping
    @BussinessLog(value = "删除banner", key = "id")
    @RequiresPermissions(value = {Permission.BANNER_DEL})
    public Object remove(Long id) {
        bannerService.delete(id);
        return Rets.success();
    }

    @GetMapping(value = "/list")
    @RequiresPermissions(value = {Permission.BANNER})
    public Object list(@RequestParam(required = false) String title) {
        SearchFilter filter = null;
        if (StringUtil.isNotEmpty(title)) {
            filter = SearchFilter.build("title", SearchFilter.Operator.LIKE, title);
        }
        List<Banner> list = bannerService.queryAll(filter);
        return Rets.success(list);
    }
}
