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
        this.amount = Math.min(amount,capacity);
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void AddAmount(int givenAmount){
        this.amount = Math.min(this.amount+givenAmount, capacity);
    }
    public void AddCapacity(int givenCapacity){
        this.capacity+=givenCapacity;
    }
    public void ReduceAmount(int givenAmount){
        this.amount = Math.max(this.amount-givenAmount, 0);
    }

    public void restore(){
        amount = capacity;
    }
    public String toString(){
        return String.format("%d/%d",amount,capacity);
    }
}
