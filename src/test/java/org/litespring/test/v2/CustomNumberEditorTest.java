package org.litespring.test.v2;/**
 * Created by DELL on 2018/6/26.
 */

import org.junit.Assert;
import org.junit.Test;
import org.litespring.beans.propertyeditors.CustomNumberEditor;

/**
 * user is lwb
 **/


public class CustomNumberEditorTest {

    //CustomNumberEditor将字符串转换成数字类型，首先执行何种数字类型，int,double或其他数字类型，第二个参数指定是否对空或者null的字符串进行处理。
    @Test
    public void testConverString(){
        CustomNumberEditor editor = new CustomNumberEditor(Integer.class,true);
        editor.setAsText("3");
        Object value = editor.getValue();
        Assert.assertTrue(value instanceof Integer);
        Assert.assertEquals(3,((Integer)editor.getValue()).intValue());

        editor.setAsText("");
        Assert.assertTrue(editor.getValue() == null);

        try{
            editor.setAsText("3.1");
        }catch (IllegalArgumentException e){
            return ;
        }
        Assert.fail();
    }
}
