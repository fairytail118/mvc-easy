package ${entity_package};

import com.easy.core.entity.BaseEntity;
import com.easy.core.entity.IdEntity;
/**
 * ${entity_comment}
 * @author ${author}
 * @version v 0.1 ${date}
 */
public class ${entity} extends <#if pkname??>IdEntity<#else>BaseEntity</#if> {
	private static final long serialVersionUID = ${serialVersionUID}L;
	
	${entity_content}
    
}
