package cn.enilu.flash.service.system;

import cn.enilu.flash.bean.entity.system.Dict;
import cn.enilu.flash.cache.DictCache;
import cn.enilu.flash.dao.system.DictRepository;
import cn.enilu.flash.service.BaseService;
import cn.enilu.flash.utils.factory.MutiStrFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 字典服务
 *
 * @author fengshuonan
 * @date 2017-04-27 17:00
 */
@Service
public class DictService extends BaseService<Dict,Long,DictRepository> {
    private Logger logger = LoggerFactory.getLogger(DictService.class);
    @Resource
    DictRepository dictRepository;
    @Autowired
    private DictCache dictCache;

    public void addDict(String dictName, String dictValues) {
        //判断有没有该字典
        List<Dict> dicts = dictRepository.findByNameAndPid(dictName,0L);
        if(dicts != null && dicts.size() > 0){
            return ;
        }

        //解析dictValues
        List<Map<String, String>> items = MutiStrFactory.parseKeyValue(dictValues);

        //添加字典
        Dict dict = new Dict();
        dict.setName(dictName);
        dict.setNum("0");
        dict.setPid(0L);
        this.dictRepository.save(dict);

        //添加字典条目
        for (Map<String, String> item : items) {
            String num = item.get(MutiStrFactory.MUTI_STR_KEY);
            String name = item.get(MutiStrFactory.MUTI_STR_VALUE);
            Dict itemDict = new Dict();
            itemDict.setPid(dict.getId());
            itemDict.setName(name);
            try {
                itemDict.setNum(num);
            }catch (NumberFormatException e){
                logger.error(e.getMessage(),e);
            }
            this.dictRepository.save(itemDict);
        }
        dictCache.cache();
    }

    public void editDict(Long dictId, String dictName, String dicts) {
        //删除之前的字典
        this.delteDict(dictId);

        //重新添加新的字典
        this.addDict(dictName,dicts);

        dictCache.cache();
    }

    public void delteDict(Long dictId) {
        //删除这个字典的子词典
        List<Dict> subList = dictRepository.findByPid(dictId);
        dictRepository.deleteAll(subList);
        //删除这个词典
        dictRepository.deleteById(dictId);

        dictCache.cache();
    }
    @Override
    public Dict get(Long id) {
        Optional<Dict> optional = dictRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    public List<Dict> findByNameLike(String name) {
        return dictRepository.findByNameLike(name);
    }

    public List<Dict> findByPid(Long pid) {
        return dictRepository.findByPid(pid);
    }
}
