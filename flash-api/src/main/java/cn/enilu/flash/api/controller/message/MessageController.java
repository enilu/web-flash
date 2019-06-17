package cn.enilu.flash.api.controller.message;

import cn.enilu.flash.bean.entity.message.Message;
import cn.enilu.flash.service.message.MessageService;

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
@RequestMapping("/message")
public class MessageController {
	private  Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private MessageService messageService;

	@RequestMapping(value = "/list",method = RequestMethod.GET)
	public Object list() {
	Page<Message> page = new PageFactory<Message>().defaultPage();
		page = messageService.findPage(page, Maps.newHashMap());
		page.setRecords(page.getRecords());
		return Rets.success(page);
	}
	@RequestMapping(method = RequestMethod.POST)
	@BussinessLog(value = "编辑历史消息", key = "name",dict= CommonDict.class)
	public Object save(@ModelAttribute Message tMessage){
		messageService.save(tMessage);
		return Rets.success();
	}
	@RequestMapping(method = RequestMethod.DELETE)
	@BussinessLog(value = "删除历史消息", key = "id",dict= CommonDict.class)
	public Object remove(Long id){
		if (ToolUtil.isEmpty(id)) {
			throw new GunsException(BizExceptionEnum.REQUEST_NULL);
		}
		messageService.delete(id);
		return Rets.success();
	}
}