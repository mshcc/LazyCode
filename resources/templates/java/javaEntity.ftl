package ${entityPkg};

import java.util.Objects;

/**
* @author ${author}
* @Date ${generatorDate}
*/
public class ${entityName} {

<#list fields as field>
    private ${field.fieldType} ${field.fieldName};
</#list>

    public ${entityName}() {
    }

    public ${entityName}(<#list fields as field><#if field_has_next>${field.fieldType} ${field.fieldName}, <#else>${field.fieldType} ${field.fieldName}</#if></#list>) {
    <#list fields as field>
        this.${field.fieldName} = ${field.fieldName};
    </#list>
    }
    <#list fields as field>

        public void set${field.upperFieldName}(${field.fieldType} ${field.fieldName}) {
        this.${field.fieldName} = ${field.fieldName};
        }
    </#list>
    <#list fields as field>

        public ${field.fieldType} get${field.upperFieldName}() {
        return ${field.fieldName};
        }
    </#list>

    <#if toString=="1">
        @Override
        public String toString() {
        return "${entityName}{"
        <#list fields as field>
            <#if field_has_next>
                + "${field.fieldName}='" + ${field.fieldName} + '\'' + ','
            <#else>
                + "${field.fieldName}='" + ${field.fieldName} + '\''
            </#if>
        </#list>
        + '}';
        }
    </#if>
        @Override
        public boolean equals(Object o) {
        if (this == o){
        return true;
        }
        if (o == null || getClass() != o.getClass()) {
        return false;
        }
        ${entityName} obj = (${entityName}) o;
        return
        <#list fields as field>
            <#if field_has_next>
                Objects.equals(${field.fieldName}, obj.get${field.upperFieldName}()) &&
            <#else>
                Objects.equals(${field.fieldName}, obj.get${field.upperFieldName}())</#if></#list>;
        }
        @Override
        public int hashCode() {
        return Objects.hash(<#list fields as field><#if field_has_next>${field.fieldName}, <#else>${field.fieldName}</#if></#list>);
        }
}
