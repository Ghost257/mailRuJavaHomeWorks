import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileLogger implements Logger {
    FileWriter fileWriter;
    @Override
    public void log(int counter, String tag, String message) {
        try {
                fileWriter = new FileWriter("log.txt", true);
                if (tag != null && !tag.isEmpty()) {
                    fileWriter.write("<" + tag + ">");
                }

                fileWriter.write(counter + " " + message);
            if (tag != null && !tag.isEmpty()) {
                fileWriter.write("</" + tag + ">\n");
            }
            else {
                fileWriter.write("\n");
            }

                fileWriter.close();
            }

        catch (IOException e) {
            e.printStackTrace();
        }


    }
}
