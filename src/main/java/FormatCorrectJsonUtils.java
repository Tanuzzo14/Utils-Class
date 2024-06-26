import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class FormatCorrectJsonUtils {
    private static final String JSON_EXT = ".json";
    private static final String UNDERSCORE_CORRECT = "correct";

    public static void main(String[] args) {

        // Note: Specify the path of the JSON file to clean
        // It can be an absolute path, for example: "C:/absolute/path/your_file.json"
        // Or a relative path, for example: "subdirectory/your_file.json"
        // If the file is in the same directory as the program, just the file name is enough: "your_file.json"

        formatJson("C:\\Users\\Utente\\Desktop\\Request_IMAGE_BASE64.json");
    }

    private static void formatJson(String fileJsonPath) {

        // Name of the JSON file to clean
        String filePathToCorrect = fileJsonPath.replaceAll(JSON_EXT, "");
        String cleanJsonFileName = filePathToCorrect + UNDERSCORE_CORRECT + JSON_EXT;

        try {
            // read  JSON file content
            String jsonString = readJsonFile(fileJsonPath);

            // Remove invalid char
            String jsonStringPulito = removeNotValidChars(jsonString);

            // Save correct Json
            writeCorrectJsonFile(cleanJsonFileName, jsonStringPulito);

            System.out.println("Caratteri non validi rimossi e JSON pulito salvato come '" + cleanJsonFileName + "'");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String removeNotValidChars(String jsonString) {
            // Define the regex to find invalid characters
        return jsonString.replaceAll("[^\\x20-\\x7E]", "");
    }

    public static String readJsonFile(String nomeFile) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(nomeFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString();
    }

    public static void writeCorrectJsonFile(String nomeFile, String contenuto) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nomeFile))) {
            bw.write(contenuto);
        }
    }
}
