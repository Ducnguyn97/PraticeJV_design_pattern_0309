public class Booksingleton {
    private String author;
    private String title;
    private static Booksingleton book;
    private static boolean isLoanedOut;

    private Booksingleton(){
        author = "Gama, Helm, Jonhson, and Vlissides";
        title = "Design Pattern";
        isLoanedOut = false;
    }
    public static Booksingleton borrowBook(){
        if(!isLoanedOut){
            if(book == null){
                book = new Booksingleton();
            }
            isLoanedOut = true;
            return book;
        }
        return null;
    }
    public void returnBook(Booksingleton bookReturned){
      isLoanedOut = false;
    }
    public String getAuthor(){
        return author;
    }
    public String getTitle(){
        return title;
    }
    public String getAuthorAndTitle(){
        return author + ", " + title;
    }
}
