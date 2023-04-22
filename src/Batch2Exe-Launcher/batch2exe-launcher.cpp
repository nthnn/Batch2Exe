#include <stdio.h>
#include <string>
#include <windows.h>

using namespace std;

int WINAPI WinMain(HINSTANCE hInstance,
	HINSTANCE hPrevInstance,
	LPSTR lpCmdLine,
	int nCmdShow) {
	STARTUPINFOW process_startup_info{ 0 };
	PROCESS_INFORMATION process_info{ 0 };

	wstring params = L"java -jar Batch2Exe-GUI.jar";
	process_startup_info.cb = sizeof(process_startup_info);
	process_startup_info.wShowWindow = SW_HIDE;

	if(CreateProcessW(NULL, (LPWSTR) params.data(), NULL, NULL, TRUE, CREATE_NO_WINDOW, NULL, NULL, &process_startup_info, &process_info)) {
		WaitForSingleObject(process_info.hProcess, INFINITE);

		CloseHandle(process_info.hProcess);
		CloseHandle(process_info.hThread);
	}

	return 0;
}