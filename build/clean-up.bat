@echo off
if exist ..\src\Batch2Exe-GUI\out rmdir /s ..\src\Batch2Exe-GUI\out
if exist ..\src\Batch2Exe-Wrapper\bin rmdir /s ..\src\Batch2Exe-Wrapper\bin
if exist ..\src\Batch2Exe-Wrapper\obj rmdir /s ..\src\Batch2Exe-Wrapper\obj
if exist ..\src\Batch2Exe-Wrapper-Template\bin rmdir /s ..\src\Batch2Exe-Wrapper-Template\bin
if exist ..\src\Batch2Exe-Wrapper-Template\obj rmdir /s ..\src\Batch2Exe-Wrapper-Template\obj
echo Done cleaning up...
pause