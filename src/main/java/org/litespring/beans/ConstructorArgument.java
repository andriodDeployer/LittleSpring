package org.litespring.beans;/**
 * Created by Administrator on 2018-6-29 0029.
 */

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * user is
 **/


public class ConstructorArgument {
    private final List<ValueHolder> argumentValues = new LinkedList<ValueHolder>();

    public List<ValueHolder> getArgumentValues() {
        return Collections.unmodifiableList(argumentValues);
    }

    public ConstructorArgument(){}

    public void addArgumentValue(Object value,String type){
        argumentValues.add(new ValueHolder(value,type));
    }

    public void addArgumentValue(ValueHolder valueHolder){
        argumentValues.add(valueHolder);
    }

    public int getArgumentCount() {
        return argumentValues.size();
    }

    public boolean isEmpty(){
        return argumentValues.isEmpty();
    }

    public void clear(){
        argumentValues.clear();
    }

}

