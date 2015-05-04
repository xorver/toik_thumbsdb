package pl.edu.agh.toik;

import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.DocumentInputStream;
import org.apache.poi.poifs.filesystem.Entry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ThumbExtractor {

    private FileInputStream file;

    public ThumbExtractor(FileInputStream file) {
        this.file = file;
    }

    public List<BufferedImage> extract() throws IOException {
        POIFSFileSystem fs = new POIFSFileSystem(file);
        DirectoryEntry root = fs.getRoot();

        List<BufferedImage> result = new ArrayList<>();

        for(Iterator<Entry> it = root.getEntries();it.hasNext();) {
            Entry entry = it.next();
            DocumentInputStream is = fs.createDocumentInputStream(entry.getName());

            // Read the header lines
            int header_len = is.read();
            for (int i = 1; i < header_len; i++) {
                is.read();
            }

            BufferedImage image = ImageIO.read(is);

//            JPEGImageDecoder decoder = JPEGCodec.createJPEGDecoder(is);
//            JPEGDecodeParam param = JPEGCodec.getDefaultJPEGEncodeParam(4, JPEGDecodeParam.COLOR_ID_RGBA);
//            decoder.setJPEGDecodeParam(param);

            result.add(image);
        }
        return result;
    }
}
