using System.Diagnostics;

public class Batch2ExeWrapperTemplate {
    private static string Title = "Hello, world!";
    private static string Arguments = null;
    private static string DefaultWorkingDirectory = null;
    private static string Script = "echo \"Hello, world\";";
    private static string FileName = "hello.bat";

    public static void Main(string[] Args) {
        File.WriteAllText(FileName, "@echo off\r\ntitle " + Title + "\r\n" + Script);
        Process proc = new Process();

        proc.StartInfo.FileName = FileName;
        if(Arguments != null)
            proc.StartInfo.Arguments = Arguments;

        if(DefaultWorkingDirectory != null)
            proc.StartInfo.WorkingDirectory = DefaultWorkingDirectory;

        proc.Start();
        File.SetAttributes(FileName, File.GetAttributes(FileName) | FileAttributes.Hidden);

        proc.WaitForExit();
        File.Delete(FileName);
    }
}