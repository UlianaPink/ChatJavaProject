package com.db.edu.message;

import com.db.edu.exceptions.FileControllerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FileController {
    private final Logger logger = LoggerFactory.getLogger(FileController.class);

    private final String LogsFolder = "JavaChatLogs"; // TODO: to property constants
    private Path filePath;

    public FileController(String fileNamePrefix) {

        filePath = createFileIfNotExists(LogsFolder, String.format("%s-logFile.log", fileNamePrefix));
    }

    private Path createFileIfNotExists(String folderName, String fileName) {
        Path jarDirPath;

        try {
            jarDirPath = Paths.get(
                    Optional.ofNullable(ClassLoader.getSystemClassLoader().getResource(".")).orElseThrow().toURI()
            );
        } catch (URISyntaxException e) {
            throw new FileControllerException("Unable to get current directory: " + e);
        }


        Path logFileDirectoryPath = jarDirPath.resolve(folderName);
        Path logFilePath = logFileDirectoryPath.resolve(fileName);

        try {
            if (!Files.exists(logFileDirectoryPath)) {
                Files.createDirectory(logFileDirectoryPath);
            }
            if (!Files.exists(logFilePath)) {
                Files.createFile(logFilePath);
            }
        } catch (IOException e) {
            throw new FileControllerException("Unable to create file: " + logFilePath);
        }

        return logFilePath;
    }

    public void setFilePath(Path newFilePath) {
        throw new IllegalArgumentException("No such accessible file: " + newFilePath);
    }

    public void addMessage(String message) {
        try {
            Files.writeString(filePath, message + System.lineSeparator(), StandardCharsets.UTF_8, StandardOpenOption.APPEND);
        } catch (IOException e) {
            logger.error("Could not write to file " + filePath);
        }
    }

    public ArrayList<String> getLastMessages(int linesNumber) {
        // TODO: read from last
        ArrayList<String> result = new ArrayList<>();
        try {
            List<String> messages = Files.readAllLines(filePath);
            result = new ArrayList<>(messages.subList(Math.max(0, messages.size() - linesNumber), messages.size()));
        } catch (IOException e) {
            logger.error("Could not read history from file " + filePath);
        }

        return result;
    }
}
