/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package ${daoimpl_package};

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;
import ${dao_package}.${entity}Dao;
import ${entity_package}.${entity};
import com.easy.core.common.Page;
import com.easy.core.dao.impl.MyBatisGenericDao;

/**
 * 
 * @author ${author}
 * @version v 0.1 ${date}
 */
@Repository
public class ${entity}DaoImpl extends MyBatisGenericDao implements ${entity}Dao {
 	
    @Override
    public ${entity} create(${entity} ${lower_entity}) {
        save("${dao_package}.${entity}Dao.create", ${lower_entity});
        return ${lower_entity};
    }

   
    @Override
    public int update(${entity} ${lower_entity}) {
        return update("${dao_package}.${entity}Dao.update", ${lower_entity});
    }

   
    @Override
    public int deleteByPrimaryKeys(${pkparams}) {
    	Map<String, Object> params = new HashMap<String, Object>();
    	<#if pklist??>
		<#list pklist as pk>
		params.put("${pk}", ${pk});
		</#list>
		</#if>
        return delete("${dao_package}.${entity}Dao.deleteByPrimaryKeys", params);
    }

   
    @Override
    public ${entity} getByPrimaryKey(${pkparams}) {
    	Map<String, Object> params = new HashMap<String, Object>();
    	<#if pklist??>
		<#list pklist as pk>
		params.put("${pk}", ${pk});
		</#list>
		</#if>
        return get("${dao_package}.${entity}Dao.getByPrimaryKey", params);
    }

   
    @Override
    public List<${entity}> selectByCriteria(${entity} ${lower_entity}) {
        return list("${dao_package}.${entity}Dao.selectByCriteria", ${lower_entity});
    }
    


}
