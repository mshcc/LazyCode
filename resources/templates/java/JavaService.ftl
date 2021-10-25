package ${servicePkg};

import ${entityPkg}.${entityName};

import java.util.List;

/**
* @author ${author}
* @Date ${generatorDate}
*/
public interface ${entityName}Service {

    int insert(${entityName} record);

    int insertList(List<${entityName}> records);

    int delete(${entityName} record);
    <#if primaryKeyFieldNameInDb=="-1">
    <#else>
        int deleteByPrimaryKey(${primaryKeyType} primaryKey);
    </#if>
    int deleteList(List<${entityName}> records);

    int update(${entityName} record);
    <#if primaryKeyFieldNameInDb=="-1">
    <#else>
    ${entityName} selectByPrimaryKey(${primaryKeyType} primaryKey);
    </#if>
    List<${entityName}> selectList(${entityName} record);

}
