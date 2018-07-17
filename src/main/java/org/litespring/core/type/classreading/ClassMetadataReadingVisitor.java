package org.litespring.core.type.classreading;
/**
 * Created by DELL on 2018/7/17.
 */


import org.litespring.util.ClassUtils;
import org.springframework.asm.ClassVisitor;
import org.springframework.asm.Opcodes;

/**
 * user is
 **/


public class ClassMetadataReadingVisitor extends ClassVisitor {

    private int version;
    private String signature;
    private String superName;
    private String[] interfaces;
    private String className;
    private boolean isInterface;
    private boolean isAbstract;
    private boolean isFinal;

    public ClassMetadataReadingVisitor(int i) {
        super(i);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        this.version = version;
        this.signature = signature;
        this.className = ClassUtils.convertResourcePathToClassName(name);
        this.isInterface = ((access & Opcodes.ACC_INTERFACE) != 0);
        this.isAbstract = ((access & Opcodes.ACC_ABSTRACT) != 0);
        this.isFinal = ((access & Opcodes.ACC_FINAL) != 0);

        if (superName != null){
            this.superName = ClassUtils.convertResourcePathToClassName(superName);
        }
        this.interfaces = new String[interfaces.length];
        for(int i=0;i<interfaces.length;i++){
            this.interfaces[i] = ClassUtils.convertResourcePathToClassName(interfaces[i]);
        }
    }

    public int getVersion() {
        return version;
    }

    public String getSignature() {
        return signature;
    }

    public String getSuperName() {
        return superName;
    }

    public String[] getInterfaces() {
        return interfaces;
    }

    public String getClassName() {
        return className;
    }

    public boolean isInterface() {
        return isInterface;
    }

    public boolean isAbstract() {
        return isAbstract;
    }

    public boolean isFinal() {
        return isFinal;
    }
}
