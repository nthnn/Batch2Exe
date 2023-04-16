package xyz.nthnn.batch2exe.core;

public class ProjectInfo {
    public String title, fileName, output, icon, workingDirectory, arguments;

    protected ProjectInfo() { }

    public static ProjectInfo initInfo(String title, String fileName, String output, String icon, String workingDirectory, String arguments) {
        ProjectInfo info = new ProjectInfo();
        info.title = title;
        info.fileName = fileName;
        info.output = output;
        info.icon = icon;
        info.workingDirectory = workingDirectory;
        info.arguments = arguments;

        return info;
    }
}
