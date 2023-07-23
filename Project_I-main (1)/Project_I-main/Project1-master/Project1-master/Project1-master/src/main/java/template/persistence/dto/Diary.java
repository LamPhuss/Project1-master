package template.persistence.dto;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Diary {
    private static final Logger logger = Logger.getLogger("MyLog");

    public static Logger getLogger() {
        try {
            // Kiểm tra và tạo file "batch_request.log"
            File file = new File("batch_request.log");
            if (!file.exists()) {
                file.createNewFile();
            }

            String filePath = new File(file.getPath()).getAbsolutePath();

            // Tạo FileHandler và thiết lập đường dẫn tuyệt đối cho file
            FileHandler fh = new FileHandler(filePath, true);


            // Thiết lập định dạng cho log bằng SimpleFormatter
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);

            // Thêm FileHandler vào logger
            logger.addHandler(fh);
        } catch (IOException var10) {
            logger.warning("Error creating log file: " + var10.getMessage());
        }
        return logger;
    }

}
