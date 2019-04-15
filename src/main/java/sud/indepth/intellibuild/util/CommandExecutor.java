package sud.indepth.intellibuild.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

import static sud.indepth.intellibuild.util.Printer.println;

/**
 * Created by sud on 14/4/19.
 */
public class CommandExecutor {
  private static final Runtime RUNTIME = Runtime.getRuntime();

  public static List<String> executeCommand(String command) throws IOException, InterruptedException {
    Printer.println("Executing command: " + command);
    Process process = RUNTIME.exec(command);
    process.waitFor();
    return getCommandOutput(process);
  }

  private static List<String> getCommandOutput(Process process) throws IOException {
    BufferedReader reader = new BufferedReader(
        new InputStreamReader(process.getInputStream()));
    return reader.lines().collect(Collectors.toList());
  }
}
