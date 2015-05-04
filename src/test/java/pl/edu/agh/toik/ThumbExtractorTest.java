package pl.edu.agh.toik;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.List;

import static org.junit.Assert.*;

public class ThumbExtractorTest {

    ThumbExtractor underTest;

    @org.junit.Test
    public void testExtractCount() throws Exception {
        //given
        URL url = Thread.currentThread().getContextClassLoader().getResource("test_pictures/Thumbs.db");
        assert url != null;
        underTest = new ThumbExtractor(new FileInputStream(url.getPath()));

        //when
        List<BufferedImage> list = underTest.extract();

        //then
        assertEquals(8, list.size());
//        for(int i = 0; i < list.size(); i++) {
//            File outputfile = new File(String.valueOf(i + ".jpeg"));
//            ImageIO.write(list.get(i), "jpeg", outputfile);
//        }
    }
}