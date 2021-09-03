package com.goldenraspberryawards.worstmovie.util;

import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileUtil {

    private final static String CLASS_PATH = "resource:";

    public static List<String> readFileFromPath(String path) throws IOException {
        if (path.toLowerCase().startsWith(CLASS_PATH)) {
            ClassPathResource res = new ClassPathResource(path.toLowerCase().replace(CLASS_PATH, ""));
            return Files.readAllLines(res.getFile().toPath());
        }
        return Files.readAllLines(Path.of(path));
    }
}
