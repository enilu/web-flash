package cn.enilu.flash.bean.constant.factory;

import cn.enilu.flash.bean.constant.state.Order;
import cn.enilu.flash.utils.HttpUtil;
import cn.enilu.flash.utils.StringUtil;
import cn.enilu.flash.utils.factory.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;

import javax.servlet.http.HttpServletRequest;

/**
 * BootStrap Table默认的分页参数创建
 *
 * @author fengshuonan
 * @date 2017-04-05 22:25
 */
public class PageFactory<T> {

    public Page<T> defaultPage() {
        HttpServletRequest request = HttpUtil.getRequest();
        //每页多少条数据
        int limit = Integer.valueOf(request.getParameter("limit"));
        String pageNum = request.getParameter("page");
        int current = 1;
        //每页的偏移量(本页当前有多少条)
        if (StringUtils.isNotEmpty(pageNum)) {
            current = Integer.valueOf(pageNum);
        } else {
            current = Integer.valueOf(request.getParameter("offset")) / limit + 1;
        }
        //排序字段名称
        String sortName = request.getParameter("sort");
        //asc或desc(升序或降序)
        String order = request.getParameter("order");
        Page<T> page = new Page<>(current, limit);
        if (StringUtil.isNotEmpty(sortName)) {
            Sort.Direction direction = Order.ASC.getDes().equals(order) ? Sort.Direction.ASC : Sort.Direction.DESC;
            Sort sort = Sort.by(direction, sortName);
            page.setSort(sort);
        }
        return page;
    }
}
