package com.bootstrappers.backend;
import java.util.ArrayList;
import java.util.Scanner;

public class MainTester {


    public void start(){


        System.out.println("****************************************");
        System.out.println("*** WELCOME TO HOME SEARCH PLATFORM! ***");
        System.out.println("****************************************");

        BootStrappers test = new BootStrappers();
        FileOperations fileOperations = new FileOperations();
        Scanner sc=new Scanner(System.in);
        System.out.print("Are you admin/user? (a/u): ");
        String type = sc.next();
        Integer choice=0;

        Member User = new Member("No name yet","No surname yet", "No password yet","0000","nomailyet@gmail.com","Member");
        Admin admin = new Admin("Erdogan","Sevilgen","2620001","esevilgen@gtu.edu.tr","12345","Admin");
        if(type.equals("u")) {
            System.out.print("1 for Sign up\n2 for Sign in\n");
            System.out.print("-> ");
            choice = sc.nextInt();
            if(choice.equals(1)) {
                System.out.print("Name: ");
                String name = sc.next();
                System.out.print("Surname: ");
                String surname = sc.next();
                System.out.print("E-mail: ");
                String mail = sc.next();
                System.out.print("Phone number: ");
                String phone = sc.next();
                System.out.print("Password: ");
                String password = sc.next();
                String usertype = "Member";

                User = new Member(name, surname, password, phone, mail, usertype);
                if(!User.registeration(test.getUserArrayList())) {
                    System.err.println("Bu mail zaten sisteme kayıtlı!");
                    return;
                }
                fileOperations.writeUserList(test.getUserArrayList());
            }
            else if(choice.equals(2)) {
                System.out.print("E-mail: ");
                String mail = sc.next();
                System.out.print("Password: ");
                String password = sc.next();

                User = new Member(null, null, password, null, mail, "Member");
                if(!User.login(test.getUserArrayList())) {
                    System.err.println("Yanlış mail veya şifre!");
                    return;
                }
            }
            else
                System.out.println("Invalid choice!");

            System.out.println("MENU: ");
            System.out.println("1. Add Advertisement");
            System.out.println("2. Remove Advertisement");
            System.out.println("3. Rent/Buy Advertisement");
            choice = sc.nextInt();

            switch (choice) {
                case 1: //add
                    System.out.println("Enter country:");
                    String country=sc.next();
                    System.out.println("Enter neighborhood:");
                    String neighborhood=sc.next();
                    Address newAddress = new Address("Istanbul", country, neighborhood);
                    Home newHome;
                    System.out.println("Enter type(Apartment/Family Home/Townhouse/...):");
                    String typeHome=sc.next();
                    System.out.println("Enter status(Rent/Buy):");
                    String status=sc.next();
                    System.out.println("Enter count of rooms:");
                    String countofRoom=sc.next();
                    System.out.println("Enter area(metrekare):");
                    int area=sc.nextInt();
                    System.out.println("Enter price:");
                    int price=sc.nextInt();
                    System.out.println("Enter distance:");
                    int distance =sc.nextInt();
                    if(type.equals("u")) {
                        Member owner = new Member(User.getName(), User.getSurname(), User.getPassword(), User.getPhoneNumber(), User.getEmail(), User.getUserType());
                        newHome = new Home(countofRoom, status, typeHome, newAddress, test.generateAdNumber(), owner, area, price, distance);
                        test.getHomeArrayList().add(newHome);
                        fileOperations.writeHomeList(test.getHomeArrayList());
                    }
                    else if(type.equals("a"))
                    {
                        Admin adminOwner = new Admin(admin.getName(),admin.getSurname(),admin.getPassword(),admin.getPhoneNumber(),admin.getEmail(),admin.getUserType());
                        newHome = new Home(countofRoom,status,typeHome,newAddress,test.generateAdNumber(),adminOwner,area,price, distance);
                        test.getHomeArrayList().add(newHome);
                        fileOperations.writeHomeList(test.getHomeArrayList());
                    }
                    else{
                        System.out.println("Invalid Value");
                        System.exit(0);
                    }
                    System.out.println("Add operations is successful");
                    break;
                case 2: //remove
                    if(User == null)
                        System.out.println("User Null");
                    System.out.println("Enter ad number:");
                    test.removeHome(sc.nextInt(), User);
                    System.out.println("Remove operation is successfull.");
                    break;
                case 3:
                    System.out.println("****** FILTERS ******");
                    System.out.print("Price lower limit(or -1): ");
                    int low = sc.nextInt();
                    System.out.print("Price upper limit(or -1): ");
                    int high = sc.nextInt();
                    System.out.print("Neighborhood(or null): ");
                    neighborhood = sc.next();
                    System.out.print("Count of rooms(or null):");
                    String countOfroom = sc.next();
                    System.out.print("Status(Rent/Sale/null):");
                    status = sc.next();
                    System.out.print("Type(Apartment/House/null):");
                    String typeofhome = sc.next();
                    System.out.print("Area: (or -1):");
                    area = sc.nextInt();

                    ArrayList<Home> filterHomes = new ArrayList<Home>();
                    filterHomes = test.filterHomes(neighborhood, countOfroom, status, typeofhome, low, high);

                    for(Home home : filterHomes)
                        System.out.println(home.toString());

                    System.out.print("Sort price by asc/desc/none: ");
                    String sortPrice = sc.next();
                    if(sortPrice.equals("asc")) {
                        test.displayPriorityQueue(test.generateAscendingOrderedQueue(filterHomes));
                    }
                    else if(sortPrice.equals("desc")) {
                        test.displayStack(test.generateDescendingOrderedStack(filterHomes));
                    }
                    else if(sortPrice.equals("none")) {
                        System.out.print("Sort area by asc/desc/none: ");
                        String sortArea = sc.next();
                        if (sortArea.equals("desc")) {
                            test.generateBSTforArea(filterHomes);
                            test.generateAscendingArea();
                            ArrayList<Integer> ascending = new ArrayList<Integer>();
                            ascending = test.getAscendingArea();
                            for (Integer area1 : ascending)
                                System.out.println("Area: " + area1);
                        } else if (sortArea.equals("asc")) {
                            test.generateBSTforArea(filterHomes);
                            test.generateDescendingArea();
                            ArrayList<Integer> descending = new ArrayList<Integer>();
                            descending = test.getDescendingArea();
                            for (Integer area1 : descending)
                                System.out.println("Area: " + area1);
                        } else if (sortArea.equals("none")) {
                            for (Home home : filterHomes)
                                System.out.println(home.toString());
                        }
                    }
                    break;
            }
        }
        else if(type.equals("a")) {
            System.out.print("E-mail: ");
            String mail = sc.next();
            System.out.print("Password: ");
            String password = sc.next();
            admin = new Admin(null, null, password, null, mail, "com.bootstrappers.backend.Admin");
            if(!admin.login(test.getUserArrayList())) {
                System.err.println("Yanlış mail veya şifre!");
                return;
            }
            System.out.println("MENU: ");
            System.out.println("1. Add Advertisement");
            System.out.println("2. Remove Advertisement");
            choice = sc.nextInt();

            switch (choice) {
                case 1: //add
                    System.out.println("Enter country:");
                    String country = sc.next();
                    System.out.println("Enter neighborhood:");
                    String neighborhood = sc.next();
                    Address newAddress = new Address("Istanbul", country, neighborhood);
                    Home newHome;
                    System.out.println("Enter type(Mustakil/Daire/Villa/...):");
                    String typeHome = sc.next();
                    System.out.println("Enter status(Rent/Buy):");
                    String status = sc.next();
                    System.out.println("Enter count of rooms:");
                    String countofRoom = sc.next();
                    System.out.println("Enter area(metrekare):");
                    int area = sc.nextInt();
                    System.out.println("Enter price:");
                    int price = sc.nextInt();
                    System.out.println("Enter distance:");
                    int distance =sc.nextInt();
                    if (type.equals("u")) {
                        Member owner = new Member(User.getName(), User.getSurname(), User.getPassword(), User.getPhoneNumber(), User.getEmail(), User.getUserType());
                        newHome = new Home(countofRoom, status, typeHome, newAddress, test.generateAdNumber(), owner, area, price, distance);
                        test.getHomeArrayList().add(newHome);
                        fileOperations.writeHomeList(test.getHomeArrayList());
                    } else if (type.equals("a")) {
                        Admin adminOwner = new Admin(admin.getName(), admin.getSurname(), admin.getPassword(), admin.getPhoneNumber(), admin.getEmail(), admin.getUserType());
                        newHome = new Home(countofRoom, status, typeHome, newAddress, test.generateAdNumber(), adminOwner, area, price, distance);
                        test.getHomeArrayList().add(newHome);
                        fileOperations.writeHomeList(test.getHomeArrayList());
                    } else {
                        System.out.println("Invalid Value");
                        System.exit(0);
                    }
                    System.out.println("Add operations is successful");
                    break;
                case 2: //remove
                    if (User == null)
                        System.out.println("User Null");
                    System.out.println("Enter ad number:");
                    test.removeHome(sc.nextInt(), admin);
                    System.out.println("Remove operation is successfull.");
                    break;


            }
        }


        /*for (Home it : test.getHomeArrayList())
        {
            System.out.println(it.getAddress().getNeighborhood());
        }*/

/*
        System.out.println("\nTest of Priority Queue:");
        test.generateAscendingOrderedQueue();
        test.displayPriorityQueue();

        test.generateDescendingOrderedStack();
        test.displayStack();

        System.out.println("\nTest of Binary Search Tree: ");
        test.generateBSTforArea(test.getHomeArrayList());
        test.generateDescendingArea();
        System.out.println("Descending Area: " + test.getDescendingArea());
        test.generateAscendingArea();
        System.out.println("Ascending Area: " + test.getAscendingArea());


        System.out.println("\nTest of filterHomes(..) method:");
        System.out.println("Find: " + test.filterHomes("Sisli", null, null,null,
                                        null, null));

        System.out.println("Find: " + test.filterHomes("Sisli", "1+1", null,null,
                null, null));

        System.out.println("Find: " + test.filterHomes("Kartal", null, null,null,
                null, null));

        System.out.println("Find: " + test.filterHomes("Fatih", null, null,null,
                null, null));

        System.out.println("Find: " + test.filterHomes(null, null, null,null,
                null, null));

   		System.out.println("\nTest of Map: ");
   		Map<String,Integer> map = new HashMap<>();
        map = test.generateTotalHomesInPlace(test.getHomeArrayList());
        System.out.println("##################################################");
        System.out.println("MAP Size : " + map.size());
        for (int i = 0; i < map.size(); ++i)
        {
            System.out.println(map.keySet().toArray()[i] + " -> " + map.get(map.keySet().toArray()[i]));
        }
        System.out.println("##################################################");
*/


    }
    public static void main(String arg[]){

      MainTester test= new MainTester();
      test.start();
    }

}
