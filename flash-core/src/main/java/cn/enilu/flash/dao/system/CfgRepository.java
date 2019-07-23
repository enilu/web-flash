
package cn.enilu.flash.dao.system;

import cn.enilu.flash.bean.entity.system.Cfg;
import cn.enilu.flash.dao.BaseRepository;
/**
 * 全局参数dao
 *
 * @author ：enilu
 * @date ：Created in 2019/6/29 12:50
 */
public interface CfgRepository extends BaseRepository<Cfg,Long> {

    Cfg findByCfgName(String cfgName);
}
