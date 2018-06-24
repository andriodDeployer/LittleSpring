package org.litespring.core.io;/**
 * Created by DELL on 2018/6/20.
 */

import org.litespring.util.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * user is
 **/


public class FileSystemResource implements Resource {
    private String path;
    private File file;
    public FileSystemResource(String path) {
        Assert.notNull(path,"path must not null");
        this.path = path;
        this.file = new File(path);
    }


    public InputStream getInputStream() throws IOException {
        return new FileInputStream(file);
    }

    public String getDescription() {
        return "file [ "+this.file.getAbsolutePath() + "]";
    }
}
