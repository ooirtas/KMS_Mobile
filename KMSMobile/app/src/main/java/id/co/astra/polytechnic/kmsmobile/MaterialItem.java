package id.co.astra.polytechnic.kms_mobile;

public class MaterialItem {
    private String title;
    private String program;
    private String author;
    private String date;

    public MaterialItem(String title, String program, String author, String date) {
        this.title = title;
        this.program = program;
        this.author = author;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public String getProgram() {
        return program;
    }

    public String getAuthor() {
        return author;
    }

    public String getDate() {
        return date;
    }
}
