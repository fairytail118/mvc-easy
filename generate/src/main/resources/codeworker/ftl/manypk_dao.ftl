/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package ${dao_package};

import java.util.List;

import ${entity_package}.${entity};
import com.easy.core.common.Page;

/**
 ${entity}[${entity_comment}]的数据访问接口
 * @author ${author}
 * @version v 0.1 ${date}
 */
public interface ${entity}Dao {

    /**
     * 保存${entity_comment}
     * 
     * @param ${lower_entity}
     * @return 返回${entity}
     */
    public ${entity} create(${entity} ${lower_entity});

    /**
     * 更新${entity_comment}
     * 
     * @param ${lower_entity}
     * @param 返回更新数量
     */
    public int update(${entity} ${lower_entity});

    /**
     * 根据主键删除${entity_comment}
     * 
     * @param ids
     * @return
     */
    public int deleteByPrimaryKeys(${pkparams});

    /**
     * 根据主键查询${entity_comment}
     * 
     * @param id
     * @return
     */
    public ${entity} getByPrimaryKey(${pkparams});

    /**
     * 条件查询${entity_comment}列表
     * 
     * @param ${lower_entity}
     * @return
     */
    public List<${entity}> selectByCriteria(${entity} ${lower_entity});
    
    <#if pklist??>
		<#list pklist as pk>
	/**
     * 根据${pk}删除${entity_comment}
     * 
     * @param ${pk}
     * @return
     */
    public int deleteBy${pk?cap_first}(Long ${pk});		
		</#list>
	</#if>
    
    

}
