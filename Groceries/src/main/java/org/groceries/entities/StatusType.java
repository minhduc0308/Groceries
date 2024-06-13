package org.groceries.entities;

public enum StatusType {
    Shipped, Cancelled, Processing, Completed;

    @Override
    public String toString(){
        switch (this) {
            case Shipped:
                return "Shipped";
            case Cancelled:
                return "Cancelled";
            case Processing:
                return "Processing";
            case Completed:
                return "Completed";
            default:
                throw new UnsupportedOperationException();
        }
    }
}
