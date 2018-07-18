package org.litespring.core.type;

/**
 * Created by DELL on 2018/7/18.
 */
public interface ClassMetadata {
    String getClassName();
    boolean isInterface();
    boolean isAbstract();
    boolean isFinal();
    boolean hasSuperClass();
    String getSuperClassName();
    String[] getInterfaceNames();
}
