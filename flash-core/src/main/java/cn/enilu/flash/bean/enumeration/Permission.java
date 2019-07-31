package cn.enilu.flash.bean.enumeration;

/**
 * 权限编码列表<br>
 * 权限编码需要和菜单中的菜单编码一致
 * @author ：enilu
 * @date ：Created in 2019/7/31 11:05
 */
public interface Permission {

    //系统管理
    String CFG = "cfg";
    String CFG_EDIT = "cfg.edit";
    String CFG_DEL = "cfg.delete";
    String DICT = "dict";
    String DICT_EDIT = "dict.edit";
    String LOG = "log";
    String LOG_CLEAR = "log.clear";
    String LOGIN_LOG = "login.log";
    String LOGIN_LOG_CLEAR = "login.log.clear";
    String ROLE = "role";
    String ROLE_EDIT = "role.edit";
    String ROLE_DEL = "role.delete";
    String TASK = "task";
    String TASK_EDIT = "task.edit";
    String TASK_DEL = "task.delete";
    String MENU = "menu";
    String MENU_EDIT = "menu.edit";
    String MENU_DEL = "menu.delete";
    String USER = "mgr";
    String USER_EDIT = "mgr.edit";
    String USER_DEL = "mgr.delete";
    String DEPT = "dept";
    String DEPT_EDIT = "dept.edit";
    String DEPT_DEL = "dept.delete";

    //消息管理
    String MSG = "msg";
    String MSG_CLEAR = "msg.clear";
    String MSG_SENDER = "msg.sender";
    String MSG_SENDER_EDIT = "msg.sender.edit";
    String MSG_SENDER_DEL = "msg.sender.delete";
    String MSG_TPL = "msg.tpl";
    String MSG_TPL_EDIT = "msg.tpl.edit";
    String MSG_TPL_DEL = "msg.tpl.delete";

    //CMS管理
    String ARTICLE = "article";
    String ARTICLE_EDIT = "article.edit";
    String ARTICLE_DEL = "article.delete";
    String BANNER = "banner";
    String BANNER_EDIT = "banner.edit";
    String BANNER_DEL = "banner.delete";
    String CHANNEL = "channel";
    String CHANNEL_EDIT = "channel.edit";
    String CHANNEL_DEL = "channel.delete";
    String CONTACTS = "contacts";
    String FILE = "file";
    String FILE_UPLOAD = "file.upload";


}
