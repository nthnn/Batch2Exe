using System.CodeDom.Compiler;
using System.Diagnostics;
using System.Runtime.InteropServices;
using System.Text.Json;
using Microsoft.CSharp;

#pragma warning disable CS0618

namespace Batch2ExeWrapper {
    public class Program {
        public class Project {
            public string? Title { get; set; }
            public string? FileName { get; set; }
            public string? Output { get; set; }
            public string? Icon { get; set; }
            public string? Arguments { get; set; }
            public string? DefaultWorkingDirectory { get; set; }

            public bool HasNoParsedOrIncompleteData() {
                return this.Title == null && this.FileName == null && this.Output == null;
            }
        }

        private static string GetCompilerFilePath() {
            return Path.Combine(@"C:\Windows\Microsoft.NET\Framework", Path.Combine(RuntimeEnvironment.GetSystemVersion(), "csc.exe"));
        }

        private static void Compile(Project Proj) {
            if(Proj.HasNoParsedOrIncompleteData()) {
                Console.WriteLine("Invalid parsed data from input JSON.");
                Environment.Exit(0);
            }

            if(Proj.FileName == null) {
                Console.WriteLine("No batch file input");
                Environment.Exit(0);
            }
            else if(Proj.FileName != null && !File.Exists(Proj.FileName)) {
                Console.WriteLine("Batch file doesn't exists: " + Proj.FileName);
                Environment.Exit(0);
            }

            if(Proj.Title == null)
                Proj.Title = new FileInfo(Proj.FileName ?? "").Name.Replace(".bat", "");

            if(Proj.Icon != null && Proj.Icon != "" && !File.Exists(Proj.Icon)) {
                Console.WriteLine("Icon file doesn't exist: " + Proj.Icon);
                Environment.Exit(0);
            }

            if(Proj.Output != null && File.Exists(Proj.Output)) {
                Console.WriteLine("Output file already exist: " + Proj.Output);
                Environment.Exit(0);
            }
            else if(Proj.Output == null)
                Proj.Output = Proj.FileName?.Substring(0, Proj.FileName.Length - 3) + "exe";

            string cscArgs = "/target:winexe /debug- /optimize+ /out:" + Proj.Output + " ";
            if(Proj.Icon != null && Proj.Icon != "")
                cscArgs += "/win32icon:" + Proj.Icon + " ";

            string template = Template.Wrapper;
            template = template.Replace("{title}", Proj.Title);
            template = template.Replace("{filename}", new FileInfo(Proj.FileName ?? "").Name);
            template = template.Replace("{script}", File.ReadAllText(Proj.FileName ?? "").Replace("\"", "\\\"").Replace("\r\n", "\\r\\n"));

            if(Proj.Arguments == null)
                template = template.Replace("{arguments}", "null");
            else template = template.Replace("{arguments}", "\"" + Proj.Arguments + "\"");

            if(Proj.DefaultWorkingDirectory == null)
                template = template.Replace("{def_cd}", "null");
            else template = template.Replace("{def_cd}", "\"" + Proj.DefaultWorkingDirectory + "\"");

            string tempFile = Guid.NewGuid().ToString() + ".cs";
            File.WriteAllText(tempFile, template);

            Process proc = new Process();
            proc.StartInfo.FileName = Program.GetCompilerFilePath();
            proc.StartInfo.Arguments = cscArgs + tempFile;
            proc.StartInfo.RedirectStandardOutput = false;
            proc.StartInfo.RedirectStandardError = false;
            proc.StartInfo.CreateNoWindow = true;
            proc.StartInfo.WindowStyle = ProcessWindowStyle.Hidden;
            proc.Start();
            proc.WaitForExit();

            File.Delete(tempFile);
            if(File.Exists(Proj.Output)) {
                Console.WriteLine("Done wrapping batch file to exe file.");
                Environment.Exit(2);
            }
            else {
                Console.WriteLine("Something went wrong while compiling.");
                Environment.Exit(1);
            }
        }

        private static void PrintHelp() {
            Console.WriteLine("Usage:");
            Console.WriteLine("batch2exe               Prints this message");
            Console.WriteLine("batch2exe -c <file>     Compiles an input JSON");
            Console.WriteLine("batch2exe -a            Show about information");
        }

        private static void PrintAbout() {
            Console.WriteLine("Batch2Exe is a program for converting batch");
            Console.WriteLine("files to executable files on Windows. It has a");
            Console.WriteLine("user-friendly CLI and GUI interface for input-");
            Console.WriteLine("ting information and uses advanced compression");
            Console.WriteLine("and obfuscation techniques for security.");

            Console.WriteLine("\r\nDeveloped by Group 3");
            Console.WriteLine("Leader:\r\n- Baldivicio, Jonathan Eldy I.\r\n");
            Console.WriteLine("Members:");
            Console.WriteLine("- Dela Pena, Jefford");
            Console.WriteLine("- Evaristo, Argie");
            Console.WriteLine("- Pago, Jhondel");
            Console.WriteLine("- Reyes, Damiel O.");
        }

        public static void Main(string[] Args) {
            Console.WriteLine("Batch2Exe v1.0");
            Console.WriteLine("------------------------------------------------");

            if(Args == null || Args.Length == 0)
                Program.PrintHelp();
            else if(Args[0] == "-a")
                Program.PrintAbout();
            else if(Args[0] == "-c") {
                if(Args.Length != 2)
                    Console.WriteLine("No defined input JSON file to be compiled.");
                else {
                    if(!File.Exists(Args[1]))
                        Console.WriteLine("File doesn't exists: " + Args[1]);
                    else Program.Compile(JsonSerializer.Deserialize<Project>(File.ReadAllText(Args[1])) ?? new Project());
                }
            }
            else Program.PrintHelp();
        }
    }
}