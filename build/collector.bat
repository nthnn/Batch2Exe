@echo off
echo Moving relevant files...
if not exist ..\dest mkdir ..\dest
if exist ..\src\Batch2Exe-Wrapper\bin\Release\net6.0\Batch2Exe-Wrapper.dll move ..\src\Batch2Exe-Wrapper\bin\Release\net6.0\Batch2Exe-Wrapper.dll ..\dest\Batch2Exe-Wrapper.dll
if exist ..\src\Batch2Exe-Wrapper\bin\Release\net6.0\Batch2Exe-Wrapper.exe move ..\src\Batch2Exe-Wrapper\bin\Release\net6.0\Batch2Exe-Wrapper.exe ..\dest\Batch2Exe-Wrapper.exe
if exist ..\src\Batch2Exe-Wrapper\bin\Release\net6.0\Batch2Exe-Wrapper.runtimeconfig.json move ..\src\Batch2Exe-Wrapper\bin\Release\net6.0\Batch2Exe-Wrapper.runtimeconfig.json ..\dest\Batch2Exe-Wrapper.runtimeconfig.json
if exist ..\src\Batch2Exe-GUI\out\artifacts\Batch2Exe_GUI_jar\Batch2Exe-GUI.jar move ..\src\Batch2Exe-GUI\out\artifacts\Batch2Exe_GUI_jar\Batch2Exe-GUI.jar ..\dest\Batch2Exe-GUI.jar
echo Done moving to destination folder!
pause