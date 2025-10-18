package com.spacegame.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "players")
public class PlayerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private int health;

    @Column(nullable = false)
    private int money;

    @Column(name = "current_image")
    private String currentImage;

    @Column(name = "current_location")
    private String currentLocation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "player_inventory", joinColumns = @JoinColumn(name = "player_id"))
    @Column(name = "item")
    private Set<String> inventory = new HashSet<>();

    public void addItem(String item) {
        this.inventory.add(item);
    }

    public boolean removeFromInventory(String item){
        return inventory.remove(item);
    }

    // Пустой конструктор для Hibernate
    public PlayerEntity() {
        this.health = 100;
        this.money = 0;
    }

    // Геттеры и сеттеры
    public Integer getId() { return id; }
    public int getHealth() { return health; }
    public void setHealth(int health) { this.health = health; }

    public int getMoney() { return money; }
    public void setMoney(int money) { this.money = money; }

    public String getCurrentImage() { return currentImage; }
    public void setCurrentImage(String currentImage) { this.currentImage = currentImage; }

    public String getCurrentLocation() { return currentLocation; }
    public void setCurrentLocation(String currentLocation) { this.currentLocation = currentLocation; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Set<String> getInventory() { return inventory; }
    public void setInventory(Set<String> inventory) { this.inventory = inventory; }
}