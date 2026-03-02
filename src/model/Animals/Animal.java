package model.Animals;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Animal {
    protected StringProperty  name;
    protected IntegerProperty age;
    protected BooleanProperty isAdopted;

    public Animal(String name, int age) {
        this.name       = new SimpleStringProperty(name);
        this.age        = new SimpleIntegerProperty(age);
        this.isAdopted  = new SimpleBooleanProperty(false);
    }

    public String getName()            { return name.get(); }
    public boolean isAdopted()         { return isAdopted.get(); }
    public void    adopt()             { isAdopted.set(true); }

    public StringProperty  nameProperty()       { return name; }
    public IntegerProperty ageProperty()        { return age; }
    public StringProperty  typeProperty() {
        switch (getClass().getSimpleName()) {
            case "Cat":    return new SimpleStringProperty("Cat");
            case "Dog":    return new SimpleStringProperty("Dog");
            case "Rabbit": return new SimpleStringProperty("Rabbit");
        }
        return new SimpleStringProperty("");
    }

    public StringProperty isAdoptedProperty() {
        return isAdopted()
            ? new SimpleStringProperty("Adopted")
            : new SimpleStringProperty("Available");
    }

    @Override
    public String toString() {
        // Use String.format instead of '+' concatenation
        return String.format("%s (Age: %d)", name.get(), age.get());
    }
}
