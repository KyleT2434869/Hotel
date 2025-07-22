public class Hotel {
    private Room standard;
    private Room deluxe;
    private Room suite;

    public Hotel() {
        standard.setValues(100, 1, 250);
        deluxe.setValues(50, 2, 500);
        suite.setValues(5, 5, 1000);

         System.out.println(" Hotel configuration updated by pushpak");

         System.out.println("Second update: Print statement added");
    }
}
