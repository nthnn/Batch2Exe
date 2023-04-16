namespace Batch2ExeWrapper {
    public sealed class Template {
        public static string Wrapper =
            "using System.Diagnostics;\r\n" +
            "using System.IO;\r\n\r\n" +
            "public class Batch2ExeWrapper {\r\n" +
            "    private static string Title = \"{title}\";\r\n" +
            "    private static string Arguments = {arguments};\r\n" +
            "    private static string DefaultWorkingDirectory = {def_cd};\r\n" +
            "    private static string Script = \"{script}\";\r\n" +
            "    private static string FileName = \"-{filename}\";\r\n\r\n" +
            "    public static void Main(string[] Args) {\r\n" +
            "        File.WriteAllText(FileName, \"@echo off\\r\\ntitle \" + Title + \"\\r\\n\" + Script);\r\n" +
            "        Process proc = new Process();\r\n\r\n" +
            "        proc.StartInfo.FileName = FileName;\r\n" +
            "        if(Arguments != null)\r\n" +
            "            proc.StartInfo.Arguments = Arguments;\r\n\r\n" +
            "        if(DefaultWorkingDirectory != null)\r\n" +
            "            proc.StartInfo.WorkingDirectory = DefaultWorkingDirectory;\r\n\r\n" +
            "        proc.StartInfo.CreateNoWindow = true;\r\n" +
            "        proc.Start();\r\n" +
            "        File.SetAttributes(FileName, File.GetAttributes(FileName) | FileAttributes.Hidden);\r\n\r\n" +
            "        proc.WaitForExit();\r\n" +
            "        File.Delete(FileName);\r\n" +
            "    }\r\n" +
            "}";
    }
}