package org.litespring.core.io.support;/**
 * Created by DELL on 2018/7/17.
 */

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.litespring.core.io.FileSystemResource;
import org.litespring.core.io.Resource;
import org.litespring.util.Assert;
import org.litespring.util.ClassUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * user is
 **/


public class PackageResouceLoader {


    private static final Log logger = LogFactory.getLog(PackageResouceLoader.class);
    private final ClassLoader classLoader;

    public PackageResouceLoader(){
        this.classLoader = ClassUtils.getDefaultClassLoader();
    }

    public PackageResouceLoader(ClassLoader classLoader){
        Assert.notNull(classLoader,"ResouceLoader must not be null");
        this.classLoader = classLoader;
    }

    public ClassLoader getClassLoader(){
        return classLoader;
    }

    public Resource[] getResouces(String basePackage) throws IOException{
        Assert.notNull(basePackage,"basePackage must not be null");
        String location = ClassUtils.convertClassNameToResourcePath(basePackage);
        ClassLoader classLoader = getClassLoader();
        URL url = classLoader.getResource(location);
        File rootDir = new File(url.getFile());

        Set<File> matchingFiles = retrieveMatchingFiles(rootDir);
        Resource[] result = new Resource[matchingFiles.size()];
        int i =0;
        for(File file : matchingFiles){
            result[i++] = new FileSystemResource(file);
        }
        return result;
    }


    //检索匹配的文件
    protected Set<File> retrieveMatchingFiles(File rootDir) throws IOException {
        if(!rootDir.exists()){
            if(logger.isDebugEnabled()){
                logger.debug("Skipping [" + rootDir.getAbsolutePath() + "] because it is null" );
            }
            return Collections.emptySet();
        }

        if(!rootDir.isDirectory()){
            if(logger.isDebugEnabled()){
                logger.debug("Skipping ["+rootDir.getAbsolutePath() + "] because it is not dircetory");
            }
            return Collections.emptySet();
        }

        if(!rootDir.canRead()){
            if(logger.isDebugEnabled()){
                logger.debug("Cannot search for matching files underneath ["+rootDir.getAbsolutePath() + "] because the application is not allowed to read the directory");
            }
            return  Collections.emptySet();
        }

        Set<File> result = new LinkedHashSet<File>(8);
        doRetrieveMatchingFiles(rootDir,result);
        return result;
    }

    protected void doRetrieveMatchingFiles(File rootDir, Set<File> result) {

        File[] dirContents = rootDir.listFiles();
        if(dirContents == null){
            if(logger.isWarnEnabled()){
                logger.warn("Could not retrieves contents of directory [" + rootDir.getAbsolutePath() + "]");
            }
            return;
        }

        for(File file : dirContents){
            if(file.isDirectory()){
                if(!file.canRead()){
                    if(logger.isDebugEnabled()){
                        logger.debug("Skipping subdirectory ["+ rootDir.getAbsolutePath() + "] because the application is not allowed to read the directory");
                    }
                }else{
                    doRetrieveMatchingFiles(file,result);
                }
            }else {
                result.add(file);
            }
        }


    }


}
