package org.litespring.core.type.classreading;
/**
 * Created by DELL on 2018/7/17.
 */


import org.litespring.core.type.ClassMetadata;
import org.litespring.util.ClassUtils;
import org.springframework.asm.*;

/**
 * user is
 **/


public class ClassMetadataReadingVisitor extends ClassVisitor implements ClassMetadata{

    private int version;
    private String signature;
    private String superName;
    private String[] interfaces;
    private String className;
    private boolean isInterface;
    private boolean isAbstract;
    private boolean isFinal;

    public ClassMetadataReadingVisitor() {
        super(SpringAsmInfo.ASM_VERSION);
    }

    //当classReader读取到class的元数据信息时，进行调用
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

    //当classReader解析class字节码中的方法，每解析一个，就会被调用一次
    @Override
    public MethodVisitor visitMethod(int i, String s, String s1, String s2, String[] strings) {
        return super.visitMethod(i, s, s1, s2, strings);
    }

    //当classReader解析class字节码中的字段，每解析一个，就会被调用一次
    @Override
    public FieldVisitor visitField(int i, String s, String s1, String s2, Object o) {
        return super.visitField(i, s, s1, s2, o);
    }

    public int getVersion() {
        return version;
    }

    public String getSignature() {
        return signature;
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



    public boolean hasSuperClass() {
        return superName != null;
    }

    public String getSuperClassName() {
        if(hasSuperClass()){
            return superName;
        }
        return "";
    }

    public String[] getInterfaceNames() {
        return interfaces;
    }
}
