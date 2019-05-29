# 基础代码

## 实体Entity

```java
@Entity
@Table(name = "t_sys_cfg")
public class Cfg {
    private Long id;
    private String cfgName;
    private String cfgValue;
    private String cfgDesc;

    @Id
    @GeneratedValue
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "cfg_name")
    public String getCfgName() {
        return cfgName;
    }

    public void setCfgName(String cfgName) {
        this.cfgName = cfgName;
    }

    @Basic
    @Column(name = "cfg_value")
    public String getCfgValue() {
        return cfgValue;
    }

    public void setCfgValue(String cfgValue) {
        this.cfgValue = cfgValue;
    }

    @Basic
    @Column(name = "cfg_desc")
    public String getCfgDesc() {
        return cfgDesc;
    }

    public void setCfgDesc(String cfgDesc) {
        this.cfgDesc = cfgDesc;
    }
 
}
```

## 数据库操作Repository

```java
public interface CfgRepository extends JpaRepository<Cfg, Long> {

}
```

## controller

```java

@Controller
@RequestMapping("/cfg")
public class CfgController extends BaseController {
    @Autowired
    private CfgRepository cfgRepository;
    private static String PREFIX = "/back/cfg/";
    /**
     * 跳转到参数首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "cfg.html";
    }

    /**
     * 跳转到添加参数
     */
    @RequestMapping("/cfg_add")
    public String orgAdd() {
        return PREFIX + "cfg_add.html";
    }

    /**
     * 跳转到修改参数
     */
    @RequestMapping("/cfg_update/{cfgId}")
    public String orgUpdate(@PathVariable Long cfgId, Model model) {
        Cfg cfg = cfgRepository.findOne(cfgId);
        model.addAttribute("item",cfg);
        return PREFIX + "cfg_edit.html";
    }

    /**
     * 获取参数列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list() {
        return cfgRepository.findAll();
    }

    /**
     * 新增参数
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Cfg cfg) {
        cfgRepository.save(cfg);
        return SUCCESS_TIP;
    }

    /**
     * 删除参数
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Long cfgId) {
        cfgRepository.delete(cfgId);
        return SUCCESS_TIP;
    }

    /**
     * 修改参数
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Cfg cfg) {
        cfgRepository.save(cfg);
        return SUCCESS_TIP;
    }

    /**
     * 参数详情
     */
    @RequestMapping(value = "/detail/{cfgId}")
    @ResponseBody
    public Object detail(@PathVariable("cfgId") Long cfgId) {
        return cfgRepository.findOne(cfgId);
    }

}
```
 