package org.litespring.core.io;/**
 * Created by DELL on 2018/6/20.
 */

import org.litespring.util.ClassUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * user is
 **/


public class ClassPathResource implements Resource {

    private String path;
    private ClassLoader classLoader;

    public ClassPathResource(String path) {
       this(path,(ClassLoader) null);//这里进行强转，是为了防止代码产生歧义。
    }

    public ClassPathResource(String path, ClassLoader loader){
        this.path = path;
        this.classLoader = (loader == null ? ClassUtils.getDefaultClassLoader() : loader);
    }

    public InputStream getInputStream() throws IOException {
        InputStream in = classLoader.getResourceAsStream(path);
        if(in == null){
            throw new FileNotFoundException(path + "cannot be opened");
        }
        return in;
    }

    public String getDescription() {
        return path;
    }
}
