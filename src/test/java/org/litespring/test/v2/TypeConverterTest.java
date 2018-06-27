package org.litespring.test.v2;/**
 * Created by DELL on 2018/6/27.
 */

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.litespring.beans.SimpleTypeConverter;
import org.litespring.beans.TypeConverter;
import org.litespring.beans.TypeMismatchException;

/**
 * user is lwb
 **/

//类型转换测试
public class TypeConverterTest {
    TypeConverter converter;
    @Before
    public void setUp(){
        converter = new SimpleTypeConverter();
    }

    //将String类型数据转换成int
    @Test
    public void testConvertStringToInt() throws org.litespring.beans.TypeMismatchException {
        Integer i = converter.convertIfNecessary("3",Integer.class);
        Assert.assertEquals(3,i.intValue());

        try{
            converter.convertIfNecessary("3.1",Integer.class);
        }catch (TypeMismatchException e){
            return;
        }
        Assert.fail();
    }


    @Test
    public void testConvertStringToBoolean() throws org.litespring.beans.TypeMismatchException {
        Boolean b = converter.convertIfNecessary("true",Boolean.class);
        Assert.assertEquals(true,b.booleanValue());

        try{
            converter.convertIfNecessary("aaaa",Boolean.class);
        }catch (TypeMismatchException e){
            return;
        }
        Assert.fail();
    }

}
