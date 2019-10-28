public class Library {
    public static void main(String[] args) {


        final String appName = "Library v0.4";

        Book book1 = new Book("Core Java Volume 1 - Fundamentals", "Cay S. Horstman",
                2016, 872, "Pearson Education", "9788373271890");
        Book book2 = new Book("Brave New World", "Aldous Huxley", 1932, 311,
                "Vintage Classics", "8798654456664");

        System.out.println(appName);
        System.out.println("Available books");
        book1.printInfo();
        book2.printInfo();


    }

}
