package com.spacegame.admin;

public class ResetDatabase{
    public static void main(String[] args) {
        new AdminService().deleteAllAccount();
    }
}
