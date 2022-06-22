public class Employee {

    private String name;
    private String position;
    private String office;

    public Employee(String name, String position, String office) {
        this.name = name;
        this.position = position;
        this.office = office;
    }

    @Override
    public String toString() {
        return name + " " + position + " " + office;
    }
}
