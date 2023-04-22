@echo off
echo Building Batch2Exe launcher...
echo id ICON "..\\logos\\batch2exe-logo.ico" > ..\dest\batch2exe-launcher.rc
windres ..\dest\batch2exe-launcher.rc -O coff -o ..\dest\batch2exe-launcher.res
g++ -mwindows -o../dest/Batch2Exe-Launcher.exe -std=c++17 ..\src\Batch2Exe-Launcher\batch2exe-launcher.cpp ..\dest\batch2exe-launcher.res
del ..\dest\batch2exe-launcher.rc
del ..\dest\batch2exe-launcher.res
echo Done building the launcher!
pause