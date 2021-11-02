package main.com.db.edu.message;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Optional;

public class FileController {
    private final String LogsFolder = "JavaChatLogs"; // TODO: to property constants
    private Path filePath;

    public FileController(String fileNamePrefix) {

        filePath = createFileIfNotExists(LogsFolder, String.format("%s-logFile", fileNamePrefix));
    }

    private Path createFileIfNotExists(String folderName, String fileName) {
        Path jarDir = Paths.get(
                Optional.ofNullable(ClassLoader.getSystemClassLoader().getResource(".")).orElseThrow().getPath()
        );

        Path logFilePath = jarDir.resolve(folderName).resolve(fileName);
        if (!Files.exists(logFilePath)) {
            try {
                Files.createFile(logFilePath);
            } catch (IOException e) {
                throw new IllegalArgumentException("Unable to create file: " + logFilePath);
            }
        }

        return logFilePath;
    }

    public void setFilePath(Path newFilePath) {
        throw new IllegalArgumentException("No such accessible file: " + newFilePath);
    }

    public void addMessage(String message) throws IOException {
        Files.writeString(filePath, message, StandardCharsets.UTF_8, StandardOpenOption.APPEND);
    }
}
