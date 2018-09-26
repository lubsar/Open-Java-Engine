package svk.lubsar.j2dgl.fileio;

import java.io.File;
import java.net.URI;

public class FilePort {
	public static File loadFile(String path) {
		return new File(path);
	}
	
	public static File loadFile(URI uri) {
		return new File(uri);
	}
}
