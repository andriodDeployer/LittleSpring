package org.litespring.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by DELL on 2018/6/20.
 */

public interface Resource {

    InputStream getInputStream() throws IOException;
    String getDescription();
}
