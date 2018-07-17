package org.litespring.test.v4;
/**
 * Created by DELL on 2018/7/17.
 */

import org.junit.Test;
import org.litespring.core.io.ClassPathResource;
import org.litespring.core.type.classreading.ClassMetadataReadingVisitor;
import org.objectweb.asm.ClassReader;

import java.io.IOException;

/**
 * user is lwb
 **/


public class ClassReaderTest {

    @Test
    public void testGetClassMetaData() throws IOException {
        ClassPathResource resource = new ClassPathResource("org/litespring/service/v4/PetStoreService.class");
        ClassReader reader = new ClassReader(resource.getInputStream());

        ClassMetadataReadingVisitor visitor = new ClassMetadataReadingVisitor();
        reader.accept(visitor,ClassReader.SKIP_DEBUG);

        //Assert.assertFalse(visitor.is);
    }

}
