package ac.ku.Lorna.models;

/**
 * FedhaGroup (models)
 * Created by: user
 * On: 10/5/2024 8:14 AM
 * Description:
 **/

//CREATE TABLE members (
//id SERIAL PRIMARY KEY,              -- Auto-incrementing ID for each member
//name VARCHAR(100) NOT NULL,         -- Member's name (up to 100 characters)
//age INT NOT NULL,                   -- Member's age (integer)
//shares DECIMAL(10, 2) NOT NULL,     -- Member's shares (decimal with 2 decimal places)
//registration_fee DECIMAL(10, 2) NOT NULL -- Registration fee (decimal with 2 decimal places)
//);

public class Member {
    private String name;
    private int age;
    private double shares;
    private double registrationFee;

    public Member(String name, int age, double shares, double registrationFee) {
        this.name = name;
        this.age = age;
        this.shares = shares;
        this.registrationFee = registrationFee;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getShares() {
        return shares;
    }

    public void setShares(double shares) {
        this.shares = shares;
    }

    public double getRegistrationFee() {
        return registrationFee;
    }

    public void setRegistrationFee(double registrationFee) {
        this.registrationFee = registrationFee;
    }

    @Override
    public String toString() {
        return String.format("%s (Age: %d, Shares: %.2f, Registration Fee: %.2f)", name, age, shares, registrationFee);
    }
}
