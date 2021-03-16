package br.com.lucashilles.keystone.utils;

public class ConstraintHelper {

    static String getMessage(String constraintName) {
        switch (constraintName) {
            case "email_sys_user":
                return "Já existe um usuário com este e-mail.";
            default:
                return "Violação de chave única: " + constraintName;
        }
    }

}
