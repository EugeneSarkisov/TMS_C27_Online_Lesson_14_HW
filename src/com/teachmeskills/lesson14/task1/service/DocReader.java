package com.teachmeskills.lesson14.task1.service;

/**
 * This class contain main method "readDocNumbers" which write the doc names and logs
 * into the files.
 */

import java.io.*;
import static com.teachmeskills.lesson14.task1.consts.PathConsts.*;
import static com.teachmeskills.lesson14.task1.consts.RegexConsts.*;


public class DocReader {
    public static void readDocNames() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(PATH_TO_DOC))) {
            FileWriter userLogsFileWriter = new FileWriter(PATH_TO_USER_LOGS, true);
            userLogsFileWriter.write("Starting program. Getting path to document..." + "\n");
            FileWriter docWriter = new FileWriter(PATH_TO_DOCNUM, true);
            FileWriter conWriter = new FileWriter(PATH_TO_CONTRACT, true);
            FileWriter errDocWriter = new FileWriter(PATH_TO_ERRDOC, true);
            String line;
            while ((line = br.readLine()) != null) {
                if(line.matches(DOCNUM_REGEX)){
                    docWriter.write(line + "\n");
                } else if(line.matches(CONTRACT_REGEX)){
                    conWriter.write(line + "\n");
                } else {
                    if (line.contains(" ")) {
                        errDocWriter.write(line + " :contains spaces" + "\n");
                    } else if (line.matches("\\W") || line.contains("_")) {
                        errDocWriter.write(line + " :contains meta symbols or underscores" + "\n");
                    } else {
                        errDocWriter.write(line + " :invalid doc name or length" + "\n");
                    }
                }
            }
            userLogsFileWriter.write("Successfully complete. Closing program.");
            docWriter.close();
            conWriter.close();
            errDocWriter.close();
            userLogsFileWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Can't find file! Please, check the filepath.");
            FileWriter userLogsFileWriter = new FileWriter(PATH_TO_USER_LOGS, true);
            FileWriter errorLogFileWriter = new FileWriter(PATH_TO_ERROR_LOGS, true);
            userLogsFileWriter.write("Path couldn't be find. Please, check the error logs." + "\n");
            errorLogFileWriter.write(e.getMessage());
            userLogsFileWriter.close();
            errorLogFileWriter.close();
        } catch (Exception e){
            System.out.println("Unexpected error. Please try later or contact the administrator");
            FileWriter userLogsFileWriter = new FileWriter(PATH_TO_USER_LOGS, true);
            FileWriter errorLogFileWriter = new FileWriter(PATH_TO_ERROR_LOGS, true);
            userLogsFileWriter.write("Unexpected error. Please, check the error logs." + "\n");
            errorLogFileWriter.write(e.getMessage());
            userLogsFileWriter.close();
            errorLogFileWriter.close();
            
        }
    }
}
