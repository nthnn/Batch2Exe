@echo off
candle -o ..\dest\installer.wxobj ..\src\Batch2Exe-MSI\installer.wxs
light -out ..\dest\Batch2Exe ..\dest\installer.wxobj
del ..\dest\installer.wxobj
del ..\dest\Batch2Exe.wixpdb
pause