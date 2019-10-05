package model;

import javax.persistence.*;

@Entity
@Table(name = "db_example.users")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String firstName;

    @Column(name = "surname")
    private String lastName;


    public User(){

    }

    public User(String firstName,String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User(long id,String firstName,String lastName){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString(){
        return "Фамилия:" + getLastName() +";" + "\n" +
                "\nИмя:"+ getFirstName() + ";\n";
    }
}
