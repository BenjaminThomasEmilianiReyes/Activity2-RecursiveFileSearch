import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class FileNode {
    String name;
    boolean isDirectory;
    List<FileNode> children;

    FileNode(String name, boolean isDirectory) {
        this.name = name;
        this.isDirectory = isDirectory;
        this.children = new ArrayList<>();
    }

    void addChild(FileNode child) {
        if (this.isDirectory) {
            this.children.add(child);
        }
    }

    void search(String extension, FileWriter writer) throws IOException {
        if (!isDirectory && name.endsWith(extension)) {
            writer.write(name + "\n");
            System.out.println("File found: " + name);
        }
        for (FileNode child : children) {
            child.search(extension, writer);
        }
    }
}

public class RecursiveFileSearch {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter file extension to search for: ");
        String extension = scanner.nextLine();
        scanner.close();

        FileNode root = new FileNode("root", true);
        root.addChild(new FileNode("notes.txt", false));
        root.addChild(new FileNode("todo.txt", false));

        try (FileWriter writer = new FileWriter("search_results.txt")) {
            root.search(extension, writer);
            System.out.println("Search completed.");
        } catch (IOException e) {
            System.out.println("Error writing to file.");
        }
    }
}