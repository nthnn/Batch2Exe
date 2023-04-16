package xyz.nthnn.batch2exe.io;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import xyz.nthnn.batch2exe.core.ProjectInfo;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ConfigIO {
    public static ProjectInfo load(String fileName) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        JSONObject obj = (JSONObject) parser.parse(new FileReader(fileName));

        String title = (String) obj.get("Title"),
               batchFileName = (String) obj.get("FileName"),
               output = (String) obj.get("Output"),
               icon = (String) obj.get("Icon"),
               workingDir = (String) obj.get("DefaultWorkingDirectory"),
               arguments = (String) obj.get("Arguments");

        return ProjectInfo.initInfo(title, batchFileName, output, icon, workingDir, arguments);
    }

    @SuppressWarnings("all")
    public static void save(String fileName, ProjectInfo info) throws IOException {
        JSONObject obj = new JSONObject();
        obj.put("Title", info.title);
        obj.put("FileName", info.fileName);
        obj.put("Output", info.output);

        if(info.icon != null)
            obj.put("Icon", info.icon);

        if(info.workingDirectory != null)
            obj.put("DefaultWorkingDirectory", info.workingDirectory);

        if(info.arguments != null)
            obj.put("Arguments", info.arguments);

        Files.write(Paths.get(fileName), obj.toJSONString().getBytes());
    }
}