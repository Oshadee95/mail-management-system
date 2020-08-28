/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package configurations;

/**
 *
 * @author RED-HAWK
 */
public class StorageConfig {

    private static final String SPACE_NAME = "";
    private static final String SPACE_REGION = "";
    private static final String SPACE_ENDPOINT = "";
    private static final String SPACE_KEY = "";
    private static final String SPACE_SECRET = "";
    private final static String SEPERATOR = "/";
    private String folderName, fileName, pathToLocalFile;

    public static String getSpaceName() {
        return SPACE_NAME;
    }

    public static String getSpaceRegion() {
        return SPACE_REGION;
    }

    public static String getSpaceEndpoint() {
        return SPACE_ENDPOINT;
    }

    public static String getSpaceSecret() {
        return SPACE_SECRET;
    }

    public static String getSpaceKey() {
        return SPACE_KEY;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return this.folderName + SEPERATOR + this.fileName;
    }

    public void setPathToLocalFile(String pathToLocalFile) {
        this.pathToLocalFile = pathToLocalFile;
    }

    public String getPathToLocalFile() {
        return this.pathToLocalFile;
    }
}
