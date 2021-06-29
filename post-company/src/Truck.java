import java.util.LinkedList;

public class Truck implements Comparable<Truck> {
    private int weight = 0;
    private final LinkedList<Integer> packages = new LinkedList<>();

    public void addPackage(int pack) {
        packages.add(pack);
        weight += pack;
    }

    public int getWeight() {
        return weight;
    }

    public LinkedList<Integer> getPackages() {
        return packages;
    }

    @Override
    public int compareTo(Truck truck) {
        return Integer.compare(weight, truck.getWeight());
    }
}
