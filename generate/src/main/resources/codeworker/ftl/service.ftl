package ${service_package};

import ${entity_package}.${entity};
import com.easy.core.common.Page;

/**
 * ${entity}[${entity_comment}]的业务接口
 * @author ${author} 
 * @version v 0.1 ${date!''}
 */
public interface ${entity}Service {
    
    /**
     * 保存${entity_comment},根据ID判断是保存还是更新
     * 
     * @param ${lower_entity}
     * @return 
     */
    public ${entity} save(${entity} ${lower_entity});

    /**
     * 根据主键删除${entity_comment}
     * 
     * @param ids
     * @return
     */
    public void deleteByPrimaryKeys(Long[] keys);

    /**
     * 根据主键查询${entity_comment}
     * 
     * @param id
     * @return
     */
    public ${entity} getByPrimaryKey(Long id);

    /**
     * 分页查询${entity_comment}
     * 
     * @param page
     */
    public void page(Page<${entity}> page);
    

}
