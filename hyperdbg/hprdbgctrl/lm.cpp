#include "pch.h"

using namespace std;

VOID CommandLmHelp() {
  ShowMessages(
      "lm : list kernel modules' base address, size, name and path.\n\n");
  ShowMessages("syntax : \tlm [name]\n");
  ShowMessages("\t\te.g : lm\n");
  ShowMessages("\t\t\tdescription : list all modules\n");
  ShowMessages("\t\te.g : lm nt\n");
  ShowMessages("\t\t\tdescription : search and show all modules that contain "
               "'nt' in their path or name\n");
}

int CommandLm(vector<string> SplittedCommand) {

  PRTL_PROCESS_MODULES ModuleInfo;
  NTSTATUS status;
  ULONG i;
  char *Search;

  if (SplittedCommand.size() >= 3) {
    ShowMessages("incorrect use of 'lm'\n\n");
    CommandLmHelp();
    return -1;
  }

  ModuleInfo = (PRTL_PROCESS_MODULES)VirtualAlloc(
      NULL, 1024 * 1024, MEM_COMMIT | MEM_RESERVE,
      PAGE_READWRITE); // Allocate memory for the module list

  if (!ModuleInfo) {
    ShowMessages("\nUnable to allocate memory for module list (%d)\n",
                 GetLastError());
    return -1;
  }

  if (!NT_SUCCESS(status = NtQuerySystemInformation(
                      (SYSTEM_INFORMATION_CLASS)11, ModuleInfo, 1024 * 1024,
                      NULL))) // 11 = SystemModuleInformation
  {
    ShowMessages("\nError: Unable to query module list (%#x)\n", status);

    VirtualFree(ModuleInfo, 0, MEM_RELEASE);
    return -1;
  }

  ShowMessages("start\t\t\tsize\tname\t\tpath\n\n");

  for (i = 0; i < ModuleInfo->NumberOfModules; i++) {
    //
    // Check if we need to search for the module or not
    //
    if (SplittedCommand.size() == 2) {
      Search = strstr((char *)ModuleInfo->Modules[i].FullPathName,
                      SplittedCommand.at(1).c_str());
      if (Search == NULL) {
        //
        // not found
        //
        continue;
      }
    }

    ShowMessages("%016llx\t", ModuleInfo->Modules[i].ImageBase);
    ShowMessages("%d\t", ModuleInfo->Modules[i].ImageSize);

    ShowMessages("%s\t", ModuleInfo->Modules[i].FullPathName +
                             ModuleInfo->Modules[i].OffsetToFileName);
    ShowMessages("%s\n", ModuleInfo->Modules[i].FullPathName);
  }

  VirtualFree(ModuleInfo, 0, MEM_RELEASE);
  return 0;
}
