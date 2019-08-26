package cn.enilu.flash.service.system;

import cn.enilu.flash.bean.entity.system.Dept;
import cn.enilu.flash.bean.vo.node.DeptNode;
import cn.enilu.flash.bean.vo.node.ZTreeNode;
import cn.enilu.flash.dao.system.DeptRepository;
import cn.enilu.flash.service.BaseService;
import cn.enilu.flash.utils.ToolUtil;
import com.google.common.base.Strings;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created  on 2018/3/21 0021.
 *
 * @author enilu
 */
@Service
public class DeptService extends BaseService<Dept,Long,DeptRepository> {
    @Autowired
    private DeptRepository deptRepository;

    public List<ZTreeNode> tree() {
        List<Object[]> list = deptRepository.tree();
        List<ZTreeNode> nodes = new ArrayList<>();
        for(Object[] obj:list){
            ZTreeNode node = transfer(obj);
            nodes.add(node);
        }
        return nodes;
    }

    private ZTreeNode transfer(Object[] obj){
        ZTreeNode node = new ZTreeNode();
        node.setId(Long.valueOf(obj[0].toString()));
        node.setpId(Long.valueOf(obj[1].toString()));
        node.setName(obj[2].toString());
        node.setIsOpen(Boolean.valueOf(obj[3].toString()));
        return node;
    }
    public List<Dept> query(String condition) {
        List<Dept> list = new ArrayList<>();
        if(Strings.isNullOrEmpty(condition)){
            list = (List<Dept>) deptRepository.findAll();
        }else{
            condition = "%"+condition+"%";
            list = deptRepository.findBySimplenameLikeOrFullnameLike(condition,condition);
        }
        return list;
    }

    public void deleteDept(Long deptId) {
        Dept dept = get(deptId);

        List<Dept> subDepts = deptRepository.findByPidsLike("%[" + dept.getId() + "]%");
        deptRepository.deleteAll(subDepts);
        deptRepository.delete(dept);
    }

    public List<DeptNode> queryAllNode() {
        List<Dept> list = super.queryAll();
        return generateTree(list);
    }

    public void deptSetPids(Dept dept) {
        if (ToolUtil.isEmpty(dept.getPid()) || dept.getPid().equals(0)) {
            dept.setPid(0L);
            dept.setPids("[0],");
        } else {
            Long pid = dept.getPid();
            Dept temp = get(pid);
            String pids = "";
            if(temp!=null){
                pids = temp.getPids();
            }
            dept.setPid(pid);
            dept.setPids(pids + "[" + pid + "],");
        }
    }

    private List<DeptNode> generateTree(List<Dept> list){

        List<DeptNode> nodes = new ArrayList<>(20);
        for(Dept dept:list){
            DeptNode deptNode = new DeptNode();
            BeanUtils.copyProperties(dept,deptNode);
            nodes.add(deptNode);
        }
        for(DeptNode deptNode:nodes){
            for(DeptNode child:nodes){
                if(child.getPid().intValue() == deptNode.getId().intValue()){
                    deptNode.getChildren().add(child);
                }
            }
        }
        List<DeptNode> result = new ArrayList<>(20);
        for(DeptNode node:nodes){
            if(node.getPid().intValue() == 0){
                result.add(node);
            }
        }
        return result;


    }



}
