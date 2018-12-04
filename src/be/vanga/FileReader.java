package be.vanga;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.function.Consumer;

public class FileReader {

	public void readAndConsume(String path, Consumer<String> consumer)	{
		File f = new File(path);
		
		
		try(BufferedReader b = new BufferedReader(new java.io.FileReader(f));) {

            String readLine = "";
            while ((readLine = b.readLine()) != null) {
                consumer.accept(readLine);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
