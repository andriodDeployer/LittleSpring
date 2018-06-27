package org.litespring.beans;/**
 * Created by DELL on 2018/6/27.
 */

import org.litespring.beans.propertyeditors.CustomBooleanEditor;
import org.litespring.beans.propertyeditors.CustomNumberEditor;
import org.litespring.util.ClassUtils;

import java.beans.PropertyEditor;
import java.util.HashMap;
import java.util.Map;

/**
 * user is
 **/

//利用各种editor进行相应的类型转换
public class SimpleTypeConverter implements TypeConverter{
    Map<Class<?>,PropertyEditor> defaultEditors;

    public <T> T convertIfNecessary(Object value, Class<T> requiredType) throws TypeMismatchException {
        //如果需要转换的数据就是需要的类型，就无需利用editor进行转换了
        if(ClassUtils.isAssignableValue(requiredType,value)){
            return (T)value;
        }else{
            if(value instanceof String){
                PropertyEditor editor = findDefaultEditor(requiredType);
                try{
                    editor.setAsText(((String) value));
                }catch (IllegalArgumentException e){
                    throw new TypeMismatchException(value,requiredType);
                }
                return (T)editor.getValue();
            }else{
                throw new RuntimeException("Todo : cant't convert value for "+value+" class:"+requiredType);
            }
        }
    }

    private PropertyEditor findDefaultEditor(Class<?> requiredType){
        PropertyEditor editor = this.getDefaultEditor(requiredType);
        if(editor == null){
            throw new RuntimeException("Editor for "+requiredType+" has not been implemented");
        }
        return editor;
    }

    private PropertyEditor getDefaultEditor(Class<?> requiredType){
        if(defaultEditors == null){
            createDefaultEditor();
        }
        return defaultEditors.get(requiredType);
    }

    private void createDefaultEditor() {
        defaultEditors = new HashMap<Class<?>, PropertyEditor>(64);
        defaultEditors.put(Boolean.class,new CustomBooleanEditor(false));
        defaultEditors.put(boolean.class,new CustomBooleanEditor(true));

        defaultEditors.put(int.class,new CustomNumberEditor(Integer.class,false));
        defaultEditors.put(Integer.class,new CustomNumberEditor(Integer.class,true));
    }
}
