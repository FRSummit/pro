/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package computerstore;

/**
 *
 * @author F R SUMMIT
 */
public class Manager {
    private String id;
    private String name;

    public Manager() {
    }

    public Manager(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Manager{" + "id=" + id + ", name=" + name + '}';
    }
    
}
