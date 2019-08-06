package cn.enilu.flash.code;

import org.nutz.ioc.Ioc;

import java.util.Map;

/**
 * 基础的数据结构加载器<br>
 * @author : enilu <br>
 * 创建日期: 16-7-12<br>
 */
public abstract class AbstractLoader {

    public abstract Map<String, TableDescriptor> loadTables(Ioc ioc,
                                                   String basePackageName, String baseUri,
                                                            String servPackageName,
                                                            String repositoryPackageName,
                                                            String modPackageName) throws Exception;


    }
