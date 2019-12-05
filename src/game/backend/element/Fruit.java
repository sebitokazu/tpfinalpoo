package game.backend.element;

public class Fruit extends Element {

    private FruitType type;

    public Fruit(){}

    public Fruit(FruitType type){ this.type = type; }

    public FruitType getType(){ return type; }

    @Override
    public boolean isMovable() { return true; }

    @Override
    public int hashCode() { return ((type == null) ? 0 : type.hashCode()); }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Fruit))
            return false;
        Fruit other = (Fruit) obj;
        if (type != other.type)
            return false;
        return true;
    }

    @Override
    public String getFullKey() {
        return type.toString() + "-FRUIT";
    }

    @Override
    public String getKey() { return "FRUIT"; }
}
