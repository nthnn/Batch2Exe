@echo off
if exist ..\src\Batch2Exe-GUI\out rmdir /s ..\src\Batch2Exe-GUI\out
if exist ..\src\Batch2Exe-Wrapper\bin rmdir /s ..\src\Batch2Exe-Wrapper\bin
if exist ..\src\Batch2Exe-Wrapper\obj rmdir /s ..\src\Batch2Exe-Wrapper\obj
if exist ..\src\Batch2Exe-Wrapper-Template\bin rmdir /s ..\src\Batch2Exe-Wrapper-Template\bin
if exist ..\src\Batch2Exe-Wrapper-Template\obj rmdir /s ..\src\Batch2Exe-Wrapper-Template\obj
if exist ..\dest\Batch2Exe-GUI.jar del ..\dest\Batch2Exe-GUI.jar
if exist ..\dest\Batch2Exe-Launcher.exe del ..\dest\Batch2Exe-Launcher.exe
if exist ..\dest\Batch2Exe-Wrapper.exe del ..\dest\Batch2Exe-Wrapper.exe
if exist ..\dest\Batch2Exe-Wrapper.dll del ..\dest\Batch2Exe-Wrapper.dll
if exist ..\dest\Batch2Exe-Wrapper.runtimeconfig.json del ..\dest\Batch2Exe-Wrapper.runtimeconfig.json
echo Done cleaning up...
pause