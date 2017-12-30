package com.frsummit.hr2.configuration;

import com.frsummit.hr2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.HashMap;
import java.util.Map;

public class LeaveConfig {

    @Autowired
    private UserService userService;

    @Value("${spring.query.leave-modify-to-list}")
    private String chainRoleList;


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////No need any more/////////////////////////////////////////////////////////////////////////////////////

    String role_user = "FACULTY CO-ORDINATOR CHAIRMAN DIN ADMIN";
    String role_faculty = "CO-ORDINATOR CHAIRMAN DIN ADMIN";
    String role_co_ordinator = "CHAIRMAN DIN ADMIN";
    String role_chairman = "DIN ADMIN";
    String role_din = "ADMIN";
    String role_admin = "ADMIN";

    String leave_chain = "USER FACULTY CO-ORDINATOR CHAIRMAN DIN ADMIN";


    public void setModifyRole(String myRole){
        String currentRole;
        String modifyRole;
        String[] parts = leave_chain.split(" ");
        for(int i=0; i<parts.length; i++){
            if(myRole.equalsIgnoreCase(parts[i])){
                if(myRole.equalsIgnoreCase(parts[parts.length - 1])){
                    System.out.println("Final Role : ADMIN");
                    break;
                }
                if(myRole.equalsIgnoreCase(parts[parts.length - parts.length])){
                    System.out.println("Final Role : USER");
                    break;
                }
                currentRole = parts[i];
                modifyRole = parts[i+1];
                System.out.println("Current Role : " + currentRole);
                System.out.println("Next Role : " +modifyRole);
                break;
            }
//            else if(myRole.equalsIgnoreCase("ADMIN"))
//                System.out.println(myRole);
//                System.out.println("Ignore Case");
//                break;
        }

        //return null;
    }

    public String returnNextRole(String myRole){
        String currentRole;
        String modifyRole;
        String[] parts = leave_chain.split(" ");
        for(int i = 0; i<parts.length; i++){
            if(myRole.equalsIgnoreCase(parts[i])){
                if(myRole.equalsIgnoreCase(parts[parts.length - 1]) || myRole.equalsIgnoreCase(parts[parts.length - parts.length])){
                    myRole = myRole;
                    break;
                }
                currentRole = parts[i];
                modifyRole = parts[i+1];
                myRole = modifyRole;
                break;
            }
        }
        return myRole;
    }

    public String returnNextRoleForLeaveApply(String myRole){
        String currentRole;
        String modifyRole;
        String[] parts = leave_chain.split(" ");
        for(int i = 0; i<parts.length; i++){
            if(myRole.equalsIgnoreCase(parts[i])){
                if(myRole.equalsIgnoreCase(parts[parts.length - 1])){
                    myRole = myRole;
                    break;
                }
                currentRole = parts[i];
                modifyRole = parts[i+1];
                myRole = modifyRole;
                break;
            }
        }
        return myRole;
    }

    public String getLastModifierRole(String myRole){
        String[] parts = leave_chain.split(" ");
        if(myRole.equalsIgnoreCase(parts[parts.length - 1]))
            return  myRole = parts[parts.length - 1];
        return null;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void testSplit(){
        String[] parts = role_user.split(" ");
        String part1 = parts[0];
        String part2 = parts[1];
        String part3 = parts[2];
        String part4 = parts[3];
        String part5 = parts[4];
        System.out.println(part1 + " " + part2 + " " + part3 + " " + part4 + " " + part5);
    }

    public void testMap(){
        Map<String, String> myMap = new HashMap<>();
        myMap.put("2014200000071", "Summit");
        myMap.put("USER", "FACULTY CO-ORDINATOR CHAIRMAN DEAN");

        System.out.println("------------------------------");
        System.out.println(myMap);

        System.out.println("------------------------------");
        for(Map.Entry<String, String> entry : myMap.entrySet()){
            //System.out.println("Id : " + entry.getKey() + "    " + "Name : " + entry.getValue());
            if(entry.getKey().equalsIgnoreCase("USER")){
                String[] parts = entry.getValue().split(" ");
                for(int i=0; i<parts.length; i++)
                    System.out.println("Step " + i + " = " + parts[i]);
            }
        }

        System.out.println("-------------------------------");
        myMap.forEach((x, y) -> System.out.println("Id : " + x + "    " + "Name : " + y + "    " + y.length()));
    }

    public void testMapForRole(String myRole, String myChain){
        String nextRole;
        Map<String, String> myMap = new HashMap<>();
        myMap.put(myRole, myChain);

        System.out.println("------------------------------");
        for(Map.Entry<String, String> entry : myMap.entrySet()){
            System.out.println("Role : " + entry.getKey() + "\n" + "Chain : " + entry.getValue());
            if(entry.getKey().equalsIgnoreCase(myRole)){
                String[] parts = entry.getValue().split(" ");
                for(int i=0; i<parts.length; i++){
                    System.out.println("Step " + i + " = " + parts[i]);
                }
                nextRole = parts[0];
                System.out.println("Next Role is : " + nextRole);
            }
        }
    }
}
