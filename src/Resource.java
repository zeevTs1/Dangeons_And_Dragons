public class Resource {
    protected int amount;
    protected int capacity;

    public Resource(int amount, int capacity) {
        this.amount = amount;
        this.capacity = capacity;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void AddAmount(int givenAmount){
        this.amount+=givenAmount;
    }
    public void AddCapacity(int givenCapacity){
        this.capacity+=givenCapacity;
    }
    public void ReduceAmount(int givenAmount){
        this.amount-=givenAmount;
    }
    public void ReduceCapacity(int givenCapacity){
        this.capacity-=givenCapacity;
    }

    public void restore(){
        amount = capacity;
    }
    public String toString(){
        return String.format("%d%d",amount,capacity);
    }
}
