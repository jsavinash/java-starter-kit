package data.structure.array.size;

public class FixedArray implements IFixedArray {
  private String arr[];
  private int capacity;
  private int size;

  public FixedArray(int capacity) {
    this.capacity = capacity;
    this.size = 0;
    this.arr = new String[this.capacity];
  }

  public void insertAtEnd(String data) throws ArrayIndexOutOfBoundsException {
    try {
      this.arr[this.size] = data;
      this.size++;
    } catch (Exception e) {
      throw new ArrayIndexOutOfBoundsException("Index out of bounds");
    }
  }

  public String traverse() {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < this.size; i++) {
      if (i == 0) {
        sb.append(this.arr[i]);
      } else {
        sb.append(String.format(", " + this.arr[i]));
      }
    }
    return sb.toString();
  }
}
