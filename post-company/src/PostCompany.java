import java.util.*;

public class PostCompany {
    private int trucksCount;
    private int[] packages;

    PostCompany(int trucksCount, int[] packages) {
        this.trucksCount = trucksCount;
        this.packages = packages;
    }

    public Truck[] assignPackages() {
        PriorityQueue<Truck> truckQueue = new PriorityQueue<>();
        for (int i = 0; i < trucksCount; i++) {
            truckQueue.add(new Truck());
        }
        for (int p : packages) {
            Truck lightestTruck = truckQueue.poll();
            lightestTruck.addPackage(p);
            truckQueue.add(lightestTruck);
        }

        Truck[] trucks = new Truck[trucksCount];
        truckQueue.toArray(trucks);

        return trucks;
    }

    public void setPackages(int[] packages) {
        this.packages = packages;
    }

    public void setTrucksCount(int trucksCount) {
        this.trucksCount = trucksCount;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter number of trucks: ");
        int k = in.nextInt();
        System.out.print("Enter number of packages: ");
        int n = in.nextInt();
        System.out.println("Enter packages weight: ");
        int[] packages = new int[n];
        for (int i = 0; i < n; i++) {
            packages[i] = in.nextInt();
        }
        PostCompany postCompany = new PostCompany(k, packages);
        Truck[] trucks = postCompany.assignPackages();

        int maxWeight = -1;

        for (int i = 0; i < trucks.length; i++) {
            System.out.print("Truck " + i + "  packages :");
            System.out.print(trucks[i].getPackages());
            System.out.println(" -> Total weight : " + trucks[i].getWeight());
            if (trucks[i].getWeight() > maxWeight) {
                maxWeight = trucks[i].getWeight();
            }
        }

        System.out.println("Maximum weight : " + maxWeight);
    }
}
