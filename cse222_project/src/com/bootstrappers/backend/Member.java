package com.bootstrappers.backend;

import javax.sound.midi.Soundbank;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import static java.io.File.separator;

public class Member extends SystemUserAbstract {

    public HashSet<Integer> savedHomes = new HashSet<Integer>();
    private static String delimiter=",";
    private static String separator="\n";

    public Member(String name, String surname, String password, String phoneNumber, String email, String userType) {
        super(name, surname, password, phoneNumber, email,userType);
    }

    @Override
    public boolean login(ArrayList<SystemUserAbstract> userList) {
        for (SystemUserAbstract target: userList) {
            if (target.getUserType().equals("com.bootstrappers.backend.Member")){
                if (target.getEmail().equals(this.getEmail())) {
                    if (target.getPassword().equals(this.getPassword()))
                        return true;
                }
            }
        }
        return false;
    }

    public boolean registeration(ArrayList<SystemUserAbstract> userList){
        for (SystemUserAbstract target : userList) {
            if (target.getEmail().equals(this.getEmail())){
                return false;
            }
        }
        userList.add(this);
        FileOperations fo = new FileOperations();
        fo.writeUserList(userList);
        return true;
    }

    public void saveHome(Integer adNumber) {

        BootStrappers bst=new BootStrappers();
        for(int i=0; i<bst.getHomeArrayList().size(); ++i) {
            if(bst.getHomeArrayList().get(i).getAdNumber() == adNumber) {

                savedHomes.add(bst.getHomeArrayList().get(i).getAdNumber());
                writeToFileFavorites(this.getEmail(), bst.getHomeArrayList().get(i));
            }
        }
        for (Integer ad: savedHomes) {
            System.out.println(ad);
        }
    }
    public void writeToFileFavorites(String email, Home home) {
        //String fileHeader = "Ad No, Province, Country, Neighborhood, Status, Type, Count Of Room, Owner's Contact Email, Area, Price, Distance";
        FileWriter fileWriter = null;
        try{
            fileWriter = new FileWriter("favorites.csv", true);

            fileWriter.write(email);
            fileWriter.write(delimiter);
            fileWriter.write(String.valueOf(home.getAdNumber()));
            fileWriter.write(delimiter);
            fileWriter.write(home.getAddress().getProvince());
            fileWriter.write(delimiter);
            fileWriter.write(home.getAddress().getCountry());
            fileWriter.write(delimiter);
            fileWriter.write(home.getAddress().getNeighborhood());
            fileWriter.write(delimiter);
            fileWriter.write(home.getStatus());
            fileWriter.write(delimiter);
            fileWriter.write(home.getType());
            fileWriter.write(delimiter);
            fileWriter.write(home.getCountOfRoom());
            fileWriter.write(delimiter);
            fileWriter.write(home.getOwner().getEmail());
            fileWriter.write(delimiter);
            fileWriter.write(String.valueOf(home.getArea()));
            fileWriter.write(delimiter);
            fileWriter.write(String.valueOf(home.getPrice()));
            fileWriter.write(delimiter);
            fileWriter.write(String.valueOf(home.getDistance()));
            fileWriter.write(separator);

        }
        catch(Exception e){
            System.err.println("Error: com.bootstrappers.backend.Home list was not written in the file.");
            e.printStackTrace();
        }
        finally{
            try{
                fileWriter.close();
            }
            catch(IOException e){
                System.err.println("Error: 'favorites.csv' file could not close.");
                e.printStackTrace();
            }
        }
    }
    public void readFavorites() {
        BootStrappers bst=new BootStrappers();
        int favUser=0, adNo = 1,  province = 2, country = 3, neighborhood = 4, status = 5, type = 6, countOfRoom = 7, contactEmail = 8, area = 9, price=10, distance = 11;
        BufferedReader fileReader = null;
        try{
            fileReader = new BufferedReader(new FileReader("favorites.csv"));
            String line;

            while((line=fileReader.readLine()) != null)
            {
                String[] tokens = line.split(delimiter);
                Address address = new Address(tokens[province], tokens[country], tokens[neighborhood]);

                Member user = null;

                if (this.getEmail().equals(tokens[favUser])) {
                    Home home = new Home(tokens[countOfRoom], tokens[status], tokens[type], address, Integer.parseInt(tokens[adNo]), user, Integer.parseInt(tokens[area]), Integer.parseInt(tokens[price]), Integer.parseInt((tokens[distance])));
                    this.savedHomes.add(home.getAdNumber());
                }

            }
            System.out.println("size: " + this.savedHomes.size());
            for (Integer ad: this.savedHomes) {
                System.out.println(ad);
            }
        }
        catch(Exception e){
            System.err.println("Error: 'favorites.csv' was not read in file.");
            e.printStackTrace();
        }
        finally{
            try{
                fileReader.close();
            }
            catch(IOException e){
                System.err.println("Error: 'favorites' file could not close.");
                e.printStackTrace();
            }
        }

    }

    public ArrayList getFavorites() {
        ArrayList<Integer> favs = new ArrayList<>();
        for(Integer adNumber : this.savedHomes)
            favs.add(adNumber);

        return favs;
    }

    public void deleteFav(int adNumber) {
        Iterator<Integer> iterator = savedHomes.iterator();
        while (iterator.hasNext()) {
            Integer element = iterator.next();
            if (element == adNumber) {
                iterator.remove();
            }
        }
    }
    @Override
    public String toString() {
        return this.getName() + " " + this.getSurname();
    }
}
