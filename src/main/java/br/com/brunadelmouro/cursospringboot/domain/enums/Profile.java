package br.com.brunadelmouro.cursospringboot.domain.enums;

public enum Profile {

    ADMIN(1, "ROLE_ADMIN"),
    CLIENTE(2, "ROLE_CLIENTE");

    private int cod;
    private String description;

    Profile(int cod, String description) {
        this.cod = cod;
        this.description = description;
    }

    public int getCod() {
        return cod;
    }

    public String getDescription() {
        return description;
    }

    //return object - enum(convert Integer to enum)
    public static Profile toEnum(Integer cod){

        if(cod == null) return null;

        for (Profile ct : Profile.values()) {
            if(cod.equals(ct.getCod()))
                return ct;
        }
        throw new IllegalArgumentException("Invalid id " + cod);
    }
}
