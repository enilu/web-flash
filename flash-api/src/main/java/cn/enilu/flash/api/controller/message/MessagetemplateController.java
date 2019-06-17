package cn.enilu.flash.api.controller.message;

import cn.enilu.flash.bean.entity.message.MessageTemplate;
import cn.enilu.flash.service.message.MessagetemplateService;

import cn.enilu.flash.bean.core.BussinessLog;
import cn.enilu.flash.bean.constant.factory.PageFactory;
import cn.enilu.flash.bean.dictmap.CommonDict;
import cn.enilu.flash.bean.enumeration.BizExceptionEnum;
import cn.enilu.flash.bean.exception.GunsException;
import cn.enilu.flash.bean.vo.front.Rets;

import cn.enilu.flash.utils.Maps;
import cn.enilu.flash.utils.ToolUtil;
import cn.enilu.flash.utils.factory.Page;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/message/template")
public class MessagetemplateController {
	private  Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private MessagetemplateService messagetemplateService;

	@RequestMapping(value = "/list",method = RequestMethod.GET)
	public Object list() {
	Page<MessageTemplate> page = new PageFactory<MessageTemplate>().defaultPage();
		page = messagetemplateService.findPage(page, Maps.newHashMap());
		page.setRecords(page.getRecords());
		return Rets.success(page);
	}
	@RequestMapping(method = RequestMethod.POST)
	@BussinessLog(value = "编辑消息模板", key = "name",dict= CommonDict.class)
	public Object save(@ModelAttribute MessageTemplate tMessageTemplate){
		messagetemplateService.save(tMessageTemplate);
		return Rets.success();
	}
	@RequestMapping(method = RequestMethod.DELETE)
	@BussinessLog(value = "删除消息模板", key = "id",dict= CommonDict.class)
	public Object remove(Long id){
		if (ToolUtil.isEmpty(id)) {
			throw new GunsException(BizExceptionEnum.REQUEST_NULL);
		}
		messagetemplateService.delete(id);
		return Rets.success();
	}
}